$(function () {
    findAll();
})

function findAll() {
    $.ajax({
        url: "/product",
        type:'get',
        data: '',
        dataType: 'json',
        success: function (data) {
            console.log(data);
            setNavModel(data.data.navModel);
        }
    });
}

function setNavModel(data) {
    let html='';
    for (let i = 0; i <data.length ; i++) {
        html +=' <div onclick="window.location.href=\''+data[i].href+'\'">'+data[i].title+'</div>'
    }
    $("#navModel").html(html);
}