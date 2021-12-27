$(function () {
    // 判断用户名是否存在session中，如果会话结束那么跳转到登录
    let loginName = sessionStorage.getItem("loginName");
    if (loginName == null) {
        alert("登录过期请，重新登录！");
        location.href = "/html/login.html";
    }
});

function myAjax(url, data, type) {
    //判断当前用户是否已经登录

    let res = {};
    $.ajax({
        url: url,
        type: type == null ? 'post' : 'get',
        data: data,
        async: false,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            res = data;
        }
    });
    return res;
}
function findTypeAll() {
    $.ajax({
        url: '/back/type/findAll',
        data: {},
        type: 'get',
        async: false,
        dataType: 'json',
        success: function (res) {
            console.log(res);
            setTypeData(res.data);
        }
    })
}

function setTypeData(data) {
    layui.use(['laydate', 'form', 'layer'],
        function () {
            $ = layui.jquery;
            var form = layui.form;
            let html = '';
            for (let i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].title + '</option>';
            }
            $("#title").html(html);
            // 重新渲染表单
            form.render();
        })

}