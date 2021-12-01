package com.cc.sms.dao;

import com.cc.sms.bean.LoginForm;
import com.cc.sms.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    //学生登录
    Student login(LoginForm loginForm);

    //根据id修改指定用户密码
    int updatePassword(Student student);

    //根据学生与班级名查询指定/全部学生信息列表
    List<Student> selectList(Student student);

    //根据学号获取指定学生信息
    Student findBySno(Student student);

    //添加班级信息
    int insert(Student student);

    //根据id修改指定学生信息
    int update(Student student);

    //根据id删除指定的学生信息
    int deleteById(Integer[] ids);
}
