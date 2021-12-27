package com.cn.wanxi.servlet.product;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.dress.SellerMessage;
import com.cn.wanxi.service.dress.SellerMessageService;
import com.cn.wanxi.service.dress_impl.SellerMessageServiceImpl;
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
 * @ClassName ProductMessageServlet.java
 * @Description TODO
 * @createTime 2021年12月13日 15:35:00
 */
@WebServlet("/back/product/productMessage")
public class ProductMessageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        封装前端传过来的值
        String pageNo = req.getParameter("page");
        String pageSize = req.getParameter("limit");
//        封装数据
        SellerMessage sellerMessage = new SellerMessage();
        sellerMessage.setPageNo(Tool.stringToInt(pageNo));
        sellerMessage.setPageSize(Tool.stringToInt(pageSize));

//        创建一个业务层
        SellerMessageService sellerMessageService = new SellerMessageServiceImpl();
//        调用业务层查获取询数据
        ResultModel resultModel = sellerMessageService.getfindAll(sellerMessage);
//        返回数据到前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
