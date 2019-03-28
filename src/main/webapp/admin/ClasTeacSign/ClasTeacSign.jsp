<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--//时间格式化引用--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>离校清单管理系统</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/ClasTeacSign.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/menu.css" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.metisMenu.js"></script>

</head>
<body>

<div id="allBody">
    <div class="row border-bottom">
        <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
            <ul class="nav navbar-top-links navbar-left">
                <li>
                    <span class="m-l-sm text-muted welcome-message"
                          style="font-size: 24px;line-height: 70px;padding-left: 30px;">欢迎进入${sessionScope.admin.department.departname}！</span>
                </li>
            </ul>
            <ul class="nav navbar-top-links navbar-right">
                <ul class="menu">
                    <li><a href=""><img src="${sessionScope.admin.adminphoto}" class="touxiangimg" id="touxiaoimg"><span id="loginName" style="height: 30px;font-size: 14px;line-height: 30px">${sessionScope.admin.adminname}</span></a>
                        <ul>
                            <li><a id="selectMyselfOfAdmin">查看个人信息</a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/admin/ClasTeacSign/ClasTeacSignMain.do">签名盖章管理</a>
                            </li>
                            <li><a id="change-click">修改密码</a></li>
                            <li><a href="${pageContext.request.contextPath}/admin/adminLogout.do">退出账号</a></li>
                        </ul>
                    </li>
                </ul>
            </ul>
        </nav>
        <span id="loginId" style="display: none">${sessionScope.admin.adminid}</span>
    </div>
    <div class="navbar-header" style="padding-top: 8px;">
        <a class="navbar-minimalize minimalize-styl-2 btn btn-primary down" onclick="downloadSuppcenfile()">导出</a>
        <ul class="menu" style="width: auto;height:28px;margin-left: 100px">
            <li><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#" style="margin: 0;border: 1px solid #1ab394;font-size: 14px;font-weight: 400">导入</a>
                <ul style="background-color: white;padding: 10px 10px 30px 10px">
                    <div class="upload">
                        <form
                        <%--action="" method="post" --%>
                                enctype="multipart/form-data">
                            <input type='text' name='textfield' id='textfield' class='txt' />
                            <input type='button' class='btn' value='选择文件' style="height: 30px" />
                            <input type="file" id="importfile" name="multipartFile" class="file" id="fileField" size="28" onchange="document.getElementById('textfield').value=this.value"/>
                        </form>
                    </div>
                </ul>
            </li>
        </ul>
    </div>
    <div class="row col-md-offset-3" style="position: relative;right: -25px;margin-top: 20px">
        <div class="col-md-7">
            <form role="form" action="${pageContext.request.contextPath}/admin/ClasTeacSign/SelectClasTeacSign.do"
                  style="margin-bottom: 20px;" id="selectform">
                <div class="input-group">
                    <div class="input-group-btn">
                        <select class="form-control" style="width: auto" name="condition">
                            <option value="all">
                                <button>查询全部</button>
                            </option>
                            <option value="1">
                                <button>学号</button>
                            </option>
                            <%--<option value="2">--%>
                                <%--<button>学院</button>--%>
                            <%--</option>--%>
                            <option value="3">
                                <button>专业</button>
                            </option>
                            <%--<option value="4">--%>
                                <%--<button>班级</button>--%>
                            <%--</option>--%>
                            <%--<option value="5">--%>
                                <%--<button>年级</button>--%>
                            <%--</option>--%>
                            <option value="6">
                                <button>姓名</button>
                            </option>
                            <%--<option value="7">--%>
                                <%--<button>性别</button>--%>
                            <%--</option>--%>
                            <%--<option value="8">--%>
                            <%--<button>经办人</button>--%>
                            <%--</option>--%>
                            <%--<option value="9">--%>
                                <%--<button>经办人</button>--%>
                            <%--</option>--%>
                        </select>
                    </div>
                    <input type="text" class="form-control" name="condiValue">
                </div>
            </form>
        </div>
        <div class="col-md-5">
            <button class="btn btn-info btn-search" type="submit" form="selectform" value="Submit">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查找
            </button>
            <button class="btn btn-info btn-search" onclick="signAll()">
                <span class="glyphicon glyphicon-save" aria-hidden="true"></span>一键签名
            </button>
        </div>
    </div>
    <div class="test" style="height: 797px">
        <div class="table">
            <div class="header">
                <div>学号</div>
                <div>专业</div>
                <div>班级</div>
                <div>年级</div>
                <div>姓名</div>
                <div>资助中心</div>
                <div>宿管中心</div>
                <div>学工处</div>
                <div>水电中心</div>
                <div>饮用水中心</div>
                <div>后勤管理处</div>
                <div>图书馆</div>
                <div>宇正热水</div>
                <div>信息中心</div>
                <div>纽恩泰热水</div>
                <div>二级学院</div>
                <div>签名时间</div>
                <div>确认签名</div>
                <p class="cl"></p>
            </div>
            <c:forEach items="${pageInfo.list}" var="ClasTeacSign">
                <div class="body">
                    <div id="stud_id">${ClasTeacSign.studId}</div>
                    <div id="major">${ClasTeacSign.students.major}</div>
                    <div id="sclass">${ClasTeacSign.students.sclass}</div>
                    <div id="grade">${ClasTeacSign.students.grade}</div>
                    <div id="studname">${ClasTeacSign.students.studname}</div>
                    <div id="suppcentsign"><c:if test="${ClasTeacSign.suppcent.admin != 'null'}">
                        <c:out value="${ClasTeacSign.suppcent.admin.adminname}"/></c:if></div>
                    <div id="dormmanasign"><c:if test="${ClasTeacSign.dormmana.admin != 'null'}">
                        <c:out value="${ClasTeacSign.dormmana.admin.adminname}"/></c:if></div>
                    <div id="studaffadivisign"><c:if test="${ClasTeacSign.studaffadivi.admin != 'null'}">
                        <c:out value="${ClasTeacSign.studaffadivi.admin.adminname}"/></c:if></div>
                    <div id="hydropowersign"><c:if test="${ClasTeacSign.hydropower.admin != 'null'}">
                        <c:out value="${ClasTeacSign.hydropower.admin.adminname}"/></c:if></div>
                    <div id="drinkwatersign"><c:if test="${ClasTeacSign.drinkwater.admin != 'null'}">
                        <c:out value="${ClasTeacSign.drinkwater.admin.adminname}"/></c:if></div>
                    <div id="logimanasign"><c:if test="${ClasTeacSign.logimana.admin != 'null'}">
                        <c:out value="${ClasTeacSign.logimana.admin.adminname}"/></c:if></div>
                    <div id="librarysign"><c:if test="${ClasTeacSign.library.admin != 'null'}">
                        <c:out value="${ClasTeacSign.library.admin.adminname}"/></c:if></div>
                    <div id="hotwater1sign"><c:if test="${ClasTeacSign.hotwater1.admin != 'null'}">
                        <c:out value="${ClasTeacSign.hotwater1.admin.adminname}"/></c:if></div>
                    <div id="hotwater2sign"><c:if test="${ClasTeacSign.hotwater2.admin != 'null'}">
                        <c:out value="${ClasTeacSign.hotwater2.admin.adminname}"/></c:if></div>
                    <div id="acadsignasign"><c:if test="${ClasTeacSign.acadsign.admin != 'null'}">
                        <c:out value="${ClasTeacSign.acadsign.admin.adminname}"/></c:if></div>
                    <div id="adminname">${ClasTeacSign.admin.adminname}</div>
                    <div id="signtime"><fmt:formatDate value="${ClasTeacSign.signtime}"
                                                       pattern="yyyy-MM-dd HH:mm:ss"/></div>
                    <div>
                        <!--签名按钮触发模态框-->
                        <button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                data-target="#myModal">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            签名
                        </button>
                    </div>
                    <p class="cl"></p>
                </div>
            </c:forEach>
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h3 class="modal-title" id="myModalLabel">
                                请核实信息
                            </h3>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" role="form"
                                  action="${pageContext.request.contextPath}/admin/ClasTeacSign/ClasTeacSignSign.do" id="edit">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">学生学号</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control"
                                               id="studId" name="studId">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">学生姓名</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control"
                                               id="studentName" name="studentName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">您的工号</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control"
                                               id="adminid" name="adminid">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">您的名字</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control"
                                               id="signName" name="adminName">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="submit" class="btn btn-primary" form="edit"
                                    value="Submit">提交更改
                            </button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>
            <!-- 这里加上后面部分，因为我没有源文件，所以就让你来了 -->
            <div class="row">
                <div class="col-md-7">
                    当前为第${pageInfo.pageNum}页，总共有${pageInfo.pages}页，共有${pageInfo.total}条记录
                </div>
                <div class="col-md-5">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li><a href="${pageContext.request.contextPath}/admin/ClasTeacSign/distributionClasTeacSign.do?pn=1">首页</a>
                            </li>
                            <li>
                                <c:if test="${pageInfo.hasPreviousPage}">
                                    <a href="${pageContext.request.contextPath}/admin/ClasTeacSign/distributionClasTeacSign.do?pn=${pageInfo.pageNum-1}"
                                       aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </c:if>
                            </li>
                            <c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
                                <c:if test="${page_num==pageInfo.pageNum}">
                                    <li class="active"><a
                                            href="${pageContext.request.contextPath}/admin/ClasTeacSign/distributionClasTeacSign.do?pn=${page_num}">${page_num}</a>
                                    </li>
                                </c:if>
                                <c:if test="${page_num!=pageInfo.pageNum}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/admin/ClasTeacSign/distributionClasTeacSign.do?pn=${page_num}">${page_num}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                            <li>
                                <c:if test="${pageInfo.hasNextPage}">
                                    <a href="${pageContext.request.contextPath}/admin/ClasTeacSign/distributionClasTeacSign.do?pn=${pageInfo.pageNum+1}"
                                       aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </c:if>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/admin/ClasTeacSign/distributionClasTeacSign.do?pn=${pageInfo.pages}">末页</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <%--</form>--%>
        </div>
    </div>
