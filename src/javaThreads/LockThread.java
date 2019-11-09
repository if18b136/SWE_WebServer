/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaThreads;

import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arthur
 */
public class LockThread extends Thread {

    private Queue<String> strings;
    private boolean running = true;
    public final static Object SYNC = new Object();

    public void stopMyThread() {
        running = false;
    }

    public LockThread(Queue<String> strings) {
        this.strings = strings;
    }

    @Override
    public void run() {
        while (running) {
            boolean workDone = false;
            try {
                String str = null;

                //synchronized (SYNC) {
                    if (!strings.isEmpty()) {
                        str = strings.poll();
                    }
                //}
                if (str != null) {
                    try {
                        System.out.println(str);
                        // Do some work
                        Thread.sleep(20);
                        workDone = true;
                    } catch (Exception ex) {
                        //synchronized (SYNC) {
                            strings.add(str);
                        //}
                        System.out.println(ex);
                    }
                }
                if (!workDone) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(LockThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
