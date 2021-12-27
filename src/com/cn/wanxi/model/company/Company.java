package com.cn.wanxi.model.company;


import com.cn.wanxi.model.BaseModel;

/** 公司信息
 * @title
 * @description
 * @author admin  Dshzs月
 * @updateTime 2021/11/21 15:45
 * @throws
 */
public class Company extends BaseModel {

    private String name;
    //    电话
    private String phone;
    //网址
    private String net;
    //电子邮件
    private String email;
    //QQ
    private String qq;
    //地址
    private String address;

    private String version;
    //公司简介
    private String proFile;
    //公司展示图片
    private String imgHref;
    //设计师图片
    private String headShot;
    //公司人员-设计师
    private String stylist;
    //设计师资料
    private String aboutDesigner;
    //线下门店地址
    private String offlineStore;
    //线下门店图片
    private String storeImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public String getOfflineStore() {
        return offlineStore;
    }

    public void setOfflineStore(String offlineStore) {
        this.offlineStore = offlineStore;
    }

    public String getStylist() {
        return stylist;
    }

    public String getHeadShot() {
        return headShot;
    }

    public void setHeadShot(String headShot) {
        this.headShot = headShot;
    }

    public void setStylist(String stylist) {
        this.stylist = stylist;
    }

    public String getAboutDesigner() {
        return aboutDesigner;
    }

    public void setAboutDesigner(String aboutDesigner) {
        this.aboutDesigner = aboutDesigner;
    }

    public String getImgHref() {
        return imgHref;
    }

    public void setImgHref(String imgHref) {
        this.imgHref = imgHref;
    }

    public String getProFile() {
        return proFile;
    }

    public void setProFile(String proFile) {
        this.proFile = proFile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
