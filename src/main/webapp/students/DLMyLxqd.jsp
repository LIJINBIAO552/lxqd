<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--//时间格式化引用--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>HtmlToPdf</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/DLMyselfLxqd.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/canvg2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/html2canvas-0.4.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jspdf.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
</head>
<body>
<%--<span>${students1}</span>--%>
<div class="mid">
    <input id="downloadPdf" type="button" value="下载为PDF文件"/>
</div>
<div id="pdfContainer" class="container">
    <h1>${students1.lxqd.title}</h1>
    <div class="header">
        <div class="header-left">
            <p class="word">二级学院（盖章）</p>
            <p class="underline" style="width: 80px;height: 19px"></p>
            <p class="word">班别:</p>
            <p class="underline"
               style="width: 160px;height: 19px">${students1.grade}${students1.major}${students1.sclass}</p></div>
        <div class="header-right">
            <span style="display: none">
                <c:if test="${students1.acadsign.signtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.acadsign.signtime, 0, 10), '-') }" var="acadsigntime1"/>
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
            <div class="body-first-number" style="width: 50px;">学号</div>
            <div class="nothing"><p class="underline" style="width: 103px;height:25px;padding-top: 6px">${students1.studid}</p></div>
            <div class="body-first-number" style="width: 50px">姓名</div>
            <div class="nothing"><p class="underline" style="width: 103px;height:25px;padding-top: 6px">${students1.studname}</p></div>
            <div class="body-first-number" style="width: 84px">所在宿舍</div>
            <div class="body-first-number" style="border: 0;width:157px;padding-left: 10px">
                <c:set value="${fn:split(students1.dormitory, '-') }" var="dormitory1"/>
                <p class="underline" style="width: 30px;height: 23px">${dormitory1[0]}</p>
                <p class="word">区</p>
                <p class="underline" style="width: 30px;height: 23px">${dormitory1[1]}</p>
                <p class="word">栋</p>
                <p class="underline" style="width: 30px;height: 23px">${dormitory1[2]}<p class="word">房</p>
            </div>
            <div class="cachet5" style="background: none"></div>
        </div>
        <div class="body-third-left">
            <p class="word-bold">学工处（资助中心、宿管中心）：</p>
            <p class="word-bold">&nbsp&nbsp${students1.lxqd.studaffadividesc}</p>
            <p class="word-normal" style="width:264px;padding-top: 0px">&nbsp&nbsp学生资助管理中心</p>
            <p class="word-normal word px">（经办人签名）：</p>
            <p class="underline" style="width: 64px;position: absolute">
            <p class="font" style="position: relative;bottom: 20px;"><c:if test="${students1.suppcent.admin != 'null'}">
                <c:set value="${fn:split(students1.dormitory, '-') }" var="dormitory1"/>
            <c:out value="${students1.suppcent.admin.adminname}"/></c:if></p>
            </p>
            <p class="word-normal word" style="width:264px;padding-top: 1px">&nbsp&nbsp学生宿舍管理中心</p>
            <p class="word-normal word px">（经办人签名）：</p>
            <p class="underline" style="width: 64px;position: absolute;">
            <p class="font" style="position: relative;bottom: 20px"><c:if
                test="${students1.dormmana.admin != 'null'}"><c:out value="${students1.dormmana.admin.adminname}"/></c:if></p>
            </p>
            <div class="date" style="padding-left: 55px;padding-top: 0px">
                <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                    <div class="cachet1" style="background: none"></div>
                </p>
                <span style="display: none">
                <c:if test="${students1.studaffadivi.sealtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.studaffadivi.sealtime, 0, 10), '-') }" var="studaffadivisealtime1"/>
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
                    <div class="cachet1" style="background: none"></div>
                </p>
                <span style="display: none">
                <c:if test="${students1.logimana.sealtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.logimana.sealtime, 0, 10), '-') }" var="logimanasealtime1"/>
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
            <div class="date" style="padding-left: 55px;padding-top: 20px">
                <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                    <div class="cachet2" style="background: none"></div>
                </p>
                <span style="display: none">
                <c:if test="${students1.library.sealtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.library.sealtime, 0, 10), '-') }" var="librarysealtime1"/>
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
            <div class="date" style="padding-left: 55px;padding-top: 30px">
                <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                    <div class="cachet2" style="background: none"></div>
                </p>
                <span style="display: none">
                <c:if test="${students1.hotwater1.sealtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.hotwater1.sealtime, 0, 10), '-') }" var="hotwater1sealtime1"/>
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
            </div>
        </div>
        <div class="body-fifth-left">
            <p class="word-bold">信息中心：</p>
            <p class="word-bold">${students1.lxqd.educandcompdesc}</p>
            <p class="word-normal word px" style="padding-top: 33px">（经办人签名）：</p>
            <p class="underline" style="width: 64px;position: absolute">
            <p class="font" style="position: relative;bottom: -20px"><c:if test="${students1.educandcomp.admin != 'null'}"><c:out
                value="${students1.educandcomp.admin.adminname}"/></c:if></p></p>
            <div class="date" style="padding-left: 55px;padding-top: 44px">
                <p class="word-normal-np">&nbsp&nbsp&nbsp（盖章）
                    <div class="cachet3" style="background: none"></div>
                </p>
                <span style="display: none">
                <c:if test="${students1.educandcomp.sealtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.educandcomp.sealtime, 0, 10), '-') }" var="educandcompsealtime1"/>
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
                    <div class="cachet3" style="background: none"></div>
                </p>
                <span style="display: none">
                <c:if test="${students1.hotwater2.sealtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.hotwater2.sealtime, 0, 10), '-') }" var="hotwater2sealtime1"/>
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
                    <div class="cachet4" style="background: none"></div>
                </p>
                <span style="display: none">
                <c:if test="${students1.acadsign.signtime != 'null'}">
                    <c:set value="${fn:split(fn:substring(students1.acadsign.signtime, 0, 10), '-') }" var="acadsigntime2"/>
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
                    <c:set value="${fn:split(fn:substring(students1.clasteacsign.signtime, 0, 10), '-') }" var="clasteacsignsigntime1"/>
                </c:if>
                </span>
                <p class="underline" style="width: 40px;height: 19px"><c:if
                        test="${students1.clasteacsign.signtime != 'null'}"><c:out
                        value="${clasteacsignsigntime1[0]}"/></c:if>
                </p>
                <p class="word-normal-np">年</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.clasteacsign.signtime != 'null'}"><c:out
                        value="${clasteacsignsigntime1[1]}" /></c:if>
                </p>
                <p class="word-normal-np">月</p>
                <p class="underline" style="width: 24px;height: 19px"><c:if
                        test="${students1.clasteacsign.signtime != 'null'}"><c:out
                        value="${clasteacsignsigntime1[2]}"/></c:if>
                </p>
                <p class="word-normal-np">日</p>
            </div>
        </div>
    </div>
    <div class="buttom">
        ${students1.lxqd.remarkdesc}
    </div>
