package com.cn.wanxi.dao.dress.impl;

import com.cn.wanxi.dao.dress.NavDao;
import com.cn.wanxi.model.dress.Nav;
import com.cn.wanxi.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NavDaoImpl.java
 * @Description TODO
 * @createTime 2021年12月15日 14:22:00
 */
public class NavDaoImpl implements NavDao {
    /**
     * 查询所有
     * @param
     * @return
     */
    @Override
    public List<Nav> findAll() {
//        编写sql
        String sql = "select * from dress_nav";
//        编译sql
        ResultSet resultSet = JdbcUtil.query(sql);
//        解析sql并返回结果集
        List<Nav> list = new ArrayList<>();
        try {
            while (resultSet.next()){
                Nav model = new Nav();
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
