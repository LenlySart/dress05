package com.cn.wanxi.servlet.dress;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.service.dress.CompanyService;
import com.cn.wanxi.service.dress.NewsService;
import com.cn.wanxi.service.dress_impl.CompanyServiceImpl;
import com.cn.wanxi.service.dress_impl.NewsServiceImpl;

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
 * @ClassName MoreServlet.java
 * @Description TODO
 * @createTime 2021年11月22日 20:06:00
 */
@WebServlet("/more")
public class MoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultModel resultModel = new ResultModel();
        HashMap map = new HashMap();

//        公司信息
        CompanyService companyService = new CompanyServiceImpl();
        map.put("companys",companyService.getCompanyModle());

        //将获取的值存进data中转发到前端页面
        resultModel.setData(map);

        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
