$(function () {
    $("#welcomName").html(sessionStorage.getItem("loginName"));
    //设定了一个定时器
    setInterval("setLoginTime()", "1000");
})


function setLoginTime() {
    dateTimes()
    let time1 = new Date().format("yyyy-MM-dd hh:mm:ss");//拿到系统时间
    $("#sysDate").html(time1);
}