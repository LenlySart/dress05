package com.cn.wanxi.servlet.news;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.news.News;
import com.cn.wanxi.service.dress.NewsService;
import com.cn.wanxi.service.dress_impl.NewsServiceImpl;
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
 * @ClassName NewsAddServlet.java
 * @Description TODO
 * @createTime 2021年12月14日 11:39:00
 */
@WebServlet("/back/news/add")
public class NewsAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //封装前端传过来的值
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String nAbstract = req.getParameter("nAbstract");
        String state = req.getParameter("state");
        String isShow = req.getParameter("isShow");
        String isRecommend = req.getParameter("isRecommend");

        //封装数据
        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setState(Tool.stringToInt(state));
        news.setnAbstract(nAbstract);
        news.setIsShow(Tool.stringToInt(isShow));
        news.setIsRecommend(Tool.stringToInt(isRecommend));

//        创建一个业务层
        NewsService newsService = new NewsServiceImpl();
//        调用业务层查获取询数据Product
        ResultModel resultModel = newsService.add(news);
//        返回数据到前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
