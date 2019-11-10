package WebServer;

import BIF.SWE1.interfaces.Response;

import java.io.*;
import java.util.Map;
import java.util.LinkedHashMap;

public class myResponse implements Response {

    private Map<String, String> responseMap = new LinkedHashMap<>();
    private Integer statusCode;
    private String status;
    private String contentType;
    private String serverHeader = "BIF-SWE1-Server";
    private String content;
    private OutputStream network;


    @Override
    public Map<String, String> getHeaders() {
        return this.responseMap;
    }

    @Override
    public int getContentLength() {
        try {
            return this.content.getBytes("UTF-8").length;
        } catch(UnsupportedEncodingException uex) {
            System.out.println(uex);
            return 0;
        }
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public void setStatusCode(int status) {
        this.statusCode = status;
    }

    @Override
    public String getStatus() {
        switch(this.statusCode) {
            case 200:
                this.status = "200 OK";
                break;
            case 404:
                this.status = "404 NOT FOUND";
                break;
            case 500:
                this.status = "500 INTERNAL SERVER ERROR";
                break;
            default:
                break;
        }
        return this.status;
    }

    @Override
    public void addHeader(String header, String value) {
        this.responseMap.put(header,value);
    }

    @Override
    public String getServerHeader() {
        return this.serverHeader;
    }

    @Override
    public void setServerHeader(String server) {
        this.serverHeader = server;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = String.valueOf(content);
    }

    @Override
    public void setContent(InputStream stream) {
        this.content = String.valueOf(stream);
    }

    @Override
    public void send(OutputStream network) {
        try {
            for(Map.Entry elem : this.responseMap.entrySet()) {
                String line = elem.getKey() + ": " + elem.getValue();
                network.write(line.getBytes());
            }
            network.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
