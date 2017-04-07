
<%-- 
    Document   : login
    Created on : Mar 26, 2017, 3:04:40 PM
    Author     : lujamanandhar
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hotel Awesome</title>
    <!-- Core CSS - Include with every page -->
    


 
    <spring:url value="/hotelsys/admin" var="adminrootpath" /> 
    
      <%@include file="../layouts/springvars.jsp" %>
    <%@include file="../layouts/csslinks.jsp" %>


   </head>
<body class="body-Login-back">

    <div class="container">
       
        <div class="row">
            <div class="col-md-4 col-md-offset-4 text-center logo-margin ">
              <img src="${logo}" alt="" />[ADMIN]
                </div>
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">                  
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        
                        <form role="form" method="POST">
                            <fieldset>
                                <div class="form-group">
                                    <c:if test = "${errors != null}">
                                         
                                        <label class="alert alert-danger">
                                            <c:forEach var="error" items="${errors}">
                                                <span>${error}</span>
                                            </c:forEach> 

                                        </label>
                                        
                                    </c:if>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" value="${login}" placeholder="Admin Login Name" name="login" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" value="${password}" placeholder="Password" name="password" type="password" value="">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" ${remember} type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Login" />
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>


</body>

</html>
