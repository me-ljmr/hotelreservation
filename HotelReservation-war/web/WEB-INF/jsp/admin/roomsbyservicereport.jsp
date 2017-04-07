
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
    <title>Rooms by Amenities</title>
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
                    <h1 class="page-header">Rooms by Amenities </h1>
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
                    <form id="searchform" class="form-horizontal"  action="" method="POST" >
 
                        <div class="row" >
                            <div class="col-md-11">
                                <div class="form-group">
                                     
                                    <select  name="services" class="form-control servicescombo" multiple="multiple">
                                        <c:forEach var="service" items="${services}">
                                            <option value="${service.id}">${service.title}</option> 
                                        </c:forEach>
                                    </select>
                                    
                            
                                </div>
                            </div>
                            <div class="col-md-1">
                                 <input type="button" id="searchbutton" class="btn btn-primary" value="search">
<!--                                <button id="searchbutton" class="btn btn-primary">Search
                                </button>-->
                            </div>
                        </div>
                        <div class="checkedServices" id="checkedServices">
                            
                        </div> 
                        
                    </form>
                 </div>
             </div>
              <div class="row">
                  <div class="panel panel-body">
                    <table class="table table-bordered ">

                        <tr>
                            <th>Room ID</th>
                            <th>Room</th>
                            <th>Floor</th>
                            <th>Price</th>
                            <th style="width: 400px;">Description</th>
                            
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
                            <tr >
 
                                <td rowspan="2">${room.id}</td>
                                <td>${room.roomNumber}</td>
                                <td>${room.floor}</td>
                                <td><h4>$${room.roomTypeId.rate}</h4></td>
                                <td>
                                    <div>${room.roomTypeId.description}</div>
                                   
                                </td>
                                 
                            </tr>
                            <tr >
                                 
                                <td colspan="4">
                                    <div><div>Available Amenities:</div>
                                        <c:forEach var="service" items="${room.roomServiceCollection}">
                                            <label class="label label-default">${service.serviceId.title}</label>
                                            
                                        </c:forEach>
                                        
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </table>
                      </div>
                    </div>
            </div>

        </div>
        <!-- end page-wrapper -->

     <!-- end wrapper -->

    <!-- Core Scripts - Include with every page -->
    <%@include file="../layouts/scriptlinks.jsp" %>
     <script>
         
            $('#searchbutton').on('click', function(event) {
                 event.preventDefault();
                 //console.log($(".servicescombo").select2("val"));
                
                var checkedServicesDiv =  document.getElementById("checkedServices");
                  
                while (checkedServicesDiv.firstChild) {

                    checkedServicesDiv.removeChild(checkedServicesDiv.firstChild);
                }
                 
                var checkedItems={} ;
                checkedItems = $(".servicescombo").select2("val");
                $.each(checkedItems,function(i,v){
                    
                    console.log(v);
                    var selServices = document.createElement("input");
                    selServices.setAttribute("name","services");
                    selServices.setAttribute("class","nuga");
                    selServices.setAttribute("type","hidden");
                    selServices.setAttribute("value",v);
                    checkedServicesDiv.appendChild(selServices);
                });
                 
                $("#searchform").submit();
                
            });
         
        $(document).ready(function() {
            $(".servicescombo").select2({
                placeholder: "Type some amenities title"
                
                
            });
             
        });
         
    </script> 

</body>

</html>
