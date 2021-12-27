package com.cn.wanxi.model.product;

import com.cn.wanxi.model.BaseModel;

/** 产品展示导航
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NavModel.java
 * @Description TODO
 * @createTime 2021年11月22日 20:12:00
 */
public class NavModel extends BaseModel {
    //    导航名称
    private String title;
    //    导航跳转链接
    private String href;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}


