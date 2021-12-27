layui.use('table', function () {
    let table = layui.table,
        form = layui.form;
    //监听工具条
    table.on('tool(demo)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {

            // layer.msg('ID：'+ data.id + ' 的查看操作');
        } else if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                let res = myAjax("/back/user/remove", {id: data.id});
                if (res.count == 1) {
                    obj.del();
                    layer.close(index);
                } else {
                    layui.msg("删除失败");
                }

            });
        } else if (obj.event === 'edit') {
            sessionStorage.setItem("userId", data.id);
            xadmin.open('编辑', 'member-edit.html', 600, 400);

        }
    });
    table.render({
        elem: '#test'
        , url: '/back/user/all'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , page: true
        , cols: [[
            {field: 'id', width: 80, title: 'ID', sort: true}
            , {field: 'username', width: 180, title: '用户名'}
            , {field: 'sex', width: 80, title: '性别', sort: true,templet: function (data) {
                    return setSexData(data);
                }}

            , {field: 'phone', width: 150, title: '电话'}
            , {field: 'birthday', title: '生日', minWidth: 100} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
            , {
                field: 'state', title: '状态', width: 150, templet: function (data) {
                    return '<span class="layui-btn layui-btn-normal layui-btn-mini ' + (data.state == 1 ? '' : 'layui-btn-disabled') + ' ">' + (data.state == 1 ? '已启用' : '已停用') + '</span>';
                }
            }
            , {field: 'state', title: '状态', templet: '#switchTpl'}
            , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barDemo'}
        ]]
    });
    //监听锁定操作
    form.on('switch(enableDemo)', function (obj) {
        console.log(obj.elem);
        let res = myAjax("/back/user/state", {id: this.value});
        layer.tips(this.value + ' ' + this.name + '：' + obj.elem.checked, obj.othis);
    });
});

function setSexData(data) {
    let sex = data.sex
    if (sex==1){
        return "男";
    }else if (sex==2){
        return "女";
    }
    return "保密";
}


