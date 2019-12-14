package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class myPlugin implements Plugin {

    @Override
    public float canHandle(Request req) {
        try {
            String request = req.getUrl().getPath();
            File pluginFile = new File("D:\\#FH_Technikum\\Semester_3\\SWE\\MyWebServer\\src\\WebServer\\plugins.txt");
            Scanner sc = new Scanner(pluginFile);
            String plugin;
            while (sc.hasNextLine()) {
                plugin = sc.nextLine();
                if (plugin.equals(request)) {
                    return 1;
                }
                if(req.getUrl().getRawUrl().contains("test")) {
                    return 1;
                }
            }
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
        }
        return (float) 0.0;
    }

    @Override
    public Response handle(Request req) {
        myResponse res = new myResponse();
        res.setStatusCode(200);
        res.setServerHeader("foo");
        res.setContentType("text/html");
        res.setContent("<html><body><h1> Hello World </h1> Hello User </body></html>");
        return res;
    }
/*
    @Override
    public Response handle(Request req) {
        //select Plugin that will handle the request
        selectPlugin(this.mgr,req);
        myResponse res = new myResponse();
        // get content into new response object
        return res;
    }

    private Plugin selectPlugin(PluginManager mgr, Request req) {
        Plugin plugin = null;
        float max = 0;
        for (Plugin p : mgr.getPlugins()) {
            float canHandle = p.canHandle(req);
            if (canHandle > max) {
                max = canHandle;
                plugin = p;
            }
        }
        return plugin;
    }
 */

}
