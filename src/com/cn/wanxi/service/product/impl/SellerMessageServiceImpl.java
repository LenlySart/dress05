package com.cn.wanxi.service.product.impl;

import com.cn.wanxi.dao.product.SellerMessageDao;
import com.cn.wanxi.dao.product.impl.SellerMessageDaoImpl;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.dress.SellerMessage;
import com.cn.wanxi.service.product.SellerMessageService;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
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
     *
     * @param sellerMessage
     * @return
     */
    @Override
    public ResultModel getfindAll(SellerMessage sellerMessage) {
        SellerMessageDao sellerMessageDao = new SellerMessageDaoImpl();
        int count = sellerMessageDao.getCountStatus(sellerMessage);
        List<SellerMessage> list = sellerMessageDao.findAll(sellerMessage);
        return ResultModel.getModel(count, list);
    }

    /**
     * 卖家秀展示首页
     *
     * @return
     */
    @Override
    public List<SellerMessage> getproductFeature() {

        SellerMessageDao sellerMessageDao = new SellerMessageDaoImpl();
        List<SellerMessage> sellerMessageList = new ArrayList<>();
//        获取redis对象
        Jedis jedis = new Jedis();
//        查询Redis第二个数据库
        jedis.select(1);
//        定义一个Boolean类型的字段接收Redis中的某个值用于判断
        boolean isHave = jedis.exists("seller");
//        判断Redis中是否有值，如果有从Redis取，没有从数据库中取
        if (isHave) {
            List<String> seller = jedis.lrange("seller", 0, -1);
            List<String> manMessage = jedis.lrange("manMessage", 0, -1);
            List<String> femaleMessage = jedis.lrange("femaleMessage", 0, -1);
            int length = seller.size();
            for (int i = 0; i < length; i++) {
                SellerMessage sellerMessage = new SellerMessage();
                sellerMessage.setSeller(seller.get(i));
                sellerMessage.setManMessage(manMessage.get(i));
                sellerMessage.setFemaleMessage(femaleMessage.get(i));
                sellerMessageList.add(sellerMessage);
            }

        } else {
            sellerMessageList = sellerMessageDao.getproductFeature();
            for (SellerMessage model :
                    sellerMessageList) {
//            添加数据到底二个数据库
                jedis.select(1);
                jedis.rpush("seller", model.getSeller());
                jedis.rpush("manMessage", model.getManMessage());
                jedis.rpush("femaleMessage", model.getFemaleMessage());
            }
        }
        return sellerMessageList;
    }
}
