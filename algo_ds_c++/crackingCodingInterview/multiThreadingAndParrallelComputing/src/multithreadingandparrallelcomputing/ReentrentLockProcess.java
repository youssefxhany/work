/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadingandparrallelcomputing;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssef hany
 */
public class ReentrentLockProcess {
   
    private static int counter = 0;
    //ReentrantLock(boolean fairness) when fairness is true -> the thread that has been waiting for the lock the longest time gets the lock
    private static final Lock lock = new ReentrantLock(true);
    
    public static void execute(){
        Thread t1 = new Thread(()->incrementBy10000());
        Thread t2 = new Thread(()->incrementBy10000());
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ReentrentLockProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Counter value is: " + counter);
    }
    
    private static void incrementBy10000(){
        for(int i=0;i<10000;i++)
            incrementCounter();
    }
    
    private static void incrementCounter(){
        //What if an exception happened in critical section -> then the thread will have the lock forever and will never release it
        //conclusion -> a deadlock
        //better to surrount it with try - catch - finaly (this pattern is usualy used with ReetrantLocks)
        lock.lock();
        try {
            counter++;
        } finally{
            //If an exception happened than the lock will be released
            lock.unlock();
        }
    }
}
