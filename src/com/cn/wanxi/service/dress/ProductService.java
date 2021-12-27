package com.cn.wanxi.service.dress;

import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.product.Product;
import com.cn.wanxi.service.BaseService;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName ProductService.java
 * @Description TODO
 * @createTime 2021年12月09日 11:29:00
 */
public interface ProductService extends BaseService<Product> {


    /**
     * 修改置顶
     * @param product
     * @return
     */
    ResultModel getIsShow(Product product);

    /**
     * 产品展示
     * @return
     */
    ResultModel getProductList(Product product);

    /**
     * 礼服
     * @return
     */
    List<Product> getRodetList();
    /**
     * 西装
     * @return
     */
    List<Product> getBlazertList();
    /**
     * 定制
     * @return
     */
    List<Product> getTailoredList();
    /**
     * 婚纱
     * @return
     */
    List<Product> getBridalVeil();

    /**
     * 以id查询
     * @param product
     * @return
     */
    ResultModel findByIdDress(Product product);
    /**
     * 以id查询
     * @param product
     * @return
     */
    ResultModel getfindById(Product product);
}
