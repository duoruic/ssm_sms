package com.cc.sms.service.impl;

import com.cc.sms.bean.LoginForm;
import com.cc.sms.bean.Teacher;
import com.cc.sms.dao.TeacherMapper;
import com.cc.sms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 老师登录
     * @param loginForm
     * @return
     */
    @Override
    public Teacher login(LoginForm loginForm) {
        return teacherMapper.login(loginForm);
    }

    /**
     * 根据id修改指定用户密码
     * @param teacher
     * @return
     */
    @Override
    public int updatePassword(Teacher teacher) {
        return teacherMapper.updatePassword(teacher);
    }

    /**
     * 根据教师与班级名查询指定/全部教师信息
     * @param teacher
     * @return
     */
    @Override
    public List<Teacher> selectList(Teacher teacher) {
        return teacherMapper.selectList(teacher);
    }

    /**
     * 根据工号查询指定教师信息
     * @param teacher
     * @return
     */
    @Override
    public Teacher findByTno(Teacher teacher) {
        return teacherMapper.findByTno(teacher);
    }

    /**
     * 添加教师信息
     * @param teacher
     * @return
     */
    @Override
    public int insert(Teacher teacher) {
        return teacherMapper.insert(teacher);
    }

    /**
     * 根据id修改指定教师信息
     * @param teacher
     * @return
     */
    @Override
    public int update(Teacher teacher) {
        return teacherMapper.update(teacher);
    }

    /**
     * 根据id删除指定教师信息
     * @param ids
     * @return
     */
    @Override
    public int deleteById(Integer[] ids) {
        return teacherMapper.deleteById(ids);
    }
}
