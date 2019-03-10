package com.telran.person.core;

import com.telran.net.client.TcpClient;
import com.telran.person.dto.Person;
import com.telran.protocol.ProtocolResponse;

import java.io.IOException;

public class PersonClient extends TcpClient {


    public PersonClient(String host) throws IOException {
        super(host, PersonApi.PORT);
    }

    public boolean sendAddPersonRequest(Person data) {
        ProtocolResponse response = sendRequest(PersonApi.RequestType.ADD_PERSON, data);
        return response.code == ProtocolResponse.Code.OK;
    }

    public boolean sendRemovePerson(int id) {
        ProtocolResponse response = sendRequest(PersonApi.RequestType.REMOVE_PERSON, id);
        return response.code == ProtocolResponse.Code.OK;
    }

    public Person sendGetPersonById(int id) {
        ProtocolResponse response = sendRequest(PersonApi.RequestType.GET_BY_ID, id);
        return response.code == ProtocolResponse.Code.OK ? (Person) response.data : null;
    }
}
