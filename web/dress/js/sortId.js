$(function () {
    findSortAll();
});
function findSortAll() {
    $.ajax({
        url: '/sort/findAll',
        data: '',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            console.log(data);
            setSortData(data.data);
        }
    })
}
function setSortData(data) {
    let html='';
    for (let i = 0; i < data.length; i++) {
        html+='<div class="back'+i+'" onclick="findNews('+data[i].id+')">'+data[i].name+'</div>';
    }
    $("#sort").html(html);
//    假如现在需要对第二个进行背景颜色的设置

}