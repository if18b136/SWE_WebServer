package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

public class myPlugin implements Plugin {

    @Override
    public float canHandle(Request req) {
        return (float) 0.5;
    }

    @Override
    public Response handle(Request req) {
        return null;
    }
}
