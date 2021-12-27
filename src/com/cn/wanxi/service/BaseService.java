package com.cn.wanxi.service;


import com.cn.wanxi.enums.ResultModel;

public interface BaseService<T> {

    //    添加列表
    ResultModel add(T t);

    //    分页查询
    ResultModel getCount(T t);

    //    以id查询
    ResultModel findById(T t);

    //    修改
    ResultModel update(T t);

    //    删除
    ResultModel deleteId(T t);

    //    查询没有被删除的页数
    ResultModel getStatusCount(T t);

    //    查询状态
    ResultModel getState(T t);

    //    查询
    ResultModel getfindAll(T t);
}
