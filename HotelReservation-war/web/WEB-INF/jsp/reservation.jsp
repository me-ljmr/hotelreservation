<%@page import="com.awesomegroup.entity.Reservation"%>
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
<!-- Body -->
<p style="padding-top: 50px"></p>
<div class="container">
    <h2>Room List</h2>              
    <div class="row">

        <table class="table">      

            <c:if test="${rooms eq null}">
                <tr>
                    <td colspan="5">
                        <span class="label label-info">Room list is empty</span>

                    </td>
                </tr>
            </c:if>
            <!--where is Room Type-->
            <c:forEach var="room" items="${rooms}">
                <tr >
                    <td class="col-md-4"> <label>${room.roomTypeId.title}</label> <br/>
                            <img src="../../resources/img/${room.room_photo_gallery_id.photo_title}" height="300" width="300">
                          </td>                 
                    <td><label>Descriptions</label><br/>
                        <div>${room.roomTypeId.description}</div>                      
                            <c:forEach var="service" items="${room.roomServiceCollection}">
                              <ul style="margin: 0" >
                                    <li >${service.serviceId.title} </li>
                              </ul> 
                            </c:forEach>        
                    </td>
                    <td>
                        <label>Price</label><br/>
                        $${room.roomTypeId.rate} </td>
                </tr>
            </c:forEach> 
        </table>
    </div>
</div>

<!--<div class="col-md-4">
 <img class="img-responsive" src="${pageContext.request.contextPath}/resources/img/${room.photo_title}" alt="${room.photo_title}"/>
<img class="img-responsive" src="${pageContext.request.contextPath}/resources/img/${reservation.car.category.picture}" alt="${reservation.car.category.name}"/>
</div>-->

<p>&nbsp;</p>
<%@ include file="footer.jsp" %>
</body>
</html>
