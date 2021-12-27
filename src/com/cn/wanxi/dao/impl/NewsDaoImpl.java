package com.cn.wanxi.dao.impl;

import com.cn.wanxi.dao.dress.NewsDao;
import com.cn.wanxi.model.news.News;
import com.cn.wanxi.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NewsDaoImpl.java
 * @Description TODO
 * @createTime 2021年12月14日 10:07:00
 */
public class NewsDaoImpl implements NewsDao {
    /**
     * 查询所有
     * @param news
     * @return
     */
    @Override
    public List<News> findAll(News news) {
//        编写sql语句
        String sql = "select *,date_format(create_time,'%Y-%m-%d') temp," +
                "date_format(update_time,'%Y-%m-%d') time from dress_news where status=0";
        sql += " limit " + (news.getPageNo() + -1) * news.getPageSize() + "," + news.getPageSize();
//        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
//        解析sql语句并返回结果集
        return getList(rs);
    }

    /**
     * 添加
     * @param news
     * @return
     */
    @Override
    public int add(News news) {
        //        编写sql语句
        String sql = "insert into `dress_news`(title,content,nAbstract,href,state,is_show,is_recommend,create_time)  VALUES(?,?,?,?,now())";
        //编译sql语句
        List list = new ArrayList<>();
        list.add(news.getTitle());
        list.add(news.getContent());
        list.add(news.getnAbstract());
        list.add(news.getState());
        list.add(news.getIsShow());
        list.add(news.getIsRecommend());
        return JdbcUtil.updatea(sql,list);
    }

