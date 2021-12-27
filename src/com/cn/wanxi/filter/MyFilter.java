package com.cn.wanxi.filter;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class MyFilter extends HttpFilter {
    /**
     *
     * 去查询servlet的生命周期
     * init   service   destroy
     */

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        // 过滤
        //乱码解决
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/html;charset=utf-8");
//        获取拦截路径
        String path = req.getServletPath();
//        拦截以/back/开头的语句
        if (!path.startsWith("/back/")) {
            chain.doFilter(req, resp);
            return;
        }
//        //获取传来的请求
//        String uri = req.getRequestURI();
//        // 对请求的字符串进行截取 例如http://localhost:8080/aaa/bbb.jsp中我们想要的请求字符串为bbb.jsp
//        uri = uri.substring(uri.lastIndexOf("/back/")+1);
//        // 放行登录注册界面和请求以及相对应的效验请求还有css样式！！！很多人加了过滤器没有放行样式所以导致样式都没了
//        // 解决方法放行样式：如下
//        if (uri.equals("login.jsp") || uri.equals("checkUserTele.do") || uri.equals("reguser.do")
//                || uri.equals("checkUserCardId.do") || uri.equals("userLogin.do") || uri.equals("insertUser.do")
//                || uri.indexOf(".jpg") > 0 || uri.indexOf(".jpeg") > 0 || uri.indexOf(".png") > 0
//                || uri.indexOf(".gif") > 0 || uri.indexOf(".css") > 0
//                || uri.indexOf("js") > 0) {
//            // 放行到下一个过滤器，如果是最后一个，跳转到对应请求处理方法
//            chain.doFilter(req, resp);
//            return;
//        }
//        if (path.indexOf(".jpg") > 0 || path.indexOf(".png") > 0 || path.indexOf(".gif") > 0 || path.indexOf(".css") > 0
//                || path.indexOf("js") > 0) {
//            chain.doFilter(req, resp);
//        }

        //        将用户名存到session中
        //        获取session中的用户名
        String loginName = (String) req.getSession().getAttribute("loginName");
//        判断是否为空
        boolean isLogin = loginName == null;
//        如果不为空
        if (!isLogin) {

//        （放行）
            chain.doFilter(req, resp);
            return;
        }
        resp.setContentType("text/html;charset=utf-8");
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(-1);
        resultModel.setMsg("用户未登录");
        resultModel.setData("/html/login.html");
        resp.getWriter().print(JSONObject.toJSONString(resultModel));

    }

     /**
     * 初始化，项目只加载一次
     */

    @Override
    public void init() {
//  一般情况下这里面会写定时器
    }

    @Override
    public void destroy() {
//  也是执行一次，结束某一个代码
    }
}
