package com.project.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 报名信息：(EnrollmentInformation)表实体类
 *
 */
@TableName("`enrollment_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class EnrollmentInformation implements Serializable {

    // EnrollmentInformation编号
    @TableId(value = "enrollment_information_id", type = IdType.AUTO)
    private Integer enrollment_information_id;

    // 教师用户
    @TableField(value = "`teacher_user`")
    private Integer teacher_user;
    // 活动编号
    @TableField(value = "`activity_number`")
    private String activity_number;
    // 活动名称
    @TableField(value = "`event_name`")
    private String event_name;
    // 活动类型
    @TableField(value = "`activity_type`")
    private String activity_type;
    // 活动时间
    @TableField(value = "`activity_time`")
    private String activity_time;
    // 活动地点
    @TableField(value = "`activity_location`")
    private String activity_location;
    // 学生用户
    @TableField(value = "`student_users`")
    private Integer student_users;
    // 学生姓名
    @TableField(value = "`student_name`")
    private String student_name;
    // 报名日期
    @TableField(value = "`registration_date`")
    private String registration_date;
    // 报名人数
    @TableField(value = "`number_of_enrolment`")
    private String number_of_enrolment;
    // 报名申请
    @TableField(value = "`application_for_registration`")
    private String application_for_registration;
    // 活动进度
    @TableField(value = "`activity_progress`")
    private String activity_progress;





    // 审核状态
    @TableField(value = "examine_state")
    private String examine_state;



    // 审核回复
    @TableField(value = "examine_reply")
    private String examine_reply;











		// 通知限制次数
	@TableField(value = "activity_notification_limit_times")
	private String activity_notification_limit_times;
		// 费用限制次数
	@TableField(value = "activity_costs_limit_times")
	private String activity_costs_limit_times;
		// 评价限制次数
	@TableField(value = "feedback_evaluation_limit_times")
	private String feedback_evaluation_limit_times;
	
				// 来源表
	@TableField(value = "source_table")
	private String source_table;
	
	// 来源ID
	@TableField(value = "source_id")
	private Integer source_id;
	
	// 来源用户ID
	@TableField(value = "source_user_id")
	private Integer source_user_id;
		


	// 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}
