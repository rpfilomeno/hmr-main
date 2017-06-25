<!DOCTYPE html>
<%@ page 
		 import="java.util.List"
		 import="java.util.ArrayList"
		 import="java.util.HashMap"
		 import="bizoncloudone.com.bean.Request"
		 import="bizoncloudone.com.bean.ParamsLov"
		 import="javax.servlet.RequestDispatcher"
		 import="bizoncloudone.com.bean.Snapshot"
		 import="bizoncloudone.com.bean.UserLogin"
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
    <link rel="stylesheet" href="maxcdn.bootstrapcdn.com/font-awesome-4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="code.ionicframework.com/ionicons-2.0.1/css/ionicons.min.css">
    <!-- jvectormap -->
    <link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">
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
    	//String userRole = "admin";
		String sid = (String)request.getAttribute("sid")!=null ?  (String)request.getAttribute("sid") :  (String)request.getSession().getAttribute("sid");
		String emailAdd =(String)request.getAttribute("emailAdd");
	    UserLogin ul = (UserLogin)request.getSession().getAttribute("userLogin_"+sid);

		Integer userRoleLoginId = (Integer)request.getSession().getAttribute("userRoleLoginId_"+sid);
		String userRoleLoginName = (String)request.getSession().getAttribute("userRoleLoginName_"+sid);
		
		//HashMap<String,Snapshot> shm = (HashMap<String,Snapshot>)request.getSession().getAttribute("snapshotHM");
	    
	    if(ul!=null){
	    	System.out.println("PAGE dashboard.jsp: ul is not null");
	    }else{
	    	System.out.println("PAGE dashboard.jsp: ul is null");
	    	
	    	request.setAttribute("msgType", "Alert");
	    	request.setAttribute("msg", "Please Sign In.");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
	    }
	    
	    List<Request> reqList = (ArrayList<Request>) request.getAttribute("requestList");

	    HashMap<Integer,UserLogin> userLoginHM = (HashMap<Integer,UserLogin>) request.getSession().getAttribute("userLoginHM"); 

	    HashMap<Integer,ParamsLov> reqStatusParamLovHM = (HashMap<Integer,ParamsLov>) request.getSession().getAttribute("reqStatusParamLovHM"); 

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
            <li class="header">MAIN NAVIGATION</li>
 
            <li class="active treeview">
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
              <a href="#">
                <i class="fa fa-dashboard"></i>
                <span>Reports</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="#" onclick="reportsPage()"><i class="fa fa-circle-o"></i> Sales Performance</a></li>
                <li><a href="#" onclick="phFlashPage()"><i class="fa fa-circle-o"></i> Philippines Flash</a></li>

                
              </ul>
            </li> -->
            
            
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
            <li class="treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i>
                <span>Maintenance</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="#" onclick="usersPage()"><i class="fa fa-circle-o"></i> Users</a></li>
                <li><a href="#" onclick="rolesPage()"><i class="fa fa-circle-o"></i> Roles</a></li>
                <!-- 
                <li><a href="#" onclick="documentTypesPage()"><i class="fa fa-circle-o"></i> Document Types</a></li>
                <li><a href="#" onclick="requestorsPage()"><i class="fa fa-circle-o"></i> Requestors</a></li>
                -->
              </ul>
            </li>
            <%}%>
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

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            MV Admin Console
            <!-- <small>Version 2.0</small> -->
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">MV Admin Console</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <!-- Info boxes -->
          
          
      


          <!-- Main row -->
          <div class="row">
            <!-- Left col -->
            <div class="col-md-12">
              
              
              <div id="msgDiv"></div>
              
              <!-- TABLE: LATEST ACTIONS -->
              <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">Latest 7 Requests</h3>
                  <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                  </div>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <div class="table-responsive">
                     <table class="table no-margin">
                      <thead>
                        <tr>
                          <th>ID</th>
                          <th>User</th>
                          <th>Email</th>
                          <th>Status</th>
                          <th>Date Requested</th>
                          <th>Date Updated</th>
                          <th>Action</th>
                        </tr>
                      </thead>
                      <tbody>
 
 						<%
 						
 						System.out.println("sssssssss : ");

                      	for(Request req : reqList){

                            String date_created = "";

                            //System.out.println("req.getDate_created() : "+req);
                            //System.out.println("req.getDate_updated() : "+req.getDate_updated());
                            
                            if(req.getDate_created()!=null){
                            	date_created = sdf.format(req.getDate_created());
                            }

                            String date_updated = "";

                            if(req.getDate_updated()!=null){
                            	date_updated = sdf.format(req.getDate_updated());
                            }
                            
                            String user_name = "";
                            String email_add = "";

                            if(userLoginHM.get(req.getUser_id())!=null){
                            	UserLogin userLogin = userLoginHM.get(req.getUser_id());
                            	user_name = userLogin.getFirst_name() + " "+userLogin.getLast_name();
                            	email_add = userLogin.getEmail_add();
                            }
                            
                            Integer status_id = new Integer("0");
                            String status_name = "";
                            if(reqStatusParamLovHM.get(req.getStatus())!=null){
                            	ParamsLov paramsLov = reqStatusParamLovHM.get(req.getStatus());
                            	status_id = paramsLov.getId();
                            	status_name = paramsLov.getValue_name();
                            }
 						
 						%>
                        <tr>
                          <td><%=req.getId()%></td>
                          <td><%=user_name%></td>
                          <td><%=email_add%></td>
                          <td><%=status_name%></td>
                          <td><%=date_created%></td>
                          <td><%=date_updated%></td>
                          
                          
                          <td>
                          
                          <%
                          
                          System.out.println("status_id : "+status_id);
                          
                          if(status_id.equals(1)){
                          %>
                          
                          <a href="#" onclick="processActionSMS('<%=req.getId()%>','2', '<%=req.getVerify_code()%>');" class="btn btn-sm btn-default btn-flat pull-left">Change Password with SMS verification</a>
                          
                          <% } else if(status_id.equals(2)){
                          %>
                          
                          <a href="#" onclick="processAction('<%=req.getId()%>','<%=status_id%>');" class="btn btn-sm btn-default btn-flat pull-left">Change Password</a>
                          
                          <%}else if(status_id.equals(3)){ %>
                          
                          
                          <%}else if(status_id.equals(4)){ %>
                          
                          <a href="#" onclick="processActionSMSOverride('<%=req.getId()%>','4');" class="btn btn-sm btn-default btn-flat pull-left">Request Override</a>

                          </td>
                          <!-- <td><div class="sparkbar" data-color="#00a65a" data-height="20">90,80,90,-70,61,-83,63</div></td> -->
                        </tr>
                        
                        <%
                          }
 						}
                        %>
                        
                        <!-- 
                        <tr>
                          <td>User 2</td>
                          <td>User2@marcventures.com</td>
                          <td>Approved</td>
                          <td>February 4, 2016 8:00 AM</td>
                          <td>February 4, 2016 9:00 AM</td>
                          <td><a href="#" onclick="processAction('16sITgUoZzdLl67on2meDTUVUJo_jiA2KPjjEpcexFXk', '2016-02-01', '2016-02-29', '101', '2016-01-01', '2016-01-31');" class="btn btn-sm btn-default btn-flat pull-left">Change Password</a></td>
                          
                        </tr>
                        
                        <tr>
                          <td>User 3</td>
                          <td>User3@marcventures.com</td>
                          <td>Denied</td>
                          <td>February 3, 2016 8:00 AM</td>
                          <td>February 3, 2016 9:00 AM</td>
                          <td>&nbsp;</td>
                         
                        </tr>
                        
                        <tr>
                          <td>User 4</td>
                          <td>User4@marcventures.com</td>
                          <td>Lapse</td>
                          <td>February 2, 2016 8:00 AM</td>
                          <td>February 2, 2016 9:00 AM</td>
                          <td>&nbsp;</td>
                          
                        </tr>
                        
                        <tr>
                          <td>User 5</td>
                          <td>User5@marcventures.com</td>
                          <td>Overriden</td>
                          <td>February 1, 2016 8:00 AM</td>
                          <td>February 1, 2016 9:00 AM</td>
                          <td>&nbsp;</td>
                         
                        </tr>
                        -->
                        
                      </tbody>
                    </table>
                  </div><!-- /.table-responsive -->
                </div><!-- /.box-body -->
                
                
                
                
                <div class="box-footer clearfix">
                  <a href="#" onclick="createRequest()" class="btn btn-sm btn-info btn-flat">Create Request</a>
                  <a href="#" onclick="requestsPage()" class="btn btn-sm btn-info btn-flat">View All Request</a>
                </div>   <!-- /.box-footer -->
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
    <!-- FastClick -->
    <script src="plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js"></script>
    <!-- Sparkline -->
    <script src="plugins/sparkline/jquery.sparkline.min.js"></script>
    <!-- jvectormap -->
    <script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <!-- SlimScroll 1.3.0 -->
    <script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- ChartJS 1.0.1 -->
    <script src="plugins/chartjs/Chart.min.js"></script>
    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
    <!-- <script src="dist/js/pages/dashboard2.js"></script> -->
    <!-- AdminLTE for demo purposes -->
    <script src="dist/js/demo.js"></script>
    <script src="js/pageNav.js"></script>
    <script type="text/javascript">
    function createRequest(){
    	document.getElementById("action").value="createRequest";
  		document.frm.submit();
  	}
  	
  	function viewBooking(id){
  	  	console.log("id: "+id);
		document.getElementById("action").value="viewBooking";
		document.getElementById("bookingId").value=id;
		document.frm.submit();
	}

  	function processAction(id, status_id, respond){
  	  	console.log("id: "+id);
		document.getElementById("action").value="processAction";
		document.getElementById("requestId").value=id;
		document.getElementById("status_id").value=status_id;
		document.getElementById("respond").value=respond;

		document.frm.submit();
	}

  	function processActionSMS(id, status_id, verify_code){
  	  	console.log("id: "+id);
		document.getElementById("action").value="processAction";
		document.getElementById("requestId").value=id;
		document.getElementById("status_id").value=status_id;
		document.getElementById("respond").value=respond;


	    var vf = prompt("Please enter 4 number verification code.", "");

	    //alert(vf);
	    //alert(verify_code);
	    
	    if (vf != null) {
	        //document.getElementById("demo").innerHTML = "Hello " + vf + "! How are you today?";

	    	document.getElementById("verify_code").value=vf;

	    	if(vf==verify_code){
	    		document.frm.submit();
		    }else{
			    alert('Verification code does not match.');
			}
	    }else{
		    
		}
		
	}

  	function processActionSMSOverride(id, status_id){
  	  	console.log("id: "+id);
		document.getElementById("action").value="processAction";
		document.getElementById("requestId").value=id;
		document.getElementById("status_id").value=status_id;
	}
	
    </script>
    
    <form action="controller" name="frm" id="frm" method="post">
    	<input type="hidden" name="action" id="action" value=""/>
    	<input type="hidden" name="emailAdd" id="emailAdd" value="<%=emailAdd%>"/>
    	<input type="hidden" name="sid" id="sid" value="<%=sid%>"/>
    	<input type="hidden" name="requestId" id="requestId" value=""/>
    	<input type="hidden" name="status_id" id="status_id" value=""/>
    	<input type="hidden" name="respond" id="respond" value=""/>
    	<input type="hidden" name="verify_code" id="verify_code" value=""/>
    	
    	<!-- 
    	<input type="hidden" name="spreadsheetId" id="spreadsheetId" value=""/>
    	<input type="hidden" name="dateFrom" id="dateFrom" value=""/>
    	<input type="hidden" name="dateTo" id="dateTo" value=""/>
    	<input type="hidden" name="storeNo" id="storeNo" value=""/>
    	<input type="hidden" name="dateFromLY" id="dateFromLY" value=""/>
    	<input type="hidden" name="dateToLY" id="dateToLY" value=""/>
-->
    	
    </form>
  </body>
</html>
