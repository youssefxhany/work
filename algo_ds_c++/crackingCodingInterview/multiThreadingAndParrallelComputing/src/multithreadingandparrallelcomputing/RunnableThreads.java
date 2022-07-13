/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadingandparrallelcomputing;

/**
 *
 * @author youssef hany
 */
public class RunnableThreads implements Runnable{
    private String name;
    private final String color;
    private final String ANSI_RESET = "\u001B[0m";
    
    RunnableThreads(String name, String color){
        this.name = name;
        this.color = color;
    }
    
    @Override
    public void run(){
        this.incrementBy10();
    }
    
    public void incrementBy10(){
        for(int i=0;i<10;i++)
            System.out.println(this.color + " " + this.name + ": " + i + " " + ANSI_RESET);
    }
}
