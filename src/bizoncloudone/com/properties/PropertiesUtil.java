package bizoncloudone.com.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

public class PropertiesUtil {

	public static HashMap<String, String> getProperties(String path) {
		 HashMap<String, String> hmProp = new  HashMap<String, String>();
		 
			Properties prop = new Properties();
			InputStream input = null;

			try {


				input = new FileInputStream(path);

				prop.load(input);
				
				Iterator<Object> itKey = prop.keySet().iterator();
				Iterator<Object> itVal = prop.values().iterator();
				while(itKey.hasNext()){
					
					String key = (String)itKey.next();
					String val = (String)itVal.next();
					hmProp.put(key, val);
					
				}

			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		 
		 return hmProp;
	}
	
	public static HashMap<String, String> getAllProperties() {
		 HashMap<String, String> hmProp = new  HashMap<String, String>();
		 
			Properties prop = new Properties();
			Properties prop2 = new Properties();
			InputStream input = null;
			InputStream input2 = null;

			try {
				
				//input = new FileInputStream(PropertiesUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"/bizoncloudone.properties");
				//D:\BIZ\MaxGroup\GoogleSheets
				//input = new FileInputStream("D:\\BIZ\\MaxGroup\\GoogleSheets\\bizoncloudone-google-sheets\\configuration-files\\bizoncloudone.properties");
				//input = new FileInputStream("D:\\BIZ\\MaxGroup\\GoogleSheets\\bizoncloudone-google-sheets\\configuration-files\\bizoncloudone.properties");
				//input = new FileInputStream("/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/JJ/configuration-files/bizoncloudone.properties");
				
				
				//prop.load(input);
				
				hmProp.put("base.dir", "D:\\BIZ\\MaxGroup\\GoogleSheets\\bizoncloudone-google-sheets");
				
				String baseDir = prop.getProperty("base.dir");
				
				//input2 = new FileInputStream(baseDir+"/configuration-files"+"/bizoncloudone.properties");
				//prop2.load(input2);
				
				hmProp.put("base.dir", "D:\\BIZ\\MaxGroup\\GoogleSheets\\bizoncloudone-google-sheets");
				hmProp.put("batch.dir", "D:\\BIZ\\MaxGroup\\GoogleSheets\\bizoncloudone-google-sheets/batch-files");
				hmProp.put("config.dir", "D:\\BIZ\\MaxGroup\\GoogleSheets\\bizoncloudone-google-sheets/configuration-files");
				hmProp.put("libraries.dir", "D:\\BIZ\\MaxGroup\\GoogleSheets\\bizoncloudone-google-sheets/libraries");
				hmProp.put("data.store.dir", "D:\\BIZ\\MaxGroup\\GoogleSheets\\bizoncloudone-google-sheets/data-store-files");
				hmProp.put("driver", "com.mysql.jdbc.Driver");
				hmProp.put("url", "jdbc:mysql://mysql3000.mochahost.com:3306/bizadmin_jj");
				hmProp.put("user", "bizadmin_jj");
				hmProp.put("password", "JJadmin!");
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		 
		 return hmProp;
	}
	public static HashMap<String, String> getAllPropertiesjvm() {
		 HashMap<String, String> hmProp = new  HashMap<String, String>();
		 
			Properties prop = new Properties();
			Properties prop2 = new Properties();
			InputStream input = null;
			InputStream input2 = null;

			try {
				
				//input = new FileInputStream(PropertiesUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"/bizoncloudone.properties");
				//D:\BIZ\MaxGroup\GoogleSheets
				//input = new FileInputStream("D:\\BIZ\\MaxGroup\\GoogleSheets\\bizoncloudone-google-sheets\\configuration-files\\bizoncloudone.properties");
				//input = new FileInputStream("D:\\BIZ\\MaxGroup\\GoogleSheets\\bizoncloudone-google-sheets\\configuration-files\\bizoncloudone.properties");
				//input = new FileInputStream("/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/JJ/configuration-files/bizoncloudone.properties");
				
				
				//prop.load(input);
				
				hmProp.put("base.dir", "/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/JJ");
				
				String baseDir = prop.getProperty("base.dir");
				
				//input2 = new FileInputStream(baseDir+"/configuration-files"+"/bizoncloudone.properties");
				//prop2.load(input2);
				
				hmProp.put("base.dir", "/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/JJ");
				hmProp.put("batch.dir", "/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/JJ/batch-files");
				hmProp.put("config.dir", "/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/JJ/configuration-files");
				hmProp.put("libraries.dir", "/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/JJ/libraries");
				hmProp.put("data.store.dir", "/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/JJ/data-store-files");
				hmProp.put("driver", "com.mysql.jdbc.Driver");
				hmProp.put("url", "jdbc:mysql://mysql3000.mochahost.com:3306/bizadmin_jj");
				hmProp.put("user", "bizadmin_jj");
				hmProp.put("password", "JJadmin!");
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		 
		 return hmProp;
	}
	
	public static void main(String args[]){
		
		HashMap<String, String> propAllHm = getAllProperties();

		System.out.println(propAllHm.size());
		
		//System.out.println(propAllHm.get("base.dir"));
		
		
		//System.out.println(PropertiesUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	}
	
}	