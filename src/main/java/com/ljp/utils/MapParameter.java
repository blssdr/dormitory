package com.ljp.utils;

import java.util.HashMap;
import java.util.Map;

public class MapParameter {
    private Map<String ,Object> paramMap = new HashMap<>();

    private MapParameter(){

    }

    public static MapParameter getInstance(){
        return new MapParameter();
    }

    public Map<String ,Object> getMap(){
        return paramMap;
    }

    public MapParameter put(String key, Object val){
        this.paramMap.put(key,val);
        return this;
    }

    public MapParameter put(Map<String,Object> map){
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            paramMap.put(entry.getKey(),entry.getValue());
        }
        return this;
    }

    public MapParameter add(String key, Object val){
        this.put(key, val);
        return this;
    }

    public MapParameter addId(Object val){
        this.put("id", val);
        return this;
    }

    public static void main(String[] args) {
        Map<String, Object> map = MapParameter.getInstance().put("name", "jack").add("age", 18).getMap();
        System.out.println(map);
    }


}
