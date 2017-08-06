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
<section class="instagram-section">
	<div class="container-fluid">
		<!-- <div id="instagram" class="row no-gutter"></div> -->
	</div>
</section>

<div id="footer">
	<div class="container">
		<div class="footer-lv1">
			<div class="row">
				<div class="col-md-8">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-3">
									<h3 class="top10"><a href="">HMR</a></h3>
								</div>
								<div class="col-md-9">
									<div class="row">
										<div class="col-sm-6">
											<h4 class="footer-header">Purchase</h4>
											<ul class="list-block">
																								<li>
													<a href="account">My Account</a>
												</li>
												<% if(user_id!=null){ %>
												<li>
												 	<a href="bid?mngr=get&a=my-bids">My Bids</a>
												</li>
												<% } %>
												<li>
													<a href="terms-and-conditions">Terms and Conditions</a>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="tarasd">
						<h4 class="footer-header">Subscribe to our Weekly Newsletter</h4>
						<form action="q/validate_email_subscribers" id="newsletter-form" method="post" accept-charset="utf-8" onkeypress="stopEnterSubmitting(window.event)">
						<div class="form-group">
							<div class="input-group">
								<input type="text" name="email" id="email" class="form-control" placeholder="Your email address">
								<span class="input-group-btn">
									<input type="submit" class="btn btn-primary" value="Join" style="width: 90px;" />
								</span>
							</div><!-- /input-group -->
							<div class="help-block"></div>
						</div>
						</form>					</div>

					<ul class="social-media-links social-media-links-inline">
						<li>
							<a target="_BLANK" href="#">
								<span class="social-icon"><img src="assets/themes/hmr/img/facebook-logo-white.png" class="img-responsive"></span>
							</a>
						</li>
						<li>
							<a target="_BLANK" href="#">
								<span class="social-icon"><img src="assets/themes/hmr/img/youtube-logo-white.png" class="img-responsive"></span>
							</a>
						</li>
						<li>
							<a target="_BLANK" href="#">
								<span class="social-icon"><img src="assets/themes/hmr/img/twitter-logo-white.png" class="img-responsive"></span>
							</a>
						</li>
						
					</ul>

					<div class="clearfix"></div>
					<div class="all-rights-reserved">
						HMR Auctions © 2017. All Rights Reserved.
					</div>

				</div>
			</div>
		</div>
	</div>
</div>