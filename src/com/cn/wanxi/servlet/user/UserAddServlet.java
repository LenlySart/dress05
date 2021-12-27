package com.cn.wanxi.servlet.user;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.model.User;
import com.cn.wanxi.enums.ResultModel;
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
 * @ClassName UserAddServlet.java
 * @Description TODO
 * @createTime 2021年12月02日 15:57:00
 */

@WebServlet("/back/user/add")
public class UserAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取前端传过来的值
        String username = req.getParameter("username");
        String password = req.getParameter("pass");
        String birthday = req.getParameter("birthday");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String sex = req.getParameter("sex");
        String hobby = req.getParameter("hobby");
        String state = req.getParameter("state");
//        封装前端传过来的值
        User user = new User();
        user.setUsername(username);
//        加密密码
        user.setPassword(Tool.encoderByMd5(password));
        user.setBirthday(birthday);
        user.setPhone(phone);
        user.setEmail(email);
        user.setHobby(hobby);
        user.setSex(Tool.stringToInt(sex));
        user.setState(Tool.stringToInt(state));
//        创建业务层实现add（）方法
        UserService service = new UserServiceImpl();
        ResultModel resultModel = service.add(user);
//        将添加成功的信息传给前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
