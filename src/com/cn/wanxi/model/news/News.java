package com.cn.wanxi.model.news;

/**新闻
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName News.java
 * @Description TODO
 * @createTime 2021年11月22日 15:36:00
 */
public class News extends NewsInfo {
    //    标题
    private String title;
    //    内容
    private String content;
    //    摘要
    private String nAbstract;
    //被选中的字段
    private String field;
    //修改的值
    private String value;
    //    热点
    private Integer isRecommend;
    //    是否置顶
    private Integer isShow;
    //    分类
    private Integer sortId;


    public String getnAbstract() {
        return nAbstract;
    }

    public void setnAbstract(String nAbstract) {
        this.nAbstract = nAbstract;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
