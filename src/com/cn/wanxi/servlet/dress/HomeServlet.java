package com.cn.wanxi.servlet.dress;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.service.company.CompanyService;
import com.cn.wanxi.service.company.impl.CompanyServiceImpl;
import com.cn.wanxi.service.dress.*;
import com.cn.wanxi.service.dress.impl.*;
import com.cn.wanxi.service.news.NewsService;
import com.cn.wanxi.service.news.impl.NewsServiceImpl;
import com.cn.wanxi.service.product.ProductFeatureService;
import com.cn.wanxi.service.product.SellerMessageService;
import com.cn.wanxi.service.product.impl.ProductFeatureServiceImpl;
import com.cn.wanxi.service.product.impl.SellerMessageServiceImpl;

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
 * @ClassName HomeServlet.java
 * @Description TODO
 * @createTime 2021年11月22日 15:16:00
 */
//外部访问后台的接口（地址）
@WebServlet("/index")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        ResultModel resultModel = new ResultModel();
        HashMap map = new HashMap();

//        首页导航和产品导航
        NavService navService = new NavServiceImpl();
        map.put("navs", navService.findAll());


//        公司信息
        CompanyService companyService = new CompanyServiceImpl();
        map.put("company", companyService.getCompanyModle());

        //产品展示选型
        ProductFeatureService productFeatureService = new ProductFeatureServiceImpl();
        map.put("productFeature", productFeatureService.getproductFeature());

//        卖家秀及留言
        SellerMessageService sellerMessageService = new SellerMessageServiceImpl();
        map.put("sellerMessage", sellerMessageService.getproductFeature());

//        底部新闻
        NewsService newsService = new NewsServiceImpl();
        map.put("newsList", newsService.getNewsModel());

        //将获取的值存进data中转发到前端页面
        resultModel.setData(map);

        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
