
layui.use('table', function() {
    let table = layui.table,
        form = layui.form;
    table.render({
        elem: '#message'
        , url: '/back/product/productMessage'
        , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
            title: '提示'
            , layEvent: 'LAYTABLE_TIPS'
            , icon: 'layui-icon-tips'
        }]
        , title: '用户数据表'
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', width: 80, fixed: 'left', unresize: true, sort: true}
            , {field: 'seller', title: '卖家秀', width: 150, templet: '<div><img src="{{d.seller}}"></div>'}
            , {field: 'manMessage', title: '卖家留言'}
            , {field: 'femaleMessage', title: '卖家留言'}
            , {field: 'state', title: '状态', width: 80, templet: '#switchTpl', unresize: true}
            , {field: 'createTime', title: '创建时间', width: 106}
            , {field: 'updateTime', title: '修改时间', width: 106}
            , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 163}
        ]]
        , page: true
    });
})