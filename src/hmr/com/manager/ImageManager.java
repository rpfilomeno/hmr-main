package hmr.com.manager;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hmr.com.bean.Image;
import hmr.com.bean.Lov;
import hmr.com.bean.User;
import hmr.com.dao.ImageDao;
import hmr.com.dao.UserDao;


public class ImageManager {

	HttpServletRequest req = null;
	HttpServletResponse res = null;
	//String emailAdd = null;
	
	public static final DateFormat INPUT_DATE_FMT = new SimpleDateFormat("yyyy/MM/dd HH:mm"); 
	
	public ImageManager(){}
	/*
	public ImageManager(HttpServletRequest req, HttpServletResponse res, String emailAdd){
		this.req = req;
		this.res = res;
	}
	*/
	public ImageManager(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
	}
	
	public String doImageManager(File file, String action, BigDecimal imageId_wip){
		String page = "index.jsp";
		
		if(action.equals("saveImageImage")){
			
			Image i = new Image();
			
			i = updateImageImage(file,1,imageId_wip);

			if(i!=null){
				//i = getImageById(i.getId());
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Image images updated succeessful.");
			}else{
				//i = getImageById(imageId_wip);
			}
			
			
			page = "image.jsp";
		}
		
		return page;
	}
	
	public String doImageManager(){
		String page = null;
		
		
		String action = req.getParameter("action")!=null ? req.getParameter("action") : (String)req.getSession().getAttribute("action");
		//String file_name = "";
		Integer user_id = 0;
		BigDecimal imageId_wip = new BigDecimal(0);
		//String manager = "";
		//String userId = "";
		
		System.out.println("Paramerters doImageManager - start");
		System.out.println("action : "+action);
		try{
			System.out.println("imageId_wip Session : "+Integer.valueOf(req.getSession().getAttribute("imageId-wip").toString()) );
		}catch(Exception exx){}
		
		System.out.println("Paramerters doImageManager - end");
		System.out.println("");
		
		
		if("imageList".equals(action)){
			
			System.out.println("imageList");

			List<Image> iList = getImageList();
			
			req.setAttribute("imageList", iList);

			page ="image-list.jsp";
		
		}else if("createImage".equals(action)){
			
			System.out.println("createImage");
			
			UserDao ud = new UserDao();
			
			List<User> userBidderList = ud.getUserListByRole(2);
			
			setLovValuesCategoryLevel(req, res);

			req.setAttribute("userBidderList", userBidderList);

			page ="image-create.jsp";
		
		}else if("saveImage".equals(action)){
			
			System.out.println("saveImage");

			user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : Integer.valueOf((String)req.getSession().getAttribute("user-id"));

			BigDecimal auction_id = !req.getParameter("auction_id").equals("") ? new BigDecimal(req.getParameter("auction_id")) : new BigDecimal(0);
			BigDecimal lot_id = !req.getParameter("lot_id").equals("") ? new BigDecimal(req.getParameter("lot_id")) : new BigDecimal(0);
			BigDecimal item_id = !req.getParameter("item_id").equals("") ? new BigDecimal(req.getParameter("item_id")) : new BigDecimal(0);
			Integer active = !req.getParameter("active").equals("") ? Integer.valueOf(req.getParameter("active")) : 0;
			
			File file = null;
			
			Image i = insertImageOnCreate(
					    auction_id,
					    lot_id,
					    item_id,
					    file,
					    active,
						user_id
					);
			
			if(i!=null && i.getId()!=null){
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Image created succeessful.");

				//i = getImageById(i.getId());
				
				UserManager uMngr = new UserManager(req, res);
				
				HashMap<Integer,User> bidderUserHM = null;
				List<User> bidderUserList = null;
				
				if(req.getSession().getAttribute("BIDDER-USER-HM")==null ){
					try {
						bidderUserHM = uMngr.getUserHMByRole(2);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					req.getSession().setAttribute("BIDDER-USER-HM", bidderUserHM);
					req.setAttribute("BIDDER-USER-HM", bidderUserHM);
				}else{
					bidderUserHM = (HashMap<Integer,User>)req.getSession().getAttribute("BIDDER-USER-HM");
					req.setAttribute("BIDDER-USER-HM", bidderUserHM);
				}
				
				
				if(req.getSession().getAttribute("BIDDER-USER-LIST")==null ){
					try {
						bidderUserList = uMngr.getUserListByRole(2);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					req.getSession().setAttribute("BIDDER-USER-LIST", bidderUserList);
					req.setAttribute("BIDDER-USER-LIST", bidderUserList);
				}else{
					bidderUserList = (List<User>)req.getSession().getAttribute("BIDDER-USER-LIST");
					req.setAttribute("BIDDER-USER-LIST", bidderUserList);
				}
				
				setLovValuesCategoryLevel(req, res);
				
				req.getSession().setAttribute("image", i);
				
				req.setAttribute("image", i);
				
				page ="image.jsp";
				
			}else{
				/*
				i = new Image();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "Image created failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("image", i);
				
				req.setAttribute("image", i);
				
				page ="image-list.jsp";
				*/
			}

			

		
		}else if("viewImage".equals(action)){
			
			System.out.println("viewImage");

			imageId_wip = req.getParameter("imageId_wip")!=null ? new BigDecimal(req.getParameter("imageId_wip")): new BigDecimal(0);
			if(imageId_wip !=  new BigDecimal(0)){
				
				
				UserManager uMngr = new UserManager(req, res);
				
				HashMap<Integer,User> bidderUserHM = null;
				List<User> bidderUserList = null;
				
				if(req.getSession().getAttribute("BIDDER-USER-HM")==null ){
					try {
						bidderUserHM = uMngr.getUserHMByRole(2);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					req.getSession().setAttribute("BIDDER-USER-HM", bidderUserHM);
					req.setAttribute("BIDDER-USER-HM", bidderUserHM);
				}else{
					bidderUserHM = (HashMap<Integer,User>)req.getSession().getAttribute("BIDDER-USER-HM");
					req.setAttribute("BIDDER-USER-HM", bidderUserHM);
				}
				
				
				try {
					bidderUserHM = uMngr.getUserHMByRole(2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				req.getSession().setAttribute("BIDDER-USER-HM", bidderUserHM);
				req.setAttribute("BIDDER-USER-HM", bidderUserHM);
				
				
				//setLovValuesCategoryLevel(req, res);
				
				//Image i = getImageById(imageId_wip);
				Image i = null;
				
				req.getSession().setAttribute("image", i);
				
				req.setAttribute("image", i);
				
				page ="image.jsp";
				
			}

		}else if("updateImage".equals(action)){
			
			System.out.println("updateImage");

			imageId_wip = req.getParameter("imageId_wip")!=null && !req.getParameter("imageId_wip").equals("") ? new BigDecimal(req.getParameter("imageId_wip")): new BigDecimal(req.getSession().getAttribute("imageId-wip").toString());
			
			System.out.println("updateImage : imageId_wip "+imageId_wip);
			if(imageId_wip == new BigDecimal(0)){
				imageId_wip = new BigDecimal(req.getSession().getAttribute("imageId-wip").toString());
			}
			if(imageId_wip !=  new BigDecimal(0)){
				
				//Image i = getImageById(imageId_wip);
				Image i = null;
				
				req.getSession().setAttribute("image", i);
				
				req.setAttribute("image", i);
				
				
				UserDao ud = new UserDao();
				
				List<User> userBidderList = ud.getUserListByRole(2);
				
				setLovValuesCategoryLevel(req, res);

				req.setAttribute("userBidderList", userBidderList);
				
				page ="image-update.jsp";
				
			}

			
		}else if("saveUpdateImage".equals(action)){
			
			System.out.println("saveUpdateImage");

			user_id = req.getParameter("user-id")!=null ? Integer.valueOf(req.getParameter("user-id")) : Integer.valueOf((String)req.getSession().getAttribute("user-id"));
			
			
			System.out.println("req.getParameter(imageId_wip) "+req.getParameter("imageId_wip"));
			imageId_wip = req.getParameter("imageId_wip")!=null ? new BigDecimal(req.getParameter("imageId_wip")) :  new BigDecimal(0);

			BigDecimal auction_id = !req.getParameter("auction_id").equals("") ? new BigDecimal(req.getParameter("auction_id")) : new BigDecimal(0);
			BigDecimal lot_id = !req.getParameter("lot_id").equals("") ? new BigDecimal(req.getParameter("lot_id")) : new BigDecimal(0);
			BigDecimal item_id = !req.getParameter("item_id").equals("") ? new BigDecimal(req.getParameter("item_id")) : new BigDecimal(0);
			Integer active = !req.getParameter("active").equals("") ? Integer.valueOf(req.getParameter("active")) : 0;
			
			File file = null;
			
			//System.out.println("Image Manager buy_price "+buy_price);
			
			
			Image i = updateImageOnUpdate(
					 auction_id,
					 lot_id,
					 item_id,
				     file,
				     active,
				     user_id,
				     imageId_wip
					
					);
			

			
			
			if(i!=null && i.getId()!=null){
				
				//i = getImageById(imageId_wip);
				i = null;
				
				req.setAttribute("msgbgcol", "green");
				req.setAttribute("msgInfo", "Image updated succeessful.");
				
				
				UserDao ud = new UserDao();
				
				List<User> userBidderList = ud.getUserListByRole(2);
				
				setLovValuesCategoryLevel(req, res);

				req.setAttribute("userBidderList", userBidderList);
				
				req.getSession().setAttribute("image", i);
				
				req.setAttribute("image", i);
				
				page ="image.jsp";
				
			}else{
				
				i = new Image();
				req.setAttribute("msgbgcol", "red");
				req.setAttribute("msgInfo", "Image update failed.<br>Please contact your administrator.");
				
				req.getSession().setAttribute("image", i);
				
				req.setAttribute("image", i);
				
				page ="image-list.jsp";
				
			}
			
			
		}else if("saveImageImage".equals(action)){
			
			System.out.println("saveImageImage");
			
			
			/*
			try {
				//processRequestUpload(req,res);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 */
			page= "image.jsp";
		}
		
		
		
		
	
		
		System.out.println("Paramerters doImageManager - page : "+page);
		
		return page;
		
	}
	

	
	/*
	public Image getImage(String imageId){
		
		Image i = new Image();

		ImageDao id = new ImageDao();

		i = id.getImage(imageId);
		
		return i;
		
	}	
	*/
	public Image getImageByAuctionId(BigDecimal auction_id){
		
		Image i = new Image();

		ImageDao id = new ImageDao();

		i = id.getImageByAuctionId(auction_id);

		return i;
		
	}	
	
	public Image getImageByLotId(BigDecimal lot_id){
		
		Image i = new Image();

		ImageDao id = new ImageDao();

		i = id.getImageByLotId(lot_id);

		return i;
		
	}
	
	public Image getImageBytesById(BigDecimal image_id) {
		Image i = new Image();

		ImageDao id = new ImageDao();

		i = id.getImageBytesById(image_id);

		return i;
	}
	
	public Image getImageById(BigDecimal image_id) {
		ImageDao id = new ImageDao();
		return id.getImageById(image_id);
	}
	
	public boolean deleteImage(BigDecimal image_id) {
		ImageDao id = new ImageDao();
		if(id.deleteImage(image_id)>1)return true;
		return false;
	}
	
	public Image getImageByItemId(BigDecimal item_id){
		
		Image i = new Image();

		ImageDao id = new ImageDao();

		i = id.getImageByItemId(item_id);

		return i;
		
	}
	
	public int insertImageInputStream(
			Integer auction_id,
			Integer lot_id,
			Integer item_id,
			InputStream file,
			Integer active,
			Integer user_id
		){
	
		ImageDao id = new ImageDao();
	
		return (id.insertImageOnCreate(
			auction_id,
			lot_id,
			item_id,
			file,
			active,
			user_id
			));
	
}
	
	
	public Image insertImageOnCreate(
				BigDecimal auction_id,
				BigDecimal lot_id,
				BigDecimal item_id,
				File file,
				Integer active,
				Integer user_id
			){
		
		Image i = null;
		
		ImageDao id = new ImageDao(req,res);

		i = id.insertImageOnCreate(
				auction_id,
				lot_id,
				item_id,
				file,
				active,
				user_id
				);
		
		return i;
		
	}
	
	
	public Image updateImageOnUpdate(
	
				BigDecimal auction_id,
				BigDecimal lot_id,
				BigDecimal item_id,
			    File file,
			    Integer active,
			    Integer user_id,
			    BigDecimal imageId_wip
			
			){
		
		Image i = null;
		
		ImageDao id = new ImageDao();
	
		i = id.updateImageOnUpdate(
			    auction_id,
			    lot_id,
			    item_id,
			    file,
			    active,

					user_id,
					imageId_wip
				);
		
		return i;
		
	}
	
	
	
	public Image updateImageImage(
			File file,
			Integer user_id,
			BigDecimal imageId_wip
		){
	
	Image i = null;
	
	ImageDao id = new ImageDao();
/*
	i = id.updateImageImage(
				file,
				user_id,
				imageId_wip
			);
	*/
	return i;
	
}
	/*
	public Image getImageRegistration(String imageId, String vek){
		
		Image i = new Image();

		ImageDao id = new ImageDao();

		i = id.getImageRegistration(imageId, vek);
		
		return i;
		
	}	
	*/
	/*
	public int updatePasswordOnActivation(String imageId, String pw, Integer image_id){
		
		int i = 0;
		
		ImageDao id = new ImageDao();

		i = id.updatePasswordOnActivation(imageId, pw, image_id);
		
		return i;
		
	}
	*/
	public List<Image> getImageList(){
		
		List<Image> iList = new ArrayList<Image>();

		ImageDao id = new ImageDao();

		iList = id.getImageList();
		
		return iList;
		
	}
	
	
	public List<Image> getImageListByAuctionId(BigDecimal auction_id){
		List<Image> iList = new ArrayList<Image>();
		ImageDao id = new ImageDao();
		iList = id.getImageListByAuctionId(auction_id);
		return iList;
		
	}
	public List<Image> getImageListByLotId(BigDecimal lot_id){
		List<Image> iList = new ArrayList<Image>();
		ImageDao id = new ImageDao();
		iList = id.getImageListByLotId(lot_id);
		return iList;
		
	}
	public List<Image> getImageListByItemId(BigDecimal item_id){
		List<Image> iList = new ArrayList<Image>();
		ImageDao id = new ImageDao();
		iList = id.getImageListByAuctionId(item_id);
		return iList;
		
	}
	
	
	
	private void setLovValuesCategoryLevel(HttpServletRequest req, HttpServletResponse res){
		

		LovManager lovMngr = new LovManager(req, res);
		
		HashMap<Integer,Lov> catLev1LovHM = null;
		List<Lov> catLev1LovList = null;
		
		HashMap<Integer,Lov> catLev2LovHM = null;
		List<Lov> catLev2LovList = null;
		
		HashMap<Integer,Lov> catLev3LovHM = null;
		List<Lov> catLev3LovList = null;

		try {
			
			System.out.println("ITEM-CATEGORY-LEVEL-1-HM : " + req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-1-HM"));
			System.out.println("ITEM-CATEGORY-LEVEL-1-LIST : " + req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-1-LIST"));
			System.out.println("ITEM-CATEGORY-LEVEL-2-HM : " + req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-2-HM"));
			System.out.println("ITEM-CATEGORY-LEVEL-2-LIST : " + req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-2-LIST"));
			System.out.println("ITEM-CATEGORY-LEVEL-3-HM : " + req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-3-HM"));
			System.out.println("ITEM-CATEGORY-LEVEL-3-LIST : " + req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-3-LIST"));
			
			if(req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-1-HM")==null ){
				catLev1LovHM = lovMngr.getLovHM("ITEM-CATEGORY-LEVEL-1");
				req.getSession().setAttribute("ITEM-CATEGORY-LEVEL-1-HM", catLev1LovHM);
			}else{
				catLev1LovHM = (HashMap<Integer,Lov>)req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-1-HM");
				req.setAttribute("ITEM-CATEGORY-LEVEL-1-HM", catLev1LovHM);
			}
			
			if(req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-1-LIST")==null ){
				catLev1LovList = lovMngr.getLovList("ITEM-CATEGORY-LEVEL-1");
				req.getSession().setAttribute("ITEM-CATEGORY-LEVEL-1-LIST", catLev1LovList);
			}else{
				catLev1LovList = (List<Lov>)req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-1-LIST");
				req.setAttribute("ITEM-CATEGORY-LEVEL-1-LIST", catLev1LovList);
			}
			
			if(req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-2-HM")==null ){
				catLev2LovHM = lovMngr.getLovHM("ITEM-CATEGORY-LEVEL-2");
				req.getSession().setAttribute("ITEM-CATEGORY-LEVEL-2-HM", catLev2LovHM);
			}else{
				catLev2LovHM = (HashMap<Integer,Lov>)req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-2-HM");
				req.setAttribute("ITEM-CATEGORY-LEVEL-2-HM", catLev2LovHM);
			}
			
			if(req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-2-LIST")==null ){
				catLev2LovList = lovMngr.getLovList("ITEM-CATEGORY-LEVEL-2");
				req.getSession().setAttribute("ITEM-CATEGORY-LEVEL-2-LIST", catLev2LovList);
			}else{
				catLev2LovList = (List<Lov>)req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-2-LIST");
				req.setAttribute("ITEM-CATEGORY-LEVEL-2-LIST", catLev2LovList);
			}
			
			
			if(req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-3-HM")==null ){
				catLev3LovHM = lovMngr.getLovHM("ITEM-CATEGORY-LEVEL-3");
				req.getSession().setAttribute("ITEM-CATEGORY-LEVEL-3-HM", catLev3LovHM);
			}else{
				catLev3LovHM = (HashMap<Integer,Lov>)req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-3-HM");
				req.setAttribute("ITEM-CATEGORY-LEVEL-3-HM", catLev3LovHM);
			}
			
			if(req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-3-LIST")==null ){
				catLev3LovList = lovMngr.getLovList("ITEM-CATEGORY-LEVEL-3");
				req.getSession().setAttribute("ITEM-CATEGORY-LEVEL-3-LIST", catLev3LovList);
			}else{
				catLev3LovList = (List<Lov>)req.getSession().getAttribute("ITEM-CATEGORY-LEVEL-3-LIST");
				req.setAttribute("ITEM-CATEGORY-LEVEL-3-LIST", catLev3LovList);
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void setLovValuesCurrency(HttpServletRequest req, HttpServletResponse res){

		LovManager lovMngr = new LovManager(req, res);
		
		HashMap<Integer,Lov> currencyLovHM = null;
		List<Lov> currencyLovList = null;

		try {
			
			System.out.println("CURRENCY-HM : " + req.getSession().getAttribute("CURRENCY-HM"));
			System.out.println("CURRENCY-LIST : " + req.getSession().getAttribute("CURRENCY-LIST"));

			
			if(req.getSession().getAttribute("CURRENCY-HM")==null ){
				currencyLovHM = lovMngr.getLovHM("CURRENCY");
				req.getSession().setAttribute("CURRENCY-HM", currencyLovHM);
			}else{
				currencyLovHM = (HashMap<Integer,Lov>)req.getSession().getAttribute("CURRENCY-HM");
				req.setAttribute("CURRENCY-HM", currencyLovHM);
			}
			
			if(req.getSession().getAttribute("CURRENCY-LIST")==null ){
				currencyLovList = lovMngr.getLovList("CURRENCY");
				req.getSession().setAttribute("CURRENCY-LIST", currencyLovList);
			}else{
				currencyLovList = (List<Lov>)req.getSession().getAttribute("CURRENCY-LIST");
				req.setAttribute("CURRENCY-LIST", currencyLovList);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
