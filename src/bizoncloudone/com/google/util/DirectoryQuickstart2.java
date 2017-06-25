package bizoncloudone.com.google.util;

import bizoncloudone.com.bean.Request;
import bizoncloudone.com.bean.UserLogin;
import bizoncloudone.com.manager.RequestManager;
import bizoncloudone.com.manager.RunnableEmailManager;
import bizoncloudone.com.manager.UserLoginManager;
import bizoncloudone.com.util.EmailUtils;
import bizoncloudone.com.util.StringUtils;

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

public class DirectoryQuickstart2 {
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
            DirectoryQuickstart2.class.getResourceAsStream("/client_secret.json");
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

    

    public static void main(String[] args) throws IOException {
        // Build a new authorized API client service.
    	Directory service = getDirectoryService();

        //Print the first 10 users in the domain.
        
        Users result = service.users().list()
             .setCustomer("my_customer")
             .setMaxResults(500)
             .setOrderBy("email")
             .execute();
        List<User> users = result.getUsers();
        if (users == null || users.size() == 0) {
            System.out.println("No users found.");
        } else {
            System.out.println("Users:");
            for (User user : users) {
                //System.out.println(user.getName().getGivenName());
                //System.out.println(user.getName().getFamilyName());
                System.out.println(user.getPrimaryEmail());
                //String[] userPhones = (String[]) user.getPhones();
                
                
                //System.out.println("Users phones:" +user.getPhones());
                /*
                if(userPhones!=null){
	                for(int i=0; i < userPhones.length; i++){
	                	System.out.println("Users phones:" +userPhones[i]);
	                }
                }
                */
                //@SuppressWarnings("unchecked")
                /*
				List<UserPhone> upList = (List<UserPhone>)user.getPhones();
                if(upList!=null){
                    for(UserPhone up : upList){
                    	System.out.println("Users phones:" +up.getValue()+" "+up.getPrimary());
                    }
                 */
                }
                

                //UserPhone up = (UserPhone)user.getPhones();
                //System.out.println("Users phones:" +up);
                
            }
        }
        
        
        
        

        
        
    
    
    
    
    public static void main4(String[] args) throws IOException {

    	int[] statusArr = {10,11};
    	
    	RequestManager rMngr = new RequestManager();
    	
    	for(int i=0; i<statusArr.length;i++){
    	   	List<Request> rList = rMngr.getRequestListByStatus(statusArr[i]);
        	
        	for(Request r : rList){
        		System.out.println("Request ID : "+r.getId());
        		System.out.println("Request User ID : "+r.getUser_id());
        		
        		UserLoginManager ulMngr = new UserLoginManager();
        		
        		UserLogin ul = ulMngr.getUserLogin(r.getUser_id());
        		
        		String userKey = ul.getEmail_add();
        		
        		String passWord = "";
        		
        		Boolean email_tag = r.getEmail_tag();
        		
        		System.out.println("UserLogin User Email Address / password : "+userKey);
        		
        		User user = getUserGoogle(userKey);
        		
        		if(user!=null && user.getPrimaryEmail()!=null && userKey.equals(user.getPrimaryEmail())){
        			
    				Integer status_id_approved_processing = 10;
    				Integer status_id_approved_to_override_processing = 11;
    				Integer status_id_pending_approval_processing = 12;
    				Integer status_id_pennding_approval = 1;
    				Integer status_id_approved_success = 7;
    				Integer status_id_approved_to_override_success = 8;
    				Integer status_id = r.getStatus();
    				Integer updated_by  = r.getUpdated_by();

    				r = rMngr.getRequest(r.getId());
    				
    				if(r!=null){
    					
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
    						String email_add_cc = "test-superadmin@marcventures.com.ph";
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
    						
    					   if(status_id.equals(status_id_pending_approval_processing)){
    							

    							
    							
    						}else{
        						
        						//RunnableEmailManager R4 = new RunnableEmailManager(name, SERVER_DIRECTORY, email_add_to, email_add_cc, comp_name, first_name, last_name, login_link, passWord);
        						//R4.start();
        						
    						}

    						
    					}
    					
    					
    				}
        			
        		}
        		
        		
				
        	}
    	}
    	
 
    	
    	

    	
    	//processChangePassword(ul.getEmail_add());
        
    }
    
    
    public static void main2(String[] args) throws IOException {

    	int[] statusArr = {10,11};
    	
    	RequestManager rMngr = new RequestManager();
    	
    	for(int i=0; i<statusArr.length;i++){
    	   	List<Request> rList = rMngr.getRequestListByStatus(statusArr[i]);
        	
        	for(Request r : rList){
        		System.out.println("Request ID : "+r.getId());
        		System.out.println("Request User ID : "+r.getUser_id());
        		
        		UserLoginManager ulMngr = new UserLoginManager();
        		
        		UserLogin ul = ulMngr.getUserLogin(r.getUser_id());
        		
        		String userKey = ul.getEmail_add();
        		
        		String passWord = "";
        		
        		Boolean email_tag = r.getEmail_tag();
        		
        		System.out.println("UserLogin User Email Address / password : "+userKey);
        		
        		User user = getUserGoogle(userKey);
        		
        		if(user!=null && user.getPrimaryEmail()!=null && userKey.equals(user.getPrimaryEmail())){
        			
    				Integer status_id_approved_processing = 10;
    				Integer status_id_approved_to_override_processing = 11;
    				Integer status_id_pending_approval_processing = 12;
    				Integer status_id_pennding_approval = 1;
    				Integer status_id_approved_success = 7;
    				Integer status_id_approved_to_override_success = 8;
    				Integer status_id = r.getStatus();
    				Integer updated_by  = r.getUpdated_by();

    				r = rMngr.getRequest(r.getId());
    				
    				if(r!=null){
    					
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
    						String email_add_cc = "test-superadmin@marcventures.com.ph";
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
    						
    					   if(status_id.equals(status_id_pending_approval_processing)){
    							

    							
    							
    						}else{
        						
        						//RunnableEmailManager R4 = new RunnableEmailManager(name, SERVER_DIRECTORY, email_add_to, email_add_cc, comp_name, first_name, last_name, login_link, passWord);
        						//	R4.start();
        						
    						}

    						
    					}
    					
    					
    				}
        			
        		}
        		
        		
				
        	}
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
