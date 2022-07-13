/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadingandparrallelcomputing;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssef hany
 */
public class Livelock {
    
    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);
    
    public void createLivelock(){
        new Thread(()->worker1()).start();
        new Thread(()->worker2()).start();
    }
    
    private void worker1(){
        while(true){
            try {
                Thread.sleep(1000L);
                lock1.tryLock(50,TimeUnit.MILLISECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(Livelock.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("WORKER1 holds LOCK1");
            System.out.println("WORKER1 tries to hold LOCK2");
            
            if(lock2.tryLock()){
                System.out.println("WORKER1 holds LOCK2");
                break;
            }else{
                System.out.println("WORKER1 can't hold LOCK2");
            }
        }
        
        lock1.unlock();
        lock2.unlock();
    }
    
    private void worker2(){
        while(true){
            try {
                Thread.sleep(1000L);
                lock2.tryLock(50,TimeUnit.MILLISECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(Livelock.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("WORKER2 holds LOCK2");
            System.out.println("WORKER2 tries to hold LOCK1");
            
            if(lock1.tryLock()){
                System.out.println("WORKER2 holds LOCK1");
                break;
            }else{
                System.out.println("WORKER2 can't hold LOCK1");
            }
        }
        
        lock1.unlock();
        lock2.unlock();
    }
    
}
