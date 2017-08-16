<%	
String COMPANY_NAME = (String)request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";
String COMPANY_NAME_ACRONYM = (String)request.getSession().getAttribute("COMPANY_NAME_ACRONYM");

String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";

//TODO: messages displayed on page should be session based only to avoid XSS
if(msgInfo==null) {
	msgInfo = (String)request.getSession().getAttribute("msgInfo")!=null ? (String)request.getSession().getAttribute("msgInfo") :null;
	msgbgcol = (String)request.getSession().getAttribute("msgbgcol")!=null ? (String)request.getSession().getAttribute("msgbgcol") :"";
}
request.getSession().removeAttribute("msgInfo");
request.getSession().removeAttribute("msgbgcol");


String firstName = (String)request.getSession().getAttribute("firstName");
String lastName = (String)request.getSession().getAttribute("lastName");
String fullName = (String)request.getSession().getAttribute("fullName");
String userId = (String)request.getSession().getAttribute("userId");
String aid = (String)request.getSession().getAttribute("auctionId");
String currency = "PHP";

// IDs
Integer user_id = request.getSession().getAttribute("user-id")!=null ? (Integer)request.getSession().getAttribute("user-id") : null;
Integer user_role_id = request.getSession().getAttribute("user-role-id")!=null ? (Integer)request.getSession().getAttribute("user-role-id") : 0;

%>
<div id="sub-navigation">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <ul class="navbar-subnav">
          	<li>
              <a href="">Home</a>
            </li>
          	<% if(user_role_id==1 || user_role_id ==4) { %>
          	<li>
              <a href="bid?mngr=auction-manager&a=auctionList&uid=<%=userId %>">Administration</a>
            </li>
            <% } %>
            <% if(user_id!=null){ %>
			<li>
			 	<a href="bid?mngr=get&a=my-bids">My Bids</a>
			</li>
			<% } %>
            <li>
              <a href="bid?mngr=pages&a=services">Services</a>
            </li>
            <li>
              <a href="bid?mngr=pages&a=gallery">Gallery</a>
            </li>
            <li>
              <a href="bid?mngr=pages&a=contactus">Contact Us</a>
            </li>
          </ul>    
        </div>
      </div>
    </div>
  </div>