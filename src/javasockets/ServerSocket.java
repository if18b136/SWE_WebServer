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
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerSocket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        listen();
    }

    private static void listen() {
        try {
            java.net.ServerSocket listener = new java.net.ServerSocket(8080);
            while(true){
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
            }
            //listener.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
