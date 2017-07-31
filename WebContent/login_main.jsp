<!DOCTYPE html>
<%@ page import="java.util.List" %>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%String COMPANY_NAME = (String)request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"MARCVENTURES";%>    <title><%=COMPANY_NAME%></title>    <link rel="shortcut icon" href="favicon.ico" />
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="maxcdn.bootstrapcdn.com/font-awesome-4.5.0/css/font-awesome.min.css">
    <!-- Ionsns -->
    <link rel="stylesheet" href="code.ionicframework.com/ionicons-2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="plugins/iCheck/square/blue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
   <%
   	String msg = (String)request.getAttribute("msg");
    String msgType = (String)request.getAttribute("msgType");
    System.out.println("PAGE login.jsp : "+msg+" "+msgType);
   %>
  </head>
  <body class="hold-transition login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href="http://www.marcventuresholdings.com"><img alt="<%=COMPANY_NAME%>" src="img/mhi_logo.png" width="50%"></a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
      	<div id="msgDiv"></div>
        <p class="login-box-msg">Sign in to start your session</p>

        <form action="controller" method="post">
          <div class="form-group has-feedback">
            <input type="email" class="form-control" placeholder="Email" name="emailAdd" id="emailAdd" autocomplete="off" value="test-admin@marcventures.com.ph">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" placeholder="Password" name="passWord" id="passWord" autocomplete="off" value="password">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
          
            <div class="col-xs-8">
            <!-- 
              <div class="checkbox icheck">
                <label>
                  <input type="checkbox"> Remember Me
                </label>
              </div> -->
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
            </div><!-- /.col -->
          </div>
          <input type="hidden" name="action" id="action" value="signIn"/>
        </form>
<!-- 
        <div class="social-auth-links text-center">
          <p>- OR -</p>
          <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using Facebook</a>
          <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using Google+</a>
        </div>
        -->
        <!-- /.social-auth-links -->
 <!--
        <a href="#">I forgot my password</a><br>
        <a href="register.html" class="text-center">Register a new membership</a>
 -->
      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="plugins/iCheck/icheck.min.js"></script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });


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
  </body>
</html>
