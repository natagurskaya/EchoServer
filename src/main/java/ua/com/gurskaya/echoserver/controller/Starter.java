package ua.com.gurskaya.echoserver.controller;

import ua.com.gurskaya.echoserver.service.Server;

public class Starter {
    public static void main(String[] args){
        Server server = new Server(8080);
        server.start();
    }
}
