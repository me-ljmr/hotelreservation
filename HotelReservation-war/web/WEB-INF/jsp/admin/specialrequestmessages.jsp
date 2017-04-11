
<%-- 
    Document   : roombyservice
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
    <title>Special Service Requests</title>
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
                    <h1 class="page-header">Special Service Requests</h1>
                </div>
                <!--End Page Header -->
            </div>
                <div class="row">
                    <c:if test = "${errors != null}">
                        <div class="col-lg-12">
                        <label class="alert alert-danger">
                            <c:forEach var="error" items="${errors}">
                                <span>${error}</span><br>
                            </c:forEach>
                            
                        
                        </label>
                        </div>
                    </c:if>
                    <c:if test="${messages != null}">
                        <div class="col-lg-12">
                        <label class="alert alert-success">
                            <c:forEach var="message" items="${messages}">
                                <span>${message}</span><br>
                            </c:forEach>
                        </label>
                        </div>
                    </c:if>
                </div>
             <div class="row">
                 <div class="col-md-6">
                    <form id="searchform" class="form-horizontal"  action="" method="GET" >
 
                        <div class="row" >
                             
                            <div class="col-md-3">
                                 <input  type="submit" id="refresh" class="form-control btn btn-primary" value="Refresh">
                                 &nbsp;
                            </div>
                        </div>
                         
                        
                    </form>
                 </div>
             </div>
              <div class="row">
                  <div class="panel panel-body">
                    <table class="table table-bordered ">

                        <tr>
                            <th style="width:15%;">Message ID</th>
                            <th style="width:20%;">From</th>
                            <th style="width:50%;">Message</th> 
                            <th style="width: 15%;">Action</th>
                            
                        </tr>


                     


                        
                        
                        </table>
                      </div>
                    </div>
            </div>

        </div>
        <!-- end page-wrapper -->

     <!-- end wrapper -->

    <!-- Core Scripts - Include with every page -->
    <%@include file="../layouts/scriptlinks.jsp" %>
     

</body>

</html>
