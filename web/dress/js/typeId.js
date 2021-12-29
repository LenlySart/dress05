$(function () {
    findTypeAll();
});
function findTypeAll() {
    $.ajax({
        url: '/type/findAll',
        data: '',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            console.log(data);
            setTypeData(data.data);
        }
    })
}
function setTypeData(data) {
    let html='';
    for (let i = 0; i < data.length; i++) {
        html+='<div onclick="findChangeType('+data[i].id+')">'+data[i].title+'</div>';
    }
    $("#navModel").html(html);
//    假如现在需要对第二个进行背景颜色的设置

}