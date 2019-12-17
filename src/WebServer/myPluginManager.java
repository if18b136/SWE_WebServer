package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;
import BIF.SWE1.interfaces.Request;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class myPluginManager implements PluginManager {

    private List pluginList = new ArrayList<Plugin>();

    public Iterator<Plugin> iterator() {
        return this.pluginList.iterator();
    }

    private Iterable<Plugin> plugins = pluginList;

    // constructor um alle Plugins erstmal zu laden - vorhandene Plugins aus textdatei auslesen, dann ist das ganze dynamisch
    public myPluginManager(){
        try{
            File pluginFile = new File("D:\\#FH_Technikum\\Semester_3\\SWE\\MyWebServer\\src\\WebServer\\plugins.txt");
            Scanner sc = new Scanner(pluginFile);
            String pluginName;
            while (sc.hasNextLine()) {
                pluginName = sc.nextLine();
                add(pluginName);
            }
        } catch(IOException | IllegalAccessException | InstantiationException | ClassNotFoundException ioe){
            ioe.printStackTrace();
        }


    }
    @Override
    public Iterable<Plugin> getPlugins() {
        return this::iterator;
    }

    @Override
    public void add(Plugin plugin) {
        boolean exists = false;
        for(Object pluginTemp : pluginList){
            if(plugin == pluginTemp){
                exists = true;
            }
        }
        if(!exists && plugin != null){
            this.pluginList.add(plugin);
        }
    }

    @Override
    public void add(String plugin) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            String pluginString;

            if(!plugin.contains(".")) {
                pluginString = "WebServer." + plugin;
            }
            else {
                pluginString = plugin;
            }

            if(Class.forName(pluginString).newInstance() instanceof Plugin ) {
                Plugin newPlugin = (Plugin) Class.forName(pluginString).newInstance();
                this.add(newPlugin);
            }
            //Class pluginClass = Class.forName(pluginString);
            //Plugin newPlugin = (Plugin) pluginClass.getDeclaredConstructor().newInstance();
            //add(newPlugin);
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        this.pluginList = new ArrayList<Plugin>(); // alternative list.Clear(), garbage collector sollte hier jedoch gut greifen
    }

    public Plugin selectPlugin(Request req) {
        Plugin plugin = null;
        float max = 0;
        for (Plugin p : this.plugins) {
            float canHandle = p.canHandle(req);
            if (canHandle > max) {
                max = canHandle;
                plugin = p;
            }
        }
        return plugin;
    }
}
