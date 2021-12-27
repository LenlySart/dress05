package com.cn.wanxi.dao.impl;

import com.cn.wanxi.dao.NavModelDao;
import com.cn.wanxi.model.product.NavModel;
import com.cn.wanxi.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NavMedelDaoImpl.java
 * @Description TODO
 * @createTime 2021年12月15日 17:01:00
 */
public class NavModelDaoImpl implements NavModelDao {
    /**
     * 查询所有
     * @param
     * @return
     */
    @Override
    public List<NavModel> getProductNav() {
        //        编写sql
        String sql = "select * from product_nav";
//        编译sql
        ResultSet resultSet = JdbcUtil.query(sql);
//        解析sql并返回结果集
        List<NavModel> list = new ArrayList<>();
        try {
            while (resultSet.next()){
                NavModel model = new NavModel();
                model.setTitle(resultSet.getString("title"));
                model.setHref(resultSet.getString("href"));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
