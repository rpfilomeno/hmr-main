<!DOCTYPE html>
<html>
  <%
  String manager = (String)request.getAttribute("manager");
  String action = (String)request.getAttribute("action");
  String userId = (String)request.getAttribute("userId");
  String sid = (String)request.getAttribute("sid");
  String vek = (String)request.getAttribute("vek");
  String aid = (String)request.getAttribute("aid");

  
  System.out.println("PAGE security-check.jsp : manager = "+manager+" / action = "+action+" / userId = "+userId+" / sid = "+sid+" / vek = "+vek+" / aid = "+aid);
  %>
  <head>
    <title>HMR Auctions</title>
  </head>
  <body>
 	<form action="bid" name="frm" id="frm" method="post">
 		<input type="hidden" id="manager" name="manager" value="<%=manager%>"/>
 		<input type="hidden" id="action" name="action" value="<%=action%>"/>
 		<input type="hidden" id="userId" name="userId" value="<%=userId%>"/>
 		<input type="hidden" id="sid" name="sid" value="<%=sid%>"/>
 		<input type="hidden" id="vek" name="vek" value="<%=vek%>"/>
 		<input type="hidden" id="aid" name="aid" value="<%=aid%>"/>
 	</form>
 	Verifying Access...
  </body>
  <script>
		document.frm.submit();
  </script>
</html>
