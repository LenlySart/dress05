$(function () {
    let id = sessionStorage.getItem("messageId");
    let res = myAjax("/back/message/findById", {id: id}, 'get');
    setMessageData(res.data);
});

function setMessageData(data) {
    layui.use(['laydate', 'form', 'layer'],
        function() {
            $ = layui.jquery;
            let form = layui.form;
            console.log(data);
            $('#visitorName').html('访客：'+data.visitorName);
            $('#visitorEmail').html('访客邮箱：'+data.visitorEmail);
            $('#state').html('状态：'+(data.state == 1 ?'已回复':'未回复'));
            $('#createTime').html('时间：'+data.createTime);
            $('#message').html(data.message);
            // 重新渲染表单
            form.render();
        });

}
 