package com.telran.person.core;

import com.telran.net.client.TcpClient;
import com.telran.person.dto.Person;

import java.io.IOException;

public class PersonClient extends TcpClient {


    public PersonClient(String host) throws IOException {
        super(host, PersonApi.PORT);
    }

    public boolean sendAddPersonRequest(Person data) {
        return sendRequest(PersonApi.RequestType.ADD_PERSON, data);
    }

    public boolean sendRemovePerson(int id) {
        return sendRequest(PersonApi.RequestType.REMOVE_PERSON, id);
    }

    public Person sendGetPersonById(int id) {
        return sendRequest(PersonApi.RequestType.GET_BY_ID, id);
    }
}
