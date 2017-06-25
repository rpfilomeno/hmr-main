package hmr.com.util;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

public class StringUtil
{
  private static final Random RANDOM = new SecureRandom();
  
  public static final int KEY_LENGTH = 8;
  public static final int PASSWORD_LENGTH = 8;
  public static final int VERIFY_LENGTH = 4;
  static String sc = "$!12";
  
  public static String generateRandomKey()
  {
      // Pick from some letters that won't be easily mistaken for each
      // other. So, for example, omit o O and 0, 1 l and L.
      String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789";
      

      String pw = "";
      //String pwsc = "";
      for (int i=0; i<KEY_LENGTH; i++)
      {
          int index = (int)(RANDOM.nextDouble()*letters.length());
          pw += letters.substring(index, index+1);
      }

      return pw;
  }
  
  public static String generatePassword()
  {
      // Pick from some letters that won't be easily mistaken for each
      // other. So, for example, omit o O and 0, 1 l and L.
      String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789";

      String pw = "";
      
      for (int i=0; i<PASSWORD_LENGTH; i++)
      {
          int index = (int)(RANDOM.nextDouble()*letters.length());
          pw += letters.substring(index, index+1);
      }

      pw = pw + sc;

      return pw;
  }
  
  public static String generateSMSVerify()
  {
      // Pick from some letters that won't be easily mistaken for each
      // other. So, for example, omit o O and 0, 1 l and L.
      String letters = "123456789";

      String pw = "";
      
      for (int i=0; i<VERIFY_LENGTH; i++)
      {
          int index = (int)(RANDOM.nextDouble()*letters.length());
          pw += letters.substring(index, index+1);
      }


      return pw;
  }
  
  
	public static void main(String[] args) throws IOException {
		StringUtil su = new StringUtil();
		System.out.println("Generated Password : "+su.generatePassword());
	}
}
