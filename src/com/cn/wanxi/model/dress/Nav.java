package com.cn.wanxi.model.dress;

import com.cn.wanxi.model.BaseModel;

/**
 * @title 导航
 * @description 
 * @author admin  Dshzs月
 * @updateTime 2021/11/22 15:33 
 * @throws 
 */
public class Nav extends BaseModel {
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
