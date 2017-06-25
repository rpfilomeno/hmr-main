package bizoncloudone.com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDifferentExample {

	public static void main(String[] args) {

		String dateStart = "01/07/2016 09:29:58";
		String dateStop = "01/07/2016 10:24:48";

		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		Date d1 = null;
		Date d2 = null;
		Date dt2 = new Date();

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			//in milliseconds
			long diff = dt2.getTime() - d1.getTime();
			
			 long diffHours = diff / (60 * 60 * 1000);
			 
	        //if (diffInDays > 1) {
	        //    System.err.println("Difference in number of days (2) : " + diffInDays);
	        //    return false;
	        //} else if (diffHours > 24) {

	        //    System.err.println(">24");
	        //    return false;
	        //} else if ((diffHours == 24) && (diffMinutes >= 1)) {
	        //    System.err.println("minutes");
	        //    return false;
	        //}

			//long diffSeconds = diff / 1000 % 60;
			//long diffMinutes = diff / (60 * 1000) % 60;
			//long diffHours = diff / (60 * 60 * 1000) % 24;
			//long diffDays = diff / (24 * 60 * 60 * 1000);

			//System.out.print(diffDays + " days, ");
			//System.out.print(diffHours + " hours, ");
			//System.out.print(diffMinutes + " minutes, ");
			//System.out.print(diffSeconds + " seconds.");
			System.out.print(diffHours + " hours.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static String getUserTokenStatus(Date d1, Boolean isRTExist) {
		String status = "";
		//String dateStart = "01/14/2012 09:29:58";
		//String dateStop = "01/15/2012 10:31:48";
		Date dt2 = new Date();

		//HH converts hour in 24 hours format (0-23), day calculation
		//SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		//Date d1 = null;
		//Date d2 = null;

		try {
			//d1 = format.parse(dateStart);
			//d2 = format.parse(dateStop);

			//in milliseconds
			long diff = dt2.getTime() - d1.getTime();
			
			 long diffHours = diff / (60 * 60 * 1000);
			 
	        //if (diffInDays > 1) {
	        //    System.err.println("Difference in number of days (2) : " + diffInDays);
	        //    return false;
	        //} else if (diffHours > 24) {

	        //    System.err.println(">24");
	        //    return false;
	        //} else if ((diffHours == 24) && (diffMinutes >= 1)) {
	        //    System.err.println("minutes");
	        //    return false;
	        //}

			//long diffSeconds = diff / 1000 % 60;
			//long diffMinutes = diff / (60 * 1000) % 60;
			//long diffHours = diff / (60 * 60 * 1000) % 24;
			//long diffDays = diff / (24 * 60 * 60 * 1000);

			//System.out.print(diffDays + " days, ");
			//System.out.print(diffHours + " hours, ");
			//System.out.print(diffMinutes + " minutes, ");
			//System.out.print(diffSeconds + " seconds.");
			System.out.print(diffHours + " hours.");
			
			if(diffHours > 1 && isRTExist){
				status = "Please refresh token.";
			}else if(diffHours > 1 && !isRTExist){
				status = "Please send email verification.";
			}else{
				status = "Ready for download.";
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;

	}

}