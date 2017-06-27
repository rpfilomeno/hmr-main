package hmr.com.manager;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hmr.com.bean.AuctionRange;
import hmr.com.bean.Lot;
import hmr.com.bean.LotRange;
import hmr.com.bean.User;
import hmr.com.dao.AuctionRangeDao;
import hmr.com.dao.LotDao;
import hmr.com.dao.LotRangeDao;
import hmr.com.dao.UserDao;

public class LotRangeManager {
	HttpServletRequest req = null;
	HttpServletResponse res = null;
	//String emailAdd = null;
	
	public static final DateFormat INPUT_DATE_FMT = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
	
	public LotRangeManager(){}

	public LotRangeManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public String doLotRangeManager(){
		String page = null;
		String action = req.getParameter("action")!=null ? req.getParameter("action") : (String)req.getSession().getAttribute("action");
		//String file_name = "";
		Integer user_id = 0;
		BigDecimal lotRangeId_wip = new BigDecimal(0);
		//String manager = "";
		//String userId = "";
		
		BigDecimal lotId_wip = new BigDecimal(0);
		
		try{
			lotId_wip = req.getParameter("lotId_wip")!=null && !"".equals(req.getParameter("lotId_wip"))  ? new BigDecimal(req.getParameter("lotId_wip")): new BigDecimal(0);
		}catch(Exception exx){}
		
		System.out.println("Paramerters doLotRangeManager - start");
		System.out.println("action : "+action);
		try{
			System.out.println("lotRangeId_wip Session : "+Integer.valueOf(req.getSession().getAttribute("lotRangeId-wip").toString()) );
		}catch(Exception ex){}
		
		System.out.println("Paramerters doLotRangeManager - end");
		System.out.println("");
		
		
		if("lotRangeList".equals(action)){
			System.out.println("lotRangeList");
			BigDecimal lot_id = req.getParameter("lot_id")!=null && !req.getParameter("lot_id").equals("") ? new BigDecimal(req.getParameter("lot_id")) : new BigDecimal(req.getAttribute("lotId_wip").toString());
			if(lot_id==null || lot_id.doubleValue() < 1){
				lot_id = new BigDecimal(req.getParameter("lotId_wip"));		
				LotDao ld = new LotDao();
				Lot l = ld.getLotById(lot_id);
				req.getSession().setAttribute("lot", l);
				req.setAttribute("lot", l);
			}
			System.out.println("lotRangeList lot_id "+lot_id);
			List<LotRange> lList = getLotRangeListByLotId(lot_id);
			req.setAttribute("lotRangeList", lList);
			req.setAttribute("lotId_wip", lot_id);
			page ="lot-range-list.jsp";	
		}else if("createLotRange".equals(action)){
			System.out.println("createLotRange");
			BigDecimal lot_id = req.getParameter("lotId_wip")!=null && !req.getParameter("lotId_wip").equals("") ? new BigDecimal(req.getParameter("lotId_wip")) : new BigDecimal(req.getAttribute("lotId_wip").toString());
			req.setAttribute("lotId_wip", lot_id);
			page ="lot-range-create.jsp";
		}else if("saveLotRange".equals(action)){
			System.out.println("saveAuctionRange");
			user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : Integer.valueOf((String)req.getSession().getAttribute("user-id"));

			BigDecimal lot_id = !req.getParameter("lot_id").equals("") ? new BigDecimal(req.getParameter("lot_id")) : new BigDecimal(0);
			BigDecimal range_start = !req.getParameter("range_start").equals("") ? new BigDecimal(req.getParameter("range_start")) : new BigDecimal(0);
			BigDecimal range_end = !req.getParameter("range_end").equals("") ? new BigDecimal(req.getParameter("range_end")) : new BigDecimal(0);
			BigDecimal increment_amount = !req.getParameter("increment_amount").equals("") ? new BigDecimal(req.getParameter("increment_amount")) : new BigDecimal(0);
			LotRange lr = insertLotRangeOnCreate(
					lot_id,
					range_start,
					range_end,
					increment_amount,
					user_id
				);
			req.setAttribute("lotId_wip", lot_id);
			if(lr!=null && lr.getId()!=null){
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Lot Range created succeessful.");
				lr = getLotRangeById(lr.getId());
				req.getSession().setAttribute("lotRange", lr);
				req.setAttribute("lotRange", lr);
				page ="lot-range.jsp";
			} else {
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "Lot Range not created.");
				page ="lot-range-create.jsp";
			}
		}else if("viewLotRange".equals(action)){
			BigDecimal lotRange_id = !req.getParameter("lotRangeId_wip").equals("") ? new BigDecimal(req.getParameter("lotRangeId_wip")) : new BigDecimal(0);
			BigDecimal lot_id = !req.getParameter("lotId_wip").equals("") ? new BigDecimal(req.getParameter("lotId_wip")) : new BigDecimal(0);
			LotRange lr = getLotRangeById(lotRange_id);
			req.getSession().setAttribute("lotRange", lr);
			req.setAttribute("lotRange", lr);
			req.setAttribute("lotId_wip", lot_id);
			page ="lot-range.jsp";
		}else if("updateLotRange".equals(action)){
			BigDecimal lotRange_id = !req.getParameter("lotRangeId_wip").equals("") ? new BigDecimal(req.getParameter("lotRangeId_wip")) : new BigDecimal(0);
			BigDecimal lot_id = !req.getParameter("lotId_wip").equals("") ? new BigDecimal(req.getParameter("lotId_wip")) : new BigDecimal(0);
			LotRange lr = getLotRangeById(lotRange_id);
			req.getSession().setAttribute("lotRange", lr);
			req.setAttribute("lotRange", lr);
			req.setAttribute("lotId_wip", lot_id);
			page ="lot-range-update.jsp";
		}else if("saveUpdateLotRange".equals(action)){
			System.out.println("saveUpdateLotRange");
			user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : Integer.valueOf((String)req.getSession().getAttribute("user-id"));
			lotRangeId_wip = !req.getParameter("lotRangeId_wip").equals("") ? new BigDecimal(req.getParameter("lotRangeId_wip")) : new BigDecimal(0);
			
			BigDecimal lot_id = !req.getParameter("lot_id").equals("") ? new BigDecimal(req.getParameter("lot_id")) : new BigDecimal(0);
			BigDecimal range_start = !req.getParameter("range_start").equals("") ? new BigDecimal(req.getParameter("range_start")) : new BigDecimal(0);
			BigDecimal range_end = !req.getParameter("range_end").equals("") ? new BigDecimal(req.getParameter("range_end")) : new BigDecimal(0);
			BigDecimal increment_amount = !req.getParameter("increment_amount").equals("") ? new BigDecimal(req.getParameter("increment_amount")) : new BigDecimal(0);
			
			LotRange lr = updateLotRangeOnUpdate(
					lot_id,
					range_start,
					range_end,
					increment_amount,
					user_id,
					lotRangeId_wip
				);
			req.setAttribute("lotId_wip", lot_id);
			if(lr!=null && lr.getId()!=null){
				lr = getLotRangeById(lotRangeId_wip);
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Lot Range updated succeessful.");
				req.getSession().setAttribute("lotRange", lr);
				req.setAttribute("lotRange", lr);
				page ="lot-range.jsp";
			}else{
				lr = new LotRange();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "Lot Range update failed.<br>Please contact your administrator.");
				req.getSession().setAttribute("lot", lr);
				req.setAttribute("lot", lr);
				page ="lot-range-list.jsp";
			}
			
		}else if("deleteLotRange".equals(action)){
			lotRangeId_wip = !req.getParameter("lotRangeId_wip").equals("") ? new BigDecimal(req.getParameter("lotRangeId_wip")) : new BigDecimal(0);
			BigDecimal lot_id = !req.getParameter("lot_id").equals("") ? new BigDecimal(req.getParameter("lot_id")) : new BigDecimal(0);
			if(deleteLotRange(lotRangeId_wip)) {
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Lot Range deleted succeessful.");
			}else {
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "Lot Range update failed.<br>Please contact your administrator.");
			}
			List<LotRange> lList = getLotRangeListByLotId(lot_id);
			req.setAttribute("lotRangeList", lList);
			req.setAttribute("lotId_wip", lot_id);
			page ="lot-range-list.jsp";	
			
		}
		return page;
	}
	
	
	public List<LotRange> getLotRangeListByLotId(BigDecimal lot_id){
		List<LotRange> lList = new ArrayList<LotRange>();
		LotRangeDao ld = new LotRangeDao();
		lList = ld.getLotRangeListByLotId(lot_id);
		return lList;
		
	}
	
	public List<LotRange> getLotRangeListByLotId(BigDecimal lot_id, BigDecimal amount_bid){
		List<LotRange> lList = new ArrayList<LotRange>();
		LotRangeDao ld = new LotRangeDao();
		lList = ld.getLotRangeListByLotId(lot_id);
		return lList;
	}
	
	public LotRange insertLotRangeOnCreate(
			BigDecimal lot_id,
			BigDecimal range_start,
			BigDecimal range_end,
			BigDecimal increment_amount,
			Integer user_id
		){
		LotRange lr = null;
		LotRangeDao ld = new LotRangeDao(req,res);
		lr = ld.insertLotRangeOnCreate(
				lot_id,
				range_start,
				range_end,
				increment_amount,
				user_id
				);
		return lr;
	}
	
	public LotRange getLotRangeById(BigDecimal id){
		LotRange lr = new LotRange();
		LotRangeDao ld = new LotRangeDao();
		lr = ld.getLotRangeById(id);
		return lr;
	}
	
	public boolean deleteLotRange(BigDecimal lotRangeId_wip){
		LotRangeDao ld = new LotRangeDao();
		if(ld.deleteLotRange(lotRangeId_wip) > 0) return true;
		return false;
	}
	
	public LotRange updateLotRangeOnUpdate(
			BigDecimal lot_id,
			BigDecimal range_start,
			BigDecimal range_end,
			BigDecimal increment_amount,
			Integer user_id,
			BigDecimal lotRangeId_wip
		
		){
	
		LotRange lr = null;
		
		LotRangeDao ld = new LotRangeDao();
	
		lr = ld.updateLotRangeOnUpdate(
					lot_id,
					range_start,
					range_end,
					increment_amount,
					user_id,
					lotRangeId_wip
				);
		
		return lr;
	}
	
	public BigDecimal getIncrementAmountByLotId(BigDecimal lot_id, BigDecimal amount_bid) {
		LotRangeDao ld = new LotRangeDao();
		LotRange lr = ld.getLotRangeByLotIdAndBidAmount(lot_id, amount_bid);
		if(lr !=null) {
			return lr.getIncrement_amount();
		}
		return BigDecimal.ZERO;
	}
}
