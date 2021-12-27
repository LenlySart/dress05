package com.cn.wanxi.dao;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName BaseDaoImpl.java
 * @Description TODO
 * @createTime 2021年12月11日 09:13:00
 */

public interface BaseDaoImpl<T> {

    /**
     * 查询所有表
     * @param
     * @return
     */
    List<T> findAll(T t);

    /**
     * 插入表
     * @param 
     * @return
     */
    int add(T t);

    /**
     * 查询总条数
     * @return
     */
    int getCount(T t);

    /**
     * 以id查询
     * @param 
     * @return
     */
    T findById(T t);

    /**
     * 修改用户
     * @param 
     * @return
     */
    int update(T t);


    /**
     * 删除
     * @param
     * @return
     */
    int deleteId(T t);

    /**
     * 查询删除状态
     * @param 
     * @return
     */
    T selectDele(T t);



    /**
     * 查询没有被删除
     * @param 
     * @return
     */
    int getCountStatus(T t);

    /**
     * 修改状态
     * @param
     * @return
     */
    int updateState(T t);

}
