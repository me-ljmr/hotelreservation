<nav class="navbar-default navbar-static-side" role="navigation">
    <!-- sidebar-collapse -->
    <div class="sidebar-collapse">
        <!-- side-menu -->
        <ul class="nav" id="side-menu">
            <li>
                <!-- user image section-->
                <div class="user-section">
                    <div class="user-section-inner">
                        <img src="${assetsfolder}/img/user.jpg" alt="">
                    </div>
                    <div class="user-info">
                        <div>${admininfo.loginName}</div>
<!--                        <div class="user-text-online">
                            <span class="user-circle-online btn btn-success btn-circle "></span>&nbsp;Online
                        </div>-->
                        &nbsp;
                    </div>
                </div>
                <!--end user image section-->
            </li>
<!--            <li class="sidebar-search">
                 search section
                <div class="input-group custom-search-form">
                    <input type="text" class="form-control" placeholder="Search...">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button">
                            <i class="fa fa-search"></i>
                        </button>
                    </span>
                </div>
                end search section
            </li>-->
            <li class="">
                <a href="${adminrootpath}/dashboard"><i class="fa fa-dashboard fa-fw"></i>Dashboard</a>
            </li>
            <!--<li class="">-->
                <!--<a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Basic Setup<span class="fa arrow"></span></a>-->
                <!--<ul class="nav nav-second-level">-->
                    <li>
                        <a href="${adminrootpath}/roomtypes"><i class="fa fa-building-o fa-fw"></i>Room Type Setup</a>
                    </li>
                    <li>
                        <a href="${adminrootpath}/services"><i class="fa fa-cogs fa-fw"></i>Room Amenities Setup</a>
                    </li>
                <!--</ul>-->
            <!--</li>--> 
            <li class="">
                <a href="${adminrootpath}/rooms"><i class="fa fa-cogs fa-fw"></i>Rooms</a>
            </li> 
            <li class="">
                <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Admin Reports <span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="${adminrootpath}/reports/roomsbyservice"><i class="fa fa-cogs fa-fw"></i>Rooms-by-service Report</a>
                    </li>
                    <li>
                        <a href="${adminrootpath}/reports/reservationdetail"><i class="fa fa-cogs fa-fw"></i>Reservation Details Report</a>
                    </li>
                    
                </ul>
            </li>
         
        </ul>
        <!-- end side-menu -->
    </div>
    <!-- end sidebar-collapse -->
    </nav>