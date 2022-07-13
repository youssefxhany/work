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
public class ExtendingThreads extends Thread{
    private String name;
    private String color;
    private final String ANSI_RESET = "\u001B[0m";
    
    ExtendingThreads(String name, String color){
        this.name = name;
        this.color = color;
    }
    
    @Override
    public void run(){
        incrementBy10(1000);
    }
    
    public void incrementBy10(long sleepingTime){
        for(int i=0;i<10;i++){
            try {
                //cease the execution of the thread for x miliseconds and y nanoseconds
                Thread.sleep(sleepingTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(ExtendingThreads.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(this.color + " " + this.name + ": " + i + " " + ANSI_RESET);
        }
    }
    
}
