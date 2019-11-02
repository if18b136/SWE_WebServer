package uebungen;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import BIF.SWE1.interfaces.Url;

import WebServer.myUrl;
import WebServer.myRequest;
import WebServer.myResponse;

import java.io.InputStream;

public class UEB2 {

	public void helloWorld() {

	}

	public Url getUrl(String path) {
		myUrl url = new myUrl();
		if(path != null && !path.isEmpty()){
			url.setUrl(path);
		}
		return url;
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
}
