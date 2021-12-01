package com.cc.sms.dao;

import com.cc.sms.bean.Clazz;

import java.util.List;

/**
 * @author 多瑞c
 * @date 2021/11/24 11:56
 */
public interface ClazzMapper {
    //根据班级与年级名查询指定/全部班级信息
    List<Clazz> selectList(Clazz clazz);

    //根据班级名获取指定班级信息
    Clazz findByName(String name);

    //添加班级信息
    int insert(Clazz clazz);

    //根据id修改指定的班级信息
    int update(Clazz clazz);

    //删除指定id的班级信息
    int deleteById(Integer[] ids);

    //查询所有班级信息列表
    List<Clazz> selectAll();
}
