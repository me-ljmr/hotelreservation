
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
    <title>Reservation Details</title>
    <spring:url value="/hotelsys/admin" var="adminrootpath" />    
    <%@include file="../layouts/springvars.jsp" %>
    <%@include file="../layouts/csslinks.jsp" %>
      <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

 
    
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
                    <h1 class="page-header">Room Reservation Detail</h1>
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
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>Floor</label>
                                    <input type="text" class="form-control" name="floor" value="${floor}">
                                    <label for="amount">Price range:</label>
                                    
                                    <div class="row">
                                        <div class="col-md-3">
                                             <input class="form-control" type="text" readonly name="priceMin" id="price-min" value="${priceMin}" >
                                        </div>
                                        <div class="col-md-6">
                                            <div id="slider-range"></div>
                                        </div>
                                        <div class="col-md-3">
                                            <input class="form-control" type="text" readonly name="priceMax" id="price-max" value="${priceMax}">
                                        </div>
                                    </div>
  
  
                                    <label>Date range</label>
                                    <div class="row">
                                        <div class="col-md-5">
                                            <input type="text" class="form-control" name="from" id="from" value="${from}"> till
                                        </div>
                                        <div class="col-md-2">
                                            till
                                        </div>
                                        <div class="col-md-5">
                                            <input type="text" class="form-control" name="to" id="to" value="${to}">
                                        </div>
                                    </div>
                                    
                                    
                                    
                                    
                                </div>
                                 <input type="button" id="searchbutton" class="btn btn-primary pull-right" value="search"  >
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
                            <th>Booked Date</th>
                            <th>Customer Name</th>
                            <th>Contact Number</th>
                            <th>Floor</th>
                            <th>Room Number</th>
                            <th>Check-In</th>
                            <th>Check-Out</th>
                            <th>Price</th>
                            <th>Status</th>
                            
                        </tr>
 
                         
                    <c:if test="${reservations eq null}">
                        <tr>
                            <td colspan="7">
                        <span class="label label-info">No reservations found</span>
                            
                            </td>
                        </tr>
                    </c:if>


                        
                        <c:forEach var="r" items="${reservations}">
                            <tr >
                                <td>${r.bookedDate}</td>
                                 <td>${r.userId.firstName}&nbsp;${r.userId.lastName}</td>
                                 <td>${r.userId.contactNumber}</td>
                                 <td>${r.roomId.floor}</td>
                                 <td>${r.roomId.roomNumber}</td>
                                 <td>${r.dateFrom}</td>
                                 <td>${r.dateTill}</td>
                                 <td><h4>$${r.roomId.roomTypeId.rate}</h4></td>
                                 <c:choose>
                                    <c:when test="${r.status eq 'BK'}">
                                       <td>${"Reserved"}</td>    
                                    </c:when>
                                    <c:when test="${r.status eq 'CC'}">
                                        <td>${"Canceled"}<label class="badge badge-success">Available</label></td>    
                                    </c:when>
                                    <c:when test="${r.status eq 'CI'}">
                                        <td>${"Checked In"}</td>    
                                    </c:when>
                                    <c:when test="${r.status eq 'CO'}">
                                        <td>${"Checked Out"}<label class="badge badge-success">Available</label></td>    
                                    </c:when>
                                    <c:otherwise>
                                        <td>&nbsp;</td>
                                    </c:otherwise>
                                </c:choose>
                                  
                                 
                                    
                                
                            </tr>
                            <tr >
                                 
                                <td colspan="4">
                                    <div><div>Available Amenities:</div>
                                        <c:forEach var="service" items="${r.roomId.roomServiceCollection}">
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
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $( function() {
          var dateFormat = "mm/dd/yy",
            from = $( "#from" )
              .datepicker({
                  
                defaultDate: "+1w",
                
                changeMonth: true,
                numberOfMonths: 3
              })
              .on( "change", function() {
                  
                to.datepicker( "option", "minDate", getDate( this ) );
                
              }),
            to = $( "#to" ).datepicker({
              defaultDate: "+1w",
              changeMonth: true,
              numberOfMonths: 3
            })
            .on( "change", function() {
              from.datepicker( "option", "maxDate", getDate( this ) );
            });
            $( "#from" ).datepicker("option","dateFormat","yy-mm-dd");
            $( "#to" ).datepicker("option","dateFormat","yy-mm-dd");

          function getDate( element ) {
            var date;
            try {
                
              date = $.datepicker.parseDate( dateFormat, element.value );
              
              
            } catch( error ) {
              date = null;
            }

            return date;
          }
        } );
        </script>
    <script>
        $( function() {
          $( "#slider-range" ).slider({
            range: true,
            min: 0,
            max: 1200,
            values: [ $( "#price-min" ).val(), $( "#price-max" ).val()==0?500: $( "#price-max" ).val()],
            slide: function( event, ui ) {
              $( "#price-min" ).val( ui.values[0] );
              $( "#price-max" ).val(  ui.values[ 1 ] );
            }
          });
          $("#price-min").val(  $( "#slider-range" ).slider( "values", 0 ));
          $("#price-max").val(  $( "#slider-range" ).slider( "values", 1 ));
          
        } );
    </script>
     <script>
         
            $('#searchbutton').on('click', function(event) {
                 event.preventDefault();
                 //console.log($(".servicescombo").select2("val"));
                 
                $("#searchform").submit();
                
            });
         
         
         
    </script> 
    

</body>

</html>
