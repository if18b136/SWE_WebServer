package WebServer;

import BIF.SWE1.interfaces.Url;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class myUrl implements Url {

    public myUrl(){
        //Code goes here
    }

    private String rawUrl;
    private String path;
    private Map<String, String> params = new LinkedHashMap<>();
    private String fragment;
    private String[] segments;
    private String fileName;
    private String extension;

    public void setUrl(String path){
        this.rawUrl = path;
    }
    public String getUrl() {
        return this.rawUrl;
    }

    @Override
    public String getRawUrl() {
        return this.rawUrl;
    }

    @Override
    public String getPath() {
        if(this.rawUrl == null) {
            return null;
        }
        String[] split = this.rawUrl.split(" ");
        // satisfy the unit Tests
        if(split.length != 3) {

            this.path = this.rawUrl.split("\\?")[0];
            this.path = this.path.split("#")[0];
            return this.path;
        }
        else {
            this.path = split[1].split("\\?")[0];
            this.path = split[1].split("#")[0]; // noch nicht in Webserver getestet
            return this.path;
        }
    }

    @Override
    public Map<String, String> getParameter() {
        try {
            String parameter;
            String[] query;
            String[] split = this.rawUrl.split(" ");
            if(split.length == 3) {
                query = split[1].split("\\?");
                if(query.length == 2) {
                    parameter = query[1];
                }
                else {
                    return this.params;
                }
            }
            else {
                String[] split_query = this.rawUrl.split("\\?");
                if(split_query.length < 2) {
                    return this.params;
                }
                else {
                    parameter = split_query[1];
                }
            }

            Map<String,String> params = this.params;
            for (String param : parameter.split("&")){
                String[] keyValue = param.split("=", 2);
                String key = URLDecoder.decode(keyValue[0], "UTF-8");
                String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], "UTF-8"): "";
                if(!key.isEmpty()){
                    params.put(key,value);
                }
            }
            this.params = params;
            return this.params;
        } catch(UnsupportedEncodingException ex){
            throw new IllegalStateException(ex); // won't happen with UTF-8 encoding.
        }
    }

    @Override
    public int getParameterCount() {
        try {
            if(this.rawUrl == null || this.getRawUrl().split("\\?")[0] == this.rawUrl){
                return 0;
            }
            String query;
            String[] split = this.rawUrl.split(" ");
            if(split.length == 3) {
                query = split[1].split("\\?")[1];
            }
            else {
                String[] split_query = this.rawUrl.split("\\?");
                if(split_query.length < 2) {
                    return 0;
                }
                query = split_query[1];
            }
            Map<String,String> params = this.params;
            for (String param : query.split("&")){
                String[] keyValue = param.split("=", 2);
                String key = URLDecoder.decode(keyValue[0], "UTF-8");
                String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], "UTF-8"): "";
                if(!key.isEmpty()){
                    params.put(key,value);
                }
            }
            return params.size();
        } catch(UnsupportedEncodingException ex){
            throw new IllegalStateException(ex); // won't happen with UTF-8 encoding.
        }
    }

    @Override
    public String[] getSegments() {
        if(this.rawUrl == null) {
            return null;
        }
        if(this.path == null) { //please the unit test gods, should not be necessary in WebServer
            getPath();
        }
        this.segments = this.path.replaceFirst("^/", "").split("/");
        return this.segments;
    }

    @Override
    public String getFileName() {
        if(this.rawUrl == null || this.path == null) {
            return "";
        }
        int segments = this.segments.length;
        this.fileName = this.segments[segments - 1];
        return this.fileName;
    }

    @Override
    public String getExtension() {
        if(this.fileName == null) {
            getFileName();
        }
        this.extension = this.fileName.split(".")[1];
        return this.extension;
    }

    @Override
    public String getFragment() {
        if(this.rawUrl == null) {
            return "";
        }
        this.fragment = this.rawUrl.split("#")[1];

        return this.fragment;
    }
}