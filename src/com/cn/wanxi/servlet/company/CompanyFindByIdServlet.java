package com.cn.wanxi.servlet.company;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.company.Company;
import com.cn.wanxi.service.company.CompanyService;
import com.cn.wanxi.service.company.impl.CompanyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName CompanyFindByIdServlet.java
 * @Description TODO
 * @createTime 2021年12月13日 17:33:00
 */
@WebServlet("/back/company/findById")
public class CompanyFindByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        封装前端传过来的值

        Company company = new Company();
        company.setId(1);
//        创建一个业务层
        CompanyService companyService = new CompanyServiceImpl();
//        调用业务层查获取询数据
        ResultModel resultModel = companyService.getfindById(company);
//        返回数据到前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
