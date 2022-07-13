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
public class VolatileKeyword {
    
    //When tow threads run on different CPUs each one may have its own local copy of a shared varriable in the CPU cashe
    //If one thread modifies its value -> the change may not reflect to the other thread
    //Conclusion-> data inconsistency
    //The volative keyword gurantees tha a varriable will be read from main memory not the cashe
    //This property lead to synchronization as the cashe may be sometimes inconsistent
    private volatile boolean terminated = false;
    private volatile int MY_INT = 0;
    
    public void execute(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process();
                } catch (InterruptedException ex) {
                    Logger.getLogger(VolatileKeyword.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        t1.start();
        
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException ex) {
            Logger.getLogger(VolatileKeyword.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //This is the way to stop a thread as Thread.stop() is thread unsafe
        this.setTerminated(true);
        System.out.println("Thread was terminated....");
    }
    
    public void process() throws InterruptedException{
        while(!this.isTerminated()){
            System.out.println("thread is running....");
            Thread.sleep(500L);
        }
    }
    
    public boolean isTerminated(){
        return this.terminated;
    }
    
    public void setTerminated(boolean terminated){
        this.terminated = terminated;
    }
    
    public void inconsistencyTest(){
        Thread t1 = new Thread(()->monitorValue());
        Thread t2 = new Thread(()->mutateValue());
        
        t1.start();
        t2.start();
    }
    
    public void monitorValue(){
        int local_value = MY_INT;
        while(local_value < 5){
            if(local_value != MY_INT){
                System.out.println("MY_INT VALUE CHANGED TO: " + MY_INT);
                local_value = MY_INT;
            }
        }
    }
    
    public void mutateValue(){
        int local_value = MY_INT;
        while(local_value < 5){
            System.out.println("Incrementing MY_INT to: " + (local_value+1));
            MY_INT=++local_value;
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException ex) {
                Logger.getLogger(VolatileKeyword.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
