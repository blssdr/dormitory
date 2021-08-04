package com.ljp.controller;

import com.ljp.entity.Worker;
import com.ljp.service.WorkerService;
import com.ljp.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> create(@RequestBody Worker worker){
        int result = workerService.create(worker);
        if(result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(String ids){
//        System.out.println(ids);
        int result = workerService.deleteBatch(ids);
        if(result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Worker worker){
        int result = workerService.update(worker);
        if(result <= 0){
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> query(@RequestBody Worker worker){
//        System.out.println(worker.getPage());
//        System.out.println(worker.getLimit());
//        System.out.println(worker.getAccount());
//        System.out.println(worker.getName().isEmpty());
//        System.out.println(worker.getPhone());
        List<Worker> list = workerService.query(worker);
        Integer count = workerService.count(worker);
        return MapControl.getInstance().page(list,count).getMap();
    }

    @GetMapping("/edit")
    public String  detail(Integer id, ModelMap modelMap) {
        Worker worker = workerService.detail(id);
        modelMap.addAttribute("worker",worker);
//        modelMap.addAttribute(worker);
        return "worker/edit";
    }

    @GetMapping("/list")
    public String list(){
        return "worker/list";
    }

    @GetMapping("/add")
    public String add(){
        return "worker/add";
    }



//    @GetMapping("/create")
//    public void create(HttpServletResponse response){
//        Worker worker = new Worker();
//        worker.setAccount("xx");
//        workerService.create(worker);
//        try {
//            response.getWriter().println("success");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @GetMapping("/delete")
//    public void delete(HttpServletResponse response){
//        workerService.delete(2);
//        try {
//            response.getWriter().println("success");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
