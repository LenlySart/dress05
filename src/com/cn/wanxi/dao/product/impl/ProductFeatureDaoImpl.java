package com.cn.wanxi.dao.product.impl;

import com.cn.wanxi.dao.product.ProductFeatureDao;
import com.cn.wanxi.model.product.ProductFeature;
import com.cn.wanxi.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName ProductFeatureDaoImpl.java
 * @Description TODO
 * @createTime 2021年12月15日 19:33:00
 */
public class ProductFeatureDaoImpl implements ProductFeatureDao {
    @Override
    public List<ProductFeature> getproductFeature() {
        //        编写sql
        String sql = "select * from product_feature";
//        编译sql
        ResultSet resultSet = JdbcUtil.query(sql);
//        解析sql并返回结果集
        List<ProductFeature> list = new ArrayList<>();
        try {
            while (resultSet.next()){
                ProductFeature model = new ProductFeature();
                model.setName(resultSet.getString("name"));
                model.setImgHref(resultSet.getString("imgHref"));
                model.setpDescribe(resultSet.getString("p_describe"));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
