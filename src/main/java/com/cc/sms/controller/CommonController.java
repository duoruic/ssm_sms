package com.cc.sms.controller;

import com.cc.sms.bean.Admin;
import com.cc.sms.bean.Student;
import com.cc.sms.bean.Teacher;
import com.cc.sms.service.AdminService;
import com.cc.sms.service.StudentService;
import com.cc.sms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 多瑞c
 * @date 2021/11/23 21:24
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    //注入业务对象
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    //存储预返回给页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    /**
     * 跳转到个人信息管理页面
     * @return
     */
    @GetMapping("/goSettingView")
    public String goSettingView(){
        return "common/settings";
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param request
     * @return
     */
    @PostMapping("/editPassword")
    @ResponseBody
    public Map<String, Object> editPassword(String oldPassword, String newPassword, HttpServletRequest request) {
        //判断当前用户的用户类型
        int userType = Integer.parseInt(request.getSession().getAttribute("userType").toString());
        //管理员身份
        if (userType == 1) {
            Admin admin = (Admin)request.getSession().getAttribute("userInfo");
            //如果用户输入的密码与原密码不同
            if (!(admin.getPassword().equals(oldPassword))) {
                result.put("success", false);
                result.put("msg", "原密码错误!");
                return result;
            }
            try {
                //修改密码
                admin.setPassword(newPassword);
                if (adminService.updatePassword(admin) > 0) {
                    result.put("success", true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.put("success", false);
                result.put("msg", "修改失败! 服务器端发生异常!");
            }
        }
        //学生身份
        if (userType == 2) {
            Student student = (Student)request.getSession().getAttribute("userInfo");
            //如果用户输入的密码与原密码不同
            if (!(student.getPassword().equals(oldPassword))) {
                result.put("success", false);
                result.put("msg", "原密码错误!");
                return result;
            }
            try {
                //修改密码
                student.setPassword(newPassword);
                if (studentService.updatePassword(student) > 0) {
                    result.put("success", true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.put("success", false);
                result.put("msg", "修改失败! 服务器端发生异常!");
            }
        }
        //老师身份
        if (userType == 3) {
            Teacher teacher = (Teacher)request.getSession().getAttribute("userInfo");
            //如果用户输入的密码与原密码不同
            if (!(teacher.getPassword().equals(oldPassword))) {
                result.put("success", false);
                result.put("msg", "原密码错误!");
                return result;
            }
            try {
                //修改密码
                teacher.setPassword(newPassword);
                if (teacherService.updatePassword(teacher) > 0) {
                    result.put("success", true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.put("success", false);
                result.put("msg", "修改失败! 服务器端发生异常!");
            }
        }
        return result;
    }
}
