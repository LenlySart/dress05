package com.cn.wanxi.model.product;

import com.cn.wanxi.model.BaseModel;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName ProductMessage.java
 * @Description TODO
 * @createTime 2021年12月28日 16:23:00
 */
public class ProductMessage extends BaseModel {

    //    卖家秀
    private String seller;

    //    男-买家留言
    private String manMessage;

    //    女-买家留言
    private String femaleMessage;

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getManMessage() {
        return manMessage;
    }

    public void setManMessage(String manMessage) {
        this.manMessage = manMessage;
    }

    public String getFemaleMessage() {
        return femaleMessage;
    }

    public void setFemaleMessage(String femaleMessage) {
        this.femaleMessage = femaleMessage;
    }
}
