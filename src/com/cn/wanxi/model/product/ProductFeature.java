package com.cn.wanxi.model.product;

import com.cn.wanxi.model.BaseModel;

/**
 * @author Dshzs月
 * @version 1.0.0
 * 定制展示
 *
 * @ClassName ProductFeature.java
 * @Description TODO
 * @createTime 2021年12月15日 19:26:00
 */
public class ProductFeature extends BaseModel {
//    定制的名称
    private String name;
//    定制的展示图片
    private String imgHref;
//    定制的介绍
    private String pDescribe;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgHref() {
        return imgHref;
    }

    public void setImgHref(String imgHref) {
        this.imgHref = imgHref;
    }

    public String getpDescribe() {
        return pDescribe;
    }

    public void setpDescribe(String pDescribe) {
        this.pDescribe = pDescribe;
    }
}
