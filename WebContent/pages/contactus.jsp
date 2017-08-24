<!DOCTYPE html>
<%@ page import="java.text.SimpleDateFormat" %>
<%

	String COMPANY_NAME = (String)request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";
	String COMPANY_NAME_ACRONYM = (String)request.getSession().getAttribute("COMPANY_NAME_ACRONYM");

	String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
	String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";
	  
	String firstName = (String)request.getSession().getAttribute("firstName");
	String lastName = (String)request.getSession().getAttribute("lastName");
	String fullName = (String)request.getSession().getAttribute("fullName");
	String userId = (String)request.getSession().getAttribute("userId");
	
	
	// IDs
	Integer user_id = request.getSession().getAttribute("user-id")!=null ? (Integer)request.getSession().getAttribute("user-id") : null;
	Integer user_role_id = request.getSession().getAttribute("user-role-id")!=null ? (Integer)request.getSession().getAttribute("user-role-id") : 0;

	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy  HH:mm");
	
%>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <jsp:include page="../includes/header-meta.jsp"></jsp:include>
        <style type="text/css">
        	ul.contact {
        		list-style: none;
        	}
        
	        ul.contact li.phone:before {
			   font-family: FontAwesome;
			   content: "\f095";
			   display: inline-block;
			   padding-right: 3px;
			   vertical-align: middle;
			   width: 12px;
			}
        
	        ul.contact li.email:before {
			   font-family: FontAwesome;
			   content: "\f003";
			   display: inline-block;
			   padding-right: 3px;
			   vertical-align: middle;
			   width: 12px;
			}
			
			ul.contact li.map:before {
			   font-family: FontAwesome;
			   content: "\f0ac";
			   display: inline-block;
			   padding-right: 3px;
			   vertical-align: middle;
			   width: 12px;
			}
			
			p.map:before {
			   font-family: FontAwesome;
			   content: "\f041";
			   display: inline-block;
			   padding-right: 3px;
			   vertical-align: middle;
			   width: 12px;
			}
			
			.well {
    			background: #fcfcfc;
			}
			
			
        </style>
    </head>
    <body data-is-mobile="" id="c" >
    
    <input type="hidden" id="base_url" value="">
        <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <h4>outdated</h4> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

