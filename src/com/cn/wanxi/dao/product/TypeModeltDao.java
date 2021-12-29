package com.cn.wanxi.dao.product;

import com.cn.wanxi.model.product.TypeModel;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName TypeModeltDao.java
 * @Description TODO
 * @createTime 2021年12月11日 13:59:00
 */
public interface TypeModeltDao {

    /**
     * 查询所有
      * @param typeModel
     * @return
     */
    List<TypeModel> findAll(TypeModel typeModel);
}
