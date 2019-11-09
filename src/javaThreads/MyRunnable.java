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
public class MyRunnable implements Runnable {
    private boolean running = true;
    private String threadName;

    public MyRunnable(String name) {
        this.threadName = name;
    }

    public void stop() {
        running = false;
    }

    public void run() {
        while(running) {
            try {
                Thread.sleep(100);
                System.out.println("Hello from Runnable " + threadName);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
