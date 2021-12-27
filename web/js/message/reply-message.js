$(function () {
    let id = sessionStorage.getItem("messageId");
    let res = myAjax("/back/message/findById", {id: id}, 'get');
    setMessageData(res.data);
});

let layedit;
let index;
layui.use(['layedit', 'upload', 'element', 'form', 'layer', 'jquery', 'laydate'],
    function () {
        $ = layui.jquery;
        let form = layui.form
            , element = layui.element
            , upload = layui.upload
            , layer = layui.layer
            , laydate = layui.laydate;
        let layedit = layui.layedit;
        //监听提交
        layedit.set({
            uploadImage: {
                url: '/upload' //接口url
                // , type: 'post' //默认post
            }
        });
        index = layedit.build('content'); //建立编辑器
        form.on('submit(edit)',
            function(data) {
                data = data.field;
                // 获取id
                data.id=sessionStorage.getItem("messageId");
                data.content = layedit.getContent(index);
                console.log(data);
                //发异步，把数据提交给java后台
                 myAjax("/email" ,data,"get");
                let res = myAjax("/back/message/update" ,data);
                if (res != undefined && res.count == 1) {
                    layer.alert("回复成功", {
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
                    layer.alert("回复失败");
                }
                return false;
            });


    });

function setMessageData(data) {
    layui.use(['laydate', 'form', 'layer'],
        function() {
            $ = layui.jquery;
            let form = layui.form;
            console.log(data);
            $("#visitorName").val(data.visitorName);
            $("#visitorEmail").val(data.visitorEmail);
            $('input:radio[name=state][value=' + data.state + ']').attr("checked", true);
            // 重新渲染表单
            form.render();
        });

}