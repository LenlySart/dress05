package com.cn.wanxi.servlet.message;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.dress.Message;
import com.cn.wanxi.service.dress.MessageService;
import com.cn.wanxi.service.dress_impl.MessageServiceImpl;
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
 * @ClassName NewsDeleteServlet.java
 * @Description TODO
 * @createTime 2021年12月14日 14:02:00
 */
@WebServlet("/back/message/remove")
public class MessageDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        封装前端传过来的值
        String id = req.getParameter("id");
        String status = req.getParameter("status");

//        封装数据
        Message message = new Message();
        message.setId(Tool.stringToInt(id));
        message.setStatus(Tool.stringToInt(status));

//        创建一个业务层
        MessageService messageService = new MessageServiceImpl();
//        调用业务层查获取询数据
        ResultModel resultModel = messageService.deleteId(message);
//        返回数据到前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
