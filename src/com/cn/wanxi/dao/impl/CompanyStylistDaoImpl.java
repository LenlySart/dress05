package com.cn.wanxi.dao.impl;

import com.cn.wanxi.dao.dress.CompanyStylistDao;
import com.cn.wanxi.model.company.CompanyStylist;
import com.cn.wanxi.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName CompanyStylistDaoImpl.java
 * @Description TODO
 * @createTime 2021年12月16日 19:07:00
 */
public class CompanyStylistDaoImpl implements CompanyStylistDao {
    @Override
    public List<CompanyStylist> getCompanyStylist() {
        //        编写sql语句
        String sql = "select * from comany_stylist";
        //        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
        //解析结果集
        List<CompanyStylist> list = new ArrayList<>();
        try {
            while (rs.next()) {
                CompanyStylist model = new CompanyStylist();
                model.setId(rs.getInt("id"));
                model.setStylist(rs.getString("stylist"));
                model.setAboutDesigner(rs.getString("about_designer"));
                model.setHeadShot(rs.getString("head_shot"));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
