$(function () {

});

/**
 * 用户列表查询
 * @param data
 */
function findAll(data) {
    $.ajax({
        url: '/back/user/all',
        data: data,
        type: 'get',
        dataType: 'json',
        success: function (res) {
            console.log(res);
            setList(res.data);
        }
    });
}
function getFndAll(data) {
    $.ajax({
        url: '/back/user/fndAll',
        data: data,
        type: 'get',
        dataType: 'json',
        success: function (res) {
            console.log(res);
            setList(res.data);
        }
    });
}
/**
 * 用户列表
 * @param data
 */
function setList(data) {
            let html = '';
            for (let i = 0; i < data.length; i++) {

                html += '<tr>\n' +
                    '    </td>\n' +
                    '    <td>' + data[i].id + '</td>\n' +
                    '    <td>' + data[i].username + '</td>\n' +
                    '    <td>' + sex(data[i].sex) + '</td>\n' +
                    '    <td>' + data[i].phone + '</td>\n' +
                    '    <td>' + data[i].email + '</td>\n' +
                    '    <td>' + data[i].hobby + '</td>\n' +
                    '    <td>' + data[i].birthday + '</td>\n' +
                    '<td class="td-status">\n' +
                    ' <span class="layui-btn layui-btn-normal layui-btn-mini" '+(data[i].state==1?'':'layui-btn-disabled')+' id="state">'+(data[i].state==1?'启用':'禁用')+'</span>\n' +
                    ' </td>\n' +
                    '<td class="td-manage">\n' +
                    '    <a onclick="member_stop(this,'+ data[i].id +','+ data[i].state +')" href="javascript:;"  title="'+(data[i].state==1?'启用':'禁用')+'">\n' +
                    '    <i class="layui-icon">'+(data[i].state==1?'&#xe601;':'&#xe62f;')+'</i>\n' +
                    '</a>\n' +
                    '<a title="编辑"  onclick="edit('+ data[i].id +')" href="javascript:;">\n' +
                    '    <i class="layui-icon">&#xe642;</i>\n' +
                    '</a>\n' +
                    '<a onclick="editPass('+ data[i].id +')" title="修改密码" href="javascript:;">\n' +
                    '    <i class="layui-icon">&#xe631;</i>\n' +
                    '</a>\n' +
                    '<a title="删除" onclick="member_del(this,'+ data[i].id +')" href="javascript:;">\n' +
                    '    <i class="layui-icon">&#xe640;</i>\n' +
                    '</a>\n' +
                    '</td>\n' +
                    '</tr>';
            }
            $("#tbody").html(html)
        }




/**
 * 修改
 * @param id
 */
function edit(id) {
    sessionStorage.setItem("userId", id);
    xadmin.open('编辑', 'member-edit.html', 600, 400);
}

function editPass(id) {
    sessionStorage.setItem("userId", id);
    xadmin.open('修改密码','member-password.html',600,400)
}
/**
 * 性别判断
 * @param sex
 * @returns {string}
 */
function sex(sex) {
    if (sex==1){
        return "<span class=\"layui-btn layui-btn-normal layui-btn-mini\" id=\"sex\">男</span>";
    }else if (sex==2){
        return "<span class=\"layui-btn layui-btn-success layui-btn-mini\" id=\"sex\">女</span>";
    }
    return "<span class=\"layui-btn layui-btn-danger layui-btn-mini\" id=\"sex\">保密</span>";
}

/**
 * 分页
 * @param data
 */
function page(data) {
    layui.use('laypage', function () {
        let laypage = layui.laypage
                //完整功能
                laypage.render({
                    elem: 'page',
                    count: myAjax("/back/user/status", data, 'get').count,
                    layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
                    jump: function (obj) {
                        console.log(obj)
                        data.page=obj.curr;
                        data.limit=obj.limit;
                        findAll(data);
                    }
             });
        });
}

function pages(data) {
    layui.use('laypage', function () {
        let laypage = layui.laypage

        //完整功能
        laypage.render({
            elem: 'page',
            count: myAjax("/back/user/status", data, 'get').count,
            layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip'],
            jump: function (obj) {
                console.log(obj)
                data.page=obj.curr;
                data.limit=obj.limit;
                getFndAll(data);
            }
        });
    });
}

layui.use( ['form'], function () {
    let form = layui.form;
    page({});
    //监听提交
    form.on('submit(sreach)',
        function (data) {
            data = data.field;
            console.log(data)
            let arr = [];
            $('input:checkbox[name=hobby]:checked').each(function () {
                arr.push($(this).attr("title"));
            });
            data.hobby = arr.toLocaleString();
            console.log(data);
            //查询所有
            // pageFndAll(data);
                page(data)
            return false;
        });

    // pageFndAll({})
});

layui.use(['laydate','form'], function(){
    var laydate = layui.laydate;
    var  form = layui.form;


    // 监听全选
    form.on('checkbox(checkall)', function(data){

        if(data.elem.checked){
            $('tbody input').prop('checked',true);
        }else{
            $('tbody input').prop('checked',false);
        }
        form.render('checkbox');
    });

    //执行一个laydate实例
    laydate.render({
        elem: '#start' //指定元素
    });

    //执行一个laydate实例
    laydate.render({
        elem: '#end' //指定元素
    });


});



/*用户-停用*/
function member_stop(obj,id,state){
    layer.confirm('确认要'+(state==1?'启用':'禁用')+'吗？', function (index) {
    layui.use(['laydate','form'], function(){
        var laydate = layui.laydate;
        var  form = layui.form;
        //发异步把用户状态进行更改
    $.ajax({
        url: '/back/user/state',
        data: {id: id},
        type: 'post',
        dataType: 'json',
        success: function (res) {
            console.log(res);
            if (res.count == 1) {
                if ($(obj).attr('title') == '启用') {
                    $(obj).attr('title', '停用')
                    $(obj).find('i').html('&#xe62f;');

                    $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('禁用');
                    layer.msg('已停用!', {icon: 5, time: 1000});

                } else {
                    $(obj).attr('title', '启用')
                    $(obj).find('i').html('&#xe601;');

                    $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('启用');
                    layer.msg('已启用!', {icon: 6, time: 1000});
                }
            }
           }
    })
    })
    });
}

/*用户-删除*/
function member_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
        //发异步删除数据

        $.ajax({
            url: '/back/user/remove',
            data: {id: id},
            type: 'post',
            dataType: 'json',
            success: function (res) {
                console.log(res);
                if (res.count == 1) {
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!', {icon: 1, time: 1000});
                    pages(res);
                } else {
                    layer.msg('删除失败!', {icon: 2, time: 1000});
                }

            }
        });
})
}



function delAll (argument) {
    var ids = [];

    // 获取选中的id
    $('tbody input').each(function(index, el) {
        if($(this).prop('checked')){
            ids.push($(this).val())
        }
    });

    layer.confirm('确认要删除吗？'+ids.toString(),function(index){
        //捉到所有被选中的，发异步进行删除
        layer.msg('删除成功', {icon: 1});
        $(".layui-form-checked").not('.header').parents('tr').remove();
    });
}