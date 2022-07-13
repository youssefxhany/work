/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadingandparrallelcomputing;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssef hany
 */
public class AtomicIntegerExample {
    private AtomicInteger counter = new AtomicInteger(0);
    
    public void execute(){
        Thread t1 = new Thread(()->this.incrementCounterBy10000());
        Thread t2 = new Thread(()->this.incrementCounterBy10000());
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(AtomicIntegerExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Counter value is: " + this.counter);
    }
    
    private void incrementCounterBy10000(){
        for(int i=0;i<10000;i++)
            this.increment();
    }
    
    private void increment(){
        this.counter.getAndIncrement();
    }
}
