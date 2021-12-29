package com.cn.wanxi.service.product.impl;

import com.cn.wanxi.dao.product.ProductMessageDao;
import com.cn.wanxi.dao.product.impl.ProductMessageDaoImpl;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.product.ProductMessage;
import com.cn.wanxi.service.product.ProductMessageService;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName ProductMessageServiceImpl.java
 * @Description TODO
 * @createTime 2021年12月28日 16:26:00
 */
public class ProductMessageServiceImpl implements ProductMessageService {
    @Override
    public ResultModel getFindById(ProductMessage product) {

        ProductMessageDao productMessageDao = new ProductMessageDaoImpl();
        ProductMessage productMessage = productMessageDao.FindById(product);
        return ResultModel.getModel(productMessage);
    }
}
