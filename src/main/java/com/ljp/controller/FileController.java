package com.ljp.controller;

import com.ljp.entity.Admin;
import com.ljp.service.AdminService;
import com.ljp.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {


    @GetMapping("/upload")
    public String uploadGet(){
        return "file/upload";
    }

    //图片上传
    @RequestMapping("uploadPost")
    @ResponseBody
    public Map<String, Object> uploadResource(MultipartFile file) throws Exception{
        String path = "D:\\"+"testUpload";
        String fileName = file.getOriginalFilename();
        File dir = new File(path,fileName);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //写入磁盘
        file.transferTo(dir);
        return MapControl.getInstance().success().getMap();
    }


}
