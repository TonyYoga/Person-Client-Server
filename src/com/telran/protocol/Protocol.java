package com.telran.protocol;

public interface Protocol {
    ProtocolResponse getResponse(ProtocolRequest request);
}
