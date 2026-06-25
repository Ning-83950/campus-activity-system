package com.project.demo.dao;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.ActivityCheckin;
import org.apache.ibatis.annotations.Mapper;

/**
 * 活动签到记录Mapper接口
 */
@Mapper
public interface ActivityCheckinMapper extends BaseMapper<ActivityCheckin> {
}
