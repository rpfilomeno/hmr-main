package bizoncloudone.com.google.util;

import bizoncloudone.com.properties.PropertiesUtil;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class GoogleApiUtil {

  public static final List<String> SCOPES = Arrays.asList(
		  "https://spreadsheets.google.com/feeds"
  );
  
  /** Application name. */
/*
  private static final String APPLICATION_NAME =
      "BIZONCLOUDONE GOOGLE APPLICATION";
*/
  //private static String BASE_DIR = "";
  
  private static String DATA_STORE_DIR_ = "";
  
  static {
      try {
    	  /*
    	  URL location = GoogleApiUtil.class.getProtectionDomain().getCodeSource().getLocation();
          System.out.println(location.getFile());
    	  */
    	  //HashMap<String, String> propHm = PropertiesUtil.getAllProperties(); 
    	  //DATA_STORE_DIR_ = "/home/bizadmin/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/JJ/data-store-files";
    	  //DATA_STORE_DIR_ = "/home/bizadmin/jvm/apache-tomcat-7.0.57/domains/bizoncloudone.com/JJ";
    	  //DATA_STORE_DIR_ = "/home/bizadmin/jvm/apache-tomcat-7.0.57/domains/bizoncloudone.com/JJ";
    	  //DATA_STORE_DIR_ = "D:\\BIZ\\MaxGroup\\GoogleSheets\\bizoncloudone-google-sheets\\data-store-files";
    	  //DATA_STORE_DIR_ = "D:\\BIZ\\MaxGroup\\GoogleSheets\\bizoncloudone-google-sheets\\data-store-files";
    	  //C:\Program Files\Apache Software Foundation\Tomcat 7.0\webapps\JJ
    	  //PROD
    	  //DATA_STORE_DIR_ = "C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\JJ";
    	  
    	  
    	  //BASE_DIR = propHm.get("data.store.dir");
      } catch (Throwable t) {
          t.printStackTrace();
          System.exit(1);
      }
  }

  /** Directory to store user credentials for this application. */
  private static final java.io.File DATA_STORE_DIR = new java.io.File(DATA_STORE_DIR_);

  /** Global instance of the {@link FileDataStoreFactory}. */
  private static FileDataStoreFactory DATA_STORE_FACTORY;

  /** Global instance of the JSON factory. */
  private static final JsonFactory JSON_FACTORY =
      JacksonFactory.getDefaultInstance();

  /** Global instance of the HTTP transport. */
  private static HttpTransport HTTP_TRANSPORT;

  /** Global instance of the scopes required by this quickstart. */
  static {
      try {
          HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
          DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
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
	  System.out.println(DATA_STORE_DIR_+"/client_secret.json");
	  

	  
	  InputStream in = new FileInputStream(DATA_STORE_DIR_+"/client_secret.json");
	  //InputStream in = GoogleApiUtil.class.getResourceAsStream(DATA_STORE_DIR_+"\\client_secret.json");
	  
	  
	  GoogleClientSecrets clientSecrets =
			  GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

	  
	  
	  // Build flow and trigger user authorization request.
	  GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
					  HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
	  			.setDataStoreFactory(DATA_STORE_FACTORY)
	  			.setAccessType("offline")
	  			.build(); 
	   
	  
	  //String clientId = "207931614760-0uaqt47pb1aeuu77b6jl6g0oaj09foee.apps.googleusercontent.com";
	  //String clientSecret = "r1o5E18QYD4MzuSRlyx_EzS6";
	  
	  // Build flow and trigger user authorization request.
	  //GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
		//			  HTTP_TRANSPORT, JSON_FACTORY, clientId, clientSecret, SCOPES)
	  ////			.setDataStoreFactory(DATA_STORE_FACTORY)
	  	//		.setAccessType("offline")
	  	//		.build();
	  
	  //System.out.println("flow :"+flow.getAuthorizationServerEncodedUrl());
	  //System.out.println("flow :"+flow.getTokenServerEncodedUrl());
	  //GoogleCredential credential = new GoogleCredential().setAccessToken(token.getAccessToken());
	  Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	  System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
	  return credential;
	}
  
  /**
   * Creates an authorized Credential object.
   * @return an authorized Credential object.
   * @throws IOException
   */
  public static Credential authorize(String DATA_STORE_DIR_) throws IOException {
      // Load client secrets.
	  System.out.println(DATA_STORE_DIR_+"/client_secret.json");
	  
	  InputStream in = new FileInputStream(DATA_STORE_DIR_+"/client_secret.json");
	  //InputStream in = GoogleApiUtil.class.getResourceAsStream(DATA_STORE_DIR_+"\\client_secret.json");
	  
	  
	  GoogleClientSecrets clientSecrets =
			  GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

	  
	  
	  // Build flow and trigger user authorization request.
	  GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
					  HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
	  			.setDataStoreFactory(DATA_STORE_FACTORY)
	  			.setAccessType("offline")
	  			.build(); 
	   
	  
	  //String clientId = "207931614760-0uaqt47pb1aeuu77b6jl6g0oaj09foee.apps.googleusercontent.com";
	  //String clientSecret = "r1o5E18QYD4MzuSRlyx_EzS6";
	  
	  // Build flow and trigger user authorization request.
	  //GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
		//			  HTTP_TRANSPORT, JSON_FACTORY, clientId, clientSecret, SCOPES)
	  ////			.setDataStoreFactory(DATA_STORE_FACTORY)
	  	//		.setAccessType("offline")
	  	//		.build();
	  
	  //System.out.println("flow :"+flow.getAuthorizationServerEncodedUrl());
	  //System.out.println("flow :"+flow.getTokenServerEncodedUrl());
	  //GoogleCredential credential = new GoogleCredential().setAccessToken(token.getAccessToken());
	  Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	  System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
	  return credential;
	}

	public static Credential getCredentials(String CRENDENTIALS_DIRECTORY) throws IOException {
		Credential credentials = authorize(CRENDENTIALS_DIRECTORY);
		return credentials;
	}
	
	public static Credential getCredentials() throws IOException {
		Credential credentials = authorize();
		return credentials;
	}
	
	public static void main(String args[]){
		
	}

}