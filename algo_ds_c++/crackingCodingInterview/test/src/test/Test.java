/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssef hany
 */

class Worker {
    private static int MY_INT = 0;
    
    public static void execute(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                monitor();
            }
        });
        
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                mutator();
            }
        });
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void monitor(){
        int localVariable = MY_INT;
        while(localVariable < 5){
            if(localVariable != MY_INT){
                System.out.println("MY_INT VALUE CHANGED");
                localVariable = MY_INT;
            }
        }
    }
    
    private static void mutator(){
        int localVariable = MY_INT;
        while(localVariable < 5){
            try {
                Thread.sleep(1000L);
                System.out.println("MY_INT VALUE CHANGED TO: " + (localVariable+1));
                localVariable++;
                MY_INT = localVariable;
            } catch (InterruptedException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Worker.execute();
    }
    
}
