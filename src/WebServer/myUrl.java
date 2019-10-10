package WebServer;

import BIF.SWE1.interfaces.Url;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class myUrl implements Url {

    private String rawUrl;
    private Map<String, String> params = new LinkedHashMap<>();

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
        try {
            String query = this.getRawUrl().split("\\?")[1];
            Map<String,String> params = this.params;
            for (String param : query.split("&")){
                String[] keyValue = param.split("=", 2);
                String key = URLDecoder.decode(keyValue[0], "UTF-8");
                String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], "UTF-8"): "";
                if(!key.isEmpty()){
                    params.put(key,value);
                }
            }
            return params;
        } catch(UnsupportedEncodingException ex){
            throw new IllegalStateException(ex); // won't happen with UTF-8 encoding.
        }
    }

    @Override
    public int getParameterCount() {
        try {
            if(this.getRawUrl().split("\\?")[0] == this.rawUrl){
                return 0;
            }
            String query = this.getRawUrl().split("\\?")[1];
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