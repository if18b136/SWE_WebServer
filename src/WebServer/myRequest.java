package WebServer;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Url;

import java.io.*;

import java.util.LinkedHashMap;
import java.util.Map;

import WebServer.myUrl;

public class myRequest implements Request {

    private InputStream is;
    private String method;
    private boolean isValid = true;
    private myUrl url = new myUrl();
    private Map<String, String> headers = new LinkedHashMap<>();;

    public void setRequest(InputStream inputStream) {
        this.is = inputStream;
    }

    public InputStream getInputStream() {
        return this.is;
    }

    @Override
    public boolean isValid() {
        if(this.method == null || this.method.isEmpty()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(this.is));
                String method = reader.readLine();
                String[] first_line = method.split(" ");
                if(first_line.length == 3){
                    this.method = first_line[0].toUpperCase();
                    this.url.setUrl(first_line[1]);

                    if(!this.method.equals("GET") && !this.method.equals("POST")) {
                        this.isValid = false;
                        return false;
                    }
                    else {
                        this.isValid = true;
                        // set Headers and Content here?
                        String line;
                        while((line = reader.readLine()) != null && !line.equals("")) {
                            String[] keyValue = line.split(":",2);
                            String key = keyValue[0].toLowerCase();
                            String value = keyValue.length > 1 ? keyValue[1]: "";
                            System.out.println(key + " : " + value);
                            this.headers.put(key,value);
                        }
                        return true;
                    }
                }
                else {
                    this.isValid = false;
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return this.isValid;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public myUrl getUrl() {
        return this.url;
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public int getHeaderCount() {
        return this.headers.size();
    }

    @Override
    public String getUserAgent() {
        if(this.headers.size() == 0) { // otherwise the unit test wont initialize anything
            isValid();
        }
        if(this.headers.containsKey("user-agent")) {
            return this.headers.get("user-agent");
        }
        return "";
    }

    @Override
    public int getContentLength() {
        return 0;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public InputStream getContentStream() {
        return null;
    }

    @Override
    public String getContentString() {
        return null;
    }

    @Override
    public byte[] getContentBytes() {
        return new byte[0];
    }
}
