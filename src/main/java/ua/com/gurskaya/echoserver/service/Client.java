package ua.com.gurskaya.echoserver.service;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(HOST, PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader userEntry = new BufferedReader(new InputStreamReader(System.in));

        String clientInput;
        String serverInput;
        System.out.print("Enter message: ");

        while ((clientInput = userEntry.readLine()) != null) {

            System.out.println("We're gonna send " + clientInput);
            output.println(clientInput);

            serverInput = input.readLine();
            System.out.println("Server response " + serverInput);

            System.out.print("Enter message: ");
        }
        output.close();
        input.close();
        userEntry.close();
        socket.close();
    }
}

