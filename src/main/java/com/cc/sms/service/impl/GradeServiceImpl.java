package com.cc.sms.service.impl;

import com.cc.sms.bean.Grade;
import com.cc.sms.dao.GradeMapper;
import com.cc.sms.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 多瑞c
 * @date 2021/11/23 22:35
 */
@Service
@Transactional
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    /**
     * 根据管理员姓名查询指定的管理员信息
     * @param gradename
     * @return
     */
    @Override
    public List<Grade> selectList(Grade gradename) {
        return gradeMapper.selectList(gradename);
    }

    /**
     * 根据年级名称查询指定年级信息
     * @param gradename
     * @return
     */
    @Override
    public Grade findByName(String gradename) {
        return gradeMapper.findByName(gradename);
    }

    /**
     * 添加年级信息
     * @param grade
     * @return
     */
    @Override
    public int insert(Grade grade) {
        return gradeMapper.insert(grade);
    }

    /**
     * 根据id修改指定年级信息
     * @param grade
     * @return
     */
    @Override
    public int update(Grade grade) {
        return gradeMapper.update(grade);
    }

    /**
     * 根据id删除指定的年级信息
     * @param ids
     * @return
     */
    @Override
    public int deleteById(Integer[] ids) {
        return gradeMapper.deleteById(ids);
    }

    /**
     * 查询所有年级列表信息
     * @return
     */
    @Override
    public List<Grade> selectAll() {
        return gradeMapper.selectAll();
    }
}
