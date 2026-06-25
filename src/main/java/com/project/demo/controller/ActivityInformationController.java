package com.project.demo.controller;

import com.project.demo.entity.ActivityInformation;
import com.project.demo.entity.EnrollmentInformation;
import com.project.demo.service.ActivityNotificationService;
import com.project.demo.service.ActivityInformationService;
import com.project.demo.service.EnrollmentInformationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 活动信息：(ActivityInformation)表控制层
 *
 */
@RestController
@RequestMapping("/activity_information")
public class ActivityInformationController extends BaseController<ActivityInformation, ActivityInformationService> {

    /**
     * 活动信息对象
     */
    @Autowired
    public ActivityInformationController(ActivityInformationService service) {
        setService(service);
    }

    @Autowired
    private EnrollmentInformationService enrollmentInformationService;

    @Autowired
    private ActivityNotificationService activityNotificationService;



    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapactivity_number = new HashMap<>();
        mapactivity_number.put("activity_number",String.valueOf(paramMap.get("activity_number")));
        List listactivity_number = service.selectBaseList(service.select(mapactivity_number, new HashMap<>()));
        if (listactivity_number.size()>0){
            return error(30000, "字段活动编号内容不能重复");
        }
        this.addMap(paramMap);
        ActivityInformation activity = findActivity(String.valueOf(paramMap.get("activity_number")));
        if (activity != null) {
            pushActivitySubmitNotice(activity);
        }
        return success(1);
    }

    @PostMapping("/set")
    @Transactional
    public Map<String, Object> set(HttpServletRequest request) throws IOException {
        Map<String, String> query = service.readQuery(request);
        Map<String, String> config = service.readConfig(request);
        Map<String, Object> body = service.readBody(request.getReader());

        Integer activityId = parseInteger(query.get("activity_information_id"));
        ActivityInformation before = activityId == null ? null : findActivity(activityId);
        service.update(query, config, body);

        if (body.get("examine_state") != null && activityId != null) {
            String newState = String.valueOf(body.get("examine_state"));
            String oldState = before == null ? null : before.getExamine_state();
            if (!newState.equals(oldState) && ("已通过".equals(newState) || "未通过".equals(newState))) {
                ActivityInformation after = findActivity(activityId);
                if (after != null) {
                    pushActivityAuditNotice(after, newState);
                }
            }
        }
        return success(1);
    }

    @RequestMapping("/hot_rank")
    public Map<String, Object> hotRank(HttpServletRequest request) {
        String size = request.getParameter("size");
        int limit = 10;
        if (size != null && !"".equals(size)) {
            try {
                limit = Integer.parseInt(size);
            } catch (NumberFormatException e) {
                limit = 10;
            }
        }
        if (limit < 1) {
            limit = 10;
        }
        if (limit > 50) {
            limit = 50;
        }
        String sql = "SELECT ai.activity_information_id, ai.activity_number, ai.event_name, ai.activity_type, ai.activity_time, ai.activity_location, ai.activity_cover, " +
                "IFNULL(ai.hits,0) AS hits, IFNULL(ai.praise_len,0) AS praise_len, IFNULL(ai.collect_len,0) AS collect_len, IFNULL(ai.comment_len,0) AS comment_len, " +
                "IFNULL(enroll.signup_count,0) AS signup_count, IFNULL(checkin.checkin_count,0) AS checkin_count, " +
                "(IFNULL(ai.hits,0) + IFNULL(ai.praise_len,0) * 3 + IFNULL(ai.collect_len,0) * 5 + IFNULL(ai.comment_len,0) * 2 + IFNULL(enroll.signup_count,0) * 4 + IFNULL(checkin.checkin_count,0) * 6) AS heat_score " +
                "FROM `activity_information` ai " +
                "LEFT JOIN (SELECT activity_number, COUNT(*) AS signup_count FROM `enrollment_information` WHERE examine_state = '已通过' GROUP BY activity_number) enroll ON enroll.activity_number = ai.activity_number " +
                "LEFT JOIN (SELECT activity_number, COUNT(*) AS checkin_count FROM `activity_checkin` GROUP BY activity_number) checkin ON checkin.activity_number = ai.activity_number " +
                "WHERE ai.examine_state = '已通过' " +
                "ORDER BY heat_score DESC, ai.create_time DESC LIMIT " + limit;
        List<Map<String, Object>> list = service.selectMapBaseList(sql);
        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("count", list.size());
        return success(result);
    }

    private ActivityInformation findActivity(Integer activityId) {
        if (activityId == null) {
            return null;
        }
        Map<String, String> query = new HashMap<>();
        query.put("activity_information_id", String.valueOf(activityId));
        List list = service.selectBaseList(service.select(query, new HashMap<>()));
        if (list.size() == 0) {
            return null;
        }
        return (ActivityInformation) list.get(0);
    }

    private ActivityInformation findActivity(String activityNumber) {
        if (activityNumber == null || "".equals(activityNumber) || "null".equals(activityNumber)) {
            return null;
        }
        Map<String, String> query = new HashMap<>();
        query.put("activity_number", activityNumber);
        List list = service.selectBaseList(service.select(query, new HashMap<>()));
        if (list.size() == 0) {
            return null;
        }
        return (ActivityInformation) list.get(0);
    }

    private void pushActivitySubmitNotice(ActivityInformation activity) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Map<String, Object> notice = buildActivityNotice(activity, now);
        notice.put("notification_title", "活动提交成功");
        notice.put("notification_type", "活动通知");
        notice.put("notification_time", now);
        notice.put("notification_content", "您提交的活动[" + nullToEmpty(activity.getEvent_name()) + "]已提交成功，请等待审核。");
        activityNotificationService.insert(notice);
    }

    private void pushActivityAuditNotice(ActivityInformation activity, String examineState) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Map<String, Object> notice = buildActivityNotice(activity, now);
        String eventName = nullToEmpty(activity.getEvent_name());
        notice.put("notification_title", "已通过".equals(examineState) ? "活动审核通过" : "活动审核未通过");
        notice.put("notification_type", "审核通知");
        notice.put("notification_time", now);
        if ("已通过".equals(examineState)) {
            notice.put("notification_content", "您提交的活动[" + eventName + "]已审核通过，学生现在可以查看并报名。");
        } else {
            notice.put("notification_content", "您提交的活动[" + eventName + "]审核未通过，请修改后重新提交。");
        }
        activityNotificationService.insert(notice);
    }

    private Map<String, Object> buildActivityNotice(ActivityInformation activity, Timestamp now) {
        Map<String, Object> notice = new HashMap<>();
        notice.put("teacher_user", activity.getTeacher_user());
        notice.put("activity_number", activity.getActivity_number());
        notice.put("event_name", activity.getEvent_name());
        notice.put("activity_type", activity.getActivity_type());
        notice.put("activity_time", activity.getActivity_time());
        notice.put("activity_location", activity.getActivity_location());
        notice.put("source_table", "activity_information");
        notice.put("source_id", activity.getActivity_information_id());
        notice.put("source_user_id", activity.getTeacher_user());
        notice.put("create_time", now);
        notice.put("update_time", now);
        return notice;
    }

    private Integer parseInteger(String value) {
        if (value == null || "".equals(value)) {
            return null;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String nullToEmpty(Object value) {
        return value == null ? "" : String.valueOf(value);
    }

    @PostMapping("/push_notice")
    @Transactional
    public Map<String, Object> pushNotice(@RequestBody Map<String, Object> body) {
        String activityNumber = String.valueOf(body.get("activity_number"));
        String title = body.get("notification_title") == null ? "活动通知" : String.valueOf(body.get("notification_title"));
        String content = body.get("notification_content") == null ? "" : String.valueOf(body.get("notification_content"));
        if (activityNumber == null || "null".equals(activityNumber) || "".equals(activityNumber)) {
            return error(30000, "活动编号不能为空");
        }

        Map<String, String> query = new HashMap<>();
        query.put("activity_number", activityNumber);
        query.put("examine_state", "已通过");
        List enrollments = enrollmentInformationService.selectBaseList(enrollmentInformationService.select(query, new HashMap<>()));
        if (enrollments.size() == 0) {
            return error(30000, "没有审核通过的报名用户");
        }

        Date nowDate = new Date();
        java.sql.Timestamp now = new java.sql.Timestamp(nowDate.getTime());
        for (Object item : enrollments) {
            EnrollmentInformation enrollment = (EnrollmentInformation) item;
            Map<String, Object> notice = new HashMap<>();
            notice.put("teacher_user", enrollment.getTeacher_user());
            notice.put("activity_number", enrollment.getActivity_number());
            notice.put("event_name", enrollment.getEvent_name());
            notice.put("activity_type", enrollment.getActivity_type());
            notice.put("activity_time", enrollment.getActivity_time());
            notice.put("activity_location", enrollment.getActivity_location());
            notice.put("student_users", enrollment.getStudent_users());
            notice.put("student_name", enrollment.getStudent_name());
            notice.put("notification_title", title);
            notice.put("notification_type", "站内信");
            notice.put("notification_time", now);
            notice.put("notification_content", content);
            notice.put("source_table", "activity_information");
            notice.put("source_id", enrollment.getEnrollment_information_id());
            notice.put("source_user_id", enrollment.getStudent_users());
            notice.put("create_time", now);
            notice.put("update_time", now);
            activityNotificationService.insert(notice);
        }
        return success(enrollments.size());
    }


}
