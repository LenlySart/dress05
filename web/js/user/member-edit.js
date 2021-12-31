$(function () {
    let id = sessionStorage.getItem("userId");
    let res = myAjax("/back/user/findById", {id: id}, 'get');
    setData(res.data);
});

function setData(data) {
    layui.use(['form'],
        function() {
            $ = layui.jquery;
            let form = layui.form;

            console.log(data);
            $("#L_username").val(data.username);
            $("#L_email").val(data.email);
            $("#birthday").val(data.birthday);
            $("#phone").val(data.phone);
            $("#sex").val(data.sex);
            //爱好
            let hobby = data.hobby;
            let arr = hobby.split(",");
            $('input:checkbox[name=hobby]').each(function () {
                for (let i = 0; i < arr.length; i++) {
                    if ($(this).attr("title") == arr[i]) {
                        $(this).attr("checked", true);
                    }
                }
            });
            $("#hobby").val(data.hobby);
            $('input:radio[name=state][value=' + data.state + ']').attr("checked", true);
            form.render();
    });

}

layui.use(['form', 'layer'],
    function() {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;

        //自定义验证规则
        form.verify({
            nikename: function(value) {
                if (value.length < 5) {
                    return '昵称至少得5个字符啊';
                }
            },
            pass: [/(.+){6,12}$/, '密码必须6到12位'],
            repass: function(value) {
                if ($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
        });

        //监听提交
        form.on('submit(edit)',
            function(data) {
                data = data.field;
                let arr=[];
                // checkbox被选中，用[name=hobby]来分化组
                $('input:checkbox[name=hobby]:checked').each(function() {
                    // 每次只能拿一个，需要将所有的值全部放在一起，则需要数组
                    //   val表示拿到value的值，
                    arr.push($(this).attr("title")) ;

                });
                arr +=","+[$("#hobby").val()];
                console.log(arr)
                // 将爱好转换为字符串
                data.hobby =arr.toLocaleString();
                // 获取id
                data.id=sessionStorage.getItem("userId");
                console.log(data);
                //发异步，把数据提交给php
                let res = myAjax("/back/user/update" ,data);
                if (res != undefined && res.count == 1) {
                    layer.alert("修改成功", {
                            icon: 6
                        },
                        function() {
                            // 获得frame索引
                            let index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                            // 可以对父窗口进行刷新
                            xadmin.father_reload();
                        });
                }else {
                    layer.alert("更新失败");
                }
                return false;
            });
    });