<jsp:include page="../includes/header.jsp"></jsp:include>

	
<div id="ms--main--body">
	

	
	<section id="hmr-main-container">
		<div class="container">
			<div class="row gutter-10">
				<div data-alerts="alerts" data-titles='{"warning": "<em>Warning!</em>", "error": "<em>Error!</em>"}' data-ids="myid" data-fade="3000"></div>
				
					<div class="col-md-4 col-lg-3">
					<jsp:include page="../includes/side-widget.jsp"></jsp:include>
					</div>
					
					<div class="col-md-8 col-lg-9">	
						<section id="hmr-main-body">
							<div class="row section-row">
								<div class="col-md-12">
									<h2 id="contactus">Contact Us</h2>
									<hr>
									
									<p>Call any of our account managers today to know more about the customized 
									surplus asset management solution that we can offer your company.</p>
									
									<div class="col-md-6 col-xs-12">
										<div class="well">
											<h5>HMR Auction Services, Inc. - Dau, Pampanga Branch</h5>
											<p class="map">ETASI Warehouse, Brgy. Duquit, Dau, Mabalacat, Pampanga</p>
											<ul class="contact">
												<li class="phone">&nbsp;0917 552-1795</li>
												<li class="phone">&nbsp;0917 822-3599</li>
												<li class="phone">&nbsp;0917 621-8843</li>
												<li class="phone">&nbsp;0919 615-3431</li>
												<li class="email">&nbsp;dau@hmrphils.com</li>
												<li class="map">&nbsp;<a href="https://goo.gl/maps/L8ckB9czjeA2" target="_blank" rel="noopener noreferrer">see location map</a></li>
											</ul>
										</div>
										
										<div class="well">
											<h5>HMR Auction Services, Inc. - West Service Road Branch</h5>
											<p class="map">KM. 21, West Service Rd., South Superhighway, Sucat, Muntinlupa City</p>
											<ul class="contact">
												<li class="phone">&nbsp;(02) 576-7829</li>
												<li class="phone">&nbsp;0917 830-7717</li>
												<li class="email">&nbsp;wsr@hmrphils.com</li>
												<li class="map">&nbsp;<a href="https://goo.gl/maps/MA9uJQoRzPr" target="_blank" rel="noopener noreferrer">see location map</a></li>
											</ul>
										</div>
										
										<div class="well">
											<h5>HMR Auction Services, Inc. - Sucat, Muntinlupa Main Office</h5>
											<p class="map">KM. 21, East Service Rd., South Superhighway, Sucat, Muntinlupa City</p>
											<ul class="contact">
												<li class="phone">&nbsp;(02) 548-6962</li>
												<li class="phone">&nbsp;0917 548-3603</li>
												<li class="email">&nbsp;auction@hmrphils.com</li>
												<li class="map">&nbsp;<a href="https://www.google.com.ph/maps/place/HMR/@14.4505273,121.0507096,15z/data=!4m5!3m4!1s0x3397cfc66f8a90b9:0xeecf422a4189e26b!8m2!3d14.4508389!4d121.0472453?hl=en" target="_blank" rel="noopener noreferrer">see location map</a></li>
											</ul>
										</div>
										
										<div class="well">
											<h5>HMR Auction Services, Inc. - Mandaue, Cebu Branch</h5>
											<p class="map">Logarta Ave., Brgy. Subangdaku, North Reclamation Area, 6014 Mandaue City, Cebu</p>
											<ul class="contact">
												<li class="map">&nbsp;<a href="https://wego.here.com/directions/mix//HMR-Cebu,-Logarta-Ave.,-Brgy.-Subangdaku,-North-Reclamation-Area,-Mandaue-City:e-eyJuYW1lIjoiSE1SIENlYnUiLCJhZGRyZXNzIjoiTG9nYXJ0YSBBdmUuLCBCcmd5LiBTdWJhbmdkYWt1LCBOb3J0aCBSZWNsYW1hdGlvbiBBcmVhLCBNYW5kYXVlIENpdHkiLCJsYXRpdHVkZSI6MTAuMzIzNiwibG9uZ2l0dWRlIjoxMjMuOTIyLCJwcm92aWRlck5hbWUiOiJmYWNlYm9vayIsInByb3ZpZGVySWQiOjE3OTMyNzU4NTgzNzc0N30=?map=10.3236,123.922,15,normal&fb_locale=en_GB" target="_blank" rel="noopener noreferrer">see location map</a></li>
											</ul>
										</div>
									</div>
									
									<div class="col-md-6 col-xs-12">
										<form action="bid" 
											class="form-contactus" 
											name="frm" 
											data-toggle="validator" 
											role="form"
											method="post"
											onkeypress="stopEnterSubmitting(window.event)">
			                            	<div class="row">
			                            	
				                            	<div class="col-md-12">
				                                    <div class="form-group has-feedback">
				                                    	<div class="input-group">
				                                    		<span class="input-group-addon" title="* First name">First name</span>
				                                    		<input class="form-control" 
				                                    			type="text" 
				                                    			name="firstName" 
				                                    			placeholder="First Name" 
				                                    			maxlength="50" 
				                                    			pattern="^(?:[A-z]+\.{0,1}\s{0,1})+[A-z]+$"
				                                    			data-error="Must contain only word(s) not ending in space"
				                                    			value="" 
				                                    			required />
				                                    	</div>
				                                    	<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
				    									<div class="help-block with-errors"></div>
				                                	</div>
				                                	
				                                	<div class="form-group has-feedback">
				                                    	<div class="input-group">
				                                    		<span class="input-group-addon" title="* Last Name">Last Name</span>
				                                    		<input class="form-control" 
				                                    			type="text" 
				                                    			name="lastName" 
				                                    			placeholder="Last Name" 
				                                    			maxlength="50" 
				                                    			pattern="^(?:[A-z]+\.{0,1}\s{0,1})+[A-z]+$"
				                                    			data-error="Must contain only word(s) not ending in space"
				                                    			value="" 
				                                    			required />
				                                    	</div>
				                                    	<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
				    									<div class="help-block with-errors"></div>
				                                	</div>
				                                	
				                                	<div class="form-group has-feedback">
				                                    	<div class="input-group">
				                                    		<span class="input-group-addon" title="* Email">Email</span>
				                                    		<input class="form-control" 
				                                    			type="email" name="userEmail" 
				                                    			placeholder="Email@domain.com" 
				                                    			maxlength="50" 
				                                    			value=""
				                                    			data-error="Must contain valid email address"
				                                    			required />
				                                		</div>
				                                		<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
				    									<div class="help-block with-errors"></div>
				                                	</div>
				                                	
				                                	<div class="form-group has-feedback">
				                                    	<div class="input-group">
				                                    		<span class="input-group-addon" title="* City / Town">City / Town</span>
				                                    		<input class="form-control" 
				                                    			type="text" name="addressCity"  
				                                    			placeholder="City/Town" 
				                                    			maxlength="50" 
				                                    			pattern="^(?:\w|\s|\.|#)*\w+$"
				                                    			data-error="Please fill out this field."
				                                    			value=""   
				                                    			required />
				                                    	</div>
				                                    	<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
				    									<div class="help-block with-errors"></div>
				                                	</div>
				                                	
				                                	<div class="form-group has-feedback">
				                                    	<div class="input-group">
				                                    		<span class="input-group-addon" title="* Company">Company</span>
				                                    			<input class="form-control" 
						                                    		type="text" 
						                                    		name="companyName"  
						                                    		placeholder="Company" 
						                                    		maxlength="160" 
						                                    		value=""   
						                                    		data-error="Please fill out this field."
						                                    		required />
						                            	</div>
						                            	<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
				    									<div class="help-block with-errors"></div>
				                                    </div>
				                                    
				                                    <div class="form-group has-feedback">
					                                	<div class="input-group">
					                                    		<span class="input-group-addon" title="* Mobile No">Mobile No. +63</span>
					                                    		<input class="form-control" 
					                                    			type="text" name="mobileNo"  
					                                    			placeholder="9191234567" 
					                                    			maxlength="15" 
					                                    			pattern="^[1-9]{10}$" 
					                                    			value="" 
					                                    			data-error="Must contain valid 10-digit mobile"
					                                    			required />
					                                	</div>
					                                	<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
				    									<div class="help-block with-errors"></div>
				                                	</div>
				                                	
				                                	<div class="form-group has-feedback">
				                                		<label class="control-label">How did you find out about HMR Auction Services?</label>
														<div class="input-group">
															<select class="form-control" 
																id="discovered" 
																name="discovered" 
																pattern="^(?:\w|\s|\.|#)*\w+$"
				                                    			data-error="Please fill out this field."
																required>
																<option value="" disabled selected>-- Choose one --</option>
																<option>Search Engine</option>
																<option>Print Advertisement</option>
																<option>Flyers / Posters</option>
																<option>Referral</option>
																<option>Others</option>
															</select>
														</div>
														<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
				    									<div class="help-block with-errors"></div>
													</div>
													
													<div class="form-group has-feedback"> <!-- Message field -->
														<label class="control-label " for="message">Message</label>
														<div class="input-group">
															<textarea class="form-control" cols="40" id="message" name="message" rows="10" required></textarea>
														</div>
														<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
				    									<div class="help-block with-errors"></div>
													</div>
				                                	
				                                	
				                                	<div class="form-group col-md-6">
					                                	<button type="submit" class="btn btn-primary">Send</button>
					                                </div>
					  								 
					                                <input type="hidden" name="action" id="action" value="contactus-send"/>
					                            	<input type="hidden" name="manager" id="manager" value="pages"/>
				                                	
				                                </div>
			                            	</div>
			                            </form>
									
									</div>
									
								</div>
							</div>
						</section>
					</div>
				</div>
			</div>
	</section>
	<div class="clearfix top50"></div>

</div>

<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/footer-meta.jsp"></jsp:include>


<script>
function showAlert(msgInfo, msgbgcol) {
	var priority = null;
	if(msgbgcol=="red") {
		priority = "error";
	}else if(msgbgcol=="green") {
		priority = "success";
	}else {
		priority = "warning";
	}

	$(document).trigger("add-alerts", [
		{
			"message": msgInfo,
	         "priority": priority
	    }
	]);
	
	
}

function stopEnterSubmitting(e) {
    if (e.keyCode == 13) {
        var src = e.srcElement || e.target;
        if (src.tagName.toLowerCase() != "textarea") {
            if (e.preventDefault) {
                e.preventDefault();
            } else {
                e.returnValue = false;
            }
        }
    }
}	

$(document).ready(function(){
	<%if(msgInfo!=null){%>
	var msgInfo = "<%=msgInfo%>";
	var msgbgcol = "<%=msgbgcol%>";
	showAlert(msgInfo, msgbgcol);
	<%}%>
	
});


</script>
</body>
</html>
