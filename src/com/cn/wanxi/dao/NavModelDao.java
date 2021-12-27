package com.cn.wanxi.dao;

import com.cn.wanxi.model.product.NavModel;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NavModelDao.java
 * @Description TODO
 * @createTime 2021年12月15日 17:00:00
 */
public interface NavModelDao {
    /**
     * 查询所有
     * @return
     */
    List<NavModel> getProductNav();
}
