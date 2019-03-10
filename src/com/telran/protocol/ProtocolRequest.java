package com.telran.protocol;

import java.io.Serializable;

public class ProtocolRequest implements Serializable {
    public String type;
    public Serializable data;

    public ProtocolRequest(String type, Serializable data) {
        this.type = type;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProtocolRequest{" +
                "type='" + type + '\'' +
                ", data=" + data +
                '}';
    }
}
