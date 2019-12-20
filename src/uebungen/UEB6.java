package uebungen;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;
import BIF.SWE1.interfaces.Request;
import WebServer.*;

import java.io.InputStream;
import java.time.LocalDate;

public class UEB6 {

	private WebServer.staticPlugin staticPlugin = new staticPlugin();
	private WebServer.naviPlugin naviPlugin = new naviPlugin();
	private WebServer.tempPlugin tempPlugin = new tempPlugin();
	private WebServer.toLowerPlugin toLowerPlugin = new toLowerPlugin();


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

	public Plugin getTemperaturePlugin() {
		return this.tempPlugin;
	}

	public Plugin getNavigationPlugin() {
		return this.naviPlugin;
	}

	public Plugin getToLowerPlugin() {
		return this.toLowerPlugin;
	}

	public String getTemperatureUrl(LocalDate localDate, LocalDate localDate1) {
		return null;
	}

	public String getTemperatureRestUrl(LocalDate localDate, LocalDate localDate1) {
		return null;
	}

	public String getNaviUrl() {
		return null;
	}

	public String getToLowerUrl() {
		return null;
	}
}
