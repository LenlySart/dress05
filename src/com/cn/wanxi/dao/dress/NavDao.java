package com.cn.wanxi.dao.dress;

import com.cn.wanxi.model.dress.Nav;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NavDao.java
 * @Description TODO
 * @createTime 2021年11月22日 17:55:00
 */
public interface NavDao {

    /**
     * 查询所有
     * @param
     * @return
     */
    List<Nav> findAll();
}
