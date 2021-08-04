package com.ljp.controller;

import com.ljp.entity.Admin;
import com.ljp.entity.Room;
import com.ljp.entity.Student;
import com.ljp.mapper.AdminDao;
import com.ljp.service.AdminService;
import com.ljp.service.RoomService;
import com.ljp.service.StudentService;
import com.ljp.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/assignments")
public class AssignmentsController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private RoomService roomService;

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
//        int result = studentService.deleteBatch(ids);
//        if(result <= 0){
//            return MapControl.getInstance().error().getMap();
//        }
//        return MapControl.getInstance().success().getMap();
        return null;
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

    @PostMapping("/join/query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Student student){
        student.setState("待分配");
        List<Student> list = studentService.query(student);
        Integer count = studentService.count(student);
        return MapControl.getInstance().page(list,count).getMap();
    }
    @PostMapping("/leave/query")
    @ResponseBody
    public Map<String, Object> query2(@RequestBody Student student){
        student.setState("已分配");
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

    @GetMapping("/join/checkin")
    public String  checkin(String ids,ModelMap modelMap) {
//        ids = "ids=" + ids;
//        modelMap.addAttribute("ids",ids);
//        System.out.println(ids);
        List<Integer> list = new ArrayList<Integer>();
        String [] str = ids.split(",");
        for (String s : str){
//            System.out.println(Integer.parseInt(s));
            list.add(Integer.parseInt(s));
        }
        modelMap.addAttribute("list",list);
        return "assignments/join/checkin";
    }


    @GetMapping("/join/list")
    public String list(){
        return "assignments/join/list";
    }

    @GetMapping("/leave/list")
    public String list2(){
        return "assignments/leave/list";
    }

    @PostMapping("/join/queryRoom")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Room room){
        List<Room> list = roomService.query(room);
        Integer count = roomService.count(room);
//        room.setState("待入住");
//        List<Room> list1 = roomService.query(room);
//        Integer count1 = roomService.count(room);
//        room.setState("有空位");
//        List<Room> list2 = roomService.query(room);
//        Integer count2 = roomService.count(room);
//        List<Room> list = new ArrayList<Room>();
//        list.addAll(list1);
//        list.addAll(list1);
//        Integer count = count1 + count2;
        return MapControl.getInstance().page(list,count).getMap();
    }

    @PostMapping("/join/select")
    @ResponseBody
    public Map<String, Object> select(String ids){
        System.out.println("ids="+ids);
        List<Integer> list = new ArrayList<Integer>();
        String [] str = ids.split(",");
        for (String s : str){
//            System.out.println(Integer.parseInt(s));
            list.add(Integer.parseInt(s));
        }

//      房间信息更改
        Room room = roomService.detail(list.get(0));
        System.out.println(list.size());
        if ((room.getRules()-room.getCount()+1) < list.size()){
            return MapControl.getInstance().error("房间不足").getMap();
        }
        if (room.getLeaderId()==null){
            room.setLeaderId(list.get(1));
        }
        room.setCount(room.getCount()+list.size()-1);
        if (room.getCount()==room.getRules()){
            room.setState(Room.state_over);
        }else{
            room.setState(Room.state_exec);
        }
        int resultRoom = roomService.update(room);
        if(resultRoom <= 0){
            return MapControl.getInstance().error("房间更新失败").getMap();
        }
//        学生信息更改
        for (int i =1; i < list.size(); i++){
            Student student = studentService.detail(list.get(i));
            student.setRoomId(list.get(0));
            student.setState(Student.state_exec);
//            System.out.println(student.getStartTime());
//            System.out.println(new Date());
            student.setStartTime(new Date());
            int resultStudent = studentService.update(student);
            if(resultStudent <= 0){
                return MapControl.getInstance().error("学生更新失败").getMap();
            }
        }
//        int result = studentService.update(student);
//        int result = studentService.deleteBatch(ids);
//        if(result <= 0){
//            return MapControl.getInstance().error().getMap();
//        }
        return MapControl.getInstance().success().getMap();
//        return null;
    }

    @PostMapping("/leave/lea")
    @ResponseBody
    public Map<String, Object> lea(String ids){
        System.out.println("ids="+ids);
        List<Integer> list = new ArrayList<Integer>();
        String [] str = ids.split(",");
        for (String s : str){
            System.out.println(Integer.parseInt(s));
            list.add(Integer.parseInt(s));
        }
        //        学生信息更改
        for (int i =0; i < list.size(); i++){
            Student student = studentService.detail(list.get(i));
            Room room = roomService.detail(student.getRoomId());
            room.setCount(room.getCount()-1);
            if (room.getCount() != room.getRules()){
                room.setState(Room.state_exec);
            }
            if (room.getCount() == 0){
                room.setState(Room.state_create);
            }
            if (room.getLeaderId() == student.getId()){
                room.setLeaderId(0);
            }
            int resultRoom = roomService.update(room);
            if(resultRoom <= 0){
                return MapControl.getInstance().error("房间更新失败").getMap();
            }
            student.setRoomId(0);
            student.setState(Student.state_over);
            student.setEndTime(new Date());
            int resultStudent = studentService.update(student);
            if(resultStudent <= 0){
                return MapControl.getInstance().error("学生更新失败").getMap();
            }
        }
        return MapControl.getInstance().success().getMap();
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
