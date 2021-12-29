package com.cn.wanxi.dao.news.impl;

import com.cn.wanxi.dao.news.NewsSortDao;
import com.cn.wanxi.model.news.NewsSort;
import com.cn.wanxi.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NewsSortDaoImpl.java
 * @Description TODO
 * @createTime 2021年12月22日 14:06:00
 */
public class NewsSortDaoImpl implements NewsSortDao {

    /**
     * 查询所有分类
     * @param newsSort
     * @return
     */
    @Override
    public List<NewsSort> getFindAll(NewsSort newsSort) {
        //        编写sql语句
        String sql = "select * from news_sort ";
//        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
        List<NewsSort> list = new ArrayList<>();
        try {
            while (rs.next()){
                NewsSort model = new NewsSort();
                model.setId(rs.getInt("id"));
                model.setName(rs.getString("name"));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
