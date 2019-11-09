/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaThreads;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arthur
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //runnable();
        thread();
        //lock();
    }

    private static void runnable() {
        try {
            System.out.println("Starting Threads");
            List<MyRunnable> threads = new LinkedList<MyRunnable>();
            for (int i = 0; i < 10; i++) {
                MyRunnable t = new MyRunnable("Thread " + i);
                new Thread(t).start();
                threads.add(t);
            }
            System.out.println("Threads are running");
            Thread.sleep(10000);
            System.out.println("Stopping Threads");
            for (MyRunnable t : threads) {
                t.stop();
            }
            System.out.println("Threads stopped");
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void thread() {
        try {
            System.out.println("Starting Threads");
            List<MyThread> threads = new LinkedList<MyThread>();
            for (int i = 0; i < 10; i++) {
                MyThread t = new MyThread("Thread " + i);
                t.start();
                threads.add(t);
            }
            System.out.println("Threads are running");
            Thread.sleep(10000);
            System.out.println("Stopping Threads");
            for (MyThread t : threads) {
                t.stopMyThread();
                t.join();
            }
            System.out.println("Threads stopped");
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void lock() {
        try {
            Queue<String> queue = new LinkedList<String>();

            System.out.println("Starting Threads");
            List<LockThread> threads = new LinkedList<LockThread>();
            for (int i = 0; i < 10; i++) {
                LockThread t = new LockThread(queue);
                t.start();
                threads.add(t);
            }
            System.out.println("Threads are running");


            for (int i = 0; i < 100; i++) {
                //synchronized (LockThread.SYNC) {
                    queue.add("Item " + i);
                //}
                Thread.sleep(10);
            }

            System.out.println("Stopping Threads");
            for (LockThread t : threads) {
                t.stopMyThread();
                t.join();
            }
            System.out.println("Threads stopped");
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
