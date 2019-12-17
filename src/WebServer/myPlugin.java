package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class myPlugin implements Plugin {

    @Override
    public float canHandle(Request req) {
        if(req.isValid()) {
            if(req.getUrl().getPath().equals("/") ) {
                return (float) 1.0;
            }
            else if(req.getUrl().getRawUrl().contains("test")) {
                return (float) 0.1;
            }
        }
        return (float) 0.0;
    }

    @Override
    public Response handle(Request req) {
        myResponse resObj = new myResponse();
        htmlConstructor html = new htmlConstructor();
        String htmlString = html.getHome();

        resObj.setStatusCode(200);
        resObj.addHeader("Content-Type", "text/html");
        resObj.addHeader("Content-length", String.valueOf(htmlString.length()));
        resObj.addHeader("connection", "close");
        resObj.setContentType("text/html");
        resObj.setContent(htmlString);

        return resObj;
    }

}
