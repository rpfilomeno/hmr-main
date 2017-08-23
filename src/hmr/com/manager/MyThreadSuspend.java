package hmr.com.manager;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import hmr.com.util.BiddingExtendUtil;
 
public class MyThreadSuspend {
    public static void main(String a[]){
        List<ExmpThread> list = new ArrayList<ExmpThread>();
        for(int i=0;i<1;i++){
            ExmpThread et = new ExmpThread(i+1);
            list.add(et);
            et.start();
        }
        for(ExmpThread et:list){
            try {
                et.join();
            } catch (InterruptedException ex) { }
        }
    }
}
 
class ExmpThread extends Thread{
    private int suspendCount;
    public ExmpThread(int count){
        this.suspendCount = count;
    }
    public void run(){
        for(int i=0;i<1;i++){
            if(i%suspendCount == 0){
                try {
                	
                	Thread.sleep(30000);
                    System.out.println("Thread Sleep: " + getName());
                    
                    
                    BiddingExtendUtil d = new  BiddingExtendUtil();
                    String[] args = {""};
                    try {
						d.main(null);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {  
                
                
                // TODO Auto-generated catch block		
                ex.printStackTrace();
            }
            }
        }
    }
}
