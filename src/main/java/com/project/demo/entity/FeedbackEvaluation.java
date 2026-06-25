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
 * 反馈评价：(FeedbackEvaluation)表实体类
 *
 */
@TableName("`feedback_evaluation`")
@Data
@EqualsAndHashCode(callSuper = false)
public class FeedbackEvaluation implements Serializable {

    // FeedbackEvaluation编号
    @TableId(value = "feedback_evaluation_id", type = IdType.AUTO)
    private Integer feedback_evaluation_id;

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
    // 报名人数
    @TableField(value = "`number_of_enrolment`")
    private String number_of_enrolment;
    // 参加人数
    @TableField(value = "`number_of_participants`")
    private String number_of_participants;
    // 活动评分
    @TableField(value = "`activity_rating`")
    private Double activity_rating;
    // 评价内容
    @TableField(value = "`evaluation_content`")
    private String evaluation_content;
    // 反馈内容
    @TableField(value = "`feedback_content`")
    private String feedback_content;




















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
