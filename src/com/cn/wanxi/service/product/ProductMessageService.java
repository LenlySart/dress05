package com.cn.wanxi.service.product;

import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.product.ProductMessage;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName ProductMessageService.java
 * @Description TODO
 * @createTime 2021年12月28日 16:26:00
 */
public interface ProductMessageService {
    /**
     * 以id查询
     * @param product
     * @return
     */
    ResultModel getFindById(ProductMessage product);
}
