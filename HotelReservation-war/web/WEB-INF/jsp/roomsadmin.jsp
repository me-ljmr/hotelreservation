<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="java.util.List"%>
<%@page import="com.awesomegroup.sessionbean.RoomSessionBeanRemote"%>
<%@page import="com.awesomegroup.entity.Room"%>
<%@page import="com.awesomegroup.sessionbean.HotelSessionBeanRemote"%>
<%@page import="javax.naming.InitialContext"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />
        <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapjs" />
        <spring:url value="/resources/js/jquery.js" var="jquery"/> 
        <spring:url value="/hotelsys/admin" var="adminrootpath" />    
        <script src="${bootstrapjs}"></script> 
        <script src="${jquery}"></script>  
        <link href="${bootstrapcss}" rel="stylesheet" /> 
         
        <title>Rooms</title>
    </head>

    <body>
        <div class="container">
            <h2>Room List</h2>
            <a href="${adminrootpath}/rooms/new" class="pull-right btn btn-success" >Create New</a>
            <div class="row">
                <c:if test="${roomsaved != null}">
                    <label class="alert alert-dismissable alert-success">${roomsaved}</label>
                </c:if>
            </div>
            <div class="row">
                <table class="table">
                    
                    <tr>
                        <th>Room ID</th>
                        <th>Room Number</th>
                        <th>Floor Number</th>
                        
                        <th>Type</th>
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

                            <td>${room.roomTypeId.description}-${room.roomTypeId.rate}</td>
                            <td><a href="/rooms/edit/${room.id}" class="btn btn-primary">Edit</a></td>
                        </tr>
                    </c:forEach>
                    </table>
                </div>
        </div>
         
        
        
    
        
    </body>
</html>
