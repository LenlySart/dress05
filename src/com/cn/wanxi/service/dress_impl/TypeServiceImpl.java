package com.cn.wanxi.service.dress_impl;

import com.cn.wanxi.dao.dress.TypeModeltDao;
import com.cn.wanxi.dao.impl.TypeModelDaoImpl;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.product.TypeModel;
import com.cn.wanxi.service.dress.TypeService;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName TypeServiceImpl.java
 * @Description TODO
 * @createTime 2021年12月11日 13:54:00
 */
public class TypeServiceImpl implements TypeService {
    /**
     * 查询所有
     * @param
     * @return
     */
    @Override
    public ResultModel getfindAll(TypeModel typeModel) {
        TypeModeltDao typeModeltDao = new TypeModelDaoImpl();
//        获取dao层查询的数据
        List<TypeModel> list = typeModeltDao.findAll(typeModel);
        return  ResultModel.getModel(list);
    }
}
