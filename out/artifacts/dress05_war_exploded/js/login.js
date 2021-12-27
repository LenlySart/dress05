$(function () {
    layui.use('form', function (){
        let form = layui.form;
        form.on('submit(login)', function(data){

            console.log(data.field);
            //将这些值传递到Java后台
            $.ajax({
                url: '/login',//对应的是Java servlet里面的注解地址
                data: data.field,// 要传递给Java的数据，统一以对象的形式传递{}
                type: 'post',//对应的是servlet里面的方法 $("#username").val()
                dataType: 'json',//java传递到前端的数据统一格式json
                success: function (res) {// Java后台给前端的返回信息，res就是返回的结果
                    console.log(res);
                    if (res.msg == 'error-code') {
                        layer.msg("验证码错误");
                    } else if (res.msg == "error") {
                        layer.msg("登陆失败！用户名和密码错误(!0-0!)!");
                    }else if (res.msg == 'success'){
                        layer.msg("登录成功");
                        sessionStorage.setItem("loginName",$("#user").val());
                        location.href='/html/index.html'
                    }
                    }
            });
            return false;
        });
        //自定义验证规则
        form.verify({
            // username:[/^\w{6,16}$/,'用户名必须是6到16位的字符数字下划线'],
            username: [/^[A-z]\w{5,15}$/, '用户名必须是以字母开头，6到16位的字符数字下划线'],
            pass: [/(.+){6,12}$/, '密码必须6到12位'],
        });
    });
});
// 验证码点击事件
function changeCode() {
    $("#imgCode").attr("src","/code?q=" + Math.random());
    $("#input").focus();
}

