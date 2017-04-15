<%-- 
    Document   : bookingcancel
    Created on : Apr 12, 2017, 6:48:15 PM
    Author     : n01060117
--%>

         <div class="modal " id="myModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Booking Cancel</h4>
                    </div>
                     <form role="form" method="POST"  action="${userrootpath}/awesomegroup/hotelsys/booking/cancel/${reservation.id}">   
                    <div class="modal-body">  
                            <p>Are you sure you want to cancel your booking?</p>
                    </div>
                    <div class="modal-footer">
                        <input type="submit" value="Yes"/>&nbsp;<a href="${userrootpath}/awesomegroup/hotelsys/booking" ><input type="button" class="btn btn-default" data-dismiss="modal" value="No"/> 
                        </a> 
                    </div>
                    </form>
                </div>
            </div>
        </div>
