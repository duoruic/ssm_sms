package com.cc.sms.controller;

import com.cc.sms.bean.Admin;
import com.cc.sms.service.AdminService;
import com.cc.sms.util.UploadFile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 多瑞c
 * @date 2021/11/23 12:03
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    /**
     * 跳转到管理员信息页面
     * @return
     */
    @GetMapping("/goAdminListView")
    public String goAdminListView() {
        return "admin/adminList";
    }

    /**
     * 根据管理员姓名查询指定的管理员信息
     * @param page
     * @param rows
     * @param username
     * @return
     */
    @PostMapping("/getAdminList")
    @ResponseBody
    public Map<String, Object> getAdminList(Integer page, Integer rows, String username) {
        //获取查询的用户名
        Admin admin = new Admin();
        admin.setName(username);
        //设置每页记录数
        PageHelper.startPage(page, rows);
        //根据管理员姓名查询指定的管理员信息
        List<Admin> list = adminService.selectList(admin);
        //封装查询结果
        PageInfo<Admin> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页的数据列表
        List<Admin> adminList = pageInfo.getList();
        //存储对象
        result.put("total", total);
        result.put("rows", adminList);
        return result;
    }

    /**
     * 添加管理员信息
     * @param admin
     * @return
     */
    @PostMapping("/addAdmin")
    @ResponseBody
    public Map<String, Object> addAdmin(Admin admin) {
        //判断用户名是否存在
        Admin user = adminService.findByName(admin.getName());
        if (user == null) {
            if (adminService.insert(admin) > 0) {
                result.put("success", true);
            } else {
                result.put("success", false);
                result.put("msg", "添加失败! 服务器异常!");
            }
        } else {
            result.put("success", false);
            result.put("msg", admin.getName() + "已经存在! 请修改后重试");
        }
        return result;
    }

    /**
     * 根据id修改指定管理员信息
     * @param admin
     * @return
     */
    @PostMapping("/editAdmin")
    @ResponseBody
    public Map<String, Object> editAdmin(Admin admin) {
        //只修改用户名以外的信息
        Admin user = adminService.findByName(admin.getName());
        if (user != null) {
            if (!(admin.getId().equals(user.getId()))) {
                result.put("success", false);
                result.put("msg", "该用户名已经存在! 请修改后重试!");
                return result;
            }
        }
        //修改
        if (adminService.update(admin) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "修改失败! 服务器发生异常!");
        }
        return result;
    }

    /**
     * 删除指定id的管理员信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteAdmin")
    @ResponseBody
    public Map<String, Object> deleteAdmin(@RequestParam(value = "ids[]", required = true) Integer[] ids) {
        if (adminService.deleteById(ids) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    /**
     * 上传头像-原理：将头像上传到项目发布目录中,通过读取数据库中的头像路径来获取头像
     * @param photo
     * @param request
     * @return
     */
    @PostMapping("/uploadPhoto")
    @ResponseBody
    public Map<String, Object> uploadPhoto(MultipartFile photo, HttpServletRequest request) {
        //存储头像的本地目录
        final String dirPath = request.getServletContext().getRealPath("/upload/admin_portrait");
        //存储头像的项目发布目录
        final String portraitPath = request.getServletContext().getContextPath() + "/upload/admin_portrait/";
        //返回头像上传的结果
        return UploadFile.getUploadResult(photo, dirPath, portraitPath);
    }
}
