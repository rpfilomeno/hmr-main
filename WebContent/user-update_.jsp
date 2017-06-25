<!DOCTYPE html>
<%@ page import="bizoncloudone.com.bean.UserLogin"
		 import="bizoncloudone.com.bean.RoleLogin"
		 import="java.util.List"
		 import="java.util.ArrayList"
		 import="javax.servlet.RequestDispatcher"
		 import="java.util.HashMap" %>
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
		String userRoleLoginName = (String)request.getSession().getAttribute("userRoleLoginName_"+sid);
		
		ArrayList<RoleLogin> rlList = (ArrayList<RoleLogin>) request.getSession().getAttribute("roleLoginList");
	    
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
	    System.out.println("userLogin active "+userLogin.getActive());
	    
	    System.out.println("userRoleLoginId_userLogin "+userRoleLoginId_userLogin);
	    System.out.println("userRoleLoginName_userLogin "+userRoleLoginName_userLogin);
    
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
            <small>Update User</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">Maintenance</a></li>
            <li class="active">Users</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">

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
	        <form action="controller" name="frm" id="frm" method="post" role="form">
	    	<input type="hidden" name="action" id="action" value="saveUser"/>
	    	<input type="hidden" name="emailAdd" id="emailAdd" value="<%=emailAdd%>"/>
	    	<input type="hidden" name="sid" id="sid" value="<%=sid%>"/>
	    	<input type="hidden" name="userLoginId" id="userLoginId" value="<%=userLogin.getId()%>"/>
              <div class="form-group">
                <label><b>Acitve : </b></label><!-- String email_add, String pass_word, String first_name, String last_name, Boolean active, String updated_by -->
				<!-- <input type="text" class="form-control" placeholder="Acitve" value="<%=userLogin.getActive()%>" id="active" name="active" autocomplete="off"/> -->
				<select class="form-control" id="active" name="active"><option value="true">Yes</option><option value="false">No</option></select>
              </div>
              <div class="form-group">
                <label><b>First Name : </b></label>
				<input type="text" class="form-control" placeholder="First Name" value="<%=userLogin.getFirst_name()%>" id="first_name" name="first_name" autocomplete="off"/>
              </div>
              <div class="form-group">
                <label><b>Last Name : </b></label>
				<input type="text" class="form-control" placeholder="Last Name" value="<%=userLogin.getLast_name()%>"  id="last_name" name="last_name" autocomplete="off"/>
              </div>
              <div class="form-group">
                <label for="email_add"><b>Email Address : </b></label>
				<input type="text" class="form-control" placeholder="Email Address" value="<%=userLogin.getEmail_add()%>"  id="email_add" name="email_add" autocomplete="off"/>
              </div>
              <div class="form-group">
                <label><b>Password : </b></label>
				<input type="password" class="form-control" placeholder="Password" value="**********"  id="pass_word" name="pass_word" autocomplete="off"/>
              </div>
              <div class="form-group">
                <label><b>Mobile No : </b></label>
				<input type="email" class="form-control" placeholder="Contact No" value="<%=userLogin.getMobile_no()%>"  id="mobile_no" name="mobile_no" autocomplete="off"/>
              </div>
              <div class="form-group">
                <label><b>Role : </b></label>
				<select class="form-control" id="role_id" name="role_id">
					<%for(RoleLogin rl :rlList){ %>
						<option value="<%=rl.getId()%>"><%=rl.getRole_name()%></option>
					<%}%>
				</select>
              </div>
              </form>
            </div><!-- /.col -->
            
            <!-- 
            <div class="col-sm-4 invoice-col">
            <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">Horizontal Form</h3>
                </div>
                <form class="form-horizontal">
                  <div class="box-body">
                    <div class="form-group">
                      <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                      <div class="col-sm-10">
                        <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                      <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                          <label>
                            <input type="checkbox"> Remember me
                          </label>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="box-footer">
                    <button type="submit" class="btn btn-default">Cancel</button>
                    <button type="submit" class="btn btn-info pull-right">Sign in</button>
                  </div>
                </form>
              </div>
            </div>
             -->
          </div><!-- /.row -->
            </div><!-- /.box-body -->
            <!--
            <div class="box-footer">
              Footer
            </div> --> <!-- /.box-footer-->
            
         <div class="box-footer clearfix">
           <a href="#" onclick="saveUser()" class="btn btn-sm btn-info btn-flat ">Save</a>&nbsp;&nbsp;
           <a href="#" onclick="usersPage()" class="btn btn-sm btn-info btn-flat ">Cancel</a>
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
 	function saveUser(){
  	//  	console.log("id: "+id);
	//	document.getElementById("action").value="saveUser";
	//	document.getElementById("userLoginId").value=id;
		document.frm.submit();
	}

 	document.getElementById("active").value='<%=userLogin.getActive()%>';
 	document.getElementById("role_id").value='<%=userRoleLoginId_userLogin%>';
    </script>
  </body>
</html>
