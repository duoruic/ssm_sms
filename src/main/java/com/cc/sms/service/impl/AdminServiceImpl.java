package com.cc.sms.service.impl;

import com.cc.sms.bean.Admin;
import com.cc.sms.bean.LoginForm;
import com.cc.sms.dao.AdminMapper;
import com.cc.sms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 管理员登录
     * @param loginForm
     * @return
     */
    @Override
    public Admin login(LoginForm loginForm) {
        return adminMapper.login(loginForm);
    }

    /**
     * 根据管理员姓名查询指定/所有的管理员信息
     * @param admin
     * @return
     */
    @Override
    public List<Admin> selectList(Admin admin) {
        return adminMapper.selectList(admin);
    }

    /**
     * 根据用户名查询指定的管理员信息
     * @param name
     * @return
     */
    @Override
    public Admin findByName(String name) {
        return adminMapper.findByName(name);
    }

    /**
     * 添加管理员信息
     * @param admin
     * @return
     */
    @Override
    public int insert(Admin admin) {
        return adminMapper.insert(admin);
    }

    /**
     * 根据id修改管理员信息
     * @param admin
     * @return
     */
    @Override
    public int update(Admin admin) {
        return adminMapper.update(admin);
    }

    /**
     * 删除指定id的管理员信息
     * @param ids
     * @return
     */
    @Override
    public int deleteById(Integer[] ids) {
        return adminMapper.deleteById(ids);
    }

    /**
     * 根据id修改指定用户密码
     * @param admin
     * @return
     */
    @Override
    public int updatePassword(Admin admin) {
        return adminMapper.updatePassword(admin);
    }
}
