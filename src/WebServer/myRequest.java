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

    public void setRequest(InputStream inputStream) {
        this.is = inputStream;
    }

    public InputStream getInputStream() {
        return this.is;
    }

    @Override
    public boolean isValid() {
        if(this.method == null || this.method.isEmpty()) {
            this.isValid = false;
            return false;
        }
        if(!this.method.equals("GET") && !this.method.equals("POST")){
            this.isValid = false;
            return false;
        }
        return this.isValid;
    }

    @Override
    public String getMethod() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.is));
            String method = reader.readLine();
            this.method = method.split(" ")[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.method;
    }

    @Override
    public Url getUrl() {
        return null;
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
