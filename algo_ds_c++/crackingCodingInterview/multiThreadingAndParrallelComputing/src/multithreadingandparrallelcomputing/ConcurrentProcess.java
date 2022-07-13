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
public class ConcurrentProcess {
    private static int inconsistentCounter = 0;
    private static int consistentCounter = 0;
    
    //counter1 and counter2 are independent varriables
    private int counter1 = 0;
    private int counter2 = 0;
    
    private int counter3 = 0;
    private int counter4= 0;
    private final Object lock3 = new Object();
    private final Object lock4 = new Object();
    
    private String ObjectName;
    
    ConcurrentProcess(){
        
    }
    
    ConcurrentProcess(String objectName){
        this.ObjectName = objectName;
    }
    
    public static void inconsistentCounter(){
        Thread t1 = new Thread(()->increment10000TimesInconsistent());
        Thread t2 = new Thread(()->increment10000TimesInconsistent());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ConcurrentProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Counter value is: " + inconsistentCounter);
    }
    
    private static void increment10000TimesInconsistent(){
        for(int i=0;i<10000;i++)
            incrementInconsistentCounter();
    }
    
    private static void incrementInconsistentCounter(){
        inconsistentCounter++;
    } 
    
    public static void consistentCounter(){
        Thread t1 = new Thread(()->increment10000TimesConsistent());
        Thread t2 = new Thread(()->increment10000TimesConsistent());
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ConcurrentProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Counter value is: " + consistentCounter);
    }
    
    private static void increment10000TimesConsistent(){
        for(int i=0;i<10000;i++)
            incrementConsistentCounter();
    }
    
    //synchronized -> Guarentees that this part of the code cannot be execuated by more than One thread at a given time
    //synchronized -> Leads to mutual exclusion
    //Every object of a class has one intrinsic lock 
    //The thread who has the lock at a moment of time can enter the critical section
    //After the thread finishes its work it release the lock
    //As long as a thread has not the lock it won't enter a critical section and won't be able to alter/change the shared ressource
    //We can use class level locking (with static varriable) and also object level locking
    //Each Object of a class has a single Intrinsic/Monitor lock
    //It's not a good practice to use synchronized keyword in the method prototype -> better to use synchronized blocks
    private static void incrementConsistentCounter() {
        //class level locking as we increment a static varriable
        //All objects of the same class share one Intrinsic lock
        synchronized (ConcurrentProcess.class) {
            consistentCounter++;
        }
    }
    
    //VIP one
    //also counter1 and counter2 are independent 
    //An Object of the ConcurrentProcess class cannot increment them at the same time
    //As each Object has a single Internsic/Monitor lock
    //Conclusion-> also we use two threads + each thread increment a counter that is independent of the other + each thread will wait for the
    //other to finish its operation as we have one single lock
    //This will lead to slowliness althought multithreading was used to achieve a better performance
    //The result will be right but the performance is bad
    public void slowConcurency(){
        Thread t1 = new Thread(()->incrementCounter1By10000());
        Thread t2 = new Thread(()->incrementCounter2By10000());
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ConcurrentProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Counter1 is: " + this.counter1 + " " + this.ObjectName);
        System.out.println("Counter2 is: " + this.counter2 + " " + this.ObjectName);
    }
    
    private void incrementCounter1By10000(){
        for(int i=0;i<10000;i++)
            incrementCounter1();
    }
    
    private void incrementCounter2By10000(){
        for(int i=0;i<10000;i++)
            incrementCounter2();
    }
   
    private void incrementCounter1() {
        //object level synchronization <- each object of a class has a single intrinsic lock
        synchronized (this) {
            this.counter1++;
        }
    }
    
    private void incrementCounter2(){
        //object level synchronization <- each object of a class has a single intrinsic lock
        synchronized (this) {
            this.counter2++;
        }
    }
    
    //Now we will implement better solution for previous situation
    //We will use custom objects locking
    //we can use a different lock for each counter
    //Now a thread incrementing counter3 won't wait for a thread that is incrementing counter4
    //And also no two thread would incrment one of the counters at the same given time
    public void betterConcurency(){
        Thread t1 = new Thread(()->this.incrmentCounter3By10000());
        Thread t2 = new Thread(()->this.incrmentCounter4By10000());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ConcurrentProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Counter 3 is: " + counter3);
        System.out.println("Counter 4 is: " + counter4);
    }
    
    private void incrmentCounter3By10000(){
        for(int i=0;i<10000;i++)
            incrementCounter3();
    }
    
    private void incrmentCounter4By10000(){
        for(int i=0;i<10000;i++)
            incrementCounter4();
    }
    
    private void incrementCounter3(){
        synchronized(lock3){
            this.counter3++;
        }
    }
    
    private void incrementCounter4(){
        synchronized(lock4){
            this.counter4++;
        }
    }
}
