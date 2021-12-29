package com.cn.wanxi.dao.news;

import com.cn.wanxi.model.news.NewsSort;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NewsSortDao.java
 * @Description TODO
 * @createTime 2021年12月22日 14:06:00
 */
public interface NewsSortDao {
    /**
     * 查询所有分类
     * @param newsSort
     * @return
     */
    List<NewsSort> getFindAll(NewsSort newsSort);
}
