package WebServer;

import BIF.SWE1.interfaces.Response;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.LinkedHashMap;

public class myResponse implements Response {

    private Map<String, String> responseMap = new LinkedHashMap<>();
    private Integer statusCode;
    private String status;

    @Override
    public Map<String, String> getHeaders() {
        return this.responseMap;
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
    public void setContentType(String contentType) {

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
        return null;
    }

    @Override
    public void setServerHeader(String server) {

    }

    @Override
    public void setContent(String content) {

    }

    @Override
    public void setContent(byte[] content) {

    }

    @Override
    public void setContent(InputStream stream) {

    }

    @Override
    public void send(OutputStream network) {

    }
}
