package com.cn.wanxi.dao.dress;

import com.cn.wanxi.dao.BaseDaoImpl;
import com.cn.wanxi.model.news.News;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NewsDao.java
 * @Description TODO
 * @createTime 2021年11月24日 16:24:00
 */
public interface NewsDao extends BaseDaoImpl<News> {
    /**
     * 更新
     * @param news
     * @return
     */
    int updateField(News news);

    /**
     * 查询
     * @return
     */
    List<News> getNewsList();

    /**
     * 查询所有
     * @return
     */
    List<News> FindAll(News news);

    /**
     * 更改置顶
     * @param news
     * @return
     */
    int updateIsShow(News news);

    /**
     * 更改热点
     * @param news
     * @return
     */
    int updateIsRecommend(News news);

    /**
     * 以id查询
     * @param id
     * @return
     */
    List<News> getFindById(Integer id);
}
