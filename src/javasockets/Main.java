/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javasockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arthur
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //read();
        listen();
    }

    private static void read() {
        try {
            Socket s = new Socket("orf.at", 80);
            write(s);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;
            while((line = in.readLine()) != null) {
                System.out.println(line);
            }
            s.close();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void write(Socket s) {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            out.write("GET / HTTP/1.1\r\n");
            out.write("host: orf.at\r\n");
            out.write("connection: close\r\n");
            out.write("\r\n");
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void listen() {
        try {
            ServerSocket listener = new ServerSocket(8081);
            Socket s = listener.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;
            while((line = in.readLine()) != null) {
                System.out.println(line);
                if("".equals(line)){
                    break;
                }
            }
            String body = "<html><body><h1> Hello World </h1> Hello Blabla </body></html>";
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            out.write("HTTP/1.1 200 OK\r\n");
            out.write("Content-Type: text/html\r\n");
            out.write(String.format("Content-Length: %d\r\n",body.length()));
            out.write("connection: close\r\n");
            out.write("\r\n");
            out.write(body);
            out.flush();
            listener.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
