package com.cn.wanxi.service.dress_impl;

import com.cn.wanxi.dao.dress.NewsSortDao;
import com.cn.wanxi.dao.impl.NewsSortDaoImpl;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.news.NewsSort;
import com.cn.wanxi.service.dress.NewsSortService;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NewsSortServiceImpl.java
 * @Description TODO
 * @createTime 2021年12月22日 14:03:00
 */
public class NewsSortServiceImpl implements NewsSortService {

    /**
     * 查询所有
     * @param newsSort
     * @return
     */
    @Override
    public ResultModel getFindAll(NewsSort newsSort) {
        NewsSortDao newsDao = new NewsSortDaoImpl();
        List<NewsSort> list = newsDao.getFindAll(newsSort);
        return ResultModel.getModel(list);
    }
}
