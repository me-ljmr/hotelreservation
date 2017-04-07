
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
    <title>Modify Room Information</title>
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
                    <h1 class="page-header">Modify Room Information</h1>
                </div>
                <!--End Page Header -->
            </div>

            <div class="row">
                <div class="col-md-6">
                
               
                    <form id="editform" class="form-horizontal"  action="${adminrootpath}/rooms/edit/${room.id}" method="post">
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
                        <div class="row" >
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>Room Amenities:</label><span>(Select atleast one)</span>
                                 <div class="well" style="max-height: 300px;overflow: auto;">
                                    <ul class="list-group checked-list-box">
                                        <c:forEach  items="${services}" var="item" varStatus="stat">
                                            <c:set var="contains" value="false" />
                                            
                                            <c:forEach items="${room.roomServiceCollection}" var="srv" >
                                                
                                                <c:if test="${item.id eq srv.serviceId.id}">
                                                     
                                                    <c:set var="contains" value="true" />
                                                </c:if>
                                                 
                                                     
                                            </c:forEach>
                                            
                                            <li class="list-group-item" data-checked="${contains}" data-value="${item.id}">
                                                 
                                                ${item.id}&nbsp;-&nbsp; ${item.title}

                                            </li>
<!--                                       
                                       -->
                                        </c:forEach>
                                    </ul>
                                 </div>
                                </div>
                            </div>
                        </div>
                        <div class="checkedServices" id="checkedServices">
                            
                        </div> 
                        <!--<input  type="submit" value="Save"  class="btn btn-success"/>-->
                        <button id="savethisformbutton" class="btn btn-success">Save
                        </button>
                       
                    </form>
                </div>
                </div>

        </div>
        <!-- end page-wrapper -->

    </div>
    <!-- end wrapper -->

    <!-- Core Scripts - Include with every page -->
 <%@include file="../layouts/scriptlinks.jsp" %>
     <script>
            $('#savethisformbutton').on('click', function(event) {
                event.preventDefault(); 
                 var checkedServicesDiv =  document.getElementById("checkedServices");
                  
                    while (checkedServicesDiv.firstChild) {
                        console.log(checkedServicesDiv.firstChild)
                        checkedServicesDiv.removeChild(checkedServicesDiv.firstChild);
                    }
                 
                var checkedItems = {}, counter = 0;
                $(".checked-list-box li.active").each(function(idx, li) {
                    checkedItems[counter] = $(li).attr("data-value");
                    var selServices = document.createElement("input");
                    selServices.setAttribute("name","services");
                    selServices.setAttribute("class","nuga");
                    selServices.setAttribute("type","hidden");
                    selServices.setAttribute("value",checkedItems[counter]);
                    checkedServicesDiv.appendChild(selServices);
                    counter++;
                });
                $("#editform").submit();
                
            });

    </script>
    
</body>

</html>
