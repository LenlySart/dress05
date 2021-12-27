package com.cn.wanxi.servlet.message;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.dress.Message;
import com.cn.wanxi.service.dress.MessageService;
import com.cn.wanxi.service.dress_impl.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 产品展示
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName Product.java
 * @Description TODO
 * @createTime 2021年11月22日 19:33:00
 */
@WebServlet("/back/message/add")
public class MessageAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String visitorName = req.getParameter("name");
        String visitorEmail = req.getParameter("email");
        String message = req.getParameter("message");

        Message model = new Message();
        model.setVisitorName(visitorName);
        model.setVisitorEmail(visitorEmail);
        model.setMessage(message);

        MessageService messageService = new MessageServiceImpl();
        ResultModel resultModel = messageService.add(model);

        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
