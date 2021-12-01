package com.cc.sms.controller;

import com.cc.sms.bean.Clazz;
import com.cc.sms.bean.Teacher;
import com.cc.sms.service.ClazzService;
import com.cc.sms.service.TeacherService;
import com.cc.sms.util.UploadFile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 多瑞c
 * @date 2021/11/24 14:53
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private TeacherService teacherService;

    //存储预返回页面的数据对象
    private Map<String, Object> result = new HashMap<>();

    /**
     * 跳转到教师信息管理页面
     * @param modelAndView
     * @return
     */
    @GetMapping("/goTeacherListView")
    public ModelAndView goTeacherListView(ModelAndView modelAndView) {
        modelAndView.addObject("clazzList", clazzService.selectAll());
        modelAndView.setViewName("teacher/teacherList");
        return modelAndView;
    }

    /**
     * 根据教师与班级名查询指定/全部教师信息
     * @param page
     * @param rows
     * @param teachername
     * @param clazzname
     * @return
     */
    @PostMapping("/getTeacherList")
    @ResponseBody
    public Map<String, Object> getTeacherList(Integer page, Integer rows, String teachername, String clazzname) {
        //存储查询的teachername, clazzname信息
        Teacher teacher = new Teacher(teachername, clazzname);
        //设置每页的记录数
        PageHelper.startPage(page, rows);
        //根据教师与班级名查询指定/全部教师信息
        List<Teacher> list = teacherService.selectList(teacher);
        //封装列表信息
        PageInfo<Teacher> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Teacher> teacherList = pageInfo.getList();
        //存储数据对象
        result.put("total", total);
        result.put("rows", teacherList);
        return result;
    }

    /**
     * 添加教师信息
     * @param teacher
     * @return
     */
    @PostMapping("/addTeacher")
    @ResponseBody
    public Map<String, Object> addTeacher(Teacher teacher) {
        //判断工号是否存在
        if (teacherService.findByTno(teacher) != null) {
            result.put("success", false);
            result.put("msg", "工号已存在! 请修改后重试!");
            return result;
        }
        if (teacherService.insert(teacher) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "添加失败!  (ಥ_ಥ)服务器端发生异常!");
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

    /**
     * 根据id修改指定教师信息
     * @param teacher
     * @return
     */
    @PostMapping("/editTeacher")
    @ResponseBody
    public Map<String, Object> editTeacher(Teacher teacher) {
        if (teacherService.update(teacher) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "修改失败! (ಥ_ಥ)服务器端发生异常!");
        }
        return result;
    }

    /**
     * 根据id删除指定教师信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteTeacher")
    @ResponseBody
    public Map<String, Object> deleteTeacher(@RequestParam(value = "ids[]", required = true) Integer[] ids) {
        if (teacherService.deleteById(ids) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "删除失败! (ಥ_ಥ)服务器端发生异常!");
        }
        return result;
    }
}
