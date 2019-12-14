package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

public class tempPlugin implements Plugin {
    @Override
    public float canHandle(Request req) {
        if(req.getUrl().getPath().contains("temp")) {
            return (float) 0.9;
        }
        else {
            return 0;
        }
    }

    @Override
    public Response handle(Request req) {
        myResponse res = new myResponse();
        String body = "<html><body><h1> Hello World </h1> temperature Plugin </body></html>";
        res.setStatusCode(200);
        res.addHeader("Content-Type", "text/html");
        res.addHeader("Content-length", String.valueOf(body.length()));
        res.addHeader("connection", "close");
        res.setContentType("text/html");
        res.setContent(body);
        return res;
    }
}
