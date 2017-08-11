<!DOCTYPE html>
<%@page import="hmr.com.bean.UserAddress"%>
<%@ page import="hmr.com.bean.User"
		 import="java.util.List"
		 import="java.math.BigDecimal"
%>
<%
User u = request.getAttribute("user") != null ? (User) request.getAttribute("user")	: null;
UserAddress ua = request.getAttribute("address") != null ? (UserAddress) request.getAttribute("address")	: null;
Integer currentYear = request.getAttribute("currentYear") != null ? (Integer) request.getAttribute("currentYear") : 2017;
Integer dobyear = request.getAttribute("dobyear") != null ? (Integer) request.getAttribute("dobyear") : 1;
Integer dobmonth = request.getAttribute("dobmonth") != null ? (Integer) request.getAttribute("dobmonth") : 1;
Integer dobday = request.getAttribute("dobday") != null ? (Integer) request.getAttribute("dobday") : 1;

String msgInfo = request.getAttribute("msgInfo") != null ? (String) request.getAttribute("msgInfo") : null;
String msgbgcol = request.getAttribute("msgbgcol") != null ? (String) request.getAttribute("msgbgcol") : "";

%>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <jsp:include page="includes/header-meta.jsp"></jsp:include>
    </head>
    <body data-is-mobile="" id="c" >
    
    <input type="hidden" id="base_url" value="">
        <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
        
<jsp:include page="includes/header.jsp"></jsp:include>

