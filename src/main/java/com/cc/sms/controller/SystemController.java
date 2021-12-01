package com.cc.sms.controller;

import com.cc.sms.bean.Admin;
import com.cc.sms.bean.LoginForm;
import com.cc.sms.bean.Student;
import com.cc.sms.bean.Teacher;
import com.cc.sms.service.AdminService;
import com.cc.sms.service.StudentService;
import com.cc.sms.service.TeacherService;
import com.cc.sms.util.CreateVerifyCodeImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    //存储返回页面的对象数据
    private Map<String, Object> result = new HashMap<>();

    /**
     * 跳转到登录界面
     * @return
     */
    @GetMapping("/goLogin")
    public String goLogin() {
        return "system/login";
    }

    /**
     * 显示验证码
     * @param request
     * @param response
     */
    @GetMapping("/getVerifyCodeImage")
    public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) {
        //1.获取到验证码图片
        BufferedImage VerifyCodeImage = CreateVerifyCodeImage.getVerifyCodeImage();
        //2.验证码
        String VerifyCode = String.valueOf(CreateVerifyCodeImage.getVerifyCode());
        //3.将验证码图片输出到登录页面
        try {
            ImageIO.write(VerifyCodeImage,"JPEG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //存储验证码session
        request.getSession().setAttribute("VerifyCode", VerifyCode);

    }

    /**
     * 验证登录信息
     * @param loginForm
     * @param request
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public Map<String, Object> login(LoginForm loginForm, HttpServletRequest request) {
        //从session中获取验证码
        String code = (String)request.getSession().getAttribute("VerifyCode");
        //判断code的值
        if ("".equals(code)) {
            result.put("success", false);
            result.put("msg", "长时间未操作，会话已失效，请重新刷新网页");
            return result;
        } else if (!loginForm.getverifyCode().equalsIgnoreCase(code)) {
            result.put("success", false);
            result.put("msg", "验证码错误");
            return result;
        }
        //清除验证码缓存
        request.getSession().removeAttribute("VerifyCode");
        //校验用户身份
        switch (loginForm.getUserType()) {
            //管理员登录
            case 1:
                try {
                    Admin admin = adminService.login(loginForm);
                    if (admin != null) {
                        //存储当前用户信息到session
                        HttpSession session = request.getSession();
                        session.setAttribute("userInfo", admin);
                        session.setAttribute("userType", loginForm.getUserType());
                        result.put("success", true);
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "输入的账号或者密码不正确,登录失败");
                    return result;
                }
                break;
                //学生登录
            case 2:
                try {
                    Student student = studentService.login(loginForm);
                    if (student != null) {
                        //存储当前用户信息到session
                        HttpSession session = request.getSession();
                        session.setAttribute("userInfo", student);
                        session.setAttribute("userType", loginForm.getUserType());
                        result.put("success", true);
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "输入的账号或者密码不正确,登录失败");
                    return result;
                }
                break;
            //教师登录
            case 3:
                try {
                    Teacher teacher = teacherService.login(loginForm);
                    if (teacher != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("userInfo", teacher);
                        session.setAttribute("userType", loginForm.getUserType());
                        result.put("success", true);
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "输入的账号或者密码不正确,登录失败");
                    return result;
                }
                break;
        }
        //登录失败
        result.put("success", false);
        result.put("msg", "输入的账号或者密码不正确,登录失败");
        return result;
    }

    /**
     * 登录成功，跳转到系统主页面
     * @return
     */
    @GetMapping("/goSystemMainView")
    public String goSystemMainView() {
        return "system/main";
    }

    /**
     * 用户退出
     * @param request
     * @param response
     */
    @GetMapping("/loginOut")
    public void loginOut(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("userInfo");
        request.getSession().removeAttribute("userType");
        //注销后重定向到登录页面
        try {
            response.sendRedirect("../index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
