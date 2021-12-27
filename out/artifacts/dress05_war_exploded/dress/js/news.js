$(function () {
    findNews(-1);
})

function findNews(sortId) {
    $.ajax({
        url: "/news",
        type:'get',
        data: {page: 1, limit: 8,sortId:sortId},
        dataType: 'json',
        success: function (data) {
            console.log(data);
            setNewsList(data.data);
        }
    });
}
// 新闻
function setNewsList(data) {
    console.log(data)
    let titles ='';
    let createTimes ='';
    data.forEach(function (value, index) {
        titles +='<div class="news_ct_text" onclick="getNewsData('+value.id+')">'+value.title+'</div>';
        createTimes +='<div>'+value.createTime+'</div>';
    })
    let exhibition='<div>'+data[0].title+'</div>\n' +
            '         <div>'+data[0].nAbstract+'\n' +
            '           <div class="news_content_til" onclick="getNewsData('+data[0].id+')">【详细】</div>\n' +
            '        </div>\n' +
            '        <div>'+data[0].createTime+'</div>';

    $("#titles").html(titles);
    $("#createTimes").html(createTimes);
    $("#exhibition").html(exhibition);
}
function getNewsData(id) {
    sessionStorage.setItem("newsId", id);
    window.open("/dress/html/newsDetail.html");
}