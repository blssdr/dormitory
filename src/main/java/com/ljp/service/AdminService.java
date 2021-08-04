package com.ljp.service;

import com.github.pagehelper.PageHelper;
import com.ljp.entity.Admin;
import com.ljp.mapper.AdminDao;
import com.ljp.utils.BeanMapUtils;
import com.ljp.utils.MD5Utils;
import com.ljp.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminDao adminDao;

    public Admin login(String account,String password){
        Map<String,Object> map = MapParameter.getInstance().put("account",account).put("password",password).getMap();
        return adminDao.detail(map);
    }

    public int create(Admin pi){
        pi.setPassword(MD5Utils.getMD5(pi.getPassword()));
        return adminDao.create(pi);
    }

    public int delete(Integer id){
        return adminDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int deleteBatch(String ids){
        int flag = 0;
        String [] arr = ids.split(",");
//        System.out.println(arr[0]);
        for (String s : arr){
            adminDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
            flag++;
        }
        return flag;
    }

    public int update(Admin admin){
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMapForUpdate(admin)).addId(admin.getId()).getMap();
        return adminDao.update(map);
    }

    public List<Admin> query(Admin admin){
        PageHelper.startPage(admin.getPage(),admin.getLimit());
//        Map<String, Object> map = BeanMapUtils.beanToMap(admin);
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(admin)).getMap();
        return adminDao.query(map);
    }

    public Admin detail(Integer id){
        return adminDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Admin admin){
//        Map<String, Object> map = BeanMapUtils.beanToMap(admin);
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(admin)).getMap();
        return adminDao.count(map);
    }
}
