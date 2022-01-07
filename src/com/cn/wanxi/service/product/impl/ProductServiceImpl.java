package com.cn.wanxi.service.product.impl;

import com.cn.wanxi.dao.product.ProductDao;
import com.cn.wanxi.dao.product.impl.ProductDaoImpl;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.company.Company;
import com.cn.wanxi.model.product.Product;
import com.cn.wanxi.service.product.ProductService;
import com.cn.wanxi.util.Redis;
import com.cn.wanxi.util.Tool;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

import static com.cn.wanxi.util.Redis.*;

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
     *
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
     * 首页产品展示
     *
     * @return
     */
    @Override
    public ResultModel getProductList(Product product) {
        ProductDao productDao = new ProductDaoImpl();
        int count = productDao.getCount(product);
        //创建Redis对象
        Jedis jedis = new Jedis();
        //创建一个list集合
        List<Product> productList = new ArrayList<>();
//        String key = "product";
        int index = 2;
        //定义一个Integer类型的字段接收前端传过来的limit
        Integer limit = product.getPageNo();
        //判断Redis中是否有值，如果有从Redis取，没有从数据库中取
        if (limit < 2) {
            if (product.getTypeId() > 0) {
                productList = productDao.getProductList(product);
                return ResultModel.getModel(count, productList);
            }

            //定义一个Boolean类型的字段接收Redis中的某个值用于判断
            //查询Redis第三个数据库
            jedis.select(index);
            boolean isHave = jedis.exists("productName");
//            boolean isHave = jedis.exists(key);
            if (!isHave) {
                productList = productDao.getProductList(product);
                for (Product model :
                        productList) {
                    jedis.rpush("productName", model.getName());
                    jedis.rpush("productImgHref", model.getImgHref());
                    jedis.rpush("productMarkedPrice", (model.getMarkedPrice()).toString());
                    jedis.rpush("productNormalPrice", (model.getNormalPrice()).toString());
                    jedis.rpush("productTitle", model.getTitle());
                }
                return ResultModel.getModel(count, productList);
            }
            List<String> productName = jedis.lrange("productName", 0, -1);
            List<String> productImgHref = jedis.lrange("productImgHref", 0, -1);
            List<String> productMarkedPrice = jedis.lrange("productMarkedPrice", 0, -1);
            List<String> productNormalPrice = jedis.lrange("productNormalPrice", 0, -1);
            List<String> productTitle = jedis.lrange("productTitle", 0, -1);
            int length = productName.size();
            for (int i = 0; i < length; i++) {
                Product model = new Product();
                model.setName(productName.get(i));
                model.setImgHref(productImgHref.get(i));
                model.setMarkedPrice(Tool.bigDecimal(productMarkedPrice.get(i)));
                model.setNormalPrice(Tool.bigDecimal(productNormalPrice.get(i)));
                model.setTitle(productTitle.get(i));
                productList.add(model);
            }
        } else {
            productList = productDao.getProductList(product);
        }
        return ResultModel.getModel(count, productList);
    }

    private void Redis(int count) {
        if (count > 0) {
            Jedis jedis = new Jedis();
            jedis.select(3);
            jedis.del("newsTitle", "newsContent", "newsNAbstract", "newsCreateTime");
        }
    }

    /**
     * 以id查询
     *
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
     *
     * @param product
     * @return
     */
    @Override
    public ResultModel add(Product product) {
        //        创建model对象
        ProductDao productDao = new ProductDaoImpl();
        //        获取dao层查询的数据
        int count = productDao.add(product);
        Redis(count);
        return ResultModel.getModel(count);
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
     *
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
     *
     * @param product
     * @return
     */
    @Override
    public ResultModel update(Product product) {
        //        创建model对象
        ProductDao productDao = new ProductDaoImpl();
        int count = productDao.update(product);
        Redis(count);
        return ResultModel.getModel(count);
    }

    /**
     * 删除
     *
     * @param product
     * @return
     */
    @Override
    public ResultModel deleteId(Product product) {
        //        创建model对象
        ProductDao productDao = new ProductDaoImpl();
        Product model = productDao.selectDele(product);
        if (model.getStatus() != 0) {
            return ResultModel.notLogin();
        }
        int id = model.getId();
        Product in = new Product();
        in.setStatus(1);
        in.setId(id);
        int count = productDao.deleteId(in);
        Redis(count);
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
     *
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
     *
     * @param product
     * @return
     */
    @Override
    public ResultModel getfindAll(Product product) {
        ProductDao productDao = new ProductDaoImpl();
        // 查询总条数
        int count = productDao.getCountStatus(product);
        // 获取dao层查询的数据
        List<Product> list = productDao.findAll(product);
        return ResultModel.getModel(count, list);
    }
}
