<%--
  Created by IntelliJ IDEA.
  User: Blssdr
  Date: 2021/6/17
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../static/lib/layui-src/css/layui.css" media="all">
    <link rel="stylesheet" href="../static/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <blockquote class="layui-elem-quote layui-text">
            添加用户
<%--            鉴于小伙伴的普遍反馈，先温馨提醒两个常见“问题”：1. <a href="/doc/base/faq.html#form" target="_blank">为什么select/checkbox/radio没显示？</a> 2. <a href="/doc/modules/form.html#render" target="_blank">动态添加的表单元素如何更新？</a>--%>
        </blockquote>

        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>用户属性</legend>
        </fieldset>

        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">账户</label>
                <div class="layui-input-block">
                    <input type="text" name="account" lay-verify="required" autocomplete="off" placeholder="请输入账户" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="text" name="password" lay-verify="required" autocomplete="off" placeholder="请输入密码" value="123456" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入姓名" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-block">
                    <input type="text" name="phone" lay-verify="phone" autocomplete="off" placeholder="请输入电话" value="19858111111" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea name="remark" lay-verify="remark" autocomplete="off" placeholder="请输入备注" class="layui-textarea"></textarea>
                </div>
            </div>
            <!--<div class="layui-form-item layui-form-text">
              <label class="layui-form-label">编辑器</label>
              <div class="layui-input-block">
                <textarea class="layui-textarea layui-hide" name="content" lay-verify="content" id="LAY_demo_editor"></textarea>
              </div>
            </div>-->
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="../static/lib/layui-src/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['form', 'jquery','layer'], function () {
        var form = layui.form
            , $ = layui.jquery
            , layer = layui.layer;

        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

        //监听提交
        form.on('submit(demo1)', function (data) {
            // layer.alert(JSON.stringify(data.field), {
            //     title: '最终的提交信息'
            // })

            $.ajax({
                url:'create',
                type:'POST',
                contentType:'application/json',
                data:JSON.stringify(data.field),
                dataType:'json',
                success:function (data) {
                    layer.msg(data.msg,{
                        time:1000
                    },function () {
                        parent.layer.close(index);
                    })
                    console.log("成功");
                    // location.href = 'create'
                },
                error:function (data) {
                    layer.msg(data.msg)
                    console.log("失败");
                }
            })



            return false;
        });

        //表单初始赋值
        form.val('example', {
            "username": "贤心" // "name": "value"
            , "password": "123456"
            , "interest": 1
            , "like[write]": true //复选框选中状态
            , "close": true //开关状态
            , "sex": "女"
            , "desc": "我爱 layui"
        })


    });
</script>

</body>
</html>
