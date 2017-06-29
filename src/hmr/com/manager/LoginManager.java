package hmr.com.manager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bizoncloudone.com.manager.RunnableEmailManager;
import hmr.com.bean.Lov;
import hmr.com.bean.User;
import hmr.com.dao.UserDao;
import hmr.com.util.EmailUtil;
import hmr.com.util.StringUtil;


public class LoginManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	//String emailAdd = null;
	
	public LoginManager(){}

	public LoginManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public String doLoginManager(){
		String page = null;
		String action = req.getParameter("action")!=null ? req.getParameter("action") : "";
		
		if(action.equals("login")){
			
			String userId = req.getParameter("userId");
			String pw = req.getParameter("pw");
			
			User u = getUserOnLogin(userId, pw);

			if(u!=null){
				
				System.out.println("User login successful.");
				
				setLovValues(req, res, u);
				setUserValues(req, res);

				req.getSession().setAttribute("user-id", u.getId());
				req.getSession().setAttribute("userId", u.getEmail_address());
				req.getSession().setAttribute("firstName", u.getFirst_name());
				req.getSession().setAttribute("lastName", u.getLast_name());
				req.getSession().setAttribute("fullName", u.getFirst_name()+" "+u.getLast_name());
				req.getSession().setAttribute("user-role-id", u.getRole());
				
				//req.getSession().setAttribute("user-lov-role", lovRole);
				

				//System.out.println("Details : "+u.getFirst_name()+" "+u.getLast_name() + " : "+u.getEmail_address()+" "+u.getNew_password().indexOf("done"));

				if(u.getNew_password()!=null && u.getNew_password().indexOf("done") == -1){
					updateUserNewPassword(userId, u.getNew_password()+"done", Integer.valueOf(0));
				}
				
				if(pw.equals(u.getNew_password())){
					updateUserPassword(userId, pw);
				}
				page ="index.jsp";
				
			}else{
				
				System.out.println("User login failed.");
				
				
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "Email and Password does not match.");
				
				page ="login.jsp";
				
			}
		}else if("passwordReset".equals(action)){			
			String userId = req.getParameter("userId");
			
			User u = getUser(userId);

			if(u!=null){
				
				System.out.println("User email verified existing.");
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Password reset instructions has been sent to "+userId+".");
				page ="login.jsp";
				
				String ADMIN_EMAIL_ADD_CC = (String)req.getSession().getAttribute("ADMIN_EMAIL_ADD_CC");
				String newPassword = StringUtil.generateRandomKey();

				//updateUserPassword(userId, newPassword);
				updateUserNewPassword(userId, newPassword);
				
				String SERVER_DIRECTORY = (String)req.getSession().getAttribute("SERVER_DIRECTORY");			
				EmailUtil.setSERVER_DIRECTORY(SERVER_DIRECTORY);
				//EmailUtil.sendNewPasswordEmailUser(userId, ADMIN_EMAIL_ADD_CC, u.getFirst_name(), u.getLast_name(), newPassword);

				RunnableEmailManager REM = new RunnableEmailManager("HMR Auctions : Password Reset Successful", SERVER_DIRECTORY, userId, ADMIN_EMAIL_ADD_CC, u.getFirst_name(), u.getLast_name(), newPassword);
				
				
				//System.out.println("RunnableEmailManager ");
				
				REM.start();
				
				
			}else{
				
				System.out.println("User email does not exist.");
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Password reset instructions has been sent to "+userId+".");
				page ="login.jsp";
				
			}
		}
		
		return page;
		
	}
	

	
	
	public User getUser(String userId, String pw){
		
		User u = new User();

		UserDao ud = new UserDao();

		u = ud.getUser(userId, pw);
		
		return u;
		
	}	
	
	public User getUserOnLogin(String userId, String pw){
		
		User u = new User();

		UserDao ud = new UserDao();

		u = ud.getUserOnLogin(userId, pw);
		
		return u;
		
	}	
	
	public User getUser(String userId){
		
		User u = new User();

		UserDao ud = new UserDao();

		u = ud.getUser(userId);
		
		return u;
		
	}	
	
	
	public int updateUserPassword(String userId, String pw){
		
		//User u = new User();

		UserDao ud = new UserDao();

		int i = ud.updateUserPassword(userId, pw);
		
		return i;
		
	}
	
	public int updateUserNewPassword(String userId, String pw){
		
		//User u = new User();

		UserDao ud = new UserDao();

		int i = ud.updateUserNewPassword(userId, pw);
		
		return i;
		
	}
	
	public int updateUserNewPassword(String userId, String pw, Integer showChangePasswordNextLogin){
		
		//User u = new User();

		UserDao ud = new UserDao();

		int i = ud.updateUserNewPassword(userId, pw, showChangePasswordNextLogin);
		
		return i;
		
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
	public HashMap<Integer,UserLogin> getUserLoginHM(){

		UserLoginDao uld = new UserLoginDao();

		HashMap<Integer,UserLogin> hm = uld.getUserLoginHM();
		
		return hm;
		
	}
	*/
	
	
	private void setLovValues(HttpServletRequest req, HttpServletResponse res, User u){
		

		LovManager lovMngr = new LovManager(req, res);
		
		HashMap<Integer,Lov> userRoleLovHM = null;
		List<Lov> userRoleLovList = null;
		
		HashMap<Integer,Lov> genderLovHM = null;
		List<Lov> genderLovList = null;
		
		HashMap<Integer,Lov> userStatusLovHM = null;
		List<Lov> userStatusLovList = null;
		/*
		HashMap<Integer,User> coordinatorUserHM = null;
		List<User> coordinatorUserList = null;
		
		HashMap<Integer,User> bidderUserHM = null;
		List<User> bidderUserList = null;
		*/
		try {
			
			System.out.println("USER-ROLE-HM : " + req.getSession().getAttribute("USER-ROLE-HM"));
			System.out.println("USER-ROLE-LIST : " + req.getSession().getAttribute("USER-ROLE-LIST"));
			System.out.println("GENDER-HM : " + req.getSession().getAttribute("GENDER-HM"));
			System.out.println("GENDER-LIST : " + req.getSession().getAttribute("GENDER-LIST"));
			System.out.println("USER-STATUS-HM : " + req.getSession().getAttribute("USER-STATUS-HM"));
			System.out.println("USER-STATUS-LIST : " + req.getSession().getAttribute("USER-STATUS-LIST"));

			if(req.getSession().getAttribute("USER-ROLE-HM")==null ){
				userRoleLovHM = lovMngr.getLovHM("USER-ROLE");
				req.getSession().setAttribute("USER-ROLE-HM", userRoleLovHM);
			}else{
				userRoleLovHM = (HashMap<Integer,Lov>)req.getSession().getAttribute("USER-ROLE-HM");
			}
			
			if(req.getSession().getAttribute("USER-ROLE-LIST")==null ){
				userRoleLovList = lovMngr.getLovList("USER-ROLE");
				req.getSession().setAttribute("USER-ROLE-LIST", userRoleLovList);
			}else{
				userRoleLovList = (List<Lov>)req.getSession().getAttribute("USER-ROLE-LIST");
			}
			
			if(req.getSession().getAttribute("GENDER-HM")==null ){
				genderLovHM = lovMngr.getLovHM("GENDER");
				req.getSession().setAttribute("GENDER-HM", genderLovHM);
			}else{
				genderLovHM = (HashMap<Integer,Lov>)req.getSession().getAttribute("GENDER-HM");
			}
			
			if(req.getSession().getAttribute("GENDER-LIST")==null ){
				genderLovList = lovMngr.getLovList("GENDER");
				req.getSession().setAttribute("GENDER-LIST", genderLovList);
			}else{
				genderLovList = (List<Lov>)req.getSession().getAttribute("GENDER-LIST");
			}
			
			
			if(req.getSession().getAttribute("USER-STATUS-HM")==null ){
				userStatusLovHM = lovMngr.getLovHM("USER-STATUS");
				req.getSession().setAttribute("USER-STATUS-HM", userStatusLovHM);
			}else{
				userStatusLovHM = (HashMap<Integer,Lov>)req.getSession().getAttribute("USER-STATUS-HM");
			}
			
			if(req.getSession().getAttribute("USER-STATUS-LIST")==null ){
				userStatusLovList = lovMngr.getLovList("USER-STATUS");
				req.getSession().setAttribute("USER-STATUS-LIST", userStatusLovList);
			}else{
				userStatusLovList = (List<Lov>)req.getSession().getAttribute("USER-STATUS-LIST");
			}

			System.out.println("User Role : "+u.getRole());
			Lov lovRole = userRoleLovHM.get(u.getRole());
			System.out.println("User Role : "+lovRole.getName());
			req.getSession().setAttribute("user-lov-role", lovRole);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	private void setUserValues(HttpServletRequest req, HttpServletResponse res){

		UserManager uMngr = new UserManager(req, res);

		HashMap<Integer,User> coordinatorUserHM = null;
		List<User> coordinatorUserList = null;
		
		HashMap<Integer,User> bidderUserHM = null;
		List<User> bidderUserList = null;
		
		HashMap<Integer,User> allUserHM = null;
		List<User> allUserList = null;
		
		try {
			
			System.out.println("COORDINATOR-USER-HM : " + req.getSession().getAttribute("COORDINATOR-USER-HM"));
			System.out.println("COORDINATOR-USER-LIST : " + req.getSession().getAttribute("COORDINATOR-USER-LIST"));
			System.out.println("BIDDER-USER-HM : " + req.getSession().getAttribute("BIDDER-USER-HM"));
			System.out.println("BIDDER-USER-LIST : " + req.getSession().getAttribute("BIDDER-USER-LIST"));
			System.out.println("ALL-USER-HM : " + req.getSession().getAttribute("ALL-USER-HM"));
			System.out.println("ALL-USER-LIST : " + req.getSession().getAttribute("ALL-USER-LIST"));
			
			if(req.getSession().getAttribute("COORDINATOR-USER-HM")==null ){
				coordinatorUserHM = uMngr.getUserHMByRole(5);
				req.getSession().setAttribute("COORDINATOR-USER-HM", coordinatorUserHM);
			}else{
				coordinatorUserHM = (HashMap<Integer,User>)req.getSession().getAttribute("COORDINATOR-USER-HM");
			}
			
			if(req.getSession().getAttribute("COORDINATOR-USER-LIST")==null ){
				coordinatorUserList = uMngr.getUserListByRole(5);
				req.getSession().setAttribute("COORDINATOR-USER-LIST", coordinatorUserList);
			}else{
				coordinatorUserList = (List<User>)req.getSession().getAttribute("COORDINATOR-USER-LIST");
			}
			
			if(req.getSession().getAttribute("BIDDER-USER-HM")==null ){
				bidderUserHM = uMngr.getUserHMByRole(2);
				req.getSession().setAttribute("BIDDER-USER-HM", bidderUserHM);
			}else{
				bidderUserHM = (HashMap<Integer,User>)req.getSession().getAttribute("BIDDER-USER-HM");
			}
			
			if(req.getSession().getAttribute("BIDDER-USER-LIST")==null ){
				bidderUserList = uMngr.getUserListByRole(2);
				req.getSession().setAttribute("BIDDER-USER-LIST", bidderUserList);
			}else{
				bidderUserList = (List<User>)req.getSession().getAttribute("BIDDER-USER-LIST");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}