package uebungen;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;
import BIF.SWE1.interfaces.Request;
import WebServer.*;

import java.io.InputStream;

public class WebServer {

    private tempPlugin tempPlugin = new tempPlugin();
    private toLowerPlugin toLowerPlugin = new toLowerPlugin();
    private staticPlugin staticPlugin = new staticPlugin();

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
    public Plugin getToLowerPlugin() {
        return this.toLowerPlugin;
    }
    public Plugin getStaticPlugin() {
        return this.staticPlugin;
    }
    public htmlConstructor getHTML() { return new htmlConstructor(); }

}
