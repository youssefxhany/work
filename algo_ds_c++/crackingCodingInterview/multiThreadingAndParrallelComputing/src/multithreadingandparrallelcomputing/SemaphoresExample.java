/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadingandparrallelcomputing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssef hany
 */

//Singlelton pattern -> we can call a method using the INSTANCE keyword without actualy instanciating an object of the class
enum Downloader{
    INSTANCE;
    
    //three threads can execute at the same time
    Semaphore semaphore = new Semaphore(3, true);
    
    public void download(){
        try {
            semaphore.acquire();
            downloadData();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } finally{
            semaphore.release();
        }
    }
    
    private void downloadData() throws InterruptedException {
        System.out.println("Data is being downloaded from the web...");
        Thread.sleep(3000);
    }
}

public class SemaphoresExample {
    
    public void execute(){
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i=0;i<12;i++){
            service.execute(() -> Downloader.INSTANCE.download());
        }
        
        service.shutdown();
        try{
            if(!service.awaitTermination(10000, TimeUnit.SECONDS)){
                service.shutdownNow();
            }
        }catch(InterruptedException ex){
            service.shutdownNow();
        }
    }
}
