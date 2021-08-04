package com.ljp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class IndexController {
//json文件路径
    @Value("classpath:init.json")
    private Resource resource;
//  index页面跳转
    @GetMapping("/index")
    public String index(){
        return "index";
    }
//  菜单请求
    @GetMapping("/menu")
    @ResponseBody
    public void menu(HttpServletResponse response){

        try {
            File file = resource.getFile();
//            FileReader fileReader = new FileReader(file);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));

            String str;
            StringBuffer sb = new StringBuffer();
            while((str = bufferedReader.readLine()) != null){
                sb.append(str);
            }
//            System.out.println(sb.toString());
            bufferedReader.close();
//            fileReader.close();
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
