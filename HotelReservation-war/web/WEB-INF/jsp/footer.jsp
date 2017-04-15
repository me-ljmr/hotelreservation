<spring:url value="/resources/css/bootstrap.css" var="bootscss" />
<spring:url value="/resources/js/jquery-1.8.3.min.js" var="jquerymin"/>   
<spring:url value="/resources/js/bootstrap-datetimepicker.js" var="bootsdatetimepicker" />    
<script src="${bootscss}"></script> 
<script src="${jquerymin}"></script>  
<script src="${bootsdatetimepicker}"></script> 

<script type="text/javascript">
    $('.form_date').datetimepicker({
        language: 'en',
        startDate: "",
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

</script>     
<footer>        
            <div class="row">
                <div class="col-md-offset-1" style="float: left">
                     Copyright &copy; Awesome Group 2017 
                </div>
             
                <div style="float: right; padding-right: 50px">
                    <ul class="list-inline quicklinks">
                        <li> Privacy Policy </li>       
                        <li> Terms of Use </li>
                        
                    </ul>
                </div>
            </div>
    </footer>
    </body>
</html>