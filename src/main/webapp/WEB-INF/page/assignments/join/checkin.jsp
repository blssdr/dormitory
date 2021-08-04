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
    <link rel="stylesheet" href="../../static/lib/layui-src/css/layui.css" media="all">
    <link rel="stylesheet" href="../../static/css/public.css" media="all">
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
                            <label class="layui-form-label">房间号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="name" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label">状态</label>
                            <div class="layui-input-inline" disabled="">
                                <select name="state" >
                                    <option value=""></option>
                                    <option value="待入住">待入住</option>
                                    <option value="有空位">有空位</option>
                                    <option value="已满">已满</option>
                                </select>
                            </div>
                        </div>

                        <%--                        <div class="layui-inline">--%>
                        <%--                            <label class="layui-form-label">状态</label>--%>
                        <%--                            <div class="layui-input-inline">--%>
                        <%--                                <input type="text" name="name" autocomplete="off" class="layui-input">--%>
                        <%--                            </div>--%>
                        <%--                        </div>--%>
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
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="sel"> 确定 </button>
                <%--                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 编辑 </button>--%>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="details">详情</a>
        </script>

    </div>
</div>
<script src="../../static/lib/layui-src/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        table.render({
            elem: '#currentTableId',
            url: 'queryRoom',
            method:'post',
            contentType:'application/json',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print'],
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 80, title: 'ID', sort: true},
                {field: 'name', width: 150, title: '房间号'},
                {field: 'roomLeader', width: 120, title: '寝室长'},
                {field: 'rules', width: 120, title: '限制人数'},
                {field: 'count', width: 120, title: '人数'},
                {field: 'state', width: 120, title: '状态'},
                {field: 'remark', minWidth: 200, title: '备注'},
                {title: '操作', width: 100, toolbar: '#currentTableBar', align: "center"}
            ]],
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
                    name:data.field.name,
                    state:data.field.state,
                }
            }, 'data');

            return false;
        });

        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
             if (obj.event === 'sel') {  // 监听确定操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                var arr = [];
                for (index in data){
                    arr.push(data[index].id);
                }
                if (arr.length != 1){
                    layer.msg("请选择一行数据")
                    return;
                }
                for (index in ${list}){
                    arr.push(${list}[index])
                }
                 console.log(arr)

                $.ajax({
                    url:'select',
                    type:'POST',
                    data:"ids="+arr.join(),
                    dataType:'json',
                    success:function (data) {
                        layer.msg(data.msg,{
                            time:1000
                        },function () {
                            table.reload('currentTableId')
                        })
                    }
                })
            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            // layer.alert(data, {
            //     title: '最终的搜索信息'
            // });
            console.log(data);
            // if (data.sta)
            // console.log(data.)
            if (obj.event === 'details') {

                var index = layer.open({
                    title: '编辑用户',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['80%', '80%'],
                    content: 'details',
                    // 'edit?id=' + arr[0]
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;

            }

        });

    });
</script>

</body>
</html>
