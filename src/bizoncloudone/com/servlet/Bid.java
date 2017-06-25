package bizoncloudone.com.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import bizoncloudone.com.bean.ParamsLov;
import bizoncloudone.com.bean.Request;
import bizoncloudone.com.bean.RoleLogin;
import bizoncloudone.com.bean.UserLogin;
import bizoncloudone.com.bean.UserRoleLogin;
import hmr.com.manager.AuctionManager;
import hmr.com.manager.LoginManager;
import hmr.com.manager.UserManager;
import bizoncloudone.com.manager.ParamsLovManager;
import bizoncloudone.com.manager.RequestManager;
import bizoncloudone.com.manager.RoleLoginManager;
import bizoncloudone.com.manager.RunnableEmailManager;
import bizoncloudone.com.manager.UserLoginManager;
import bizoncloudone.com.util.DBConnection;
import hmr.com.bean.User;

@SuppressWarnings("serial")
public class Bid extends HttpServlet {
	
	//String emaidAddPost = null;
	//String emaidAddGet = null;
	
	String managerGet = null;
	String actionGet = null;
	String userIdGet = null;
	String vekGet = null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//emaidAddPost = req.getParameter("emailAdd")!=null ? req.getParameter("emailAdd") : req.getParameter("userId");
		doProcess(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("Controller doProcessGet - start");
		
		String action = req.getParameter("a")!=null ? (String)req.getParameter("a") : "";
		String uid = req.getParameter("uid")!=null ? (String)req.getParameter("uid") : "";
		String mngr = req.getParameter("mngr")!=null ? (String)req.getParameter("mngr") : "";
		String vek = req.getParameter("vek")!=null ? (String)req.getParameter("vek") : "";
		
		managerGet = mngr;
		actionGet = action;
		userIdGet = uid;
		vekGet = vek;
		
		System.out.println("managerGet : "+managerGet);
		System.out.println("actionGet : "+actionGet);
		System.out.println("userIdGet : "+userIdGet);
		System.out.println("vekGet : "+vek);
		
		if(actionGet!=null){
			
			req.setAttribute("action", actionGet);
			req.setAttribute("userId", userIdGet);
			req.setAttribute("manager", managerGet);
			req.setAttribute("vek", vekGet);

			System.out.println("");
			System.out.println("Controller doProcessGet - end");
			RequestDispatcher rd = req.getRequestDispatcher("security-check.jsp");
			rd.forward(req, res);
			
		}else{
			doProcess(req, res);
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	protected void doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		System.out.println("Controller - start");
		System.out.println("");
		
		String COMPANY_NAME = (String)req.getSession().getAttribute("COMPANY_NAME");
		String COMPANY_NAME_ACRONYM = (String)req.getSession().getAttribute("COMPANY_NAME_ACRONYM");
		String SKIN_HEADER_COLOR = (String)req.getSession().getAttribute("SKIN_HEADER_COLOR");
		String CREDENTIALS_DIRECTORY = (String)req.getSession().getAttribute("CREDENTIALS_DIRECTORY");
		String HOST_NAME = (String)req.getSession().getAttribute("HOST_NAME");
		String SERVER_DIRECTORY = (String)req.getSession().getAttribute("SERVER_DIRECTORY");
		String PROTOCOL = (String)req.getSession().getAttribute("PROTOCOL");
		String ADMIN_MOBILE_NO = (String)req.getSession().getAttribute("ADMIN_MOBILE_NO");
		String ADMIN_EMAIL_ADD_CC = (String)req.getSession().getAttribute("ADMIN_EMAIL_ADD_CC");
		String SERVER_DIRECTORY_HMR_IMAGES = (String)req.getSession().getAttribute("SERVER_DIRECTORY_HMR_IMAGES");
		
		

		if(COMPANY_NAME==null){
		    try {
	            Properties prop = new Properties();
	            InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("app.properties");
	            prop.load(inputStream);
	            
	            COMPANY_NAME = prop.getProperty("COMPANY_NAME");
	            COMPANY_NAME_ACRONYM = prop.getProperty("COMPANY_NAME_ACRONYM");
	            SKIN_HEADER_COLOR = prop.getProperty("SKIN_HEADER_COLOR");

	            String HOST_NAME_ = prop.getProperty("HOST_NAME");
	            String HOST_NAME_ONLINE_ = prop.getProperty("HOST_NAME_ONLINE");
	            String PROTOCOL_ = prop.getProperty("PROTOCOL");
	            String PROTOCOL_ONLINE_ = prop.getProperty("PROTOCOL_ONLINE");
	            String SERVER_DIRECTORY_ = prop.getProperty("SERVER_DIRECTORY");
	            String SERVER_DIRECTORY_ONLINE_ = prop.getProperty("SERVER_DIRECTORY_ONLINE");
	            String CREDENTIALS_DIRECTORY_ = prop.getProperty("CREDENTIALS_DIRECTORY");
	            String CREDENTIALS_DIRECTORY_ONLINE_ = prop.getProperty("CREDENTIALS_DIRECTORY_ONLINE");
	            ADMIN_MOBILE_NO = prop.getProperty("ADMIN_MOBILE_NO");
	            ADMIN_EMAIL_ADD_CC = prop.getProperty("ADMIN_EMAIL_ADD_CC");
	            SERVER_DIRECTORY_HMR_IMAGES = prop.getProperty("SERVER_DIRECTORY_HMR_IMAGES");
	            String IS_ONLINE_ = prop.getProperty("IS_ONLINE");
	            
	            
	            if("YES".equals(IS_ONLINE_)){
	            	HOST_NAME = HOST_NAME_ONLINE_;
	            	PROTOCOL = PROTOCOL_ONLINE_;
	            	SERVER_DIRECTORY = SERVER_DIRECTORY_ONLINE_;
	            	CREDENTIALS_DIRECTORY = CREDENTIALS_DIRECTORY_ONLINE_;
	            }else{
	            	HOST_NAME = HOST_NAME_;
	            	PROTOCOL = PROTOCOL_;
	            	SERVER_DIRECTORY = SERVER_DIRECTORY_;
	            	CREDENTIALS_DIRECTORY = CREDENTIALS_DIRECTORY_;
	            }
	            
	            req.getSession().setAttribute("HOST_NAME", HOST_NAME);
	            req.getSession().setAttribute("SERVER_DIRECTORY", SERVER_DIRECTORY);
	            req.getSession().setAttribute("PROTOCOL", PROTOCOL);
	            req.getSession().setAttribute("COMPANY_NAME", COMPANY_NAME);
	            req.getSession().setAttribute("COMPANY_NAME_ACRONYM", COMPANY_NAME_ACRONYM);
	            req.getSession().setAttribute("SKIN_HEADER_COLOR", SKIN_HEADER_COLOR);
	            req.getSession().setAttribute("CREDENTIALS_DIRECTORY", CREDENTIALS_DIRECTORY);
	            req.getSession().setAttribute("ADMIN_MOBILE_NO", ADMIN_MOBILE_NO);
	            req.getSession().setAttribute("ADMIN_EMAIL_ADD_CC", ADMIN_EMAIL_ADD_CC);
	            req.getSession().setAttribute("SERVER_DIRECTORY_HMR_IMAGES", SERVER_DIRECTORY_HMR_IMAGES);
	            
	            
	            System.out.println("HOST_NAME = "+HOST_NAME);
	            System.out.println("SERVER_DIRECTORY = "+SERVER_DIRECTORY);
	            System.out.println("PROTOCOL = "+PROTOCOL);
	            System.out.println("COMPANY_NAME = "+COMPANY_NAME);
	            System.out.println("COMPANY_NAME_ACRONYM = "+COMPANY_NAME_ACRONYM);
	            System.out.println("SKIN_HEADER_COLOR = "+SKIN_HEADER_COLOR);
	            System.out.println("CREDENTIALS_DIRECTORY = "+CREDENTIALS_DIRECTORY);
	            System.out.println("ADMIN_MOBILE_NO = "+ADMIN_MOBILE_NO);
	            System.out.println("ADMIN_EMAIL_ADD_CC = "+ADMIN_EMAIL_ADD_CC);
	            System.out.println("SERVER_DIRECTORY_HMR_IMAGES = "+SERVER_DIRECTORY_HMR_IMAGES);
	            

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
		}
    
		String sid = req.getSession().getId();
		String manager = req.getParameter("manager")!=null ? (String)req.getParameter("manager") : "";
		String action = req.getParameter("action")!=null ? (String)req.getParameter("action") : "";
		String userId = req.getParameter("userId")!=null ? (String)req.getParameter("userId") : (String)req.getAttribute("userId");
		String vek = req.getParameter("vek")!=null ? (String)req.getParameter("vek") : (String)req.getAttribute("vek");
		
		String page = null;

		System.out.println("Paramerters - start");
		System.out.println("manager : "+manager);
		System.out.println("action : "+action);
		System.out.println("userId : "+userId);
		System.out.println("vek : "+vek);
		System.out.println("Paramerters - end");
		System.out.println("");

		
		
		if(manager.equals("get")){
			
			if("login".equals(action)){
				
				if(userId!=null){
					req.setAttribute("userId", userId);
				}
				
				page ="login.jsp";
			
			}else if("registration".equals(action)){
				
				page ="registration.jsp";
				
			}else if("home".equals(action)){
				
				page ="index.jsp";
				
			}else if("forgotPassword".equals(action)){
				
				page ="forgot-password.jsp";
				
			}else if("logout".equals(action)){
				System.out.println("User log out successful.");

				req.getSession().removeAttribute("firstName");
				req.getSession().removeAttribute("lastName");
				req.getSession().removeAttribute("fullName");
				req.getSession().removeAttribute("userId");
				req.getSession().removeAttribute("user-id");
				req.getSession().removeAttribute("user-role-id");
				req.getSession().removeAttribute("user-lov-role");

				page ="index.jsp";
			}else if("verifyEmail".equals(action)){
				
				UserManager uMngr = new UserManager(req,res);
				page = uMngr.doUserManager();
			}
			
		}

		if(userId!=null && !"".equals(userId) && !manager.equals("get")){
			
			String lastAction = (String)req.getSession().getAttribute("userLastAction_"+sid);
			System.out.println("lastAction :"+lastAction);
			/*
			if("signIn".equals(lastAction) ){
				System.out.println("reSignIn");
				action = "reSignIn";
			}
			*/
			if(manager.equals("login-manager")){
				LoginManager liMngr = new LoginManager(req,res);
				page = liMngr.doLoginManager();
				
				
			}else if(manager.equals("user-manager")){
				UserManager uMngr = new UserManager(req,res);
				page = uMngr.doUserManager();
				
			}else if(manager.equals("auction-manager")){
				AuctionManager aMngr = new AuctionManager(req,res);
				page = aMngr.doAuctionManager();
				
			}
			else 
			{
				if("signIn".equals(action) || "reSignIn".equals(action)){
					
					LoginManager liMngr = new LoginManager(req, res);
					
					UserLogin ul = null;
					
					if("signIn".equals(action)){
						//ul = liMngr.getUserLogin(emailAdd);
					}else{
						ul = (UserLogin)req.getSession().getAttribute("userLogin_"+sid);
						
						if(ul!=null && userId.equals(ul.getEmail_add())){
							ul = (UserLogin)req.getSession().getAttribute("userLogin_"+sid);
						}else if(ul==null){
							//ul = liMngr.getUserLogin(emailAdd);
						}else{
							//ul = liMngr.getUserLogin(emailAdd);
						}
					}
					
					//System.out.println("ul.getEmail_add() "+ul.getEmail_add());
					
					if(ul!=null && ul.getEmail_add()!=null){
						
						System.out.println("User sign in successful.");
						
						HashMap<Integer,UserRoleLogin> urlhm = (HashMap<Integer,UserRoleLogin>)req.getSession().getAttribute("userRoleLoginHM");
						HashMap<Integer,RoleLogin> rlhm = (HashMap<Integer,RoleLogin>)req.getSession().getAttribute("roleLoginHM");
						HashMap<Integer,UserLogin> ulhm = (HashMap<Integer,UserLogin>)req.getSession().getAttribute("userLoginHM");
						/*
						if(urlhm==null || urlhm.isEmpty()){
							System.out.println("urlhm is null");
							urlhm = liMngr.getUerRoleLoginHM();
						}
						
						if(rlhm==null || rlhm.isEmpty()){
							System.out.println("rlhm is null");
							rlhm = liMngr.getRoleLoginHM();
						}
	
						if(ulhm==null || ulhm.isEmpty()){
							System.out.println("ulhm is null");
							//ulhm = liMngr.getUserLoginHM();
						}
						*/
						
						//liMngr.updateRequestStatus();
	
						HashMap<String,String> shm = (HashMap<String,String>)req.getSession().getAttribute("snapshotHM");
						
						if(shm==null || shm.isEmpty()){
							System.out.println("shm is null");
							//liMngr.updateSnapshot();
							//shm = liMngr.getSnapshotHM();
						}
						
						RoleLoginManager rlMngr = new RoleLoginManager();
						ArrayList<RoleLogin> rlList = (ArrayList<RoleLogin>)req.getSession().getAttribute("roleLoginList");
						
						if(rlList==null || rlList.isEmpty()){
							System.out.println("rlList is null");
							rlList = rlMngr.getRoleLoginList();
						}
		
						List<UserLogin> ulList = (ArrayList<UserLogin>)req.getSession().getAttribute("userLoginList");
						List<UserLogin> ulList3 = (ArrayList<UserLogin>)req.getSession().getAttribute("userLoginList3");
						
						if(ulList==null || ulList.isEmpty()){
							System.out.println("ulList is null");
							UserLoginManager ulMngr = new UserLoginManager();
							ulList = ulMngr.getUserLoginList();
							ulList3 = ulMngr.getUserLoginList3();
						}
	
						ParamsLovManager plMngr = new ParamsLovManager(req, res);
						
						HashMap<Integer,ParamsLov> reqStatusParamLovHM = null;
						List<ParamsLov> reqStatusParamLovList = null;
						try {
							reqStatusParamLovHM = plMngr.getParamLovHM("REQUEST-STATUS");
							reqStatusParamLovList = plMngr.getParamLovList("REQUEST-STATUS");
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
	
						Integer userLoginId = ul.getId();
						
						req.getSession().setAttribute("userLoginId_"+sid, userLoginId);
						req.getSession().setAttribute("userLoginEmailAdd_"+sid, ul.getEmail_add());
						
						UserRoleLogin url = urlhm.get(userLoginId);
	
						RoleLogin rl = rlhm.get(url.getRole_id());
						
						req.getSession().setAttribute("userRoleLoginId_"+sid, url.getRole_id());
						req.getSession().setAttribute("userRoleLoginName_"+sid, rl.getRole_name());
	
						System.out.println("userRoleLoginId : "+url.getRole_id());
						System.out.println("userRoleLoginName : "+rl.getRole_name());
	
						req.setAttribute("userRoleLoginHM", urlhm);
						req.setAttribute("roleLoginHM", rlhm);
						
						req.getSession().setAttribute("emailAdd_"+userId, userId);
						req.getSession().setAttribute("emailAdd_"+sid, userId);
						req.getSession().setAttribute("userLogin_"+sid, ul);
						req.getSession().setAttribute("userRoleLoginHM", urlhm);
						req.getSession().setAttribute("roleLoginHM", rlhm);
						req.getSession().setAttribute("userLoginHM", ulhm);
						req.getSession().setAttribute("roleLoginList", rlList);
						req.getSession().setAttribute("snapshotHM", shm);
						req.getSession().setAttribute("userLoginList", ulList);
						req.getSession().setAttribute("reqStatusParamLovHM", reqStatusParamLovHM);
						req.getSession().setAttribute("reqStatusParamLovList", reqStatusParamLovList);
						
						
						req.getSession().setAttribute("userLoginList3", ulList3);
						
						RequestManager rManager = new RequestManager(req, res);
						
						List<Request> requestList = rManager.getRequestListLatest();
						
						req.setAttribute("requestList", requestList);
	
						page ="dashboard.jsp";
						
					}else{
						
						System.out.println("User login failed.");
						
						req.setAttribute("msgType", "Alert");
						req.setAttribute("msg", "Please input valid credentials.");
						
						page ="login.jsp";
						
					}
				}else if("login".equals(action) || "reLogin".equals(action)){
				
				System.out.println("aaaaaaaaaa");	
					
				LoginManager liMngr = new LoginManager(req, res);
				
				userId = req.getParameter("userId");
				String pw = req.getParameter("pw");
				
				User u = new User();
				
				if("login".equals(action)){
					u = liMngr.getUser(userId, pw);
				}else{
					//u = (UserLogin)req.getSession().getAttribute("userLogin_"+sid);
					
					//if(ul!=null && emailAdd.equals(ul.getEmail_add())){
						//u = (UserLogin)req.getSession().getAttribute("userLogin_"+sid);
					//}else if(ul==null){
						//u = liMngr.getUserLogin(emailAdd);
					//}else{
						//u = liMngr.getUserLogin(emailAdd);
					//}
				}
				
				//System.out.println("ul.getEmail_add() "+ul.getEmail_add());
				
				if(u!=null){
					
					System.out.println("User log in successful.");
					
					//HashMap<Integer,UserRoleLogin> urlhm = (HashMap<Integer,UserRoleLogin>)req.getSession().getAttribute("userRoleLoginHM");
					//HashMap<Integer,RoleLogin> rlhm = (HashMap<Integer,RoleLogin>)req.getSession().getAttribute("roleLoginHM");
					//HashMap<Integer,UserLogin> ulhm = (HashMap<Integer,UserLogin>)req.getSession().getAttribute("userLoginHM");
	/*
					if(urlhm==null || urlhm.isEmpty()){
						System.out.println("urlhm is null");
						urlhm = liMngr.getUerRoleLoginHM();
					}
					
					if(rlhm==null || rlhm.isEmpty()){
						System.out.println("rlhm is null");
						rlhm = liMngr.getRoleLoginHM();
					}
	
					if(ulhm==null || ulhm.isEmpty()){
						System.out.println("ulhm is null");
						ulhm = liMngr.getUserLoginHM();
					}
					
					*/
					//liMngr.updateRequestStatus();
					/*
					HashMap<String,String> shm = (HashMap<String,String>)req.getSession().getAttribute("snapshotHM");
					
					if(shm==null || shm.isEmpty()){
						System.out.println("shm is null");
						//liMngr.updateSnapshot();
						//shm = liMngr.getSnapshotHM();
					}
					
					RoleLoginManager rlMngr = new RoleLoginManager();
					ArrayList<RoleLogin> rlList = (ArrayList<RoleLogin>)req.getSession().getAttribute("roleLoginList");
					
					if(rlList==null || rlList.isEmpty()){
						System.out.println("rlList is null");
						rlList = rlMngr.getRoleLoginList();
					}
	
					List<UserLogin> ulList = (ArrayList<UserLogin>)req.getSession().getAttribute("userLoginList");
					List<UserLogin> ulList3 = (ArrayList<UserLogin>)req.getSession().getAttribute("userLoginList3");
					
					if(ulList==null || ulList.isEmpty()){
						System.out.println("ulList is null");
						UserLoginManager ulMngr = new UserLoginManager();
						ulList = ulMngr.getUserLoginList();
						ulList3 = ulMngr.getUserLoginList3();
					}
					*/
					/*
					ParamsLovManager plMngr = new ParamsLovManager(req, res);
					
					HashMap<Integer,ParamsLov> reqStatusParamLovHM = null;
					List<ParamsLov> reqStatusParamLovList = null;
					try {
						reqStatusParamLovHM = plMngr.getParamLovHM("REQUEST-STATUS");
						reqStatusParamLovList = plMngr.getParamLovList("REQUEST-STATUS");
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					*/
					/*
					Integer userLoginId = ul.getId();
					
					req.getSession().setAttribute("userLoginId_"+sid, userLoginId);
					req.getSession().setAttribute("userLoginEmailAdd_"+sid, ul.getEmail_add());
					
					UserRoleLogin url = urlhm.get(userLoginId);
	
					RoleLogin rl = rlhm.get(url.getRole_id());
					
					req.getSession().setAttribute("userRoleLoginId_"+sid, url.getRole_id());
					req.getSession().setAttribute("userRoleLoginName_"+sid, rl.getRole_name());
	
					System.out.println("userRoleLoginId : "+url.getRole_id());
					System.out.println("userRoleLoginName : "+rl.getRole_name());
	
					req.setAttribute("userRoleLoginHM", urlhm);
					req.setAttribute("roleLoginHM", rlhm);
					
					req.getSession().setAttribute("emailAdd_"+emailAdd, emailAdd);
					req.getSession().setAttribute("emailAdd_"+sid, emailAdd);
					req.getSession().setAttribute("userLogin_"+sid, ul);
					req.getSession().setAttribute("userRoleLoginHM", urlhm);
					req.getSession().setAttribute("roleLoginHM", rlhm);
					req.getSession().setAttribute("userLoginHM", ulhm);
					req.getSession().setAttribute("roleLoginList", rlList);
					req.getSession().setAttribute("snapshotHM", shm);
					req.getSession().setAttribute("userLoginList", ulList);
					req.getSession().setAttribute("reqStatusParamLovHM", reqStatusParamLovHM);
					req.getSession().setAttribute("reqStatusParamLovList", reqStatusParamLovList);
					*/
					
					//req.getSession().setAttribute("userLoginList3", ulList3);
					req.getSession().setAttribute("userId", u.getEmail_address());
					req.getSession().setAttribute("firstName", u.getFirst_name());
					req.getSession().setAttribute("lastName", u.getLast_name());
					req.getSession().setAttribute("fullName", u.getFirst_name()+" "+u.getLast_name());
					
					System.out.println("Details : "+u.getFirst_name()+" "+u.getLast_name() + " : "+u.getEmail_address());
					//RequestManager rManager = new RequestManager(req, res);
					
					//List<Request> requestList = rManager.getRequestListLatest();
					
					//req.setAttribute("requestList", requestList);
	
					page ="index.jsp";
					
				}else{
					
					System.out.println("User login failed.");
					
					req.setAttribute("msgType", "Alert");
					req.setAttribute("msg", "Please input valid credentials.");
					
					page ="login.jsp";
					
				}
				}else if("signOut".equals(action)){
					
					System.out.println("User sign out successful.");
					
					req.setAttribute("msgType", "CallOut");
					req.setAttribute("msg", "Sign Out.");
	
					req.getSession().removeAttribute("emailAdd_"+userId);
					req.getSession().removeAttribute("emailAdd_"+sid);
					req.getSession().removeAttribute("userLogin_"+sid);
					req.getSession().removeAttribute("userLastAction_"+sid);
					
					page ="login.jsp";
					
				}else if("logout".equals(action)){
					
					System.out.println("User sign out successful.");
					
					//req.setAttribute("msgType", "CallOut");
					//req.setAttribute("msg", "Sign Out.");
	
					//req.getSession().removeAttribute("emailAdd_"+emailAdd);
					//req.getSession().removeAttribute("emailAdd_"+sid);
					//req.getSession().removeAttribute("userLogin_"+sid);
					//req.getSession().removeAttribute("userLastAction_"+sid);
					req.getSession().removeAttribute("firstName");
					req.getSession().removeAttribute("lastName");
					req.getSession().removeAttribute("fullName");
					
					page ="index.jsp";
					
				}else if("changePassword".equals(action)){
					
					System.out.println("Change password page.");
					
					UserLogin ul = (UserLogin)req.getSession().getAttribute("userLogin_"+sid);
	
					System.out.println("ul : "+ul);
					
					req.setAttribute("userLogin", ul);
	
					page ="change-password.jsp";
					
				}else if("savePassword".equals(action)){
					
					System.out.println("Save password page.");
					
					Integer userLoginId = Integer.valueOf(req.getParameter("userLoginId"));
					String pass_word = req.getParameter("pass_word");
					
					UserLogin ulCurUsr = (UserLogin)req.getSession().getAttribute("userLogin_"+sid);
					
					Integer updated_by = ulCurUsr.getId();
					
					UserLoginManager ulMngr = new UserLoginManager();
					
				
					int i = ulMngr.updateUserLogin(userLoginId, pass_word, updated_by);
					
					if(i==1){
						req.setAttribute("msgType", "CallOut");
						req.setAttribute("msg", "Change password.");
					}else{
						req.setAttribute("msgType", "Alert");
						req.setAttribute("msg", "Please call you system administrator.");
					}
	
					UserLogin ul = ulMngr.getUserLogin(userLoginId);
					
					System.out.println("ul : "+ul);
					
					req.setAttribute("userLogin", ul);
	
					page ="change-password.jsp";
					
				}else if("dashboard".equals(action)){
					
					System.out.println("Dashboard page.");
					
					LoginManager liMngr = new LoginManager(req, res);
	
					//HashMap<Integer,UserRoleLogin> urlhm = liMngr.getUerRoleLoginHM();
					//HashMap<Integer,RoleLogin> rlhm = liMngr.getRoleLoginHM();
	
					//req.setAttribute("userRoleLogin", urlhm);
					//req.setAttribute("roleLogin", rlhm);
					
					RequestManager rManager = new RequestManager(req, res);
					
					List<Request> requestList = rManager.getRequestListLatest();
					
					req.setAttribute("requestList", requestList);
					
	
					page ="dashboard.jsp";
					
					
				}else if("users".equals(action)){
					
					System.out.println("Users list page.");
					
					UserLoginManager ulMngr = new UserLoginManager();
					
					List<UserLogin> ulList = ulMngr.getUserLoginList();
					
					System.out.println("ulList : "+ulList.size());
					
					req.setAttribute("userLoginList", ulList);
	
					page ="user-list.jsp";
	
				}else if("requests".equals(action)){
					
					System.out.println("Requests list page.");
	
					RequestManager rMngr = new RequestManager(req,res);
					
					List<Request> rList = rMngr.getRequestList();
					
					System.out.println("rList : "+rList.size());
					
					req.setAttribute("requestList", rList);
	
					page ="request-list.jsp";
	
				}else if("roles".equals(action)){
					
					System.out.println("Roles list page.");
					
					//UserLoginManager ulMngr = new UserLoginManager();
					
					RoleLoginManager rlMngr = new RoleLoginManager();
					
					List<RoleLogin> rlList = rlMngr.getRoleLoginList();
					
					System.out.println("rlList : "+rlList.size());
					
					req.setAttribute("roleLoginList", rlList);
	
					page ="role-list.jsp";
					
				}else if("viewRole".equals(action)){
					
					System.out.println("Role page.");
					
					Integer roleId = Integer.valueOf(req.getParameter("roleId")) ;
					
					RoleLoginManager rlMngr = new RoleLoginManager();
					
					RoleLogin rl = rlMngr.getRoleLogin(roleId);
					
					System.out.println("rl : "+rl);
					
					req.setAttribute("roleLogin", rl);
					
					page ="role.jsp";
	
				}else if("viewUser".equals(action)){
					
					System.out.println("User page.");
					
					Integer userLoginId = Integer.valueOf(req.getParameter("userLoginId")) ;
					
					UserLoginManager ulMngr = new UserLoginManager();
					
					UserLogin ul = ulMngr.getUserLogin(userLoginId);
					
					//List<UserLogin> ulList = ulMngr.getUserLoginList();
					
					System.out.println("ul : "+ul);
					
					req.setAttribute("userLogin", ul);
					
					LoginManager liMngr = new LoginManager(req, res);
					
					//HashMap<Integer,UserRoleLogin> urlhm = liMngr.getUerRoleLoginHM();
					//HashMap<Integer,RoleLogin> rlhm = liMngr.getRoleLoginHM();
									
					//UserRoleLogin url = urlhm.get(ul.getId());
	
					//RoleLogin rl = rlhm.get(url.getRole_id(	));
					
					//req.setAttribute("userRoleLoginId_userLogin", url.getRole_id());
					//req.setAttribute("userRoleLoginName_userLogin", rl.getRole_name());
	
					//System.out.println("userRoleLoginId_userLogin : "+url.getRole_id());
					//System.out.println("userRoleLoginName_userLogin : "+rl.getRole_name());
					
					page ="user.jsp";
	
				}else if("viewRequest".equals(action)){
					
					System.out.println("Request page.");
					
					Integer requestId = Integer.valueOf(req.getParameter("requestId")) ;
					
					RequestManager rMngr = new RequestManager();
					
					Request r = rMngr.getRequest(requestId);
					
					System.out.println("r "+r);
					
					req.setAttribute("requestCur", r);
	
					page ="request.jsp";
	
				}else if("updateUser".equals(action)){
					
					System.out.println("User update page.");
					
					Integer userLoginId = Integer.valueOf(req.getParameter("userLoginId")) ;
					
					UserLoginManager ulMngr = new UserLoginManager();
					
					UserLogin ul = ulMngr.getUserLogin(userLoginId);
					
					//List<UserLogin> ulList = ulMngr.getUserLoginList();
					
					System.out.println("ul : "+ul);
					
					req.setAttribute("userLogin", ul);
					
					LoginManager liMngr = new LoginManager(req, res);
					
					//HashMap<Integer,UserRoleLogin> urlhm = liMngr.getUerRoleLoginHM();
					//HashMap<Integer,RoleLogin> rlhm = liMngr.getRoleLoginHM();
									
					//UserRoleLogin url = urlhm.get(ul.getId());
	
					//RoleLogin rl = rlhm.get(url.getRole_id());
					
					//req.setAttribute("userRoleLoginId_userLogin", url.getRole_id());
					//req.setAttribute("userRoleLoginName_userLogin", rl.getRole_name());
	
					//System.out.println("userRoleLoginId_userLogin : "+url.getRole_id());
					//System.out.println("userRoleLoginName_userLogin : "+rl.getRole_name());
					
					page ="user-update.jsp";
	
				}else if("updateRequest".equals(action)){
					
					System.out.println("Request update page.");
					
					Integer requestId = Integer.valueOf(req.getParameter("requestId")) ;
					
					RequestManager rMngr = new RequestManager();
					
					Request r = rMngr.getRequest(requestId);
	
					System.out.println("r : "+r);
					
					req.setAttribute("requestCur", r);
	
					page ="request-update.jsp";
	
				}else if("updateRole".equals(action)){
					
					System.out.println("Role update page.");
					
					Integer roleLoginId = Integer.valueOf(req.getParameter("roleLoginId")) ;
					
					System.out.println("roleLoginId : "+roleLoginId);
					
					RoleLoginManager aMngr = new RoleLoginManager();
					
					RoleLogin rl = aMngr.getRoleLogin(roleLoginId);
	
					System.out.println("rl : "+rl);
					
					req.setAttribute("roleLogin", rl);
					
					page ="role-update.jsp";
					
				
					
	
				}else if("createUser".equals(action)){
					
					System.out.println("User create page.");
	
					page ="user-create.jsp";
					
				}else if("createRequest".equals(action)){
					
					System.out.println("Request create page.");
	
					page ="request-create.jsp";
					
				}else if("createRole".equals(action)){
					
					System.out.println("Role create page.");
	
					page ="role-create.jsp";
	
				}else if("saveNewRequest".equals(action)){
					
					System.out.println("Request save new page.");
					
					Integer user_id = Integer.valueOf(req.getParameter("user_id"));
					
					//Boolean pass_word = Boolean.valueOf(req.getParameter("pass_word"));
					
					Boolean pass_word = true;
					
					System.out.println("user_id "+user_id);
					
					System.out.println("pass_word "+pass_word);
					
					UserLogin ulCurUsr = (UserLogin)req.getSession().getAttribute("userLogin_"+sid);
					
					Integer created_by = ulCurUsr.getId();
					
					RequestManager rMngr = new RequestManager(req, res);
	
					//access_key = StringUtils.generateRandomKey();
					
					Integer status_id = 1;
					
					//Request r = rMngr.createRequest(user_id, status_id, access_key, created_by, pass_word);
					Request r = null;
	
					
						if(r!=null){
							
							
							UserLoginManager ulMngr = new UserLoginManager(req,res);
							
							UserLogin ul = ulMngr.getUserLogin(user_id);
							
							String name = "sendEmailRequest";
							String email_add_to = ul.getEmail_add();
							String email_add_cc = "CustomGoogleApplication@marcventures.com.ph";
							//String email_add_cc = "dakkiboy17@gmail.com";,test-admin@marcventures.com.ph
							String comp_name = "";
							String first_name = ul.getFirst_name();
							String last_name = ul.getLast_name();
							
							String mobile_no = ul.getMobile_no(); 
							
							Request nr = rMngr.getRequest(r.getId());
							
							Integer verify_code = nr.getVerify_code();
							Integer id = nr.getId();
							System.out.println("verify_code "+verify_code);
							System.out.println("ADMIN_MOBILE_NO "+ADMIN_MOBILE_NO);
							
	
	
							//HOST_NAME = (String)req.getSession().getAttribute("HOST_NAME");
							//PROTOCOL = (String)req.getSession().getAttribute("PROTOCOL");
							
							System.out.println("HOST_NAME PROTOCOL "+HOST_NAME+" "+PROTOCOL);
							
							//String login_link = PROTOCOL+"://"+HOST_NAME+"/MV/controller?a=cp&ak="+access_key;
							String login_link = "";
							System.out.println("login_link "+login_link);
							
							RunnableEmailManager R4 = new RunnableEmailManager(name, SERVER_DIRECTORY, email_add_to, email_add_cc, comp_name, first_name, last_name, login_link, mobile_no , "", verify_code, ADMIN_MOBILE_NO, id);
							R4.start();
							
	
							req.setAttribute("msgType", "CallOut");
							req.setAttribute("msg", "New request created.");
						}else{
							req.setAttribute("msgType", "Alert");
							req.setAttribute("msg", "Please call you system administrator.");
						}
						
						r = rMngr.getRequest(r.getId());
						
						req.setAttribute("requestCur", r);
						
					page ="request.jsp";
					
				}else if("saveNewUser".equals(action)){
					
					System.out.println("User save new page.");
					
					String email_add = req.getParameter("email_add");
					String pass_word = req.getParameter("pass_word");
					String first_name = req.getParameter("first_name");
					String last_name = req.getParameter("last_name");
					String mobile_no = req.getParameter("mobile_no");
					
					
					String status = "New";
					
					
					
					
					Integer role_id = Integer.valueOf(req.getParameter("role_id"));
					Boolean active =  true;
					
					System.out.println("email_add "+email_add);
					System.out.println("pass_word "+pass_word);
					System.out.println("first_name "+first_name);
					System.out.println("last_name "+last_name);
					
					System.out.println("mobile_no "+mobile_no);
					
					
					System.out.println("active "+active);
					System.out.println("role_id "+role_id);
					
					
					UserLogin ulCurUsr = (UserLogin)req.getSession().getAttribute("userLogin_"+sid);
					
					Integer created_by = ulCurUsr.getId();
					
					UserLoginManager ulMngr = new UserLoginManager(req, res);
					
					UserLogin ulRec = ulMngr.getUserLogin(email_add);
	
					UserLogin ul = null;
					if(ulRec!=null){
						
						System.out.println("User email already exists.");
						
						req.setAttribute("msgType", "Alert");
						req.setAttribute("msg", "Please input new email address.");
						
						ul = ulMngr.getUserLogin(ulRec.getId());
						
						req.setAttribute("userLogin", ul);
						
					}else{
						ul = new UserLogin();
						ul = ulMngr.createUserLogin(email_add, pass_word, first_name, last_name, active, mobile_no, created_by, role_id, status);
						if(ul!=null){
							req.setAttribute("msgType", "CallOut");
							req.setAttribute("msg", "New user created.");
							
							LoginManager liMngr = new LoginManager(req,res);
							//HashMap<Integer,UserLogin> ulhm = liMngr.getUserLoginHM(); 
							//req.getSession().setAttribute("userLoginHM", ulhm);
	
						}else{
							req.setAttribute("msgType", "Alert");
							req.setAttribute("msg", "Please call you system administrator.");
						}
						
						ul = ulMngr.getUserLogin(ul.getId());
						
						req.setAttribute("userLogin", ul);
						
						LoginManager liMngr = new LoginManager(req, res);
						
						//HashMap<Integer,UserRoleLogin> urlhm = liMngr.getUerRoleLoginHM();
						//HashMap<Integer,RoleLogin> rlhm = liMngr.getRoleLoginHM();
						
						System.out.println("ul.getId() "+ul.getId());
										
						//UserRoleLogin url = urlhm.get(ul.getId());
						
						//System.out.println("url.getRole_id() "+url.getRole_id());
						
						//System.out.println("rlhm "+rlhm.size());
	
						//RoleLogin rl = rlhm.get(url.getRole_id());
						
						//req.setAttribute("userRoleLoginId_userLogin", url.getRole_id());
						//req.setAttribute("userRoleLoginName_userLogin", rl.getRole_name());
	
						//System.out.println("userRoleLoginId_userLogin : "+url.getRole_id());
						//System.out.println("userRoleLoginName_userLogin : "+rl.getRole_name());
	
					}
	
					page ="user.jsp";
	
				}else if("saveNewRole".equals(action)){
					
					System.out.println("Role save new page.");
					
					String email_add = req.getParameter("email_add");
					String role_name = req.getParameter("role_name");
	
					System.out.println("email_add "+email_add);
					System.out.println("role_name "+role_name);
					
					UserLogin ulCurUsr = (UserLogin)req.getSession().getAttribute("userLogin_"+sid);
					
					Integer created_by = ulCurUsr.getId();
					
					RoleLoginManager rlMngr = new RoleLoginManager(req, res);
	
					RoleLogin rl = null;
	
						rl = rlMngr.createRoleLogin(role_name, created_by);
						if(rl!=null){
							req.setAttribute("msgType", "CallOut");
							req.setAttribute("msg", "New role created.");
						}else{
							req.setAttribute("msgType", "Alert");
							req.setAttribute("msg", "Please call you system administrator.");
						}
						
						rl = rlMngr.getRoleLogin(rl.getId());
						
						req.setAttribute("roleLogin", rl);
						
					page ="role.jsp";
	
				}else if("saveRole".equals(action)){
					
					System.out.println("Role save page.");
					
					Integer roleLoginId = Integer.valueOf(req.getParameter("roleLoginId")) ;
					String role_name = req.getParameter("role_name");
					
					System.out.println("roleLoginId "+roleLoginId);
					System.out.println("role_name "+role_name);
					
	
					UserLogin ulCurUsr = (UserLogin)req.getSession().getAttribute("userLogin_"+sid);
					
					Integer updated_by = ulCurUsr.getId();
					
					RoleLoginManager rlMngr = new RoleLoginManager(req, res);
	
					int i = rlMngr.updateRoleLogin(roleLoginId, role_name, updated_by);
					
					if(i==1){
						req.setAttribute("msgType", "CallOut");
						req.setAttribute("msg", "Role updated.");
					}else{
						req.setAttribute("msgType", "Alert");
						req.setAttribute("msg", "Please call you system administrator.");
					}
					
					RoleLogin rl = rlMngr.getRoleLogin(roleLoginId);
					
					System.out.println("rl : "+rl);
					
					req.setAttribute("roleLogin", rl);
					
					page ="role.jsp";
					
				
	
					
				}else if("saveUser".equals(action)){
					
					System.out.println("User save page.");
					
					Integer userLoginId = Integer.valueOf(req.getParameter("userLoginId")) ;
					String email_add = req.getParameter("email_add");
					String pass_word = req.getParameter("pass_word");
					String first_name = req.getParameter("first_name");
					String last_name = req.getParameter("last_name");
					String mobile_no = req.getParameter("mobile_no");
					
					
					Boolean active =  Boolean.valueOf(req.getParameter("active"));
					Integer role_id = Integer.valueOf(req.getParameter("role_id"));
					
					System.out.println("userLoginId "+userLoginId);
					System.out.println("email_add "+email_add);
					System.out.println("pass_word "+pass_word);
					System.out.println("first_name "+first_name);
					System.out.println("last_name "+last_name);
					System.out.println("active "+active);
					//String sid = req.getParameter("sid");
					UserLogin ulCurUsr = (UserLogin)req.getSession().getAttribute("userLogin_"+sid);
					
					Integer updated_by = ulCurUsr.getId();
					
					UserLoginManager ulMngr = new UserLoginManager(req, res);
	
					int i = ulMngr.updateUserLogin(userLoginId, email_add, pass_word, first_name, last_name, active, mobile_no, updated_by, role_id);
					
					if(i==1){
						req.setAttribute("msgType", "CallOut");
						req.setAttribute("msg", "User updated.");
						
						LoginManager liMngr = new LoginManager(req,res);
						//HashMap<Integer,UserLogin> ulhm = liMngr.getUserLoginHM(); 
						//req.getSession().setAttribute("userLoginHM", ulhm);
						
					}else{
						req.setAttribute("msgType", "Alert");
						req.setAttribute("msg", "Please call you system administrator.");
					}
					
					UserLogin ul = ulMngr.getUserLogin(userLoginId);
					
					System.out.println("ul : "+ul);
					
					req.setAttribute("userLogin", ul);
	
					LoginManager liMngr = new LoginManager(req, res);
					
					//HashMap<Integer,UserRoleLogin> urlhm = liMngr.getUerRoleLoginHM();
					//HashMap<Integer,RoleLogin> rlhm = liMngr.getRoleLoginHM();
									
					//UserRoleLogin url = urlhm.get(ul.getId());
	
					//RoleLogin rl = rlhm.get(url.getRole_id());
					
					//req.setAttribute("userRoleLoginId_userLogin", url.getRole_id());
					//req.setAttribute("userRoleLoginName_userLogin", rl.getRole_name());
	
					//System.out.println("userRoleLoginId_userLogin : "+url.getRole_id());
					//System.out.println("userRoleLoginName_userLogin : "+rl.getRole_name());
					
					page ="user.jsp";
					
				}else if("saveRequest".equals(action)){
					
					System.out.println("Request save page.");
					
					Integer requestId = Integer.valueOf(req.getParameter("requestId"));
					Integer user_id = Integer.valueOf(req.getParameter("user_id"));
					Integer status = Integer.valueOf(req.getParameter("status"));
					
					System.out.println("requestId "+requestId);
					System.out.println("status "+status);
	
					UserLogin ulCurUsr = (UserLogin)req.getSession().getAttribute("userLogin_"+sid);
					
					Integer updated_by = ulCurUsr.getId();
					
					RequestManager rMngr = new RequestManager(req, res);
	
					int i = rMngr.updateRequest(requestId, user_id, status, updated_by);
					
					if(i==1){
						req.setAttribute("msgType", "CallOut");
						req.setAttribute("msg", "Request updated.");
	
					}else{
						req.setAttribute("msgType", "Alert");
						req.setAttribute("msg", "Please call you system administrator.");
					}
					
					Request r = rMngr.getRequest(requestId);
					
					System.out.println("r : "+r);
					
					req.setAttribute("requestCur", r);
					
					page ="request.jsp";
					
				}else if("processAction".equals(action)){
					
					System.out.println("processAction Dashboard page.");
					
					UserLogin ulCurUsr = (UserLogin)req.getSession().getAttribute("userLogin_"+sid);
					
					Integer updated_by = ulCurUsr.getId();
					
					
					Integer requestId = Integer.valueOf(req.getParameter("requestId"));
					Integer status_id = Integer.valueOf(req.getParameter("status_id"));
					//respond = req.getParameter("changePassword");
					
					Integer status_id_approved = 2;
					Integer status_id_approved_to_override = 9;
					Integer status_id_approved_processing = 10;
					Integer status_id_approved_to_override_processing = 11;
					
					RequestManager rMngr = new RequestManager(req,res);
	
					Request r = rMngr.getRequest(requestId);
					
					if(r!=null){
						
						if(status_id.equals(status_id_approved)){
							status_id = status_id_approved_processing;
						}else if(status_id.equals(status_id_approved_to_override)){
							status_id = status_id_approved_to_override_processing;
						}
	
						int i = rMngr.updateRequest(r.getId(), r.getUser_id(), status_id, updated_by);
						
						if(i > 0){
							/*
		                    DirectoryQuickstart d = new  DirectoryQuickstart();
		                    String[] args = {""};
		                    d.main(null);
							*/
						}
						
					}
					
					
					LoginManager liMngr = new LoginManager(req, res);
	/*
					HashMap<Integer,UserRoleLogin> urlhm = liMngr.getUerRoleLoginHM();
					HashMap<Integer,RoleLogin> rlhm = liMngr.getRoleLoginHM();
	
					req.setAttribute("userRoleLogin", urlhm);
					req.setAttribute("roleLogin", rlhm);
					*/
					RequestManager rManager = new RequestManager(req, res);
					
					List<Request> requestList = rManager.getRequestListLatest();
					
					req.setAttribute("requestList", requestList);
					
					page ="dashboard.jsp";
					
				}else if("processActionSMS".equals(action)){
					
					System.out.println("processActionSMS Dashboard page.");
					
					UserLogin ulCurUsr = (UserLogin)req.getSession().getAttribute("userLogin_"+sid);
					
					Integer updated_by = ulCurUsr.getId();
					
					
					Integer requestId = Integer.valueOf(req.getParameter("requestId"));
					Integer status_id = Integer.valueOf(req.getParameter("status_id"));
					//respond = req.getParameter("changePassword");
					
					Integer status_id_approved = 2;
					Integer status_id_approved_to_override = 9;
					Integer status_id_approved_processing = 10;
					Integer status_id_approved_to_override_processing = 11;
					
					RequestManager rMngr = new RequestManager(req,res);
	
					Request r = rMngr.getRequest(requestId);
					
					if(r!=null){
						
						if(status_id.equals(status_id_approved)){
							status_id = status_id_approved_processing;
						}else if(status_id.equals(status_id_approved_to_override)){
							status_id = status_id_approved_to_override_processing;
						}
	
						int i = rMngr.updateRequest(r.getId(), r.getUser_id(), status_id, updated_by);
						
						if(i > 0){
							/*
		                    DirectoryQuickstart d = new  DirectoryQuickstart();
		                    String[] args = {""};
		                    d.main(null);
							*/
						}
						
					}
					
					
					LoginManager liMngr = new LoginManager(req, res);
	/*
					HashMap<Integer,UserRoleLogin> urlhm = liMngr.getUerRoleLoginHM();
					HashMap<Integer,RoleLogin> rlhm = liMngr.getRoleLoginHM();
	
					req.setAttribute("userRoleLogin", urlhm);
					req.setAttribute("roleLogin", rlhm);
					*/
					RequestManager rManager = new RequestManager(req, res);
					
					List<Request> requestList = rManager.getRequestListLatest();
					
					req.setAttribute("requestList", requestList);
					
					page ="dashboard.jsp";
					
					
				}else{
					
					req.setAttribute("msgType", "Alert");
					req.setAttribute("msg", "Please Sign In.");
						
					page ="login.jsp";
					
				}
			}
			
			}else{
				
				if("cp".equals(action)){
					
					String msg = "";
					
					RequestManager rMngr = new RequestManager(req,res);
					
					//Request r = rMngr.getRequestByAccessKey(access_key);
					Request r = null;
					String respond = "";
					if(r!=null){
						
						Integer status_id_pending_approval = 1;
						
						if(!status_id_pending_approval.equals(r.getStatus())){
							
							msg = "You have already responded to this request. No action is done.";
							
						}else{
							
							if("y".equals(respond)){
								respond = "Yes";
								
								msg = "You have successfully responded "+respond+".";
								
								Integer status_id_approved = 2;
								
								rMngr.updateRequest(r.getId(), r.getUser_id(), status_id_approved, r.getUser_id());
								
							}else if("n".equals(respond)){
								respond = "No";
								
								msg = "You have successfully responded "+respond+".";
								
								Integer status_id_approved = 3;
								
								rMngr.updateRequest(r.getId(), r.getUser_id(), status_id_approved, r.getUser_id());
								
							}else{
								
								msg = "Access Denied.";
							}
							
							
						}
						
						
					}else{
						msg = "Access Denied.";
					}
	
					req.setAttribute("msg", msg);
					
					page ="request-msg.jsp";
					
					
				}else{
					
					if(!manager.equals("get")){
						
						req.setAttribute("msgType", "Alert");
						req.setAttribute("msg", "Please Sign In.");
							
						page ="login.jsp";
						
					}					

				}
	
	
			}
		
		if(manager.equals("") && action.equals("")){
			page = "index.jsp";
		}
		
		System.out.println("");
		System.out.println("Controller - end");
		
		System.out.println("page : "+page);

		RequestDispatcher rd = req.getRequestDispatcher(page);
		rd.forward(req, res);
		
		
	}
	
}


