package uebungen;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import WebServer.myPlugin;
import WebServer.myRequest;
import WebServer.myResponse;

import java.io.InputStream;

public class UEB3 {

	public void helloWorld() {

	}

    public Request getRequest(InputStream inputStream) {
        myRequest req = new myRequest();
        if(inputStream != null){
            req.setRequest(inputStream);
        }
        return req;
    }

    public Response getResponse() {
        myResponse resp = new myResponse();
        return resp;
    }
	public Plugin getTestPlugin() {
        myPlugin plug = new myPlugin();
        return plug;
	}
}
