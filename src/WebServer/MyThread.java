package WebServer;

import BIF.SWE1.interfaces.Plugin;
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
        //try {
            myUrl urlObj;
            myRequest reqObj = new myRequest();
            myResponse resObj = new myResponse();
            myPluginManager pmg = new myPluginManager();

            reqObj.setRequest(this.is);

            // check if request is valid
            if(reqObj.isValid()) {
                urlObj = reqObj.getUrl();

                // default webpage if path is empty
                if(urlObj.getPath().equals("/")) { // eventuell in PluginManager verschieben und generalisieren zsm mit anderen paths
                    String body = "<html><body><h1> Hello World </h1> Hello Blabla </body></html>";
                    resObj.setStatusCode(200);
                    resObj.addHeader("Content-Type", "text/html");
                    resObj.addHeader("Content-length", String.valueOf(body.length()));
                    resObj.addHeader("connection", "close");
                    resObj.setContentType("text/html");
                    resObj.setContent(body);

                    resObj.send(os);
                }
                else {
                    Plugin plugin = pmg.selectPlugin(reqObj);
                    resObj = (myResponse) plugin.handle(reqObj);
                    resObj.send(os);
                }
            }
            else {
                // error message to client that request is not valid
            }




            //BufferedWriter out = new BufferedWriter(new OutputStreamWriter(this.os));

            // body und buffered output sollte in response objekt gespeichert und dann ausgegeben werden.


//            out.write("HTTP/1.1 200 OK\r\n");
//            out.write("Content-Type: text/html\r\n");
//            out.write(String.format("Content-Length: %d\r\n",body.length()));
//            out.write("connection: close\r\n");
//            out.write("\r\n");
//            out.write(body);
//            out.flush();

        //} catch (IOException ex) {
        //    Logger.getLogger(MyRunnable.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }

}
