package com.cn.wanxi.servlet.dress;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.service.dress.NavService;
import com.cn.wanxi.service.product.ProductService;
import com.cn.wanxi.service.dress.impl.NavServiceImpl;
import com.cn.wanxi.service.product.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName Page1Servlet.java
 * @Description TODO
 * @createTime 2021年11月26日 09:57:00
 */
@WebServlet("/page")
public class PageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ResultModel resultModel = new ResultModel();
        HashMap map = new HashMap();

//        首页导航和产品导航
        NavService navService = new NavServiceImpl();
        map.put("nevModel",navService.getProductNav());


//        总的产品展示
        ProductService productService = new ProductServiceImpl();

        /*礼服*/
        map.put("rodeList",productService.getRodetList());
        /*西装*/
        map.put("blazerList",productService.getBlazertList());
        /*定制*/
        map.put("tailoredList",productService.getTailoredList());
        /*婚纱*/
        map.put("bridalVeil",productService.getBridalVeil());

        //将获取的值存进data中转发到前端页面
        resultModel.setData(map);

        resp.getWriter().println(JSONObject.toJSONString(resultModel));


    }
}
