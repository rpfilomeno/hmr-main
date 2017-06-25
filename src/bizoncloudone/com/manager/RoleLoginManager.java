package bizoncloudone.com.manager;

import java.util.ArrayList;
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

public class RoleLoginManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	String emailAdd = null;
	
	public RoleLoginManager(){}
	
	public RoleLoginManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public List<UserLogin> getUserLoginList(){

		UserLoginDao uld = new UserLoginDao();

		List<UserLogin> ulList = uld.getUerLoginList();
		
		return ulList;
		
	}
	
	public RoleLogin getRoleLogin(Integer id){

		RoleLoginDao rld = new RoleLoginDao();

		RoleLogin rl = rld.getRoleLogin(id);
		
		return rl;
		
	}
	
	public int updateRoleLogin(Integer id, String role_name, Integer updated_by){

		RoleLoginDao rld = new RoleLoginDao();

		int i = rld.updateRoleLogin(id, role_name, updated_by);
		
		return i;
		
	}	
	
	/*
	public UserLogin createUserLogin(String email_add, String pass_word, String first_name, String last_name, Boolean active, Integer created_by){

		UserLoginDao uld = new UserLoginDao();

		UserLogin ul = uld.insertUserLogin(email_add, pass_word, first_name, last_name, active, created_by);
		
		return ul;
		
	}	
	*/
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
	
	public ArrayList<RoleLogin> getRoleLoginList(){

		RoleLoginDao rld = new RoleLoginDao();

		ArrayList<RoleLogin> rl = rld.getRoleLoginList();
		
		return rl;
		
	}
	
	public RoleLogin createRoleLogin(String role_name,  Integer created_by){

		RoleLoginDao rld = new RoleLoginDao();

		RoleLogin rl = rld.insertRoleLogin(role_name, created_by);
		
		return rl;
		
	}	
	
}
