package com.cn.wanxi.service.news.impl;

import com.cn.wanxi.dao.news.NewsDao;
import com.cn.wanxi.dao.news.impl.NewsDaoImpl;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.news.News;
import com.cn.wanxi.service.news.NewsService;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NewsServiceImpl.java
 * @Description TODO
 * @createTime 2021年11月29日 12:08:00
 */
public class NewsServiceImpl implements NewsService {

    /**
     * 添加
     * @param news
     * @return
     */
    @Override
    public ResultModel add(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        int count = newsDao.add(news);
        return ResultModel.getModel(count);
    }

    /**
     * 查询总条数
     * @param news
     * @return
     */
    @Override
    public ResultModel getCount(News news) {
        return null;
    }

    /**
     * 以id查询
     * @param news
     * @return
     */
    @Override
    public ResultModel findById(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        News model = newsDao.findById(news);
        return ResultModel.getModel(model);
    }

    /**
     * 更新
     * @param news
     * @return
     */
    @Override
    public ResultModel update(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        int count = newsDao.update(news);
        return ResultModel.getModel(count);
    }

    /**
     * 以value更新
     * @param news
     * @return
     */
    @Override
    public ResultModel updateField(News news) {
        NewsDao newsDao = new NewsDaoImpl();
//        if (news.getField().equals("username")) {
//            News model = newsDao.findByUsername(news);
//            if (model.getUsername().equals(news.getValue())) {
//                return ResultModel.getModel("sameUsername");
//            }
//        }
        int result = newsDao.updateField(news);
        return ResultModel.getModel(result == 1 ? "success" : "error");
    }

    /**
     * 查询内容
     * @return
     */
    @Override
    public ResultModel getFindAll(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        int count = newsDao.getCountStatus(news);
        List<News> list = newsDao.FindAll(news);
        return ResultModel.getModel(count,list);
    }

    /**
     * 更新置顶
     * @param news
     * @return
     */
    @Override
    public ResultModel getIsShow(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        int count = newsDao.updateIsShow(news);
        return ResultModel.getModel(count);
    }
    /**
     * 更新热点
     * @param news
     * @return
     */
    @Override
    public ResultModel getIsRecommend(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        int count = newsDao.updateIsRecommend(news);
        return ResultModel.getModel(count);
    }

    /**
     * 以id查询
     * @param
     * @return
     */
    @Override
    public ResultModel getFindById(Integer id) {
        NewsDao newsDao = new NewsDaoImpl();
        List<News> list = newsDao.getFindById(id);
        return ResultModel.getModel(list);
    }


    /**
     * 删除
     * @param news
     * @return
     */
    @Override
    public ResultModel deleteId(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        int id =news.getId();
        News in = new News();
        in.setStatus(1);
        in.setId(id);
        int count = newsDao.deleteId(in);
        return ResultModel.getModel(count);
    }

    /**
     * 查询没被删除的条数
     * @param news
     * @return
     */
    @Override
    public ResultModel getStatusCount(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        int count = newsDao.getCountStatus(news);
        return ResultModel.getModel(count);
    }

    /**
     * 状态
     * @param news
     * @return
     */
    @Override
    public ResultModel getState(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        int count = newsDao.updateState(news);
        return ResultModel.getModel(count);
    }

    /**
     * 查询所有
     * @param news
     * @return
     */
    @Override
    public ResultModel getfindAll(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        int count = newsDao.getCountStatus(news);
        List<News> list = newsDao.findAll(news);
        return ResultModel.getModel(count,list);
    }

    /**
     * 查询底部和首页内容
     * @return
     */
    @Override
    public List<News> getNewsModel() {
        List<News> list = new ArrayList<>();
        Jedis jedis = new Jedis();
        jedis.select(1);
        boolean isHave = jedis.exists("newsTitle");
        if (isHave) {
            List<String> newsTitle = jedis.hvals("newsTitle");
            List<String> newsCreateTime = jedis.hvals("newsCreateTime");
            int length = newsTitle.size();
            for (int i = 0; i <length ; i++) {
                News model = new News();
                model.setTitle(newsTitle.get(i));
                model.setCreateTime(newsCreateTime.get(i));
                list.add(model);
            }
            System.out.println("11--redis");
        } else {
            NewsDao newsDao = new NewsDaoImpl();
            list = newsDao.getNewsList();
            jedis.select(1);
            for (News news:
                 list) {
                jedis.hset("newsTitle", String.valueOf(news.getId()),news.getTitle());
                jedis.hset("newsCreateTime", String.valueOf(news.getId()),news.getCreateTime());
            }

        }
        return list;

    }
}
