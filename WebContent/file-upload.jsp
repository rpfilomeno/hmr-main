<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    <%    String COMPANY_NAME = (String)request.getSession().getAttribute("COMPANY_NAME");    String SKIN_HEADER_COLOR = (String)request.getSession().getAttribute("SKIN_HEADER_COLOR");    %>    <title><%=COMPANY_NAME%></title>    <link rel="shortcut icon" href="favicon.ico" />
    </head>
 
    <body> 
        <div>
            <h3> Choose File to Upload in Server </h3>
            <form action="upload" method="post" enctype="multipart/form-data">
            	<input type="hidden" id="bookingId" name="bookingId" value="">
            	<input type="hidden" id="doc_type_id" name="doc_type_id" value="">
            	<input type="hidden" id="file_name" name="file_name" value="">
            	<input type="hidden" id="file_type" name="file_type" value="">
            	<input type="hidden" id="status" name="status" value="">
            	<input type="hidden" id="created_by" name="created_by" value="">
                <input type="file" name="file" />
                <input type="submit" value="upload" />
            </form>   
        </div>
      
    </body>
    <script type="text/javascript">

    document.getElementById('bookingId').value = window.opener.document.getElementById('bookingId').value;
    document.getElementById('doc_type_id').value = window.opener.document.getElementById('doc_type_id').value;
    document.getElementById('file_name').value = window.opener.document.getElementById('file_name').value;
    document.getElementById('file_type').value = window.opener.document.getElementById('file_type').value;
    document.getElementById('status').value = window.opener.document.getElementById('status').value;
    document.getElementById('created_by').value = window.opener.document.getElementById('created_by').value;


    console.log("file-upload.jsp: bookingId : "+document.getElementById('bookingId').value);
    console.log(document.getElementById('doc_type_id').value);
    console.log(document.getElementById('file_name').value);
    console.log(document.getElementById('file_type').value);
    console.log(document.getElementById('status').value);
    console.log(document.getElementById('created_by').value);
    </script>
</html>
