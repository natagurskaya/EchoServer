package ua.com.gurskaya.echoserver.service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            throw new RuntimeException("Can't open server socket", e);
        }
        Socket socket = null;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't connect to client", e);
        }

        while (true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                printWriter.println(line);
            }
            bufferedReader.close();
            printWriter.close();
            socket.close();
            serverSocket.close();
        }
    }
}

