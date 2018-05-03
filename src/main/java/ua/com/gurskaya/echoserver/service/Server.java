package ua.com.gurskaya.echoserver.service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() {

        while (true) {
            ServerSocket serverSocket = null;
            Socket socket = null;
            BufferedReader bufferedReader = null;
            BufferedWriter bufferedWriter = null;
            try {
                serverSocket = new ServerSocket(port);
                socket = serverSocket.accept();
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                try {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);
                        bufferedWriter.write(line);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                } catch (IOException e) {
                    System.out.println("Waiting for new connection...");
                }
            } catch (IOException e) {
                throw new RuntimeException("Couldn't connect to client", e);
            } finally {
                try {
                    serverSocket.close();
                    bufferedReader.close();
                    bufferedWriter.close();
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

