package WebServer;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Url;

import java.io.*;

import java.util.LinkedHashMap;
import java.util.Map;

import WebServer.myUrl;

//static import for header value for content length parse to int - recommended by intellij
import static java.lang.Integer.parseInt;

public class myRequest implements Request {

    private InputStream is;
    private String method;
    private boolean isValid = false;
    private myUrl url = new myUrl();
    private Map<String, String> headers = new LinkedHashMap<>();
    private int contentLength;
    private String contentType;
    private InputStream contentStream;
    private String contentString;
    private byte[] contentBytes;

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
                if(method == null) {    //After testing the html Bootstrap Version, some requests seemed to be completely empty
                    this.isValid = false;
                    return false;
                }
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
                        String line;
                        while((line = reader.readLine()) != null && !line.equals("")) {     // set Header-Value Map
                            String[] keyValue = line.split(":",2);
                            String key = keyValue[0].toLowerCase();
                            String value = keyValue.length > 1 ? keyValue[1]: "";

                            System.out.println(key + " : " + value);    //testing

                            this.headers.put(key,value);
                            if(key.equals("content-length")) {
                                this.contentLength = parseInt(value.trim());    // set contentLength
                            }
                            if(key.equals("content-type")) {
                                this.contentType = value.trim();    // set contentType
                            }
                        }

                        // after header is finished, we can read the content (we could also set content-length here manually)
                         if(reader.ready()){    // if no content will be following we want to skip the next part
                             if((line = reader.readLine()) != null && !line.equals("")) {
                                 this.contentStream = new ByteArrayInputStream(line.getBytes());

                                 ByteArrayOutputStream result = new ByteArrayOutputStream();
                                 byte[] buffer = new byte[1024];
                                 int length;
                                 while ((length = this.contentStream.read(buffer)) != -1) {
                                     result.write(buffer, 0, length);
                                 }
                                 this.contentString =  result.toString("UTF-8");

                                 this.contentBytes = this.contentString.getBytes();
                             }
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
        return this.contentLength;
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public InputStream getContentStream() {
        if(this.contentStream != null) {
            return this.contentStream;
        }
        return null;
    }

    @Override
    public String getContentString() {
       return this.contentString;
    }

    @Override
    public byte[] getContentBytes() {
        return this.contentBytes;

    }
}
