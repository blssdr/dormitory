package com.ljp.entity;

import com.ljp.utils.Entity;

import java.util.List;

public class Room extends Entity {

    public static final String state_create="待入住";
    public static final String state_exec="有空位";
    public static final String state_over="已满";

    private Integer id;
    private String name;
    private Integer leaderId;
    private Integer rules;
    private Integer count;
    private String state;
    private String remark;

    private List<Student> students;

    public String getRoomLeader(){
        if (students != null){
            for(Student s : students){
                if(s.getId() == leaderId){
                    return s.getName();
                }
            }
        }
        return "暂无寝室长";
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leaderId=" + leaderId +
                ", rules=" + rules +
                ", count=" + count +
                ", state='" + state + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public Integer getRules() {
        return rules;
    }

    public void setRules(Integer rules) {
        this.rules = rules;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
