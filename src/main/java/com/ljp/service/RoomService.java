package com.ljp.service;

import com.github.pagehelper.PageHelper;
import com.ljp.entity.Room;
import com.ljp.mapper.RoomDao;
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
public class RoomService {
    @Autowired
    private RoomDao roomDao;

    public Room login(String account,String password){
        Map<String,Object> map = MapParameter.getInstance().put("account",account).put("password",password).getMap();
        return roomDao.detail(map);
    }

    public int create(Room room){
//        room.setPassword(MD5Utils.getMD5(room.getPassword()));
        room.setState(Room.state_create);
        room.setCount(0);
        return roomDao.create(room);
    }

    public int delete(Integer id){
        return roomDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int deleteBatch(String ids){
        int flag = 0;
        String [] arr = ids.split(",");
//        System.out.println(arr[0]);
        for (String s : arr){
            roomDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
            flag++;
        }
        return flag;
    }

    public int update(Room room){
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMapForUpdate(room)).addId(room.getId()).getMap();
        return roomDao.update(map);
    }

    public List<Room> query(Room room){
        PageHelper.startPage(room.getPage(),room.getLimit());
//        Map<String, Object> map = BeanMapUtils.beanToMap(room);
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(room)).getMap();
        return roomDao.query(map);
    }

    public Room detail(Integer id){
        return roomDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Room room){
//        Map<String, Object> map = BeanMapUtils.beanToMap(room);
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(room)).getMap();
        return roomDao.count(map);
    }
}
