<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../static/lib/layui-src/css/layui.css" media="all">
    <link rel="stylesheet" href="../static/css/public.css" media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>


<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>常规使用：普通图片上传</legend>
</fieldset>

<div class="layui-upload">
    <button type="button" class="layui-btn" id="test1">上传图片</button>
    <div class="layui-upload-list">
        <img class="layui-upload-img" id="demo1">
        <p id="demoText"></p>
    </div>
</div>

<%--<div class="layui-upload">--%>
<%--    <button type="button" class="layui-btn" id="test1">上传图片</button>--%>
<%--    <div class="layui-upload-list">--%>
<%--        <img class="layui-upload-img" id="demo1">--%>
<%--        <p id="demoText"></p>--%>
<%--    </div>--%>
<%--    <div style="width: 95px;">--%>
<%--        <div class="layui-progress layui-progress-big" lay-showpercent="yes" lay-filter="demo">--%>
<%--            <div class="layui-progress-bar" lay-percent=""></div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>


<script src="../static/lib/layui-src/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->
<script>
    layui.use(['upload', 'element', 'layer'], function(){
        var $ = layui.jquery
            ,upload = layui.upload
            ,element = layui.element
            ,layer = layui.layer;

        //常规使用 - 普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            ,url: 'uploadPost' //改成您自己的上传接口
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });

                element.progress('demo', '0%'); //进度条复位
                layer.msg('上传中', {icon: 16, time: 0});
            }
            ,done: function(res){
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功的一些操作
                //……
                $('#demoText').html(''); //置空上传失败的状态
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
            //进度条
            ,progress: function(n, elem, e){
                element.progress('demo', n + '%'); //可配合 layui 进度条元素使用
                if(n == 100){
                    layer.msg('上传完毕', {icon: 1});
                }
            }
        });


    });
</script>

</body>
</html>
