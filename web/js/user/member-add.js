layui.use(['form', 'layer', 'jquery', 'laydate'],
    function () {
        $ = layui.jquery;
        let form = layui.form,
            layer = layui.layer
            , laydate = layui.laydate;
        //日期
        laydate.render({
            elem: '#birthday',
            trigger: 'click'
        });
        //自定义验证规则
        form.verify({
            username: [/[a-zA-Z]\w{5,15}/, "以字母开头的后面跟5到15个字母，数字，下划线"],
            password: [/(.+){6,12}$/, '密码必须6到12位'],
            repass: function (value) {
                if ($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
        });

        //监听提交
        form.on('submit(add)',
            function (data) {
                data = data.field;
                //拿到复选框的所有的title的值
                //提交之前，需要重新设置爱好
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
                console.log(data);
                //发异步，把数据提交给java
                $.ajax({
                    url:'/back/user/add',
                    data:data,
                    type:'post',
                    async: false,
                    dataType:'json',
                    success:function (res) {
                        if (res != undefined && res.count == 1) {
                        // if (res.msg=="success"){
                            layer.alert("增加成功", {
                                    icon: 6
                                },
                                function () {
                                    //关闭当前frame

                                    xadmin.close();

                                    // 可以对父窗口进行刷新
                                    xadmin.father_reload();

                                });
                        } else {
                            layer.alert("增加失败");
                        }

                    }
                })
                return false;
            });

    });