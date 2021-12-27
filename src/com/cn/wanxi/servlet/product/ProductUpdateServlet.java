package com.cn.wanxi.servlet.product;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.product.Product;
import com.cn.wanxi.service.dress.ProductService;
import com.cn.wanxi.service.impl.ProductServiceImpl;
import com.cn.wanxi.util.Tool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName ProductUpdateServlet.java
 * @Description TODO
 * @createTime 2021年12月13日 09:25:00
 */
@WebServlet("/back/product/update")
public class ProductUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        封装前端传过来的值
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String imgHref = req.getParameter("imgHref");
        String markedPrice = req.getParameter("markedPrice");
        String normalPrice = req.getParameter("normalPrice");
        String typeId = req.getParameter("title");
        String isShow = req.getParameter("isShow");
        String state = req.getParameter("state");
//        封装数据
        Product product = new Product();
        product.setId(Tool.stringToInt(id));
        product.setName(name);
        product.setImgHref(imgHref);
        product.setTypeId(Tool.stringToInt(typeId));
        try {
            product.setMarkedPrice(new BigDecimal(markedPrice));
            product.setNormalPrice(new BigDecimal(normalPrice));
        }catch (Exception e){
            e.printStackTrace();
        }

        product.setIsShow(Tool.stringToInt(isShow));
        product.setState(Tool.stringToInt(state));


//        创建一个业务层
        ProductService productService = new ProductServiceImpl();
//        调用业务层查获取询数据
        ResultModel resultModel = productService.update(product);
//        返回数据到前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