    /**
     * 查询总条数
     * @param news
     * @return
     */
    @Override
    public int getCount(News news) {
        //       查询数据库
        String sql = "select count(*) count from dress_news";
        ResultSet resultSet = JdbcUtil.query(sql);
        int count = 0;
        try {
            while (resultSet.next()){
//              编列count
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 以id查询
     * @param news
     * @return
     */
    @Override
    public News findById(News news) {
        //        编写sql语句
        String sql = "select *,date_format(create_time,'%Y-%m-%d') temp,date_format(update_time,'%Y-%m-%d') time from `dress_news` where id=" + news.getId();
        //        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
        //解析结果集
        return getList(rs).get(0);
    }

    /**
     * 更新
     * @param news
     * @return
     */
    @Override
    public int update(News news) {
        //        编写sql语句
        String sql = "UPDATE `dress_news` SET " +
                "`title`=?," +
                " `content`=?, " +
                " `nAbstract`=?, " +
                "`state`=?," +
                "`is_show`=?," +
                "`is_recommend`=?," +
                "`update_time`= "+"now() " +
                "WHERE (`id`=?)";
        //编译sql语句
        List list = new ArrayList<>();
        list.add(news.getTitle());
        list.add(news.getContent());
        list.add(news.getnAbstract());
        list.add(news.getState());
        list.add(news.getIsShow());
        list.add(news.getIsRecommend());
        list.add(news.getId());
        return JdbcUtil.updatea(sql,list);
    }

    /**
     * 更新
     * @param news
     * @return
     */
    @Override
    public int updateField(News news) {
        String sql = " UPDATE `dress_news` " +
                "SET `" + news.getField() + "` = '" + news.getValue() + "' " +
                "WHERE (`id` = '" + news.getId() + "');";
        return JdbcUtil.update(sql);
    }

    /**
     * 查询首页显示
     * @return
     */
    @Override
    public List<News> getNewsList() {
        //        编写sql语句
        String sql = "select *,date_format(create_time,'%Y-%m-%d') temp," +
                "date_format(update_time,'%Y-%m-%d') time from dress_news where state=1";
//        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
//        解析sql语句并返回结果集
        return getList(rs);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<News> FindAll(News model) {
        //查询热点产品
        String sqlHot = "SELECT d.*,s.name,date_format(d.create_time,'%Y-%m-%d') temp,date_format(d.update_time,'%Y-%m-%d') time FROM dress_news AS d JOIN news_sort AS s ON d.sort_id=s.id where d.is_show=1 ";
        if(model.getSortId()!=-1){
            sqlHot+=" and d.sort_id=" + model.getSortId() ;
        }
        sqlHot += " limit " + (model.getPageNo() - 1) * 2 + "," + 1;
        List<News> listAll = new ArrayList<>();
        List<News> listHot = NewsList(sqlHot, model);
        //将热点产品放入要返回的list
        for (int i = 0; i < listHot.size(); i++) {
            listAll.add(listHot.get(i));
        }
        //查询置顶的产品
        String sqlTop = "SELECT d.*,s.name,date_format(d.create_time,'%Y-%m-%d') temp,date_format(d.update_time,'%Y-%m-%d') time FROM dress_news AS d JOIN news_sort AS s ON d.sort_id=s.id where d.is_recommend=1 ";
        if(model.getSortId()!=-1){
            sqlTop+=" and d.sort_id=" + model.getSortId() ;
        }
        sqlTop += " limit " + (model.getPageNo() - 1) * 2 + "," + 3;
        List<News> listTop = NewsList(sqlTop, model);
        //将置顶产品放入要返回的list
        for (int i = 0; i < listTop.size(); i++) {
            listAll.add(listTop.get(i));
        }
        //查询普通的产品 ，总条数应该为8条减去热点和新闻的条数
        String sqlNormal = "SELECT d.*,s.name,date_format(d.create_time,'%Y-%m-%d') temp,date_format(d.update_time,'%Y-%m-%d') time FROM dress_news AS d JOIN news_sort AS s ON d.sort_id=s.id where  d.is_recommend!=1 and d.is_show!=1 and d.`status`=0 ";
        if(model.getSortId()!=-1){
            sqlNormal+=" and d.sort_id=" + model.getSortId() ;
        }
        Integer pageSize=model.getPageSize()-listAll.size();
        sqlNormal += " limit " + (model.getPageNo() - 1) * pageSize + "," + pageSize;
        List<News> listNormal = NewsList(sqlNormal, model);
        //将普通的放入list
        for (int i = 0; i < listNormal.size(); i++) {
            listAll.add(listNormal.get(i));
        }
        return listAll;
    }

    /**
     * 更新置顶
     * @param news
     * @return
     */
    @Override
    public int updateIsShow(News news) {
        //        编写sql
        String sql = "UPDATE `dress_news` SET `is_show`='"+news.getIsShow()+"' WHERE id="+news.getId();
//        编译sql
        int count = JdbcUtil.update(sql);
        return count;
    }

    /**
     * 更新热点
     * @param news
     * @return
     */
    @Override
    public int updateIsRecommend(News news) {
        //        编写sql
        String sql = "UPDATE `dress_news` SET `is_recommend`='"+news.getIsRecommend()+"' WHERE id="+news.getId();
//        编译sql
        int count = JdbcUtil.update(sql);
        return count;
    }

    /**
     * 以id查询
     * @param id
     * @return
     */
    @Override
    public List<News> getFindById(Integer id) {
        String sql="(SELECT dn.id,dn.title,dn.content,dn.nAbstract,date_format(dn.create_time,'%Y-%m-%d') temp,ni.author,ni.page_view  FROM dress_news dn, news_info ni where dn.`status`=0 AND dn.id=(select min(id) from dress_news where id>"+ id +"))\n" +
                "union\n" +
                "(SELECT dn.id,dn.title,dn.content,dn.nAbstract,date_format(dn.create_time,'%Y-%m-%d') temp,ni.author,ni.page_view  FROM dress_news dn, news_info ni where dn.`status`=0 AND dn.id="+ id +")\n" +
                "union\n" +
                "(SELECT dn.id,dn.title,dn.content,dn.nAbstract,date_format(dn.create_time,'%Y-%m-%d') temp,ni.author,ni.page_view  FROM dress_news dn, news_info ni where dn.`status`=0 AND dn.id=(select max(id) from dress_news where id<"+ id +"))";
        ResultSet rs = JdbcUtil.query(sql);
        List<News> list = new ArrayList<>();
        try {
            while (rs.next()){
                News model = new News();
                model.setId(rs.getInt("id"));
                model.setTitle(rs.getString("title"));
                model.setContent(rs.getString("content"));
                model.setnAbstract(rs.getString("nAbstract"));
                model.setAuthor(rs.getString("author"));
                model.setPageView(rs.getString("page_view"));
                model.setCreateTime(rs.getString("temp"));
                list.add(model);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 删除id要删除的
     * @param
     * @return
     */
    @Override
    public int deleteId(News in) {
        //        编写sql
        String sql = "UPDATE `dress_news` SET `status`='"+in.getStatus()+"' WHERE id="+in.getId();
//        编译sql
        int count = JdbcUtil.update(sql);
        return count;
    }

    /**
     * 查询删除
     * @param news
     * @return
     */
    @Override
    public News selectDele(News news) {
        //        编写sql语句
        String sql ="SELECT * FROM `dress_news` WHERE id="+news.getId();
        //        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
        //解析结果集
        List<News> list = new ArrayList<>();
        try {
            while (rs.next()) {
                News model = new News();
                model.setId(rs.getInt("id"));
                model.setStatus(rs.getInt("status"));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.size()>0?list.get(0):null;
    }

    /**
     * 查询没被删除状态的总条数
     * @param news
     * @return
     */
    @Override
    public int getCountStatus(News news) {
        //       查询数据库
        String sql = "select count(*) count from dress_news where status=0 ";
        ResultSet resultSet = JdbcUtil.query(sql);
        int count = 0;
        try {
            while (resultSet.next()){
//              编列count
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 更新状态
     * @param news
     * @return
     */
    @Override
    public int updateState(News news) {
        //        编写sql
        String sql = "UPDATE `dress_news` SET `state`='"+news.getState()+"' WHERE id="+news.getId();
//        编译sql
        int count = JdbcUtil.update(sql);
        return count;
    }

    /**
     * 封装list
     *
     * @param rs
     * @return
     */
    private List<News> getList(ResultSet rs) {
        List<News> list = new ArrayList<>();
        try {
            while (rs.next()) {
                News model = new News();
                model.setId(rs.getInt("id"));
                model.setTitle(rs.getString("title"));
                model.setContent(rs.getString("content"));
                model.setnAbstract(rs.getString("nAbstract"));
                model.setCreateTime(rs.getString("create_time"));
                model.setUpdateTime(rs.getString("update_time"));
                model.setCreateTime(rs.getString("temp"));
                model.setUpdateTime(rs.getString("time"));
                model.setState(rs.getInt("state"));
                model.setIsRecommend(rs.getInt("is_recommend"));
                model.setIsShow(rs.getInt("is_show"));
                model.setStatus(rs.getInt("status"));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        4.返回数据
        return list;
    }
    
    
    private List<News> NewsList(String sql, News model) {
        List<News> list = new ArrayList<>();
        ResultSet rs = JdbcUtil.query(sql);
        try {
            while (rs.next()) {
                News news = new News();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setnAbstract(rs.getString("nAbstract"));
                news.setCreateTime(rs.getString("create_time"));
                news.setUpdateTime(rs.getString("update_time"));
                news.setCreateTime(rs.getString("temp"));
                news.setUpdateTime(rs.getString("time"));
                news.setState(rs.getInt("state"));
                news.setSortId(rs.getInt("sort_id"));
                news.setIsRecommend(rs.getInt("is_recommend"));
                news.setIsShow(rs.getInt("is_show"));
                news.setStatus(rs.getInt("status"));
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        4.返回数据
        return list;
    }

}
