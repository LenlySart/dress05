layui.use(['laypage', 'layer'], function () {
    var laypage = layui.laypage
        , layer = layui.layer;
    page();
})
let sortId=-1;
function findChangeSortId(id) {
    // 重新赋值sortId
    sortId=id;
    page();

}

function page() {
    layui.use(['laypage', 'layer'], function () {
        var laypage = layui.laypage
            , layer = layui.layer;
        let count = getNewsCount();
        console.log(count)
        //总页数低于页码总数
        laypage.render({
            elem: 'demo'
            , count: count
            , async: false//关闭异步
            , limit: 8,
            jump: function (obj) {
                console.log(obj)
                let data = {page: obj.curr, limit: obj.limit, sortId:sortId};
                findNews(data);
            }

        });
    });
}
function getNewsCount() {
    let count = 0;
    $.ajax({
        url: '/news/count',
        data: {sortId:sortId},
        type: 'get',
        async: false,
        dataType: 'json',
        success: function (res) {
            console.log(res);
            count = res.count;
        }
    });
    return count;
}

function findNews(data) {
    $.ajax({
        url: "/news",
        type:'get',
        data: data,
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
    data.forEach(function (value) {
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
    location.href="/dress/html/newsDetail.html";
}