package bizoncloudone.com.google.util;

import bizoncloudone.com.bean.Request;
import bizoncloudone.com.bean.UserLogin;
import bizoncloudone.com.dao.UserDao;
import bizoncloudone.com.manager.RequestManager;
import bizoncloudone.com.manager.RunnableEmailManager;
import bizoncloudone.com.manager.UserLoginManager;
import bizoncloudone.com.util.EmailUtils;
import bizoncloudone.com.util.StringUtils;
import bizoncloudone.com.util.samplecode;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.admin.directory.DirectoryScopes;
import com.google.api.services.admin.directory.model.*;
import com.google.api.services.admin.directory.Directory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class DirectoryQuickstart {
    /** Application name. */
    private static final String APPLICATION_NAME =
        "Directory API Java Quickstart";
    
	private static String SERVER_DIRECTORY_LOCAL = "D:\\eclipse_workspace_BIZ\\MV\\WebContent\\img\\"; 
	//private static String SERVER_DIRECTORY_ONLINE = "/opt/apache-tomcat-7.0.54/webapps/IPCGS/images/";
	private static String SERVER_DIRECTORY_ONLINE = "jvm//apache-tomcat-8.0.20//domains//bizoncloudone.com//MV//";
    
    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/admin-directory_v1-java-quickstart.json");
    
    //private static final java.io.File DATA_STORE_DIR = new java.io.File(SERVER_DIRECTORY_LOCAL+"admin-directory_v1-java-quickstart.json");
    
    

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/admin-directory_v1-java-quickstart.json
     */
    private static final List<String> SCOPES =
        Arrays.asList(DirectoryScopes.ADMIN_DIRECTORY_USER_READONLY, "https://www.googleapis.com/auth/admin.directory.user",
        		"https://www.googleapis.com/auth/apps.groups.settings","https://www.googleapis.com/auth/admin.directory.group",
        		"https://www.googleapis.com/auth/admin.directory.group.member");

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
            
            //DATA_STORE_FACTORY = new Filedat
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
            DirectoryQuickstart.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        
        
    
        
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        
        
        
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Admin SDK Directory client service.
     * @return an authorized Directory client service
     * @throws IOException
     */
    public static Directory getDirectoryService() throws IOException {
        Credential credential = authorize();
        return new Directory.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    

    public static void main1(String[] args) throws IOException {


    	Directory service = getDirectoryService();

    	
        // Print the first 10 users in the domain.
        
        Users result = service.users().list()
             .setCustomer("my_customer")
             .setMaxResults(10)
             .setOrderBy("email")
             .execute();
        List<User> users = result.getUsers();
        if (users == null || users.size() == 0) {
            System.out.println("No users found.");
        } else {
            System.out.println("Users:");
            for (User user : users) {
                System.out.println(user.getName().getFullName());
            }
        }
        
        /*
    	Directory service = getDirectoryService();
        
        
        User user = new User();
        // populate are the required fields only
        UserName name = new UserName();
        name.setFamilyName("One");
        name.setGivenName("Bizoncloud One");
        user.setName(name);
        //user.setPassword("Welcome1077");
        //user.setPrimaryEmail("test-user@marcventures.com.ph");
        //DirectoryScopes.ADMIN_DIRECTORY_USER;
        // requires DirectoryScopes.ADMIN_DIRECTORY_USER scope  
        //user = directory.users().insert(user).execute();
        String userKey = "test-user@marcventures.com.ph";
        user = service.users().update(userKey, user).execute();
        */
        
    }
    
    
    
    public static void main(String[] args) throws IOException {

    	int[] statusArr = {10,11,1,7};
    	
    	RequestManager rMngr = new RequestManager();
    	
    	for(int i=0; i<statusArr.length;i++){
    		
    	   	List<Request> rList = rMngr.getRequestListByStatus(statusArr[i]);
        	
        	for(Request r : rList){
        		System.out.println("Request ID : "+r.getId());
        		System.out.println("Request User ID : "+r.getUser_id());
        		
        		UserLoginManager ulMngr = new UserLoginManager();
        		
        		UserLogin ul = ulMngr.getUserLogin(r.getUser_id());
        		
        		String userKey = ul.getEmail_add();
        		
        		String mobile_no = ul.getMobile_no();
        		
        		String passWord = "";
        		
        		Boolean email_tag = r.getEmail_tag();
        		
        		String sms_code = r.getSms_code();
        		String sms_msg = r.getSms_msg();
        		String sms_vc = String.valueOf(r.getVerify_code());
        		
        		if(sms_code.equalsIgnoreCase("0") && sms_msg == null){
        			sms_msg = "MARCVENTURES Google Admin would like to change your Google Password. Please see your google mail for details or text "+sms_vc+" to 0998-8487290.";
        		}
        		
        		Integer id = r.getId();
        		
        		
        		System.out.println("UserLogin User Email Address / password : "+userKey);
        		
        		//User user = getUserGoogle(userKey);
        		
        		//if(user!=null && user.getPrimaryEmail()!=null && userKey.equals(user.getPrimaryEmail())){
        			
    				Integer status_id_approved_processing = 10;
    				Integer status_id_approved_to_override_processing = 11;
    				Integer status_id_pending_approval_processing = 12;
    				Integer status_id_pennding_approval = 1;
    				Integer status_id_approved_success = 7;
    				Integer status_id_approved_to_override_success = 8;
    				Integer status_id = r.getStatus();
    				Integer updated_by  = r.getUpdated_by();
    				Integer status_id_pending_approval = 1;

    				r = rMngr.getRequest(r.getId());
    				
    				if(r!=null){
    					
    					System.out.println("sms_code "+sms_code);
    					System.out.println("sms_msg "+sms_msg);
    					
    					if(status_id.equals(status_id_approved_success) && sms_code.length() <=5){
    						
    						samplecode sms = new samplecode();
    						
    						sms.sendSMSOW(mobile_no, sms_msg);
    						
    			            rMngr = new RequestManager();
    						rMngr.updateRequestSMS(id, "000000", "sms_code");    						
    						
    					}else if(status_id.equals(status_id_pending_approval) && sms_code.length() <=1){
    						
    						
    						samplecode sms = new samplecode();
    						
    						System.out.println("mobile_no "+mobile_no+ " sms_msg : "+sms_msg);
    						
    						sms.sendSMSOW(mobile_no, sms_msg);
    						
    			            rMngr = new RequestManager();
    						rMngr.updateRequestSMS(id, "0000", "sms_code");
    						
    					}else if ((status_id.equals(status_id_approved_processing) ||
    							status_id.equals(status_id_approved_to_override_processing) ||
    							status_id.equals(status_id_pending_approval_processing)) && sms_code.length() <=4){
    						
    						
    		        	User user = getUserGoogle(userKey);
    		        		
    		        	if(user!=null && user.getPrimaryEmail()!=null && userKey.equals(user.getPrimaryEmail())){	
    						
    					
    					
    					if(status_id.equals(status_id_approved_processing)){
    						status_id = status_id_approved_success;
    						
    						if(email_tag.booleanValue()==true){
    							passWord = StringUtils.generatePassword();
    							processChangePasswordOverride(userKey, passWord);
    						}else{
    							processChangePassword(userKey);
    						}
    						
    						
    						
    					}else if(status_id.equals(status_id_approved_to_override_processing)){
    						status_id = status_id_approved_to_override_success;
    						
    						passWord = StringUtils.generatePassword();
    						processChangePasswordOverride(userKey, passWord);
    						
    					}else if(status_id.equals(status_id_pending_approval_processing)){
    						status_id = status_id_pennding_approval;
    						
    					}
    					
    					
    		        	

    					int x = rMngr.updateRequest(r.getId(), r.getUser_id(), status_id, updated_by);
    					
    					if(x > 0){

    						String name = "sendEmailChangePassword";
    						
    						if(!"".equals(passWord)){
    							name = "sendEmailChangePasswordwithPassword";
    						}
    						
    						String email_add_to = ul.getEmail_add();
    						String email_add_cc = "customgoogleapplication@marcventures.com.ph";
    						//String email_add_cc = "dakkiboy17@gmail.com";test-admin@marcventures.com.ph
    						String comp_name = String.valueOf(r.getId());
    						String first_name = ul.getFirst_name();
    						String last_name = ul.getLast_name();

    						
    						String HOST_NAME = "";
    						String PROTOCOL = "";
    						String SERVER_DIRECTORY = "D:\\eclipse_workspace_BIZ\\MV\\WebContent\\img\\";
    						
    						//SERVER_DIRECTORY = SERVER_DIRECTORY_ONLINE;

    						String login_link = "";

    						System.out.println("login_link "+login_link);
    						login_link = "https://bizoncloudone.com/MV";
    						
    					   if(status_id.equals(status_id_pending_approval_processing) && sms_code.length() <=4){
    							
    							ulMngr = new UserLoginManager();
    							
    							
    							String access_key = r.getAccess_key();
    							//ul = ulMngr.getUserLogin(user_id);
    							
    							name = "sendEmailRequest";
    							email_add_to = ul.getEmail_add();
    							email_add_cc = "customgoogleapplication@marcventures.com.ph";
    							comp_name = "";
    							first_name = ul.getFirst_name();
    							last_name = ul.getLast_name();
    							
    							System.out.println("HOST_NAME PROTOCOL "+HOST_NAME+" "+PROTOCOL);
    							
    						    login_link = PROTOCOL+"://"+HOST_NAME+"/MV/controller?a=cp&ak="+access_key;

    							System.out.println("login_link "+login_link);
    							
    							RunnableEmailManager R4 = new RunnableEmailManager(name, SERVER_DIRECTORY, email_add_to, email_add_cc, comp_name, first_name, last_name, login_link, "09273145965" ,"");
    							R4.start();
    							
    							
    						}else{
        						
        						RunnableEmailManager R4 = new RunnableEmailManager(name, SERVER_DIRECTORY, email_add_to, email_add_cc, comp_name, first_name, last_name, login_link, passWord, id);
        						R4.start();
        						
        			            rMngr = new RequestManager();
        						rMngr.updateRequestSMS(id, "00000", "sms_code");
        						
        						r = rMngr.getRequest(id);
        						sms_msg = r.getSms_msg();
        						samplecode.sendSMSOW(mobile_no, sms_msg);
        						
        						if("sendEmailChangePasswordwithPassword".equals(name)){
        							name = "sendEmailChangePasswordwithPasswordAdmin";
        							email_add_to = "customgoogleapplication@marcventures.com.ph";
        							email_add_cc = "customgoogleapplication@marcventures.com.ph";
        							RunnableEmailManager R5 = new RunnableEmailManager(name, SERVER_DIRECTORY, email_add_to, email_add_cc, comp_name, first_name, last_name, login_link, passWord, id);
            						R5.start();

            			            rMngr = new RequestManager();
            						rMngr.updateRequestSMS(id, "00000", "sms_code");
            						
            						r = rMngr.getRequest(id);
            						sms_msg = r.getSms_msg();
            						samplecode.sendSMSOW(mobile_no, sms_msg);
            						
            						
        						}
        						
    						}

    						
    					}
    					
    		        	}
    					
    				}
    					
    					
    				}
        			
        		//}
        		
        		
				
        	}
    	}
    	
 
    	
    	
    	UserDao userDao = new UserDao();
    	
    	List<bizoncloudone.com.bean.User> users =  userDao.getNewUserList();
    	
    	for(bizoncloudone.com.bean.User u : users){

            // Build a new authorized API client service.
        	Directory service = getDirectoryService();

        	User user = new User();
        	
        	UserName un = new UserName();
        	
        	un.setGivenName(u.getFirst_name());
        	un.setFamilyName(u.getLast_name());
        	un.setFullName(u.getFirst_name()+" "+u.getLast_name());
        	
        	user.setName(un);
        	
        	user.setPrimaryEmail(u.getEmail_add());
        	user.setPassword("marcventures1!");
        	user.setChangePasswordAtNextLogin(true);
        	
        	service.users().insert(user).execute();

        	userDao.updateUserStatus("Sync", u.getId());

    	}
    	
    	//processChangePassword(ul.getEmail_add());
        
    }
    
    
    public static User getUserGoogle(String userKey) {

    	User user = new User();
        //String userKey = "test-user@marcventures.com.ph";
        
        try {
        	Directory service = getDirectoryService();
            
			user = service.users().get(userKey).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return user;
    }
    
    public static User processChangePassword(String userKey) {

    	User user = new User();
        //String userKey = "test-user@marcventures.com.ph";
        
        try {
        	Directory service = getDirectoryService();
            user.setChangePasswordAtNextLogin(true);
			user = service.users().update(userKey, user).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return user;
    }
    
    public static User processChangePasswordOverride(String userKey, String passWord) {

    	User user = new User();
        //String userKey = "test-user@marcventures.com.ph";
        
        try {
        	Directory service = getDirectoryService();
        	System.out.println("passWord gmail of "+ passWord +"  : "+passWord);
            //user.setChangePasswordAtNextLogin(true);
        	user.setPassword(passWord);
            
			user = service.users().update(userKey, user).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return user;
    }

}