</div>

</body>
<script>

</script>
<script type="text/javascript">
    // renderPdf
    var downPdf = document.getElementById("downloadPdf");
    var body = document.getElementById('pdfContainer');
    downPdf.onclick = function() {
        html2canvas(body, {
            onrendered:function(canvas) {

                var contentWidth = canvas.width+1;
                var contentHeight = canvas.height-1;
                console.log(contentWidth+'............bbb.....'+contentHeight);
                //一页pdf显示html页面生成的canvas高度;
                var pageHeight = contentWidth / 595.28 * 841.89;
                //未生成pdf的html页面高度
                var leftHeight = contentHeight;

                console.log(pageHeight+'............aaaa.....'+leftHeight);
                //pdf页面偏移
                var position = 0;
                //a4纸的尺寸[595.28,841.89]，html页面生成的canvas在pdf中图片的宽高
                var imgWidth = 595.28;
                var imgHeight = (595.28/contentWidth * contentHeight);
                //返回图片dataURL，参数：图片格式和清晰度(0-1)
                var pageData = canvas.toDataURL('image/png', 1.0);
                // 三个参数，第一个方向，第二个尺寸，第三个尺寸格式
                var pdf = new jsPDF('', 'pt', 'a4');
                //有两个高度需要区分，一个是html页面的实际高度，和生成pdf的页面高度(841.89)
                //当内容未超过pdf一页显示的范围，无需分页
                if (leftHeight < pageHeight) {
                    pdf.addImage(pageData, 'JPEG', 0, 40, imgWidth, imgHeight );
                } else {
                    while(leftHeight > 0) {
                        pdf.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
                        leftHeight -= pageHeight;
                        position -= 841.89;
                        //避免添加空白页
                        if(leftHeight > 0) {
                            pdf.addPage();
                        }
                    }
                }

                pdf.save('lxqd.pdf');
            }
        })
    }
