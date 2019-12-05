package WebServer;

import javaThreads.MyRunnable;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyThread extends Thread {
    
    private boolean running = true;
    private String threadName;

    private InputStream is;
    private OutputStream os;
    private BufferedReader in;
    private BufferedWriter out;

    public MyThread(String name, InputStream is, OutputStream os) {
        this.threadName = name;
        this.is = is;
        this.os = os;
    }

    public void stopMyThread() {
        running = false;
    }

    @Override
    public void run() {
        try {
            myUrl urlObj = new myUrl();
            myRequest reqObj = new myRequest();
            myResponse resObj = new myResponse();

            reqObj.setRequest(this.is);
            reqObj.isValid();
            //System.out.println("Request valid: " + reqObj.isValid() + ".");
            //System.out.println("Request Method: " + reqObj.getMethod() + ".");
            //System.out.println("Url from Request: " + reqObj.getUrl().getPath() + ".");
            urlObj = reqObj.getUrl();


            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(this.os));

            // body und buffered output sollte in response objekt gespeichert und dann ausgegeben werden.

            String body = "<html><body><h1> Hello World </h1> Hello Blabla </body></html>";

            out.write("HTTP/1.1 200 OK\r\n");
            out.write("Content-Type: text/html\r\n");
            out.write(String.format("Content-Length: %d\r\n",body.length()));
            //out.write("connection: close\r\n");
            out.write("\r\n");
            out.write(body);
            out.flush();

        } catch (IOException ex) {
            Logger.getLogger(MyRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
