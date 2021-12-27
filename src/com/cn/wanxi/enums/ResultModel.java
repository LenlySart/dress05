package com.cn.wanxi.enums;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName ResultModel.java
 * @Description TODO
 * @createTime 2021年11月30日 17:39:00
 */
public class ResultModel {

    private String msg;
    private Integer code = 0;
    private Integer count;
    private Object data;
    private static ResultModel resultModel;

    public ResultModel() {
    }

    private static ResultModel getSingtenModel(Integer code, String msg, Integer count, Object data) {
        if (resultModel == null) {
            resultModel = new ResultModel();
        }
        resultModel.setData(data);
        resultModel.setCount(count);
        resultModel.setCode(code);
        resultModel.setMsg(msg);
        return resultModel;
    }
    public static ResultModel getModel(Integer count, Object data) {
        if (count>0){
            return getSingtenModel(0, "success", count, data);
        }else {
            return getSingtenModel(0, "error", count, data);
        }

    }

    public static ResultModel getModel(Integer count) {
        if (count>0){
            return getSingtenModel(0, "success", count, null);
        }else {
            return getSingtenModel(0, "error", count, null);
        }

    }

    public static ResultModel getModel(Object data) {
        if (data!=null){
            return getSingtenModel(0, "success", 1, data);
        }else {
            return getSingtenModel(0, "error", 1, data);
        }
    }

    public static ResultModel noLogin() {
        return getSingtenModel(ResultTool.NO_LOGIN.getCode(), ResultTool.NO_LOGIN.getMsg(), 1, null);
    }
    public static ResultModel inLogin() {
        return getSingtenModel(ResultTool.SUCCESS.getCode(), ResultTool.SUCCESS.getMsg(), 1, null);
    }
    public static ResultModel notLogin() {
        return getSingtenModel(ResultTool.ERROR.getCode(), ResultTool.ERROR.getMsg(), 1, null);
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
