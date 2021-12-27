package com.cn.wanxi.servlet.dress;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.service.dress.CompanyStoreService;
import com.cn.wanxi.service.dress.CompanyStylistService;
import com.cn.wanxi.service.dress_impl.CompanyStoreServiceImpl;
import com.cn.wanxi.service.dress_impl.CompanyStylistServiceImpl;

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
 * @ClassName AboutServlet.java
 * @Description TODO
 * @createTime 2021年11月22日 20:06:00
 */
@WebServlet("/about")
public class AboutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ResultModel resultModel = new ResultModel();
        HashMap map = new HashMap();

//        设计师信息
        CompanyStylistService companyStylistService = new CompanyStylistServiceImpl();
        map.put("stylist",companyStylistService.getCompanyStylist());

        //线下门店信息
        CompanyStoreService companyStoreService = new CompanyStoreServiceImpl();
        map.put("storeList",companyStoreService.getCompanyStore());
//        查询门店图片
        map.put("stores",companyStoreService.getCompanyStoreList());

        //将获取的值存进data中转发到前端页面
        resultModel.setData(map);

        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }

}
