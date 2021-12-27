package com.cn.wanxi.model.product;

import com.cn.wanxi.model.BaseModel;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName TypeModel.java
 * @Description TODO
 * @createTime 2021年12月11日 13:56:00
 */
public class TypeModel extends BaseModel {
    private String title;
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
