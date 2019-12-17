package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

import java.io.File;

public class staticPlugin implements Plugin {

    public staticPlugin(){

    }

    @Override
    public float canHandle(Request req) {
        if(req.isValid()) {
            if(req.getUrl().getPath().contains("static")) {
                return (float) 0.9;
            }
            else if(req.getUrl().getPath().contains("/")) {
                return (float) 0.1;
            }
        }
        return 0;
    }

    @Override
    public Response handle(Request req) {
        // Check folder for the file
        String staticDir = "D:\\#FH_Technikum\\Semester_3\\SWE\\MyWebServer\\src\\WebServer\\plugins.txt";
        boolean fileExists = new File (staticDir + req.getUrl().getPath()).isFile();
        htmlConstructor html = new htmlConstructor();
        myResponse res = new myResponse();
        if(fileExists) {
            String htmlString = html.getStatic();
            res.setStatusCode(200);
            res.addHeader("Content-Type", "text/html");
            res.addHeader("Content-length", String.valueOf(htmlString.length()));
            res.addHeader("connection", "close");
            res.setContentType("text/html");
            res.setContent(htmlString);
        }
        else {
            res.setStatusCode(404);
            res.addHeader("connection", "close");
        }
        return res;
    }
}
