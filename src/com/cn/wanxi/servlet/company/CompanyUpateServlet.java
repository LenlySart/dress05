package com.cn.wanxi.servlet.company;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.company.Company;
import com.cn.wanxi.service.company.CompanyService;
import com.cn.wanxi.service.company.impl.CompanyServiceImpl;
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
 * @ClassName CompanyUpateServlet.java
 * @Description TODO
 * @createTime 2021年12月13日 18:39:00
 */
@WebServlet("/back/company/update")
public class CompanyUpateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        封装前端传过来的值
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String imgHref = req.getParameter("imgHref");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String proFile = req.getParameter("proFile");
        String state = req.getParameter("state");
        String qq = req.getParameter("qq");

        Company company = new Company();
        company.setId(1);
        company.setPhone(phone);
        company.setName(name);
        company.setEmail(email);
        company.setImgHref(imgHref);
        company.setAddress(address);
        company.setProFile(proFile);
        company.setQq(qq);
        company.setState(Tool.stringToInt(state));
//        创建一个业务层
        CompanyService companyService = new CompanyServiceImpl();
//        调用业务层查获取询数据
        ResultModel resultModel = companyService.update(company);
//        返回数据到前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
