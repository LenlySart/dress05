$(function () {
    findFoot();
})

function findFoot() {
    $.ajax({
        url: "/foot",
        type:'get',
        data: '',
        async: false,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            setCompany(data.data.company);
            setNews(data.data.newsList);
        }
    });
}
// 公司信息
function setCompany(data) {
    $("#address").html("地址:  "+data.address);
    $("#phone").html("电话:  "+data.phone);
    $("#email").html("邮箱:  "+data.email);
    $("#proFile").html(data.proFile);
    $("#imgHref").attr("src",data.imgHref);
}
// 新闻
function setNews(data) {
    let newsTitle ='';
    let newsTime ='';
    let length = 3;
    for (let i = 0; i < length; i++) {
        newsTitle +='<div>'+data[i].title+'</div>';
        newsTime +='<div>'+data[i].createTime+'</div>';
    }
    $("#newsTitle").html(newsTitle);
    $("#newsTime").html(newsTime);
}