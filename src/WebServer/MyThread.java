package WebServer;

import javaThreads.MyRunnable;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyThread extends Thread {
    
    private boolean running = true;
    private String threadName;
    private BufferedReader in;
    private BufferedWriter out;

    public MyThread(String name, BufferedReader in, BufferedWriter out) {
        this.threadName = name;
        this.in = in;
        this.out = out;
    }

    public void stopMyThread() {
        running = false;
    }

    @Override
    public void run() {
        try {
            myUrl urlObj = new myUrl();

            String line;
            line = in.readLine();
            urlObj.setUrl(line);

            System.out.println("Path: " + urlObj.getPath());
            System.out.println("Parameter Count: " + urlObj.getParameterCount());
            urlObj.getParameter();

            System.out.println(urlObj.getUrl());
            while((line = in.readLine()) != null) {
                System.out.println(line);
                if("".equals(line)){
                    break;
                }
            }
            String body = "<html><body><h1> Hello World </h1> Hello Blabla </body></html>";
            //BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            out.write("HTTP/1.1 200 OK\r\n");
            out.write("Content-Type: text/html\r\n");
            out.write(String.format("Content-Length: %d\r\n",body.length()));
            out.write("connection: close\r\n");
            out.write("\r\n");
            out.write(body);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(MyRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
