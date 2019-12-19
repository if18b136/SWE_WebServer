package uebungen;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;
import BIF.SWE1.interfaces.Request;
import WebServer.myPluginManager;
import WebServer.myRequest;
import WebServer.staticPlugin;

import java.io.InputStream;

public class UEB5 {

	private staticPlugin staticPlugin = new staticPlugin();


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
		return  this.staticPlugin;
	}

	public void setStatiFileFolder(String s) {
		this.staticPlugin.setStaticDir("D:\\#FH_Technikum\\Semester_3\\SWE\\MyWebServer\\deploy\\" + s + "\\");
	}

	public String getStaticFileUrl(String s) {

		String dirURL = this.staticPlugin.getStaticDir();

		return dirURL + s;
	}
}
