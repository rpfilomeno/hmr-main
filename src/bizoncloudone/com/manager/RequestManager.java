package bizoncloudone.com.manager;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bizoncloudone.com.bean.RoleLogin;
import bizoncloudone.com.bean.Request;
import bizoncloudone.com.bean.UserRoleLogin;

import bizoncloudone.com.dao.RoleLoginDao;
import bizoncloudone.com.dao.RequestDao;
import bizoncloudone.com.dao.UserRoleLoginDao;

public class RequestManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	String emailAdd = null;
	
	public RequestManager(){}
	
	public RequestManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public List<Request> getRequestList(){

		RequestDao rd = new RequestDao();

		List<Request> ulList = rd.getRequestList();
		
		return ulList;
		
	}
	
	
	
	public List<Request> getRequestListByStatus(Integer status){

		RequestDao rd = new RequestDao();

		List<Request> ulList = rd.getRequestListByStatus(status);
		
		return ulList;
		
	}
	
	
	public List<Request> getRequestListLatest(){

		RequestDao rd = new RequestDao();

		List<Request> ulList = rd.getRequestListLatest();
		
		return ulList;
		
	}
	
	
	public Request getRequest(Integer id){

		RequestDao rd = new RequestDao();

		Request ul = rd.getRequest(id);
		
		return ul;
		
	}
	
	public Request getRequestByAccessKey(String access_key){

		RequestDao rd = new RequestDao();

		Request ul = rd.getRequestByAccessKey(access_key);
		
		return ul;
		
	}
	
	
	public int updateRequest(Integer id, String email_add, String pass_word, String first_name, String last_name, Boolean active, Integer updated_by, Integer role_id){

		RequestDao rd = new RequestDao();

		int i = rd.updateRequest(id, email_add, pass_word, first_name, last_name, active, updated_by, role_id);
		
		return i;
		
	}	
	
	public int updateRequest(Integer id, Integer user_id,  Integer status, Integer updated_by){

		RequestDao rd = new RequestDao();

		int i = rd.updateRequest(id, user_id, status, updated_by);
		
		return i;
		
	}	
	
	
	public int updateRequestSMS(Integer id, String sms,  String cd){

		RequestDao rd = new RequestDao();

		int i = rd.updateRequestSMS(id, sms, cd);
		
		return i;
		
	}	
	
	public Request createRequest(Integer user_id, Integer status_id, String access_key, Integer created_by, Boolean pass_word){

		RequestDao rd = new RequestDao();

		Request r = rd.insertRequest(user_id, status_id, access_key, created_by, pass_word);
		
		return r;
		
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
	
	public Request getRequest(String email_add){

		RequestDao rd = new RequestDao();

		Request ul = rd.getRequest(email_add);
		
		return ul;
		
	}
	
}
