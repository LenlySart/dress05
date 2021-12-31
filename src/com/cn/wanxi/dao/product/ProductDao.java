package com.cn.wanxi.dao.product;


import com.cn.wanxi.dao.BaseDaoImpl;
import com.cn.wanxi.model.product.Product;

import java.util.List;


/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName ProductDao.java
 * @Description TODO
 * @createTime 2021年11月25日 17:44:00
 */
public interface ProductDao extends BaseDaoImpl<Product> {
    /**
     * 修改置顶
     * @param product
     * @return
     */
    int updateIsShow(Product product);

    /**
     * 展示产品
     * @return
     */
    List<Product> getProductList(Product product);


    /**
     * 以id查询
     * @param product
     * @return
     */
    List<Product> findByIdDress(Product product);

    /**
     * 以id查询
     * @param product
     * @return
     */
    List<Product> getFindById(Product product);
}
