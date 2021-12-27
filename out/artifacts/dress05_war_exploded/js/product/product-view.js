$(function () {
    let id = sessionStorage.getItem("productId");
    let res = myAjax("/findById", {id: id}, 'get');
    setData(res.data);
});

function setData(data) {
    layui.use(['laydate', 'form', 'layer'],
        function() {
            $ = layui.jquery;
            let form = layui.form;
            console.log(data[0]);
            $("#productName").html('产品名：'+data[0].name);
            $("#photograph").attr("src",data[0].imgHref);
            $("#markedPrice").html('市价：'+data[0].markedPrice);
            $("#normalPrice").html(data[0].normalPrice);
            $('#state').html('状态是否正常：'+(data[0].state == 1 ?'是':'否'));
            $('#isShow').html('是否置顶：'+(data[0].isShow == 1 ?'是':'否'));
            $('#isRecommend').html('是否是热点：'+(data[0].isRecommend == 1 ?'是':'否'));
            $('#fashionElement').html('流行元素：'+data[0].fashionElement);
            $('#style').html('风格：'+data[0].style);
            $('#model').html('服装版型：'+data[0].model);
            $('#fashion').html('款式：'+data[0].fashion);
            $('#clothesLength').html('衣长：'+data[0].clothesLength);
            $('#character').html('质地：'+data[0].character);
            $('#ingredient').html('成分含量：'+data[0].ingredient);
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