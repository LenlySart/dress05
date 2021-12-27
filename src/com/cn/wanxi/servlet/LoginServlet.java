package com.cn.wanxi.servlet;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.User;
import com.cn.wanxi.service.user.UserService;
import com.cn.wanxi.service.impl.UserServiceImpl;
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
 * @ClassName LoginServlet.java
 * @Description TODO
 * @createTime 2021年11月30日 17:20:00
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //乱码解决
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //        获取前端传过来的值
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String code = req.getParameter("code");
        String sessionCode = (String) req.getSession().getAttribute("code");

//        封装前端传过来的值到model中
        User user = new User();
        user.setUsername(username);
        user.setPassword(Tool.encoderByMd5(password));
        user.setCode(code);
        user.setSessionCode(sessionCode);

//        创建service对象获取其中方法
        UserService userService = new UserServiceImpl();
//        接收服务逻辑层返回信息
        ResultModel resultModel = userService.login(user);
        if (resultModel.getMsg().equals("success")) {
            req.getSession().setAttribute("loginName", username);
        }
        //将信息返回给前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
