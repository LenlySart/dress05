package com.cn.wanxi.servlet;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.enums.ResultModel;
import org.apache.commons.fileupload.FileItem;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName SendEmail.java
 * @Description TODO
 * @createTime 2021年12月17日 17:04:00
 */
@WebServlet("/email")
public class SendEmail extends HttpServlet {
    // 上传文件存储目录
    private static final String EMAIL_FILE = "email";

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", "smtp.qq.com");
        //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
        props.put("mail.smtp.port", "25");
        // 此处填写你的账号
        props.put("mail.user","1941486128@qq.com");
        // 此处的密码就是前面说的16位STMP口令
        props.put("mail.password","whuezbjvxqqfbbeh");

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form;

//        获取前端数据
        String email = req.getParameter("visitorEmail");
        String visitorMessage = req.getParameter("content");
        String title = req.getParameter("title");


        try {
            form = new InternetAddress(
                    props.getProperty("mail.user"));

            message.setFrom(form);


            // 设置收件人的邮箱
            InternetAddress to = new InternetAddress(email);
            message.setRecipient(Message.RecipientType.TO, to);

            // 设置邮件标题
            message.setSubject(title);

//            // 创建消息部分
//            BodyPart messageBodyPart = new MimeBodyPart();
//
//            // 填写消息
//            messageBodyPart.setText(visitorMessage);
//
//
//            // 创建一个多部分消息
//            Multipart multipart = new MimeMultipart();
//
//            // 设置文本消息部分
//            multipart.addBodyPart(messageBodyPart);
//
//
//            // 第二部分是附件
//            messageBodyPart = new MimeBodyPart();
////            文件名
//            String filename = "";
//            DataSource source = new FileDataSource(filename);
//            messageBodyPart.setDataHandler(new DataHandler(source));
//            messageBodyPart.setFileName(filename);
//            multipart.addBodyPart(messageBodyPart);
//
//            // 发送完整的消息部分
//            message.setContent(multipart );


            // 设置邮件的内容体
            message.setContent(visitorMessage, "text/html;charset=UTF-8");

            // 最后当然就是发送邮件啦
            Transport.send(message);
            ResultModel resultModel = new ResultModel();
            resultModel.setMsg("success");
            resp.getWriter().println(JSONObject.toJSONString(resultModel));
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
