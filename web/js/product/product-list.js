
layui.use('table', function(){
        let table = layui.table,
            form = layui.form;
            findTypeAll();
        table.render({
            elem: '#test'
            ,url:'/back/product/fndAll'
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '用户数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field:'name', title:'产品名', width:120}
                ,{field:'imgHref', title:'产品', width:150 ,templet: '<div><img src="{{d.imgHref}}"></div>'}
                ,{field:'markedPrice', title:'市价', width:80, sort: true}
                ,{field:'normalPrice', title:'常价', width:80, sort: true}
                ,{field:'title', title:'类型', width:80}
                ,{field:'isShow', title:'是否置顶', width:90,templet: '#switchIsShow', unresize: true}
                ,{field:'state', title:'状态', width:80,templet: '#switchTpl', unresize: true}
                ,{field:'temp', title:'创建时间', width:106}
                ,{field:'time', title:'修改时间',width:106}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo',width:163}
            ]]
            ,page: true
        });
        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            sessionStorage.setItem("productId", data.id);
            if(obj.event === 'del'){
                layer.confirm('确认要删除吗？',function(index){
                    //发异步删除数据
                    $.ajax({
                        url: '/back/product/remove',
                        data: {id: data.id},
                        type: 'post',
                        dataType: 'json',
                        success: function (res) {
                            console.log(res);
                            if (res.count == 1) {
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
            } else if(obj.event === 'edit'){
                sessionStorage.setItem("productId", data.id);
                xadmin.open('编辑产品', '/html/product/product-update.html', 600, 400);
            }else if (obj.event === 'detail'){
                sessionStorage.setItem("productId", data.id);
                xadmin.open('编辑产品', '/html/product/product-view.html', 600, 450);
            }

        });

    //监听状态操作
    form.on('switch(stateDemo)', function (obj) {
        console.log(obj.elem);
        let data = {id: this.value, state: obj.elem.checked ? 1 : 0};
        myAjax("/back/product/state", data);
        layer.tips(this.value + ' ' + this.name + '：' + obj.elem.checked, obj.othis);
    });
    //监听状态操作
    form.on('switch(isShowDemo)', function (obj) {
        let data = {id: this.value, isShow: obj.elem.checked ? 1 : 0};
        myAjax("/back/product/isShow", data);
        layer.tips(this.value + ' ' + this.name + '：' + obj.elem.checked, obj.othis);
    });
});

