package com.project.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 活动签到记录
 */
@TableName("`activity_checkin`")
@Data
@EqualsAndHashCode(callSuper = false)
public class ActivityCheckin implements Serializable {

    @TableId(value = "activity_checkin_id", type = IdType.AUTO)
    private Integer activity_checkin_id;

    @TableField(value = "`teacher_user`")
    private Integer teacher_user;

    @TableField(value = "`activity_number`")
    private String activity_number;

    @TableField(value = "`event_name`")
    private String event_name;

    @TableField(value = "`activity_type`")
    private String activity_type;

    @TableField(value = "`activity_time`")
    private String activity_time;

    @TableField(value = "`activity_location`")
    private String activity_location;

    @TableField(value = "`student_users`")
    private Integer student_users;

    @TableField(value = "`student_name`")
    private String student_name;

    @TableField(value = "`enrollment_information_id`")
    private Integer enrollment_information_id;

    @TableField(value = "`checkin_type`")
    private String checkin_type;

    @TableField(value = "`checkin_time`")
    private Timestamp checkin_time;

    @TableField(value = "`checkin_status`")
    private String checkin_status;

    @TableField(value = "`certificate_no`")
    private String certificate_no;

    @TableField(value = "source_table")
    private String source_table;

    @TableField(value = "source_id")
    private Integer source_id;

    @TableField(value = "source_user_id")
    private Integer source_user_id;

    @TableField(value = "update_time")
    private Timestamp update_time;

    @TableField(value = "create_time")
    private Timestamp create_time;
}
