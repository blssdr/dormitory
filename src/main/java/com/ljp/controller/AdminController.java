package com.ljp.controller;

import com.ljp.entity.Admin;
import com.ljp.service.AdminService;
import com.ljp.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody Admin admin){
        int result = adminService.create(admin);
        if(result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(String ids){
//        System.out.println(ids);
        int result = adminService.deleteBatch(ids);
        if(result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Admin admin){
        int result = adminService.update(admin);
        if(result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Admin admin){
//        System.out.println(admin.getPage());
//        System.out.println(admin.getLimit());
//        System.out.println(admin.getAccount());
//        System.out.println(admin.getName().isEmpty());
//        System.out.println(admin.getPhone());
        List<Admin> list = adminService.query(admin);
        Integer count = adminService.count(admin);
        return MapControl.getInstance().page(list,count).getMap();
    }

    @GetMapping("/edit")
    public String  detail(Integer id, ModelMap modelMap) {
        Admin admin = adminService.detail(id);
        modelMap.addAttribute("admin",admin);
//        modelMap.addAttribute(admin);
        return "admin/edit";
    }

    @GetMapping("/list")
    public String list(){
        return "admin/list";
    }

    @GetMapping("/add")
    public String add(){
        return "admin/add";
    }



//    @GetMapping("/create")
//    public void create(HttpServletResponse response){
//        Admin admin = new Admin();
//        admin.setAccount("xx");
//        adminService.create(admin);
//        try {
//            response.getWriter().println("success");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @GetMapping("/delete")
//    public void delete(HttpServletResponse response){
//        adminService.delete(2);
//        try {
//            response.getWriter().println("success");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
