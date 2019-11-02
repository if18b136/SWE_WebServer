package WebServer;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Url;

import java.io.*;

import java.util.Map;

import WebServer.myUrl;

public class myRequest implements Request {

    private InputStream is;
    private String method;
    private boolean isValid = true;
    private myUrl url = new myUrl();

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

                    if(!this.method.equals("GET") && !this.method.equals("POST")){
                        this.isValid = false;
                        return false;
                    }
                    else{
                        this.isValid = true;
                        return true;
                    }
                }
                else{
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
    public Url getUrl() {
        return this.url;
    }

    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    @Override
    public int getHeaderCount() {
        return 0;
    }

    @Override
    public String getUserAgent() {
        return null;
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