<div id="ms--main--body">
	<section id="register-account-section" class="page-section">
	<div class="container">
		<div class="row">
			<div data-alerts="alerts" data-titles='{"warning": "<em>Warning!</em>", "error": "<em>Error!</em>"}' data-ids="myid" data-fade="3000"></div>
			<div class="col-md-4 col-lg-3">
				<jsp:include page="includes/side-widget.jsp"></jsp:include>
			
			</div>
			
			<div class="col-md-8 col-lg-8">
			
				<div role="tabpanel">
					<!-- Nav tabs -->
						<ul class="nav nav-tabs nav-tabs-2" role="tablist">
							<li role="presentation" class="active">
								<a href="#profile-tab" aria-controls="profile-tab" role="tab" data-toggle="tab">Edit Profile</a>
							</li>
							<li role="presentation" class="">
								<a href="#password-tab" aria-controls="password-tab" role="tab" data-toggle="tab">Change Password</a>
							</li>
					</ul>
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="profile-tab">
				<div class="customer-register-wrap">
					<div class="clearfix top20"></div>

						<form action="bid" 
							class="form-login" 
							name="frm" 
							data-toggle="validator" 
							role="form"
							method="post"
							onkeypress="stopEnterSubmitting(window.event)">
                            <div class="row">
                            
                            	<div class="col-md-12">
                            		<h4>Personal Details</h4>
                            	</div>
                            
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
                                    			value="<%=u.getFirst_name()%>" 
                                    			required />
                                    	</div>
                                    	<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
    									<div class="help-block with-errors"></div>
                                	</div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group has-feedback">
                                    	<div class="input-group">
                                    		<span class="input-group-addon" title="* First name">Last name</span>
                                    		<input class="form-control" 
                                    			type="text" name="lastName" 
                                    			placeholder="Last Name" 
                                    			maxlength="50" 
                                    			pattern="^(?:[A-z]+\.{0,1}\s{0,1})+[A-z]+$"
                                    			data-error="Must contain only word(s) not ending in space"
                                    			value="<%=u.getLast_name()%>"
                                    			required />
                                    	</div>
                                    	<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
    									<div class="help-block with-errors"></div>
                                	</div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group has-feedback">
                                    	<div class="input-group">
                                    		<span class="input-group-addon" title="* Email">Email</span>
                                    		<input class="form-control" 
                                    			type="email" name="userEmail" 
                                    			placeholder="Email@domain.com" 
                                    			maxlength="50" 
                                    			value="<%=u.getEmail_address()%>"
                                    			data-error="Must contain valid email address"
                                    			readonly
                                    			required />
                                		</div>
                                		<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
    									<div class="help-block with-errors"></div>
                                	</div>
                                </div>
                                <div class="col-md-12">
                                	<div class="form-group has-feedback">
	                                	<div class="input-group">
	                                    		<span class="input-group-addon" title="* Mobile No">Mobile No. +63</span>
	                                    		<input class="form-control" 
	                                    			type="text" name="mobileNo"  
	                                    			placeholder="9191234567" 
	                                    			maxlength="15" 
	                                    			pattern="^[1-9]{10}$" 
	                                    			value="<%=u.getMobile_no_1()%>" 
	                                    			data-error="Must contain valid 10-digit mobile"
	                                    			required />
	                                	</div>
	                                	<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
    									<div class="help-block with-errors"></div>
                                	</div>
                                </div>
                                
                                <div class="col-md-12">
										<div class="form-group">
											<div class="input-group">
                                    			<span class="input-group-addon" title="* Gender">Gender</span>
													<select class="form-control" id="gender" name="gender">
														<option value="13" <% if(u.getGender()==13){ %>selected<% } %>>I'm Male</option>
														<option value="14" <% if(u.getGender()==14){ %>selected<% } %>>I'm Female</option>
													</select>
											</div>
										</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<div class="input-group">
    										<span class="input-group-addon" title="* Birth Date" id="dob">Birth Date</span>
    										<select class="form-control" id="dobmonth" name="dobmonth">
												<option value="1" <% if( dobmonth == 0 ){ %>selected<% } %>>January</option>
												<option value="2" <% if( dobmonth == 1 ){ %>selected<% } %>>February</option>
												<option value="3" <% if( dobmonth == 2 ){ %>selected<% } %>>March</option>
												<option value="4" <% if( dobmonth == 3 ){ %>selected<% } %>>April</option>
												<option value="5" <% if( dobmonth == 4 ){ %>selected<% } %>>May</option>
												<option value="6" <% if( dobmonth == 5 ){ %>selected<% } %>>June</option>
												<option value="7" <% if( dobmonth == 6 ){ %>selected<% } %>>July</option>
												<option value="8" <% if( dobmonth == 7 ){ %>selected<% } %>>August</option>
												<option value="9" <% if( dobmonth == 8 ){ %>selected<% } %>>September</option>
												<option value="10" <% if( dobmonth == 9 ){ %>selected<% } %>>October</option>
												<option value="11" <% if( dobmonth == 10 ){ %>selected<% } %>>November</option>
												<option value="12" <% if( dobmonth == 11 ){ %>selected<% } %>>December</option>
											</select>
											<span class="input-group-addon" style="width:0px; padding-left:0px; padding-right:0px; border:none;"></span>
											<select class="form-control" id="dobday" name="dobday">
												<option value="" disabled selected>Day?</option>
												
												<% for(int i = 1; i < 32 ; i++) {  %>
												<option value="<%=i %>" <% if( dobday == i ){ %>selected<% } %>><%=i %></option>
												<% } %>
											</select>
											<span class="input-group-addon" style="width:0px; padding-left:0px; padding-right:0px; border:none;"></span>
											<select class="form-control" id="dobyear" name="dobyear">
												<option value="" disabled>---------------------</option>
												<% for(int i = (currentYear-110); i < currentYear ; i++) {  %>
												<option value="<%=i %>" <% if( dobyear == i ){ %>selected<% } %>><%=i %></option>
												<% } %>
											</select>
    									</div>
									</div>
								</div>
								
								<div class="col-md-12">
                                    <div class="form-group has-feedback">
                                    	<div class="input-group">
                                    		<span class="input-group-addon" title="* Company">Company</span>
                                    			<input class="form-control" 
		                                    		type="text" 
		                                    		name="companyName"  
		                                    		placeholder="Company" 
		                                    		maxlength="160" 
		                                    		value="<%=u.getCompany()%>"   
		                                    		data-error="Please fill out this field."
		                                    		required />
		                            	</div>
		                            	<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
    									<div class="help-block with-errors"></div>
                                    </div>
                                </div>
								
								<div class="clearfix top50"></div>
								<div class="clearfix top50"></div>
								
								<div class="col-md-12">
									<h4>Address</h4>
								</div>
							
								<div class="col-md-12">
                                    <div class="form-group has-feedback">
                                    	<div class="input-group">
                                    		<span class="input-group-addon" title="* Street / Number">Street / Number</span>
                                    		<input class="form-control" 
                                    			type="text" 
                                    			name="addressStreetNo"  
                                    			placeholder="Street/Number" 
                                    			maxlength="50" 
                                    			pattern="^(?:\w|\s|\.|#)*\w+$"
                                    			value="<%=ua.getAddress_line_1()%>"  
                                    			data-error="Please fill out this field."
                                    			required />
                                    	</div>
                                    	<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
    									<div class="help-block with-errors"></div>
                                	</div>
                                </div>
                                
                                <div class="col-md-12">
                                    <div class="form-group has-feedback">
                                    	<div class="input-group">
                                    		<span class="input-group-addon" title="* Barangay">Barangay</span>
                                    		<input class="form-control" 
                                    			type="text" name="addressBaranggay"  
                                    			placeholder="Barangay" 
                                    			maxlength="50" 
                                    			pattern="^(?:\w|\s|\.|#)*\w+$"
                                    			value="<%=ua.getBaranggay() %>"   
                                    			data-error="Please fill out this field."
                                    			required />
                                    	</div>
                                    	<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
    									<div class="help-block with-errors"></div>
                                	</div>
                                </div>
                                
                                <div class="col-md-12">
                                    <div class="form-group has-feedback">
                                    	<div class="input-group">
                                    		<span class="input-group-addon" title="* City / Town">City / Town</span>
                                    		<input class="form-control" 
                                    			type="text" name="addressCity"  
                                    			placeholder="City/Town" 
                                    			maxlength="50" 
                                    			pattern="^(?:\w|\s|\.|#)*\w+$"
                                    			data-error="Please fill out this field."
                                    			value="<%=ua.getCity()%>"   
                                    			required />
                                    	</div>
                                    	<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
    									<div class="help-block with-errors"></div>
                                	</div>
                                </div>
                                
                                <div class="col-md-12">
                                    <div class="form-group has-feedback">
                                    	<div class="input-group">
                                    		<span class="input-group-addon" title="* Province">Province</span>
                                    		<input class="form-control" type="text" 
                                    		name="addressProvince"  
                                    		placeholder="Province" 
                                    		maxlength="50" 
                                    		pattern="^(?:\w|\s|\.|#)*\w+$"
                                    		data-error="Please fill out this field."
                                    		value="<%=ua.getProvince()%>"   
                                    		required />
                                    	</div>
                                    	<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
    									<div class="help-block with-errors"></div>
                                	</div>
                                </div>
                                
                                <div class="col-md-12">
										<div class="form-group">
											<div class="input-group">
                                    			<span class="input-group-addon" title="* Country">Country</span>
												<select class="form-control" id="addressCountry" name="addressCountry">
													<option selected><%=ua.getCountry() %></option>
													<option>United States</option>
													<option value="" disabled>---------------------</option>
													<option>Afghanistan</option>
													<option>Albania</option>
													<option>Algeria</option>
													<option>Andorra</option>
													<option>Angola</option>
													<option>Antigua &amp; Deps</option>
													<option>Argentina</option>
													<option>Armenia</option>
													<option>Australia</option>
													<option>Austria</option>
													<option>Azerbaijan</option>
													<option>Bahamas</option>
													<option>Bahrain</option>
													<option>Bangladesh</option>
													<option>Barbados</option>
													<option>Belarus</option>
													<option>Belgium</option>
													<option>Belize</option>
													<option>Benin</option>
													<option>Bhutan</option>
													<option>Bolivia</option>
													<option>Bosnia Herzegovina</option>
													<option>Botswana</option>
													<option>Brazil</option>
													<option>Brunei</option>
													<option>Bulgaria</option>
													<option>Burkina</option>
													<option>Burundi</option>
													<option>Cambodia</option>
													<option>Cameroon</option>
													<option>Canada</option>
													<option>Cape Verde</option>
													<option>Central African Rep</option>
													<option>Chad</option>
													<option>Chile</option>
													<option>China</option>
													<option>Colombia</option>
													<option>Comoros</option>
													<option>Congo</option>
													<option>Congo {Democratic Rep}</option>
													<option>Costa Rica</option>
													<option>Croatia</option>
													<option>Cuba</option>
													<option>Cyprus</option>
													<option>Czech Republic</option>
													<option>Denmark</option>
													<option>Djibouti</option>
													<option>Dominica</option>
													<option>Dominican Republic</option>
													<option>East Timor</option>
													<option>Ecuador</option>
													<option>Egypt</option>
													<option>El Salvador</option>
													<option>Equatorial Guinea</option>
													<option>Eritrea</option>
													<option>Estonia</option>
													<option>Ethiopia</option>
													<option>Fiji</option>
													<option>Finland</option>
													<option>France</option>
													<option>Gabon</option>
													<option>Gambia</option>
													<option>Georgia</option>
													<option>Germany</option>
													<option>Ghana</option>
													<option>Greece</option>
													<option>Grenada</option>
													<option>Guatemala</option>
													<option>Guinea</option>
													<option>Guinea-Bissau</option>
													<option>Guyana</option>
													<option>Haiti</option>
													<option>Honduras</option>
													<option>Hungary</option>
													<option>Iceland</option>
													<option>India</option>
													<option>Indonesia</option>
													<option>Iran</option>
													<option>Iraq</option>
													<option>Ireland {Republic}</option>
													<option>Israel</option>
													<option>Italy</option>
													<option>Ivory Coast</option>
													<option>Jamaica</option>
													<option>Japan</option>
													<option>Jordan</option>
													<option>Kazakhstan</option>
													<option>Kenya</option>
													<option>Kiribati</option>
													<option>Korea North</option>
													<option>Korea South</option>
													<option>Kosovo</option>
													<option>Kuwait</option>
													<option>Kyrgyzstan</option>
													<option>Laos</option>
													<option>Latvia</option>
													<option>Lebanon</option>
													<option>Lesotho</option>
													<option>Liberia</option>
													<option>Libya</option>
													<option>Liechtenstein</option>
													<option>Lithuania</option>
													<option>Luxembourg</option>
													<option>Macedonia</option>
													<option>Madagascar</option>
													<option>Malawi</option>
													<option>Malaysia</option>
													<option>Maldives</option>
													<option>Mali</option>
													<option>Malta</option>
													<option>Marshall Islands</option>
													<option>Mauritania</option>
													<option>Mauritius</option>
													<option>Mexico</option>
													<option>Micronesia</option>
													<option>Moldova</option>
													<option>Monaco</option>
													<option>Mongolia</option>
													<option>Montenegro</option>
													<option>Morocco</option>
													<option>Mozambique</option>
													<option>Myanmar, {Burma}</option>
													<option>Namibia</option>
													<option>Nauru</option>
													<option>Nepal</option>
													<option>Netherlands</option>
													<option>New Zealand</option>
													<option>Nicaragua</option>
													<option>Niger</option>
													<option>Nigeria</option>
													<option>Norway</option>
													<option>Oman</option>
													<option>Pakistan</option>
													<option>Palau</option>
													<option>Panama</option>
													<option>Papua New Guinea</option>
													<option>Paraguay</option>
													<option>Peru</option>
													<option>Philippines</option>
													<option>Poland</option>
													<option>Portugal</option>
													<option>Qatar</option>
													<option>Romania</option>
													<option>Russian Federation</option>
													<option>Rwanda</option>
													<option>St Kitts &amp; Nevis</option>
													<option>St Lucia</option>
													<option>Saint Vincent &amp; the Grenadines</option>
													<option>Samoa</option>
													<option>San Marino</option>
													<option>Sao Tome &amp; Principe</option>
													<option>Saudi Arabia</option>
													<option>Senegal</option>
													<option>Serbia</option>
													<option>Seychelles</option>
													<option>Sierra Leone</option>
													<option>Singapore</option>
													<option>Slovakia</option>
													<option>Slovenia</option>
													<option>Solomon Islands</option>
													<option>Somalia</option>
													<option>South Africa</option>
													<option>South Sudan</option>
													<option>Spain</option>
													<option>Sri Lanka</option>
													<option>Sudan</option>
													<option>Suriname</option>
													<option>Swaziland</option>
													<option>Sweden</option>
													<option>Switzerland</option>
													<option>Syria</option>
													<option>Taiwan</option>
													<option>Tajikistan</option>
													<option>Tanzania</option>
													<option>Thailand</option>
													<option>Togo</option>
													<option>Tonga</option>
													<option>Trinidad &amp; Tobago</option>
													<option>Tunisia</option>
													<option>Turkey</option>
													<option>Turkmenistan</option>
													<option>Tuvalu</option>
													<option>Uganda</option>
													<option>Ukraine</option>
													<option>United Arab Emirates</option>
													<option>United Kingdom</option>
													<option>United States</option>
													<option>Uruguay</option>
													<option>Uzbekistan</option>
													<option>Vanuatu</option>
													<option>Vatican City</option>
													<option>Venezuela</option>
													<option>Vietnam</option>
													<option>Yemen</option>
													<option>Zambia</option>
													<option>Zimbabwe</option>
												</select>
											</div>
										</div>
								</div>
                                
                                <div class="col-md-12">
                                    <div class="form-group has-feedback">
                                    	<div class="input-group">
                                    		<span class="input-group-addon" title="* ZipCode">ZipCode</span>
                                    		<input class="form-control" 
                                    			type="text" 
                                    			name="addressZipCode"  
                                    			placeholder="9999" 
                                    			maxlength="50" 
                                    			pattern="^\d+$"
                                    			data-error="Please fill out this field."
                                    			value="<%if(ua.getPostal_code()!=null){ %><%=ua.getPostal_code()%><%} %>"   
                                    			required />
                                    	</div>
                                    	<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
    									<div class="help-block with-errors"></div>
                                	</div>
                                </div>
                                
                                <div class="clearfix top50"></div>
                                <div class="clearfix top50"></div>
                                
                                <div class="form-group col-md-6">
                                	<button type="submit" class="btn btn-primary">Save</button>
                                </div>
                                
                            </div>
                            <input type="hidden" name="action" id="action" value="update-profile"/>
                            <input type="hidden" name="manager" id="manager" value="user-manager"/>
                            <input type="hidden" name="userId" id="userId" value="<%=u.getEmail_address() %>"/>
   							<input type="hidden" name="user-id" id="user-id" value="<%=u.getId()%>"/>
                        </form>				</div>
			
						</div><!-- profile-tab  -->
							
						
						<div role="tabpanel" class="tab-pane" id="password-tab">
							<form action="bid" 
							class="form-login" 
							name="frm2" 
							data-toggle="validator" 
							role="form"
							method="post"
							onkeypress="stopEnterSubmitting(window.event)" >
							
								<div class="col-md-12">
									<div class="form-group has-feedback">
										<div class="input-group">
											<span class="input-group-addon" title="* Current Password">Current Password</span>
											<input type="password" 
	        									data-minlength="6" 
	        									class="form-control" 
	        									id="currentPassword" 
	        									name="currentPassword"
	        									placeholder="Password" 
	        									autocomplete="off"
	        									data-error="Minimum of 6 characters"
	        									required />
										</div>
										<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
	        							<div class="help-block with-errors"></div>
									</div>
								</div>
								
								<div class="clearfix top50"></div>
							
								<div class="col-md-12">
									<div class="form-group">
	    								<div class="form-inline row">
	    									<div class="col-md-6">
		      									<div class="form-group has-feedback">
		      										<div class="input-group">
		                                    			<span class="input-group-addon" title="* New Password">New Password</span>
		        											<input type="password" 
		        												data-minlength="6" 
		        												class="form-control" 
		        												id="inputPassword" 
		        												name="inputPassword"
		        												placeholder="Password" 
		        												autocomplete="off"
		        												data-error="Minimum of 6 characters"
		        												required />
		        									</div>
		        									<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
		        									<div class="help-block with-errors"></div>
		      									</div>
	      									</div>
	      									<div class="col-md-6">
		      									<div class="form-group has-feedback">
		      										<div class="input-group">
			                                    		<span class="input-group-addon" title="* Confirm New Password">Confirm New Password</span>
			        									<input type="password" 
			        										class="form-control" 
			        										id="inputPasswordConfirm" 
			        										name="inputPasswordConfirm"
			        										data-match="#inputPassword" 
			        										autocomplete="off"
			        										data-match-error="Whoops, these don't match" 
			        										placeholder="Confirm" required>
		        									</div>
		        									<span class="glyphicon form-control-feedback" aria-hidden="true"></span>
		        									<div class="help-block with-errors"></div>
		      									</div>
	      									</div>
	    								</div>
	  								</div>
  								</div>
  								
  								<div class="clearfix top50"></div>
  								<div class="clearfix top50"></div>
  								
  								<div class="form-group col-md-6">
                                	<button type="submit" class="btn btn-primary">Save</button>
                                </div>
  								
                                <input type="hidden" name="action" id="action" value="update-password"/>
                            	<input type="hidden" name="manager" id="manager" value="user-manager"/>
                            	<input type="hidden" name="userId" id="userId" value="<%=u.getEmail_address() %>"/>
   								<input type="hidden" name="user-id" id="user-id" value="<%=u.getId()%>"/>	
							</form>
						</div><!-- class="tab-password"  -->
						
						
					</div><!-- class="tab-content"  -->
				</div><!-- role="tabpanel" -->
			</div>
		</div>
	</div>

	<div class="clearfix top100"></div>
</section></div>

<jsp:include page="includes/footer.jsp"></jsp:include>
<jsp:include page="includes/footer-meta.jsp"></jsp:include>


<!-- JS Local -->
<script type="text/javascript">


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
<!-- /JS Local -->



</body>
</html>