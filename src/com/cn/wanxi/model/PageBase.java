package com.cn.wanxi.model;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName PageBend.java
 * @Description TODO
 * @createTime 2021年12月03日 15:57:00
 */
public class PageBase {
//    第一个页面
    private  Integer pageNo;
//    一个页面多少条
    private  Integer pageSize;

    public  Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo( Integer pageNo) {
        this.pageNo = pageNo;
    }

    public  Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize( Integer pageSize) {
        this.pageSize = pageSize;
    }
}
