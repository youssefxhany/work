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
public class StockMarketUpdater implements Runnable{
    
    @Override
    public void run(){
        System.out.println("Updating and downloading stock data from the server...");
    }
}
