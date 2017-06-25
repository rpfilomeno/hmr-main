package bizoncloudone.com.manager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bizoncloudone.com.bean.ParamsLov;
import bizoncloudone.com.dao.ParamsLovDao;


public class ParamsLovManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;

	public ParamsLovManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public HashMap<String, Object> doProcess(){
		HashMap<String, Object> hm = new HashMap<String, Object>();

			HashMap<Integer,ParamsLov> paramLovHM = null;
			try {
				paramLovHM = getParamLovHM("SYS-BRANCH");
				
				req.setAttribute("sysBranchParamLov", paramLovHM);
				req.getSession().setAttribute("sysBranchParamLov", paramLovHM);
				hm.put("sysBranchParamLov", paramLovHM);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		return hm;
	}
	
	
	public HashMap<Integer,ParamsLov> getParamLovHM() throws SQLException{

		ParamsLovDao pld = new ParamsLovDao();

		HashMap<Integer,ParamsLov> paramLovHM = pld.getParamsLovHM();
		
		return paramLovHM;
		
	}
	
	public HashMap<Integer,ParamsLov> getParamLovHM(String key_cd) throws SQLException{

		ParamsLovDao pld = new ParamsLovDao();

		HashMap<Integer,ParamsLov> paramLovHM = pld.getParamsLovHM(key_cd);
		
		return paramLovHM;
		
	}
	
	public List<ParamsLov> getParamLovList(String key_cd) throws SQLException{

		ParamsLovDao pld = new ParamsLovDao();

		List<ParamsLov> paramLovList = pld.getParamsLovList(key_cd);
		
		return paramLovList;
		
	}
	
	/*
	public List<Customer> getCustomerRequestorList(){

		CustomerDao uld = new CustomerDao();

		List<Customer> ulList = uld.getCustomerRequestorList();
		
		return ulList;
		
	}
	
	public List<Requestor> getRequestorList(){

		RequestorDao rd = new RequestorDao();

		List<Requestor> rList = rd.getRequestorList();
		
		return rList;
		
	}
	
	
	public Customer getCustomer(Integer id){

		CustomerDao uld = new CustomerDao();

		Customer ul = uld.getUerLogin(id);
		
		return ul;
		
	}
	
	public int updateCustomer(Integer id, String email_add, String pass_word, String first_name, String last_name, Boolean active, Integer updated_by, Integer role_id){

		CustomerDao uld = new CustomerDao();

		int i = uld.updateCustomer(id, email_add, pass_word, first_name, last_name, active, updated_by, role_id);
		
		return i;
		
	}	
	
	public int updateCustomer(Integer id, String pass_word, Integer updated_by){

		CustomerDao uld = new CustomerDao();

		int i = uld.updateCustomer(id, pass_word, updated_by);
		
		return i;
		
	}	
	
	public Customer createCustomer(String email_add, String pass_word, String first_name, String last_name, Boolean active, Integer created_by, Integer role_id){

		CustomerDao uld = new CustomerDao();

		Customer ul = uld.insertCustomer(email_add, pass_word, first_name, last_name, active, created_by, role_id);
		
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
	
	public Customer getCustomer(String email_add){

		CustomerDao uld = new CustomerDao();

		Customer ul = uld.getCustomer(email_add);
		
		return ul;
		
	}
	*/
}
