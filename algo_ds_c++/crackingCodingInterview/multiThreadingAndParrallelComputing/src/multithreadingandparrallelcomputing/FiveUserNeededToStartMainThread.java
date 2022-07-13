/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadingandparrallelcomputing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssef hany
 */
class User {

    private final CountDownLatch latch;
    private final long sleepingTime;
    private static int userCount = 0;

    public User(CountDownLatch latch, long sleepingTime) {
        this.latch = latch;
        this.sleepingTime = sleepingTime;
        userCount++;
    }

    public void joinApp() {
        try {
            Thread.sleep(sleepingTime);
            System.out.println("User " + this.userCount + " joined the app...");
            this.latch.countDown();
        } catch (InterruptedException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int getUserCount(){
        return userCount;
    }
}

public class FiveUserNeededToStartMainThread {

    public void execute() {
        final CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newSingleThreadExecutor();

        for(int i=0;i<5;i++)
            service.execute(new Runnable() {
            @Override
            public void run() {
                new User(latch, 1000L).joinApp();
            }
        });
        
        try {
            latch.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(FiveUserNeededToStartMainThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("User reached " + User.getUserCount() + " SO app can START...");
        
        service.shutdown();
    }

}
