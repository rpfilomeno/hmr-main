package hmr.com.manager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hmr.com.bean.Lov;
import hmr.com.dao.LovDao;
import hmr.com.dao.UserDao;


public class LovManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;

	public LovManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public LovManager(){
		this.req = req;
		this.res = res;
	}
	
	public String doLovManager(){
		String page = null;
		String action = req.getParameter("action")!=null ? req.getParameter("action") : "";
		
		if(action.equals("login")){
			
		}
		
		return page;
	}
	
	public HashMap<String, Object> doProcess(){
		HashMap<String, Object> hm = new HashMap<String, Object>();

			HashMap<Integer,Lov> paramLovHM = null;
			try {
				paramLovHM = getLovHM("SYS-BRANCH");
				
				req.setAttribute("sysBranchLov", paramLovHM);
				req.getSession().setAttribute("sysBranchLov", paramLovHM);
				hm.put("sysBranchLov", paramLovHM);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		return hm;
	}
	
	
	public HashMap<Integer,Lov> getLovHM() throws SQLException{

		LovDao ld = new LovDao();

		HashMap<Integer,Lov> paramLovHM = ld.getLovHM();
		
		return paramLovHM;
		
	}
	
	public HashMap<Integer,Lov> getLovHM(String group) throws SQLException{

		LovDao ld = new LovDao();

		HashMap<Integer,Lov> paramLovHM = ld.getLovHM(group);
		
		return paramLovHM;
		
	}
	
	public List<Lov> getLovList(String key_cd) throws SQLException{

		LovDao ld = new LovDao();

		List<Lov> paramLovList = ld.getLovList(key_cd);
		
		return paramLovList;
		
	}
	
	
	
	public int updateLovListValue(String key_cd, Integer id, String newValue, Integer user_id) throws SQLException{
		int i = 0;
		
		LovDao ld = new LovDao();
		
		List<Lov> lovList = getLovList(key_cd);
		
		for(Lov lov : lovList){
			
			i = ld.updateLovListValue(lov.getId(), newValue, user_id);
			
		}
		
		
		return i;
		
	}
	
}
