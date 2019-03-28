<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/adminStyle.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/layui.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modules/laydate/default/laydate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modules/layer/default/layer.css">
</head>
<body>
<form class="layui-form" action="" style="width: 700px;height: 500px;">
    <div class="layui-row">
        <div class="layui-col-xs6">
            <div class="grid-demo grid-demo-bg1">
                <div style="padding-top: 20px">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">账 号</label>
                            <div class="layui-input-inline">
                                <input type="text" id="adminid" name="adminid" lay-verify="required" placeholder="请输入"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">密 码</label>
                            <div class="layui-input-inline">
                                <input name="adminpass" lay-verify="required" autocomplete="off" placeholder="请输入"
                                       class="layui-input" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">姓 名</label>
                            <div class="layui-input-inline">
                                <input name="adminname" lay-verify="required" autocomplete="off" placeholder="请输入"
                                       class="layui-input" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">部 门</label>
                            <div class="layui-input-inline">
                                <input name="departid" lay-verify="required" autocomplete="off" placeholder="请输入"
                                       class="layui-input" type="text">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-xs6">
            <div class="grid-demo">
                <div class="layui-upload" style="padding-left: 10px">
                    <div class="layui-upload-list">
                        <img class="layui-upload-img" style="height: 200px;width: 142.8px;" id="adminPhoto" src="">
                        <p id="demoText"></p>
                    </div>
                    <button type="button" class="layui-btn" id="uploadPhoto">修改头像</button>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">身份证 </label>
            <div class="layui-input-inline">
                <input name="adminiden" lay-verify="identity" autocomplete="off" placeholder="请输入" class="layui-input"
                       type="text">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">单选框</label>
            <div class="layui-input-block">
                <input type="radio" name="adminsex" value="男" title="男" checked="">
                <input type="radio" name="adminsex" value="女" title="女">
                <input type="radio" name="adminsex" value="禁" title="禁用" disabled="">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">手 机 </label>
            <div class="layui-input-inline">
                <input name="adminphone" lay-verify="required|phone" autocomplete="off" placeholder="请输入"
                       class="layui-input" type="tel">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">邮 箱 </label>
            <div class="layui-input-inline">
                <input name="adminmail" lay-verify="email" autocomplete="off" placeholder="请输入" class="layui-input"
                       type="text">
            </div>
        </div>
    </div>
    <div class="layui-inline">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="editAdmin">保存修改</button>
        </div>
    </div>
</form>
<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/table.js"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/laypage.js"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/layer.js"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/laydate.js"></script>
</body>
<script>
    layui.use(['form', 'layedit'] ,function(){
        var $ = layui.jquery
            ,form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit;
        var index = parent.layer.getFrameIndex(window.name); //修改成功的时候点击 确定 会关闭子窗口，这里获取一下子窗口
        form.render();
        //监听提交
        form.on('submit(editAdmin)', function(data){ //这块要跟表单中的lay-filter="editStudent"对应
            $.ajax({
                url: "${pageContext.request.contextPath}/admin/AddAdmin.do",
                type: "post",
                async: false,   //不要让它异步提交
                data: data.field,
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    if(data.msg === '1'){
                        layer.alert("添加成功",{icon: 1,time:2000},function () {
                            layer.close(layer.index);
                            window.parent.location.reload();    //重新加载父页面，进行数据刷新
                        });
                    } else{
                        layer.alert(data.msg,{icon: 2,time:2000});
                    }
                }
            });
            return false;
        });

    });
</script>
<script>
    layui.use('upload', function() {
        var $ = layui.jquery
            , upload = layui.upload;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#uploadPhoto'
            , url: '${pageContext.request.contextPath}/admin/UploadAdminPhoto.do'
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#adminPhoto').attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {
                //如果上传失败
                if(res.code === 0){
                    return layer.msg('头像修改成功');
                }
                this.error(res);
            }
            ,error: function(res){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">&#x4e0a;&#x4f20;&#x5931;&#x8d25;</span> <a class="layui-btn layui-btn-xs demo-reload">&#x91cd;&#x8bd5;</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });
    });
</script>
</html>