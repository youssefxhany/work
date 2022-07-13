/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadingandparrallelcomputing;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssef hany
 */
public class ProducerConsumerList {
    private List<Integer> burgerPlate = new ArrayList();
    private Integer burgerId = 0;
    private final Integer MAX_BOUNDARY = 5;
    private final Integer MIN_BOUNDARY = 0;
    
    private final String producerColor;
    private final String consumerColor;
    private final String ANSI_RESET = "\u001B[0m";
    private final String PURPLE = "\u001B[35m";
    private final Object lock = new Object();
    
    public ProducerConsumerList(String producerColor, String consumerColor){
        this.producerColor = producerColor;
        this.consumerColor = consumerColor;
    }
    
    public void execute(){
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProducerConsumerList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });  
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consume();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProducerConsumerList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        producer.setName("Producer");
        consumer.setName("Consumer");
        
        producer.start();
        consumer.start();
    }
    
    private void produce() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                Thread.sleep(1000);
                if (burgerPlate.size() == MAX_BOUNDARY) {
                    System.out.println(this.PURPLE + " Producer reached max capacity of plate -> shift context to consumer " + ANSI_RESET);
                    this.burgerId = MAX_BOUNDARY-1;
                    lock.wait();
                } else {
                    System.out.println("Burger " + burgerId + " " + this.producerColor + " added " + ANSI_RESET + " to plate");
                    this.burgerPlate.add(burgerId++);
                    lock.notify();
                }
            }
        }
    }
    
    private void consume() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                Thread.sleep(1000);
                if (burgerPlate.size() == MIN_BOUNDARY) {
                    System.out.println(this.PURPLE + " Plate is empty, can't consume -> shift context to producer " + ANSI_RESET);
                    this.burgerId = MIN_BOUNDARY;
                    lock.wait();
                } else {
                    System.out.println("Burger " + burgerId + " " + this.consumerColor +  " removed " + ANSI_RESET + " from plate");
                    this.burgerPlate.remove(burgerId--);
                    lock.notify();
                }
            }
        }
    }
}
