package com.cn.wanxi.dao.product.impl;

import com.cn.wanxi.dao.product.ProductMessageDao;
import com.cn.wanxi.model.product.ProductMessage;
import com.cn.wanxi.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName ProductMessageDaoImpl.java
 * @Description TODO
 * @createTime 2021年12月28日 16:29:00
 */
public class ProductMessageDaoImpl implements ProductMessageDao {
    @Override
    public ProductMessage FindById(ProductMessage product) {
        //        编写sql语句
        String sql = "select * from product_message where id=" + product.getId();
        //        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
        //解析结果集
        List<ProductMessage> list = new ArrayList<>();

        try {
            while (rs.next()) {
                ProductMessage model = new ProductMessage();
                model.setSeller(rs.getString("seller"));
                model.setManMessage(rs.getString("man_message"));
                model.setFemaleMessage(rs.getString("female_message"));
                list.add(model);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list.get(0);
    }
}
