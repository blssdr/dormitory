package com.ljp.entity;

import com.ljp.utils.Entity;

public class Worker extends Entity {

    private Integer id;
    private String account;
    private String password;
    private String name;
    private String sex;
    private String phone;
    private String dutu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDutu() {
        return dutu;
    }

    public void setDutu(String dutu) {
        this.dutu = dutu;
    }
}
