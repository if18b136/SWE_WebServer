package uebungen;

import BIF.SWE1.interfaces.Url;
import WebServer.myUrl;

public class UEB1 {


	public Url getUrl(String path) {
		myUrl obj = new myUrl();
		obj.setUrl(path);
		return obj;
	}


	public void helloWorld() {
	}
}
