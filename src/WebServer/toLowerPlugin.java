package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

public class toLowerPlugin implements Plugin {
    @Override
    public float canHandle(Request req) {
        if(req.isValid()) {
            if(req.getUrl().getPath().contains("toLower")) {
                return (float) 0.9;
            }
        }
        return 0;
    }

    @Override
    public Response handle(Request req) {
        htmlConstructor html = new htmlConstructor();
        myResponse res = new myResponse();

        if(req.getMethod().equals("POST")) {
            String text = req.getContentString();
            System.out.println(text);
            text = text.toLowerCase().substring(12);
            html.setLowerText(text);
        }

        String data = html.getToLower();

        res.setStatusCode(200);
        res.addHeader("Content-Type", "text/html");
        res.addHeader("Content-length", String.valueOf(data.length()));
        res.addHeader("connection", "keep-alive");
        res.setContentType("text/html");
        res.setContent(data);

        return res;
    }
}
