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

<form class="layui-form goodsAddForm" action="" method="post">
    <div class="layui-upload" style="padding-top: 10px;padding-left: 10px">
        <button type="button" class="layui-btn layui-btn-normal" id="uploadPhotos">选择员工证件照（可多选）</button>
        <button type="button" class="layui-btn" id="allowUpload">开始上传</button>
        <div class="layui-upload-list">
            <table class="layui-table">
                <thead>
                <tr><th>文件名</th>
                    <th>预览图</th>
                    <th>大小</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr></thead>
                <tbody id="photoList"></tbody>
            </table>
        </div>
    </div>
</form>
<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/table.js"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/laypage.js"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/layer.js"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/laydate.js"></script>
</body>
<script>
    layui.use(['upload','layer'], function () {
        var $ = layui.jquery
            , upload = layui.upload;

        var demoListView = $('#photoList')
            ,uploadListIns = upload.render({
             elem: '#uploadPhotos'
            ,url: '${pageContext.request.contextPath}/admin/UploadAdminPhoto.do'
            ,accept: 'file'
            ,multiple: true
            ,auto: false
            ,bindAction: '#allowUpload'
            ,choose: function(obj){
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                //读取本地文件
                obj.preview(function(index, file, result){
                    var tr = $(['<tr id="upload-'+ index +'">'
                        ,'<td>'+ file.name +'</td>'
                        ,'<td><img src="' + result + '" alt="' + file.name +'"height="42px" width="30px" class="layui-upload-img uploadImgPreView"></td>'
                        ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                        ,'<td>等待上传</td>'
                        ,'<td>'
                        ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                        ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                        ,'</td>'
                        ,'</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function(){
                        obj.upload(index, file);
                    });

                    //删除
                    tr.find('.demo-delete').on('click', function(){
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });
                    demoListView.append(tr);
                });
            }
            ,done: function(res, index, upload){
                if(res.code === 0){ //上传成功
                    var tr = demoListView.find('tr#upload-'+ index)
                        ,tds = tr.children();
                    tds.eq(3).html('<span style="color: #5FB878;">'+res.msg+'</span>');
                    tds.eq(4).html(''); //清空操作
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }
                this.error(res, index, upload);
            }
            ,error: function(res, index, upload){
                var tr = demoListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(3).html('<span style="color: #FF5722;">'+res.msg+'</span>');
                tds.eq(4).find('.demo-reload').removeClass('layui-hide'); //显示重传
            }
        });
    });
</script>

</html>