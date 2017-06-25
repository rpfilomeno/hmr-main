<!DOCTYPE html>
<%@ page import="bizoncloudone.com.bean.UserLogin"
		 import="java.util.List"
		 import="java.util.ArrayList"
		 import="javax.servlet.RequestDispatcher"
		 import="bizoncloudone.com.bean.Booking"
		 import="java.text.SimpleDateFormat" %>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">    <%    String COMPANY_NAME = (String)request.getSession().getAttribute("COMPANY_NAME");    String SKIN_HEADER_COLOR = (String)request.getSession().getAttribute("SKIN_HEADER_COLOR");    %>    <title><%=COMPANY_NAME%></title>    <link rel="shortcut icon" href="favicon.ico" />
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
    
    <link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <%
    
    	String userRole = "admin";
    
		String sid =(String)request.getAttribute("sid");
		String emailAdd =(String)request.getAttribute("emailAdd");
	    UserLogin ul = (UserLogin)request.getSession().getAttribute("userLogin_"+sid);	    	    Integer userRoleLoginId = (Integer)request.getSession().getAttribute("userRoleLoginId_"+sid);
	   	String msg = (String)request.getAttribute("msg");
	    String msgType = (String)request.getAttribute("msgType");
	    System.out.println("PAGE booking.jsp : "+msg+" "+msgType);
	    
	    if(ul!=null){
	    	System.out.println("PAGE booking.jsp: ul is not null");
	    }else{
	    	System.out.println("PAGE booking.jsp: ul is null");
	    	
	    	request.setAttribute("msgType", "Alert");
	    	request.setAttribute("msg", "Please Sign In.");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
	    }
	    
	    Booking booking = (Booking)request.getAttribute("booking");
 
	    System.out.println("booking "+booking);
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	    
        String eta_etb_manila = "";
        
        if(booking.getEta_etb_manila()!=null){
        	eta_etb_manila = sdf.format(booking.getEta_etb_manila());
        }

        System.out.println("eta_etb_manila: " + eta_etb_manila);

    %>
  </head>
  <body class="hold-transition skin-<%=SKIN_HEADER_COLOR%> sidebar-mini">
    <!-- Site wrapper -->
    <div class="wrapper">
	  
	  <jsp:include page="main-header.jsp" />
      
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          <!-- Sidebar user panel -->
          <div class="user-panel">
            <div class="pull-left image">
              <img src="userphotos/<%=ul.getId()%>-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
              <p><%=ul.getFirst_name()%>&nbsp;<%=ul.getLast_name()%></p>
              <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
          </div>
          <!-- search form -->
          <!-- 
          <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
              <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
              </span>
            </div>
          </form>  -->
          <!-- /.search form -->
          <!-- sidebar menu: : style can be found in sidebar.less -->
          <ul class="sidebar-menu">
            <li class="header">MAIN NAVIGATION</li>
            <li class="treeview">
              <a href="#" onclick="dashboardPage()">
                <i class="fa fa-dashboard"></i> <span>Dashboard</span>
              </a>
            </li>
            <li class="treeview active">
              <a href="#" onclick="bookingsPage()">
                <i class="fa fa-dashboard"></i> <span>Bookings</span>
              </a>
            </li>
            <!-- 
            <li class="treeview">
              <a href="#">
                <i class="fa fa-files-o"></i>
                <span>Layout Options</span>
                <span class="label label-primary pull-right">4</span>
              </a>
              <ul class="treeview-menu">
                <li><a href="pages/layout/top-nav.html"><i class="fa fa-circle-o"></i> Top Navigation</a></li>
                <li><a href="pages/layout/boxed.html"><i class="fa fa-circle-o"></i> Boxed</a></li>
                <li><a href="pages/layout/fixed.html"><i class="fa fa-circle-o"></i> Fixed</a></li>
                <li><a href="pages/layout/collapsed-sidebar.html"><i class="fa fa-circle-o"></i> Collapsed Sidebar</a></li>
              </ul>
            </li> -->			<%if(userRoleLoginId.equals(1)){%>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i>
                <span>Maintenance</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="#" onclick="usersPage()"><i class="fa fa-circle-o"></i> Users</a></li>
                <li><a href="#" onclick="rolesPage()"><i class="fa fa-circle-o"></i> Roles</a></li>                <li><a href="#" onclick="documentTypesPage()"><i class="fa fa-circle-o"></i> Document Types</a></li>                <li><a href="#" onclick="requestorsPage()"><i class="fa fa-circle-o"></i> Requestors</a></li>
              </ul>
            </li>			<%} %>
            <li class="treeview">
              <a href="#" onclick="changePasswordPage()">
                <i class="fa fa-dashboard"></i> <span>Change Password</span>
              </a>
            </li>
            <li class="treeview">              <a href="#" onclick="signOutPage()">                <i class="fa fa-dashboard"></i> <span>Sign Out</span>              </a>            </li>
        
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>
      <!-- =============================================== -->

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Bookings
            <small>Create Booking, Update Booking, View Booking</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">Bookings</a></li>
            <!-- <li class="active">Users</li> -->
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
		<div id="msgDiv"></div>
          <!-- Default box -->
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title"><i class="fa fa-globe"> &nbsp; </i><%=booking.getBooking_id()%> : <%=booking.getShipper_name()%> / <%=booking.getConsignee_name()%></h3>
              <div class="box-tools pull-right">
                <button class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse"><i class="fa fa-minus"></i></button>
                <button class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
        <div class="box-body">
         <div class="row">
            
	        <form action="controller" name="frm" id="frm" method="post">
		    	<input type="hidden" name="action" id="action" value=""/>
		    	<input type="hidden" name="emailAdd" id="emailAdd" value="<%=emailAdd%>"/>
		    	<input type="hidden" name="sid" id="sid" value="<%=sid%>"/>
		    	<input type="hidden" name="bookingId" id="bookingId" value=""/>
		    </form>
				<div class="col-sm-6">
	              <div class="form-group">
	                <label><b>Booking ID : </b></label>
					<input type="text" class="form-control" placeholder="Booking ID" value="" id="booking_id" name="booking_id" autocomplete="off"/>					
	              </div>
	              <div class="form-group">
	                <label><b>Registry No : </b></label>
					<input type="text" class="form-control" placeholder="Registry No" value="<%=booking.getReg_no()%>" id="reg_no" name="reg_no" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#reg_no">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>ETA/ETB Manila : </b></label>
					<input type="text" class="form-control" placeholder="TA/ETB Manila" value="<%=eta_etb_manila%>" id="eta_etb_manila" name="eta_etb_manila" readonly="readonly"/>
	              	<button class="btn" data-clipboard-action="copy" data-clipboard-target="#eta_etb_manila">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>Shipper Name : </b></label>
					<input type="text" class="form-control" placeholder="Shipper Name" value="<%=booking.getShipper_name()%>" id="shipper_name" name="shipper_name" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#shipper_name">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>Consignee Name : </b></label>
					<input type="text" class="form-control" placeholder="Consignee Name" value="<%=booking.getConsignee_name()%>" id="consignee_name" name="consignee_name" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#consignee_name">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>Vessel : </b></label>
					<input type="text" class="form-control" placeholder="Vessel" value="<%=booking.getVessel()%>" id="vessel" name="vessel" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#vessel">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>Port of Loading : </b></label>
					<input type="text" class="form-control" placeholder="Port of Loading" value="<%=booking.getPort_of_load()%>" id="port_of_load" name="port_of_load" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#port_of_load">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>Port of Discharge : </b></label>
					<input type="text" class="form-control" placeholder="Port of Discharge" value="<%=booking.getPort_of_discharge()%>" id="port_of_discharge" name="port_of_discharge" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#port_of_discharge">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>Final Destination : </b></label>
					<input type="text" class="form-control" placeholder="Final Destination" value="<%=booking.getFinal_dest()%>" id="final_dest" name="final_dest" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#final_dest">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>HB/L Number : </b></label>
					<input type="text" class="form-control" placeholder="HB/L Number" value="<%=booking.getHbl_no()%>" id="hbl_no" name="hbl_no" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#hbl_no">Copy</button>
	              </div>

	              <div class="form-group">
	                <label><b>Master B/L Number : </b></label>
					<input type="text" class="form-control" placeholder="Master B/L Number" value="<%=booking.getMaster_bl_no()%>" id="master_bl_no" name="master_bl_no" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#master_bl_no">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>Mark and Numbers : </b></label>
					<input type="text" class="form-control" placeholder="Mark and Numbers" value="<%=booking.getMark_and_number()%>" id="mark_and_number" name="mark_and_number" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#mark_and_number">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>Number of Packages : </b></label> 
					<input type="text" class="form-control" placeholder="Number of Packages" value="<%=booking.getNo_of_pack()%>" id="no_of_pack" name="no_of_pack" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#no_of_pack">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>Container No : </b></label> 
					<input type="text" class="form-control" placeholder="Container No" value="<%=booking.getContainer_no()%>" id="container_no" name="container_no" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#container_no">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>Seal No : </b></label>
					<input type="text" class="form-control" placeholder="Seal No" value="<%=booking.getSeal_no()%>" id="seal_no" name="seal_no" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#seal_no">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>Description of Goods : </b></label>
					<input type="text" class="form-control" placeholder="Description of Goods" value="<%=booking.getDesc_goods()%>" id="desc_goods" name="desc_goods" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#desc_goods">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>Terms of Shipment : </b></label>
					<input type="text" class="form-control" placeholder="Terms of Shipment" value="<%=booking.getTerms_of_shipment()%>" id="terms_of_shipment" name="terms_of_shipment" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#terms_of_shipment">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>Gross Weight/Net Weight : </b></label>
					<input type="text" class="form-control" placeholder="Gross Weight/Net Weight" value="<%=booking.getGross_net_weight()%>" id="gross_net_weight" name="gross_net_weight" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#gross_net_weight">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>Measurement/Number of CBM : </b></label>
					<input type="text" class="form-control" placeholder="Measurement/Number of CBM" value="<%=booking.getMeasurement_no_of_cbm()%>" id="measurement_no_of_cbm" name="measurement_no_of_cbm" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#measurement_no_of_cbm">Copy</button>
	              </div>
	              <div class="form-group">
	                <label><b>Special Instructions : </b></label> 
					<input type="text" class="form-control" placeholder="Special Instructions" value="<%=booking.getSpecial_inst()%>" id="special_inst" name="special_inst" readonly="readonly"/>
					<button class="btn" data-clipboard-action="copy" data-clipboard-target="#special_inst">Copy</button>
	              </div>
            </div><!-- /.col -->
            
				

          

            
          </div><!-- /.row -->
            </div><!-- /.box-body -->
            <!--
            <div class="box-footer">
              Footer
            </div> --> <!-- /.box-footer-->
            
         <div class="box-footer clearfix">
           <a href="#" onclick="saveBooking('<%=booking.getId()%>')" class="btn btn-sm btn-info btn-flat ">Save</a>&nbsp;&nbsp;
           <a href="#" onclick="bookingsPage()" class="btn btn-sm btn-info btn-flat ">Cancel</a>
         </div><!-- /.box-footer -->
                
                
          </div><!-- /.box -->

        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
 
  	  <jsp:include page="main-footer.jsp" />

	  <jsp:include page="control-sidebar.jsp" />
      
      <!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
      <div class="control-sidebar-bg"></div>
    </div><!-- ./wrapper -->

    <!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- SlimScroll -->
    <script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="dist/js/demo.js"></script>
    <script src="js/clipboard.min.js"></script>
    <script src="js/jquery.datetimepicker.full.js"></script>
    <script src="js/pageNav.js"></script>
    <!-- page script -->
    <script>

    var clipboard = new Clipboard('.btn');

    clipboard.on('success', function(e) {
        console.log(e);
    });

    clipboard.on('error', function(e) {
        console.log(e);
    });

 	function saveBooking(id){
  	  	console.log("id: "+id);
		document.getElementById("action").value="saveBooking";
		document.getElementById("bookingId").value=id;
		document.frm.submit();
	}
    </script>
  </body>
</html>
