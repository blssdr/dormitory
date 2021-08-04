package com.ljp.controller;

import com.ljp.entity.Student;
import com.ljp.service.StudentService;
import com.ljp.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody Student student){
        int result = studentService.create(student);
        if(result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(String ids){
//        System.out.println(ids);
        int result = studentService.deleteBatch(ids);
        if(result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Student student){
        int result = studentService.update(student);
        if(result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Student student){
//        System.out.println(student.getPage());
//        System.out.println(student.getLimit());
//        System.out.println(student.getAccount());
//        System.out.println(student.getName().isEmpty());
//        System.out.println(student.getPhone());
        List<Student> list = studentService.query(student);
        Integer count = studentService.count(student);
        return MapControl.getInstance().page(list,count).getMap();
    }

    @GetMapping("/edit")
    public String  detail(Integer id, ModelMap modelMap) {
        Student student = studentService.detail(id);
        modelMap.addAttribute("student",student);
//        modelMap.addAttribute(student);
        return "student/edit";
    }

    @GetMapping("/list")
    public String list(){
        return "student/list";
    }


    @GetMapping("/add")
    public String add(){
        return "student/add";
    }




//    @GetMapping("/create")
//    public void create(HttpServletResponse response){
//        Student student = new Student();
//        student.setAccount("xx");
//        studentService.create(student);
//        try {
//            response.getWriter().println("success");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @GetMapping("/delete")
//    public void delete(HttpServletResponse response){
//        studentService.delete(2);
//        try {
//            response.getWriter().println("success");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
