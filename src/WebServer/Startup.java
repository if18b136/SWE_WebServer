package WebServer;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Startup {
    public static void main(String[] args) {
        System.out.println("Type in your Name: ");
        Scanner input = new Scanner(System.in);
        String name = input.next();
        System.out.println("Your name is " + name);
    }
}
