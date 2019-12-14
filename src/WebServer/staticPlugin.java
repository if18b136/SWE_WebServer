package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

public class staticPlugin implements Plugin {

    public staticPlugin(){

    }

    @Override
    public float canHandle(Request req) {
        if(req.getUrl().getPath().contains("static")) {
            return (float) 0.9;
        }
        else {
            return 0;
        }
    }

    @Override
    public Response handle(Request req) {
        htmlConstructor html = new htmlConstructor();
        myResponse res = new myResponse();
        String htmlString = html.getStatic();
        res.setStatusCode(200);
        res.addHeader("Content-Type", "text/html");
        res.addHeader("Content-length", String.valueOf(htmlString.length()));
        res.addHeader("connection", "close");
        res.setContentType("text/html");
        res.setContent(htmlString);
        return res;
    }
}
