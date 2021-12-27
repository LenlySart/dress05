layui.use(['laypage', 'layer'], function () {
    var laypage = layui.laypage
        , layer = layui.layer;
    page();
})

$(function () {
    finAll();
});
let typeId=-1;
function findProductAll(data) {
    $.ajax({
        url: "/product/findAll",
        type: 'get',
        data: data,
        async: false,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            setProductList(data.data);

        }
    });
}

function finAll() {
    $.ajax({
        url: "/page",
        type: 'get',
        data: '',
        async: false,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            setNavs(data.data.nevModel);
            setBridalVeil(data.data.bridalVeil);
            setRodetList(data.data.rodeList);
            setBlazerList(data.data.blazerList);
            setTailoredList(data.data.tailoredList);
        }
    });
}

function page() {
    layui.use(['laypage', 'layer'], function () {
        var laypage = layui.laypage
            , layer = layui.layer;
        let count = getProductPageCount();
        console.log(count)
        //总页数低于页码总数
        laypage.render({
            elem: 'demo'
            , count: count
            , async: false//关闭异步
            , limit: 8,
            jump: function (obj) {
                console.log(obj)
                let data = {page: obj.curr, limit: obj.limit, typeId: typeId};
                findProductAll(data);
            }

        });
    });
}
function getProductPageCount() {
    let count = 0;
    $.ajax({
        url: '/product/count',
        data: {typeId: typeId},
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

// 产品导航
function setNavs(data) {
    let navModel = '';
    for (let i = 0; i < data.length; i++) {
        navModel += ' <div onclick="window.location.href=\'' + data[i].href + '\'">' + data[i].title + '</div>';
    }
    $("#navModel").html(navModel);
}

// 婚纱
function setBridalVeil(data) {
    let bridalVeil = '';
    for (let i = 0; i < data.length; i++) {
        bridalVeil += '<div class="box">\n' +
            '              <div><img src="' + data[i].imgHref + '"/></div>\n' +
            '              <div class="in_texts" onclick="goToDetail(' +data[i].id + ')">' + data[i].name + '</div>\n' +
            '              <div class="in_text">\n' +
            '                     <div>价格：</div>\n' +
            '                     <div>' + data[i].markedPrice + '</div>\n' +
            '              </div>\n' +
            '         </div>';
    }
    $("#bridalVeil").html(bridalVeil);
}

// 礼服
function setRodetList(data) {
    let rodeList = '';
    for (let i = 0; i < data.length; i++) {
        rodeList += '<div class="box">\n' +
            '              <div><img src="' + data[i].imgHref + '"/></div>\n' +
            '              <div class="in_texts" onclick="goToDetail(' + data[i].id + ')">' + data[i].name + '</div>\n' +
            '              <div class="in_text">\n' +
            '                     <div>价格：</div>\n' +
            '                     <div>' + data[i].markedPrice + '</div>\n' +
            '              </div>\n' +
            '         </div>';
    }
    $("#rodeList").html(rodeList);
}

// 西装
function setBlazerList(data) {
    let blazerList = '';
    for (let i = 0; i < data.length; i++) {
        blazerList += '<div class="box">\n' +
            '              <div><img src="' + data[i].imgHref + '"/></div>\n' +
            '              <div class="in_texts" onclick="goToDetail(' + data[i].id + ')">' + data[i].name + '</div>\n' +
            '              <div class="in_text">\n' +
            '                     <div>价格：</div>\n' +
            '                     <div>' + data[i].markedPrice + '</div>\n' +
            '              </div>\n' +
            '         </div>';
    }
    $("#blazerList").html(blazerList);
}

// 定制
function setTailoredList(data) {
    let tailoredList = '';
    for (let i = 0; i < data.length; i++) {
        tailoredList += '<div class="box">\n' +
            '              <div><img src="' + data[i].imgHref + '"/></div>\n' +
            '              <div class="in_texts" onclick="goToDetail(' + data[i].id + ')">' + data[i].name + '</div>\n' +
            '              <div class="in_text">\n' +
            '                     <div>价格：</div>\n' +
            '                     <div>' + data[i].markedPrice + '</div>\n' +
            '              </div>\n' +
            '         </div>';
    }
    $("#tailoredList").html(tailoredList);
}

// 产品展示
function setProductList(data) {
    let productList = '';

    data.forEach(function (value, index) {
        // console.log( value);
        productList += '<div class="box">\n' +
            '              <div><img src="' + value.imgHref + '"/></div>\n' +
            '              <div class="in_texts" onclick="goToDetail(' + value.id + ')">' + value.name + '</div>\n' +
            '              <div class="in_text">\n' +
            '                     <div>价格：</div>\n' +
            '                     <div>' + value.markedPrice + '</div>\n' +
            '              </div>\n' +
            '         </div>';
    })
    $("#productList").html(productList);
}

function goToDetail(id) {
    sessionStorage.setItem("productId", id);
    window.open("/dress/html/productDetail.html");
}