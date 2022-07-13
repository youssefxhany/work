/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadingandparrallelcomputing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssef hany
 */
public class SynchronizedChollection {
    //When using synchronizedList it puts an intrinsic lock on the add and remove method
    //SO one LOCK per object
    //Which means that independent operations can't execute at the same time
    //Conclusion: Not the best solution
    List<Integer> myList =  Collections.synchronizedList(new ArrayList());
    
    public void execute(){
        Thread t1 = new Thread(()->this.addElementToList());
        Thread t2 = new Thread(()->this.addElementToList());
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(SynchronizedChollection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("MY_LIST size is: " + myList.size());
    }
    
    public void addElementToList(){
        for(int i=0;i<1000;i++)
            myList.add(i);
    }
    
}
