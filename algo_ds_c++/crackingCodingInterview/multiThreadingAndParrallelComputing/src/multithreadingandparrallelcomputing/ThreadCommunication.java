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
public class ThreadCommunication {
    
    public void execute(){
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadCommunication.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });        
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consume();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadCommunication.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        producer.setName("Producer");
        consumer.setName("Consumer");
        
        producer.start();
        consumer.start();
    }
    
    private void produce() throws InterruptedException{
        String threadName = Thread.currentThread().getName();
        synchronized(this){
            System.out.println(threadName + " is producing...");
            //.wait() make the thread release the intrinsic lock and enters the waiting state until notify is called from another thread
            wait();
            System.out.println(threadName + " is producing again...");
        }
    }
    
    private void consume() throws InterruptedException{
        String threadName = Thread.currentThread().getName();
        Thread.sleep(1000); //gurantee that producer starts before consumer
        synchronized(this){
            System.out.println(threadName + " is consuming...");
            //.notify() move a thread from waiting state to running state
            notify();
            System.out.println("Notify effect is not instant + notify doesn't givup the lock");
        }
    }
}
