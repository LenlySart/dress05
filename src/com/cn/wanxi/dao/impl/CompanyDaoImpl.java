package com.cn.wanxi.dao.impl;

import com.cn.wanxi.dao.dress.CompanyDao;
import com.cn.wanxi.model.company.Company;
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
 * @ClassName CompanyDaoImpl.java
 * @Description TODO
 * @createTime 2021年12月13日 16:36:00
 */
public class CompanyDaoImpl implements CompanyDao {

    private static Connection con = null;
    private static PreparedStatement ps = null;

    /**
     * 查询所有
     *
     * @param company
     * @return
     */
    @Override
    public List<Company> findAll(Company company) {
        //       查询数据库
        String sql = "select * from `dress_company` ";
        sql += "where `status`=0";
//        连接数据库
        ResultSet rs = JdbcUtil.query(sql);
//        用list来接收数据并使用集合 储存数据
        return getList(rs);
    }

    @Override
    public int add(Company company) {
        return 0;
    }

    @Override
    public int getCount(Company company) {
        return 0;
    }

    /**
     * 以id查询
     *
     * @param company
     * @return
     */
    @Override
    public Company findById(Company company) {
        //        编写sql语句
        String sql = "select * from `dress_company` where id=" + company.getId();
        //        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
        //解析结果集
        return getList(rs).get(0);
    }

    /**
     * 更新
     *
     * @param company
     * @return
     */
    @Override
    public int update(Company company) {
        //        编写sql语句
        String sql = "UPDATE `dress_company` SET " +
                "`name`=?,";
                if(company.getImgHref()!=null && !company.getImgHref().equals("")){
                   sql += "`img_href`=?, ";
                }
                sql +="`phone`=?, " +
                "`email`=?, " +
                "`state`=?," +
                "`address`=?, " +
                "`qq`=?, " +
                "`pro_file`=?, " +
                "`update_time`= " + "now() " +
                "WHERE (`id`=?)";
        //编译sql语句
        List list = new ArrayList<>();
        list.add(company.getName());
        if(company.getImgHref()!=null && !company.getImgHref().equals("")){
            list.add(company.getImgHref());
        }
        list.add(company.getPhone());
        list.add(company.getEmail());
        list.add(company.getState());
        list.add(company.getAddress());
        list.add(company.getQq());
        list.add(company.getProFile());
        list.add(company.getId());
        return JdbcUtil.updatea(sql,list);
    }

    @Override
    public int deleteId(Company company) {
        return 0;
    }

    @Override
    public Company selectDele(Company company) {
        return null;
    }

    @Override
    public int getCountStatus(Company company) {
        return 0;
    }

    @Override
    public int updateState(Company company) {
        return 0;
    }

    /**
     * 封装list
     *
     * @param rs
     * @return
     */
    private List<Company> getList(ResultSet rs) {
        List<Company> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Company company = new Company();
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                company.setImgHref(rs.getString("img_href"));
                company.setPhone(rs.getString("phone"));
                company.setEmail(rs.getString("email"));
                company.setCreateTime(rs.getString("create_time"));
                company.setUpdateTime(rs.getString("update_time"));
                company.setQq(rs.getString("qq"));
                company.setAddress(rs.getString("address"));
                company.setProFile(rs.getString("pro_file"));
                company.setState(rs.getInt("state"));
                company.setStatus(rs.getInt("status"));
                list.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        4.返回数据
        return list;
    }

    /**
     * 公司信息展示
     * @return
     */
    @Override
    public Company getCompanyList() {
        //        编写sql语句
        String sql = "select * from `dress_company`";
        //        编译sql语句
        ResultSet rs = JdbcUtil.query(sql);
        //解析结果集
        return getList(rs).get(0);
    }
}
