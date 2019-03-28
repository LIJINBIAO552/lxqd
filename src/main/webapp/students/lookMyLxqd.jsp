<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--//时间格式化引用--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>查看离校清单</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/lookMyselfLxqd.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/canvg2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/html2canvas-0.4.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jspdf.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
</head>
<body>
<%--<span>${students1}</span>--%>
<div class="mid">
    <form action="${pageContext.request.contextPath}/students/selectMyselfOfLxqd.do" target="_blank">
        <input id="downloadPdf" type="submit" name="downloadflag" value="点击预览下载"/>
    </form>
</div>
<div id="pdfContainer" class="container">
    <h1>${students1.lxqd.title}</h1>
    <div class="header">
        <div class="header-left">
            <p class="word">二级学院（盖章）:</p>
            <p class="underline" style="width: 280px;height: 19px">${students1.department.departname}</p>
        </div>
        <div class="header-right">
            <span style="display: none">
                <c:if test="${students1.acadsign.signtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.acadsign.signtime, 0, 10), '-') }"
                           var="acadsigntime1"/>
                </c:if>
            </span>
            <p class="underline" style="width: 40px;height: 19px"><c:if
                    test="${students1.acadsign.signtime != 'null'}"><c:out
                    value="${acadsigntime1[0]}"/></c:if>
            </p>
            <p class="word">年</p>
            <p class="underline" style="width: 30px;height: 19px"><c:if
                    test="${students1.acadsign.signtime != 'null'}"><c:out
                    value="${acadsigntime1[1]}"/></c:if>
            </p>
            <p class="word">月</p>
            <p class="underline" style="width: 30px;height: 19px">
                <c:if test="${students1.acadsign.signtime != 'null'}">
                    <c:out value="${acadsigntime1[2]}"/>
                </c:if>
            </p>
            <p class="word">日</p></div>
    </div>
    <div class="body">
        <div class="body-first">
            <div class="body-first-number" style="width: 60px;">学号</div>
            <div class="nothing"><p class="underline"
                                    style="width: 103px;height:25px;padding-top: 6px">${students1.studid}</p></div>
            <div class="body-first-number" style="width: 60px">姓名</div>
            <div class="nothing"><p class="underline"
                                    style="width: 103px;height:25px;padding-top: 6px">${students1.studname}</p></div>
            <div class="body-first-number" style="width: 60px">班别</div>
            <div class="body-first-number" style="border: 0;width:200px;padding-left: 10px">
                <p class="underline"
                   style="width: 199px;height:31px;border-right: 1px solid black;">${students1.grade}${students1.major}${students1.sclass}</p>
            </div>
            <div class="body-first-number" style="width: 84px">所在宿舍</div>
            <div class="body-first-number" style="border: 0;width:157px;padding-left: 10px">
                <c:set value="${fn:split(students1.dormitory, '-') }" var="dormitory1"/>
                <p class="underline" style="width: 30px;height: 23px">${dormitory1[0]}</p>
                <p class="word">区</p>
                <p class="underline" style="width: 30px;height: 23px">${dormitory1[1]}</p>
                <p class="word">栋</p>
                <p class="underline" style="width: 30px;height: 23px">${dormitory1[2]}<p class="word">房</p>
            </div>
            <div class="cachet6" style="background: none"></div>
        </div>
        <div class="body-third-left">
            <p class="word-bold">学工处（资助中心、宿管中心）：</p>
            <p class="word-bold">&nbsp&nbsp${students1.lxqd.studaffadividesc}</p>
            <p class="word-normal" style="width:264px;padding-top: 0px">&nbsp&nbsp学生资助管理中心</p>
            <p class="word-normal word px">（经办人签名）：</p><p class="underline" style="width: 64px;position: absolute"><p class="font" style="position: relative;bottom: 20px;"><c:if test="${students1.suppcent.admin != 'null'}"><c:out value="${students1.suppcent.admin.adminname}"/></c:if></p></p>
            <p class="word-normal word" style="width:264px;padding-top: 1px">&nbsp&nbsp学生宿舍管理中心</p>
            <p class="word-normal word px">（经办人签名）：</p>
            <p class="underline" style="width: 64px;position: absolute;">
            <p class="font" style="position: relative;bottom: 20px"><c:if
                    test="${students1.dormmana.admin != 'null'}"><c:out value="${students1.dormmana.admin.adminname}"/></c:if></p>
            </p>
            <div class="date" style="padding-left: 55px;padding-top: 0px">
                <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                <div class="cachet7" style="background: none"></div>
                </p>
                <span style="display: none">
                <c:if test="${students1.studaffadivi.sealtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.studaffadivi.sealtime, 0, 10), '-') }"
                           var="studaffadivisealtime1"/>
                </c:if>
                </span>
                <p class="underline" style="width: 40px;height: 19px"><c:if
                        test="${students1.studaffadivi.sealtime != 'null'}"><c:out
                        value="${studaffadivisealtime1[0]}"/></c:if>
                </p>
                <p class="word-normal-np">年</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.studaffadivi.sealtime != 'null'}"><c:out
                        value="${studaffadivisealtime1[1]}"/></c:if>
                </p>
                <p class="word-normal-np">月</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.studaffadivi.sealtime != 'null'}"><c:out
                        value="${studaffadivisealtime1[2]}"/></c:if>
                </p>
                <p class="word-normal-np">日</p>
                <%--鼠标移动到学工处框内是浮窗提示未签名/盖章原因--%>
                <div class="third-left-change-code" id="third-left-change-code"></div>
            </div>
        </div>
        <div class="body-third-right">
            <p class="word-bold">后勤管理处：</p>
            <p class="word-bold">&nbsp${students1.lxqd.logimanadesc}</p>
            <p class="word-normal" style="width:264px;padding-top: 24px">&nbsp&nbsp水电管理服务中心</p>
            <p class="word-normal word px">（经办人签名）：</p>
            <p class="underline" style="width: 64px;position: absolute">
            <p class="font" style="position: relative;bottom: 20px"><c:if
                    test="${students1.hydropower.admin != 'null'}"><c:out
                    value="${students1.hydropower.admin.adminname}"/></c:if></p>
            </p>
            <p class="word-normal word" style="width:264px;padding-top: 1px">&nbsp&nbsp饮用水服务中心</p>
            <p class="word-normal word px">（经办人签名）：</p>
            <p class="underline" style="width: 64px;position: absolute">
            <p class="font" style="position: relative;bottom: 20px"><c:if
                    test="${students1.drinkwater.admin != 'null'}"><c:out
                    value="${students1.drinkwater.admin.adminname}"/></c:if></p></p>
            <div class="date" style="padding-left: 55px;padding-top: 0px">
                <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                <div class="cachet7" style="background: none"></div>
                </p>
                <span style="display: none">
                <c:if test="${students1.logimana.sealtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.logimana.sealtime, 0, 10), '-') }"
                           var="logimanasealtime1"/>
                </c:if>
                </span>
                <p class="underline" style="width: 40px;height: 19px"><c:if
                        test="${students1.logimana.sealtime != 'null'}"><c:out
                        value="${logimanasealtime1[0]}"/></c:if>
                </p>
                <p class="word-normal-np">年</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.logimana.sealtime != 'null'}"><c:out
                        value="${logimanasealtime1[1]}"/></c:if>
                </p>
                <p class="word-normal-np">月</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.logimana.sealtime != 'null'}"><c:out
                        value="${logimanasealtime1[2]}"/></c:if>
                </p>
                <p class="word-normal-np">日</p>
                <%--鼠标移动到学工处框内是浮窗提示未签名/盖章原因--%>
                <div class="third-right-change-code" id="third-right-change-code"></div>
            </div>
        </div>
        <div class="body-fourth-left">
            <p class="word-bold">图书馆：</p>
            <p class="word-bold">${students1.lxqd.librarydesc}</p>
            <div class="body-fourth-right-wall"></div>
            <p class="word-normal word px" style="padding-left: 100px;">经办人签名：</p>
            <p class="underline" style="width: 64px;position: absolute">
            <p class="font" style="position: relative;bottom: 10px"><c:if
                    test="${students1.library.admin != 'null'}"><c:out
                    value="${students1.library.admin.adminname}"/></c:if></p></p>
            <div class="date" style="padding-left: 55px;padding-top: 52px">
                <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                <div class="cachet7" style="background: none"></div>
                </p>
                <span style="display: none">
                <c:if test="${students1.library.sealtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.library.sealtime, 0, 10), '-') }"
                           var="librarysealtime1"/>
                </c:if>
                </span>
                <p class="underline" style="width: 40px;height: 19px"><c:if
                        test="${students1.library.sealtime != 'null'}"><c:out
                        value="${librarysealtime1[0]}"/></c:if>
                </p>
                <p class="word-normal-np">年</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.library.sealtime != 'null'}"><c:out
                        value="${librarysealtime1[1]}"/></c:if>
                </p>
                <p class="word-normal-np">月</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.library.sealtime != 'null'}"><c:out
                        value="${librarysealtime1[2]}"/></c:if>
                </p>
                <p class="word-normal-np">日</p>
                <%--鼠标移动到学工处框内是浮窗提示未签名/盖章原因--%>
                <div class="fourth-left-change-code" id="fourth-left-change-code"></div>
            </div>
        </div>
        <div class="body-fourth-right">
            <p class="word-bold">${students1.lxqd.hotwater1name}</p>
            <p class="word-bold">&nbsp&nbsp${students1.lxqd.hotwater1desc}</p>
            <p class="word-normal word" style="padding-left: 100px;padding-top: 14px">经办人签名：</p>
            <p class="underline" style="width: 64px;position: absolute">
            <p class="font" style="position: relative;bottom: -5px"><c:if
                    test="${students1.hotwater1.admin != 'null'}"><c:out
                    value="${students1.hotwater1.admin.adminname}"/></c:if></p></p>
            <div class="date" style="padding-left: 55px;padding-top: 63px">
                <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                <div class="cachet7" style="background: none"></div>
                </p>
                <span style="display: none">
                <c:if test="${students1.hotwater1.sealtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.hotwater1.sealtime, 0, 10), '-') }"
                           var="hotwater1sealtime1"/>
                </c:if>
                </span>
                <p class="underline" style="width: 40px;height: 19px"><c:if
                        test="${students1.hotwater1.sealtime != 'null'}"><c:out
                        value="${hotwater1sealtime1[0]}"/></c:if>
                </p>
                <p class="word-normal-np">年</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.hotwater1.sealtime != 'null'}"><c:out
                        value="${hotwater1sealtime1[1]}"/></c:if>
                </p>
                <p class="word-normal-np">月</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.hotwater1.sealtime != 'null'}"><c:out
                        value="${hotwater1sealtime1[2]}"/></c:if>
                </p>
                <p class="word-normal-np">日</p>
                <%--鼠标移动到学工处框内是浮窗提示未签名/盖章原因--%>
                <div class="fourth-right-change-code" id="fourth-right-change-code"></div>
            </div>
        </div>
        <div class="body-fifth-left">
            <p class="word-bold">信息中心：</p>
            <p class="word-bold">${students1.lxqd.educandcompdesc}</p>
            <p class="word-normal word px" style="padding-top: 33px">（经办人签名）：</p>
            <p class="underline" style="width: 64px;position: absolute">
            <p class="font" style="position: relative;bottom: -20px"><c:if
                    test="${students1.educandcomp.admin != 'null'}"><c:out
                    value="${students1.educandcomp.admin.adminname}"/></c:if></p></p>
            <div class="date" style="padding-left: 55px;padding-top: 44px">
                <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                <div class="cachet8" style="background: none"></div>
                </p>
                <span style="display: none">
                <c:if test="${students1.educandcomp.sealtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.educandcomp.sealtime, 0, 10), '-') }"
                           var="educandcompsealtime1"/>
                </c:if>
                </span>
                <p class="underline" style="width: 40px;height: 19px"><c:if
                        test="${students1.educandcomp.sealtime != 'null'}"><c:out
                        value="${educandcompsealtime1[0]}"/></c:if>
                </p>
                <p class="word-normal-np">年</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.educandcomp.sealtime != 'null'}"><c:out
                        value="${educandcompsealtime1[1]}"/></c:if>
                </p>
                <p class="word-normal-np">月</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.educandcomp.sealtime != 'null'}"><c:out
                        value="${educandcompsealtime1[2]}"/></c:if>
                </p>
                <p class="word-normal-np">日</p>
                <%--鼠标移动到学工处框内是浮窗提示未签名/盖章原因--%>
                <div class="fifth-left-change-code" id="fifth-left-change-code"></div>
            </div>
        </div>
        <div class="body-fifth-right">
            <p class="word-bold">${students1.lxqd.hotwater2name}</p>
            <p class="word-bold">&nbsp&nbsp${students1.lxqd.hotwater2desc}</p>
            <p class="word-normal word px" style="padding-left: 100px;padding-top: 33px;">经办人签名：</p>
            <p class="underline" style="width: 64px;position: absolute">
            <p class="font" style="position: relative;bottom: -20px"><c:if
                    test="${students1.hotwater2.admin != 'null'}"><c:out
                    value="${students1.hotwater2.admin.adminname}"/></c:if></p></p>
            <div class="date" style="padding-left: 55px;padding-top: 42px">
                <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                <div class="cachet8" style="background: none"></div>
                </p>
                <span style="display: none">
                <c:if test="${students1.hotwater2.sealtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.hotwater2.sealtime, 0, 10), '-') }"
                           var="hotwater2sealtime1"/>
                </c:if>
                </span>
                <p class="underline" style="width: 40px;height: 19px"><c:if
                        test="${students1.hotwater2.sealtime != 'null'}"><c:out
                        value="${hotwater2sealtime1[0]}"/></c:if>
                </p>
                <p class="word-normal-np">年</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.hotwater2.sealtime != 'null'}"><c:out
                        value="${hotwater2sealtime1[1]}"/></c:if>
                </p>
                <p class="word-normal-np">月</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.hotwater2.sealtime != 'null'}"><c:out
                        value="${hotwater2sealtime1[2]}"/></c:if>
                </p>
                <p class="word-normal-np">日</p>
                <%--鼠标移动到学工处框内是浮窗提示未签名/盖章原因--%>
                <div class="fifth-right-change-code" id="fifth-right-change-code"></div>
            </div>
        </div>
        <div class="body-sixth-left">
            <p class="word-bold">二级学院：</p>
            <p class="word-bold">&nbsp${students1.lxqd.acaddesc}</p>
            <p class="word-normal word px" style="padding-left: 85px;padding-top: 13px">（经办人签名）：</p>
            <p class="underline" style="width: 64px;position: absolute">
            <p class="font" style="position: relative;bottom: 0px"><c:if
                    test="${students1.acadsign.admin != 'null'}"><c:out
                    value="${students1.acadsign.admin.adminname}"/></c:if></p></p>
            <div class="date" style="padding-left: 55px;padding-top: 30px">
                <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                <div class="cachet8" style="background: none"></div>
                </p>
                <span style="display: none">
                <c:if test="${students1.acadsign.signtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.acadsign.signtime, 0, 10), '-') }"
                           var="acadsigntime2"/>
                </c:if>
                </span>
                <p class="underline" style="width: 40px;height: 19px"><c:if
                        test="${students1.acadsign.signtime != 'null'}"><c:out
                        value="${acadsigntime2[0]}"/></c:if>
                </p>
                <p class="word-normal-np">年</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.acadsign.signtime != 'null'}"><c:out
                        value="${acadsigntime2[1]}"/></c:if>
                </p>
                <p class="word-normal-np">月</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.acadsign.signtime != 'null'}"><c:out
                        value="${acadsigntime2[2]}"/></c:if>
                </p>
                <p class="word-normal-np">日</p>
                <%--鼠标移动到学工处框内是浮窗提示未签名/盖章原因--%>
                <div class="sixth-left-change-code" id="sixth-left-change-code"></div>
            </div>
        </div>
        <div class="body-sixth-right">
            <p class="word-bold"> &nbsp</p>
            <p class="word-bold">&nbsp&nbsp${students1.lxqd.classteacdesc}</p>
            <p class="word-normal word px" style="padding-left: 100px;padding-top: 11px">班主任签名：</p>
            <p class="underline" style="width: 64px;position: absolute">
            <p class="font" style="position: relative;bottom: 0px"><c:if
                    test="${students1.clasteacsign.admin != 'null'}"><c:out
                    value="${students1.clasteacsign.admin.adminname}"/></c:if></p></p>
            <div class="date1" style="padding-left: 140px;padding-top: 30px">
                <span style="display: none">
                <c:if test="${students1.clasteacsign.signtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.clasteacsign.signtime, 0, 10), '-') }"
                           var="clasteacsignsigntime1"/>
                </c:if>
                </span>
                <p class="underline" style="width: 40px;height: 19px"><c:if
                        test="${students1.clasteacsign.signtime != 'null'}"><c:out
                        value="${clasteacsignsigntime1[0]}"/></c:if>
                </p>
                <p class="word-normal-np">年</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.clasteacsign.signtime != 'null'}"><c:out
                        value="${clasteacsignsigntime1[1]}"/></c:if>
                </p>
                <p class="word-normal-np">月</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.clasteacsign.signtime != 'null'}"><c:out
                        value="${clasteacsignsigntime1[2]}"/></c:if>
                </p>
                <p class="word-normal-np">日</p>
                <%--鼠标移动到学工处框内是浮窗提示未签名/盖章原因--%>
                <div class="sixth-right-change-code" id="sixth-right-change-code"></div>
            </div>
        </div>
    </div>
    <div class="buttom">
        ${students1.lxqd.remarkdesc}
    </div>
