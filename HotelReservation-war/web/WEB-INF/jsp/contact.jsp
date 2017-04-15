<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="java.util.List"%>
<%@page import="com.awesomegroup.sessionbean.RoomSessionBeanRemote"%>
<%@page import="com.awesomegroup.entity.Room"%>
<%@page import="com.awesomegroup.sessionbean.HotelSessionBeanRemote"%>
<%@page import="javax.naming.InitialContext"%>
 <spring:url value="/resources/img" var="imagepath" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*,javax.mail.*"%>
<%@ page import="javax.mail.internet.*" %>
<%
    //Creating a result for getting status that messsage is delivered or not!
    String result;
    // Get recipient's email-ID, message & subject-line from index.html page
     String dept = request.getParameter("dept");
     String to = "";
     if(dept == "tech"){
        to =  "support@awesomemail.com"; 
     }
   //String to = "support@awesomemail.com";
//    final String subject = request.getParameter("subject");
//    final String messg = request.getParameter("message");
    String subject = "test";
    String messg = "test";

    // Sender's email ID and password needs to be mentioned
    final String username = "a39f32a31f1361";
    final String pass = "a7b876ae8fdc12";

    // Defining the mail host
    String host = "smtp.mailtrap.io";

    // Creating Properties object
    Properties props = new Properties();

    // Defining properties
    props.put("mail.smtp.host", host);

    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.user", username);
    props.put("mail.password", pass);
    props.put("mail.port", "2525");

    // Authorized the Session object.
    Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, pass);
        }
    });

    try {
        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(mailSession);
        // Set From: header field of the header.
        message.setFrom(new InternetAddress(username));
        // Set To: header field of the header.
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(to));
        // Set Subject: header field
        message.setSubject(subject);
        // Now set the actual message
        message.setText(messg);
        // Send message
        Transport.send(message);
        result = "Your mail sent successfully....";
    } catch (MessagingException mex) {
        mex.printStackTrace();
        result = "Error: unable to send mail....";
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />
        <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapjs" />
        <spring:url value="/resources/css/main.css" var="maincss" />
        <spring:url value="/resources/js/jquery.js" var="jquery"/> 
        <spring:url value="/hotelsys/admin" var="adminrootpath" />    
        <script src="${bootstrapjs}"></script> 
        <script src="${jquery}"></script>  
        <link href="${bootstrapcss}" rel="stylesheet" /> 
        <link rel="stylesheet" href="${maincss}" >
        <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
        <title>Awesome Hotel</title>
    </head>

    <body>
        <!-- Navigation -->
    <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top" style="background-color: black; color: white">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only"></span>  <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="../">Awesome Hotel</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a style="color: white"  class="page-scroll" href="reservation">Reservations</a>
                    </li>
                    <li>
                        <a style="color: white"  class="page-scroll" href="services">Services</a>
                    </li>
                    <li>
                        <a style="color: white"  class="page-scroll" href="#about">About</a>
                    </li>
                    <li>
                        <a style="color: white"  class="page-scroll" href="contact">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
    <p style='margin: 0; padding-bottom: 25px' >&nbsp;&nbsp;  </p> 
      
    <div class="row" style="background-size: 100%;background-repeat: no-repeat; background-image: url('${imagepath}/contact_header.jpg'); padding-bottom: 0; ">
        <div class="col-md-7">
            <div class="container">
                <div  > 
                    <h1 style="font-weight: bold;font-family: 'Courier'; font-size: 70px; color: white; padding: 250px 0 0 50px"><strong>Contact Us</strong></h1>
                </div>
            </div>
        </div>
        <div class="col-md-5">
            <!-- Services Section -->
            <section style="padding:10px 0 0 0;background-color:rgba(255,255,255,0.9);" >
                <div class="row">
                    <p class="col-md-12 col-md-offset-1  ">Want to get in touch with us? Fill out the form below to send us a message 
                        and We will try to get back to you within 24 hours! </p>
                    <div class="col-md-12 col-md-offset-1 col-md-10 col-md-offset-1">
                        <form action="contact" name="sentMessage" id="contactForm" role="form" method="POST" validate>
                            <div class="row control-group">
                                <div class="form-group col-xs-12 floating-label-form-group controls" style='margin:0'>
                                    <label>Name</label>
                                    <input type="text" class="form-control" placeholder="Name" name="name" id="name" required data-validation-required-message="Please enter your name.">
                                    <span class="help-block text-danger"></span>
                                </div>
                            </div>
                            <div class="row control-group">
                                <div class="form-group col-xs-12 floating-label-form-group controls" style='margin:0'>
                                    <label>Email Address</label>
                                    <input type="email" class="form-control" placeholder="Email Address" name="email" id="email" required data-validation-required-message="Please enter your email address.">
                                    <span class="help-block text-danger"></span>
                                </div>
                            </div>
                            <div class="row control-group">
                                <div class="form-group col-xs-12 floating-label-form-group controls" style='margin:0'>
                                    <label>Department</label>
                                    <select name="dept" class="form-control">
                                        <option value="tech">Technical support </option>
                                        <option value="reserve">Reservation center</option>
                                        <option value="market">Marketing</option>
                                    </select>
                                    <span class="help-block text-danger"></span>
                                </div>
                            </div>
                            <div class="row control-group">
                                <div class="form-group col-xs-12 floating-label-form-group controls" style='margin:0'>
                                    <label>Subject</label>
                                    <input type="tel" class="form-control" placeholder="Subject" name="subject" id="subject" required data-validation-required-message="Please enter your phone number.">
                                    <span class="help-block text-danger"></span>
                                </div>
                            </div>
                            <div class="row control-group">
                                <div class="form-group col-xs-12 floating-label-form-group controls" style='margin:0; '>
                                    <label>Message</label>
                                    <textarea rows="5" class="form-control" placeholder="Message" name="message" id="message" required data-validation-required-message="Please enter a message." ></textarea>
                                    <span class="help-block text-danger"></span>
                                </div>
                            </div>
                            <div class="row control-group" >
                                <div class="form-group col-xs-12" style='margin:0'>
                                    <button type="submit" class="btn btn-default">Send</button>
                                    <span> <% out.println(result);%> </span>
                                </div>
                               
                            </div>
                                <br>
                        </form>
                    </div>
                </div>
            </section>
        </div>
    </div>
       
    <div class="row text-center" style="background-color: black; color: white; padding:0 0 10px 0;margin: 0 ">
        <div class="col-md-4">

            <h4 class="service-heading"> GENERAL INQUIRIES </h4>
            <span>reservations@awesomehotel.com</span>
        </div>
        <div class="col-md-4">

            <h4 class="service-heading">MEDIA INQUIRIES </h4>          
            <a href="../">Please visit our Press page>>></a>
        </div>
        <div class="col-md-4">

            <h4 class="service-heading">DEVELOPMENT </h4>    
            <span>  development@awesomehotel.com </span>
        </div>
    </div>

    <!--Google Maps API-->
    <div class="row">
        <div   >
            <iframe
                width="1400"
                height="350"
                frameborder="10"
                border="0" style="border:0"
                src="https://www.google.com/maps/embed/v1/place?key=AIzaSyBb7g7_1g9tt-eU5zAqHyMeCrqLjON53Qg
                &q=Awesome Hotel,Toronro" allowfullscreen>
            </iframe>
        </div>

        <%@ include file="footer.jsp" %>
    </body>
</html>

