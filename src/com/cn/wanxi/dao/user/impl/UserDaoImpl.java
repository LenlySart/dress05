package com.cn.wanxi.dao.user.impl;

import com.cn.wanxi.dao.user.UserDao;
import com.cn.wanxi.model.User;
import com.cn.wanxi.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName UserDaoImpl.java
 * @Description TODO
 * @createTime 2021年11月30日 19:26:00
 */
public class UserDaoImpl implements UserDao {

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public User getLogin(User user) {

//        sql字符串拼接
        String sql = "select * from user where username='"+ user.getUsername() +"' and password='"+user.getPassword()+"'";

//        通过调用工具类JDBC查询数据库，得到结果集
        ResultSet resultSet = JdbcUtil.query(sql);

        //        创建一个空值对象
        User model = null;
        try {
//            如果结果集里面有值，则进入while循环
            while (resultSet.next()) {
                model = new User();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    /**
     * 查询用户列表
     * @param user
     * @return
     */
    @Override
    public List<User> findAll(User user){
//       查询数据库
        String sql = "select * from user where 1=1 ";
        sql +=setSql(user);
        sql +=" limit "+(user.getPageNo() + -1) * user.getPageSize() +","+user.getPageSize();
//        连接数据库
        ResultSet rs = JdbcUtil.query(sql);
//        用list来接收数据并使用集合 储存数据
        return getList(rs);
    }

    /**
     * 封装sql语句
     *
     * @param user
     * @return
     */
    private String setSql(User user) {
        String sql = "";

        if (!"".equals(user.getUsername())) {
            sql += " and username like '%" + user.getUsername() + "%' ";
        }
        if (!"".equals(user.getPhone())) {
            sql += " and phone like '%" + user.getPhone() + "%' ";
        }
        if (user.getSex()!=-1 && !"".equals(user.getSex())) {
            sql += "and sex='" + user.getSex() + "'";
        }
//        生日
        if (!"".equals(user.getStart()) && "".equals(user.getEnd())) {
            sql += " and birthday >='" + user.getStart() + "' ";
        }
        if ("".equals(user.getStart()) && !"".equals(user.getEnd())) {
            sql += " and birthday <='" + user.getEnd() + "' ";
        }
        if (!"".equals(user.getStart()) && !"".equals(user.getEnd())) {
            sql += " and birthday between '" + user.getStart() + "' and '" + user.getEnd() + "'";
        }
        if(user.getStatus()==null){
            sql +=" and status=0";
        }
//        状态
        if (user.getState() != -1) {
            sql += " and state=" + user.getState();
        }
//        爱好
        if (user.getHobby()!=null &&!"".equals(user.getHobby())) {
            String[] hobbys = user.getHobby().split(",");
            sql += " and (";
            for (int i = 0; i < hobbys.length; i++) {
                if (i == 0) {
                    sql += "  find_in_set('" + hobbys[i] + "',hobby) ";
                } else {
                    sql += " or find_in_set('" + hobbys[i] + "',hobby) ";
                }

            }
            sql += " )";
        }
        sql += " order by id desc ";
        return sql;
    }


    /**
     * 添加用户
     * @param user
     * @return
     */
    @Override
    public int add(User user) {
        Connection con=null;
        PreparedStatement ps=null;
        int count=0;
        //连接数据库
        try {
            con =  JdbcUtil.getConnection();
            //        编写sql语句，添加数据
            String sql = "insert into user " +
                    "(`username`,`password`,`email`," +
                    "`phone`,`sex`,`state`,`birthday`," +
                    "`hobby`) VALUES('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getEmail()+"'," +
                    "'"+user.getPhone()+"','"+user.getSex()+"','"+user.getState()+"'," +
                    "'"+user.getBirthday()+"','"+user.getHobby()+"')";
            //编译sql语句
            ps=con.prepareStatement(sql);
            //赋值count
            count=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            关闭连接
            JdbcUtil.closeRe(con,ps);
        }
        return count;
    }

    /**
     * 页数查询
     * @param user
     * @return
     */
    @Override
    public int getCount(User user) {
//        编写sql语句
        String sql = "select count(*) count from user";
//        编译sql语句
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
     * @param user
     * @return
     */
    @Override
    public User findById(User user) {
        //        编写sql语句
        String sql ="select *,date_format(birthday,'%Y-%m-%d') temp from user where id="+user.getId();
        //        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
        //解析结果集
        return getList(rs).get(0);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public int update(User user) {
        //编译sql语句
        Connection con=null;
        PreparedStatement ps=null;
        int count=0;
        //连接数据库
        try {
            con =  JdbcUtil.getConnection();
            //        编写sql语句
            String sql = "UPDATE `user` SET " +
                    "`username`='"+user.getUsername()+"'," +
                    " `email`='"+user.getEmail()+"', " +
                    "`phone`='"+user.getPhone()+"', " +
                    "`sex`='"+user.getSex()+"', " +
                    "`state`='"+user.getState()+"'," +
                    " `birthday`='"+user.getBirthday()+"', " +
                    "`hobby`='"+user.getHobby()+"' " +
                    "WHERE (`id`='"+user.getId()+"')";
            //编译sql语句
            ps=con.prepareStatement(sql);
            //赋值count
            count=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            关闭连接
            JdbcUtil.closeRe(con,ps);
        }
        return count;
    }

    /**
     * 查询密码
     * @param user
     * @return
     */
    @Override
    public User getSelectPass(User user) {
//        编写sql语句
        String sql = "SELECT * FROM `user` WHERE id="+user.getId();
//        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
        List<User> list = new ArrayList<>();
        try {
            while (rs.next()) {
                User model = new User();
                model.setId(rs.getInt("id"));
                model.setPassword(rs.getString("password"));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.size()>0?list.get(0):null;
//    }

    }


    @Override
    public int updatePasswod(User user) {
        Connection con=null;
        PreparedStatement ps=null;
        int count=0;
        //        编写sql语句

//连接数据库
        try {
            con =  JdbcUtil.getConnection();
            //        编写sql语句
            String sql = "UPDATE `user` SET `password`='"+user.getPass()+"' WHERE id="+user.getId();
            //编译sql语句
            ps=con.prepareStatement(sql);
            //赋值count
            count=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            关闭连接
            JdbcUtil.closeRe(con,ps);
        }
        return count;
    }

    /**
     * 删除用户
     * @param in
     * @return
     */
    @Override
    public int deleteId(User in) {
//        编写sql
        String sql = "UPDATE `user` SET `status`='"+in.getStatus()+"' WHERE id="+in.getId();
//        编译sql
        int count = JdbcUtil.update(sql);
        return count;
    }

    /**
     * 查询删除用户状态
     * @param user
     * @return
     */
    @Override
    public User selectDele(User user) {
        //        编写sql语句
        String sql ="SELECT * FROM `user` WHERE id="+user.getId();
        //        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
        //解析结果集
        List<User> list = new ArrayList<>();
        try {
            while (rs.next()) {
                User model = new User();
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
     * 用户查询
     * @param user
     * @return
     */
    @Override
    public List<User> selcet(User user) {
//       查询数据库
        String sql = "select * from user where status=0 ";
        sql +=" limit "+(user.getPageNo() + -1) * user.getPageSize() +","+user.getPageSize();
//        连接数据库
        ResultSet resultSet = JdbcUtil.query(sql);
//        用list来接收数据并使用集合 储存数据
        return getList(resultSet);
    }

    /**
     * 查询没被删除的用户
     * @param user
     * @return
     */
    @Override
    public int getCountStatus(User user) {
        //       查询数据库
        String sql = "select count(*) count from user where status=0 ";
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
     * 修改状态
     * @param in
     * @return
     */
    @Override
    public int updateState(User in) {
        //        编写sql
        String sql = "UPDATE `user` SET `state`='"+in.getState()+"' WHERE id="+in.getId();
//        编译sql
        int count = JdbcUtil.update(sql);
        return count;
    }


    /**
     * 封装list
     * @param rs
     * @return
     */
    private List<User> getList(ResultSet rs) {
        List<User> list = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setSex(rs.getInt("sex"));
                user.setBirthday(rs.getString("birthday"));
                user.setHobby(rs.getString("hobby"));
                user.setState(rs.getInt("state"));
                user.setStatus(rs.getInt("status"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        4.返回数据
        return list;
    }
}

