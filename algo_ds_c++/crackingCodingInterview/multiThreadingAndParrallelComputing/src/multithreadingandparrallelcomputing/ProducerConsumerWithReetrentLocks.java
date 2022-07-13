/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadingandparrallelcomputing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssef hany
 */
public class ProducerConsumerWithReetrentLocks {
    private Lock lock1 = new ReentrantLock(true);
    private Condition condition1 = lock1.newCondition();
    
    private Lock lock2 = new ReentrantLock(true);
    private Condition condition2 = lock2.newCondition();
    private List<Integer> burgerPlate = new ArrayList<>();
    private Integer burgerId = 0;
    private final int MAX_LIMIT = 5;
    private final int MIN_LIMIT = 0;
    
    private final String producerColor;
    private final String consumerColor;
    private final String ANSI_RESET = "\u001B[0m";
    private final String PURPLE = "\u001B[35m";
    private final Object lock = new Object();
    
    public ProducerConsumerWithReetrentLocks(String producerColor, String consumerColor){
        this.producerColor = producerColor;
        this.consumerColor = consumerColor;
    }
    
    public void normalConsumerProducer(){
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProducerConsumerWithReetrentLocks.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consume();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProducerConsumerWithReetrentLocks.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        producer.setName("Producer");
        consumer.setName("Consumer");
        
        producer.start();
        consumer.start();
    }
    
    private void produce() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        this.lock1.lock();
        try {
            System.out.println(threadName + " is producing...");
            //Same as wait-> release lock + move to waiting state + get alive when signaled/notified
            condition1.await();
            System.out.println(threadName + " is producing again...");
        } finally {
            this.lock1.unlock();
        }
    }
    
    private void consume() throws InterruptedException{
        String threadName = Thread.currentThread().getName();
        Thread.sleep(1000L);
        this.lock1.lock();
        try {
            System.out.println(threadName + " is consuming...");
            Thread.sleep(1000L);
            //Signal wakes up one waitig thread
            this.condition1.signal();
        } finally {
            this.lock1.unlock();
        }
    }
    
    public void burgerConsumerProducer(){
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produceBurger();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProducerConsumerWithReetrentLocks.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumeBurger();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProducerConsumerWithReetrentLocks.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        producer.setName("Producer");
        consumer.setName("Consumer");
        
        producer.start();
        consumer.start();
    }
    
    private void produceBurger() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        while (true) {
            Thread.sleep(1000);
            if (!this.lock2.tryLock()) {
                System.out.println(threadName + " is doing other things");
            } else {
                if (this.burgerPlate.size() == MAX_LIMIT) {
                    System.out.println(this.PURPLE + " Plate is Full, can't produce -> switch context to consumer " + this.ANSI_RESET);
                    this.burgerId = 0;
                    this.condition2.await();
                    //this.lock2.unlock();
                } else {
                    System.out.println("Burger " + burgerId + " " + this.producerColor + " added " + this.ANSI_RESET + " to plate ");
                    burgerPlate.add(burgerId++);
                    this.condition2.signal();
                }
            }
        }
    }

    private void consumeBurger() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        while (true) {
            Thread.sleep(1000);
            if (!this.lock2.tryLock()) {
                System.out.println(threadName + " is doing other things");
            } else {
                if (this.burgerPlate.size() == MIN_LIMIT) {
                    System.out.println(this.PURPLE + " Plate is empty, can't consume -> switch context to producer " + this.ANSI_RESET);
                    this.condition2.await();
                    //this.lock2.unlock();
                } else {
                    System.out.println("Burger " + this.burgerPlate.remove(burgerPlate.size() - 1) + " " + this.consumerColor + " removed " + this.ANSI_RESET + " from plate");
                    this.condition2.signal();
                }
            }
        }
    }
}
