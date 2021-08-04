<%--
  Created by IntelliJ IDEA.
  User: Blssdr
  Date: 2021/6/14
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>列表页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../static/lib/layui-src/css/layui.css" media="all">
    <link rel="stylesheet" href="../static/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">账号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="account" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="name" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">班级</label>
                            <div class="layui-input-inline">
                                <input type="text" name="classes" autocomplete="off" class="layui-input">
                            </div>
                        </div>
<%--                        <div class="layui-inline">--%>
<%--                            <label class="layui-form-label">用户职业</label>--%>
<%--                            <div class="layui-input-inline">--%>
<%--                                <input type="text" name="classify" autocomplete="off" class="layui-input">--%>
<%--                            </div>--%>
<%--                        </div>--%>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary"  lay-submit lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="edit"> 修改 </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>
<%--                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 编辑 </button>--%>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>

    </div>
</div>
<script src="../static/lib/layui-src/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        table.render({
            elem: '#currentTableId',
            url: 'query',
            method:'post',
            contentType:'application/json',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print'],
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 80, title: 'ID', sort: true},
                {field: 'account', width: 150, title: '账号'},
                {field: 'name', width: 120, title: '姓名'},
                {field: 'sex', width: 80, title: '性别'},
                {field: 'classes', width: 150, title: '班级'},
                {field: 'phone', width: 120, title: '电话'},
                {field: 'state', width: 80, title: '状态'},
                {field: 'roomName', width: 120, title: '房间号'},
                {field: 'startTime', width: 150, title: '入住时间'},
                {field: 'endTime', width: 150, title: '离开时间'},
            ]],
            // templet: '<div>{{d.room.name}}</div>'
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,
            page: true,
            skin: 'line'
        });

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            var result = JSON.stringify(data.field);
            layer.alert(result, {
                title: '最终的搜索信息'
            });

            //执行搜索重载
            table.reload('currentTableId', {
                page: {
                    curr: 1
                },
                contentType:'application/json',
                where: {
                    account:data.field.account,
                    name:data.field.name,
                    classes:data.field.classes
                }
            }, 'data');

            return false;
        });

        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '添加用户',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: 'add',
                    end:function(){
                        table.reload('currentTableId')
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                var arr = [];
                for (index in data){
                    arr.push(data[index].id);
                }
                if (arr.length <= 0){
                    layer.msg("请选择一行进行删除")
                    return;
                }

                $.ajax({
                    url:'delete',
                    type:'POST',
                    data:"ids="+arr.join(","),
                    dataType:'json',
                    success:function (data) {
                        layer.msg(data.msg,{
                            time:1000
                        },function () {
                            table.reload('currentTableId')
                        })
                    }
                })
            } else if (obj.event === 'edit') {  // 监听修改操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                var arr = [];
                for (index in data){
                    arr.push(data[index].id);
                }
                if (arr.length != 1){
                    layer.msg("请选择一行进行修改")
                    return;
                }

                var index = layer.open({
                    title: '编辑用户',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: 'edit?id=' + arr[0],
                    end:function(){
                        table.reload('currentTableId')
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {

                var index = layer.open({
                    title: '编辑用户',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '../page/table/edit.html',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            }
        });

    });
</script>

</body>
</html>
