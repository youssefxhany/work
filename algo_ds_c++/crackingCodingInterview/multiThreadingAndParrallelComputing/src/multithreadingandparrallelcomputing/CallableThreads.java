/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadingandparrallelcomputing;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author youssef hany
 */
public class CallableThreads implements Callable<String>{
    private final int id;

    public CallableThreads(int id) {
        this.id = id;
    }
    
    @Override
    public String call() throws Exception{
        TimeUnit.MILLISECONDS.sleep(2000);
        return "ID: " + this.id;
    }
}
