package com.project.demo.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.project.demo.controller.base.BaseController;
import com.project.demo.entity.AccessToken;
import com.project.demo.entity.ActivityCheckin;
import com.project.demo.entity.EnrollmentInformation;
import com.project.demo.service.ActivityCheckinService;
import com.project.demo.service.ActivityNotificationService;
import com.project.demo.service.EnrollmentInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * 活动签到记录控制层
 */
@RestController
@RequestMapping("/activity_checkin")
public class ActivityCheckinController extends BaseController<ActivityCheckin, ActivityCheckinService> {

    @Autowired
    public ActivityCheckinController(ActivityCheckinService service) {
        setService(service);
    }

    @Autowired
    private EnrollmentInformationService enrollmentInformationService;

    @Autowired
    private ActivityNotificationService activityNotificationService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${app.checkin.host:}")
    private String checkinHost;

    @Value("${app.checkin.home-port:8081}")
    private String checkinHomePort;

    @Value("${app.checkin.protocol:http}")
    private String checkinProtocol;

    @PostMapping("/manual")
    @Transactional
    public Map<String, Object> manualCheckin(@RequestBody Map<String, Object> body) {
        EnrollmentInformation enrollment = findEnrollment(body);
        if (enrollment == null) {
            return error(30000, "未找到报名记录");
        }
        return createCheckin(enrollment, "手动签到");
    }

    @PostMapping("/scan")
    @Transactional
    public Map<String, Object> scanCheckin(@RequestBody Map<String, Object> body, HttpServletRequest request) {
        Integer userId = tokenGetUserId(request.getHeader("x-auth-token"));
        if (userId == null || userId == 0) {
            return error(10000, "用户未登录");
        }
        Object activityNumber = body.get("activity_number");
        if (activityNumber == null || StringUtils.isEmpty(String.valueOf(activityNumber))) {
            return error(30000, "活动编号不能为空");
        }
        Map<String, Object> query = new HashMap<>();
        query.put("activity_number", String.valueOf(activityNumber));
        query.put("student_users", userId);
        EnrollmentInformation enrollment = findEnrollment(query);
        if (enrollment == null) {
            return error(30000, "未找到当前用户的报名记录");
        }
        return createCheckin(enrollment, "二维码签到");
    }

    @GetMapping("/qr")
    public void qr(String activity_number, HttpServletRequest request, HttpServletResponse response) throws IOException, WriterException {
        if (StringUtils.isEmpty(activity_number)) {
            response.sendError(400, "activity_number required");
            return;
        }
        String host = StringUtils.isEmpty(checkinHost) ? request.getServerName() : checkinHost;
        String port = StringUtils.isEmpty(checkinHomePort) ? "" : ":" + checkinHomePort;
        String protocol = StringUtils.isEmpty(checkinProtocol) ? "http" : checkinProtocol;
        String target = protocol + "://" + host + port + "/#/activity_checkin/scan?activity_number=" + URLEncoder.encode(activity_number, "UTF-8");
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix matrix = new MultiFormatWriter().encode(target, BarcodeFormat.QR_CODE, 260, 260, hints);
        response.setContentType("image/png");
        MatrixToImageWriter.writeToStream(matrix, "PNG", response.getOutputStream());
    }

    @GetMapping("/certificate")
    public void certificate(Integer activity_checkin_id, HttpServletResponse response) throws IOException, DocumentException {
        if (activity_checkin_id == null) {
            response.sendError(400, "activity_checkin_id required");
            return;
        }
        Map<String, String> query = new HashMap<>();
        query.put("activity_checkin_id", String.valueOf(activity_checkin_id));
        List list = service.selectBaseList(service.select(query, new HashMap<>()));
        if (list.size() == 0) {
            response.sendError(404, "certificate not found");
            return;
        }
        ActivityCheckin checkin = (ActivityCheckin) list.get(0);
        String filename = URLEncoder.encode("参与证明-" + checkin.getCertificate_no() + ".pdf", "UTF-8");
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        writeCertificatePdf(checkin, response);
    }

    private Map<String, Object> createCheckin(EnrollmentInformation enrollment, String checkinType) {
        if (!"已通过".equals(enrollment.getExamine_state())) {
            return error(30000, "报名审核通过后才能签到");
        }
        Map<String, String> duplicateQuery = new HashMap<>();
        duplicateQuery.put("activity_number", enrollment.getActivity_number());
        duplicateQuery.put("student_users", String.valueOf(enrollment.getStudent_users()));
        List duplicates = service.selectBaseList(service.select(duplicateQuery, new HashMap<>()));
        if (duplicates.size() > 0) {
            return success(duplicates.get(0));
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());
        Map<String, Object> map = new HashMap<>();
        map.put("teacher_user", enrollment.getTeacher_user());
        map.put("activity_number", enrollment.getActivity_number());
        map.put("event_name", enrollment.getEvent_name());
        map.put("activity_type", enrollment.getActivity_type());
        map.put("activity_time", enrollment.getActivity_time());
        map.put("activity_location", enrollment.getActivity_location());
        map.put("student_users", enrollment.getStudent_users());
        map.put("student_name", enrollment.getStudent_name());
        map.put("enrollment_information_id", enrollment.getEnrollment_information_id());
        map.put("checkin_type", checkinType);
        map.put("checkin_time", now);
        map.put("checkin_status", "已签到");
        map.put("certificate_no", "CERT-" + System.currentTimeMillis() + "-" + enrollment.getStudent_users());
        map.put("source_table", "enrollment_information");
        map.put("source_id", enrollment.getEnrollment_information_id());
        map.put("source_user_id", enrollment.getStudent_users());
        map.put("create_time", now);
        map.put("update_time", now);
        service.insert(map);
        pushCheckinNotice(enrollment, checkinType, now);
        List records = service.selectBaseList(service.select(duplicateQuery, new HashMap<>()));
        if (records.size() > 0) {
            return success(records.get(0));
        }
        return success(1);
    }

