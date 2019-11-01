package uebungen;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import BIF.SWE1.interfaces.Url;

import WebServer.myUrl;
import WebServer.myRequest;

import java.io.InputStream;

public class UEB2 {

	public void helloWorld() {

	}

	public Url getUrl(String path) {
		myUrl obj = new myUrl();
		if(path != null && !path.isEmpty()){
			obj.setUrl(path);
		}
		return obj;
	}

	public Request getRequest(InputStream inputStream) {
		myRequest req = new myRequest();
		if(inputStream != null){
			req.setRequest(inputStream);
		}
		return req;
	}

	public Response getResponse() {
		return null;
	}
}
