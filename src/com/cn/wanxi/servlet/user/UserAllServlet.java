package com.cn.wanxi.servlet.user;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.model.User;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.service.user.UserService;
import com.cn.wanxi.service.user.impl.UserServiceImpl;
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
 * @ClassName UserServlet.java
 * @Description TODO
 * @createTime 2021年12月01日 19:48:00
 */
@WebServlet("/back/user/all")
public class UserAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        封装前端传过来的值
        String pageNo = req.getParameter("page");
        String pageSize = req.getParameter("limit");
        String username = req.getParameter("username");
        String start = req.getParameter("start");
        String end = req.getParameter("end");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String sex = req.getParameter("sex");
        String hobby = req.getParameter("hobby");
        String state = req.getParameter("state");
        User user = new User();
        user.setPageNo(Tool.stringToInt(pageNo));
        user.setPageSize(Tool.stringToInt(pageSize));
        user.setUsername(Tool.nullToString(username));
        user.setStart(Tool.nullToString(start));
        user.setEnd(Tool.nullToString(end));
        user.setPhone(Tool.nullToString(phone));
        user.setEmail(Tool.nullToString(email));
        user.setHobby(Tool.nullToString(hobby));
        user.setSex(Tool.stringToInt(sex));
        user.setState(Tool.stringToInt(state));

//        创建一个业务层
        UserService userService = new UserServiceImpl();
//        调用业务层查获取询数据
        ResultModel resultModel = userService.getAll(user);
//        返回数据到前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
