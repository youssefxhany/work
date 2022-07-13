/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadingandparrallelcomputing;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssef hany
 */
public class Worker implements Runnable {
    private final int id;
    private final Semaphore semaphore = new Semaphore(4,true);
    
    public Worker(int id){
        this.id = id;
    }
    
    @Override
    public void run(){
        try {
            //semaphore.acquire();
            System.out.println("task[" + this.id + "] is executed by thread[" + Thread.currentThread().getId() + "]");
            long randomTime = (long) (Math.random() * 5);
            TimeUnit.SECONDS.sleep(randomTime);
        } catch (InterruptedException ex) {
            //used to awaike current sleeping thread
            Thread.currentThread().interrupt();
        } finally{
            //semaphore.release();
        }
    }
}
