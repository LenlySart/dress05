package com.cn.wanxi.servlet.dress;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.service.dress.NavService;
import com.cn.wanxi.service.dress_impl.NavServiceImpl;

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
 * @ClassName TopServlet.java
 * @Description TODO
 * @createTime 2021年12月16日 11:34:00
 */
@WebServlet("/top")
public class TopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultModel resultModel = new ResultModel();
        HashMap map = new HashMap();

//        首页导航和产品导航
        NavService navService = new NavServiceImpl();
        map.put("navsList",navService.findAll());

        //将获取的值存进data中转发到前端页面
        resultModel.setData(map);

        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
