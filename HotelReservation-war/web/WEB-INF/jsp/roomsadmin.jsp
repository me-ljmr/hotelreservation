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
        
        <script src="${bootstrapjs}"></script> 
        <script src="${jquery}"></script> 
        
        <link href="${bootstrapcss}" rel="stylesheet" /> 
         
        <title>Rooms</title>
    </head>

    <body>
        <div class="container">
            <h1>${title}</h1>
            <div class="row">
                <c:if test = "${error != ''}">
                    <div>${error}</div>
                </c:if>

                <c:if test="${rooms eq null}">
                    <span class="alert alert-danger">Room list is empty</span>
                </c:if>
            </div>
            <div class="row">
                <table class="table">
                    <tr>
                        <th>Room ID</th>
                        <th>Room Number</th>
                        <th>Floor Number</th>
                        
                        <th>Type</th>
                    </tr>
                    
                
                    <c:forEach var="room" items="${rooms}">
                        <tr>
                            
                            <td>${room.Id}</td>
                            <td>${room.roomNumber}</td>
                            <td>${room.floor}</td>

                            <td>${roomTypeId.description}</td>
                        </tr>
                    </c:forEach>
                    </table>
                </div>
        </div>
         
        
        
    
        
    </body>
</html>
