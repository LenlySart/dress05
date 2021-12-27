package com.cn.wanxi.servlet.user;

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
 * @ClassName UserCountServlet.java
 * @Description TODO
 * @createTime 2021年12月04日 10:50:00
 */
@WebServlet("/back/user/count")
public class UserCountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取前端数据
        String pageNo = req.getParameter("page");
        String pageSize = req.getParameter("limit");
//        封装数据
        User user = new User();
        user.setPageNo(Tool.stringToInt(pageNo));
        user.setPageSize(Tool.stringToInt(pageSize));

        UserService userService = new UserServiceImpl();
        ResultModel resultModel = userService.getCount(user);
//        返回数据以json的方式给前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));

    }
}
