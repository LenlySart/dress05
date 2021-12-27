$(function () {
    findAbout();
})

function findAbout() {
    $.ajax({
        url: "/about",
        type:'get',
        data: '',
        async: false,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            setAbout(data.data.stylist);
            setStore(data.data.storeList);
            setStoreList(data.data.stores);
        }
    });
}

// 设计师
function setAbout(data) {
    let html='';
    for (let i = 0; i <data.length ; i++) {
        html +='<div class="box">\n' +
            '       <div><img src="'+data[i].headShot+'"/></div>\n' +
            '           <div class="b_title">\n' +
            '           <div></div>\n' +
            '           <div>设计师: '+data[i].stylist+'</div>\n' +
            '           <div>'+data[i].aboutDesigner+'</div>\n' +
            '      </div>\n' +
            '   </div>';
    }
    $("#stylist").html(html);
}

// 线下门店
function setStore(data) {
    let html='';
    html +='<div>线下门店地址</div>';
    for (let i = 0; i <data.length ; i++) {
        html +='<div>'+data[i].offlineStore+'</div>';
    }
    $("#storeList").html(html);
}

// 线下门店图片
function setStoreList(data) {
    let html='';
    for (let i = 0; i <data.length ; i++) {
        html +='<div><img src="'+data[i].storeImage+'"/></div>';
    }
    $("#stores").html(html);
}