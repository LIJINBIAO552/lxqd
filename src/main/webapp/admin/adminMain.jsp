<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>肇庆学院离校清单超管中心</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/adminStyle.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/layui.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modules/laydate/default/laydate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modules/layer/default/layer.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">肇庆学院</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item">
                <a id="lookProcedure"
                   data-url="${pageContext.request.contextPath}/admin/lookProcedure.do"
                   data-id="lookProcedure" data-title="查看离校流程" class="site-liebiao-active"
                   data-type="tabAdd">离校流程</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">离校通知管理</a>
                <dl class="layui-nav-child">
                    <dd><a id="procedureAdd"
                           data-url="${pageContext.request.contextPath}/admin/procedureAdd.jsp"
                           data-id="procedureAdd" data-title="添加通知" class="site-liebiao-active"
                           data-type="tabAdd">添加通知</a>
                    </dd>
                    <dd><a id="updateProcedure"
                           data-url="${pageContext.request.contextPath}/admin/updateProcedure.do"
                           data-id="updateProcedure" data-title="更新通知" class="site-liebiao-active"
                           data-type="tabAdd">更新通知</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${sessionScope.admin.adminphoto}" class="layui-nav-img">
                    ${sessionScope.admin.adminname}
                </a>
                <dl class="layui-nav-child">
                    <dd>
                        <a id="selectMyself"
                           data-url="${pageContext.request.contextPath}/admin/selectMyselfOfAdmin.do"
                           data-id="selectMyself" data-title="个人基本资料" class="site-liebiao-active"
                           data-type="tabAdd">基本资料</a>
                    </dd>
                    <dd>
                        <a id="updateMyPW"
                           data-url="${pageContext.request.contextPath}/admin/updatePW.jsp"
                           data-id="updateMyPW" data-title="修改密码" class="site-liebiao-active"
                           data-type="tabAdd">修改密码</a>
                    </dd>

                </dl>
            </li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/admin/adminLogout.do">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">教工信息管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a id="adminsPhotoUpload"
                               data-url="${pageContext.request.contextPath}/admin/adminsPhotoUpload.jsp"
                               data-id="adminsPhotoUpload" data-title="导入管理员照片" class="site-liebiao-active"
                               data-type="tabAdd">导入管理员照片</a>
                        </dd>
                        <dd>
                            <a id="adminInfoMana"
                               data-url="${pageContext.request.contextPath}/admin/adminManage.jsp"
                               data-id="adminInfoMana" data-title="管理员信息管理" class="site-liebiao-active"
                               data-type="tabAdd">管理员信息管理</a>
                        </dd>
                        <%--<dd><a href="javascript:;">列表三</a></dd>--%>
                        <%--<dd><a href="">超链接</a></dd>--%>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">学生信息管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a id="stuedentsPhotoUpload"
                               data-url="${pageContext.request.contextPath}/admin/studentsPhotoUpload.jsp"
                               data-id="stuedentsPhotoUpload" data-title="学生用户信息管理" class="site-liebiao-active"
                               data-type="tabAdd">导入学生用户照片</a>
                        </dd>
                        <dd>
                            <a id="studentsInfoMana"
                               data-url="${pageContext.request.contextPath}/admin/studentManage.jsp"
                               data-id="studentsInfoMana" data-title="学生用户信息管理" class="site-liebiao-active"
                               data-type="tabAdd">学生用户信息管理</a>
                        </dd>
                        <%--<dd><a href="">超链接</a></dd>--%>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">离校清单管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a id="lookLxqd"
                               data-url="${pageContext.request.contextPath}/admin/lookLxqd.do"
                               data-id="lookLxqd" data-title="预览离校清单模板" class="site-liebiao-active"
                               data-type="tabAdd">预览离校清单模板</a>
                        </dd>
                        <dd>
                            <a id="updateLxqd"
                               data-url="${pageContext.request.contextPath}/admin/lookUpdateLxqd.do"
                               data-id="updateLxqd" data-title="修改离校清单模板" class="site-liebiao-active"
                               data-type="tabAdd">修改离校清单模板</a>
                        </dd>
                        <%--<dd><a href="">超链接</a></dd>--%>
                    </dl>
                </li>
                <%--<li class="layui-nav-item"><a href="">云市场</a></li>--%>
                <%--<li class="layui-nav-item"><a href="">发布商品</a></li>--%>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="liebiao" lay-allowclose="true" style="padding-top: 2px">
            <ul class="layui-tab-title">
            </ul>
            <ul class="rightmenu" style="display: none;position: absolute;">
                <li data-type="closethis" class="closethis">&nbsp;&nbsp;&nbsp;关闭当前</li>
                <li data-type="closeall" class="closeall">&nbsp;&nbsp;&nbsp;关闭所有</li>
            </ul>
            <div class="layui-tab-content">
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © lxqd.com - 肇庆学院毕业生离校清单管理系统
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/table.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/laypage.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/layer.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/lay/modules/laydate.js" charset="utf-8"></script>

