package WebServer;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class staticPlugin implements Plugin {

    private static String staticDir;

    public staticPlugin(){
        //"D:\\#FH_Technikum\\Semester_3\\SWE\\MyWebServer\\
        staticDir = "/deploy/staticFiles/";
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

        if(req.getUrl().getPath().equals("/")) {
            String htmlString = html.getHome();
            res.setStatusCode(200);
            res.addHeader("Content-Type", "text/html");
            res.addHeader("Content-length", String.valueOf(htmlString.length()));
            res.addHeader("connection", "keep-alive");
            res.setContentType("text/html");
            res.setContent(htmlString);
        }
        else if(req.getUrl().getPath().contains("/deploy/MDB") || req.getUrl().getPath().contains(staticDir)) {
            //System.out.println("Working Directory = " + System.getProperty("user.dir"));
            String path = req.getUrl().getRawUrl().replace("/","\\");
            path = String.join("",System.getProperty("user.dir"),path);
            System.out.println("Checking for: " + path);
            boolean fileExists = new File (path).isFile();
            System.out.println("File found: " + fileExists);

            if(fileExists) {
                String contentType = "", folder = "";
                String nameSplit = req.getUrl().getPath().split("/")[3];

                switch (nameSplit.toLowerCase()) {
                    case "js":
                        contentType = "text/javascript";
                        break;
                    case "css":
                        contentType = "text/css";
                        break;
                    case "font":
                        contentType = "font/woff2";
                        break;
                    case "img": // needs an own response because no text
                        // we need to determine which type of image
                        folder = req.getUrl().getPath().split("/")[4];
                        if(!folder.contains(".")){  // without a "." in the name it's a directory
                            switch (folder.toLowerCase()) { // lower case so joeg is always jpeg etc.
                                case "bmp":
                                    contentType = "image/bmp";
                                    break;
                                case "gif":
                                    contentType = "image/gif";
                                    break;
                                case "ico":
                                    contentType = "image/vnd.microsoft.icon";
                                    break;
                                case "jpeg":
                                case "jpg":
                                    contentType = "image/jpeg";
                                    break;
                                case "png":
                                    contentType = "image/png";
                                    break;
                                case "svg":
                                    contentType = "image/svg";
                                    break;
                                case "tif":
                                case "tiff":
                                    contentType = "image/tiff";
                                    break;
                                case "webp":
                                    contentType = "image/webp";
                                    break;
                            }
                        }
                        break;
                }
                try{
                    if(contentType.contains("image")) {
                        /* TODO: 2019-12-27 img cant be displayed correctly */
                        BufferedImage img = ImageIO.read(new File(path));
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        ImageIO.write(img, folder, bos);
                        byte[] data = bos.toByteArray();

                        float test = data.length;
                        System.out.println(test + " Content Length (byte array)");
                        res.setStatusCode(200);
                        res.addHeader("connection", "keep-alive");
                        res.addHeader("Content-Type", contentType);
                        res.setContentType(contentType);
                        res.setContent(data);
                    }
                    else {

                        String data = "";// = Files.readString(Paths.get(Diff));
                        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
                        String line;

                        while((line = input.readLine()) != null) {
                            data = String.join("\r\n", data, line);
                        }

                        input.close();

                        res.setStatusCode(200);
                        float test = data.length();
                        System.out.println(test + " Content Length (String)");
                        res.addHeader("Content-length", String.valueOf(data.length()));
                        res.addHeader("connection", "keep-alive");
                        res.addHeader("Content-Type", contentType);
                        res.setContentType(contentType);
                        res.setContent(data);
                    }
                }
                catch(IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            else{
                res.setStatusCode(404);
                res.addHeader("connection", "close");
            }
        }   // same procedure as above, but the file search and type have to be more individual
        else if(req.getUrl().getPath().contains("/deploy/MDB")) {
            String path = req.getUrl().getRawUrl().replace("/","\\");
            path = String.join("",System.getProperty("user.dir"),path);
            System.out.println("Checking for: " + path);
            boolean fileExists = new File (path).isFile();
            System.out.println("File found: " + fileExists);

            if(fileExists) {    // einfach Inhalt anzeigen lassen - MIME Type richtig setzen
                String htmlString = html.getStatic();

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
