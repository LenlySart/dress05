package com.cn.wanxi.servlet.news;


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
 * @ClassName NewsFindAllServlet.java
 * @Description TODO
 * @createTime 2021年12月14日 10:00:00
 */
@WebServlet("/back/news/findAll")
public class NewsFindAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        封装前端传过来的值
        String pageNo = req.getParameter("page");
        String pageSize = req.getParameter("limit");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String nAbstract = req.getParameter("nAbstract");
        String start = req.getParameter("start");
        String end = req.getParameter("end");
//        封装数据
        News news = new News();
        news.setPageNo(Tool.stringToInt(pageNo));
        news.setPageSize(Tool.stringToInt(pageSize));
        news.setTitle(Tool.nullToString(title));
        news.setContent(Tool.nullToString(content));
        news.setnAbstract(Tool.nullToString(nAbstract));
        news.setStart(Tool.nullToString(start));
        news.setEnd(Tool.nullToString(end));

//        创建一个业务层
        NewsService newsService = new NewsServiceImpl();
//        调用业务层查获取询数据Product
        ResultModel resultModel = newsService.getfindAll(news);
//        返回数据到前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
