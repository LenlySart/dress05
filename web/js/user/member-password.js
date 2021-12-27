$(function () {
    let id = sessionStorage.getItem("userId");
    let res = myAjax("/back/user/findById", {id: id}, 'get');
    setPassword(res.data);
});

function setPassword(data) {
    console.log(data)
      $("#L_username").val(data.username);
}

layui.use(['form', 'layer'],
    function() {
        $ = layui.jquery;
        let form = layui.form,
            layer = layui.layer;
        //自定义验证规则
        form.verify({
            pass: [/(.+){6,12}$/, '密码必须6到12位'],
            ordpass: [/(.+){6,12}$/, '密码必须6到12位'],
            repass: function(value) {
                if ($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
        });
        //监听提交
        form.on('submit(save)',
            function(data) {
                console.log(data);
                data = data.field;
                // 获取id
                data.id=sessionStorage.getItem("userId");
                console.log(data);
                //发异步，把数据提交给java
                let res = myAjax("/back/user/updatePassword" ,data);
                if (res != undefined && res.count == 1 && res.msg=="success") {
                    layer.alert("修改成功", {
                            icon: 6
                        },
                        function() {
                            // 获得frame索引
                            let index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                        });
                }else if (res.msg=="error"){
                    layer.alert("旧密码错误");
                }
                // layer.alert("更新失败（！0.0！）");
                return false;
            });

    });