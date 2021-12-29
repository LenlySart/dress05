package com.cn.wanxi.dao.product.impl;

import com.cn.wanxi.dao.product.TypeModeltDao;
import com.cn.wanxi.model.product.TypeModel;
import com.cn.wanxi.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName TypeModelDaoImpl.java
 * @Description TODO
 * @createTime 2021年12月11日 14:01:00
 */
public class TypeModelDaoImpl implements TypeModeltDao {


    /**
     * 查询所有
     * @param typeModel
     * @return
     */
    @Override
    public List<TypeModel> findAll(TypeModel typeModel) {
        //       查询数据库
        String sql = "select * from product_nav";
//        连接数据库
        ResultSet rs = JdbcUtil.query(sql);
//        用list来接收数据并使用集合 储存数据
        List<TypeModel> list = new ArrayList<>();
        try {
            while (rs.next()){
                TypeModel model = new TypeModel();
                model.setId(rs.getInt("id"));
                model.setTitle(rs.getString("title"));
                model.setHref(rs.getString("href"));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
