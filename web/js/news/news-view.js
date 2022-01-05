$(function () {
    let id = sessionStorage.getItem("newsId");
    let res = myAjax("/back/news/findById", {id: id}, 'get');
    setNewsData(res.data);
});

function setNewsData(data) {
    layui.use(['laydate', 'form', 'layer'],
        function() {
            $ = layui.jquery;
            let form = layui.form;
            console.log(data);
            $('#title').html('标题：'+data.title);
            $('#content').html('内容：'+data.content);
            $("#nAbstract").val(data.nAbstract);
            $('#state').html('状态是否正常：'+(data.state == 1 ?'是':'否'));
            $('#isShow').html('是否置顶：'+(data.isShow == 1 ?'是':'否'));
            $('#isRecommend').html('是否是热点：'+(data.isRecommend == 1 ?'是':'否'));
            // 重新渲染表单
            form.render();
        });

}
 