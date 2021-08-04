package com.ljp.controller;

import com.google.common.base.Strings;
import com.ljp.entity.Admin;
import com.ljp.service.AdminService;
import com.ljp.utils.MD5Utils;
import com.ljp.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

//    login页面跳转
    @GetMapping("/login")
    public String v_login(){
        return "login";
    }

//    登录请求
    @PostMapping( "/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        String account = map.get("account") + "";
        String password = map.get("password") + "";
        if (Strings.isNullOrEmpty(account) || Strings.isNullOrEmpty(password)){
            return MapControl.getInstance().error("用户名或密码为空").getMap();
        }
//        System.out.println(account);
//        System.out.println(password);
        Admin admin = adminService.login(account, MD5Utils.getMD5(password));
        if (admin != null){
            HttpSession session = request.getSession();
            session.setAttribute("admin",admin);
            return MapControl.getInstance().success().getMap();
        }else{
            return MapControl.getInstance().error("用户名或密码错误").getMap();
        }
    }
}
