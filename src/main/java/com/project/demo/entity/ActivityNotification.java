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
 * 活动通知：(ActivityNotification)表实体类
 *
 */
@TableName("`activity_notification`")
@Data
@EqualsAndHashCode(callSuper = false)
public class ActivityNotification implements Serializable {

    // ActivityNotification编号
    @TableId(value = "activity_notification_id", type = IdType.AUTO)
    private Integer activity_notification_id;

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
    // 通知标题
    @TableField(value = "`notification_title`")
    private String notification_title;
    // 通知类型
    @TableField(value = "`notification_type`")
    private String notification_type;
    // 通知时间
    @TableField(value = "`notification_time`")
    private Timestamp notification_time;
    // 通知内容
    @TableField(value = "`notification_content`")
    private String notification_content;




















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