</div>

</body>
<%--签名盖章--%>
<script>
    function load() {
        var font = document.getElementsByClassName('font');
        var sealer6 = document.getElementsByClassName('cachet6');
        var sealer7 = document.getElementsByClassName('cachet7');
        var sealer8 = document.getElementsByClassName('cachet8');
        for (var i = 0; i < font.length; i++) {
            if (font[i].innerHTML == '') {
                font[i].classList.add('fontWhite');
            } else {
                font[i].classList.add('fontBlue');
            }
        }
        //判断盖章
        if ("${students1.acadsign.admin1.adminname}" != "" && "${students1.acadsign.admin1.departid}" == "20") {
            sealer6[0].style.background = "url(${pageContext.request.contextPath}/img/计算机学院.png) no-repeat";
        }
        if ("${students1.studaffadivi.admin.adminname}" != "" && "${students1.suppcent.admin.adminname}" != ""
            && "${students1.dormmana.admin.adminname}" != "") {
            sealer7[0].style.background = "url(${pageContext.request.contextPath}/img/学工处.png) no-repeat";
        }
        if ("${students1.logimana.admin.adminname}" != "" && "${students1.hydropower.admin.adminname}" != ""
            && "${students1.drinkwater.admin.adminname}" != "") {
            sealer7[1].style.background = "url(${pageContext.request.contextPath}/img/后勤管理.png) no-repeat";
        }
        if ("${students1.library.admin1.adminname}" != "" && "${students1.library.admin.adminname}" != "") {
            sealer7[2].style.background = "url(${pageContext.request.contextPath}/img/图书馆.png) no-repeat";
        }
        if ("${students1.hotwater1.admin1.adminname}" != "" && "${students1.hotwater1.admin.adminname}" != "") {
            sealer7[3].style.background = "url(${pageContext.request.contextPath}/img/宇正公司.png) no-repeat";
        }
        if ("${students1.educandcomp.admin1.adminname}" != "" && "${students1.educandcomp.admin.adminname}" != "") {
            sealer8[0].style.background = "url(${pageContext.request.contextPath}/img/信息中心.png) no-repeat";
        }
        if ("${students1.hotwater2.admin1.adminname}" != "" && "${students1.hotwater2.admin.adminname}" != "") {
            sealer8[1].style.background = "url(${pageContext.request.contextPath}/img/纽恩泰公司.png) no-repeat";
        }
        if ("${students1.acadsign.admin.adminname}" != "" && "${students1.acadsign.admin.departid}" == "20") {
            sealer8[2].style.background = "url(${pageContext.request.contextPath}/img/计算机学院.png) no-repeat";
        }
    }
    window.onload = load;
