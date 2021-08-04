package com.ljp.controller;

import com.ljp.entity.Room;
import com.ljp.service.RoomService;
import com.ljp.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody Room room){
        int result = roomService.create(room);
        if(result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(String ids){
//        System.out.println(ids);
        int result = roomService.deleteBatch(ids);
        if(result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Room room){
        int result = roomService.update(room);
        if(result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Room room){
//        System.out.println(room.getPage());
//        System.out.println(room.getLimit());
//        System.out.println(room.getAccount());
//        System.out.println(room.getName().isEmpty());
//        System.out.println(room.getPhone());
        List<Room> list = roomService.query(room);
        Integer count = roomService.count(room);
        return MapControl.getInstance().page(list,count).getMap();
    }

    @GetMapping("/edit")
    public String  detail(Integer id, ModelMap modelMap) {
        Room room = roomService.detail(id);
        modelMap.addAttribute("room",room);
//        modelMap.addAttribute(room);
        return "room/edit";
    }

    @GetMapping("/list")
    public String list(){
        return "room/list";
    }

    @GetMapping("/add")
    public String add(){
        return "room/add";
    }

    @GetMapping("/details")
    public String  details(Integer id, ModelMap modelMap) {
        Room room = roomService.detail(id);
        modelMap.addAttribute("room",room);
//        modelMap.addAttribute(room);
        return "room/details";
    }



//    @GetMapping("/create")
//    public void create(HttpServletResponse response){
//        Room room = new Room();
//        room.setAccount("xx");
//        roomService.create(room);
//        try {
//            response.getWriter().println("success");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @GetMapping("/delete")
//    public void delete(HttpServletResponse response){
//        roomService.delete(2);
//        try {
//            response.getWriter().println("success");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