</script>
<script>
    function load() {
        var font = document.getElementsByClassName('font');
        var sealer0 = document.getElementsByClassName('cachet1');
        var sealer1 = document.getElementsByClassName('cachet2');
        var sealer2 = document.getElementsByClassName('cachet3');
        var sealer3 = document.getElementsByClassName('cachet4');
        var sealer4 = document.getElementsByClassName('cachet5');
        for(var i=0;i<font.length;i++){
            if(font[i].innerHTML == ''){
                font[i].classList.add('fontWhite');
            }else {
                font[i].classList.add('fontBlue');
            }
        }
       //判断盖章
        if ("${students1.studaffadivi.admin.adminname}" !=  "" && "${students1.suppcent.admin.adminname}" !=  ""
            && "${students1.dormmana.admin.adminname}" !=  ""){
            sealer0[0].style.background="url(${pageContext.request.contextPath}/img/学工处.png) no-repeat";
        }
        if ("${students1.logimana.admin.adminname}" !=  "" && "${students1.hydropower.admin.adminname}" !=  ""
            && "${students1.drinkwater.admin.adminname}" !=  ""){
            sealer0[1].style.background="url(${pageContext.request.contextPath}/img/后勤管理.png) no-repeat";
        }
        if ("${students1.library.admin1.adminname}" !=  "" && "${students1.library.admin.adminname}" !=  ""){
            sealer1[0].style.background="url(${pageContext.request.contextPath}/img/图书馆.png) no-repeat";
        }
        if ("${students1.hotwater1.admin1.adminname}" !=  "" && "${students1.hotwater1.admin.adminname}" !=  ""){
            sealer1[1].style.background="url(${pageContext.request.contextPath}/img/宇正公司.png) no-repeat";
        }
        if ("${students1.educandcomp.admin1.adminname}" !=  "" && "${students1.educandcomp.admin.adminname}" !=  ""){
            sealer2[0].style.background="url(${pageContext.request.contextPath}/img/信息中心.png) no-repeat";
        }
        if ("${students1.hotwater2.admin1.adminname}" !=  "" && "${students1.hotwater2.admin.adminname}" !=  ""){
            sealer2[1].style.background="url(${pageContext.request.contextPath}/img/纽恩泰公司.png) no-repeat";
        }
        if ("${students1.acadsign.admin.adminname}" !=  "" && "${students1.acadsign.admin.departid}" ==  "20"){
            sealer3[0].style.background="url(${pageContext.request.contextPath}/img/计算机学院.png) no-repeat";
        }
        if ("${students1.acadsign.admin1.adminname}" !=  "" && "${students1.acadsign.admin1.departid}" ==  "20"){
            sealer4[0].style.background="url(${pageContext.request.contextPath}/img/计算机学院.png) no-repeat";
        }
    }
    window.onload = load;
</script>

</html>