</script>
<%--学工处--%>
<script>
    var thirdleftshow = document.getElementsByClassName("body-third-left");
    var thirdleftchange = document.getElementById("third-left-change-code");
    var studaffadiviflag = false;
    $(function () {
        if ("${students1.studaffadivi.admin.adminname}" == "") {
            studaffadiviflag = true;
        }
        thirdleftshow[0].onmouseover = function () {
            if (studaffadiviflag) {
                if ("${students1.suppcent.admin.adminname}" == "" && "${students1.dormmana.admin.adminname}" == "") {
                    thirdleftchange.style.display = "block";
                    thirdleftchange.innerHTML = '资助中心：' + '${students1.suppcent.suppdesc}' + '\n' + '宿管中心：' + '${students1.dormmana.dormdesc}';
                } else if ("${students1.suppcent.admin.adminname}" == "") {
                    thirdleftchange.style.display = "block";
                    thirdleftchange.innerHTML = '资助中心：' + '${students1.suppcent.suppdesc}';
                } else if ("${students1.dormmana.admin.adminname}" == "") {
                    thirdleftchange.style.display = "block";
                    thirdleftchange.innerHTML = '宿管中心：' + '${students1.dormmana.dormdesc}';
                } else {
                    thirdleftchange.style.display = "block";
                    thirdleftchange.innerHTML = "学工处：准备盖章中。。。";
                }
            }
        }
        thirdleftchange.onmouseover = function () {
            if (studaffadiviflag) {
                thirdleftchange.style.display = "block";
            }
        }
        thirdleftchange.onmouseout = function () {
            thirdleftchange.style.display = "none";
        }
        thirdleftshow[0].onmouseout = function () {
            thirdleftchange.style.display = "none";
        }
    });
