$(function () {
    findTop();
})

function findTop() {
    $.ajax({
        url: "/top",
        type:'get',
        data: '',
        async: false,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            setNavs(data.data.navsList);
        }
    });
}
function setNavs(data) {
    let html='';
    for (let i = 0; i <data.length ; i++) {
        html +=' <div onclick="window.location.href=\''+data[i].href+'\'">'+data[i].title+'</div>'
    }
    $("#dressNavs").html(html);
}

function login() {
    let loginName = sessionStorage.getItem("loginName");
    if (loginName!=null){
        location.href = "/html/index.html";
        return;
    }
    location.href = "/html/login.html";
}
function leaveWords() {
    location.href = "/dress/html/more.html";
}
