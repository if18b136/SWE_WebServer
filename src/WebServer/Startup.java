package WebServer;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Startup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        listen();
    }

    private static void listen(){
        try {
            java.net.ServerSocket listener = new java.net.ServerSocket(8081);
            List<Thread> threads = new LinkedList<Thread>();
            int thread_num = 1;

            tempThread temp = new tempThread("Thread " + thread_num);
            temp.start();
            threads.add(temp);
            thread_num++;

            while(true){
                Socket s = listener.accept();
                //System.out.println("New client connected : " + s);

                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();



                MyThread t = new MyThread("Thread " + thread_num, is, os);
                t.start();
                threads.add(t);
                thread_num++;
            }
            //for (MyThread t : threads) {
            //    t.stopMyThread();
            //    t.join();
            //}
            //listener.close();
        } catch (IOException ex) {
            Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
