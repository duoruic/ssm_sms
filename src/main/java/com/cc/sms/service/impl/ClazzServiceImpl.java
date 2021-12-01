package com.cc.sms.service.impl;

import com.cc.sms.bean.Clazz;
import com.cc.sms.dao.ClazzMapper;
import com.cc.sms.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 多瑞c
 * @date 2021/11/24 11:39
 */
@Service
@Transactional
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 根据班级与年级名查询指定/全部班级信息
     * @param clazz
     * @return
     */
    @Override
    public List<Clazz> selectList(Clazz clazz) {
        return clazzMapper.selectList(clazz);
    }

    /**
     * 根据班级名获取指定班级信息
     * @param name
     * @return
     */
    @Override
    public Clazz findByName(String name) {
        return clazzMapper.findByName(name);
    }

    /**
     * 添加班级信息
     * @param clazz
     * @return
     */
    @Override
    public int insert(Clazz clazz) {
        return clazzMapper.insert(clazz);
    }

    /**
     * 根据id修改指定的班级信息
     * @param clazz
     * @return
     */
    @Override
    public int update(Clazz clazz) {
        return clazzMapper.update(clazz);
    }

    /**
     * 删除指定id的班级信息
     * @param ids
     * @return
     */
    @Override
    public int deleteById(Integer[] ids) {
        return clazzMapper.deleteById(ids);
    }

    /**
     * 查询所有班级信息列表
     * @return
     */
    @Override
    public List<Clazz> selectAll() {
        return clazzMapper.selectAll();
    }
}
