package com.cn.wanxi.service.product.impl;

import com.cn.wanxi.dao.product.TypeModeltDao;
import com.cn.wanxi.dao.product.impl.TypeModelDaoImpl;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.news.NewsSort;
import com.cn.wanxi.model.product.TypeModel;
import com.cn.wanxi.service.product.TypeService;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName TypeServiceImpl.java
 * @Description TODO
 * @createTime 2021年12月11日 13:54:00
 */
public class TypeServiceImpl implements TypeService {
    /**
     * 查询所有-产品分类
     * @param
     * @return
     */
    @Override
    public ResultModel getfindAll(TypeModel typeModel) {
        TypeModeltDao typeModeltDao = new TypeModelDaoImpl();
        //创建Redis对象
        Jedis jedis = new Jedis();
        //创建一个list集合
        List<TypeModel> list = new ArrayList<>();
        jedis.select(2);
//        获取dao层查询的数据
        boolean isHave = jedis.exists("productNav");
        if (!isHave) {
            list = typeModeltDao.findAll(typeModel);
            for (TypeModel model:
                 list) {
                jedis.select(2);
                jedis.rpush("productNav",model.getTitle());
            }
            return  ResultModel.getModel(list);
        }
        List<String> productNav = jedis.lrange("productNav", 0, -1);
        for (int i = 0; i <productNav.size() ; i++) {
            TypeModel model = new TypeModel();
            model.setTitle(productNav.get(i));
            list.add(model);
        }
        return  ResultModel.getModel(list);
    }
}
