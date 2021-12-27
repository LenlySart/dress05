package com.cn.wanxi.service.impl;

import com.cn.wanxi.dao.dress.ProductDao;
import com.cn.wanxi.dao.impl.ProductDaoImpl;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.product.Product;
import com.cn.wanxi.service.dress.ProductService;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName ProductServiceImpl.java
 * @Description TODO
 * @createTime 2021年12月09日 11:29:00
 */
public class ProductServiceImpl implements ProductService {



    /**
     * 修改置顶
     * @param product
     * @return
     */
    @Override
    public ResultModel getIsShow(Product product) {
        //        创建model对象
        ProductDao productDao = new ProductDaoImpl();
        int count = productDao.updateIsShow(product);
        return ResultModel.getModel(count);
    }

    /**
     * 产品展示
     * @return
     */
    @Override
    public ResultModel getProductList(Product product) {
        ProductDao productDao = new ProductDaoImpl();
        int count =productDao.getCountStatus(product);
        List<Product> list = productDao.getProductList(product);
        return ResultModel.getModel(count,list);
    }

    /**
     * 礼服
     * @return
     */
    @Override
    public List<Product> getRodetList() {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.getRodetList();
    }

    /**
     * 西装
     * @return
     */
    @Override
    public List<Product> getBlazertList() {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.getBlazertList();
    }

    /**
     * 定制
     * @return
     */
    @Override
    public List<Product> getTailoredList() {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.getTailoredList();
    }

    /**
     * 婚纱
     * @return
     */
    @Override
    public List<Product> getBridalVeil() {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.getBridalVeil();
    }

    /**
     * 以id查询
     * @param product
     * @return
     */
    @Override
    public ResultModel findByIdDress(Product product) {
        //        创建model对象
        ProductDao productDao = new ProductDaoImpl();
       List<Product> list = productDao.findByIdDress(product);
        return ResultModel.getModel(list);
    }

    @Override
    public ResultModel getfindById(Product product) {
        //        创建model对象
        ProductDao productDao = new ProductDaoImpl();
        List<Product> list = productDao.getFindById(product);
        return ResultModel.getModel(list);
    }


    /**
     * 添加
     * @param product
     * @return
     */
    @Override
    public ResultModel add(Product product) {
        String msg="";
        //        创建model对象
        ProductDao productDao = new ProductDaoImpl();
        //        获取dao层查询的数据
        int count = productDao.add(product);
        if (count>0){
            msg="success";
            return ResultModel.getModel(msg);
        }else {
            //不为空且那么表示成功
            msg = "error";
            return ResultModel.getModel(msg);
        }
    }

    @Override
    public ResultModel getCount(Product product) {
        //        创建model对象
        ProductDao productDao = new ProductDaoImpl();
        int count = productDao.getCount(product);
        return ResultModel.getModel(count);
    }

    /**
     * 根据id查询所有
     * @param product
     * @return
     */
    @Override
    public ResultModel findById(Product product) {
        //        创建model对象
        ProductDao productDao = new ProductDaoImpl();
        Product model = productDao.findById(product);
        return ResultModel.getModel(model);
    }

    /**
     * 更新
     * @param product
     * @return
     */
    @Override
    public ResultModel update(Product product) {
        //        创建model对象
        ProductDao productDao = new ProductDaoImpl();
        int count = productDao.update(product);
        return ResultModel.getModel(count);
    }

    /**
     * 删除
     * @param product
     * @return
     */
    @Override
    public ResultModel deleteId(Product product) {
        //        创建model对象
        ProductDao productDao = new ProductDaoImpl();
        Product model = productDao.selectDele(product);
        if (model.getStatus()!=0){
            return ResultModel.notLogin();
        }
        int id = model.getId();
        Product in = new Product();
        in.setStatus(1);
        in.setId(id);
        int count = productDao.deleteId(in);
        return ResultModel.getModel(count);
    }

    @Override
    public ResultModel getStatusCount(Product product) {
        //        创建model对象
        ProductDao productDao = new ProductDaoImpl();
        int count = productDao.getCountStatus(product);
        return ResultModel.getModel(count);
    }

    /**
     * 修改状态
     * @param product
     * @return
     */
    @Override
    public ResultModel getState(Product product) {
        //        创建model对象
        ProductDao productDao = new ProductDaoImpl();
        int count = productDao.updateState(product);
        return ResultModel.getModel(count);
    }

    /**
     * 查询产品列表
     * @param product
     * @return
     */
    @Override
    public ResultModel getfindAll(Product product) {
        ProductDao productDao = new ProductDaoImpl();
//        获取dao层查询的数据
        //        查询总条数
        int count = productDao.getCountStatus(product);
        List<Product> list = productDao.findAll(product);
        return  ResultModel.getModel(count,list);
    }
}
