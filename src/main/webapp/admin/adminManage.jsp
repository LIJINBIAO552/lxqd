<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>肇庆学院离校清单超管中心</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modules/laydate/default/laydate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modules/layer/default/layer.css">
</head>
<body>
<div class="demoTable" style="padding-top: 12px;padding-left: 12px">
    搜索学工号：
    <div class="layui-inline">
        <input class="layui-input" name="adminid" id="adminid" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>
    <button type="button" class="layui-btn" id="ImportAdminToDB"><i class="layui-icon"></i>批量导入管理员</button>
    <button class="layui-btn" onclick="downloadAdminfile()">全部导出</button>
</div>
<!-- 内容主体区域 -->
<table id="demo" lay-filter="test"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/table.js"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/laypage.js"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/layer.js"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/laydate.js"></script>

<script>
    //JavaScript代码区域
    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel','jquery', 'upload', 'element', 'slider'], function () {
        var laydate = layui.laydate //日期
            , laypage = layui.laypage //分页
            , layer = layui.layer //弹层
            , table = layui.table //表格
            , carousel = layui.carousel //轮播
            , upload = layui.upload //上传
            , element = layui.element //元素操作
            , slider = layui.slider //滑块
        //向世界问个好
        layer.msg('Hello World');

        //指定允许上传的文件类型
        upload.render({
            elem: '#ImportAdminToDB'
            ,url: '${pageContext.request.contextPath}/admin/ImportAdminToDB.do'
            ,accept: 'file' //普通文件
            ,done: function(res){
                //如果导入成功
                if(res.code === 0){
                    return layer.msg(res.msg);
                    window.location.reload('${pageContext.request.contextPath}/admin/selectAllAdminBylimit.do');
                }
                this.error(res);
            }
            ,error: function(res){
                //如果导入失败
                return layer.msg(res.msg);
            }
        });

        //监听Tab切换
        element.on('tab(demo)', function (data) {
            layer.tips('切换了 ' + data.index + '：' + this.innerHTML, this, {
                tips: 1
            });
        });

        //执行一个 table 实例
        table.render({
            elem: '#demo'
            , height: 745
            , url: '${pageContext.request.contextPath}/admin/selectAllAdminBylimit.do' //数据接口
            , title: '管理员用户表'
            , page: true //开启分页
            , toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            , cols: [[ //表头
                //sort排序，
                {type: 'checkbox', fixed: 'left'}
                , {field: 'adminid', title: '工号', sort: true, fixed: 'left',}
                , {field: 'adminpass', title: '密码'}
                , {field: 'adminname', title: '姓名', width: 80, sort: true,}
                , {field: 'adminsex', title: '性别', width: 80, sort: true}
                , {field: 'departid', title: '部门编号', width: 120, sort: true,}
                , {field: 'department', title: '部门名称', width: 200, templet: '<div>{{d.department.departname}}</div>'}
                , {field: 'adminiden', title: '身份证号', width: 200}
                , {field: 'adminphone', title: '手机号',}
                , {field: 'adminmail', title: '邮箱地址', width: 200, sort: true,}
                , {field: 'adminphoto', title: '照片地址', sort: true,}
                , {fixed: 'right', width: 165, align: 'center', toolbar: '#barDemo'}
            ]]
            , id: 'testReload',
        });

        //监听头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id)
                , data = checkStatus.data //获取选中的数据
            ,editList=[]; //存放获取到的那条json数据中的value值（不放key）
            for(var i=0;i<data.length;i++){ //因为这块获取的是数组，所以当前行数据应该为数组中的第一条，所以要遍历数组
                $.each(data[i],function(name,value) {
                    editList.push(value);
                    // console.log(name+"...."+value);
                });
            }
            switch (obj.event) {
                case 'add':
                    //脚本编辑弹出层
                    var name = encodeURIComponent(data.toolName);
                    layer.open({
                        type: 2,
                        title: '添加管理员信息',
                        shadeClose: true,
                        shade: 0.8,
                        maxmin: true,
                        area: ['38%', '60%'],
                        content: '${pageContext.request.contextPath}/admin/adminAdd.jsp',
                        success: function(layero, index){
                            var body = layer.getChildFrame('body', index);
                            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                        }
                    });
                    break;
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        //脚本编辑弹出层
                        var name = encodeURIComponent(data.toolName);
                        layer.open({
                            type: 2,
                            title: '编辑管理员信息',
                            shadeClose: true,
                            shade: 0.8,
                            maxmin: true,
                            area: ['38%', '60%'],
                            content: '${pageContext.request.contextPath}/admin/adminEditOfSupper.jsp',
                            success: function(layero, index){
                                var body = layer.getChildFrame('body', index);
                                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                                var inputList = body.find("input");
                                var imgSrc = body.find("img");
                                $(inputList[0]).val(editList[3]); //遍历子窗口的input标签，将之前数组中的值一次放入显示
                                $(inputList[1]).val(editList[5]);
                                $(inputList[2]).val(editList[1]);
                                $(inputList[3]).val(editList[4]);
                                $(inputList[5]).val(editList[2]);
                                $(inputList[6]).attr("checked", editList[6] === '男' ? true : false);
                                $(inputList[7]).attr("checked", editList[6] === '女' ? true : false);
                                $(inputList[9]).val(editList[0]);
                                $(inputList[10]).val(editList[8]);
                                $(imgSrc[0]).attr("src",editList[9]);
                            }
                        });
                    }
                    break;
                case 'delete':
                    if (data.length === 0) {
                        layer.msg('请选择一行');
                    } else {
                        layer.confirm('真的删除行么', function (index) {
                            layer.close(index);
                            //向服务端发送删除指令
                            var adminid = checkStatus.data[0].adminid;
                            $.ajax({
                                url: "${pageContext.request.contextPath}/admin/deleteAdmin.do",
                                type: "post",
                                async: false,   //不要让它异步提交
                                data: {
                                    adminid : adminid,
                                },
                                dataType: "json",
                                success: function (data) {
                                    console.log(data);
                                    if(data.msg != '0'){
                                        layer.alert("删除成功",{icon: 1,time:2000},function () {
                                            window.location.reload();    //重新加载父页面，进行数据刷新
                                        });
                                    } else{
                                        layer.alert("删除失败",{icon: 2,time:2000});
                                    }
                                }
                            });
                        });

                    }
                    break;
            }
            ;
        });

        //监听行工具事件
        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                , layEvent = obj.event //获得 lay-event 对应的值
            ,editList=[]; //存放获取到的那条json数据中的value值（不放key）
            $.each(data,function(name,value) {//循环遍历json数据
                editList.push(value);//将json数据中的value放入数组中（下面的子窗口显示的时候要用到）
                // console.log(name+"...."+value);
            });
            if (layEvent === 'detail') {
                //脚本编辑弹出层
                var name = encodeURIComponent(data.toolName);
                // console.log(data.toolName);
                layer.open({
                    type: 2,
                    title: '查看管理员信息',
                    shadeClose: true,
                    shade: 0.8,
                    maxmin: true,
                    area: ['38%', '60%'],
                    content: '${pageContext.request.contextPath}/admin/adminLook.jsp',//设置你要弹出的jsp页面
                    success: function(layero, index){
                        var body = layer.getChildFrame('body', index);
                        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                        var inputList = body.find("input");//获取到子窗口的所有的input标签
                        var imgSrc = body.find("img");
                        $(inputList[0]).val(editList[3]); //遍历子窗口的input标签，将之前数组中的值一次放入显示
                        $(inputList[1]).val(editList[5]);
                        $(inputList[2]).val(editList[1]);
                        $(inputList[3]).val(editList[4]);
                        $(inputList[5]).val(editList[2]);
                        $(inputList[6]).attr("checked", editList[6] === '男' ? true : false);
                        $(inputList[7]).attr("checked", editList[6] === '女' ? true : false);
                        $(inputList[9]).val(editList[0]);
                        $(inputList[10]).val(editList[8]);
                        $(imgSrc[0]).attr("src",editList[9]);

                    }
                });
            } else if (layEvent === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    var adminid = data.adminid;
                    $.ajax({
                        url: "${pageContext.request.contextPath}/admin/deleteAdmin.do",
                        type: "post",
                        async: false,   //不要让它异步提交
                        data: {
                            adminid : adminid,
                        },
                        dataType: "json",
                        success: function (data) {
                            console.log(data);
                            if(data.msg != '0'){
                                layer.alert("删除成功",{icon: 1,time:2000},function () {
                                    window.location.reload();    //重新加载父页面，进行数据刷新
                                });
                            } else{
                                layer.alert("删除失败",{icon: 2,time:2000});
                            }
                        }
                    });
                });
            } else if (layEvent === 'edit') {
                //脚本编辑弹出层
                var name = encodeURIComponent(data.toolName);
                // console.log(data.toolName);
                layer.open({
                    type: 2,
                    title: '编辑管理员信息',
                    shadeClose: true,
                    shade: 0.8,
                    maxmin: true,
                    area: ['38%', '60%'],
                    content: '${pageContext.request.contextPath}/admin/adminEditOfSupper.jsp',//设置你要弹出的jsp页面
                    success: function(layero, index){
                        var body = layer.getChildFrame('body', index);
                        var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                        var inputList = body.find("input");//获取到子窗口的所有的input标签
                        var imgSrc = body.find("img");
                        $(inputList[0]).val(editList[3]); //遍历子窗口的input标签，将之前数组中的值一次放入显示
                        $(inputList[1]).val(editList[5]);
                        $(inputList[2]).val(editList[1]);
                        $(inputList[3]).val(editList[4]);
                        $(inputList[5]).val(editList[2]);
                        $(inputList[6]).attr("checked", editList[6] === '男' ? true : false);
                        $(inputList[7]).attr("checked", editList[6] === '女' ? true : false);
                        $(inputList[9]).val(editList[0]);
                        $(inputList[10]).val(editList[8]);
                        $(imgSrc[0]).attr("src",editList[9]);
                    }
                });
            }
        });

        var $ = layui.$, active = {
            reload: function(){
                var adminid = $('#adminid').val();
                //执行重载
                table.reload('testReload', {
                     where: {
                         adminid: adminid,
                    }
                });
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
</script>
<script>
    function downloadAdminfile() {
        $.ajax({
            url: "${pageContext.request.contextPath}/admin/ExportAdminFromDB.do",
            type: "POST",
            data:{
                filePath:"123"
            },
            success: return_result,
            error: alter_error0
        });
        function return_result(data) {
            if (data === "1") {
                alert("导出失败！");
            } else{
                alert("导出成功！文件已导出至："+data);
            }
        }
        function alter_error0() {
            alert("导出失败!")
        }
    }
</script>
</body>
</html>