
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
    <title>Add a Room</title>
    


 
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
                    <h1 class="page-header">Add a new room</h1>
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
                    <form id="newform" class="form-horizontal"  action="" method="POST" >
                       
<!--                            <div class="col-md-4">-->
                                <div class="form-group">
                                <label >Room Number:</label><input type="text" name="roomnumber" class="form-control"/> 
                                </div>
                            <!--</div>-->
                            <!--<div class="col-md-4">-->
                                <div class="form-group">
                                <label >Floor:</label><input type="number" name="floor" class="form-control"/> 
                                </div>
                            <!--</div>-->
                            <!--<div class="col-md-4">-->
                                <div class="form-group">
                                <label >Type:</label>


                                <select name="roomtype" class="form-control">
                                    <c:forEach  items="${roomtypes}" var="item" varStatus="stat">
                                        <option value="${item.id}">${item.description} - ${item.rate}</option>
                                    </c:forEach>
                                </select>
                                </div>
                            <!--</div>-->
                         
<!--                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>Pictures</label>
                                    <input type="file" accept="image/*" id="picbrowser" multiple class="hidden"/>


                                    <nav class="collection_wrapper bmg" >
                                        <ul id="viewcard" class="viewcard"  >
                                            <li>
                                                <span class="btn btn-primary fa fa-upload fa-3x" onclick="ibrowse()" title="Upload Pictures for this room" > 
                                                </span>

                                            </li>

                                        </ul>       
                                    </nav>

                                </div>
                            </div>
                        </div>-->
                        <div class="row" >
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>Room Amenities:</label><span>(Select atleast one)</span>
                                 <div class="well" style="max-height: 300px;overflow: auto;">
                                    <ul class="list-group checked-list-box">
                                        <c:forEach  items="${services}" var="item" varStatus="stat">
                                            <li class="list-group-item" data-value="${item.id}">${item.id} - ${item.title}</li> 
 
                                        </c:forEach>
                                    </ul>
                                 </div>
                                </div>
                            </div>
                        </div>
                        <div class="checkedServices" id="checkedServices">
                            
                        </div> 
                        <button id="savethisformbutton" class="btn btn-success">Save
                        </button>
                    </form>
                 </div>
             </div>
            </div>

        </div>
        <!-- end page-wrapper -->

     <!-- end wrapper -->

    <!-- Core Scripts - Include with every page -->
<%@include file="../layouts/scriptlinks.jsp" %>
     <script>
            $('#savethisformbutton').on('click', function(event) {
                event.preventDefault(); 
                 var checkedServicesDiv =  document.getElementById("checkedServices");
                  
                    while (checkedServicesDiv.firstChild) {
                         
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
                 
                $("#newform").submit();
                
            });

    </script>

</body>

</html>
