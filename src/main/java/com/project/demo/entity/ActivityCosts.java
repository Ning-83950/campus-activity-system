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
 * 活动费用：(ActivityCosts)表实体类
 *
 */
@TableName("`activity_costs`")
@Data
@EqualsAndHashCode(callSuper = false)
public class ActivityCosts implements Serializable {

    // ActivityCosts编号
    @TableId(value = "activity_costs_id", type = IdType.AUTO)
    private Integer activity_costs_id;

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
    // 费用项目
    @TableField(value = "`expense_item`")
    private String expense_item;
    // 费用金额
    @TableField(value = "`amount_of_expenses`")
    private Double amount_of_expenses;
    // 费用明细
    @TableField(value = "`expense_details`")
    private String expense_details;






    // 支付状态
    @TableField(value = "pay_state")
    private String pay_state;

    // 支付类型: 微信、支付宝、网银
    @TableField(value = "pay_type")
    private String pay_type;














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
