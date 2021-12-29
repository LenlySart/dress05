package com.cn.wanxi.servlet.product;

import com.alibaba.fastjson.JSONObject;
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
 * @ClassName ProductCountServlet.java
 * @Description TODO
 * @createTime 2021年12月20日 09:20:00
 */
@WebServlet("/product/count")
public class ProductCountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        封装前端传过来的值
        String pageNo = req.getParameter("page");
        String pageSize = req.getParameter("limit");
        String typeId = req.getParameter("typeId");
//        封装数据
        Product product = new Product();
        product.setPageNo(Tool.stringToInt(pageNo));
        product.setPageSize(Tool.stringToInt(pageSize));
        product.setTypeId(Tool.stringToInt(typeId));

//        创建一个业务层
        ProductService productService = new ProductServiceImpl();
//        调用业务层查获取询数据
        ResultModel resultModel = productService.getCount(product);
//        返回数据到前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
