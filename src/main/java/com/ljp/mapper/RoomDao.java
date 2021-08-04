package com.ljp.mapper;

import com.ljp.entity.Room;

import java.util.List;
import java.util.Map;

public interface RoomDao {

    public int create(Room room);
    public int delete(Map<String,Object> paramMap);
    public int update(Map<String,Object> paramMap);
    public List<Room> query(Map<String,Object> paramMap);
    public Room detail(Map<String,Object> paramMap);
    public int count(Map<String,Object> paramMap);

//    public Room getRoom(Map<String,Object> paramMap);



}
