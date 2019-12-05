package WebServer;

import BIF.SWE1.interfaces.Response;

import java.io.*;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class myResponse implements Response {

    private Map<String, String> responseMap = new LinkedHashMap<>();
    private Integer statusCode; //integer because int is set to 0 standard
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
            uex.printStackTrace();
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
                this.status = "404 Not Found";
                break;
            case 500:
                this.status = "500 Internal Server Error";
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
        this.responseMap.put("Server",this.serverHeader);
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = new String(content);
    }

    @Override
    public void setContent(InputStream stream) {
        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = stream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            this.content = result.toString("UTF-8");
        } catch(IOException use) {
            use.printStackTrace();
        }
    }

    @Override
    // every string that gets written into the network needs a linefeed! -> Add "\r\n" to every string
    public void send(OutputStream network) {
        try {
            if(this.statusCode != null) {
                getStatus();
                String line = "HTTP/1.1 " + this.status + "\r\n";
                network.write(line.getBytes());
            }
            // add content type as a header where?
            for(Map.Entry elem : this.responseMap.entrySet()) {
                String line = elem.getKey() + ": " + elem.getValue() + "\r\n";
                network.write(line.getBytes());
            }
            String lineEnd = "\r\n";
            network.write(lineEnd.getBytes());
            if(this.content != null) {
                network.write(this.content.getBytes());
            }
            else {
                if(this.contentType != null) {
                    throw new IllegalStateException("Setting a content type but no content is not allowed");
                    //throw new java.lang.Error("Setting a content type but no content is not allowed");
                }
            }
            network.flush();    // added while server configuration
            network.close();
        } catch (Exception ex) {
            //ex.printStackTrace();
            //Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException("Setting a content type but no content is not allowed");
            // why do you have to throw the exception here again?
        }
    }
}
