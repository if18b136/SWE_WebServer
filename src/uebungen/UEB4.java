package uebungen;

import BIF.SWE1.interfaces.PluginManager;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import WebServer.myRequest;
import WebServer.myResponse;
import WebServer.myPluginManager;

import java.io.InputStream;

public class UEB4 {

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

	public PluginManager getPluginManager() {
		myPluginManager mng = new myPluginManager();
		return mng;
	}
}
