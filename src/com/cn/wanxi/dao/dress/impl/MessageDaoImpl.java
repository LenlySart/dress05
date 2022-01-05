package com.cn.wanxi.dao.dress.impl;

import com.cn.wanxi.dao.dress.MessageDao;
import com.cn.wanxi.model.dress.Message;
import com.cn.wanxi.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName MessageDaoImpl.java
 * @Description TODO
 * @createTime 2021年12月17日 11:28:00
 */
public class MessageDaoImpl implements MessageDao {
    @Override
    public List<Message> findAll(Message message) {
        //        编写sql语句
        String sql = "select *,date_format(create_time,'%Y-%m-%d') temp," +
                "date_format(update_time,'%Y-%m-%d') time from dress_message where `status`=0 ";
        sql +=setSql(message);
        sql += " limit " + (message.getPageNo() + -1) * message.getPageSize() + "," + message.getPageSize();
//        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
//        解析sql语句并返回结果集
        return getList(rs);
    }

    /**
     * 模糊查询条件语句
     */
    private String setSql(Message message) {
        String sql = "";

        if (!"".equals(message.getVisitorName())) {
            sql += " and visitorName like '%" + message.getVisitorName() + "%' ";
        }
        if (!"".equals(message.getVisitorEmail())) {
            sql += " and visitorEmail like '%" + message.getVisitorEmail() + "%' ";
        }
        if (!"".equals(message.getMessage())) {
            sql += " and message like '%" + message.getMessage() + "%' ";
        }
        if (!"".equals(message.getStart()) && "".equals(message.getEnd())) {
            sql += " and create_time >= " + message.getStart() + "";
        }

        if ("".equals(message.getStart()) && !"".equals(message.getEnd())) {
            sql += " and create_time <= " + message.getEnd() + "";
        }

        if (!"".equals(message.getStart()) && !"".equals(message.getEnd())) {
            sql += " and create_time between '" + message.getStart() + " ' and '" + message.getEnd() + "'";
        }
        return sql;
    }

    /**
     * 添加
     * @param message
     * @return
     */
    @Override
    public int add(Message message) {
        //        编写sql语句
        String sql = "insert into `dress_message`(visitorName,visitorEmail,message,create_time)  VALUES(?,?,?,now())";
        //编译sql语句
        List list = new ArrayList<>();
        list.add(message.getVisitorName());
        list.add(message.getVisitorEmail());
        list.add(message.getMessage());
        return JdbcUtil.updatea(sql,list);
    }

    @Override
    public int getCount(Message message) {
        //       查询数据库
        String sql = "select count(*) count from dress_message where `status`=0 ";
        sql += setSql(message);
        ResultSet resultSet = JdbcUtil.query(sql);
        int count = 0;
        try {
            while (resultSet.next()) {
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
     * @param message
     * @return
     */
    @Override
    public Message findById(Message message) {
        //        编写sql语句
        String sql = "select *,date_format(create_time,'%Y-%m-%d') temp,date_format(update_time,'%Y-%m-%d') time from `dress_message` where id=" + message.getId();
        //        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
        //解析结果集
        return getList(rs).get(0);
    }

    /**
     * 更新
     * @param message
     * @return
     */
    @Override
    public int update(Message message) {
        //        编写sql语句
        String sql = "UPDATE `dress_message` SET " +
                "`visitorName`=?," +
                " `visitorEmail`=?, " +
                "`state`=?," +
                "`update_time`= "+"now() " +
                "WHERE (`id`=?)";
        //编译sql语句
        List list = new ArrayList<>();
        list.add(message.getVisitorName());
        list.add(message.getVisitorEmail());
        list.add(message.getState());
        list.add(message.getId());
        return JdbcUtil.updatea(sql,list);
    }

    /**
     * 根据id删除
     * @param
     * @return
     */
    @Override
    public int deleteId(Message in) {
        //        编写sql
        String sql = "UPDATE `dress_message` SET `status`='"+in.getStatus()+"' WHERE id="+in.getId();
//        编译sql
        int count = JdbcUtil.update(sql);
        return count;
    }

    @Override
    public Message selectDele(Message message) {
        return null;
    }

    /**
     * 查询没被删除的总条数
     * @param message
     * @return
     */
    @Override
    public int getCountStatus(Message message) {
        //       查询数据库
        String sql = "select count(*) count from dress_message where status=0 ";
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
     * @param message
     * @return
     */
    @Override
    public int updateState(Message message) {
        //        编写sql
        String sql = "UPDATE `dress_message` SET `state`='"+message.getState()+"' WHERE id="+message.getId();
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
    private List<Message> getList(ResultSet rs) {
        List<Message> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt("id"));
                message.setVisitorEmail(rs.getString("visitorEmail"));
                message.setVisitorName(rs.getString("visitorName"));
                message.setCreateTime(rs.getString("create_time"));
                message.setUpdateTime(rs.getString("update_time"));
                message.setCreateTime(rs.getString("temp"));
                message.setUpdateTime(rs.getString("time"));
                message.setMessage(rs.getString("message"));
                message.setState(rs.getInt("state"));
                message.setStatus(rs.getInt("status"));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        4.返回数据
        return list;
    }

    /**
     * 以value更新
     * @param message
     * @return
     */
    @Override
    public int updateField(Message message) {
        String sql = " UPDATE `dress_message` " +
                "SET `" + message.getField() + "` = '" + message.getValue() + "' " +
                "WHERE (`id` = '" + message.getId() + "');";
        return JdbcUtil.update(sql);
    }
}
