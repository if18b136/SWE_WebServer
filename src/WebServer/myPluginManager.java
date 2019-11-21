package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.PluginManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class myPluginManager implements PluginManager {

    private List pluginList = new ArrayList<Plugin>();
    public Iterator<Plugin> iterator() {
        return this.pluginList.iterator();
    }

    private Iterable<Plugin> plugins;

    @Override
    public Iterable<Plugin> getPlugins() {
        return plugins;
    }

    @Override
    public void add(Plugin plugin) {

    }

    @Override
    public void add(String plugin) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

    }

    @Override
    public void clear() {

    }
}
