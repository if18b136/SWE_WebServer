/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaThreads;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arthur
 */
public class MyThread extends Thread {

    
    private boolean running = true;
    private String threadName;

    public MyThread(String name) {
        this.threadName = name;
    }

    public void stopMyThread() {
        running = false;
    }

    @Override
    public void run() {
        while(running) {
            try {
                Thread.sleep(100);
                System.out.println("Hello from Thread " + threadName);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
