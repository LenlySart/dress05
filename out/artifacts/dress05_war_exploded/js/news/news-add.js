//加载模块
layui.use(['layedit', 'upload', 'element', 'form', 'layer', 'jquery', 'laydate'],
    function () {
        $ = layui.jquery;
        let form = layui.form
            , element = layui.element
            , upload = layui.upload
            ,  layer = layui.layer
            , laydate = layui.laydate;

        let layedit = layui.layedit;
        let index = layedit.build('content'); //建立编辑器
        //自定义验证规则
        form.verify({
            title: function(value) {
                if (value.length < 3) {
                    return '昵称至少得3个字符啊';
                }
            },
        });

        //监听提交
        form.on('submit(add)',
            function(data) {
                data=data.field;
                data.content = layedit.getContent(index);
                console.log(data);
                let res = myAjax("/back/news/add", data);
                console.log(res);
                if (res != undefined && res.count == 1) {
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
                return false;
            });

    });