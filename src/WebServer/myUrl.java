package WebServer;

import BIF.SWE1.interfaces.Url;
import java.util.Map;

public class myUrl implements Url {

    private String rawUrl;

    public void setUrl(String path){
        this.rawUrl = path;
    }

    @Override
    public String getRawUrl() {
        return this.rawUrl;
    }

    @Override
    public String getPath() {

        return rawUrl.split("\\?")[0];
    }

    @Override
    public Map<String, String> getParameter() {
        return null;
    }

    @Override
    public int getParameterCount() {
        return 0;
    }

    @Override
    public String[] getSegments() {
        return new String[0];
    }

    @Override
    public String getFileName() {
        return null;
    }

    @Override
    public String getExtension() {
        return null;
    }

    @Override
    public String getFragment() {
        return null;
    }
}