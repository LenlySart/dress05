package com.cn.wanxi.model.company;

import com.cn.wanxi.model.BaseModel;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName ComanyStore.java
 * @Description TODO
 * @createTime 2021年12月16日 19:46:00
 */
public class CompanyStore extends BaseModel {

//    线下门店
    private String offlineStore;
//    线下门店图片
    private String storeImage;
//    是否展示
    private Integer isShow;

    public String getOfflineStore() {
        return offlineStore;
    }

    public void setOfflineStore(String offlineStore) {
        this.offlineStore = offlineStore;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}
