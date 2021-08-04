package com.ljp.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapControl {

    private static final int SUCCESS_CODE = 0;
    private static final int ERROR_CODE = 500;

    private Map<String ,Object> paramMap = new HashMap<>();

    private MapControl(){

    }

    public static MapControl getInstance(){
        return new MapControl();
    }

    public Map<String ,Object> getMap(){
        return paramMap;
    }

    public MapControl put(String key, Object val){
        this.paramMap.put(key,val);
        return this;
    }

    public MapControl put(Map<String,Object> map){
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            paramMap.put(entry.getKey(),entry.getValue());
        }
        return this;
    }

    public MapControl add(String key, Object val){
        this.put(key, val);
        return this;
    }

    public MapControl success(){
        return this.put("msg","操作成功").put("code",SUCCESS_CODE);
    }

    public MapControl error(){
        return this.put("msg","操作失败").put("code",ERROR_CODE);
    }

    public MapControl success(String string){
        return this.put("msg",string).put("code",SUCCESS_CODE);
    }

    public MapControl error(String string){
        return this.put("msg",string).put("code",ERROR_CODE);
    }

    public MapControl page(List<?> data, Integer count){
        return this.put("msg","返回成功").put("code",SUCCESS_CODE).put("count",count).put("data",data);
    }

    public static void main(String[] args) {
        Map<String, Object> map = MapParameter.getInstance().put("name", "jack").add("age", 18).getMap();
        System.out.println(map);
    }
}
