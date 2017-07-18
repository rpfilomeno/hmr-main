package hmr.com.manager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bizoncloudone.com.bean.RoleLogin;
import bizoncloudone.com.bean.UserLogin;
import hmr.com.bean.Lov;
import hmr.com.bean.User;
import hmr.com.dao.LovDao;
import hmr.com.dao.UserDao;
import hmr.com.util.EmailUtil;
import hmr.com.util.StringUtil;
import bizoncloudone.com.bean.UserRoleLogin;
import bizoncloudone.com.dao.RequestDao;
import bizoncloudone.com.dao.RoleLoginDao;
import bizoncloudone.com.dao.SnapshotDao;
import bizoncloudone.com.dao.UserLoginDao;
import bizoncloudone.com.dao.UserRoleLoginDao;
import bizoncloudone.com.manager.RunnableEmailManager;
import bizoncloudone.com.util.DBConnection;

public class UserManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	//String emailAdd = null;
	
	public static final DateFormat INPUT_DATE_FMT = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
	
	public UserManager(){}
	/*
	public UserManager(HttpServletRequest req, HttpServletResponse res, String emailAdd){
		this.req = req;
		this.res = res;
	}
	*/
	public UserManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public String doUserManager(){
		String page = null;
		String action = req.getParameter("action")!=null ? req.getParameter("action") : "";
		
		
		System.out.println("Paramerters doUserManager - start");
		System.out.println("action : "+action);
		System.out.println("Paramerters doUserManager - end");
		System.out.println("");
		
		
		if(action.equals("registration")){
			
			
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String userId = req.getParameter("userId");
			BigDecimal mobileNo = new BigDecimal(req.getParameter("mobileNo"));
			Integer gender = Integer.parseInt(req.getParameter("gender"));
			Integer dobmonth = Integer.parseInt(req.getParameter("dobmonth"));
			Integer dobday = Integer.parseInt(req.getParameter("dobday"));
			Integer dobyear = Integer.parseInt(req.getParameter("dobyear"));
			String addressStreetNo = req.getParameter("addressStreetNo");
			String addressBaranggay = req.getParameter("addressBaranggay");
			String addressCity = req.getParameter("addressCity");
			String addressProvince = req.getParameter("addressProvince");
			String addressCountry = req.getParameter("addressCountry");
			String addressZipCode = req.getParameter("addressZipCode");
			String companyName = req.getParameter("companyName");
			String receiveNotification = req.getParameter("receiveNotification");
			
			User u = new User();
			
			if("registration".equals(action)){
				u = getUser(userId);
			}

			if(u==null){
				
				System.out.println("User does not exist and proceed to registration and send email confirmation");
				
				String verification_email_key = StringUtil.generateRandomKey();
				
				int i =  insertUserOnRegistration(firstName, lastName, userId, mobileNo, 
						gender, 
						dobmonth,
						dobday,
						dobyear,
						addressStreetNo,
						addressBaranggay,
						addressCity,
						addressProvince,
						addressCountry,
						addressZipCode,
						companyName,
						verification_email_key);
				
				if(i==0){	
					req.setAttribute("msgbgcol", "red");
					req.setAttribute("msgInfo", "Registration failed.<>You may call (02)548-6962 / 0917 548 3603 or Email us at auction@hmrphils.com.");
				}else{	
					req.setAttribute("msgbgcol", "green");
					req.setAttribute("msgInfo", "Registration successful.<br>Please see "+userId+" to verify email.");

					String ADMIN_EMAIL_ADD_CC = (String)req.getSession().getAttribute("ADMIN_EMAIL_ADD_CC");
					String PROTOCOL = (String)req.getSession().getAttribute("PROTOCOL");
					String HOST_NAME = (String)req.getSession().getAttribute("HOST_NAME");
					
					String login_link = PROTOCOL+"://"+HOST_NAME+"/HMR/bid?mngr=get&a=verifyEmail&uid="+userId+"&vek="+verification_email_key;
					
					//String login_link = "http://localhost:8080/HMR/bid?mngr=get&a=verifyEmail&uid="+userId+"&vek="+verification_email_key;

					//String SERVER_DIRECTORY_LOCAL = "C:\\Work\\workspace\\HMR\\WebContent\\hmr\\images\\email\\";
					String SERVER_DIRECTORY = (String)req.getSession().getAttribute("SERVER_DIRECTORY");
					//String SERVER_DIRECTORY_ONLINE = "";					
					EmailUtil.setSERVER_DIRECTORY(SERVER_DIRECTORY);
					//EmailUtil.sendVerifyEmailUser(userId, ADMIN_EMAIL_ADD_CC, firstName, lastName, login_link);
					
					RunnableEmailManager REM = new RunnableEmailManager("HMR Auctions : Registration Successful", SERVER_DIRECTORY, userId, ADMIN_EMAIL_ADD_CC, firstName, lastName, login_link);
					
					//System.out.println("RunnableEmailManager ");
					REM.start();
					
					
				}
				
				page ="registration.jsp";
				
			}else{
				
				System.out.println("User already exist.");

				req.setAttribute("firstName", firstName);
				req.setAttribute("lastName", lastName);
				req.setAttribute("userId", userId);
				req.setAttribute("mobileNo", mobileNo);
				
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "Registration Failed.<br>Email already exist.");
				
				page ="registration.jsp";
				
			}
		}else if("verifyEmail".equals(action)){
			
			String userId = req.getParameter("userId")!=null ? req.getParameter("userId") : "";
			String vek = req.getParameter("vek")!=null ? req.getParameter("vek") : "";

			User u = getUserRegistration(userId, vek);
			
			System.out.println("vek "+vek);
			
			
			if(vek!=null && !vek.equals("")){
				
				if(u!=null){
					
					System.out.println("u.getDate_registration() "+u.getDate_registration());
					
					if(u.getDate_registration()!=null){
						

						req.setAttribute("firstName", u.getFirst_name());
						req.setAttribute("lastName",  u.getLast_name());
						req.setAttribute("userId", u.getEmail_address());
						req.setAttribute("mobileNo", u.getMobile_no_1());
						req.setAttribute("user-id", u.getId());
						
						req.setAttribute("msgbgcol", "red");
						req.setAttribute("msgInfo", "Email already registered.");
						
						page ="login.jsp";
					}else{
						
						req.setAttribute("firstName", u.getFirst_name());
						req.setAttribute("lastName",  u.getLast_name());
						req.setAttribute("userId", u.getEmail_address());
						req.setAttribute("mobileNo", u.getMobile_no_1());
						req.setAttribute("user-id", u.getId());
						
						req.setAttribute("msgbgcol", "green");
						req.setAttribute("msgInfo", "Activate your account<br>Create new password.<br>Confirm new password.");
						
						page ="registration-password.jsp";
					}
					
				}else{
					
				}

				
			}else{
				page ="index.jsp";
			}
			
			
		}else if("activate".equals(action)){
			
			System.out.println("activate");
			
			String userId = req.getParameter("userId")!=null ? req.getParameter("userId") : "";
			String pw = req.getParameter("pw")!=null ? req.getParameter("pw") : "";
			Integer user_id = req.getParameter("user_id")!=null ? Integer.decode(req.getParameter("user_id")) : 0;
			
			int i = updatePasswordOnActivation(userId, pw, user_id);
			
			if(i > 0){
				
				User u = getUser(userId);
				req.getSession().setAttribute("user-id", u.getId());
				req.getSession().setAttribute("userId", u.getEmail_address());
				req.getSession().setAttribute("firstName", u.getFirst_name());
				req.getSession().setAttribute("lastName", u.getLast_name());
				req.getSession().setAttribute("fullName", u.getFirst_name()+" "+u.getLast_name());
				
				String ADMIN_EMAIL_ADD_CC = req.getSession().getAttribute("ADMIN_EMAIL_ADD_CC")!=null ?(String)req.getSession().getAttribute("ADMIN_EMAIL_ADD_CC"): "dakkiboy17@gmail.com"; 
				String PROTOCOL = (String)req.getSession().getAttribute("PROTOCOL");
				String HOST_NAME = (String)req.getSession().getAttribute("HOST_NAME");
				
				String login_link = PROTOCOL+"://"+HOST_NAME+"/HMR/bid?mngr=get&a=login&uid="+userId;
				
				
				//String login_link = "http://localhost:8080/HMR/bid?mngr=get&a=login&uid="+userId;

				
				
				//String login_link = "http://localhost:8080/HMR/bid?mngr=get&a=login&uid="+userId;
				
				//String SERVER_DIRECTORY_LOCAL = "C:\\Work\\workspace\\HMR\\WebContent\\hmr\\images\\email\\";
				String SERVER_DIRECTORY = (String)req.getSession().getAttribute("SERVER_DIRECTORY");
				//String SERVER_DIRECTORY_ONLINE = "";					
				EmailUtil.setSERVER_DIRECTORY(SERVER_DIRECTORY);
				EmailUtil.sendRegisteredSuccessfulEmailUser(userId, ADMIN_EMAIL_ADD_CC, u.getFirst_name(), u.getLast_name(), login_link);
				
				
				RunnableEmailManager REM = new RunnableEmailManager("HMR Auctions : Activation", SERVER_DIRECTORY, userId, ADMIN_EMAIL_ADD_CC, u.getFirst_name(), u.getLast_name(), login_link);
				
				//System.out.println("RunnableEmailManager ");
				REM.start();
				
				page ="index.jsp";
			}else{
				page ="index.jsp";
			}
		}else if("userList".equals(action)){
			
			System.out.println("userList");

			List<User> uList = getUserList();
			
			req.setAttribute("userList", uList);

			page ="user-list.jsp";
		
		}else if("createUser".equals(action)){
			
			System.out.println("createUser");

			page ="user-create.jsp";
		
		}else if("saveUser".equals(action)){
			
			System.out.println("saveUser");

			String userId = req.getParameter("userId")!=null ? req.getParameter("userId") : "";
			Integer user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : 0;

			String firstName = req.getParameter("firstName")!=null ? req.getParameter("firstName") : "";
			String lastName = req.getParameter("lastName")!=null ? req.getParameter("lastName") : "";
			String birthDate = req.getParameter("birthDate")!=null ? req.getParameter("birthDate") : "";
			Integer gender = req.getParameter("gender")!=null ? Integer.valueOf(req.getParameter("gender")) : 0;
			String company = req.getParameter("company")!=null ? req.getParameter("company") : "";
			String emailAddress = req.getParameter("emailAddress")!=null ? req.getParameter("emailAddress") : "";
			String passWord = req.getParameter("passWord")!=null ? req.getParameter("passWord") : "";
			Integer userStatus = req.getParameter("userStatus")!=null ? Integer.valueOf(req.getParameter("userStatus")) : 0;
			Integer active = !req.getParameter("active").equals("") ? Integer.valueOf(req.getParameter("active")) : 0;
			Integer role = req.getParameter("role")!=null ? Integer.valueOf(req.getParameter("role")) : 0;
			Integer newsLetter = !req.getParameter("newsLetter").equals("") ? Integer.valueOf(req.getParameter("newsLetter")) : 0;
			String newsLetterRegistrationDate = req.getParameter("newsLetterRegistrationDate")!=null ? req.getParameter("newsLetterRegistrationDate") : "";
			String registrationDate = req.getParameter("registrationDate")!=null ? req.getParameter("registrationDate") : "";
			String passwordChangeDate = req.getParameter("passwordChangeDate")!=null ? req.getParameter("passwordChangeDate") : "";
			BigDecimal mobileNo1 = !req.getParameter("mobileNo1").equals("") ? new BigDecimal(req.getParameter("mobileNo1")) : new BigDecimal(0);
			BigDecimal mobileNo2 = !req.getParameter("mobileNo2").equals("") ? new BigDecimal(req.getParameter("mobileNo2")) : new BigDecimal(0);
			BigDecimal landLineNo = !req.getParameter("landLineNo").equals("") ? new BigDecimal(req.getParameter("landLineNo")) : new BigDecimal(0);
			Integer bidderNo = !req.getParameter("bidderNo").equals("") ? Integer.valueOf(req.getParameter("bidderNo")) : 0;
			Integer reserveBidderNo = !req.getParameter("reserveBidderNo").equals("") ? Integer.valueOf(req.getParameter("reserveBidderNo")) : 0;
			String verificationEmailKey = req.getParameter("verificationEmailKey")!=null ? req.getParameter("verificationEmailKey") : "";
			Integer showChangePasswordNextLogin = !req.getParameter("showChangePasswordNextLogin").equals("") ? Integer.valueOf(req.getParameter("showChangePasswordNextLogin")) : 0;
			

			Date birthDate_d = null; 
			if(!"".equals(birthDate))
			{
			    try {
			    	birthDate_d = INPUT_DATE_FMT.parse(birthDate);
			    } catch (ParseException e) {}
			}
			
			Date newsLetterRegistrationDate_d = null; 
			if(!"".equals(newsLetterRegistrationDate))
			{
			    try {
			    	newsLetterRegistrationDate_d = INPUT_DATE_FMT.parse(newsLetterRegistrationDate);
			    } catch (ParseException e) {}
			}
			
			
			Date registrationDate_d = null; 
			if(!"".equals(registrationDate))
			{
			    try {
			    	registrationDate_d = INPUT_DATE_FMT.parse(registrationDate);
			    } catch (ParseException e) {}
			}
			
			Date passwordChangeDate_d = null; 
			if(!"".equals(passwordChangeDate))
			{
			    try {
			    	passwordChangeDate_d = INPUT_DATE_FMT.parse(passwordChangeDate);
			    } catch (ParseException e) {}
			}

			User u = insertUserOnCreate(
						firstName,
						lastName,
						birthDate_d,
						gender,
						company,
						emailAddress,
						passWord,
						userStatus,
						active,
						role,
						newsLetter,
						newsLetterRegistrationDate_d,
						registrationDate_d,
						passwordChangeDate_d,
						mobileNo1,
						mobileNo2,
						landLineNo,
						bidderNo,
						reserveBidderNo,
						verificationEmailKey,
						showChangePasswordNextLogin,
						user_id
					);
			
			if(u!=null && u.getId()!=null){
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "User created succeessful.");
				
				req.getSession().setAttribute("user", u);
				
				req.setAttribute("user", u);
				
				page ="user.jsp";
				
			}else{
				
				u = new User();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "User created failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("user", u);
				
				req.setAttribute("user", u);
				
				page ="user-list.jsp";
				
			}

			

		
		}else if("viewUser".equals(action)){
			
			System.out.println("viewUser");

			Integer userId_wip = req.getParameter("userId_wip")!=null ? Integer.valueOf(req.getParameter("userId_wip")): 0;
			if(userId_wip > 0){
				
				User u = getUserById(userId_wip);
				
				req.getSession().setAttribute("user", u);
				
				req.setAttribute("user", u);
				
				page ="user.jsp";
				
			}

		}else if("updateUser".equals(action)){
			
			System.out.println("updateUser");

			Integer userId_wip = !req.getParameter("userId_wip").equals("") ? Integer.valueOf(req.getParameter("userId_wip")): 0;
			if(userId_wip > 0){
				
				User u = getUserById(userId_wip);
				
				req.getSession().setAttribute("user", u);
				
				req.setAttribute("user", u);
				
				page ="user-update.jsp";
				
			}

			
		}else if("saveUpdateUser".equals(action)){
			
			System.out.println("saveUser");

			//String userId = req.getParameter("userId")!=null ? req.getParameter("userId") : "";
			Integer user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : 0;
			Integer userId_wip = req.getParameter("userId_wip")!=null ? Integer.valueOf(req.getParameter("userId_wip")) : 0;

			String firstName = req.getParameter("firstName")!=null ? req.getParameter("firstName") : "";
			String lastName = req.getParameter("lastName")!=null ? req.getParameter("lastName") : "";
			String birthDate = req.getParameter("birthDate")!=null ? req.getParameter("birthDate") : "";
			Integer gender = req.getParameter("gender")!=null ? Integer.valueOf(req.getParameter("gender")) : 0;
			String company = req.getParameter("company")!=null ? req.getParameter("company") : "";
			String emailAddress = req.getParameter("emailAddress")!=null ? req.getParameter("emailAddress") : "";
			String passWord = req.getParameter("passWord")!=null ? req.getParameter("passWord") : "";
			Integer userStatus = req.getParameter("userStatus")!=null ? Integer.valueOf(req.getParameter("userStatus")) : 0;
			Integer active = !req.getParameter("active").equals("") ? Integer.valueOf(req.getParameter("active")) : 0;
			Integer role = req.getParameter("role")!=null ? Integer.valueOf(req.getParameter("role")) : 0;
			Integer newsLetter = !req.getParameter("newsLetter").equals("") ? Integer.valueOf(req.getParameter("newsLetter")) : 0;
			String newsLetterRegistrationDate = req.getParameter("newsLetterRegistrationDate")!=null ? req.getParameter("newsLetterRegistrationDate") : "";
			String registrationDate = req.getParameter("registrationDate")!=null ? req.getParameter("registrationDate") : "";
			String passwordChangeDate = req.getParameter("passwordChangeDate")!=null ? req.getParameter("passwordChangeDate") : "";
			BigDecimal mobileNo1 = !req.getParameter("mobileNo1").equals("") ? new BigDecimal(req.getParameter("mobileNo1")) : new BigDecimal(0);
			BigDecimal mobileNo2 = !req.getParameter("mobileNo2").equals("")  && req.getParameter("mobileNo2")!=null ? new BigDecimal(req.getParameter("mobileNo2")) : new BigDecimal(0);
			BigDecimal landLineNo = !req.getParameter("landLineNo").equals("") && req.getParameter("landLineNo")!=null ? new BigDecimal(req.getParameter("landLineNo")) : new BigDecimal(0);
			Integer bidderNo = !req.getParameter("bidderNo").equals("") ? Integer.valueOf(req.getParameter("bidderNo")) : 0;
			Integer reserveBidderNo = !req.getParameter("reserveBidderNo").equals("") ? Integer.valueOf(req.getParameter("reserveBidderNo")) : 0;
			String verificationEmailKey = req.getParameter("verificationEmailKey")!=null ? req.getParameter("verificationEmailKey") : "";
			Integer showChangePasswordNextLogin = !req.getParameter("showChangePasswordNextLogin").equals("") ? Integer.valueOf(req.getParameter("showChangePasswordNextLogin")) : 0;
			

			Date birthDate_d = null; 
			if(!"".equals(birthDate))
			{
			    try {
			    	birthDate_d = INPUT_DATE_FMT.parse(birthDate);
			    } catch (ParseException e) {}
			}
			
			Date newsLetterRegistrationDate_d = null; 
			if(!"".equals(newsLetterRegistrationDate))
			{
			    try {
			    	newsLetterRegistrationDate_d = INPUT_DATE_FMT.parse(newsLetterRegistrationDate);
			    } catch (ParseException e) {}
			}
			
			
			Date registrationDate_d = null; 
			if(!"".equals(registrationDate))
			{
			    try {
			    	registrationDate_d = INPUT_DATE_FMT.parse(registrationDate);
			    } catch (ParseException e) {}
			}
			
			Date passwordChangeDate_d = null; 
			if(!"".equals(passwordChangeDate))
			{
			    try {
			    	passwordChangeDate_d = INPUT_DATE_FMT.parse(passwordChangeDate);
			    } catch (ParseException e) {}
			}
			
			
			
			User u = updateUserOnUpdate(
						firstName,
						lastName,
						birthDate_d,
						gender,
						company,
						emailAddress,
						passWord,
						userStatus,
						active,
						role,
						newsLetter,
						newsLetterRegistrationDate_d,
						registrationDate_d,
						passwordChangeDate_d,
						mobileNo1,
						mobileNo2,
						landLineNo,
						bidderNo,
						reserveBidderNo,
						verificationEmailKey,
						showChangePasswordNextLogin,
						user_id,
						userId_wip
					);
			

			
			
			if(u!=null && u.getId()!=null){
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "User updated succeessful.");
				
				req.getSession().setAttribute("user", u);
				
				req.setAttribute("user", u);
				
				page ="user.jsp";
				
			}else{
				
				u = new User();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "User update failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("user", u);
				
				req.setAttribute("user", u);
				
				page ="user-list.jsp";
				
			}
			
			
		}
		
		
		
		
		
		
	
		
		System.out.println("Paramerters doUserManager - page : "+page);
		
		return page;
		
	}
	

	
	
	public User getUser(String userId){
		
		User u = new User();

		UserDao ud = new UserDao();

		u = ud.getUser(userId);
		
		return u;
		
	}	
	
	public User getUserById(Integer id){
		
		User u = new User();

		UserDao ud = new UserDao();

		u = ud.getUserById(id);
		
		return u;
		
	}	
	
	
	public int insertUserOnRegistration(String firstName, String lastName, String userId, BigDecimal mobileNo, String verification_email_key){
		
		int i = 0;
		
		UserDao ud = new UserDao();

		i = ud.insertUserOnRegistration(firstName, lastName, userId, mobileNo, verification_email_key);
		
		return i;
		
	}
	
