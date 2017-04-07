

<%-- 
    Document   : rooms
    Created on : Mar 26, 2017, 3:04:40 PM
    Author     : lujamanandhar
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="java.util.List"%>
 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rooms Admin</title>
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
                    <h1 class="page-header">Rooms<a href="${adminrootpath}/rooms/new" class="pull-right btn btn-success" >Create New</a></h1>
                </div>
                <!--End Page Header -->
            </div>

            <div >
                 
                
                <div class="row">
                    <c:if test="${roomsaved != null}">
                        <label class="alert alert-dismissable alert-success">${roomsaved}</label>
                    </c:if>
                </div>
                <div class="row">
                    <table class="table table-bordered">

                        <tr>
                            <th>Room ID</th>
                            <th>Room Number</th>
                            <th>Floor Number</th>

                            <th style="width: 400px;">Type</th>
                            <th>Action</th>
                        </tr>


                    <c:if test = "${error != null}">
                        <label class="alert alert-danger">${error}</label>
                    </c:if>

                         
                    <c:if test="${rooms eq null}">
                        <tr>
                            <td colspan="5">
                        <span class="label label-info">Room list is empty</span>
                            
                            </td>
                        </tr>
                    </c:if>


                        
                        <c:forEach var="room" items="${rooms}">
                            <tr>

                                <td>${room.id}</td>
                                <td>${room.roomNumber}</td>
                                <td>${room.floor}</td>

                                <td>
                                    <div>${room.roomTypeId.description} - $${room.roomTypeId.rate}</div>
                                    <div>
                                        <c:forEach var="service" items="${room.roomServiceCollection}">
                                            <label class="label label-info">${service.serviceId.id} - ${service.serviceId.title}</label>
                                            
                                        </c:forEach>
                                        
                                    </div>
                                </td>
                                <td><a href="${adminrootpath}/rooms/edit/${room.id}" class="btn btn-primary">Edit</a></td>
                            </tr>
                        </c:forEach>
                        </table>
                    </div>
            </div>

        </div>
        <!-- end page-wrapper -->

    </div>
    <!-- end wrapper -->

    <!-- Core Scripts - Include with every page -->
    <%@include file="../layouts/scriptlinks.jsp" %>

</body>

</html>
