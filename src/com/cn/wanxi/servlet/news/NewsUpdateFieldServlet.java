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
 * @ClassName NewsUpadateFieldServlet.java
 * @Description TODO
 * @createTime 2021年12月14日 13:57:00
 */
@WebServlet("/back/news/updateField")
public class NewsUpdateFieldServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        封装前端传过来的值
        String id = req.getParameter("id");
        String field = req.getParameter("field");
        String value = req.getParameter("value");

//        封装数据
        News news = new News();
        news.setId(Tool.stringToInt(id));
        news.setField(field);
        news.setValue(value);

//        创建一个业务层
        NewsService newsService = new NewsServiceImpl();
//        调用业务层查获取询数据
        ResultModel resultModel = newsService.updateField(news);
//        返回数据到前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
