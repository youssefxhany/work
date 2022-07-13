/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadingandparrallelcomputing;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssef hany
 */

class Service{
    private final String name;
    private final long sleepingTime;
    private final CountDownLatch latch;
    
    Service(String name, long sleepingTime, CountDownLatch latch){
        this.name = name;
        this.sleepingTime = sleepingTime;
        this.latch = latch;
    }
    
    public void execute(){
        try {
            Thread.sleep(sleepingTime);
            System.out.println("Service " + this.name + " started execution...");
            this.latch.countDown();
        } catch (InterruptedException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

public class CountDownLatchDemo {
    
    public void execute(){
        //we cannot reset the value of a CountDownLatch ,once it reaches zero it can't be reused.
        CountDownLatch latch = new CountDownLatch(3);
        Thread cacheService = new Thread(()->new Service("CACHE_SERVICE", 1000, latch).execute());
        Thread validationService = new Thread(()->new Service("VALIDATION_SERVICE", 3000, latch).execute());
        Thread alertService = new Thread(()->new Service("ALERT_SERVICE", 5000, latch).execute());
        
        cacheService.start();
        validationService.start();
        alertService.start();
        
        try {
            latch.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(CountDownLatchDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("ALL SERVICE UP, PROGRAM CA START NOW");
    }
}