</div>
<div class="change-code" id="change-code">
    <p class="close" id="x">X</p>
    <div class="change-code-major">
        <div><p>旧密码：<a id="opw" style="color: red"></a></p><input type="password" placeholder="请输入原密码" id="oldadminpass" name="oldadminpass"><span
                id="result1"></span></div>
        <div><p>新密码：<a id="npw" style="color: red"></a></p><input type="password" placeholder="请输入6到16位的数字或字母,字母区分大小写" id="adminpass"
                                                                  name="adminpass"><span id="result3"></span>
        </div>
        <div><p>确认新密码：<a id="qnpw" style="color: red"></a></p><input type="password" placeholder="请再次确认密码" id="adminpass1" name="adminpass1"><span
                id="result2"></span></div>
    </div>
    <div class="anniu">
        <button type="button" onclick="updateadminpw()" id="anniu">确认修改</button>
    </div>
</div>

<%--查看个人信息--%>
<div class="mid" style="display: none">
    <form id="subSelectMyselfOfAdmin" action="${pageContext.request.contextPath}/admin/selectMyselfOfAdmin.do" target="_blank"></form>
</div>

</body>
<script>
    $(function () {
        if ($("#touxiaoimg").attr("src").length === 0) {
            $("#touxiaoimg").attr("src","${pageContext.request.contextPath}/img/头像.png");
        }
    })
