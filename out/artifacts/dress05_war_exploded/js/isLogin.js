//判断当前用户是否已经登录
//如果已经登录，则可以往后面执行
//如果没有登录，则跳转到登录页面进行登录
// let isLogin = false;
$(function () {
    isLogin();
});

function isLogin() {
    // 获取用户名
    let loginName = sessionStorage.getItem("loginName");
    // 判断用户名是否为null
    let isLogin = loginName == null;//当没有登录时则为true
    if (isLogin) {
        // window.open("/admin_html/login.html", "_top");
        location.href = "/html/login.html";
    }
}
