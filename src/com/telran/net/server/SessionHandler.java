package com.telran.net.server;

import com.telran.protocol.Protocol;
import com.telran.protocol.ProtocolRequest;
import com.telran.protocol.ProtocolResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class SessionHandler {
    Socket socket;
    Protocol protocol;

    public SessionHandler(Socket socket, Protocol protocol) {
        this.socket = socket;
        this.protocol = protocol;
    }

    public void run() {
        try (Socket socket = this.socket;
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {
            while (true) {
                ProtocolRequest request = (ProtocolRequest) input.readObject();
                ProtocolResponse response = protocol.getResponse(request);
                output.writeObject(response);
                output.reset();
            }

        } catch (SocketException e) {
            System.out.println("Client closed connection!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
