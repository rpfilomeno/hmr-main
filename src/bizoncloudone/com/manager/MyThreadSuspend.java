package bizoncloudone.com.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bizoncloudone.com.google.util.DirectoryQuickstart;
 
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
        for(int i=0;i<1000;i++){
            if(i%suspendCount == 0){
                try {
                    System.out.println("Thread Sleep: " + getName());
                    
                    
                    DirectoryQuickstart d = new  DirectoryQuickstart();
                    String[] args = {""};
                    d.main(null);
                    Thread.sleep(300000);
                } catch (InterruptedException ex) { } catch (IOException e) {
                
                
                // TODO Auto-generated catch block		
                e.printStackTrace();
            }
            }
        }
    }
}