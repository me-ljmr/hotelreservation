

<%-- 
    Document   : roomtypes
    Created on : Mar 26, 2017, 3:04:40 PM
    Author     : lujamanandhar
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="java.util.List"%>
 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Room Type Admin</title>
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
                    <h1 class="page-header">Room Types</h1>
                </div>
                <!--End Page Header -->
            </div>

            <div >
                 
                
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
                <form id="roomtypeform" method="post" action="" >
                    <input type="hidden" id="roomtypeid" name="roomtypeid" value="0" /> 
                    <input type="hidden" id="mode" name="mode" value="save" />
                <div class="row">
                    <div class="col-md-4">
                         
                        <input value="${title}" type="text" id="title" name="title" class="form-control" placeholder="Title" title="Room Type Description"/> 
                        
                    </div>
                    <div class="col-md-2">
                        <input value="${rate}" type="text" id="rate" title="Room Type Detail" name="rate" placeholder="Rate" class="form-control"/> 
                         
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            
                            <input type="submit" value="Add to List" class="btn btn-success form-control addtolistbutton" />
                            <button class="btn btn-primary form-control updatebutton" style="display:none;">Update</button>
                            
                         </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <input type="button" class="btn btn-primary cancelbutton form-control" style="display:none;" value="Cancel" />
                        </div>
                    </div>
                </div>
                        <div class="row">
                            <div class="col-md-6">
                                <textarea rows="4" cols="1" class="form-control" placeholder="Description" id="description" name="description"></textarea>
                                      
                            </div>
                        </div>
                </form>
                <div class="row">
                    <table class="table table-bordered ">

                        <tr>
                            <th>Title</th>
                            <th style="width:350px;">Description</th>
                            <th>Rate</th>
                             
                            <th>Action</th>
                            
                        </tr> 
                    <c:if test="${roomtypes eq null}">
                        <tr>
                            <td colspan="5">
                            <span class="label label-info">No roomtypes available</span>
                            
                            </td>
                        </tr>
                    </c:if>
  
                        <c:forEach var="roomtype" items="${roomtypes}">
                            <tr>
                                
                                <td class="title">${roomtype.title}</td>
                                <td class="description">${roomtype.description}</td> 
                                <td class="rate">${roomtype.rate}</td>
                                 
                                 
                                <td>
                                    <button class="btn btn-primary editbutton" data-id="${roomtype.id}">Edit</button>
                                    <button class="btn btn-danger deletebutton" data-id="${roomtype.id}">Delete</button>
                                </td>
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
    
   <script>
        $(".editbutton").click(
            function(){
                $("#rate").val($(this).closest("tr").find(".rate").text());
                $("#title").val($(this).closest("tr").find(".title").text());
                $("#description").val($(this).closest("tr").find(".description").text());
                $(".updatebutton").css("display", "block");
                $(".cancelbutton").css("display", "block");
                $(".addtolistbutton").css("display", "none");
                $("#roomtypeid").val($(this).attr("data-id"));
            }    
        );
        $(".cancelbutton").click(
            function(){
                $("#rate").val($(this).closest("tr").find(".rate").text());
                $("#title").val($(this).closest("tr").find(".title").text());
                $("#description").val($(this).closest("tr").find(".description").text());
                $(".updatebutton").css("display", "none");
                $(".cancelbutton").css("display", "none");
                $(".addtolistbutton").css("display", "block");
                $("#roomtypeid").val("0");
            }    
        );
        $(".deletebutton").click(
            function(){
               $("#roomtypeid").val($(this).attr("data-id"));  
               $("#mode").val("delete");
               $("#roomtypeform").submit();
            });
    </script>
</body>

</html>
