package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

/**
 * <h3>toLower Plugin</h3>
 */
public class toLowerPlugin implements Plugin {


    /**
     * if the keyword is in the request path, return a high float
     * @param req the client request
     * @return 0.9 if request can be handled by tolower plugin or 0 else
     */
    @Override
    public float canHandle(Request req) {
        if(req.isValid()) {
            if(req.getUrl().getPath().contains("toLower")) {
                return (float) 0.9;
            }
        }
        return 0;
    }

    /**
     * Differentiate between post and get requests - post means there is a text to be handled
     * Decoding of special characters happens in the html construction
     * cut off the "textfield=" part of the post request and set lower text
     * Add correct headers and content
     * @param req the client request
     * @return the server response
     */
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
