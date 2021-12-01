package com.cc.sms.service;

import com.cc.sms.bean.Admin;
import com.cc.sms.bean.LoginForm;
import java.util.List;

public interface AdminService {
    //管理员登录
    Admin login(LoginForm loginForm);

    //根据管理员姓名查询指定/所有的管理员信息
    List<Admin> selectList(Admin admin);

    //根据用户名查询指定的管理员信息
    Admin findByName(String name);

    //添加管理员信息
    int insert(Admin admin);

    //根据id修改管理员信息
    int update(Admin admin);

    //删除指定id的管理员信息
    int deleteById(Integer[] ids);

    //根据id修改指定用户密码
    int updatePassword(Admin admin);
}
