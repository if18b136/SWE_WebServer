package WebServer;

import BIF.SWE1.interfaces.Plugin;
import java.io.*;

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
            htmlConstructor html = new htmlConstructor();

            reqObj.setRequest(this.is);

            // check if request is valid
            if(reqObj.isValid()) {
                urlObj = reqObj.getUrl();

                // default webpage if path is empty
                if(urlObj.getPath().equals("/") || urlObj.getPath().equals("//") ) { // eventuell in PluginManager verschieben und generalisieren zsm mit anderen paths
                    String htmlString = html.getHome();
                    //String body = "<html><body><h1> Hello World </h1> Hello Blabla </body></html>";
                    resObj.setStatusCode(200);
                    resObj.addHeader("Content-Type", "text/html");
                    resObj.addHeader("Content-length", String.valueOf(htmlString.length()));
                    resObj.addHeader("connection", "close");
                    resObj.setContentType("text/html");
                    resObj.setContent(htmlString);

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
    }

}
