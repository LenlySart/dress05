package com.cn.wanxi.servlet.dress;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.news.NewsSort;
import com.cn.wanxi.service.dress.NewsSortService;
import com.cn.wanxi.service.dress_impl.NewsSortServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NewsSortServlet.java
 * @Description TODO
 * @createTime 2021年12月22日 13:55:00
 */
@WebServlet("/sort/findAll")
public class NewsSortServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        NewsSort newsSort = new NewsSort();

        NewsSortService newsSortService = new NewsSortServiceImpl();

        ResultModel resultModel = newsSortService.getFindAll(newsSort);

        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