<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var $ = jQuery = layui.jquery;
        var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
        //触发事件
        var active = {
            tabAdd: function (url, id, title) {
                //新增一个Tab项
                element.tabAdd('liebiao', {
                    title: title,
                    content: "<iframe width='100%' heigh='100%' src=\'" + '${pageContext.request.contextPath}' + url + "\'></iframe>",
                    id: id,
                });
                CustomRightClick(id);//绑定右键菜单
                FrameWH();//计算框架高度
            }
            , tabChange: function (id) {
                //切换到指定Tab项
                element.tabChange('liebiao', id); //切换到：用户管理
                $("iframe[data-frameid='" + id + "']").attr("src", $("iframe[data-frameid='" + id + "']").attr("src"))//切换后刷新框架
            }
            , tabDelete: function (id) {
                element.tabDelete("liebiao", id);//删除
            }
            , tabDeleteAll: function (ids) {//删除所有
                $.each(ids, function (i, item) {
                    element.tabDelete("liebiao", item);
                })
            }
        };

        //结合菜单展示内容
        $("#selectMyself").click(function () {
            Toselect("selectMyself");
        });
        $("#adminInfoMana").click(function () {
            Toselect("adminInfoMana");
        });
        $("#adminsPhotoUpload").click(function () {
            Toselect("adminsPhotoUpload");
        });
        $("#updateMyPW").click(function () {
            Toselect("updateMyPW");
        });
        $("#studentsInfoMana").click(function () {
            Toselect("studentsInfoMana");
        });
        $("#stuedentsPhotoUpload").click(function () {
            Toselect("stuedentsPhotoUpload");
        });
        $("#lookLxqd").click(function () {
            Toselect("lookLxqd");
        });
        $("#updateLxqd").click(function () {
            Toselect("updateLxqd");
        });

        $("#lookProcedure").click(function () {
            Toselect("lookProcedure");
        });
        $("#procedureAdd").click(function () {
            Toselect("procedureAdd");
        });
        $("#updateProcedure").click(function () {
            Toselect("updateProcedure");
        });


        function Toselect(id) {
            var dataid = $(document.getElementById(id));
            if ($(".layui-tab-title li[lay-id]").length <= 0) {
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
            } else {
                var isData = false;
                $.each($(".layui-tab-title li[lay-id]"), function () {
                    if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                        isData = true;
                    }
                })
                if (isData == false) {
                    active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
                }
            }
            active.tabChange(dataid.attr("data-id"));
        }

        //绑定右键菜单
        function CustomRightClick(id) {
            //取消右键
            $('.layui-tab-title li').on('contextmenu', function () {
                return false;
            })
            $('.layui-tab-title,.layui-tab-title li').click(function () {
                $('.rightmenu').hide();
            });
            //桌面点击右击
            $('.layui-tab-title li').on('contextmenu', function (e) {
                var popupmenu = $(".rightmenu");
                popupmenu.find("li").attr("data-id", id);
                l = ($(document).width() - e.clientX) < popupmenu.width() ? (e.clientX - popupmenu.width()) : e.clientX;
                t = ($(document).height() - e.clientY) < popupmenu.height() ? (e.clientY - popupmenu.height()) : e.clientY;
                popupmenu.css({left: l - 200, top: t - 60}).show();
                //alert("右键菜单")
                return false;
            });
        }

        $(".rightmenu li").click(function () {
            if ($(this).attr("data-type") == "closethis") {
                active.tabDelete($(this).attr("data-id"))
            } else if ($(this).attr("data-type") == "closeall") {
                var tabtitle = $(".layui-tab-title li");
                var ids = new Array();
                $.each(tabtitle, function (i) {
                    ids[i] = $(this).attr("lay-id");
                })
                active.tabDeleteAll(ids);
            }
            $('.rightmenu').hide();
        })

        function FrameWH() {
            var h = $(window).height();
            $("iframe").css("height", h + "px");
        }

        $(window).resize(function () {
            FrameWH();
        })
    });
</script>
</body>
</html>