package com.cn.wanxi.servlet.product;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.product.TypeModel;
import com.cn.wanxi.service.dress.TypeService;
import com.cn.wanxi.service.dress_impl.TypeServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName ProductTypeServlet.java
 * @Description TODO
 * @createTime 2021年12月11日 13:49:00
 */
@WebServlet("/back/type/findAll")
public class ProductTypeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        封装前端传过来的值
//        封装数据
        TypeModel typeModel = new TypeModel();

//        创建一个业务层
        TypeService typeService = new TypeServiceImpl();
//        调用业务层查获取询数据
        ResultModel resultModel = typeService.getfindAll(typeModel);
//        返回数据到前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
