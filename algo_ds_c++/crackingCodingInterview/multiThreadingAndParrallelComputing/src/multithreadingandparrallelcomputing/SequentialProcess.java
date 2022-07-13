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
public class SequentialProcess {
    private int counter = 0;
    private String color;
    private final String ANSI_RESET = "\u001B[0m";
    
    SequentialProcess(String color){
        this.color = color;
    }
    
    SequentialProcess(){
        
    }
    
    public int getCounter(){
        return counter;
    }
    
    public void incrementCounterBy10000(){
        for(int i=0;i<10000;i++) this.counter++;
    }
    
    public void incrementBy10(String name){
        for(int i =0;i<10;i++){
            System.out.println(color + " " + name + ": " + i + " " + ANSI_RESET);
        }
    }
}