</script>
<script>
    $("#selectMyselfOfAdmin").click(function () {
        $("#subSelectMyselfOfAdmin").submit();
    })
</script>
<script>
    // 修改密码
    function updatePW(id) {
        return document.getElementById(id);
    }

    updatePW("oldadminpass").onblur = function () {
        str = new String(this.value);
        var reg = /^[0-9a-zA-Z]+$/;
        if (str.length == 0) {
            $('#opw').html("输入不能为空！");
            updatePW("result1").className = "wrong";
        }
        else if (str.length <= 5){
            $('#opw').html("输入长度小于六位！");
            updatePW("result1").className = "wrong";
        }else if( str.length >= 16) {
            $('#opw').html("输入长度大于十六位！");
            updatePW("result1").className = "wrong";
        }
        else if (!reg.test(str)) {
            $('#opw').html("含有非法字符");
            updatePW("result1").className = "wrong";
        }
        else {
            $('#opw').html('');
            updatePW("result1").className = "right";
            updatePW("anniu").disabled = false;
        }
    }
    updatePW("adminpass").onblur = function () {
        str = new String(this.value);
        var reg = /^[0-9a-zA-Z]+$/;
        if (str.length == 0) {
            $('#npw').html("输入不能为空！");
            updatePW("result3").className = "wrong";
        }else if(str == updatePW("oldadminpass").value){
            $('#npw').html("与旧密码一样！");
            updatePW("result3").className = "wrong";
        } else if (str.length <= 5){
            $('#npw').html("输入长度小于六位！");
            updatePW("result3").className = "wrong";
        }else if (str.length >= 16) {
            $('#opw').html("输入长度大于十六位！");
            updatePW("result3").className = "wrong";
        }
        else if (!reg.test(str)) {
            $('#npw').html("含有非法字符");
            updatePW("result3").className = "wrong";
        }
        else {
            $('#npw').html('');
            updatePW("result3").className = "right";
            updatePW("anniu").disabled = false;
        }
    }
    updatePW("adminpass1").onblur = function () {
        //alert(qr);
        if (this.value == "") {
            $('#qnpw').html("输入不能为空！");
            updatePW("result2").className = "wrong";
        }
        else if (this.value == updatePW("adminpass").value) {
            $('#qnpw').html('');
            updatePW("result2").className = "right";
            updatePW("anniu").disabled = false;
        }
        else {

            $('#qnpw').html("两次输入不一致，请重新输入！");
            updatePW("result2").className = "wrong";
        }
    }
    updatePW("anniu").onmouseover = function () {
        if (updatePW("result3").className == "right" && updatePW("result2").className == "right" && updatePW("result1").className == "right") {
            updatePW("anniu").disabled = false;
            updatePW("anniu").className = "xiaoshou";
        }
        else {
            updatePW("anniu").disabled = true;
        }
    }
    updatePW("x").onclick = function () {
        updatePW("change-code").style.display = "none";
        updatePW("allBody").style.opacity = "1";
    }
    updatePW("change-click").onclick = function () {
        updatePW("change-code").style.display = "block";
        updatePW("change-code").style.opacity = "1";
        updatePW("allBody").disabled = false;
        updatePW("allBody").style.opacity = "0.3";
    }

