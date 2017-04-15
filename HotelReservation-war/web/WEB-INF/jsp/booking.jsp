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

<%@ include file="header.jsp" %>

<section class="bg-info">
    <div class="container">
        <div class="row">
            <div class="col-md-12 " style="padding-left: 10px">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Your Booking</h3>
                    </div>
                    <div class="panel-body">       
                        <table class="table table-bordered ">
                            <tr>
                                <th>Booked Date</th>
                                <th>Check-In</th>
                                <th>Check-Out</th>
                                <th>Room</th>
                                <th>Payment</th>
                                <th>Status</th>   
                                <th></th>
                            </tr>

                            <c:forEach var="reservation" items="${reservations}">
                                <c:if test="${reservation eq null}">
                                    <tr>
                                        <td colspan="5">
                                            <span class="label label-info">No booking yet</span>
                                        </td>
                                    </tr>
                                </c:if>
                                <tr >
                                    <td>${reservation.bookedDate}</td>
                                    <td>${reservation.dateFrom}</td>
                                    <td>${reservation.dateTill}</td>
                                    <td>${reservation.roomId.roomTypeId.title}</td>
                                    <td><h4>$${reservation.roomId.roomTypeId.rate}</h4></td>
                                    <c:choose>
                                        <c:when test="${reservation.status eq 'BK'}">
                                            <td><span name="status">${"Reserved"}</span></td>    
                                            </c:when>
                                            <c:when test="${reservation.status eq 'CC'}">
                                            <td><span name="status">${"Canceled"}</span> </td>    
                                        </c:when>
                                        <c:when test="${reservation.status eq 'CI'}">
                                            <td><span name="status">${"Checked In"}</span></td>    
                                        </c:when>
                                        <c:when test="${reservation.status eq 'CO'}">
                                            <td><span name="status">${"Checked Out"} </span></td>    
                                        </c:when>
                                        <c:otherwise>
                                            <td>&nbsp;</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>  
                                        <a  href="${userrootpath}/booking/cancel/${reservation.id}" class="btn btn-danger btn-sm">Cancel</a>

                                    </td>
                                </tr>         
                            </c:forEach>

                        </table>
                        <div class="col-md-3"> 
                            <a href="#newBooking" class="btn btn-lg btn-success btn-block">New Booking</a>                         
                        </div> 
                    </div>

                </div>
            </div>
        </div>

        <p style="padding-top: 300px"></p>
        <div id="newBooking" class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Book a new room</h3>
                    </div>
                    <div class="panel-body bookingtb"> 
    
                        <form action="${userrootpath}/booking/new" method="post">
                        <table border="0">
                            <thead>
                                <tr>
                                    <th><label>Date From: </label></th>
                                    <th><label>Date Till: </label></th>
                                    <th><label>Room & Rate</label></th> 
                                    <!--<th><label>Nights</label></th>-->
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="controls input-append date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd" >  

                                        <input class="form-control" size="16" type="text" name="date_from" value="" readonly>
                                        <span class="add-on"><i class="icon-search icon-large icon-black"></i></span>
                                        <span class="add-on"><i class="icon-th icon-black"></i></span>
                                        <span > </span> 
                                    </td>
                                    <td class="controls input-append date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">

                                        <input class="form-control" size="16" type="text" name="date_till" value="" readonly>
                                        <span class="add-on"><i class="icon-search icon-large icon-black"></i></span>
                                        <span class="add-on"><i class="icon-th icon-black"></i></span>

                                    </td>
                                    <td>
                                        <span style="  margin-right: 20px">                                     
                                            <select name="roomselected" class="form-control">
                                                <c:forEach var="room" items="${rooms}">                                                  
                                                    <option selected value="${item.id}">${room.roomTypeId.title} --$${room.roomTypeId.rate} </option>                                 
                                                </c:forEach> 
                                            </select>
                                        </span>
                                    </td>
                                    <!--<td> </td>-->
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <!--<td></td>-->
                                    <td>Total: </td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="col-md-3"> 
                            <a href="booking"  class="btn btn-lg btn-default btn-block">Back</a>                       
                        </div> <span style="padding: 0 5px 0 5px"></span>
                        <div class="col-md-3"> 
                            <input class="btn btn-lg btn-success btn-block" type="submit" value="Submit"  />                       
                        </div> 
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>


<%@ include file="footer.jsp" %>
