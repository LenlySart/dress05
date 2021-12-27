package com.cn.wanxi.dao.impl;

import com.cn.wanxi.dao.dress.ProductDao;
import com.cn.wanxi.model.product.Product;
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
 * @ClassName ProductDaoImpl.java
 * @Description TODO
 * @createTime 2021年12月09日 11:34:00
 */
public class ProductDaoImpl implements ProductDao {
   private static Connection con = null;
    private static PreparedStatement ps = null;
    /**
     * 添加产品
     * @param product
     * @return
     */
    @Override
    public int add(Product product) {
        
        int count = 0;
        //连接数据库
        try {
            con = JdbcUtil.getConnection();
            //        编写sql语句，添加数据
            String sql = "insert into dress_product " +
                    "(`name`,`img_href`,`marked_price`," +
                    "`normal_price`,`is_show`,`state`,`type_id`," +
                    "`create_time`) VALUES(?,?,?,?,?,?,?,"+"now()"+")";
            //编译sql语句
            ps = con.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setString(2, product.getImgHref());
            ps.setBigDecimal(3, product.getMarkedPrice());
            ps.setBigDecimal(4, product.getNormalPrice());
            ps.setInt(5, product.getIsShow());
            ps.setInt(6, product.getState());
            ps.setInt(7, product.getTypeId());
            //赋值count
            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            关闭连接
            JdbcUtil.closeRe(con, ps);
        }
        return count;
    }

