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

        layedit.set({
            uploadImage: {
                url: '/upload' //接口url
                , type: '' //默认post
            }
        });
        //注意：layedit.set 一定要放在 build 前面，否则配置全局接口将无效。
        let index = layedit.build('content'); //建立编辑器

        //常规使用 - 普通图片上传
        let uploadInst = upload.render({
            elem: '#picture' //上传文件的id
            , url: '/upload'//后台访问的地址，需要将文件传到服务器，
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                // 将上传的图片预览到下面的图片框
                obj.preview(function (index, file, result) {
                    $('#photograph').attr('src', result); //图片链接（base64）
                });

            }
            , done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }
                console.log(res);
                // 获取图片src
                sessionStorage.setItem("imgHref", res.data.src);
                // 获取上传图片的值
                // $('#photograph').attr('src', res.data.src);
                //上传成功的一些操作
                $('#demoText').html(''); //置空上传失败的状态
            }


        });


        //日期
        laydate.render({
            elem: '#birthday',
            trigger: 'click'
        });
        //自定义验证规则
        form.verify({
            markedPrice:[/^\d+$|^\d+[.]?\d+$/,'请输入正确的价格'],
            normalPrice:[/^\d+$|^\d+[.]?\d+$/,'请输入正确的价格']

        });
        // 调用类型名称
        findTypeAll();
        //监听提交
        form.on('submit(add)',
            function (data) {
                data = data.field;

                //发异步，把数据提交给java
                //提交之前，需要重新设置爱好
                //拿到复选框的所有的title的值

                data.imgHref = sessionStorage.getItem("imgHref");
                // data.imgHref = $('#photograph').attr('src');
                // data.content = layedit.getContent(index);
                console.log(data);

                let res = myAjax("/back/product/add", data);
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

