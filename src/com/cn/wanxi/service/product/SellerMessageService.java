package com.cn.wanxi.service.product;

import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.dress.SellerMessage;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName SellerMessageService.java
 * @Description TODO
 * @createTime 2021年12月13日 15:37:00
 */
public interface SellerMessageService {
    /**
     * 查询所有
     * @param sellerMessage
     * @return
     */
    ResultModel getfindAll(SellerMessage sellerMessage);
    /**
     * 首页查询所有
     * @param
     * @return
     */
    List<SellerMessage> getproductFeature();
}
