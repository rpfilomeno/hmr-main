package bizoncloudone.com.manager;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bizoncloudone.com.bean.RoleLogin;
import bizoncloudone.com.bean.UserLogin;
import bizoncloudone.com.bean.UserRoleLogin;
import bizoncloudone.com.dao.RoleLoginDao;
import bizoncloudone.com.dao.UserLoginDao;
import bizoncloudone.com.dao.UserRoleLoginDao;

public class UserLoginManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	String emailAdd = null;
	
	public UserLoginManager(){}
	
	public UserLoginManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public List<UserLogin> getUserLoginList(){

		UserLoginDao uld = new UserLoginDao();

		List<UserLogin> ulList = uld.getUerLoginList();
		
		return ulList;
		
	}
	
	public List<UserLogin> getUserLoginList3(){

		UserLoginDao uld = new UserLoginDao();

		List<UserLogin> ulList = uld.getUerLoginList3();
		
		return ulList;
		
	}
	
	public List<UserLogin> getUserLoginRequestorList(){

		UserLoginDao uld = new UserLoginDao();

		List<UserLogin> ulList = uld.getUserLoginRequestorList();
		
		return ulList;
		
	}
	

	
	
	public UserLogin getUserLogin(Integer id){

		UserLoginDao uld = new UserLoginDao();

		UserLogin ul = uld.getUerLogin(id);
		
		return ul;
		
	}
	
	public int updateUserLogin(Integer id, String email_add, String pass_word, String first_name, String last_name, Boolean active, String mobile_no, Integer updated_by, Integer role_id){

		UserLoginDao uld = new UserLoginDao();

		int i = uld.updateUserLogin(id, email_add, pass_word, first_name, last_name, active, mobile_no, updated_by, role_id);
		
		return i;
		
	}	
	
	public int updateUserLogin(Integer id, String pass_word, Integer updated_by){

		UserLoginDao uld = new UserLoginDao();

		int i = uld.updateUserLogin(id, pass_word, updated_by);
		
		return i;
		
	}	
	
	public UserLogin createUserLogin(String email_add, String pass_word, String first_name, String last_name, Boolean active, String mobile_no, Integer created_by, Integer role_id, String status){

		UserLoginDao uld = new UserLoginDao();

		UserLogin ul = uld.insertUserLogin(email_add, pass_word, first_name, last_name, active, mobile_no, created_by, role_id, status);
		
		return ul;
		
	}	
	
	public HashMap<Integer,UserRoleLogin> getUerRoleLoginHM(){

		UserRoleLoginDao urld = new UserRoleLoginDao();

		HashMap<Integer,UserRoleLogin> hm = urld.getUerRoleLoginHM();
		
		return hm;
		
	}
	
	public HashMap<Integer,RoleLogin> getRoleLoginHM(){

		RoleLoginDao rld = new RoleLoginDao();

		HashMap<Integer,RoleLogin> hm = rld.getRoleLoginHM();
		
		return hm;
		
	}
	
	public UserLogin getUserLogin(String email_add){

		UserLoginDao uld = new UserLoginDao();

		UserLogin ul = uld.getUserLogin(email_add);
		
		return ul;
		
	}
	
}
