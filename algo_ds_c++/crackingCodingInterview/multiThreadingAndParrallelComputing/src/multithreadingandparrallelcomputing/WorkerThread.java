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
public class WorkerThread implements Runnable{
    private final String name;
    private final String color;
    private final Long sleepingTime;
    
    public WorkerThread(String name, String color, Long sleepingTime){
        this.name = name;
        this.color = color;
        this.sleepingTime = sleepingTime;
    }
    
    @Override
    public void run(){
        try {
            Thread.sleep(this.sleepingTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(this.color + " " + this.name + " finishes execution");
    }
}
