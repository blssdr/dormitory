package com.ljp.service;

import com.github.pagehelper.PageHelper;
import com.ljp.entity.Worker;
import com.ljp.mapper.WorkerDao;
import com.ljp.utils.BeanMapUtils;
import com.ljp.utils.MD5Utils;
import com.ljp.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WorkerService {
    @Autowired
    private WorkerDao workerDao;

    public Worker login(String account,String password){
        Map<String,Object> map = MapParameter.getInstance().put("account",account).put("password",password).getMap();
        return workerDao.detail(map);
    }

    public int create(Worker worker){
        worker.setPassword(MD5Utils.getMD5(worker.getPassword()));
        return workerDao.create(worker);
    }

    public int delete(Integer id){
        return workerDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int deleteBatch(String ids){
        int flag = 0;
        String [] arr = ids.split(",");
//        System.out.println(arr[0]);
        for (String s : arr){
            workerDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
            flag++;
        }
        return flag;
    }

    public int update(Worker worker){
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMapForUpdate(worker)).addId(worker.getId()).getMap();
        return workerDao.update(map);
    }

    public List<Worker> query(Worker worker){
        PageHelper.startPage(worker.getPage(),worker.getLimit());
//        Map<String, Object> map = BeanMapUtils.beanToMap(worker);
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(worker)).getMap();
        return workerDao.query(map);
    }

    public Worker detail(Integer id){
        return workerDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Worker worker){
//        Map<String, Object> map = BeanMapUtils.beanToMap(worker);
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(worker)).getMap();
        return workerDao.count(map);
    }
}
