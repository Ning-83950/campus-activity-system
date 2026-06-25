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
 * 教师用户：(TeacherUser)表实体类
 *
 */
@TableName("`teacher_user`")
@Data
@EqualsAndHashCode(callSuper = false)
public class TeacherUser implements Serializable {

    // TeacherUser编号
    @TableId(value = "teacher_user_id", type = IdType.AUTO)
    private Integer teacher_user_id;

    // 教师工号
    @TableField(value = "`teacher_no`")
    private String teacher_no;
    // 教师姓名
    @TableField(value = "`teachers_name`")
    private String teachers_name;









    // 用户编号
    @TableField(value = "user_id")
    private Integer userId;











				


	// 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}
