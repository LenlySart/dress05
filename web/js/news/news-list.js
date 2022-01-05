layui.use(['form', 'table', 'util'], function () {
    var table = layui.table;
    let form = layui.form
        , util = layui.util
        ,laydate = layui.laydate;

    NewsData({});
    //监听行工具事件
    table.on('tool(test)', function (obj) {
        var data = obj.data;
        sessionStorage.setItem("newsId", data.id);
        if (obj.event === 'del') {
            layer.confirm('确认要删除吗？', function (index) {
                //发异步删除数据
                $.ajax({
                    url: '/back/news/remove',
                    data: {id: data.id},
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        console.log(res);
                        if (res.count === 1) {
                            $(obj).parents("tr").remove();
                            layer.msg('已删除!', {icon: 1, time: 1000});
                            obj.del();
                            layer.close(index);
                        } else {
                            layer.msg('删除失败!', {icon: 2, time: 1000});
                        }

                    }
                });
            })
        } else if (obj.event === 'edit') {
            sessionStorage.setItem("newsId", data.id);
            xadmin.open('编辑新闻', '/html/news/news-update.html', 600, 400);
        } else if (obj.event === 'detail') {
            sessionStorage.setItem("newsId", data.id);
            xadmin.open('查看新闻', '/html/news/news-view.html', 600, 400);
        }

    });

    //监听单元格编辑
    table.on('edit(test)', function (obj) {
        var value = obj.value //得到修改后的值
            , data = obj.data //得到所在行所有键值
            , field = obj.field; //得到字段
        $.ajax({
            url: '/back/news/updateField',//对应的是Java servlet里面的注解地址
            data: {id: data.id, field: field, value: util.escape(value)},// 要传递给Java的数据，统一以对象的形式传递{}
            type: 'post',//对应的是servlet里面的方法,除了查询用get其他都用post
            dataType: 'json',//java传递到前端的数据统一格式json
            success: function (res) {// Java后台给前端的返回信息，res就是返回的结果
                console.log(res);
                if (res.msg === "error") {
                    layer.msg("更新失败");
                } else {
                    layer.msg("更新成功");
                }
            }
        });
        // layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改值为：'+ util.escape(value));
    });
    laydate.render({
        elem: '#start',
        trigger: 'click'
    });
    laydate.render({
        elem: '#end',
        trigger: 'click'
    });
    //监听提交
    form.on('submit(sreach)',
        function (data) {
            data = data.field;
            console.log(data)
            //查询所有
            NewsData(data);
            return false;
        });

    //监听状态操作
    form.on('switch(stateDemo)', function (obj) {
        console.log(obj.elem);
        let data = {id: this.value, state: obj.elem.checked ? 1 : 0};
        myAjax("/back/news/state", data);
        layer.tips(this.value + ' ' + this.name + '：' + obj.elem.checked, obj.othis);
    });
    //监听置顶操作
    form.on('switch(isShowDemo)', function (obj) {
        console.log(obj.elem);
        let data = {id: this.value, isShow: obj.elem.checked ? 1 : 0};
        myAjax("/back/news/isShow", data);
        layer.tips(this.value + ' ' + this.name + '：' + obj.elem.checked, obj.othis);
    });
    //监听热点操作
    form.on('switch(isRecommendDemo)', function (obj) {
        console.log(obj.elem);
        let data = {id: this.value, isRecommend: obj.elem.checked ? 1 : 0};
        myAjax("/back/news/isRecommend", data);
        layer.tips(this.value + ' ' + this.name + '：' + obj.elem.checked, obj.othis);
    });
});

function NewsData(data) {
    layui.use(['form', 'table', 'util'], function () {
        var table = layui.table;

        //渲染到表格
        table.render({
            elem: '#test'
            , url: '/back/news/findAll'// 后台servlet的注解地址
            , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                , layEvent: 'LAYTABLE_TIPS'
                , icon: 'layui-icon-tips'
            }]
            , where: data
            , title: '用户数据表'
            , cols: [[
                {field: 'id', title: 'ID', width: 80, fixed: 'left', unresize: true, sort: true}
                , {field: 'title', title: '标题', width: 150, edit: 'text'}
                , {field: 'nAbstract', title: '摘要', edit: 'text'}
                , {field: 'content', title: '内容', edit: 'text'}
                , {field: 'state', title: '状态', width: 80, templet: '#switchTpl', unresize: true}
                , {field: 'isShow', title: '置顶', width: 80, templet: '#switchIsShow', unresize: true}
                , {field: 'isRecommend', title: '热点', width: 80, templet: '#switchIsRecommend', unresize: true}
                , {field: 'createTime', title: '创建时间', width: 106}
                , {field: 'updateTime', title: '更新时间', width: 106}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 190}
            ]]
            , page: true//开启分页
        });
    });
}
