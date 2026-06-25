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
 * 活动信息：(ActivityInformation)表实体类
 *
 */
@TableName("`activity_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class ActivityInformation implements Serializable {

    // ActivityInformation编号
    @TableId(value = "activity_information_id", type = IdType.AUTO)
    private Integer activity_information_id;

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
    // 活动封面
    @TableField(value = "`activity_cover`")
    private String activity_cover;
    // 活动目标
    @TableField(value = "`activity_objectives`")
    private String activity_objectives;
    // 限制人数
    @TableField(value = "`limit_the_number_of_people`")
    private Double limit_the_number_of_people;
    // 是否收费
    @TableField(value = "`whether_to_charge`")
    private String whether_to_charge;
    // 截止时间
    @TableField(value = "`deadline`")
    private Timestamp deadline;
    // 参与条件
    @TableField(value = "`conditions_for_participation`")
    private String conditions_for_participation;
    // 活动内容
    @TableField(value = "`activity_content`")
    private String activity_content;
    // 活动详情
    @TableField(value = "`event_details`")
    private String event_details;

    // 点击数
    @TableField(value = "hits")
    private Integer hits;

    // 点赞数
    @TableField(value = "praise_len")
    private Integer praise_len;

    // 收藏数
    @TableField(value = "collect_len")
    private Integer collect_len;

    // 评论数
    @TableField(value = "comment_len")
    private Integer comment_len;

    // 审核状态
    @TableField(value = "examine_state")
    private String examine_state;














		// 报名限制次数
	@TableField(value = "enrollment_information_limit_times")
	private String enrollment_information_limit_times;
	
				


	// 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}
