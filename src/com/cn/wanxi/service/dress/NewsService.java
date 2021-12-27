package com.cn.wanxi.service.dress;

import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.news.News;
import com.cn.wanxi.service.BaseService;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NewsService.java
 * @Description TODO
 * @createTime 2021年11月24日 16:36:00
 */
public interface NewsService extends BaseService<News> {

    /**
     * 新闻内容
     * @return
     */
    List<News> getNewsModel();

    /**
     * 更新
     * @param news
     * @return
     */
    ResultModel updateField(News news);

    /**
     * 新闻动态页面内容
     * @return
     */
    ResultModel getFindAll(News news);

    /**
     * 更改置顶
     * @param news
     * @return
     */
    ResultModel getIsShow(News news);

    /**
     * 更改热点
     * @param news
     * @return
     */
    ResultModel getIsRecommend(News news);

    /**
     * 以id查询
     * @param id
     * @return
     */
    ResultModel getFindById(Integer id);
}
