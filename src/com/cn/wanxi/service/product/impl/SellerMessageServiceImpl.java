package com.cn.wanxi.service.product.impl;

import com.cn.wanxi.dao.product.SellerMessageDao;
import com.cn.wanxi.dao.product.impl.SellerMessageDaoImpl;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.dress.SellerMessage;
import com.cn.wanxi.service.product.SellerMessageService;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName SellerMessageServiceImpl.java
 * @Description TODO
 * @createTime 2021年12月13日 15:38:00
 */
public class SellerMessageServiceImpl implements SellerMessageService {
    /**
     * 查询所有
     * @param sellerMessage
     * @return
     */
    @Override
    public ResultModel getfindAll(SellerMessage sellerMessage) {
        SellerMessageDao sellerMessageDao = new SellerMessageDaoImpl();
        int count = sellerMessageDao.getCountStatus(sellerMessage);
        List<SellerMessage> list = sellerMessageDao.findAll(sellerMessage);
        return  ResultModel.getModel(count,list);
    }

    @Override
    public List<SellerMessage> getproductFeature() {
        SellerMessageDao sellerMessageDao = new SellerMessageDaoImpl();
        return sellerMessageDao.getproductFeature();
    }
}
