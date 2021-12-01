package com.cc.sms.controller;

import com.cc.sms.bean.Student;
import com.cc.sms.service.ClazzService;
import com.cc.sms.service.StudentService;
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
 * @date 2021/11/24 22:11
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private StudentService studentService;

    //存储预返回页面的数据对象
    private Map<String, Object> result = new HashMap<>();

    /**
     * 跳转到学生信息管理页面
     * @param modelAndView
     * @return
     */
    @GetMapping("/goStudentListView")
    public ModelAndView goStudentListView(ModelAndView modelAndView) {
        modelAndView.addObject("clazzList", clazzService.selectAll());
        modelAndView.setViewName("student/studentList");
        return modelAndView;
    }

    /**
     * 根据学生与班级名查询指定/全部学生信息列表
     * @param page
     * @param rows
     * @param studentname
     * @param clazzname
     * @return
     */
    @PostMapping("/getStudentList")
    @ResponseBody
    public Map<String, Object> getStudentList(Integer page, Integer rows, String studentname, String clazzname) {
        //存储查询的studentname, clazzname信息
        Student student = new Student(studentname, clazzname);
        //设置每页的记录数
        PageHelper.startPage(page, rows);
        //根据学生与班级名查询指定/全部学生信息列表
        List<Student> list = studentService.selectList(student);
        //封装信息列表
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Student> studentList = pageInfo.getList();
        //存储数据对象
        result.put("total", total);
        result.put("rows", studentList);
        return result;
    }

    /**
     * 添加学生信息
     * @param student
     * @return
     */
    @PostMapping("/addStudent")
    @ResponseBody
    public Map<String, Object> addStudent(Student student) {
        //判断学号是否存在
        if (studentService.findBySno(student) != null) {
            result.put("success", false);
            result.put("msg", "该学号已经存在! 请修改后重试!");
            return result;
        }
        //添加学生信息
        if (studentService.insert(student) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "添加失败! (ಥ_ಥ)服务器端发生异常!");
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
     * 根据id修改指定学生信息
     * @param student
     * @return
     */
    @PostMapping("/editStudent")
    @ResponseBody
    public Map<String, Object> editStudent(Student student) {
        if (studentService.update(student) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "添加失败! (ಥ_ಥ)服务器端发生异常!");
        }
        return result;
    }

    /**
     * 根据id删除指定的学生信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteStudent")
    @ResponseBody
    public Map<String, Object> deleteStudent(@RequestParam(value = "ids[]", required = true) Integer[] ids) {
        if (studentService.deleteById(ids) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }
}
