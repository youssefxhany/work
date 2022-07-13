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
public class Deadlock {
    
    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);
    
    public void createDeadlock(){
        new Thread(()->this.worker1(),"WORKER1").start();
        new Thread(()->this.worker2(),"WORKER2").start();
    }
    
    private void worker1(){
        String threadName = Thread.currentThread().getName();
        lock1.lock();
        System.out.println(threadName + " aquiered LOCK1");
        
        try {
            Thread.sleep(300L);
        } catch (InterruptedException ex) {
            Logger.getLogger(Deadlock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lock2.lock();
        System.out.println(threadName + " aquiered LOCK2");
        
        lock1.unlock();
        lock2.unlock();
    }
    
    private void worker2() {
        String threadName = Thread.currentThread().getName();
        lock2.lock();
        System.out.println(threadName + " aquiered LOCK2");
        try {
            Thread.sleep(300L);
        } catch (InterruptedException ex) {
            Logger.getLogger(Deadlock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lock1.lock();
        System.out.println(threadName + " aquiered LOCK1");
        
        lock1.unlock();
        lock2.unlock();
    }
    
    //we can eliminate circular wait by emposing a total resource ordering
    //Ressource order is: lock1 --> lock2
    public void eliminateCircularWait(){
        new Thread(()->worker3(),"WORKER3").start();
        new Thread(()->worker4(),"WORKER4").start();
    }
    
    private void worker3(){
        String threadName = Thread.currentThread().getName();
        lock1.lock();
        System.out.println(threadName + " aquiered LOCK1");
        
        try {
            Thread.sleep(300L);
        } catch (InterruptedException ex) {
            Logger.getLogger(Deadlock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lock2.lock();
        System.out.println(threadName + " aquiered LOCK2");
        
        lock1.unlock();
        lock2.unlock();
    }
    
    private void worker4() {
        String threadName = Thread.currentThread().getName();
        lock1.lock();
        System.out.println(threadName + " aquiered LOCK1");
        try {
            Thread.sleep(300L);
        } catch (InterruptedException ex) {
            Logger.getLogger(Deadlock.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lock2.lock();
        System.out.println(threadName + " aquiered LOCK2");
        
        lock1.unlock();
        lock2.unlock();
    }
    
}
