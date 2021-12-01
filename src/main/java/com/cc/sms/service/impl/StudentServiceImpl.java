package com.cc.sms.service.impl;

import com.cc.sms.bean.LoginForm;
import com.cc.sms.bean.Student;
import com.cc.sms.dao.StudentMapper;
import com.cc.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 学生登录
     * @param loginForm
     * @return
     */
    @Override
    public Student login(LoginForm loginForm) {
        return studentMapper.login(loginForm);
    }

    /**
     * 根据id修改指定用户密码
     * @param student
     * @return
     */
    @Override
    public int updatePassword(Student student) {
        return studentMapper.updatePassword(student);
    }

    /**
     * 根据学生与班级名查询指定/全部学生信息列表
     * @param student
     * @return
     */
    @Override
    public List<Student> selectList(Student student) {
        return studentMapper.selectList(student);
    }

    /**
     * 根据学号获取指定学生信息
     * @param student
     * @return
     */
    @Override
    public Student findBySno(Student student) {
        return studentMapper.findBySno(student);
    }

    /**
     * 添加班级信息
     * @param student
     * @return
     */
    @Override
    public int insert(Student student) {
        return studentMapper.insert(student);
    }

    /**
     * 根据id修改指定学生信息
     * @param student
     * @return
     */
    @Override
    public int update(Student student) {
        return studentMapper.update(student);
    }

    /**
     * 根据id删除指定的学生信息
     * @param ids
     * @return
     */
    @Override
    public int deleteById(Integer[] ids) {
        return studentMapper.deleteById(ids);
    }
}
