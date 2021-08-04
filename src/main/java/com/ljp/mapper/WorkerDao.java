package com.ljp.mapper;

import com.ljp.entity.Worker;

import java.util.List;
import java.util.Map;

public interface WorkerDao {

    public int create(Worker worker);
    public int delete(Map<String,Object> paramMap);
    public int update(Map<String,Object> paramMap);
    public List<Worker> query(Map<String,Object> paramMap);
    public Worker detail(Map<String,Object> paramMap);
    public int count(Map<String,Object> paramMap);



}
