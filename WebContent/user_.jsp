<!DOCTYPE html>
<%@ page import="bizoncloudone.com.bean.UserLogin"
		 import="java.util.List"
		 import="java.util.ArrayList"
		 import="java.util.HashMap"
		 import="javax.servlet.RequestDispatcher"
		 import="java.text.SimpleDateFormat" %>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%
    String COMPANY_NAME = (String)request.getSession().getAttribute("COMPANY_NAME");
    String SKIN_HEADER_COLOR = (String)request.getSession().getAttribute("SKIN_HEADER_COLOR");
    %>
    <title><%=COMPANY_NAME%></title>
    <link rel="shortcut icon" href="favicon.ico" />
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
	    UserLogin ul = (UserLogin)request.getSession().getAttribute("userLogin_"+sid);
	    Integer userRoleLoginId = (Integer)request.getSession().getAttribute("userRoleLoginId_"+sid);

	   	String msg = (String)request.getAttribute("msg");
	    String msgType = (String)request.getAttribute("msgType");
	    System.out.println("PAGE login.jsp : "+msg+" "+msgType);
	    
	    if(ul!=null){
	    	System.out.println("PAGE user.jsp: ul is not null");
	    }else{
	    	System.out.println("PAGE user.jsp: ul is null");
	    	
	    	request.setAttribute("msgType", "Alert");
	    	request.setAttribute("msg", "Please Sign In.");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
	    }
	    
	    UserLogin userLogin = (UserLogin)request.getAttribute("userLogin");
	    
	    Integer userRoleLoginId_userLogin = (Integer)request.getAttribute("userRoleLoginId_userLogin");
	    String userRoleLoginName_userLogin = (String)request.getAttribute("userRoleLoginName_userLogin");
	    
	    
	    System.out.println("userLogin "+userLogin);
	    System.out.println("userRoleLoginId_userLogin "+userRoleLoginId_userLogin);
	    System.out.println("userRoleLoginName_userLogin "+userRoleLoginName_userLogin);
	    
	    HashMap<Integer,UserLogin> userLoginHM = (HashMap<Integer,UserLogin>)request.getSession().getAttribute("userLoginHM");
	    
	    System.out.println("userLoginHM "+userLoginHM.size());
	    
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        
        String date_created = "";
        
        if(userLogin.getDate_created()!=null){
        	date_created = sdf.format(userLogin.getDate_created());
        }
        
        String date_updated = "";
        
        if(userLogin.getDate_updated()!=null && userLogin.getUpdated_by()!=0){
        	date_updated = sdf.format(userLogin.getDate_updated());
        }
        
        String created_by = "";
        
        if(userLogin.getCreated_by()!=null){
        	UserLogin ulCreatedBy = (UserLogin)userLoginHM.get(userLogin.getCreated_by());
        	created_by = ulCreatedBy.getFirst_name()+" "+ulCreatedBy.getLast_name();
        }
        
        String updated_by = "";
        
        if(userLogin.getUpdated_by()!=null && userLogin.getUpdated_by()!=0){
        	UserLogin ulUpdatedBy = (UserLogin)userLoginHM.get(userLogin.getUpdated_by());
        	updated_by = ulUpdatedBy.getFirst_name()+" "+ulUpdatedBy.getLast_name();
        }
        
        String active = "";
        
        if(userLogin.getActive()!=null){
        	if(userLogin.getActive().booleanValue()==true ){
        		active = "Yes";
        	}else{
        		active = "No";
        	}
        	
        }
        

    
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
                <i class="fa fa-dashboard"></i> <span>MV Admin Console</span>
              </a>
            </li>    
            
            <li class="treeview">
              <a href="#" onclick="requestsPage()">
                <i class="fa fa-dashboard"></i> <span>Requests</span>
              </a>
            </li>



            <!-- 
            <li class="treeview">
              <a href="#" onclick="bookingsPage()">
                <i class="fa fa-dashboard"></i> <span>Bookings</span>
              </a>
            </li> -->
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
            </li> -->
            <%if(userRoleLoginId.equals(1) || userRoleLoginId.equals(2)){%>
            <li class="treeview active">
              <a href="#">
                <i class="fa fa-dashboard"></i>
                <span>Maintenance</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li class="active"><a href="#" onclick="usersPage()"><i class="fa fa-circle-o"></i> Users</a></li>
                <li><a href="#" onclick="rolesPage()"><i class="fa fa-circle-o"></i> Roles</a></li>
                <!-- 
                <li><a href="#" onclick="documentTypesPage()"><i class="fa fa-circle-o"></i> Document Types</a></li>
                <li><a href="#" onclick="requestorsPage()"><i class="fa fa-circle-o"></i> Requestors</a></li>
                -->
              </ul>
            </li>
            <%} %>
            <li class="treeview">
              <a href="#" onclick="changePasswordPage()">
                <i class="fa fa-dashboard"></i> <span>Change Password</span>
              </a>
            </li>
            <li class="treeview">
              <a href="#" onclick="signOutPage()">
                <i class="fa fa-dashboard"></i> <span>Sign Out</span>
              </a>
            </li>
        
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
            Users
            <small>Create User, Update User, View User</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">Maintenance</a></li>
            <li class="active">Users</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
		<div id="msgDiv"></div>
          <!-- Default box -->
          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title"><i class="fa fa-globe"> &nbsp; </i><%=userLogin.getFirst_name()%> <%=userLogin.getLast_name()%></h3>
              <div class="box-tools pull-right">
                <button class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse"><i class="fa fa-minus"></i></button>
                <button class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            <div class="box-body">
         <div class="row invoice-info">
            <div class="col-sm-4 invoice-col">
              <b>Acitve : </b> <%=active%><br>
              <b>First Name : </b> <%=userLogin.getFirst_name()%><br>
              <b>Last Name : </b> <%=userLogin.getLast_name()%><br>
              <b>Mobile No : </b> <%=userLogin.getMobile_no()%><br>
              <b>Email Address : </b> <%=userLogin.getEmail_add()%><br>
              <b>Role : </b> <%=userRoleLoginName_userLogin%><br>
            </div><!-- /.col -->
            
            <div class="col-sm-4 invoice-col">
              <b>Date Created : </b> <%=date_created%><br>
              <b>Date Updated : </b> <%=date_updated%><br>
              <b>Created by : </b> <%=created_by%><br>
              <b>Updated by : </b> <%=updated_by%><br>
            </div><!-- /.col -->
          </div><!-- /.row -->
            </div><!-- /.box-body -->
            <!--
            <div class="box-footer">
              Footer
            </div> --> <!-- /.box-footer-->
            
         <div class="box-footer clearfix">
           <a href="#" onclick="createUser()" class="btn btn-sm btn-info btn-flat ">Create</a>&nbsp;&nbsp;
           <a href="#" onclick="updateUser('<%=userLogin.getId()%>')" class="btn btn-sm btn-info btn-flat ">Update</a>&nbsp;&nbsp;
           <a href="#" onclick="usersPage()" class="btn btn-sm btn-info btn-flat ">View All</a>
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
    <script src="js/pageNav.js"></script>
    <!-- page script -->
    <script>
 	function updateUser(id){
  	  	console.log("id: "+id);
		document.getElementById("action").value="updateUser";
		document.getElementById("userLoginId").value=id;
		document.frm.submit();
	}
    </script>
    <script>
    function createUser(){
  		document.getElementById("action").value="createUser";
  		document.frm.submit();
  	}


      <%if(msg!=null){%>
      
      var msgAlertDiv = "<div class='alert alert-danger alert-dismissable'>";
      msgAlertDiv = msgAlertDiv + "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>";
      msgAlertDiv = msgAlertDiv + "<h4><i class='icon fa fa-ban'></i> Alert!</h4><%=msg%>";
      msgAlertDiv = msgAlertDiv + "</div>";

      var msgCallOutDiv = "<div class='callout callout-success'>";
      msgCallOutDiv = msgCallOutDiv + "<h4>Success!</h4>";
      msgCallOutDiv = msgCallOutDiv + "<p><%=msg%></p>";
      msgCallOutDiv = msgCallOutDiv + "</div>";


	      <%if("Alert".equals(msgType)){%>
	      		document.getElementById("msgDiv").innerHTML=msgAlertDiv;
	      <%}else if("CallOut".equals(msgType)){%>
	      		document.getElementById("msgDiv").innerHTML=msgCallOutDiv;
	      <%}%>

      <%}else{%>
      		
      <%}%>

	setTimeout(
			function(){
				document.getElementById("msgDiv").innerHTML="";
			},4000
	);
      

    </script>
        <form action="controller" name="frm" id="frm" method="post">
    	<input type="hidden" name="action" id="action" value=""/>
    	<input type="hidden" name="emailAdd" id="emailAdd" value="<%=emailAdd%>"/>
    	<input type="hidden" name="sid" id="sid" value="<%=sid%>"/>
    	<input type="hidden" name="userLoginId" id="userLoginId" value=""/>
    </form>
  </body>
</html>
