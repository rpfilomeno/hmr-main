<%@ page import="bizoncloudone.com.bean.UserLogin"
		 import="java.util.List"
		 import="java.util.ArrayList"
		 import="javax.servlet.RequestDispatcher" %>
<%
String sid = (String)request.getAttribute("sid")!=null ?  (String)request.getAttribute("sid") :  (String)request.getSession().getAttribute("sid");
UserLogin ul = (UserLogin)request.getSession().getAttribute("userLogin_"+sid);
System.out.println("PAGE main-header.jsp");
System.out.println("ul : "+ul);
Integer userRoleLoginId = (Integer)request.getSession().getAttribute("userRoleLoginId_"+sid);
String userRoleLoginName = (String)request.getSession().getAttribute("userRoleLoginName_"+sid);
String COMPANY_NAME = (String)request.getSession().getAttribute("COMPANY_NAME");
String COMPANY_NAME_ACRONYM = (String)request.getSession().getAttribute("COMPANY_NAME_ACRONYM");
System.out.println("userRoleLoginId : "+userRoleLoginId);
System.out.println("userRoleLoginName : "+userRoleLoginName);
%>
      <header class="main-header">
        <!-- Logo -->
        <a href="#" onclick="dashboardPage()" class="logo">
        
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b><%=COMPANY_NAME_ACRONYM%></b></span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg" style="font-size:14px;"><b><%=COMPANY_NAME%></b></span>
        </a>

        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <!-- Navbar Right Menu -->
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="userphotos/<%=ul.getId()%>-160x160.jpg" class="user-image" alt="User Image">
                  <span class="hidden-xs"><%=ul.getFirst_name()%>&nbsp;<%=ul.getLast_name()%></span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                    <img src="userphotos/<%=ul.getId()%>-160x160.jpg" class="img-circle" alt="User Image">
                    <p>
                      <%=ul.getFirst_name()%>&nbsp;<%=ul.getLast_name()%> - <%=userRoleLoginName%>
                      <small>Member since <%=ul.getDate_created()%></small> 
                    </p>
                  </li>
                  <!-- Menu Body -->
                  <!-- 
                  <li class="user-body">
                    <div class="col-xs-4 text-center">
                      <a href="#">Followers</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">Sales</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">Friends</a>
                    </div>
                  </li> -->
                  <!-- Menu Footer-->
                  <li class="user-footer">
                  <!-- 
                    <div class="pull-left">
                      <a href="#" class="btn btn-default btn-flat">Profile</a>
                    </div> -->
                    <div class="pull-left">
                      <a href="#" class="btn btn-default btn-flat" onclick="changePasswordPage()">Change Password</a>
                    </div>
                    <div class="pull-right">
                      <a href="#" class="btn btn-default btn-flat" onclick="signOutPage()">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>
              <!-- Control Sidebar Toggle Button -->
              <%if(userRoleLoginId.equals(1)){%>
              <li>
                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
              </li>
              <%}%>
            </ul>
          </div>        </nav>
      </header>
      
      <% System.out.println("End main-header : "); %>