</script>
<%--后勤管理处--%>
<script>
    var thirdrightshow = document.getElementsByClassName("body-third-right");
    var thirdrightchange = document.getElementById("third-right-change-code");
    var logimanaflag = false;
    $(function () {
        if ("${students1.logimana.admin.adminname}" == "") {
            logimanaflag = true;
        }
        thirdrightshow[0].onmouseover = function () {
            if (logimanaflag) {
                if ("${students1.hydropower.admin.adminname}" == "" && "${students1.drinkwater.admin.adminname}" == "") {
                    thirdrightchange.style.display = "block";
                    thirdrightchange.innerHTML = '水电中心：' + '${students1.hydropower.hydrpay}' + '\n' + '饮用水中心：' + '${students1.drinkwater.dwpay}';
                } else if ("${students1.hydropower.admin.adminname}" == "") {
                    thirdrightchange.style.display = "block";
                    thirdrightchange.innerHTML = '水电中心：' + '${students1.hydropower.hydrpay}';
                } else if ("${students1.drinkwater.admin.adminname}" == "") {
                    thirdrightchange.style.display = "block";
                    thirdrightchange.innerHTML = '饮用水中心：' + '${students1.drinkwater.dwpay}';
                } else {
                    thirdrightchange.style.display = "block";
                    thirdrightchange.innerHTML = "后勤管理处：准备盖章中。。。";
                }
            }
        }

        thirdrightchange.onmouseover = function () {
            if (logimanaflag) {
                thirdrightchange.style.display = "block";
            }
        }
        thirdrightchange.onmouseout = function () {
            thirdrightchange.style.display = "none";
        }
        thirdrightshow[0].onmouseout = function () {
            thirdrightchange.style.display = "none";
        }
    });
