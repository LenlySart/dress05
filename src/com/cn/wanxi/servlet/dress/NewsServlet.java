package com.cn.wanxi.servlet.dress;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.news.News;
import com.cn.wanxi.service.news.NewsService;
import com.cn.wanxi.service.news.impl.NewsServiceImpl;
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
 * @ClassName NewsServlet.java
 * @Description TODO
 * @createTime 2021年11月22日 16:00:00
 */
@WebServlet("/news")
public class NewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        封装前端传过来的值
        String pageNo = req.getParameter("page");
        String pageSize = req.getParameter("limit");
        String sortId = req.getParameter("sortId");
//        封装数据
        News news = new News();
        news.setPageNo(Tool.stringToInt(pageNo));
        news.setPageSize(Tool.stringToInt(pageSize));
        news.setSortId(Tool.stringToInt(sortId));

//        创建一个业务层
        NewsService newsService = new NewsServiceImpl();
//        调用业务层查获取询数据
        ResultModel resultModel = newsService.getFindAll(news);
//        返回数据到前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
