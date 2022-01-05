package com.cn.wanxi.service.product.impl;

import com.cn.wanxi.dao.product.ProductFeatureDao;
import com.cn.wanxi.dao.product.impl.ProductFeatureDaoImpl;
import com.cn.wanxi.model.product.ProductFeature;
import com.cn.wanxi.service.product.ProductFeatureService;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName ProductFeatureServiceImpl.java
 * @Description TODO
 * @createTime 2021年12月15日 19:31:00
 */
public class ProductFeatureServiceImpl implements ProductFeatureService {
    @Override
    public List<ProductFeature> getproductFeature() {
        List<ProductFeature> list = new ArrayList<>();
        Jedis jedis = new Jedis();
        boolean isHave = jedis.exists("productName");
        if (isHave) {
            List<String> productName =  jedis.lrange("productName",0,-1);
            List<String> productImgHref =  jedis.lrange("productImgHref",0,-1);
            List<String> productPDescribe =  jedis.lrange("productPDescribe",0,-1);
            int length = productName.size();
            for (int i = 0; i <length ; i++) {
                ProductFeature productFeature = new ProductFeature();
                productFeature.setName(productName.get(i));
                productFeature.setImgHref(productImgHref.get(i));
                productFeature.setpDescribe(productPDescribe.get(i));
                list.add(productFeature);
            }
        } else {
            ProductFeatureDao productFeatureDao = new ProductFeatureDaoImpl();
            list = productFeatureDao.getproductFeature();
            for (ProductFeature product:
                 list) {
                jedis.rpush("productName",product.getName());
                jedis.rpush("productImgHref",product.getImgHref());
                jedis.rpush("productPDescribe",product.getpDescribe());
            }
        }
        return list;
    }
}
