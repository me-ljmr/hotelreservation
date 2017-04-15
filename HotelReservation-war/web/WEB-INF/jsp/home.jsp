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

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />
        <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapjs" />
        <spring:url value="/resources/css/main.css" var="maincss" />
        <spring:url value="/resources/js/jquery.js" var="jquery"/> 

        <spring:url value="/resources/img" var="imagepath" />

        <script src="${bootstrapjs}"></script> 
        <script src="${jquery}"></script>  
        <link href="${bootstrapcss}" rel="stylesheet" /> 
        <link rel="stylesheet" href="${maincss}" >
        <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
        <title>Hotel Awesome</title>
    </head>

    <body>
        <!-- Navigation -->
    <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top" style="margin-bottom: 100px;background-color: black; color: white">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only"></span>  <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">Hotel Awesome</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a style="color: white"  class="page-scroll" href="#">Meeting& Event</a>
                    </li>
                    <li>
                        <a style="color: white"  class="page-scroll" href="#">Restaurant&Bars</a>
                    </li>
                    <li>
                        <a style="color: white"  class="page-scroll" href="hotelsys/reservation">Reservation</a>
                    </li>
                    <li>
                        <a style="color: white"  class="page-scroll" href="hotelsys/contact">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <!-- Header -->
    <header >

        <div class="intro-text" style="padding-top: 80px; padding-bottom: 0 ">
            <div class="intro-lead-in" > 
                <h1 id="typing_text"><b>"Welcome to Hotel Awesome"</b></h1>
            </div>
        </div>

        <div class="container">
            <!--            -->
            <div class="row">
                <div class="search-bar" style=" background-color:rgba(255,255,255,0.4);" >
                    <form class="form-horizontal" role="form">
                        <div class="col-2-5 col-md-offset-2" style="margin: 0 150px 0 150px">
                            <div class="form-group">
                                <!--                                
                                <input type="text" class="form-control" placeholder="Search" />
                            <form:select path="category" cssClass="form-control" id="category_id">
                            <form:option value="0" label="Select ..." />
                            <form:options path="category" items="${categoryList}" itemValue="id" itemLabel="name" />
                        </form:select>
                                -->
                                <label  style="font-size: 1.5em; font-weight: bolder;float: left;color: white;text-shadow: 2px 2px 4px #000000;">Room Suits</label>
                                <select class="form-control">
                                    <option value="0" selected>All Rooms</option>
                                    <option value="1">Single</option>
                                    <option value="2">Double</option>
                                    <option value="3">Luxury</option>
                                    <option value="4">Modern</option>
                                </select>
                            </div>
                        </div>                      
                        <div >
                            <div class="col-md-5 col-md-offset-1" style="color: black">

                                <div class="controls input-append date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1" data-link-format="yyyy-mm-dd" >                
                                    <label  style="font-size: 1.3em; font-weight: bolder;color: white;text-shadow: 2px 2px 4px #000000;">Date From</label>  <input size="16" type="text" name="date_from" value="" readonly>
                                    <span class="add-on"><i class="icon-search icon-large icon-black"></i></span>
                                    <span class="add-on"><i class="icon-th icon-black"></i></span>
                                </div>
                            </div>
                            <input type="hidden" id="dtp_input1" name="date_from" value="" />

                            <div class="col-md-5" style="color: black">

                                <div class="controls input-append date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">                
                                    <label  style="font-size: 1.3em; font-weight: bolder;color: white;text-shadow: 2px 2px 4px #000000;">To </label> <input size="16" type="text" value="" readonly>
                                    <span class="add-on"><i class="icon-search icon-large icon-black"></i></span>
                                    <span class="add-on"><i class="icon-th icon-black"></i></span>
                                </div>
                                <input type="hidden" id="dtp_input2" name="date_till" value="" /><br/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12">
                                <button style="font-size: 1.5em" type="submit" class="btn btn-info">Search</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>


        </div>
    </div>
</header>
<p>&nbsp;&nbsp;</p>
<!-- Services Section -->
<section id="services" style="padding: 0">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading">Services</h2>
                <h3 class="section-subheading text-muted"> Our Team is courteous, professional and dedicated to our customers.</h3>
            </div>
        </div>
        <div class="row text-center">
            <div class="col-md-4">
                <span class="fa-stack fa-4x">
                    <i class="fa fa-circle fa-stack-2x text-primary"></i>
                    <i class="fa fa-shopping-cart fa-stack-1x fa-inverse"></i>
                </span>
                <h4 class="service-heading">Stay</h4>
                <p class="text-muted">Located minutes from the world-famous Niagara Falls Canada. At Awesome Hotel, youâ€™ll find the room you want</p>
            </div>
            <div class="col-md-4">
                <span class="fa-stack fa-4x">
                    <i class="fa fa-circle fa-stack-2x text-primary"></i>
                    <i class="fa fa-laptop fa-stack-1x fa-inverse"></i>
                </span>
                <h4 class="service-heading">Meeting& Event</h4>
                <p class="text-muted">Hotel Plaza five function rooms, are perfectly designed and equipped with the very latest technology and each has its own style and atmosphere. It also perfect for the organisation of large scale events. There are two connected event spaces, are flooded with natural light. For intimate events, you can even choose the wine cellar. </p>
            </div>
            <div class="col-md-4">
                <span class="fa-stack fa-4x">
                    <i class="fa fa-circle fa-stack-2x text-primary"></i>
                    <i class="fa fa-lock fa-stack-1x fa-inverse"></i>
                </span>
                <h4 class="service-heading">Restaurant&Bars</h4>
                <p class="text-muted">The Plaza hotel are providing different atmospheres to suit all moods. Clients come here to enjoy fabulous Canadian food.</p>
            </div>
        </div>
    </div>
</section>
<section style="padding: 50px 50px 0 20px;  ">
    <h3>Rooms & suites gallery </h3>
    <table border="0">

        <tr >
            <td ><img height="242" width="382" src="${imagepath}/001.jpg" />
            </td>
            <td><img  height="242" width="382" src="${imagepath}/002.jpg" />
            </td>
            <td><img  height="242" width="382" src="${imagepath}/003.jpg" />
            </td>
        </tr>
        <tr>
            <td><img  height="242" width="382" src="${imagepath}/013.jpg" />
            </td>
            <td><img  height="242" width="382" src="${imagepath}/014.jpg" />
            </td>
            <td><img  height="242" width="382" src="${imagepath}/008.jpg" />
            </td>
        </tr>
        <tr>
            <td><img  height="242" width="382" src="${imagepath}/009.jpg" />
            </td>
            <td><img   height="242" width="382" src="${imagepath}/010.jpg" />
            </td>
            <td><img height="242" width="382" src="${imagepath}/011.jpg" /></td>
        </tr>

    </table>


</section>
<div class="row">
    <div class="col-lg-12 text-center">
        <h4 class="section-heading">Room Reservation</h4>
        <span>Tel: 888.888.8888</span><br>
        <span>Fax: 888.888.9999</span><hr/>
    </div>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>


