package com.cn.wanxi.util;

import java.sql.*;
import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName jdbcUtil.java
 * @Description TODO
 * @createTime 2021年11月29日 18:50:00
 */
public class JdbcUtil {

//    数据库的Url地址
    private static String dbUrl = "jdbc:mysql://localhost:3306/dress?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
//    数据库的权限名称
    private static String dbUserName = "root";
//    数据库的密码
    private static String dbPassword = "123456";
//    数据库的驱动
    private static String jdbcName = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;
    private static PreparedStatement pt = null;
    private static ResultSet rs = null;


//    启动驱动
    static {
    try {
        Class.forName(jdbcName);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

}
    //    连接数据库
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    /**
     * 数据更新（新增，修改，删除）
     *
     * @param sql
     * @return
     */
    public static Integer update(String sql) {
        conn = getConnection();//得到数据库连接
        try {
//            编译sql语句
            pt = conn.prepareStatement(sql);
//            执行sql语句
            return pt.executeUpdate();
        } catch (SQLException e) {

        }
        return 0;
    }
    /**
     * 数据更新（新增，修改，删除）
     *
     * @param sql
     * @return
     */
    public static int updatea(String sql ,List list) {
        conn = getConnection();//得到数据库连接
        try {
//            编译sql语句
            pt = conn.prepareStatement(sql);
//            执行sql语句
            for (int i = 0; i <list.size() ; i++) {
                pt.setObject(i+1,list.get(i));
            }
            return pt.executeUpdate();
        } catch (SQLException e) {

        }
        return 0;
    }
//    关闭连接
    public static void closeRe(Connection con, PreparedStatement pt, ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally {
                if(pt != null) {
                    try {
                        pt.close();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }finally{
                        if(con != null) {
                            try {
                                con.close();
                            } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * closeRe:关闭连接.<br/>
     * @param con
     * @param pt
     */
    public static void closeRe(Connection con, PreparedStatement pt) {
        if(pt != null) {
            try {
                pt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally {
                if(con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static ResultSet query(String sql) {
        try {
//            编译sql语句
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
//            解析结果ResultSet集
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
