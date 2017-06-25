package bizoncloudone.com.util;

import java.net.*;
import java.io.*;

import bizoncloudone.com.manager.RequestManager;

public class samplecode 
{
  public samplecode()
  {
  }

  public static void main(String[] args) {
      String sMsg = "test sms from api";
      String sURL = "http://gateway.onewaysms.ph:10001/api.aspx?apiusername=APIRVQX0QDSCG&apipassword=APIRVQX0QDSCGF7KI2&mobileno=09273145965&senderid=MHI&languagetype=1&message=" + URLEncoder.encode(sMsg);
      String result = "";  
      HttpURLConnection conn = null;
      try  {
          URL url = new URL(sURL);
          conn = (HttpURLConnection)url.openConnection();          
          conn.setDoOutput(false);                  
          conn.setRequestMethod("GET");          
          conn.connect();
          int iResponseCode = conn.getResponseCode();
          if ( iResponseCode == 200 ) {
            BufferedReader oIn = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
            String sInputLine = "";
            String sResult = "";
            while ((sInputLine = oIn.readLine()) != null) {
              sResult = sResult + sInputLine;
            }
            if (Long.parseLong(sResult) > 0) 
            {
              System.out.println("success - MT ID : " + sResult);       
            }
            else 
            {
              System.out.println("fail - Error code : " + sResult);       
            }
          }
          else {
            System.out.println("fail ");        
          }
      }
      catch (Exception e){ 
        e.printStackTrace();
      }
      finally {
        if (conn != null) {
          conn.disconnect();
        }
      }  
    }
  
  
  
  public static int sendSMSOW(String mobile_no, String sMsg){
	  
	  int i = 0;
	  
      //String sMsg = "test sms from api";
      String sURL = "http://gateway.onewaysms.ph:10001/api.aspx?apiusername=APIRVQX0QDSCG&apipassword=APIRVQX0QDSCGF7KI2&mobileno="+mobile_no+"&senderid=MHI&languagetype=1&message=" + URLEncoder.encode(sMsg);
      
      System.out.println("sURL "+sURL);
      
      String result = "";  
      HttpURLConnection conn = null;
      try  {
          URL url = new URL(sURL);
          conn = (HttpURLConnection)url.openConnection();          
          conn.setDoOutput(false); 
          
          conn.setRequestMethod("GET");          
          conn.connect();
          int iResponseCode = conn.getResponseCode();
          if ( iResponseCode == 200 ) {
            BufferedReader oIn = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
            String sInputLine = "";
            String sResult = "";
            while ((sInputLine = oIn.readLine()) != null) {
              sResult = sResult + sInputLine;
            }
            if (Long.parseLong(sResult) > 0) 
            {
              System.out.println("success - MT ID : " + sResult);       
            }
            else 
            {
              System.out.println("fail - Error code : " + sResult);       
            }
            
            

            
            
            
          }
          else {
            System.out.println("fail ");        
          }
      }
      catch (Exception e){ 
    	  System.out.println("samplecode e "+e.getCause());
        e.printStackTrace();

      }
      finally {
        if (conn != null) {
          conn.disconnect();
        }
      }  
    
	  
	  return i;
  }
  
  
  public static int sendSMSOW_local(String mobile_no, String sMsg){
	  
	  int i = 0;
	  
      //String sMsg = "test sms from api";
      String sURL = "http://gateway.onewaysms.ph:10001/api.aspx?apiusername=APIRVQX0QDSCG&apipassword=APIRVQX0QDSCGF7KI2&mobileno="+mobile_no+"&senderid=MHI&languagetype=1&message=" + URLEncoder.encode(sMsg);
      
      System.out.println("sURL "+sURL);
      
      String result = "";  
      HttpURLConnection conn = null;
      try  {
          URL url = new URL(sURL);
          conn = (HttpURLConnection)url.openConnection();          
          conn.setDoOutput(false);                  
          conn.setRequestMethod("GET");          
          conn.connect();
          int iResponseCode = conn.getResponseCode();
          if ( iResponseCode == 200 ) {
            BufferedReader oIn = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
            String sInputLine = "";
            String sResult = "";
            while ((sInputLine = oIn.readLine()) != null) {
              sResult = sResult + sInputLine;
            }
            if (Long.parseLong(sResult) > 0) 
            {
              System.out.println("success - MT ID : " + sResult);       
            }
            else 
            {
              System.out.println("fail - Error code : " + sResult);       
            }
          }
          else {
            System.out.println("fail ");        
          }
      }
      catch (Exception e){ 
    	  System.out.println("samplecode e "+e.getCause());
        e.printStackTrace();

      }
      finally {
        if (conn != null) {
          conn.disconnect();
        }
      }  
    
	  
	  return i;
  }
}