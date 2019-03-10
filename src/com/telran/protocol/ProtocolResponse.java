package com.telran.protocol;

import java.io.Serializable;

public class ProtocolResponse implements Serializable{
    public Code code;
    public Serializable data;

    public ProtocolResponse(Code code, Serializable data) {
        this.code = code;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProtocolResponse{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }

    public enum Code {
        OK, WRONG_REQUEST, UNKNOWN
    }


}
