package com.cn.wanxi.model.dress;

import com.cn.wanxi.model.BaseModel;

/** 卖家信息
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName SellerMessage.java
 * @Description TODO
 * @createTime 2021年11月26日 14:04:00
 */
public class SellerMessage extends BaseModel {
//    卖家秀
    private String seller;
//    卖家留言
    private String femaleMessage;
    private String manMessage;

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getFemaleMessage() {
        return femaleMessage;
    }

    public void setFemaleMessage(String femaleMessage) {
        this.femaleMessage = femaleMessage;
    }

    public String getManMessage() {
        return manMessage;
    }

    public void setManMessage(String manMessage) {
        this.manMessage = manMessage;
    }
}
