package com.cn.wanxi.model;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName BaseModel.java
 * @Description TODO
 * @createTime 2021年12月10日 16:22:00
 */
public class BaseModel extends PageBase{
    private Integer id;
    //    创建时间
    private String createTime;
    //    修改时间
    private String updateTime;
    //    状态
    private Integer state;
    private String remark;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
