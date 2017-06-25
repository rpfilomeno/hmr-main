package bizoncloudone.com.manager;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bizoncloudone.com.bean.RoleLogin;
import bizoncloudone.com.bean.Snapshot;
import bizoncloudone.com.bean.UserLogin;
import bizoncloudone.com.bean.UserRoleLogin;
import bizoncloudone.com.dao.RequestDao;
import bizoncloudone.com.dao.RoleLoginDao;
import bizoncloudone.com.dao.SnapshotDao;
import bizoncloudone.com.dao.UserLoginDao;
import bizoncloudone.com.dao.UserRoleLoginDao;
import hmr.com.bean.User;

public class LoginManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	String emailAdd = null;
	
	public LoginManager(){}
	
	public LoginManager(HttpServletRequest req, HttpServletResponse res, String emailAdd){
		this.req = req;
		this.res = res;
		this.emailAdd = emailAdd;
	}
	
	public LoginManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public UserLogin getUserLogin(String emailAdd){
		
		String passWord = req.getParameter("passWord");
		
		UserLoginDao uld = new UserLoginDao();

		UserLogin ul = uld.getUerLogin(emailAdd, passWord);
		
		return ul;
		
	}
	
	//public User getUser(String userId, String pw){

		//UserLoginDao uld = new UserDao();

		//UserLogin ul = uld.getUerLogin(userId, pw);
		
		//return ul;
		
	//}
	
	
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
	
	public HashMap<Integer,UserLogin> getUserLoginHM(){

		UserLoginDao uld = new UserLoginDao();

		HashMap<Integer,UserLogin> hm = uld.getUserLoginHM();
		
		return hm;
		
	}
	
}
