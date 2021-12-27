package com.cn.wanxi.dao.impl;

import com.cn.wanxi.dao.dress.CompanyStoreDao;
import com.cn.wanxi.model.company.CompanyStore;
import com.cn.wanxi.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName CompanyStoreDaoImpl.java
 * @Description TODO
 * @createTime 2021年12月16日 19:53:00
 */
public class CompanyStoreDaoImpl implements CompanyStoreDao {

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<CompanyStore> getCompanyStore() {
        //        编写sql语句
        String sql = "select * from comany_store";
        //        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
        //解析结果集
        List<CompanyStore> list = new ArrayList<>();
        try {
            while (rs.next()) {
                CompanyStore model = new CompanyStore();
                model.setId(rs.getInt("id"));
                model.setOfflineStore(rs.getString("offline_store"));
                model.setStoreImage(rs.getString("store_image"));
                model.setIsShow(rs.getInt("is_show"));
                model.setCreateTime(rs.getString("create_time"));
                model.setUpdateTime(rs.getString("update_time"));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询线下门店图片
     * @return
     */
    @Override
    public List<CompanyStore> getCompanyStoreList() {
        //        编写sql语句
        String sql = "select * from comany_store where is_show=1";
        //        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
        //解析结果集
        List<CompanyStore> list = new ArrayList<>();
        try {
            while (rs.next()) {
                CompanyStore model = new CompanyStore();
                model.setId(rs.getInt("id"));
                model.setOfflineStore(rs.getString("offline_store"));
                model.setStoreImage(rs.getString("store_image"));
                model.setIsShow(rs.getInt("is_show"));
                model.setCreateTime(rs.getString("create_time"));
                model.setUpdateTime(rs.getString("update_time"));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
