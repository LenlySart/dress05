$(function () {
    let id = sessionStorage.getItem("newsId");
    findById(id);
});

function findById(id) {

    $.ajax({
        url: '/news/findById',
        data: {id: id},
        type: 'get',
        dataType: 'json',
        success: function (data) {
            //如果我们当前拿到的数据不是中间的数据，
            // 如1，或者最后一个数据的id，那么传递过来的数据条数可能为2条
            console.log(data)
            // setDetailData(data.data[1]);
            if (data.data.length == 1) {

                setLastData({});
                setDetailData(data.data[0]);
                setNextData({});
            }
            if (data.data.length == 3) {

                setLastData(data.data[0]);
                setDetailData(data.data[1]);
                setNextData(data.data[2]);
            }
            //如果由两条的情况，表示要么得到的id为第一条，要么得到的id为最后一条

            if (data.data.length == 2) {
                for (let i = 0; i < data.data.length; i++) {
                    //表示第一条为无，查询出来只有两条数据，
                    // 第一条数据的id等于当前查询的id
                    if (i == 0 && id == data.data[0].id) {
                        setLastData({});
                        setDetailData(data.data[0]);
                        setNextData(data.data[1]);
                    } else {
                        setLastData(data.data[0]);
                        setDetailData(data.data[1]);
                        setNextData({});
                    }

                }
                }
          }
    })
}
function setDetailData(data) {
    $('#title').html(data.title);
    $('#author').html('作者：'+data.author);
    $('#createTime').html('发布时间：'+data.createTime);
    $('#pageView').html(data.pageView+'次浏览');
    $('#nAbstract').html(data.nAbstract);
    $('#content').html(data.content);
}

function setLastData(data) {
    let html = '<div>上一篇：</div>\n' +
        '                            <div onclick="findById(' + (data.id == undefined ? '0' : data.id) + ')">' + (data.title == undefined ? '无' : data.title) + '</div>';
    $("#next").html(html);
}

function setNextData(data) {
    let html = '<div>下一篇：</div>\n' +
        '                            <div onclick="findById(' + (data.id == undefined ? '0' : data.id) + ')">' + (data.title == undefined ? '无' : data.title) + '</div>';
    $("#last").html(html);
}