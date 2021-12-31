package com.cn.wanxi.service.dress;

import com.cn.wanxi.model.dress.Nav;
import com.cn.wanxi.model.product.NavModel;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NavService.java
 * @Description TODO
 * @createTime 2021年11月22日 17:54:00
 */
public interface NavService {

     /**
      * 查询所有
      * @param
      * @return
      */
     List<Nav> findAll();

}