    private EnrollmentInformation findEnrollment(Map<String, Object> body) {
        Map<String, String> query = new HashMap<>();
        if (body.get("enrollment_information_id") != null) {
            query.put("enrollment_information_id", String.valueOf(body.get("enrollment_information_id")));
        } else {
            if (body.get("activity_number") != null) {
                query.put("activity_number", String.valueOf(body.get("activity_number")));
            }
            if (body.get("student_users") != null) {
                query.put("student_users", String.valueOf(body.get("student_users")));
            }
        }
        if (query.size() == 0) {
            return null;
        }
        List list = enrollmentInformationService.selectBaseList(enrollmentInformationService.select(query, new HashMap<>()));
        if (list.size() == 0) {
            return null;
        }
        return (EnrollmentInformation) list.get(0);
    }

    private void pushCheckinNotice(EnrollmentInformation enrollment, String checkinType, Timestamp now) {
        Map<String, Object> notice = new HashMap<>();
        notice.put("teacher_user", enrollment.getTeacher_user());
        notice.put("activity_number", enrollment.getActivity_number());
        notice.put("event_name", enrollment.getEvent_name());
        notice.put("activity_type", enrollment.getActivity_type());
        notice.put("activity_time", enrollment.getActivity_time());
        notice.put("activity_location", enrollment.getActivity_location());
        notice.put("student_users", enrollment.getStudent_users());
        notice.put("student_name", enrollment.getStudent_name());
        notice.put("notification_title", "活动签到成功");
        notice.put("notification_type", "签到通知");
        notice.put("notification_time", now);
        notice.put("notification_content", "您已完成活动[" + enrollment.getEvent_name() + "]的" + checkinType + "。");
        notice.put("source_table", "activity_checkin");
        notice.put("source_id", enrollment.getEnrollment_information_id());
        notice.put("source_user_id", enrollment.getStudent_users());
        notice.put("create_time", now);
        notice.put("update_time", now);
        activityNotificationService.insert(notice);
    }

    private void writeCertificatePdf(ActivityCheckin checkin, HttpServletResponse response) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        BaseFont baseFont = createChineseBaseFont();
        Font titleFont = new Font(baseFont, 24, Font.BOLD);
        Font bodyFont = new Font(baseFont, 14, Font.NORMAL);

        Paragraph title = new Paragraph("活动参与证明", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(36);
        document.add(title);

        document.add(new Paragraph("证明编号：" + checkin.getCertificate_no(), bodyFont));
        document.add(new Paragraph("学生姓名：" + nullToEmpty(checkin.getStudent_name()), bodyFont));
        document.add(new Paragraph("活动名称：" + nullToEmpty(checkin.getEvent_name()), bodyFont));
        document.add(new Paragraph("活动编号：" + nullToEmpty(checkin.getActivity_number()), bodyFont));
        document.add(new Paragraph("活动类型：" + nullToEmpty(checkin.getActivity_type()), bodyFont));
        document.add(new Paragraph("活动时间：" + nullToEmpty(checkin.getActivity_time()), bodyFont));
        document.add(new Paragraph("活动地点：" + nullToEmpty(checkin.getActivity_location()), bodyFont));
        document.add(new Paragraph("签到方式：" + nullToEmpty(checkin.getCheckin_type()), bodyFont));
        document.add(new Paragraph("签到时间：" + nullToEmpty(checkin.getCheckin_time()), bodyFont));

        Paragraph footer = new Paragraph("特此证明。", bodyFont);
        footer.setSpacingBefore(40);
        document.add(footer);
        document.close();
    }

    private BaseFont createChineseBaseFont() throws IOException, DocumentException {
        File simsun = new File("C:/Windows/Fonts/simsun.ttc");
        if (simsun.exists()) {
            return BaseFont.createFont("C:/Windows/Fonts/simsun.ttc,0", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        }
        return BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
    }

    private String nullToEmpty(Object value) {
        return value == null ? "" : String.valueOf(value);
    }

    private Integer tokenGetUserId(String token) {
        if (token == null || "".equals(token)) {
            return 0;
        }
        AccessToken byToken = (AccessToken) redisTemplate.opsForValue().get(token);
        if (byToken == null) {
            return 0;
        }
        return byToken.getUser_id();
    }
}
