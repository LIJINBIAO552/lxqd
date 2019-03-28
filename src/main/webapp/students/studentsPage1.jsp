<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <![endif]-->
    <title>Plus Coming Soon Template </title>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    <link rel="shortcut icon" href="assets/img/favicon.ico" />
    <!--PUT YOUR FAVICON HERE-->
    <!--REQUIRED STYLE SHEETS-->
    <!--MAIN BOOTSTRAP STYLE-->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
    <!--FONT AWESOME STYLE -->
    <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
    <!--VEGAS IMAGE STYLE -->
    <link href="${pageContext.request.contextPath}/css/jquery.vegas.min.css" rel="stylesheet" />
    <!--COUNTDOWN STYLE -->
    <link href="${pageContext.request.contextPath}/css/countdown.css" rel="stylesheet" />
    <!--CUSTOM STYLE -->
    <link href="${pageContext.request.contextPath}/css/CountdownStyle.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/studentsPage.css" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<span id="biyetime" style="display: none"><fmt:formatDate value="${sessionScope.student.procedure.eventtime}" pattern="yyyy/MM/dd"/></span>
<!-- MAIN CONTAINER -->
<div class="container">
    <!-- HOME SECION -->
    <section id="home-section">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <h1>肇庆学院离校清单管理系统</h1>
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="divider"></div>
                    <h3 class="center">亲爱的同学！距离毕业还有</h3>
                    <!-- ./Headings -->
                    <div class="divider"></div>
                    <div id="counter"></div>
                    <div id="counter-default" class="row">
                        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                            <div class="inner">
                                <div id="day-number" class="timer-number"></div>
                                <div class="timer-text">DAYS</div>
                                <div class="progress medium  active">
                                    <div id="day-bar" class="progress-bar bk-blue"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                            <div class="inner">
                                <div id="hour-number" class="timer-number"></div>
                                <div class="timer-text">HOURS</div>
                                <div class="progress medium  active">
                                    <div id="hour-bar" class="progress-bar bk-green"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                            <div class="inner">
                                <div id="minute-number" class="timer-number"></div>
                                <div class="timer-text">MINUTES</div>
                                <div class="progress medium  active">
                                    <div id="minute-bar" class="progress-bar bk-pink"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                            <div class="inner">
                                <div id="second-number" class="timer-number"></div>
                                <div class="timer-text">SECOND</div>

                                <div class="progress medium  active">
                                    <div id="second-bar" class="progress-bar bk-red"></div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!-- ./Counter -->


                </div>
            </div>
        </div>
        <!--./row-->
    </section>
</div>
<!-- END MAIN CONTAINER -->
<!--REQUIRED SCRIPTS FILES-->  <!--SCRIPTS AT END SO PAGE WILL TAKE LESS TIME TO LOAD-->
<!--REQUIRED CORE JQUERY SCRIPTS FILE-->
<script src="${pageContext.request.contextPath}/js/jquery-2.1.3.min.js"></script>
<!--REQUIRED BOOTSTRAP SCRIPTS FILE-->
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<!--REQUIRED SCRIPT FILES FOR JQUERY COUNTDOWN-->
<script src="${pageContext.request.contextPath}/js/jquery.countdown.js"></script>
<script src="${pageContext.request.contextPath}/js/countdown.js"></script>
<!--REQUIRED SCRIPT FILE FOR VEGAS BACKGROUND SLIDESHOW-->
<script src="${pageContext.request.contextPath}/js/jquery.vegas.min.js"></script>
<!--REQUIRED SCRIPT FOR SCROLLING MENU LINKS-->
<script src="${pageContext.request.contextPath}/js/jquery.easing.1.3.js"></script>
<!--REQUIRED CUSTOM SCRIPT FILE-->
<%--<script src="${pageContext.request.contextPath}/js/custom-image.js"></script>--%>
</body>
</html>
