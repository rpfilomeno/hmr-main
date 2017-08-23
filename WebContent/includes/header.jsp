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
<div id="ms--main--nav">
	<nav id="main-navigation" class="navbar navbar-fixed-top">
		<div id="top-nav">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="top-nav-items nav-white visible-md visible-lg">

							<ul class="top-navbar">
								<li>(02)548-6962</li>
								<!-- <li>0917 548 3603</li>-->
								<li><a href="mailto:online-auctions@HMRBID.COM">online-auctions@HMRBID.COM</a>
								</li>
								<li><a href="">Home</a></li>
								<% if(fullName!=null && !"null".equals(fullName)){%>
								<li><a href="bid?mngr=get&a=logout&uid=<%=userId%>">Logout</a>
								</li>
								<% } else { %>
								<li><a href="bid?mngr=get&a=login">Login</a></li>
								<li><a href="bid?mngr=get&a=registration">Sign-Up</a></li>
								<% } %>

							</ul>


						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row gutter-0">
				<div class="col-md-2">
					<div class="site--logo">
						<a class="logo-default" href="bid"><img
							src="assets/themes/hmr/img/HMR-logo-white.png" alt="HMR Auctions"
							class="img-responsive"></a>
					</div>
				</div>

				<div class="col-md-10">
					<div class="row gutter-0">
						<div class="col-md-8">

							<form action="bid" id="nav-search-form" method="post"
								accept-charset="utf-8"
								onkeypress="stopEnterSubmitting(window.event)">

								<div class="input-group nav-search-group">
									<input type="text" class="form-control" name="nav-search-input"
										id="nav-search-input"
										placeholder="Search for products, brands, shops"> <span
										class="input-group-btn">
										<button class="btn btn-default nav-search-btn" type="submit">
											<span class="ion-ios-search-strong"></span>
										</button>
									</span>
								</div>

								<input type="hidden" name="manager" id="manager"
									value="search-manager" /> <input type="hidden" name="action"
									id="action" value="" /> <input type="hidden" name="userId"
									id="userId" value="<%=userId %>" /> <input type="hidden"
									name="user-id" id="user-id" value="<%=user_id%>" />
							</form>
						</div>
						<div class="col-md-4">
							<div id="main-nav-items"
								class="main-nav-items nav-white visible-md visible-lg">

								<ul class="nav navbar-nav navbar-right navbar-icons">
								    <% if(user_id!=null){ %>
									<li>
									 	<a href="bid?mngr=get&a=my-watchlist">Watch List</a>
									</li>
									<% } %>
									<% if (user_id==null) {%>
									<li><a href="bid?mngr=get&a=login">My Account</a></li>
									<% } else { %>
									<li><a href="bid?mngr=get&a=my-profile">My Account</a></li>
									<% } %>
									<li><a href="#"> <span id="bag-count"></span> <span
											class="navbar-icon ion-bag"></span>
									</a></li>


								</ul>


							</div>
						</div>
					</div>



				</div>
			</div>
		</div>
		<jsp:include page="sub-navigation.jsp"></jsp:include>
	</nav>




	<div id="mobile-nav-trigger" class="visible-sm visible-xs">
		<div id="nav-trigger">
			<span></span> <span></span> <span></span>
		</div>
	</div>

	<div id="mobile-nav-wrapper">
		<div id="mobile-logo" class="site--logo">
			<a class="logo-default" href=""><img
				src="assets/themes/hmr/img/HMR-logo-white.png" alt="HMR Auctions"
				class="img-responsive"></a>
		</div>
		<div id="mobile-nav-inject"></div>
	</div>




	<div id="search-dialog" class="ms-dialog hide">
		<div class="ms-dialog-header">
			<a href="#" class="ms-dialog-close-btn"><span
				class="ion-ios-close-empty"></span></a>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2">

					<form action="" id="search-form" method="post"
						accept-charset="utf-8"
						onkeypress="stopEnterSubmitting(window.event)">

						<div class="search-input-wrap">
							<input type="text" class="form-control" id="search-input"
								placeholder="Search">
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>

</div>

