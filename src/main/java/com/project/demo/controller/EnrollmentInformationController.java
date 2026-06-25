package com.project.demo.controller;

import com.project.demo.entity.EnrollmentInformation;
import com.project.demo.service.ActivityNotificationService;
import com.project.demo.service.EnrollmentInformationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;


/**
 * 报名信息：(EnrollmentInformation)表控制层
 *
 */
@RestController
@RequestMapping("/enrollment_information")
public class EnrollmentInformationController extends BaseController<EnrollmentInformation, EnrollmentInformationService> {

    /**
     * 报名信息对象
     */
    @Autowired
    public EnrollmentInformationController(EnrollmentInformationService service) {
        setService(service);
    }

    @Autowired
    private ActivityNotificationService activityNotificationService;



    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        String sql = "SELECT MAX(enrollment_information_id) AS max FROM "+"`enrollment_information`";
        Integer max = service.selectBaseCount(sql);
        sql = ("SELECT count(*) count FROM `activity_information` INNER JOIN `enrollment_information` ON activity_information.activity_number=enrollment_information.activity_number WHERE activity_information.limit_the_number_of_people < enrollment_information.number_of_enrolment AND enrollment_information.enrollment_information_id="+max).replaceAll("&#60;","<");
        Integer count = service.selectBaseCount(sql);
        if(count>0){
            sql = "delete from "+"enrollment_information"+" WHERE "+"enrollment_information_id"+" ="+max;
            service.deleteBaseSql(sql);
            return error(30000,"活动人数已满");
        }
        sql = ("SELECT count(*) count FROM `activity_information` INNER JOIN `enrollment_information` ON activity_information.activity_number=enrollment_information.activity_number WHERE activity_information.deadline < enrollment_information.registration_date AND enrollment_information.enrollment_information_id="+max).replaceAll("&#60;","<");
        Integer count2 = service.selectBaseCount(sql);
        if(count2>0){
            sql = "delete from "+"enrollment_information"+" WHERE "+"enrollment_information_id"+" ="+max;
            service.deleteBaseSql(sql);
            return error(30000,"已经超过截止时间");
        }
        sql = "UPDATE `activity_information` INNER JOIN `enrollment_information` ON activity_information.activity_number=enrollment_information.activity_number SET activity_information.limit_the_number_of_people= activity_information.limit_the_number_of_people - enrollment_information.number_of_enrolment WHERE enrollment_information.enrollment_information_id="+max;
        service.updateBaseSql(sql);
        EnrollmentInformation enrollment = findEnrollment(max);
        if (enrollment != null) {
            pushEnrollmentNotice(enrollment);
        }
        return success(1);
    }

    @PostMapping("/set")
    @Transactional
    public Map<String, Object> set(HttpServletRequest request) throws IOException {
        Map<String, String> query = service.readQuery(request);
        Map<String, String> config = service.readConfig(request);
        Map<String, Object> body = service.readBody(request.getReader());

        Integer enrollmentId = parseInteger(query.get("enrollment_information_id"));
        EnrollmentInformation before = enrollmentId == null ? null : findEnrollment(enrollmentId);
        service.update(query, config, body);

        if (body.get("examine_state") != null && enrollmentId != null) {
            String newState = String.valueOf(body.get("examine_state"));
            String oldState = before == null ? null : before.getExamine_state();
            if (!newState.equals(oldState) && ("已通过".equals(newState) || "未通过".equals(newState))) {
                EnrollmentInformation after = findEnrollment(enrollmentId);
                if (after != null) {
                    pushAuditNotice(after, newState);
                }
            }
        }
        return success(1);
    }

    private EnrollmentInformation findEnrollment(Integer enrollmentId) {
        if (enrollmentId == null) {
            return null;
        }
        Map<String, String> query = new HashMap<>();
        query.put("enrollment_information_id", String.valueOf(enrollmentId));
        List list = service.selectBaseList(service.select(query, new HashMap<>()));
        if (list.size() == 0) {
            return null;
        }
        return (EnrollmentInformation) list.get(0);
    }

    private void pushEnrollmentNotice(EnrollmentInformation enrollment) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Map<String, Object> notice = buildNotice(enrollment, now);
        notice.put("notification_title", "活动报名成功");
        notice.put("notification_type", "报名通知");
        notice.put("notification_time", now);
        notice.put("notification_content", "您已提交活动[" + nullToEmpty(enrollment.getEvent_name()) + "]的报名申请，请等待审核。");
        activityNotificationService.insert(notice);
    }

    private void pushAuditNotice(EnrollmentInformation enrollment, String examineState) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Map<String, Object> notice = buildNotice(enrollment, now);
        String eventName = nullToEmpty(enrollment.getEvent_name());
        String reply = nullToEmpty(enrollment.getExamine_reply());
        notice.put("notification_title", "已通过".equals(examineState) ? "报名审核通过" : "报名审核未通过");
        notice.put("notification_type", "审核通知");
        notice.put("notification_time", now);
        if ("已通过".equals(examineState)) {
            notice.put("notification_content", "您报名的活动[" + eventName + "]已审核通过，请按活动安排参加。");
        } else {
            String content = "您报名的活动[" + eventName + "]审核未通过。";
            if (!"".equals(reply)) {
                content += "审核回复：" + reply;
            }
            notice.put("notification_content", content);
        }
        activityNotificationService.insert(notice);
    }

    private Map<String, Object> buildNotice(EnrollmentInformation enrollment, Timestamp now) {
        Map<String, Object> notice = new HashMap<>();
        notice.put("teacher_user", enrollment.getTeacher_user());
        notice.put("activity_number", enrollment.getActivity_number());
        notice.put("event_name", enrollment.getEvent_name());
        notice.put("activity_type", enrollment.getActivity_type());
        notice.put("activity_time", enrollment.getActivity_time());
        notice.put("activity_location", enrollment.getActivity_location());
        notice.put("student_users", enrollment.getStudent_users());
        notice.put("student_name", enrollment.getStudent_name());
        notice.put("source_table", "enrollment_information");
        notice.put("source_id", enrollment.getEnrollment_information_id());
        notice.put("source_user_id", enrollment.getStudent_users());
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


}
