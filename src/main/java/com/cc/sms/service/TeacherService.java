package com.cc.sms.service;

import com.cc.sms.bean.LoginForm;
import com.cc.sms.bean.Teacher;

import java.util.List;

public interface TeacherService {
    //老师登录
    Teacher login(LoginForm loginForm);

    //根据id修改指定用户密码
    int updatePassword(Teacher teacher);

    //根据教师与班级名查询指定/全部教师信息
    List<Teacher> selectList(Teacher teacher);

    //根据工号查询指定教师信息
    Teacher findByTno(Teacher teacher);

    //添加教师信息
    int insert(Teacher teacher);

    //根据id修改指定教师信息
    int update(Teacher teacher);

    //根据id删除指定教师信息
    int deleteById(Integer[] ids);
}