    /**
     * 查询总条数
     *
     * @param product
     * @return
     */
    @Override
    public int getCount(Product product) {
        //        编写sql语句
        String sql = "select count(*) count from dress_product d join product_nav n on d.type_id=n.id WHERE d.`status`=0    ";
        if(product.getTypeId()!=-1){
            sql+=" and d.type_id=" + product.getTypeId() ;
        }
//        编译sql语句
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
     * @param product
     * @return
     */
    @Override
    public Product findById(Product product) {
        //        编写sql语句
        String sql ="select d.*,p.title,date_format(create_time,'%Y-%m-%d') temp," +
                " date_format(update_time,'%Y-%m-%d') time from";
                sql+="`dress_product` d inner join `product_nav`  p ON d.type_id = p.id where d.id="+product.getId();
        //        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
        //解析结果集
        return getList(rs).get(0);
    }

    /**
     * 更新
     * @param product
     * @return
     */
    @Override
    public int update(Product product) {
        //编译sql语句
        int count=0;
        //连接数据库
        try {
            con =  JdbcUtil.getConnection();
            //        编写sql语句
            String sql = "UPDATE `dress_product` SET " +
                    "`name`=?," +
                    " `img_href`=?, " +
                    "`marked_price`=?, " +
                    "`normal_price`=?, " +
                    "`state`=?," +
                    "`is_show`=?, " +
                    "`type_id`=?, " +
                    "`update_time`= "+"now() " +
                    "WHERE (`id`=?)";
            //编译sql语句
            ps=con.prepareStatement(sql);
            ps.setString(1,product.getName());
            ps.setString(2, product.getImgHref());
            ps.setBigDecimal(3, product.getMarkedPrice());
            ps.setBigDecimal(4, product.getNormalPrice());
            ps.setInt(5, product.getState());
            ps.setInt(6, product.getIsShow());
            ps.setInt(7, product.getTypeId());
            ps.setInt(8, product.getId());
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
     * 删除
     * @param 
     * @return
     */
    @Override
    public int deleteId(Product in) {
        //        编写sql
        String sql = "UPDATE `dress_product` SET `status`='"+in.getStatus()+"' WHERE id="+in.getId();
//        编译sql
        int count = JdbcUtil.update(sql);
        return count;
    }

    /**
     * 查询删除的id状态
     * @param product
     * @return
     */
    @Override
    public Product selectDele(Product product) {
        //        编写sql语句
        String sql ="SELECT * FROM `dress_product` WHERE id="+product.getId();
        //        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
        //解析结果集
        List<Product> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Product model = new Product();
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
     * 查询删除的总条数
     * @param product
     * @return
     */
    @Override
    public int getCountStatus(Product product) {
        //       查询数据库
        String sql = "select count(*) count from dress_product where status=0 ";
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
     * @param
     * @return
     */
    @Override
    public int updateState(Product in) {
        //        编写sql
        String sql = "UPDATE `dress_product` SET `state`='"+in.getState()+"' WHERE id="+in.getId();
//        编译sql
        int count = JdbcUtil.update(sql);
        return count;
    }
    /**
     * 修改置顶
     * @param product
     * @return
     */
    @Override
    public int updateIsShow(Product product) {
        //        编写sql
        String sql = "UPDATE `dress_product` SET `state`='"+product.getIsShow()+"' WHERE id="+product.getId();
//        编译sql
        int count = JdbcUtil.update(sql);
        return count;
    }

    /**
     * 展示产品
     * @return
     */
    @Override
    public List<Product> getProductList(Product model) {
        //查询热点产品
        String sqlHot = "SELECT p.*,c.title FROM dress_product p,product_nav c  where p.is_show=1  ";
        if(model.getTypeId()!=-1){
            sqlHot+=" and p.type_id=" + model.getTypeId() ;
        }
        sqlHot += " limit " + (model.getPageNo() - 1) * 2 + "," + 2;
        List<Product> listAll = new ArrayList<>();
        List<Product> listHot = getProductList(sqlHot, model);
        //将热点产品放入要返回的list
        for (int i = 0; i < listHot.size(); i++) {
            listAll.add(listHot.get(i));
        }
        //查询置顶的产品
        String sqlTop = "SELECT p.*,c.title FROM dress_product p,product_nav c  where p.is_recommend=1 ";
        if(model.getTypeId()!=-1){
            sqlTop+=" and p.type_id=" + model.getTypeId() ;
        }
        sqlTop += " limit " + (model.getPageNo() - 1) * 2 + "," + 2;
        List<Product> listTop = getProductList(sqlTop, model);
        //将置顶产品放入要返回的list
        for (int i = 0; i < listTop.size(); i++) {
            listAll.add(listTop.get(i));
        }
        //查询普通的产品 ，总条数应该为8条减去热点和新闻的条数
        String sqlNormal = "SELECT p.*,c.title FROM dress_product p INNER JOIN product_nav c ON p.type_id=c.id where  p.is_recommend!=1 and p.is_show!=1 and p.`status`=0 ";
        if(model.getTypeId()!=-1){
            sqlNormal+=" and p.type_id=" + model.getTypeId() ;
        }
        Integer pageSize=model.getPageSize()-listAll.size();
        sqlNormal += " limit " + (model.getPageNo() - 1) * pageSize + "," + pageSize;
        List<Product> listNormal = getProductList(sqlNormal, model);
        //将普通的放入list
        for (int i = 0; i < listNormal.size(); i++) {
            listAll.add(listNormal.get(i));
        }
        return listAll;
    }


    /**
     * 礼服
     * @return
     */
    @Override
    public List<Product> getRodetList() {
        //       查询数据库
        String sql = "select d.*,p.title,date_format(create_time,'%Y-%m-%d') temp," +
                "date_format(update_time,'%Y-%m-%d') time from `dress_product` d" +
                " inner join `product_nav`  p ON d.type_id = p.id ";
        sql +=" where type_id=2 and d.`status`=0";
//        连接数据库
        ResultSet rs = JdbcUtil.query(sql);
//        用list来接收数据并使用集合 储存数据
        return getList(rs);
    }

    /**
     * 西装
     * @return
     */
    @Override
    public List<Product> getBlazertList() {
        //       查询数据库
        String sql = "select d.*,p.title,date_format(create_time,'%Y-%m-%d') temp," +
                "date_format(update_time,'%Y-%m-%d') time from `dress_product` d" +
                " inner join `product_nav`  p ON d.type_id = p.id ";
        sql +=" where type_id=3 and d.`status`=0";
//        连接数据库
        ResultSet rs = JdbcUtil.query(sql);
//        用list来接收数据并使用集合 储存数据
        return getList(rs);
    }

    /**
     * 定制
     * @return
     */
    @Override
    public List<Product> getTailoredList() {
        //       查询数据库
        String sql = "select d.*,p.title,date_format(create_time,'%Y-%m-%d') temp," +
                "date_format(update_time,'%Y-%m-%d') time from `dress_product` d" +
                " inner join `product_nav`  p ON d.type_id = p.id ";
        sql +=" where type_id=4 and d.`status`=0";
//        连接数据库
        ResultSet rs = JdbcUtil.query(sql);
//        用list来接收数据并使用集合 储存数据
        return getList(rs);
    }

    /**
     * 婚纱
     * @return
     */
    @Override
    public List<Product> getBridalVeil() {
        //       查询数据库
        String sql = "select d.*,p.title,date_format(create_time,'%Y-%m-%d') temp," +
                "date_format(update_time,'%Y-%m-%d') time from `dress_product` d" +
                " inner join `product_nav`  p ON d.type_id = p.id ";
        sql +=" where type_id=1 and d.`status`=0";
//        连接数据库
        ResultSet rs = JdbcUtil.query(sql);
//        用list来接收数据并使用集合 储存数据
        return getList(rs);
    }

    /**
     * 以id查询
     * @param product
     * @return
     */
    @Override
    public  List<Product> findByIdDress(Product product) {
        String sql="(SELECT p.*,c.title,r.*  FROM dress_product AS p JOIN product_nav AS c JOIN product_parameter AS r ON p.parameter_id=r.id where p.type_id=c.id  and p.id=(select min(id) from dress_product where id>"+product.getId()+"))\n" +
                "union all\n" +
                "(SELECT p.*,c.title,r.*  FROM dress_product AS p JOIN product_nav AS c JOIN product_parameter AS r ON p.parameter_id=r.id where p.type_id=c.id  and p.id="+product.getId()+")\n" +
                "union all\n" +
                "(SELECT p.*,c.title,r.*  FROM dress_product AS p JOIN product_nav AS c JOIN product_parameter AS r ON p.parameter_id=r.id where p.type_id=c.id  and p.id=(select max(id) from dress_product where id<"+product.getId()+"))";
        ResultSet rs = JdbcUtil.query(sql);
        List<Product> list = new ArrayList<>();
        try {
            while (rs.next()){
                Product model = new Product();
                model.setId(rs.getInt("id"));
                model.setName(rs.getString("name"));
                model.setImgHref(rs.getString("img_href"));
                model.setMarkedPrice(rs.getBigDecimal("marked_price"));
                model.setNormalPrice(rs.getBigDecimal("normal_price"));
                model.setCreateTime(rs.getString("create_time"));
                model.setUpdateTime(rs.getString("update_time"));
                model.setTypeId(rs.getInt("type_id"));
                model.setIsRecommend(rs.getInt("is_recommend"));
                model.setParameterId(rs.getInt("parameter_id"));
                model.setFashionElement(rs.getString("fashion_element"));
                model.setStyle(rs.getString("style"));
                model.setModel(rs.getString("model"));
                model.setFashion(rs.getString("fashion"));
                model.setClothesLength(rs.getString("clothes_length"));
                model.setCharacter(rs.getString("character"));
                model.setIngredient(rs.getString("ingredient"));
                model.setTitle(rs.getString("title"));
                model.setState(rs.getInt("state"));
                model.setIsShow(rs.getInt("is_show"));
                model.setStatus(rs.getInt("status"));
                list.add(model);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 以id查询
     * @param product
     * @return
     */
    @Override
    public List<Product> getFindById(Product product) {
        //        编写sql语句
        String sql ="SELECT p.*,c.title,r.fashion_element,r.style,r.model,r.fashion,r.clothes_length,r.`character`,r.ingredient  FROM \n" +
                "dress_product AS p JOIN product_nav AS c \n" +
                "JOIN product_parameter AS r ON p.parameter_id=r.id where p.type_id=c.id  and p.id="+product.getId();
        //        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
        //解析结果集
        List<Product> list = new ArrayList<>();
        try {
            while (rs.next()){
                Product model = new Product();
                model.setId(rs.getInt("id"));
                model.setName(rs.getString("name"));
                model.setImgHref(rs.getString("img_href"));
                model.setMarkedPrice(rs.getBigDecimal("marked_price"));
                model.setNormalPrice(rs.getBigDecimal("normal_price"));
                model.setCreateTime(rs.getString("create_time"));
                model.setUpdateTime(rs.getString("update_time"));
                model.setTypeId(rs.getInt("type_id"));
                model.setIsRecommend(rs.getInt("is_recommend"));
                model.setParameterId(rs.getInt("parameter_id"));
                model.setFashionElement(rs.getString("fashion_element"));
                model.setStyle(rs.getString("style"));
                model.setModel(rs.getString("model"));
                model.setFashion(rs.getString("fashion"));
                model.setClothesLength(rs.getString("clothes_length"));
                model.setCharacter(rs.getString("character"));
                model.setIngredient(rs.getString("ingredient"));
                model.setTitle(rs.getString("title"));
                model.setState(rs.getInt("state"));
                model.setIsShow(rs.getInt("is_show"));
                model.setStatus(rs.getInt("status"));
                list.add(model);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询产品列表
     *
     * @param product
     * @return
     */
    @Override
    public List<Product> findAll(Product product) {
        //       查询数据库
        String sql = "select d.*,p.title,date_format(create_time,'%Y-%m-%d') temp," +
                "date_format(update_time,'%Y-%m-%d') time from `dress_product` d" +
                " inner join `product_nav`  p ON d.type_id = p.id ";
        sql +="where d.`status`=0";
        sql += " limit " + (product.getPageNo() + -1) * product.getPageSize() + "," + product.getPageSize();
//        连接数据库
        ResultSet rs = JdbcUtil.query(sql);
//        用list来接收数据并使用集合 储存数据
        return getList(rs);
    }

    /**
     * 封装list
     *
     * @param
     * @return
     */
    private List<Product> getProductList(String sql, Product model) {
        List<Product> list = new ArrayList<>();
        ResultSet rs = JdbcUtil.query(sql);
        try {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setImgHref(rs.getString("img_href"));
                product.setMarkedPrice(rs.getBigDecimal("marked_price"));
                product.setNormalPrice(rs.getBigDecimal("normal_price"));
                product.setCreateTime(rs.getString("create_time"));
                product.setUpdateTime(rs.getString("update_time"));
                product.setTypeId(rs.getInt("type_id"));
                product.setIsRecommend(rs.getInt("is_recommend"));
                product.setParameterId(rs.getInt("parameter_id"));
                product.setTitle(rs.getString("title"));
                product.setState(rs.getInt("state"));
                product.setIsShow(rs.getInt("is_show"));
                product.setStatus(rs.getInt("status"));
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        4.返回数据
        return list;
    }
    private List<Product> getList(ResultSet rs){
        List<Product> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setImgHref(rs.getString("img_href"));
                product.setMarkedPrice(rs.getBigDecimal("marked_price"));
                product.setNormalPrice(rs.getBigDecimal("normal_price"));
                product.setCreateTime(rs.getString("create_time"));
                product.setUpdateTime(rs.getString("update_time"));
                product.setTemp(rs.getString("temp"));
                product.setTime(rs.getString("time"));
                product.setTypeId(rs.getInt("type_id"));
                product.setTitle(rs.getString("title"));
                product.setState(rs.getInt("state"));
                product.setIsShow(rs.getInt("is_show"));
                product.setStatus(rs.getInt("status"));
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        4.返回数据
        return list;
    }

}
