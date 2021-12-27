package com.cn.wanxi.model.news;

import com.cn.wanxi.model.BaseModel;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NewsInfo.java
 * @Description TODO
 * @createTime 2021年12月23日 09:38:00
 */
public class NewsInfo extends BaseModel {

//    作者
    private String author;
//    浏览量
    private String pageView;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPageView() {
        return pageView;
    }

    public void setPageView(String pageView) {
        this.pageView = pageView;
    }
}
