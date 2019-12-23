package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class staticPlugin implements Plugin {

    private static String staticDir;

    public staticPlugin(){
        staticDir = "D:\\#FH_Technikum\\Semester_3\\SWE\\MyWebServer\\src\\WebServer\\staticFiles\\";
    }

    public String getStaticDir() {
        return staticDir;
    }

    public void setStaticDir(String s) {
        staticDir = s;
    }

    @Override
    public float canHandle(Request req) {
        if(req.isValid()) {
            if(req.getUrl().getRawUrl().contains(staticDir)) {
                return (float) 0.9;
            }
            else if(req.getUrl().getPath().equals("/")) {
                return (float) 0.8;
            }
            else if(req.getUrl().getPath().contains("/")) {
                return (float) 0.1;
            }
        }
        return 0;
    }

    @Override
    public Response handle(Request req) {
        htmlConstructor html = new htmlConstructor();
        myResponse res = new myResponse();
        String htmlString = html.getStatic();

        if(req.getUrl().getPath().equals("/")) {
            res.setStatusCode(200);
            res.addHeader("Content-Type", "text/html");
            res.addHeader("Content-length", String.valueOf(htmlString.length()));
            res.addHeader("connection", "keep-alive");
            res.setContentType("text/html");
            res.setContent(htmlString);
        }
        else if(req.getUrl().getPath().contains("/deploy/MDB")) {
            System.out.println("Working Directory = " +
                    System.getProperty("user.dir"));
            String path = req.getUrl().getRawUrl();
            String Diff = path.replace("/","\\");
            Diff = String.join("",System.getProperty("user.dir"),Diff);
            System.out.println("Checking for: " + path + " or " + Diff);
            boolean fileExists = new File (path).isFile();
            System.out.println(fileExists);
            fileExists = new File (Diff).isFile();
            System.out.println(fileExists);


            if(fileExists) {
                System.out.println("Worked.");
                String contentType = "";
                String nameSplit = req.getUrl().getPath().split("/")[3];

                switch (nameSplit) {
                    case "js":
                        contentType = "text/javascript";
                        break;
                    case "css":
                        contentType = "text/css";
                        break;
                    case "ico":
                        contentType = "image/vnd.microsoft.icon";
                        break;
                    case "font":
                        contentType = "font/woff2";
                }
                try{

                    String data = "";// = Files.readString(Paths.get(Diff));

                    BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(Diff),"UTF-8"));

                    String line;
                    while((line = input.readLine()) != null) {

                        data = String.join("\r\n", data, line);
                    }
                    input.close();
                    //data = data.replaceAll("\"", "\\\"");
                    //data = data.replaceAll("\'", "\\\'");
                    //System.out.println(data);
                    res.setStatusCode(200);
                    float test = data.length();
                    System.out.println(test + " Content Length");
                    res.addHeader("Content-length", String.valueOf(data.length()));
                    res.addHeader("connection", "keep-alive");
                    res.addHeader("Content-Type", contentType);
                    res.setContentType(contentType);
                    res.setContent(data);

                }
                catch(IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        else {
            String path = req.getUrl().getRawUrl();
            boolean fileExists = new File (path).isFile();

            if(fileExists) {    // einfach Inhalt anzeigen lassen - MIME Type richtig setzen
                res.setStatusCode(200);
                res.addHeader("Content-Type", "text/html");
                res.addHeader("Content-length", String.valueOf(htmlString.length()));
                res.addHeader("connection", "keep-alive");
                res.setContentType("text/html");
                res.setContent(htmlString); // hier sollte nun der reine content eingetragen werden. Rest des Contents evtl in send zusammenschnipseln.
            }
            else {
                res.setStatusCode(404);
                res.addHeader("connection", "close");
            }
        }
        return res;
    }
}
