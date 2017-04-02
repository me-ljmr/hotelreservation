
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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modify Room Information</title>
    <!-- Core CSS - Include with every page -->
    <spring:url value="/assets/plugins/bootstrap/bootstrap.css" var="bootstrapcss" />
    <spring:url value="/assets/font-awesome/css/font-awesome.css" var="fontawesomecss" />
    <spring:url value="/assets/plugins/pace/pace-theme-big-counter.css" var="themebigcss" />
    <spring:url value="/assets/css/style.css" var="stylecss" />
    <spring:url value="/assets/css/main-style.css" var="mainstylecss" />
    <spring:url value="/assets/plugins/uploader/uploadfile.css" var="uploadfilecss" />
      <spring:url value="/assets/img/logo.png" var="logo" />
          <spring:url value="/assets/plugins/jquery-1.10.2.js" var ="jqueryjs" />
    <spring:url value="/assets/plugins/bootstrap/bootstrap.min.js" var="bootstrapjs" />
    <spring:url value="/assets/plugins/metisMenu/jquery.metisMenu.js" var="jquerymetismenujs" />
    <spring:url value="/assets/plugins/pace/pace.js" var="pacejs" />
    <spring:url value="/assets/scripts/siminta.js" var = "simintajs" />
    <spring:url value="/assets/plugins/uploader/jquery.uploadfile.js" var="jqueryuploadfilejs" />
    
    <link href="${bootstrapcss}" rel="stylesheet" />
    <link href="${fontawesomecss}" rel="stylesheet" />
    <link href="${themebigcss}" rel="stylesheet" />
    <link href="${stylecss}" rel="stylesheet" />
    <link href="${mainstylecss}" rel="stylesheet" />
 <link href="${uploadfilecss}" rel="stylesheet" />
    <spring:url value="/hotelsys/admin" var="adminrootpath" />    
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
                    <h1 class="page-header">Modify Room Information</h1>
                </div>
                <!--End Page Header -->
            </div>

            <div class="row">
                <div class="col-md-6">
                
               
                    <form class="form-horizontal"  action="${adminrootpath}/rooms/new" method="post">
                        <div class="form-group">
                        <label >Room Number:</label><input type="text" name="roomnumber" value="${room.roomNumber}" class="form-control"/> 
                        </div>
                        <div class="form-group">
                        <label >Floor:</label><input type="text" name="floor" value="${room.floor}" class="form-control"/> 
                        </div>
                        <div class="form-group">
                        <label >Type:</label>


                        <select name="roomtype" class="form-control" >
                            <c:forEach  items="${roomtypes}" var="item" varStatus="stat" >
                                <c:if test="${room.roomTypeId.id == item.id}" >
                                    <option selected value="${item.id}">${item.description}- ${item.rate}</option>
                                </c:if>
                                <c:if test="${room.roomTypeId.id != item.id}">
                                    <option value="${item.id}">${item.description}- ${item.rate}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        </div>
                        
                        <input type="submit" value="Save"  class="btn btn-success"/>
                    </form>
                </div>
                </div>

        </div>
        <!-- end page-wrapper -->

    </div>
    <!-- end wrapper -->

    <!-- Core Scripts - Include with every page -->
    <script src="${jqueryjs}"></script>
    <script src="${bootstrapjs}"></script>
    <script src="${jquerymetismenujs}"></script>
    <script src="${pacejs}"></script>
    <script src="${simintajs}"></script>
    <script src="${jqueryuploadfilejs}"></script>
</body>

</html>
