<!DOCTYPE html>
<html>
  <%
  String msg = (String)request.getAttribute("msg");
   
  System.out.println("PAGE request-msg.jsp : msg = "+msg);
  %>
  <head>
    <title>IPC Google</title>
  </head>
  <body>
 	<%=msg%>
  </body>
  <script>
	setTimeout(
			function(){
				window.close();
			},4000
	);
  </script>
</html>