public int insertUserOnRegistration(String firstName, String lastName, String userId, BigDecimal mobileNo, 
		Integer gender, 
		Integer dobmonth,
		Integer dobday,
		Integer dobyear,
		String addressStreetNo,
		String addressBaranggay,
		String addressCity,
		String addressProvince,
		String addressCountry,
		String addressZipCode,
		String companyName,
		String verification_email_key){
		
		int i = 0;
		
		UserDao ud = new UserDao();

		i = ud.insertUserOnRegistration(
				firstName, 
				lastName, 
				userId, 
				mobileNo, 
				gender, 
				dobmonth,
				dobday,
				dobyear,
				addressStreetNo,
				addressBaranggay,
				addressCity,
				addressProvince,
				addressCountry,
				addressZipCode,
				companyName,
				verification_email_key);
		
		return i;
		
	}
	
	public User insertUserOnCreate(

			
				String firstName,
				String lastName,
				Date birthDate,
				Integer gender,
				String company,
				String emailAddress,
				String passWord,
				Integer userStatus,
				Integer active,
				Integer role,
				Integer newsLetter,
				Date newsLetterRegistrationDate,
				Date registrationDate,
				Date passwordChangeDate,
				BigDecimal mobileNo1,
				BigDecimal mobileNo2,
				BigDecimal landLineNo,
				Integer bidderNo,
				Integer reserveBidderNo,
				String verificationEmailKey,
				Integer showChangePasswordNextLogin,
				Integer user_id
			
			){
		
		User u = null;
		
		UserDao ud = new UserDao();

		u = ud.insertUserOnCreate(
				
				
					firstName,
					lastName,
					birthDate,
					gender,
					company,
					emailAddress,
					passWord,
					userStatus,
					active,
					role,
					newsLetter,
					newsLetterRegistrationDate,
					registrationDate,
					passwordChangeDate,
					mobileNo1,
					mobileNo2,
					landLineNo,
					bidderNo,
					reserveBidderNo,
					verificationEmailKey,
					showChangePasswordNextLogin,
					user_id
				
				
				
				);
		
		return u;
		
	}
	
	
	public User updateUserOnUpdate(
	
				
				String firstName,
				String lastName,
				Date birthDate,
				Integer gender,
				String company,
				String emailAddress,
				String passWord,
				Integer userStatus,
				Integer active,
				Integer role,
				Integer newsLetter,
				Date newsLetterRegistrationDate,
				Date registrationDate,
				Date passwordChangeDate,
				BigDecimal mobileNo1,
				BigDecimal mobileNo2,
				BigDecimal landLineNo,
				Integer bidderNo,
				Integer reserveBidderNo,
				String verificationEmailKey,
				Integer showChangePasswordNextLogin,
				Integer user_id,
				Integer userId_wip
			
			){
		
		User u = null;
		
		UserDao ud = new UserDao();
	
		u = ud.updateUserOnUpdate(
				
				
					firstName,
					lastName,
					birthDate,
					gender,
					company,
					emailAddress,
					passWord,
					userStatus,
					active,
					role,
					newsLetter,
					newsLetterRegistrationDate,
					registrationDate,
					passwordChangeDate,
					mobileNo1,
					mobileNo2,
					landLineNo,
					bidderNo,
					reserveBidderNo,
					verificationEmailKey,
					showChangePasswordNextLogin,
					user_id,
					userId_wip
				
				
				);
		
		return u;
		
	}
	
	
	public User getUserRegistration(String userId, String vek){
		
		User u = new User();

		UserDao ud = new UserDao();

		u = ud.getUserRegistration(userId, vek);
		
		return u;
		
	}	
	
	public int updatePasswordOnActivation(String userId, String pw, Integer user_id){
		
		int i = 0;
		
		UserDao ud = new UserDao();

		i = ud.updatePasswordOnActivation(userId, pw, user_id);
		
		return i;
		
	}
	
	public List<User> getUserList(){
		
		List<User> uList = new ArrayList<User>();

		UserDao ud = new UserDao();

		uList = ud.getUserList();
		
		return uList;
		
	}
	
	
	public List<User> userListByRole(Integer role){
		
		List<User> uList = new ArrayList<User>();

		UserDao ud = new UserDao();

		uList = ud.getUserList();
		
		return uList;
		
	}
	
	public HashMap<Integer,User> getUserHMByRole(Integer role) throws SQLException{

		UserDao ud = new UserDao();

		HashMap<Integer,User> uHM = ud.getUserHMByRole(role);
		
		return uHM;
		
	}
	
	public List<User> getUserListByRole(Integer role) throws SQLException{

		UserDao ud = new UserDao();

		List<User> uList =ud.getUserListByRole(role);
		
		return uList;
		
	}
	
	
	/*
	public UserLogin getUserLogin(String emailAdd){
		
		String passWord = req.getParameter("passWord");
		
		UserLoginDao uld = new UserLoginDao();

		UserLogin ul = uld.getUerLogin(emailAdd, passWord);
		
		return ul;
		
	}
	*/
	/*
	public HashMap<Integer,UserRoleLogin> getUerRoleLoginHM(){

		UserRoleLoginDao urld = new UserRoleLoginDao();

		HashMap<Integer,UserRoleLogin> hm = urld.getUerRoleLoginHM();
		
		return hm;
		
	}
	

	
	

	
	public HashMap<String,String> getSnapshotHM(){

		SnapshotDao sd = new SnapshotDao();

		HashMap<String,String> hm = sd.getSnapshotHM();
		
		return hm;
		
	}
	
	public void updateSnapshot(){

		SnapshotDao sd = new SnapshotDao();

		sd.updateSnapshot();
		
	}
	
	
	public void updateRequestStatus(){

		RequestDao rd = new RequestDao();

		rd.updateRequestStatus();
		
	}

	
	public HashMap<Integer,RoleLogin> getRoleLoginHM(){

		RoleLoginDao rld = new RoleLoginDao();

		HashMap<Integer,RoleLogin> hm = rld.getRoleLoginHM();
		
		return hm;
		
	}
	

	*/
	
	
	
	
	/*
	public HashMap<Integer,UserLogin> getUserLoginHM(){

		UserLoginDao uld = new UserLoginDao();

		HashMap<Integer,UserLogin> hm = uld.getUserLoginHM();
		
		return hm;
		
	}
	*/
	
	
	
}
