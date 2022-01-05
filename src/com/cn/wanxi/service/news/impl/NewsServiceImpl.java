package com.cn.wanxi.service.news.impl;

import com.cn.wanxi.dao.news.NewsDao;
import com.cn.wanxi.dao.news.impl.NewsDaoImpl;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.news.News;
import com.cn.wanxi.model.product.Product;
import com.cn.wanxi.service.news.NewsService;
import com.cn.wanxi.util.Tool;
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
     *
     * @param news
     * @return
     */
    @Override
    public ResultModel add(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        int count = newsDao.add(news);
        Redis(count);
        return ResultModel.getModel(count);
    }

    /**
     * 查询总条数
     *
     * @param news
     * @return
     */
    @Override
    public ResultModel getCount(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        int count = newsDao.getCount(news);
        return ResultModel.getModel(count);
    }

    /**
     * 以id查询
     *
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
     *
     * @param news
     * @return
     */
    @Override
    public ResultModel update(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        int count = newsDao.update(news);
        Redis(count);
        return ResultModel.getModel(count);
    }

    /**
     * 以value更新
     *
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
        int count = newsDao.updateField(news);
        Redis(count);
        return ResultModel.getModel(count);
    }

    private void Redis(int count){
        if(count>0){
            Jedis jedis  = new Jedis();
            jedis.select(3);
            jedis.del("newsTitle","newsContent","newsNAbstract","newsCreateTime");
        }
    }

    /**
     * 查询内容
     *
     * @return
     */
    @Override
    public ResultModel getFindAll(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        int count = newsDao.getCountStatus(news);
        //创建Redis对象
        Jedis jedis = new Jedis();
        //创建一个list集合
        List<News> list = new ArrayList<>();
        //定义一个Integer类型的字段接收前端传过来的limit
        Integer limit = news.getPageNo();
        //判断Redis中是否有值，如果有从Redis取，没有从数据库中取
        if (limit < 2) {
            if (news.getSortId() > 0) {
                list = newsDao.FindAll(news);
                return ResultModel.getModel(count, list);
            }
            //查询Redis第三个数据库
            jedis.select(3);
            //定义一个Boolean类型的字段接收Redis中的某个值用于判断
            boolean isHave = jedis.exists("newsTitle");
            if (!isHave) {
                list = newsDao.FindAll(news);
                for (News model :
                        list) {
                    jedis.rpush("newsTitle", model.getTitle());
                    jedis.rpush("newsContent", model.getContent());
                    jedis.rpush("newsNAbstract", model.getnAbstract());
                    jedis.rpush("newsCreateTime", model.getCreateTime());
                }
                return ResultModel.getModel(count, list);
            }
            List<String> newsTitle = jedis.lrange("newsTitle", 0, -1);
            List<String> newsContent = jedis.lrange("newsContent", 0, -1);
            List<String> newsNAbstract = jedis.lrange("newsNAbstract", 0, -1);
            List<String> newsCreateTime = jedis.lrange("newsCreateTime", 0, -1);
            int length = newsTitle.size();
            for (int i = 0; i < length; i++) {
                News model = new News();
                model.setTitle(newsTitle.get(i));
                model.setContent(newsContent.get(i));
                model.setnAbstract(newsNAbstract.get(i));
                model.setCreateTime(newsCreateTime.get(i));
                list.add(model);
            }
        } else {
            list = newsDao.FindAll(news);
        }
        return ResultModel.getModel(count, list);
    }

    /**
     * 更新置顶
     *
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
     *
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
     *
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
     *
     * @param news
     * @return
     */
    @Override
    public ResultModel deleteId(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        int id = news.getId();
        News in = new News();
        in.setStatus(1);
        in.setId(id);
        int count = newsDao.deleteId(in);
        Redis(count);
        return ResultModel.getModel(count);
    }

    /**
     * 查询没被删除的条数
     *
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
     *
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
     * 前台查询所有
     *
     * @param news
     * @return
     */
    @Override
    public ResultModel getfindAll(News news) {
        NewsDao newsDao = new NewsDaoImpl();
        int count = newsDao.getCountStatus(news);
        List<News> list = newsDao.findAll(news);
        return ResultModel.getModel(count, list);
    }

    /**
     * 查询底部和首页内容
     *
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
            for (int i = 0; i < length; i++) {
                News model = new News();
                model.setTitle(newsTitle.get(i));
                model.setCreateTime(newsCreateTime.get(i));
                list.add(model);
            }
        } else {
            NewsDao newsDao = new NewsDaoImpl();
            list = newsDao.getNewsList();
            jedis.select(1);
            for (News news :
                    list) {
                jedis.hset("newsTitle", String.valueOf(news.getId()), news.getTitle());
                jedis.hset("newsCreateTime", String.valueOf(news.getId()), news.getCreateTime());
            }

        }
        return list;

    }
}
