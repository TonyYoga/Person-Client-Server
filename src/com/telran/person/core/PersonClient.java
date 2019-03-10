package com.telran.person.core;

import com.telran.net.client.TcpClient;
import com.telran.person.dto.Person;

import java.io.IOException;

public class PersonClient extends TcpClient {


    public PersonClient(String host) throws IOException {
        super(host, PersonApi.PORT);
    }

    public boolean sendAddPersonRequest(Person data) {
        boolean response = sendRequest(PersonApi.RequestType.ADD_PERSON, data);
        return response;
    }

    public boolean sendRemovePerson(int id) {
        boolean response = sendRequest(PersonApi.RequestType.REMOVE_PERSON, id);
        return response;
    }

    public Person sendGetPersonById(int id) {
        Person response = sendRequest(PersonApi.RequestType.GET_BY_ID, id);
        return response;
    }
}
