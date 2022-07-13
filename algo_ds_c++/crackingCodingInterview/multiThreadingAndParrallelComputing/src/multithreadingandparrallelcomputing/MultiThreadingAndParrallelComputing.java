
package multithreadingandparrallelcomputing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssef hany
 */
public class MultiThreadingAndParrallelComputing {
    
    //Defing colors to use while debugging
    public static final String GREEN = "\033[32m";
    public static final String RED = "\033[31m";
    public static final String BLACK = "\u001B[30m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    private static final String ANSI_RESET = "\u001B[0m";
    
    public static void executeSequential(){
        SequentialProcess sequentialProcess = new SequentialProcess();
        sequentialProcess.incrementCounterBy10000();
        sequentialProcess.incrementCounterBy10000();
        System.out.println("Sequential process counter is: " + sequentialProcess.getCounter());
    }
    
    public static void sequentialRunner(){
        SequentialProcess sequentialRunner1 = new SequentialProcess(GREEN);
        SequentialProcess sequentialRunner2 = new SequentialProcess(RED);
        sequentialRunner1.incrementBy10("Runner 1");
        sequentialRunner1.incrementBy10("Runner 2");
    }
    
    public static void concurrentRunner(){
        Thread t1 = new Thread(new RunnableThreads("Runner 1",GREEN));
        Thread t2 = new Thread(new RunnableThreads("Runner 2",RED));
        //we can also make use of an ananymous Runnable class
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    System.out.println(BLUE + " " + "Runner3 " + ": " + i + " " + ANSI_RESET);
                }
            }
        });
        
        //used to start a thread
        t1.start();
        t2.start();
        t3.start();
    }
    
    public static void slowExecution(){
        //ExtendingThreads extends Thread -> ExtendingThreads is a Thread
        Thread t1 = new ExtendingThreads(GREEN, "Runner1");
        Thread t2 = new ExtendingThreads(RED, "Runner2");
        
        t1.start();
        t2.start();
    }
    
    public static void notUsingJoin(){
        Thread t1 = new ExtendingThreads("Runner1", BLUE);
        Thread t2 = new ExtendingThreads("Runner2", GREEN);
        
        t1.start();
        t2.start();
        System.out.println(RED + " Finished with the threads...");
    }
    
    public static void usingJoin(){
        Thread t1 = new ExtendingThreads("Runner1", BLUE);
        Thread t2 = new ExtendingThreads("Runner2", GREEN);
        
        t1.start();
        t2.start();
        
        try {
            //.join() is used to make the main thread wait for the execution of some other thread being finished (the thread being DEAD)
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MultiThreadingAndParrallelComputing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(RED + " Finished with the threads...");
    }
    
    public static void deamonAndWorker(){
        Thread workerThread1 = new Thread(new WorkerThread("Worker1",GREEN,3000L));
        Thread workerThread2 = new Thread(new WorkerThread("Worker2",RED,5000L));
        Thread workerThread3 = new Thread(new WorkerThread("Worker3",BLUE,8000L));
        Thread workerThread4 = new Thread(new WorkerThread("Worker4",PURPLE,10000L));
        Thread daemonThread = new Thread(new DaemonThread());
        
        //setDaemon(true)-> then daemon thread || setDaemon(false)-> then worker thread 
        //By default when we create a thread it would be a worker thread
        daemonThread.setDaemon(true);
        
        workerThread1.start();
        workerThread2.start();
        workerThread3.start();
        workerThread4.start();
        daemonThread.start();
    }
    
    public static void infiniteLoop(){
        while(true){
            
        }
    }
    
    public static void singleThreadExecutor() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            service.execute(new Worker(i));
        }

        //No new tasks will be created using the executor
        //But we need to kill previously created tasks
        service.shutdown();

        try {
            //checks if all tasks of executers are terminated within the given timeout
            if (!service.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                //force all task to shutdown even if running
                service.shutdownNow();
            }
        } catch (InterruptedException ex) {
            service.shutdownNow();
        }
    }

    public static void fixedThreadPool() {
        int availableCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Numbers of available cores is: " + availableCores);
        ExecutorService service = Executors.newFixedThreadPool(availableCores);

        for (int i = 0; i < 100; i++) {
            service.execute(new Worker(i + 1));
        }

        service.shutdown();

        try {
            if (!service.awaitTermination(10000, TimeUnit.MILLISECONDS)) {

            }
        } catch (InterruptedException ex) {
            service.shutdownNow();
        }
    }

    public static void cachedThreadPool() {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            service.execute(new Worker(i + 1));
        }

        service.shutdown();

        try {
            if (!service.awaitTermination(10000, TimeUnit.MILLISECONDS)) {

            }
        } catch (InterruptedException ex) {
            service.shutdownNow();
        }
    }

    public static void scheduledExecutor() {
        //the argument is the number of threads needed
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

        service.scheduleAtFixedRate(new Worker(1), 1000, 5000, TimeUnit.MILLISECONDS);

        try {
            if (!service.awaitTermination(20000, TimeUnit.MILLISECONDS)) {
                service.shutdownNow();
            }
        } catch (InterruptedException ex) {
            service.shutdownNow();
        }
    }
    
    public static void createThreadsWithCallable() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> future = executor.submit(new CallableThreads(i + 1));
            futureList.add(future);
        }

        for (Future<String> future : futureList) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException ex) {
                Logger.getLogger(MultiThreadingAndParrallelComputing.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(MultiThreadingAndParrallelComputing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        executor.shutdown();

        try {
            if (!executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
        }
    }
    
    public static void main(String[] args) {
        
        //The first thread to execute is the [Main thread] and it searches for the main method and executes it
        //The [main thread] is also the last thread to execute within a program cause it performs various shutdown operations
        //From the main thread we can created child threads
        //In Java a thread can be -> worker thread / Deamon thread
        //A deamon thread is a low priority thread that runs in the background
        //Deamon thread is terminated/Interrupted by JVM when every other worker threads is being terminated
//        String currrentThreadName = Thread.currentThread().getName();
//        long currentThreadPid = Thread.currentThread().getId();
//        System.out.println("Current thread is: " + currrentThreadName + " with id: " + currentThreadPid);
        
        // we can see that the excution here is sequential -> Runner1 than Runner2
//        sequentialRunner();
        
        // we ca see that the runners are executed concurrently -> but in reality it's the time slicing algorithm + It's not parrallel execution(an ilusion)
//        concurrentRunner();
        
        //Using the Runnable interface is better than inherting from the Thread class
        //as we can implement many interfaces but we can extend only one class
//        slowExecution();
        
//        notUsingJoin();
//        usingJoin();
        
        //daemon thread is terminated when all other worker thread are terminated
        //The main thread is a worker thread
        //so if we add an infinite loop then the daemon thread will worker forever as the main thread will never terminate
//        deamonAndWorker();
//        infiniteLoop
        
        //VIP CASE
        //This case will lead to the program non termination 
        //the worker thread will reminate after 12 seconds
        //but the main thread won't terminate as it will be wainting for the deamon thread to terminate -> daemon.join()
        //And the Damon thread won't be terminated as the main thread will be in life
        //Its similar to a deadlock
//        Thread daemon = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(MultiThreadingAndParrallelComputing.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    System.out.println("daemon executing...");
//                }
//            }
//        });
//        
//        Thread worker = new Thread(new WorkerThread("worker1",RED,12000L));
//        daemon.setDaemon(true);
//        daemon.start();
//        worker.start();
//        
//        try {
//            daemon.join();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(MultiThreadingAndParrallelComputing.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("EXECUTION FINISHED");

          //Now we will discuss Synchronization
          //Threads of the same process share same memory space
          
//          executeSequential();
          
          //as we can ses both processes access the counter which is a shared ressorce at the same time which lead to inconsistency 
          //in the value of the counter + Its value should be 20000
          //when we use synchronized we gurantee mutual exclusion
//          ConcurrentProcess.inconsistentCounter(); //<- underteministic
//          ConcurrentProcess.consistentCounter();   //<- deterministic
          
          //class level locking is applied -> no two object from same class can access critical section at same time
          //It's guarenteed that final counter value will be 40000
//        ConcurrentProcess concurrentProcess1 = new ConcurrentProcess();
//        ConcurrentProcess concurrentProcess2 = new ConcurrentProcess();
//
//        Thread t1 = new Thread(() -> concurrentProcess1.consistentCounter());
//        Thread t2 = new Thread(() -> concurrentProcess2.consistentCounter());
//        t1.start();
//        t2.start();

            //An Object of a class has a single Internsic/Monitor lock
//            ConcurrentProcess slowConcurrentProcess1 = new ConcurrentProcess("Object1");
//            ConcurrentProcess slowConcurrentProcess2 = new ConcurrentProcess("Object2");
//            Thread t1 = new Thread(()->slowConcurrentProcess1.slowConcurency());
//            Thread t2 = new Thread(()->slowConcurrentProcess2.slowConcurency());
//            t1.start();
//            t2.start();

    //Locking with custom objects achieved a better performance
//        ConcurrentProcess betterConcurrentProcess = new ConcurrentProcess();
//        betterConcurrentProcess.betterConcurency();

        //wait and notify are ussed for communication between processes
//        ThreadCommunication threadCommunication = new ThreadCommunication();
//        threadCommunication.execute();

//          ProducerConsumerList producerConsumer = new ProducerConsumerList(GREEN,RED);
//          producerConsumer.execute();

        //What's the difference between wait and sleep
        //1-sleep must be called from a thread (thread.sleep()) Vs wait can be called from an object
        //2-wait must be called from a synchronized block Vs sleep can be called from anywhere
        //3-wait moves thread to waiting mode and release lock and won't wake up until notified Vs sleep put the thread at waiting mode for some time only
        
        //We may use ReentrentLocks
//        ReentrentLockProcess.execute();

//        ProducerConsumerWithReetrentLocks pcwrl = new ProducerConsumerWithReetrentLocks();
//        pcwrl.normalConsumerProducer();

//         ProducerConsumerWithReetrentLocks burgerPlate = new ProducerConsumerWithReetrentLocks(GREEN, RED);
//         burgerPlate.burgerConsumerProducer();

        //The volative keyword gurantees tha a varriable will be read from main memory not the cashe
        //This property lead to synchronization as the cashe may be sometimes inconsistent
        
        //What is Mutual exclusion -> It means that only one thread can execute a block of code (critical section) in a single moment of time
        //What is visibility -> It means that changes made by one thread to shared data are visible to other threads.
        //Synchronized keyword guarentees both properties
        //Volatile keyword gurantees only visibility 
        
//        VolatileKeyword volatileKeyword = new VolatileKeyword();
//        volatileKeyword.execute();
//        volatileKeyword.inconsistencyTest();
       
        //Deadlock: It's a situation when two or more competing actions are waiting for each other to finish, thus neither action will finish.
        //  Program 1 requests resource A and receives it.
        //  Program 2 requests resource B and receives it.
        //  Program 1 requests resource B and is queued up, pending the release of B.
        //  Program 2 requests resource A and is queued up, pending the release of A.
        
        //Deadlock creation and elimination
//        Deadlock deadlock = new Deadlock();
//        deadlock.createDeadlock();
//        deadlock.eliminateCircularWait();

        //Livelock creation
//        Livelock livelock = new Livelock();
//        livelock.createLivelock();

        //Use atomic varriables to guarentee synchronization and consistency
//        AtomicIntegerExample atomicIntegerExample = new AtomicIntegerExample();
//        atomicIntegerExample.execute();

//        SemaphoresExample semaphoresExample = new SemaphoresExample();
//        semaphoresExample.execute();

//          singleThreadExecutor();
//          fixedThreadPool();
//          cachedThreadPool();
//          scheduledExecutor();
         
          //Creating threads with the callable interface
//          createThreadsWithCallable();

//          SynchronizedChollection synchronizedChollection = new SynchronizedChollection();
//          synchronizedChollection.execute();

//          CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
//          countDownLatchDemo.execute();

          FiveUserNeededToStartMainThread fiveUserNeededToStartMainThread = new FiveUserNeededToStartMainThread();
          fiveUserNeededToStartMainThread.execute();
          
    }
    
}