</script>
<%--图书馆--%>
<script>
    var fourthleftshow = document.getElementsByClassName("body-fourth-left");
    var fourthleftchange = document.getElementById("fourth-left-change-code");
    var libraryflag = false;
    $(function () {
        if ("${students1.library.admin1.adminname}" == "") {
            libraryflag = true;
        }
        fourthleftshow[0].onmouseover = function () {
            if (libraryflag) {
                    fourthleftchange.style.display = "block";
                    fourthleftchange.innerHTML = '图书管理中心：' + '${students1.library.nedesc}';
            }
            // else {
            //     fourthleftchange.style.display = "block";
            //     fourthleftchange.innerHTML = "图书管理中心：准备盖章中。。。";
            // }
        }

        fourthleftchange.onmouseover = function () {
            if (libraryflag) {
                fourthleftchange.style.display = "block";
            }
        }
        fourthleftchange.onmouseout = function () {
            fourthleftchange.style.display = "none";
        }
        fourthleftshow[0].onmouseout = function () {
            fourthleftchange.style.display = "none";
        }
    });
</script>
<%--热水1--%>
<script>
    var fourthrightshow = document.getElementsByClassName("body-fourth-right");
    var fourthrightchange = document.getElementById("fourth-right-change-code");
    var hotwater1flag = false;
    $(function () {
        if ("${students1.hotwater1.admin1.adminname}" == "") {
            hotwater1flag = true;
        }
        fourthrightshow[0].onmouseover = function () {
            if (hotwater1flag) {
                    fourthrightchange.style.display = "block";
                    fourthrightchange.innerHTML = '宇正公司提醒您：' + '${students1.hotwater1.hw1pay}';
            }
            // else {
            //     fourthrightchange.style.display = "block";
            //     fourthrightchange.innerHTML = "宇正公司：准备盖章中。。。";
            // }
        }

        fourthrightchange.onmouseover = function () {
            if (hotwater1flag) {
                fourthrightchange.style.display = "block";
            }
        }
        fourthrightchange.onmouseout = function () {
            fourthrightchange.style.display = "none";
        }
        fourthrightshow[0].onmouseout = function () {
            fourthrightchange.style.display = "none";
        }
    });
