package com.cc.sms.service;

import com.cc.sms.bean.Grade;

import java.util.List;

/**
 * @author 多瑞c
 * @date 2021/11/23 22:35
 */
public interface GradeService {
    //根据管理员姓名查询指定的管理员信息
    List<Grade> selectList(Grade gradename);

    //根据年级名称查询指定年级信息
    Grade findByName(String gradename);

    //添加年级信息
    int insert(Grade grade);

    //根据id修改指定年级信息
    int update(Grade grade);

    //根据id删除指定的年级信息
    int deleteById(Integer[] ids);

    //查询所有年级列表信息
    List<Grade> selectAll();
}
