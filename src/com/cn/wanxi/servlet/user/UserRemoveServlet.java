package com.cn.wanxi.servlet.user;

import com.alibaba.fastjson.JSON;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.User;
import com.cn.wanxi.service.impl.UserServiceImpl;
import com.cn.wanxi.service.user.UserService;
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
 * @ClassName UserRemoveServlet.java
 * @Description TODO
 * @createTime 2021年12月06日 14:34:00
 */
@WebServlet("/back/user/remove")
public class UserRemoveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        要得到前端的数据
        String id = req.getParameter("id");
        //        封装model
        User user = new User();
        user.setId(Tool.stringToInt(id));
//        4.调用服务逻辑层
        UserService userService = new UserServiceImpl();
        //        5.得到统一的返回值对象
        ResultModel resultModel = userService.deleteId(user);
        //       6.将数据返回给前端页面
        resp.getWriter().println(JSON.toJSONString(resultModel));
    }
}
