package com.cn.wanxi.servlet.message;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.dress.Message;
import com.cn.wanxi.service.dress.MessageService;
import com.cn.wanxi.service.dress.impl.MessageServiceImpl;
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
 * @ClassName MessageFindAllServlet.java
 * @Description TODO
 * @createTime 2021年12月17日 15:30:00
 */
@WebServlet("/back/message/findAll")
public class MessageFindAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        封装前端传过来的值
        String pageNo = req.getParameter("page");
        String pageSize = req.getParameter("limit");
//        封装数据
        Message message = new Message();
        message.setPageNo(Tool.stringToInt(pageNo));
        message.setPageSize(Tool.stringToInt(pageSize));

//        创建一个业务层
        MessageService messageService = new MessageServiceImpl();
//        调用业务层查获取询数据
        ResultModel resultModel = messageService.getfindAll(message);
//        返回数据到前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
