$(function () {
    myAjax();
})

function myAjax() {
    $.ajax({
        url: "/index",
        type:'get',
        data: '',
        async: false,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            setCompany(data.data.company);
            setNavs(data.data.navs);
            setProductFeature(data.data.productFeature);
            setMessage(data.data.sellerMessage);
            setNews(data.data.newsList);
        }
    });
}

// 首页导航
function setNavs(data) {
    let html='';
    for (let i = 0; i <data.length ; i++) {
        html +=' <div onclick="window.location.href=\''+data[i].href+'\'">'+data[i].title+'</div>'
    }
    $("#navs").html(html);
}
// 公司信息
function setCompany(data) {
    $("#address").html("地址:  "+data.address);
    $("#phone").html("电话:  "+data.phone);
    $("#email").html("邮箱:  "+data.email);
    $("#proFile").html(data.proFile);
    $("#imgHref").attr("src",data.imgHref);
}
// 定制服务类型
function setProductFeature(data) {
    let html='';
    for (let i = 0; i <data.length ; i++) {
        html +='<div>\n' +
            '        <img src="'+data[i].imgHref+'" height="450" width="309"/>\n' +
            '        <div>\n' +
            '             <div>'+data[i].name+'</div>\n' +
            '             <div>'+data[i].pDescribe+'</div>\n' +
            '        </div>\n' +
            '  </div>'
    }
    $("#picture").html(html);
}
// 留言
function setMessage(data) {
    let seller ={};
    let manMessage ={};
    let femaleMessage ={};
    let seller2 ={};
    let manMessage2 ={};
    let femaleMessage2 ={};
    let seller3 ={};
    let manMessage3 ={};
    let femaleMessage3 ={};
    for (let i = 0; i <1 ; i++) {
        seller =data[i].seller;
        manMessage =data[i].manMessage;
        femaleMessage =data[i].femaleMessage;
    }
    for (let i = 0; i <2 ; i++) {
        seller2 =data[i].seller;
        manMessage2 =data[i].manMessage;
        femaleMessage2 =data[i].femaleMessage;
    }
    for (let i = 0; i <3 ; i++) {
        seller3 =data[i].seller;
        manMessage3 =data[i].manMessage;
        femaleMessage3 =data[i].femaleMessage;
    }
    let html ='<div>\n' +
        '<div><img src="'+seller+'" /></div>\n' +
        '    <div><img src="/dress/image/i4.png" height="50" width="50"/></div>\n' +
        '    <div>\n' +
        '    <div>\n' +
        '    <div>'+manMessage+'</div>\n' +
        '<div>'+femaleMessage+'</div>\n' +
        '</div>\n' +
        '</div>\n' +
        '</div>\n' +
        '<div>\n' +
        '<div>\n' +
        '<div>\n' +
        '<div>'+manMessage2+'</div>\n' +
        '<div>'+femaleMessage2+'</div>\n' +
        '</div>\n' +
        '</div>\n' +
        '<div><img src="/dress/image/i4.png" height="50" width="50"/></div>\n' +
        '    <div><img src="'+seller2+'" /></div>\n' +
        '    </div>\n' +
        '    <div>\n' +
        '    <div><img src="'+seller3+'"/></div>\n' +
        '    <div><img src="/dress/image/i4.png" height="50" width="50"/></div>\n' +
        '    <div>\n' +
        '    <div>\n' +
        '    <div>'+manMessage3+'</div>\n' +
        '<div>'+femaleMessage3+'</div>\n' +
        '</div>\n' +
        '</div>\n' +
        '</div>';
    $("#content").html(html);
}
// 新闻
function setNews(data) {
    let html='';
    for (let i = 0; i <data.length ; i++) {
        html +='<div>\n' +
            '        <div class="fh">+</div>\n' +
            '        <div>'+data[i].title+'</div>\n' +
            '        <div>'+data[i].createTime+'</div>\n' +
            '  </div>';
    }
    $("#newsList").html(html);
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

function login() {
    location.href = "/html/login.html";
}
function leaveWords() {
    location.href = "/dress/html/more.html";
}

