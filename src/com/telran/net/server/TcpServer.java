package com.telran.net.server;

import com.telran.protocol.Protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    ServerSocket serverSocket;
    Protocol protocol;
    int port;

    public TcpServer(Protocol protocol, int port) throws IOException {
        this.protocol = protocol;
        this.port = port;
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        System.out.println("Server listining port: " + port);
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                SessionHandler handler = new SessionHandler(socket, protocol);
                handler.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
