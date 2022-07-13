/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadingandparrallelcomputing;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssef hany
 */
public class DaemonThread implements Runnable{
    
   @Override
   public void run(){
       while(true){
           try {
               Thread.sleep(1000);
           } catch (InterruptedException ex) {
               Logger.getLogger(DaemonThread.class.getName()).log(Level.SEVERE, null, ex);
           }
           System.out.println("Daemon thread executing...");
       }
   }
}
