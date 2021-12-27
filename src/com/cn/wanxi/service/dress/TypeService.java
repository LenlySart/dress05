package com.cn.wanxi.service.dress;

import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.product.TypeModel;

/**
 * 产品类型
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName TypeService.java
 * @Description TODO
 * @createTime 2021年12月11日 13:52:00
 */
public interface TypeService {
    /**
     * 查询所有
     * @param
     * @return
     */
    ResultModel getfindAll(TypeModel typeModel);
}
