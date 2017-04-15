<%@ include file="header.jsp" %>
<section class="bg-info">
    <div class="container">
        <div class="row">
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
                                    <input class="form-control" value="${email}" placeholder="E-mail" name="email" type="email" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" value="${password}" placeholder="Password" name="password" type="password" value="">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" ${remember} type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div>
                                <button type="submit" class="btn btn-lg btn-success btn-block">Login</button> 
                                <a href="#registration" class="btn btn-lg btn-info btn-block">Create Account</a>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section id="registration" class="bg-info" style="padding-top: 50px"> 
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Create Account</h3>
                    </div>
                    <div class="panel-body">
                                    <c:if test = "${msg != null}">
                                        <label class="alert alert-danger"> 
                                                <span>${msg}</span>
                                        </label>
                                    </c:if>
                        <form action="${userrootpath}/newuser" method="post" >
                            <fieldset>
                                <div class="form-group">     
                                    <label>Firstname: </label>
                                    <input class="form-control" name="firstname" >
                                </div>
                                <div class="form-group">     
                                    <label>Lastname: </label>
                                    <input class="form-control" name="lastname"  >
                                </div>
                                <div class="form-group">
                                    <label>Address: </label>
                                    <input class="form-control" name="address"   >
                                </div>
                                <div class="form-group">
                                    <label>Contact number: </label>
                                    <input class="form-control" name="contactNumber"  placeholder="123-123-1234">
                                </div>      
                                <div class="control-group">
                                    <label class="control-label">Date of birth:</label>
                                    <input class="form-control"  type="text" name="dob"   >
                                </div>
                                <div class="form-group"> 
                                    <label>Email: </label>
                                    <input class="form-control" name="email" type="email" placeholder="Email">
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input class="form-control" name="password" type="password" placeholder="password">
                                </div>
                                <div>
                        
                                </div>
                                <input class="btn btn-lg btn-default" type="reset" value="Reset" />
                                <button type="submit" class="btn btn-lg btn-success">Submit</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>       
</section>    


<%@ include file="footer.jsp" %>
