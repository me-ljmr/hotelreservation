<%@page import="com.awesomegroup.entity.UserInfo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="java.util.List"%>
<%@page import="com.awesomegroup.sessionbean.RoomSessionBeanRemote"%>
<%@page import="com.awesomegroup.entity.Room"%>
<%@page import="com.awesomegroup.sessionbean.HotelSessionBeanRemote"%>
<%@page import="javax.naming.InitialContext"%>
<%
    Object userinfo = request.getSession().getAttribute("whoisloggedin");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />
        <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapjs" />
        <spring:url value="/resources/css/main.css" var="maincss" />
        <spring:url value="/resources/js/jquery.js" var="jquery"/> 
        <spring:url value="/hotelsys" var="userrootpath" /> 
        <spring:url value="/resources/img" var="imagepath" />

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="${bootstrapjs}"></script> 
        <script src="${jquery}"></script>  
        <link href="${bootstrapcss}" rel="stylesheet" /> 
        <link rel="stylesheet" href="${maincss}" >
        <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
        <title>Hotel Awesome</title>
    </head>

    <body>

        <%
            if (userinfo != null && userinfo.getClass() == UserInfo.class) {
                UserInfo user = (UserInfo) userinfo;
        %>                
        <!-- Navigation -->
    <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top" style="margin-bottom: 100px;background-color: black; color: white">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only"></span>  <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="/awesomegroup">Hotel Awesome</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right " >
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a style="color: white"  class="page-scroll" href="reservation">Reservations</a>
                    </li>
                    <li>
                        <a style="color: white"  class="page-scroll" href="booking">Book a Room</a>
                    </li>
                    <li>
                        <a style="color: white"  class="page-scroll" href="contact">Contact</a>
                    </li>
                    <li  >
                        <a href="${pageContext.request.contextPath}/hotelsys/logout" style="color: white"  class="page-scroll"><%= user.getFirstName()%> <span class="glyphicon glyphicon-log-out"></span> Logout</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <%
    } else {
    %> 
    <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top" style="margin-bottom: 100px;background-color: black; color: white">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only"></span>  <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="/awesomegroup">Hotel Awesome </a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a style="color: white"  class="page-scroll" href="reservation">Reservations</a>
                    </li>
                    <li>
                        <a style="color: white"  class="page-scroll" href="#">Services</a>
                    </li>
                    <li>
                        <a style="color: white"  class="page-scroll" href="contact">Contact</a>
                    </li>
                    <li>
                        <a style="color: white"  class="page-scroll" href="booking">Book a Room</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>


    <%
        }
    %>