</script>
<%--信息中心--%>
<script>
    var fifthleftshow = document.getElementsByClassName("body-fifth-left");
    var fifthleftchange = document.getElementById("fifth-left-change-code");
    var educandcompflag = false;
    $(function () {
        if ("${students1.educandcomp.admin1.adminname}" == "") {
            educandcompflag = true;
        }
        fifthleftshow[0].onmouseover = function () {
            if (educandcompflag) {
                fifthleftchange.style.display = "block";
                fifthleftchange.innerHTML = '信息中心提醒您：' + '${students1.educandcomp.rentdesc}';
            }
            // else {
            //     fifthleftchange.style.display = "block";
            //     fifthleftchange.innerHTML = "信息中心：准备盖章中。。。";
            // }
        }
        fifthleftchange.onmouseover = function () {
            if (educandcompflag) {
                fifthleftchange.style.display = "block";
            }
        }
        fifthleftchange.onmouseout = function () {
            fifthleftchange.style.display = "none";
        }
        fifthleftshow[0].onmouseout = function () {
            fifthleftchange.style.display = "none";
        }
    });
</script>
<%--热水2--%>
<script>
    var fifthrightshow = document.getElementsByClassName("body-fifth-right");
    var fifthrightchange = document.getElementById("fifth-right-change-code");
    var hotwater2flag = false;
    $(function () {
        if ("${students1.hotwater2.admin1.adminname}" == "") {
            hotwater2flag = true;
        }
        fifthrightshow[0].onmouseover = function () {
            if (hotwater2flag) {
                fifthrightchange.style.display = "block";
                fifthrightchange.innerHTML = '纽恩泰公司提醒您：' + '${students1.hotwater2.hw2pay}';
            }
            // else {
            //     fifthrightchange.style.display = "block";
            //     fifthrightchange.innerHTML = "纽恩泰公司：准备盖章中。。。";
            // }
        }
        fifthrightchange.onmouseover = function () {
            if (hotwater2flag) {
                fifthrightchange.style.display = "block";
            }
        }
        fifthrightchange.onmouseout = function () {
            fifthrightchange.style.display = "none";
        }
        fifthrightshow[0].onmouseout = function () {
            fifthrightchange.style.display = "none";
        }
    });
