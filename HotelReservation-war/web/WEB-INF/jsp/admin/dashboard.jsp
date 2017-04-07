
<%-- 
    Document   : room
    Created on : Mar 26, 2017, 3:04:40 PM
    Author     : lujamanandhar
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <!-- Core CSS - Include with every page --> 
    <spring:url value="/hotelsys/admin" var="adminrootpath" /> 
     <%@include file="../layouts/springvars.jsp" %>
    <%@include file="../layouts/csslinks.jsp" %>
</head>

<body>
    <!--  wrapper -->
    <div id="wrapper">
        <!-- navbar top -->
        <%@include file="../layouts/topnavbar.jsp" %>
        <!-- end navbar top -->

        <!-- navbar side -->
        <%@include file="../layouts/sidenavbar.jsp" %>
        <!-- end navbar side -->
        <!--  page-wrapper -->
        <div id="page-wrapper">
            <div class="row">
                <!-- Page Header -->
                <div class="col-lg-12">
                    <h1 class="page-header">Dashboard</h1>
                </div>
                <!--End Page Header -->
            </div>
            
            <div class="row">
                <!--quick info section -->
                <div class="col-lg-3">
                    <div class="alert alert-danger text-center">
                        <i class="fa fa-gears fa-3x"></i>&nbsp;<b>20 </b>Rooms added

                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="alert alert-success text-center">
                        <i class="fa fa-gears fa-3x"></i>&nbsp;<b>27 % </b>Room Amenities  
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="alert alert-info text-center">
                        <i class="fa fa-gears fa-3x"></i>&nbsp;<b>1,900</b> Room Types

                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="alert alert-warning text-center">
                        <i class="fa  fa-gears fa-3x"></i>&nbsp;<b>2,000 $ </b>Payment Dues For Rejected Items
                    </div>
                </div>
                <!--end quick info section -->
            </div>
        </div>
        <!-- end page-wrapper -->

    </div>
    <!-- end wrapper -->

    <!-- Core Scripts - Include with every page -->
    <%@include file="../layouts/scriptlinks.jsp" %>
</body>

</html>