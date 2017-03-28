<%-- 
    Document   : room
    Created on : Mar 26, 2017, 3:04:40 PM
    Author     : lujamanandhar
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.awesomegroup.entity.RoomType"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room Editor</title>
        <spring:url value="/hotelsys/admin" var="adminrootpath" />       
       <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />
        <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapjs" />
        <spring:url value="/resources/js/jquery.js" var="jquery"/> 
        
        <script src="${bootstrapjs}"></script> 
        <script src="${jquery}"></script>  
        <link href="${bootstrapcss}" rel="stylesheet" /> 
        
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                <h2>Room Information</h1>
               
                <form class="form-horizontal"  action="${adminrootpath}/rooms/new" method="post">
                    <label >Room Number:</label><input type="text" name="roomnumber" class="form-control"/> 
                    <label >Floor:</label><input type="number" name="floor" class="form-control"/> 
                    <label >Type:</label>
                    
                     
                    <select name="roomtype" class="form-control">
                        <c:forEach  items="${roomtypes}" var="item" varStatus="stat">
                            <option value="${item.id}">${item.description}- ${item.rate}</option>
                        </c:forEach>
                    </select>
   
                    <input type="submit" value="Save"  class="btn btn-success"/>
                </form>
                </div>
                </div>
            </div>
    </body>
</html>
