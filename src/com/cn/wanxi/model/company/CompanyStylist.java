package com.cn.wanxi.model.company;

/**
 * @author Dshzs月
 * @version 1.0.0
 * 设计师
 * @ClassName CompanyStylist.java
 * @Description TODO
 * @createTime 2021年12月16日 19:01:00
 */
public class CompanyStylist {
    private Integer id;
//    设计师名字
    private String stylist;
//    设计师简介
    private String aboutDesigner;
//    设计师帅照
    private String headShot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStylist() {
        return stylist;
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

    public String getHeadShot() {
        return headShot;
    }

    public void setHeadShot(String headShot) {
        this.headShot = headShot;
    }
}
