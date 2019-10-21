package javasockets;

import java.util.Scanner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocketExample {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);  // Create a Scanner object

        try {
            // Create a connection to the server socket on the server application
            InetAddress host = InetAddress.getLocalHost();
            Socket socket = new Socket(host.getHostName(), 8080);

            System.out.println("Enter Message:");
            String msg = input.nextLine();  // Read user input

            while(msg != "quit"){
                // Send a message to the client application
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(msg);

                // Read and display the response message sent by server application
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                String message = (String) ois.readObject();
                System.out.println("Message: " + message);

                ois.close();
                oos.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}