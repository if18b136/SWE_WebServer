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

public class ClientSocket {

    public static void main(String[] args) {
        read();
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
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
