package com.telran.app;

import com.telran.net.server.TcpServer;
import com.telran.person.core.PersonApi;
import com.telran.person.core.PersonProtocol;

import java.io.IOException;

public class PersonServerApp {
    public static void main(String[] args) throws IOException {
        TcpServer personServer = new TcpServer(new PersonProtocol(), PersonApi.PORT);
        personServer.run();
    }
}
