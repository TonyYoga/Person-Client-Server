package com.telran.person.core;

public class PersonApi {
    public static final int PORT = 7001;

    static class RequestType {
        static final String ADD_PERSON = "/addPerson";
        static final String REMOVE_PERSON = "/removePerson";
        static final String GET_BY_ID = "/getById";
    }

}
