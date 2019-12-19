package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

import java.io.File;

public class staticPlugin implements Plugin {

    private static String staticDir;

    public staticPlugin(){
        staticDir = "D:\\#FH_Technikum\\Semester_3\\SWE\\MyWebServer\\src\\WebServer\\staticFiles\\";
    }

    public String getStaticDir() {
        return staticDir;
    }

    public void setStaticDir(String s) {
        staticDir = s;
    }

    @Override
    public float canHandle(Request req) {
        if(req.isValid()) {
            if(req.getUrl().getRawUrl().contains(staticDir)) {
                return (float) 0.9;
            }
            else if(req.getUrl().getPath().equals("/")) {
                return (float) 0.8;
            }
            else if(req.getUrl().getPath().contains("/")) {
                return (float) 0.1;
            }
        }
        return 0;
    }

    @Override
    public Response handle(Request req) {
        htmlConstructor html = new htmlConstructor();
        myResponse res = new myResponse();
        String htmlString = html.getStatic();

        if(req.getUrl().getPath().equals("/")) {
            res.setStatusCode(200);
            res.addHeader("Content-Type", "text/html");
            res.addHeader("Content-length", String.valueOf(htmlString.length()));
            res.addHeader("connection", "close");
            res.setContentType("text/html");
            res.setContent(htmlString);
        }
        else {
            String path = req.getUrl().getRawUrl();
            boolean fileExists = new File (path).isFile();

            if(fileExists) {
                res.setStatusCode(200);
                res.addHeader("Content-Type", "text/html");
                res.addHeader("Content-length", String.valueOf(htmlString.length()));
                res.addHeader("connection", "close");
                res.setContentType("text/html");
                res.setContent(htmlString); // hier sollte nun der reine content eingetragen werden. Rest des Contents evtl in send zusammenschnipseln.
            }
            else {
                res.setStatusCode(404);
                res.addHeader("connection", "close");
            }
        }
        return res;
    }
}
