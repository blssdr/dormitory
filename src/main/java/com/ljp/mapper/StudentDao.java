package com.ljp.mapper;

import com.ljp.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao {

    public int create(Student student);
    public int delete(Map<String,Object> paramMap);
    public int update(Map<String,Object> paramMap);
    public List<Student> query(Map<String,Object> paramMap);
    public Student detail(Map<String,Object> paramMap);
    public int count(Map<String,Object> paramMap);



}