</script>
<script>
    $(function () {
        var loginId = $("#loginId").html();
        var loginName = $("#loginName").html();
        $(".body").each(function () {
            var tmp = $(this).children().eq(17);
            var btn = tmp.children();
            btn.bind("click", function () {
                console.log(btn.parent().parent().children("div").get(0).innerHTML);
                $("#studId").val(btn.parent().parent().children("div").get(0).innerHTML);
                $('#studentName').val(btn.parent().parent().children("div").get(4).innerHTML);
                $('#adminid').val(loginId);
                $('#signName').val(loginName);
            });
        });
        $("#studId").attr("readonly", "readonly");
        $("#studentName").attr("readonly", "readonly");
        $("#adminid").attr("readonly", "readonly");
        $("#signName").attr("readonly", "readonly");
    });

    function downloadSuppcenfile() {
        $.ajax({
            url: "${pageContext.request.contextPath}/admin/ClasTeacSign/ExportClasTeacSignFromDB.do",
            type: "POST",
            data: {
                filePath: "123"
            },
            success: return_data,
            error: alter_error
        });

        function return_data(data) {
            alert("导出成功，已将：" + data.resultcount + "位同学的信息导出到：\n"+data.path+"目录下，注意查收。");
            window.location.replace("${pageContext.request.contextPath}/admin/ClasTeacSign/default.do");
        }

        function alter_error() {
            alert("一键签名失败")
        }
    }

    function signAll() {
        var adminid = $("#loginId").html();
        $.ajax({
            url: "${pageContext.request.contextPath}/admin/ClasTeacSign/ClasTeacSignSignAll.do",
            type: "POST",
            data: {
                adminid: adminid
            },
            success: return_data,
            error: alter_error
        });

        function return_data(data) {
            alert("签名成功，总共签了：" + data + "张表！");
            window.location.replace("${pageContext.request.contextPath}/admin/ClasTeacSign/distributionClasTeacSign.do");
        }

        function alter_error() {
            alert("一键签名失败")
        }
    }

    function updateadminpw() {
        var oldap = $("#oldadminpass").val();
        var ap = $("#adminpass").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/admin/updateMyselfOfPW.do",
            type: "POST",
            data: {
                oldadminpass: oldap,
                adminpass: ap
            },
            success: return_result,
            error: alter_error0
        });

        function return_result(data) {
            if (data == "1") {
                alert("更新密码成功，请重新登录！");
                window.location.replace("${pageContext.request.contextPath}/admin/adminLogout.do");
            } else if (data == "0") {
                console.log("+++++++++++++++" + data);
                alert("旧密码输入错误，请重新输入!");
                $("#oldadminpass").val('');
                $("#adminpass").val('');
                $("#adminpass1").val('');
            }
        }

        function alter_error0() {
            alert("密码更新失败，请再试一次。。。")
        }
    }

</script>
<script>
    //③创建fileLoad方法用来上传文件
    function fileLoad(ele){
        //④创建一个formData对象
        var formData = new FormData();
        //⑤获取传入元素的val
        var name = $(ele).val();
        //⑥获取files
        var files = $(ele)[0].files[0];
        //⑦将name 和 files 添加到formData中，键值对形式
        formData.append("multipartFile", files);
        formData.append("name", name);
        $.ajax({
            url: "${pageContext.request.contextPath}/admin/ClasTeacSign/ImportClasTeacSignToDB.do",
            type: 'POST',
            data: formData,
            processData: false,// ⑧告诉jQuery不要去处理发送的数据
            contentType: false, // ⑨告诉jQuery不要去设置Content-Type请求头
            success: function (responseStr) {
                //11成功后的动作
                alert(responseStr);
                $("#textfield").val('');
                $("#importfile").val('');
                window.location.href = "${pageContext.request.contextPath}/admin/ClasTeacSign/default.do";
            }
            ,
            error : function (responseStr) {
                //12出错后的动作
                alert("出错啦");
            }
        });
    }
    $(function () {
        var $importfile =  $("#importfile");
        // ①为input设定change事件
        $importfile.change(function () {
            //    ②如果value不为空，调用文件加载方法
            if($(this).val() != ""){
                fileLoad(this);
            }
        })
    })
</script>
</html>
