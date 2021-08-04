package com.ljp.service;

import com.github.pagehelper.PageHelper;
import com.ljp.entity.Student;
import com.ljp.mapper.StudentDao;
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
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public Student login(String account,String password){
        Map<String,Object> map = MapParameter.getInstance().put("account",account).put("password",password).getMap();
        return studentDao.detail(map);
    }

    public int create(Student student){
        student.setPassword(MD5Utils.getMD5(student.getPassword()));
        student.setState(Student.state_create);
        return studentDao.create(student);
    }

    public int delete(Integer id){
        return studentDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int deleteBatch(String ids){
        int flag = 0;
        String [] arr = ids.split(",");
//        System.out.println(arr[0]);
        for (String s : arr){
            studentDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
            flag++;
        }
        return flag;
    }

    public int update(Student student){
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMapForUpdate(student)).addId(student.getId()).getMap();
        return studentDao.update(map);
    }

    public List<Student> query(Student student){
        PageHelper.startPage(student.getPage(),student.getLimit());
//        Map<String, Object> map = BeanMapUtils.beanToMap(student);
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(student)).getMap();
        return studentDao.query(map);
    }

    public Student detail(Integer id){
        return studentDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Student student){
//        Map<String, Object> map = BeanMapUtils.beanToMap(student);
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(student)).getMap();
        return studentDao.count(map);
    }
}
