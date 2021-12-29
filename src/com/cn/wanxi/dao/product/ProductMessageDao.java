package com.cn.wanxi.dao.product;

import com.cn.wanxi.model.product.ProductMessage;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName ProductMessageDao.java
 * @Description TODO
 * @createTime 2021年12月28日 16:29:00
 */
public interface ProductMessageDao {
    ProductMessage FindById(ProductMessage product);
}
