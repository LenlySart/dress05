package com.cn.wanxi.service.news.impl;

import com.cn.wanxi.dao.news.NewsSortDao;
import com.cn.wanxi.dao.news.impl.NewsSortDaoImpl;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.news.NewsSort;
import com.cn.wanxi.service.news.NewsSortService;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
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
     * 查询所有导航信息分类
     *
     * @param newsSort
     * @return
     */
    @Override
    public ResultModel getFindAll(NewsSort newsSort) {
        NewsSortDao newsDao = new NewsSortDaoImpl();
        //创建Redis对象
        Jedis jedis = new Jedis();
        //创建一个list集合
        List<NewsSort> list = new ArrayList<>();
        jedis.select(3);
        boolean isHave = jedis.exists("newsName");
        if (!isHave) {
            list = newsDao.getFindAll(newsSort);
            for (NewsSort model :
                    list) {
                jedis.select(3);
                jedis.rpush("newsName", model.getName());
            }
            return ResultModel.getModel(list);
        }
        List<String> newsName = jedis.lrange("newsName", 0, -1);
        for (int i = 0; i < newsName.size(); i++) {
            NewsSort model = new NewsSort();
            model.setName(newsName.get(i));
            list.add(model);
        }
        return ResultModel.getModel(list);
    }
}
