<%-- 
    Document   : room
    Created on : Mar 26, 2017, 3:04:40 PM
    Author     : lujamanandhar
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.awesomegroup.entity.RoomType"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Editor</title>
        <spring:url value="/hotelsys/user" var="userrootpath" />       
       <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />
        <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapjs" />
        <spring:url value="/resources/js/jquery.js" var="jquery"/> 
        
        <script src="${bootstrapjs}"></script> 
        <script src="${jquery}"></script>  
        <link href="${bootstrapcss}" rel="stylesheet" /> 
        
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                <h2>User Information</h1>
               
                <form   action="${userrootpath}/new/save" method="post">
                    <div class="form-group" >
                    <label>First name</label>
                        
                     <input type="text" path="firstName" name="firstName" class="form-control"/> 
                        <c:if test = "${errorTest2!= null}">
                            
                            <label class="alert alert-danger"><h4>${errorTest2}</h4></label>
                           
                        </c:if> 
                        <c:if test = "${errorTest!= null}">
                            
                            <label class="alert alert-danger"><h4>${errorTest}</h4></label>
                           
                        </c:if>     
                            
                    </div>
                     <div class="form-group" >
                    <label>Last name</label>
                        
                     <input type="text" path="lastName" name="lastName" class="form-control"/>
                        <c:if test = "${errorTest2!= null}">
                            
                            <label class="alert alert-danger"><h4>${errorTest2}</h4></label>
                           
                        </c:if>
                        
                     
                     </div>
                    <div class="form-group" >
                        <label>Address</label>
                        
                         <input type="text" path="address" name="address" class="form-control"/>
                        
                         
                    </div>
                    <div class="form-group" >
                    <label>Date of birth</label>
                        
                    <input type="date" path="dob" name="dob" class="form-control" placeholder="yyyy-mm-dd"/> 
                    </div>
                    <div class="form-group" >
                    <label>Contact number</label>
                        
                    <input type="text" path="contactNumber" name="contactNumber" class="form-control"/>
                        <c:if test = "${errorTest3!= null}">
                            
                            <label class="alert alert-danger"><h4>${errorTest3}</h4></label>
                           
                        </c:if>
                         
                     
                    </div>
                    <div class="form-group" >
                    <label>Email address</label>
                        
                     <input type="text" path="email" name="email" class="form-control"/>
                        
                  
                    </div>
                    <div class="form-group" >
                    <label>Password</label>
                        
                    <input type="password" path="password" name="password" class="form-control"/>
                        
                    </div>
                    <input type="submit" value="Save"  class="btn btn-success"/>
                </form>
                </div>
                </div>
            </div>
    </body>
</html>
