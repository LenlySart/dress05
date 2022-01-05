layui.use(['laypage', 'layer'], function () {
    var laypage = layui.laypage
        , layer = layui.layer;
    page();
})


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

let typeId=-1;
function findChangeType(id) {
    // 重新赋值typeId
    typeId=id;
    page();

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

// 产品展示
function setProductList(data) {
    let productList = '';

    data.forEach(function (value) {
        // console.log( value);
        productList += '<div class="box">\n' +
            '              <div onclick="goToDetail(' + value.id + ')"><img src="' + value.imgHref + '"/></div>\n' +
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
    location.href="/dress/html/productDetail.html";
}