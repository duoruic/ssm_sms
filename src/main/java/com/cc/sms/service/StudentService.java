package com.cc.sms.service;

import com.cc.sms.bean.LoginForm;
import com.cc.sms.bean.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface StudentService {
    /**
     * 学生登录
     * @param loginForm
     * @return
     */
    Student login(LoginForm loginForm);

    /**
     * 根据id修改指定用户密码
     * @param student
     * @return
     */
    int updatePassword(Student student);

    /**
     * 根据学生与班级名查询指定/全部学生信息列表
     * @param student
     * @return
     */
    List<Student> selectList(Student student);

    /**
     * 根据学号获取指定学生信息
     * @param student
     * @return
     */
    Student findBySno(Student student);

    /**
     * 添加班级信息
     * @param student
     * @return
     */
    int insert(Student student);

    /**
     * 根据id修改指定学生信息
     * @param student
     * @return
     */
    int update(Student student);

    /**
     * 根据id删除指定的学生信息
     * @param ids
     * @return
     */
    int deleteById(Integer[] ids);
}
