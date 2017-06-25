<!DOCTYPE html>
<%@ page import="bizoncloudone.com.bean.UserLogin"		 import="bizoncloudone.com.bean.Request"		 import="bizoncloudone.com.bean.ParamsLov"		 import="java.util.List"
		 import="java.util.ArrayList"		 import="java.util.HashMap" 
		 import="javax.servlet.RequestDispatcher"
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
    <link rel="stylesheet" href="maxcdn.bootstrapcdn.com/font-awesome-4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="code.ionicframework.com/ionicons-2.0.1/css/ionicons.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
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
    </style>
    <%
		String sid =(String)request.getAttribute("sid");
		String emailAdd =(String)request.getAttribute("emailAdd");
	    UserLogin ul = (UserLogin)request.getSession().getAttribute("userLogin_"+sid);
	    Integer userRoleLoginId = (Integer)request.getSession().getAttribute("userRoleLoginId_"+sid);
	    if(ul!=null){
	    	System.out.println("PAGE request-list.jsp: ul is not null");
	    }else{
	    	System.out.println("PAGE request-list.jsp: ul is null");
	    	request.setAttribute("msgType", "Alert");
	    	request.setAttribute("msg", "Please Sign In.");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
	    }
	    List<Request> reqList = (ArrayList<Request>) request.getAttribute("requestList");
	    HashMap<Integer,UserLogin> userLoginHM = (HashMap<Integer,UserLogin>) request.getSession().getAttribute("userLoginHM"); 	    HashMap<Integer,ParamsLov> reqStatusParamLovHM = (HashMap<Integer,ParamsLov>) request.getSession().getAttribute("reqStatusParamLovHM"); 
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    %>
  </head>
  <body class="hold-transition skin-<%=SKIN_HEADER_COLOR%> sidebar-mini">
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
            <li class="header">MAIN NAVIGATION</li>            <li class="treeview">              <a href="#" onclick="dashboardPage()">                <i class="fa fa-dashboard"></i> <span>MV Admin Console</span>              </a>            </li>                            <li class="treeview active">              <a href="#" onclick="requestsPage()">                <i class="fa fa-dashboard"></i> <span>Requests</span>              </a>            </li>            <!--             <li class="treeview">              <a href="#" onclick="bookingsPage()">                <i class="fa fa-dashboard"></i> <span>Bookings</span>              </a>            </li> -->
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
            </li> -->			<%if(userRoleLoginId.equals(1) || userRoleLoginId.equals(2)){%>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i>
                <span>Maintenance</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li class="active"><a href="#" onclick="usersPage()"><i class="fa fa-circle-o"></i> Users</a></li>
                <li><a href="#" onclick="rolesPage()"><i class="fa fa-circle-o"></i> Roles</a></li>                <!--                 <li><a href="#" onclick="documentTypesPage()"><i class="fa fa-circle-o"></i> Document Types</a></li>                <li><a href="#" onclick="requestorsPage()"><i class="fa fa-circle-o"></i> Requestors</a></li>                -->
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

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Requests
            <small>Create Request, Update Request, View Request</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#" class="active">Requests</a></li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">                <div id="msgDiv"></div>
          <div class="row">
            <div class="col-xs-12">
              <div class="box box-info">
                <div class="box-header">
                  <h3 class="box-title">List of Requests</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                <div class="table-responsive table-responsive1">
                  <table id="example1" class="table table-bordered table-striped no-margin">
                    <thead>
                      <tr>                        <th>ID</th>
                        <th>User</th>
                        <th>Email</th>
                        <th>Status</th>
                        <th>Date Created</th>
                        <th>Date Updated</th>
                      </tr>
                    </thead>                    
                    <tbody>
                      <%
                      	for(Request req : reqList){
                            String date_created = "";
                            if(req.getDate_created()!=null){
                            	date_created = sdf.format(req.getDate_created());
                            }
                            String date_updated = "";
                            if(req.getDate_updated()!=null){
                            	date_updated = sdf.format(req.getDate_updated());
                            }                                                        String user_name = "";                            String email_add = "";                            if(userLoginHM.get(req.getUser_id())!=null){                            	UserLogin userLogin = userLoginHM.get(req.getUser_id());                            	user_name = userLogin.getFirst_name() + " "+userLogin.getLast_name();                            	email_add = userLogin.getEmail_add();                            }                                                        Integer status_id = new Integer("0");                            String status_name = "";                            if(reqStatusParamLovHM.get(req.getStatus())!=null){                            	ParamsLov paramsLov = reqStatusParamLovHM.get(req.getStatus());                            	status_id = paramsLov.getId();                            	status_name = paramsLov.getValue_name();                            }
                      %>
                          <tr>                            <td><a href="#" onclick="view('<%=req.getId()%>')"><%=req.getId()%></a></td>
                            <td><%=user_name%></td>
                            <td><%=email_add%></td>                            <td><%=status_name%></td>
                            <td><%=date_created%></td>
                            <td><%=date_updated%></td>
                          </tr>
                      <%}%>
                     
                    </tbody>
                    <!-- 
                    <tfoot>
                      <tr>
                        <th>Email Address</th>
                        <th>Last Name</th>
                        <th>First Name</th>
                        <th>Active</th>
                        <th>Date Created</th>
                        <th>Date Updated</th>
                      </tr>
                    </tfoot>  -->
                  </table>
                  </div><!-- /.table-responsive -->
                </div><!-- /.box-body -->
                
                <div class="box-footer clearfix">
                  <a href="#" onclick="create()" class="btn btn-sm btn-info btn-flat pull-left">Create</a>
                </div><!-- /.box-footer -->
              </div><!-- /.box -->
            </div><!-- /.col -->
          </div><!-- /.row -->
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
    <!-- DataTables -->
    <script src="plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
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
    $(function () {
      $("#example1").DataTable({        	"order": [[ 4, "desc" ]]      });
    });
    function create(){
  		document.getElementById("action").value="createRequest";
  		document.frm.submit();
  	}
  	function view(id){
  	  	console.log("id: "+id);
		document.getElementById("action").value="viewRequest";
		document.getElementById("requestId").value=id;
		document.frm.submit();
	}
    </script>
    <form action="controller" name="frm" id="frm" method="post">
    	<input type="hidden" name="action" id="action" value=""/>
    	<input type="hidden" name="emailAdd" id="emailAdd" value="<%=emailAdd%>"/>
    	<input type="hidden" name="sid" id="sid" value="<%=sid%>"/>
    	<input type="hidden" name="requestId" id="requestId" value=""/>
    </form>
  </body>
</html>
