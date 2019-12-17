package uebungen;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;
import BIF.SWE1.interfaces.Request;
import WebServer.myPluginManager;
import WebServer.myRequest;

import java.io.InputStream;

public class UEB5 {

	public void helloWorld() {

	}

	public Request getRequest(InputStream inputStream) {
		myRequest req = new myRequest();
		if(inputStream != null){
			req.setRequest(inputStream);
		}
		return req;
	}

	public PluginManager getPluginManager() {
		myPluginManager mng = new myPluginManager();
		return mng;
	}

	public Plugin getStaticFilePlugin() {
		return null;
	}

	public void setStatiFileFolder(String s) {

	}

	public String getStaticFileUrl(String s) {
		return null;
	}
}
