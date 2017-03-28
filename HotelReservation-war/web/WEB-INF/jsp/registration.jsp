
<%-- 
    Document   : registration
    Created on : 28-Mar-2017, 1:04:01 AM
    Author     : Aishwarya
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />
        <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapjs" />
        <spring:url value="/resources/js/jquery.js" var="jquery"/> 
        <spring:url value="/hotelsys/admin" var="adminrootpath" />    
        <script src="${bootstrapjs}"></script> 
        <script src="${jquery}"></script>  
        <link href="${bootstrapcss}" rel="stylesheet" /> 
         
        <title>Registration</title>
    </head>
    <body>
        <div class="container">
            <h2>Registration form</h2>
            <a href="${userrootpath}/user/new" class="pull-right " >New user</a>
            <div class="row">
                <c:if test="${usersaved != null}">
                    <label class="alert alert-dismissable alert-success">${usersaved}</label>
                </c:if>
            </div>
            
            <div class="row">
                <form:form  method="POST"  action="${userrootpath}/rooms/new">

                <table class="table">
                    
                    <tr>
                        <td>First name</td>
                        <td><form:input type="text" path="firstName" name="firstName" /></td>

                    </tr> 
                    <tr> 
                        <td>Last name</td>
                        <td><form:input type="text" path="lastName" name="lastName" /></td>

                    </tr> 
                    <tr> 
                        <td>Address</td>
                        <td><form:input type="text" path="address" name="address" /></td>
                    </tr>     
                    <tr>     
                        <td>Date of birth</td>
                        <td><form:input type="date" path="dateOfBirth" name="dateOfBirth" /></td>
                    </tr> 
                    <tr> 
                        <td>Contact number</td>
                        <td><form:input type="text" path="contactNumber" name="contactNumber" /></td>
                    </tr>
                    <tr> 
                        <td>Email address</td>
                        <td><form:input type="text" path="email" name="email" /></td>
                    </tr>
                    <tr> 
                        <td>Password</td>
                        <td><form:input type="password" path="password" name="password" /></td>
                    </tr>
                    
                    <input type="submit" value="Save"  />
                    <c:if test = "${error != null}">
                        <label class="alert alert-danger">${error}</label>
                    </c:if>
                    
                </table>
                </form:form>
            </div>
        </div>
    </body>
</html>
