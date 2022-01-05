$(function () {
    let id = sessionStorage.getItem("productId");
    let res = myAjax("/back/product/findById", {id: id}, 'get');
    setData(res.data);
});

layui.use(['layedit', 'upload', 'element', 'form', 'layer', 'jquery', 'laydate'],
    function () {
        $ = layui.jquery;
        let form = layui.form
            , element = layui.element
            , upload = layui.upload
            , layer = layui.layer
            , laydate = layui.laydate;

        let layedit = layui.layedit;
        layedit.set({
            uploadImage: {
                url: '/upload' //接口url
                , type: '' //默认post
            }
        });


//常规使用 - 普通图片上传
upload.render({
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
})
function setData(data) {
    layui.use(['laydate', 'form', 'layer'],
        function() {
            $ = layui.jquery;
            let form = layui.form;
            console.log(data);
            $("#name").val(data.name);
            $("#photograph").attr("src",data.imgHref);
            $("#markedPrice").val(data.markedPrice);
            $("#normalPrice").val(data.normalPrice);
            findTypeAll();
            $("#title").val(data.typeId);
            $('input:radio[name=state][value=' + data.state + ']').attr("checked", true);
            $('input:radio[name=isShow][value=' + data.isShow + ']').attr("checked", true);
            // 重新渲染表单
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
            name: function(value) {
                if (value.length < 2) {
                    return '昵称至少得2个字符啊';
                }
            },
            pass: [/(.+){6,12}$/, '密码必须6到12位'],
            markedPrice:[/^\d+$|^\d+[.]?\d+$/,'请输入正确的价格'],
            normalPrice:[/^\d+$|^\d+[.]?\d+$/,'请输入正确的价格']


        });

        //监听提交
        form.on('submit(edit)',
            function(data) {
                data = data.field;
                // 获取id
                data.id=sessionStorage.getItem("productId");
                data.imgHref = sessionStorage.getItem("imgHref");
                console.log(data);
                //发异步，把数据提交给php
                let res = myAjax("/back/product/update" ,data);
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