package WebServer;

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

public class Startup {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            switch(args[0]){
                case "listen":
                    listen();
                    break;
                case "read":
                    read();
                    break;
                default:
                    System.out.println("No command with this name.");
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, err);
        }

    }

    private static void read() {
        try {
            Socket s = new Socket("127.0.0.1", 8080);
            write(s);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;
            while((line = in.readLine()) != null) {
                System.out.println(line);
            }
            s.close();

        } catch (UnknownHostException ex) {
            Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void write(Socket s) {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            out.write("GET / HTTP/1.1\r\n");
            out.write("host: 127.0.0.1\r\n");
            out.write("connection: close\r\n");
            out.write("\r\n");
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void listen() {
        try {
            ServerSocket listener = new ServerSocket(8080);
            Socket s = listener.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line = in.readLine();
            while(line  != null && !line.equals("")) {
                System.out.println(line);
                line = in.readLine();
            }
            s.close();
            listener.close();
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
