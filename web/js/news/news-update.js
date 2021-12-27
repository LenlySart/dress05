$(function () {
    let id = sessionStorage.getItem("newsId");
    let res = myAjax("/back/news/findById", {id: id}, 'get');
    setNewsData(res.data);
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
        //监听提交

        form.on('submit(edit)',
            function(data) {
                data = data.field;
                // 获取id
                data.id=sessionStorage.getItem("newsId");
                console.log(data);
                //发异步，把数据提交给php
                let res = myAjax("/back/news/update" ,data);
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

function setNewsData(data) {
    layui.use(['laydate', 'form', 'layer'],
        function() {
            $ = layui.jquery;
            let form = layui.form;
            console.log(data);
            $("#title").val(data.title);
            $("#href").val(data.href);
            $("#nAbstract").val(data.nAbstract);
            $("#content").val(data.content);
            $('input:radio[name=state][value=' + data.state + ']').attr("checked", true);
            $('input:radio[name=isShow][value=' + data.isShow + ']').attr("checked", true);
            $('input:radio[name=isRecommend][value=' + data.isRecommend + ']').attr("checked", true);
            // 重新渲染表单
            form.render();
        });

}