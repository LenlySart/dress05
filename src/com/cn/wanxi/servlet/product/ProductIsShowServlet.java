package com.cn.wanxi.servlet.product;

import com.alibaba.fastjson.JSON;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.product.Product;
import com.cn.wanxi.service.product.ProductService;
import com.cn.wanxi.service.product.impl.ProductServiceImpl;
import com.cn.wanxi.util.Tool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName ProductIsShowServlet.java
 * @Description TODO
 * @createTime 2021年12月13日 15:04:00
 */
@WebServlet("/back/product/isShow")
public class ProductIsShowServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        2.要得到前端的数据
        String id = req.getParameter("id");
        String isShow = req.getParameter("isShow");
        //        3.封装model
        Product product = new Product();
        product.setId(Tool.stringToInt(id));
        product.setIsShow(Tool.stringToInt(isShow));
//        4.调用服务逻辑层
        //        创建一个业务层
        ProductService productService = new ProductServiceImpl();
//        调用业务层查获取询数据
        ResultModel resultModel = productService.getIsShow(product);
        //       6.将数据返回给前端页面
        resp.getWriter().println(JSON.toJSONString(resultModel));
    }
}