</script>
<%--二级学院--%>
<script>
    var sixthleftshow = document.getElementsByClassName("body-sixth-left");
    var sixthleftchange = document.getElementById("sixth-left-change-code");
    var acadsignflag = false;
    $(function () {
            if ("${students1.acadsign.admin.adminname}" == "") {
                acadsignflag = true;
            }
            sixthleftshow[0].onmouseover = function () {
                if (acadsignflag) {
                    sixthleftchange.style.display = "block";
                    sixthleftchange.innerHTML = '${students1.department.departname}' + '提醒您：' + '${students1.acadsign.itemborrdesc}';
                }
                <%--else {--%>
                    <%--sixthleftchange.style.display = "block";--%>
                    <%--sixthleftchange.innerHTML = '${students1.department.departname}' + "：准备盖章中。。。";--%>
                <%--}--%>
            }
            sixthleftchange.onmouseover = function () {
                if (acadsignflag) {
                    sixthleftchange.style.display = "block";
                }
            }
            sixthleftchange.onmouseout = function () {
                sixthleftchange.style.display = "none";
            }
            sixthleftshow[0].onmouseout = function () {
                sixthleftchange.style.display = "none";
            }
        }
    );
</script>
<%--班主任--%>
<script>
    var sixthrightshow = document.getElementsByClassName("body-sixth-right");
    var sixthrightchange = document.getElementById("sixth-right-change-code");
    var clasteacsignflag = false;
    $(function () {
        if ("${students1.clasteacsign.admin.adminname}" == "") {
            clasteacsignflag = true;
        }
        sixthrightshow[0].onmouseover = function () {
            if (clasteacsignflag) {
                sixthrightchange.style.display = "block";
                sixthrightchange.innerHTML = '同学，前面还有手续没办完啦';
            }
            <%--else {--%>
                <%--sixthrightchange.style.display = "block";--%>
                <%--sixthrightchange.innerHTML = '${students1.department.departname}' + "：准备签名中。。。";--%>
            <%--}--%>
        }
        sixthrightchange.onmouseover = function () {
            if (clasteacsignflag) {
                sixthrightchange.style.display = "block";
            }
        }
        sixthrightchange.onmouseout = function () {
            sixthrightchange.style.display = "none";
        }
        sixthrightshow[0].onmouseout = function () {
            sixthrightchange.style.display = "none";
        }
    });
</script>
</html>
