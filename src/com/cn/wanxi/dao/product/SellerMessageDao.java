package com.cn.wanxi.dao.product;

import com.cn.wanxi.model.dress.SellerMessage;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName SellerMessageDao.java
 * @Description TODO
 * @createTime 2021年12月13日 15:41:00
 */
public interface SellerMessageDao {
    /**
     * 查询总条数
     * @param sellerMessage
     * @return
     */
    int getCountStatus(SellerMessage sellerMessage);

    /**
     * * 查询所有
     * @param sellerMessage
     * @return
     */
    List<SellerMessage> findAll(SellerMessage sellerMessage);

    List<SellerMessage> getproductFeature();
}
