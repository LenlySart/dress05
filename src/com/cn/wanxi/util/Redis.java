package com.cn.wanxi.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import redis.clients.jedis.Jedis;

import java.util.List;


public class Redis {

    static Gson gson = new Gson();
    
    public static Jedis TempRedis(int index) {
        Jedis jedis = new Jedis();
        jedis.select(index);
        return jedis;
    }
//    读取Redis中的数据
    public static <T, t> List getRedis(T t, String key,int index) {
        Jedis jedis = TempRedis(index);
        List<t> list;
        list = gson.fromJson(jedis.get(key), new TypeToken<List<t>>() {
        }.getType());
        return list;
    }

//    将数据存入redis中
    public static void setRedis(List list, String key ,int index) {
        Jedis jedis = TempRedis(index);
        jedis.set(key, gson.toJson(list));
    }

}