package com.cn.wanxi.service.dress.impl;

import com.cn.wanxi.dao.NavModelDao;
import com.cn.wanxi.dao.dress.NavDao;
import com.cn.wanxi.dao.dress.impl.NavDaoImpl;
import com.cn.wanxi.dao.dress.impl.NavModelDaoImpl;
import com.cn.wanxi.model.dress.Nav;
import com.cn.wanxi.model.product.NavModel;
import com.cn.wanxi.service.dress.NavService;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NavServiceImpl.java
 * @Description TODO
 * @createTime 2021年11月29日 12:08:00
 */
public class NavServiceImpl implements NavService {

    /**
     * 首页导航
     *
     * 查询所有
     *
     */
    @Override
    public List<Nav> findAll() {

        List<Nav> list  = new ArrayList<>();
        Jedis jedis = new Jedis();
        jedis.select(0);
        boolean isHave = jedis.exists("navTitle");
        if (isHave){
            List<String> title = jedis.lrange("navTitle", 0, -1);
            List<String> href = jedis.lrange("navHref", 0, -1);
           int length =title.size();
            for (int i = 0; i < length; i++) {
                Nav model = new Nav();
                model.setTitle(title.get(i));
                model.setHref(href.get(i));
                list.add(model);
            }
        }else {
            NavDao navDao = new NavDaoImpl();
            list =navDao.findAll();
            for (Nav nav:
                 list) {
                jedis.rpush("navTitle",nav.getTitle());
                jedis.rpush("navHref",nav.getHref());
            }
        }

        return  list;
    }

}
