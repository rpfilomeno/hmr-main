package hmr.com.manager;
 
import java.math.BigDecimal;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import hmr.com.bean.User;
import hmr.com.bean.UserAddress;

import hmr.com.dao.UserAddressDao;
import hmr.com.dao.UserDao;

public class UserAddressManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	//String emailAdd = null;
	
	public static final DateFormat INPUT_DATE_FMT = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
	
	public UserAddressManager(){}
	/*
	public UserManager(HttpServletRequest req, HttpServletResponse res, String emailAdd){
		this.req = req;
		this.res = res;
	}
	*/
	public UserAddressManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public String doUserAddressManager(){
		String page = null;
		String action = req.getParameter("action")!=null ? req.getParameter("action") : "";
		
		
		System.out.println("Paramerters doUserManager - start");
		System.out.println("action : "+action);
		System.out.println("Paramerters doUserManager - end");
		System.out.println("");
		
		
		if("userAddressList".equals(action)) {
			
			System.out.println("userAddressList");

			List<UserAddress> uList = getUserAddressList();
			
			req.setAttribute("userAddressList", uList);

			page ="userAddress-list.jsp";
		
		}
		else if("createUserAddress".equals(action)) {
			List<User> users = getUserList();
			
			req.setAttribute("users", users);
			
			page ="userAddress-create.jsp";
		}
		else if("saveUserAddress".equals(action)){
			System.out.println("saveUserAddress");
			
			Integer user_id = req.getParameter("user")!=null ? Integer.valueOf(req.getParameter("user"))  : 0;
			Integer userId = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id"))  : 0;
			String address_line_1 = req.getParameter("addressLine")!=null ? req.getParameter("addressLine") : "";
			String baranggay = req.getParameter("baranggay")!=null ? req.getParameter("baranggay") : "";
			String city = req.getParameter("city")!=null ? req.getParameter("city") : "";
			String country = req.getParameter("country")!=null ? req.getParameter("country") : "";
			Integer address_type = req.getParameter("addressType")!=null ? Integer.valueOf(req.getParameter("addressType")) : 0; 
			Integer postal_code = new Integer("0");
			if(req.getParameter("postalCode")!=null) 
				postal_code = new Integer(req.getParameter("postalCode"));
			
			UserAddress ua = insertOnCreate(
					user_id,
					address_line_1,
					baranggay,
					city,
					country,
					address_type,
					postal_code,
					userId
				);
			
			if(ua!=null && ua.getId()!=null){
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "User created succeessful.");
				
				req.getSession().setAttribute("userAddress", ua);
				User u = new User();

				UserDao ud = new UserDao();

				u = ud.getUserById(ua.getUser_id());
				
				req.setAttribute("owner", u.getFirst_name() + " " + u.getLast_name());
				
				req.setAttribute("userAddress", ua);
				
				page ="userAddress.jsp";
				
			}else{
				
				ua = new UserAddress();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "User Address created failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("userAddress", ua);
				
				req.setAttribute("userAddress", ua);
				
				page ="userAddress-list.jsp";
				
			}
		}
		else if("viewUserAddress".equals(action)) {
			System.out.println("viewUserAddress");

			Integer userAddressId_wip = req.getParameter("userAddressId_wip")!=null ? Integer.valueOf(req.getParameter("userAddressId_wip")): 0;
			if(userAddressId_wip > 0){
				UserAddress ua = getUserAddressById(userAddressId_wip);
				
				req.setAttribute("userAddress", ua);
				
				User u = new User();

				UserDao ud = new UserDao();

				u = ud.getUserById(ua.getUser_id());
				
				req.setAttribute("owner", u.getFirst_name() + " " + u.getLast_name());
				page ="userAddress.jsp";
			}
		}
		else if("updateUserAddress".equals(action)) {
			System.out.println("update userAddress");
			
			List<User> users = getUserList();
			
			req.setAttribute("users", users);

			Integer userAddressId_wip = req.getParameter("userAddressId_wip")!=null ? Integer.valueOf(req.getParameter("userAddressId_wip")): 0;
			if(userAddressId_wip > 0){
				UserAddress ua = getUserAddressById(userAddressId_wip);
				
				req.setAttribute("userAddress", ua);
				User u = new User();

				UserDao ud = new UserDao();

				u = ud.getUserById(ua.getUser_id());
				
				req.setAttribute("owner", u);

				page ="userAddress-update.jsp";
			}
		}
		else if("saveUpdateUserAddress".equals(action)) {
			System.out.println("save update userAddress");
			
			Integer userAddressId_wip = req.getParameter("userAddressId_wip")!=null ? Integer.valueOf(req.getParameter("userAddressId_wip")): 0;
			
			Integer user_id = req.getParameter("user")!=null ? Integer.valueOf(req.getParameter("user"))  : 0;
			Integer userId = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id"))  : 0;
			String address_line_1 = req.getParameter("addressLine")!=null ? req.getParameter("addressLine") : "";
			String baranggay = req.getParameter("baranggay")!=null ? req.getParameter("baranggay") : "";
			String city = req.getParameter("city")!=null ? req.getParameter("city") : "";
			String country = req.getParameter("country")!=null ? req.getParameter("country") : "";
			Integer address_type = req.getParameter("addressType")!=null ? Integer.valueOf(req.getParameter("addressType")) : 0; 
			Integer postal_code = new Integer("0");
			if(req.getParameter("postalCode")!=null) 
				postal_code = new Integer(req.getParameter("postalCode"));
			
			UserAddress ua = insertOnUpdate(
					user_id,
					address_line_1,
					baranggay,
					city,
					country,
					address_type,
					postal_code,
					userId,
					userAddressId_wip
				);
			
			if(ua!=null && ua.getId()!=null){
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "User created successfully.");
				
				req.getSession().setAttribute("userAddress", ua);
				
				req.setAttribute("userAddress", ua);
				
				User u = new User();

				UserDao ud = new UserDao();

				u = ud.getUserById(ua.getUser_id());
				
				req.setAttribute("owner", u.getFirst_name() + " " + u.getLast_name());
				
				page ="userAddress.jsp";
				
			}else{
				
				ua = new UserAddress();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "User Address create failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("userAddress", ua);
				
				req.setAttribute("userAddress", ua);
				
				page ="userAddress-list.jsp";
				
			}
		}		
		
		System.out.println("Parameters doUserManager - page : " + page);
		
		return page;
		
	}
	
	public UserAddress getUserAddressById(Integer id){
		
		UserAddress ua = new UserAddress();

		UserAddressDao uad = new UserAddressDao();

		ua = uad.getUserAddressById(id);
		
		return ua;
		
	}	
	
	public List<UserAddress> getUserAddressList(){
		
		List<UserAddress> uList = new ArrayList<UserAddress>();

		UserAddressDao uad = new UserAddressDao();

		uList = uad.getUserAddressList();
		
		return uList;
	}
	
	public List<User> getUserList() {
		List<User> users = new ArrayList<User>();
		
		UserDao ud = new UserDao();
		
		users = ud.getUserList();
		
		return users;
	}
	
	public UserAddress insertOnCreate(
			Integer user_id,
			String address_line_1,
			String baranggay,
			String city,
			String country,
			Integer address_type,
			Integer postal_code,
			Integer userId
			) {
		UserAddress ua = null;
		
		UserAddressDao uad = new UserAddressDao();
		ua = uad.insertUserAddressOnCreate(
				user_id,
				address_line_1,
				baranggay,
				city,
				country,
				address_type,
				postal_code,
				userId
			);
		
		return ua;
		
	}
	
	public UserAddress insertOnUpdate (
			Integer user_id,
			String address_line_1,
			String baranggay,
			String city,
			String country,
			Integer address_type,
			Integer postal_code,
			Integer userId,
			Integer userAddressId_wip
			) {
		UserAddress ua = null;
		
		UserAddressDao uad = new UserAddressDao();
		ua = uad.updateUserAddressOnUpdate(
				user_id,
				address_line_1,
				baranggay,
				city,
				country,
				address_type,
				postal_code,
				userId,
				userAddressId_wip
			);
		
		return ua;
		
	}
}
