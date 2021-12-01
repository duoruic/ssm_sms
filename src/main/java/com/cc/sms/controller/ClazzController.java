package com.cc.sms.controller;

import com.cc.sms.bean.Clazz;
import com.cc.sms.service.ClazzService;
import com.cc.sms.service.GradeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 多瑞c
 * @date 2021/11/24 11:38
 */
@Controller
@RequestMapping("/clazz")
public class ClazzController {
    //注入业务对象
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private GradeService gradeService;

    //存储预返回页面的数据对象
    private Map<String, Object> result = new HashMap<>();

    /**
     * 跳转到班级信息管理页面
     * @param modelAndView
     * @return
     */
    @GetMapping("/goClazzListView")
    public ModelAndView goClazzListView(ModelAndView modelAndView) {
        modelAndView.addObject("gradeList", gradeService.selectAll());
        modelAndView.setViewName("clazz/clazzList");
        return modelAndView;
    }

    /**
     * 根据班级与年级名查询指定/全部班级信息
     * @param page
     * @param rows
     * @param clazzname
     * @param gradename
     * @return
     */
    @PostMapping("/getClazzList")
    @ResponseBody
    public Map<String, Object> getClazzList(Integer page, Integer rows, String clazzname, String gradename) {
        //存储查询的clazzname, gradename信息
        Clazz clazz = new Clazz(clazzname, gradename);
        //设置每页的记录数
        PageHelper.startPage(page, rows);
        //根据班级与年级名查询指定/全部班级信息
        List<Clazz> list = clazzService.selectList(clazz);
        //封装列表信息
        PageInfo<Clazz> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前数据列表
        List<Clazz> clazzList = pageInfo.getList();
        //存储数据对象
        result.put("total", total);
        result.put("rows", clazzList);
        return result;
    }

    /**
     * 添加班级信息
     * @param clazz
     * @return
     */
    @PostMapping("/addClazz")
    @ResponseBody
    public Map<String, Object> addClazz(Clazz clazz) {
        //判断班级名是否存在
        Clazz name = clazzService.findByName(clazz.getName());
        if (name == null) {
            if (clazzService.insert(clazz) > 0) {
                result.put("success", true);
            } else {
                result.put("success", false);
                result.put("msg", "添加失败! (ಥ_ಥ)服务器端发生异常!");
            }
        } else {
            result.put("success", false);
            result.put("msg", "该班级名称已存在! 请修改后重试!");
        }
        return result;
    }

    /**
     * 根据id修改指定的班级信息
     * @param clazz
     * @return
     */
    @PostMapping("/editClazz")
    @ResponseBody
    public Map<String, Object> editClazz(Clazz clazz) {
        //只修改班级名以外的信息
        Clazz name = clazzService.findByName(clazz.getName());
        if (name != null) {
            if (!(clazz.getId().equals(name.getId()))) {
                result.put("success", false);
                result.put("msg", "该班级名称已存在! 请修改后重试!");
                return result;
            }
        }
        //修改
        if (clazzService.update(clazz) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "添加失败! (ಥ_ಥ)服务器端发生异常!");
        }
        return result;
    }

    /**
     * 删除指定id的班级信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteClazz")
    @ResponseBody
    public Map<String, Object> deleteClazz(@RequestParam(value = "ids[]", required = true) Integer[] ids) {
        if (clazzService.deleteById(ids) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }
}
