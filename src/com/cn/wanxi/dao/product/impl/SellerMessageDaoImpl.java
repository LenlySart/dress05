package com.cn.wanxi.dao.product.impl;

import com.cn.wanxi.dao.product.SellerMessageDao;
import com.cn.wanxi.model.dress.SellerMessage;
import com.cn.wanxi.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName SellerMessageDaoImpl.java
 * @Description TODO
 * @createTime 2021年12月13日 15:42:00
 */
public class SellerMessageDaoImpl implements SellerMessageDao {
    /**
     * 查询总条数
     * @param sellerMessage
     * @return
     */
    @Override
    public int getCountStatus(SellerMessage sellerMessage) {

        return 0;
    }

    /**
     * 查询所有
     * @param sellerMessage
     * @return
     */
    @Override
    public List<SellerMessage> findAll(SellerMessage sellerMessage) {
        //       查询数据库
        String sql = "select *,date_format(create_time,'%Y-%m-%d') temp," +
                "date_format(update_time,'%Y-%m-%d') time from `product_message` ";
        sql += " limit " + (sellerMessage.getPageNo() + -1) * sellerMessage.getPageSize() + "," + sellerMessage.getPageSize();
//        连接数据库
        ResultSet rs = JdbcUtil.query(sql);
//        用list来接收数据并使用集合 储存数据
        List<SellerMessage> list = new ArrayList<>();
        try {
            while (rs.next()){
                SellerMessage message = new SellerMessage();
                message.setId(rs.getInt("id"));
                message.setSeller(rs.getString("seller"));
                message.setFemaleMessage(rs.getString("female_message"));
                message.setManMessage(rs.getString("man_message"));
                message.setState(rs.getInt("state"));
                message.setCreateTime(rs.getString("temp"));
                message.setUpdateTime(rs.getString("time"));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<SellerMessage> getproductFeature() {
        //       查询数据库
        String sql = "select *,date_format(create_time,'%Y-%m-%d') temp," +
                "date_format(update_time,'%Y-%m-%d') time from `product_message` ";
//        连接数据库
        ResultSet rs = JdbcUtil.query(sql);
//        用list来接收数据并使用集合 储存数据
        List<SellerMessage> list = new ArrayList<>();
        try {
            while (rs.next()){
                SellerMessage message = new SellerMessage();
                message.setId(rs.getInt("id"));
                message.setSeller(rs.getString("seller"));
                message.setFemaleMessage(rs.getString("female_message"));
                message.setManMessage(rs.getString("man_message"));
                message.setState(rs.getInt("state"));
                message.setCreateTime(rs.getString("temp"));
                message.setUpdateTime(rs.getString("time"));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
