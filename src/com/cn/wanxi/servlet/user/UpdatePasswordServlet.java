package com.cn.wanxi.servlet.user;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.User;
import com.cn.wanxi.service.user.impl.UserServiceImpl;
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
 * @ClassName UpdatePasswordServlet.java
 * @Description TODO
 * @createTime 2021年12月06日 11:04:00
 */
@WebServlet("/back/user/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前端传过来的值
        String id = req.getParameter("id");
        String oldPassword = req.getParameter("oldpass");
        String password = req.getParameter("newpass");
        //封装前端传过来的值
        User user = new User();
        user.setId(Tool.stringToInt(id));
        user.setPassword(Tool.encoderByMd5(oldPassword));
        user.setPass(Tool.encoderByMd5(password));
        //创建业务层实现add（）方法
        UserService service = new UserServiceImpl();
        ResultModel resultModel = service.updatePassword(user);
        //将添加成功的信息传给前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));

    }
}
