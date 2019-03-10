package com.telran.person.core;

import com.telran.person.dto.Person;
import com.telran.protocol.Protocol;
import com.telran.protocol.ProtocolRequest;
import com.telran.protocol.ProtocolResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class PersonProtocol implements Protocol {
    private Map<String, Function<ProtocolRequest, ProtocolResponse>> mapper;
    private List<Person> persons;

    public PersonProtocol() {
        mapper = new HashMap<>();
        persons = new ArrayList<>();
        mapper.put(PersonApi.RequestType.ADD_PERSON, this::addPerson);
        mapper.put(PersonApi.RequestType.REMOVE_PERSON, this::removePerson);
        mapper.put(PersonApi.RequestType.GET_BY_ID, this::getPersonById);

    }


    @Override
    public ProtocolResponse getResponse(ProtocolRequest request) {
        try {
            String type = request.type;
            Function<ProtocolRequest, ProtocolResponse> mappedFunction = mapper.get(type);
            if (mappedFunction == null) {
                return new ProtocolResponse(ProtocolResponse.Code.UNKNOWN, request);
            }
            return mappedFunction.apply(request);
        } catch (Exception e) {
            return new ProtocolResponse(ProtocolResponse.Code.WRONG_REQUEST, e.getMessage());
        }
    }

    private ProtocolResponse addPerson(ProtocolRequest request) {
        Person person = (Person) request.data;
        if (person == null) {
            return new ProtocolResponse(ProtocolResponse.Code.WRONG_REQUEST, person);
        }
        persons.add(person);
        return new ProtocolResponse(ProtocolResponse.Code.OK, person);
    }

    private ProtocolResponse removePerson(ProtocolRequest request) {
        int id = (int) request.data;
        if (id < 0 || id >= persons.size()) {
            return new ProtocolResponse(ProtocolResponse.Code.WRONG_REQUEST, id);
        }
        Person person = persons.remove(id);
        return new ProtocolResponse(ProtocolResponse.Code.OK, person);
    }

    private ProtocolResponse getPersonById(ProtocolRequest request) {
        int id = (int) request.data;
        if (id < 0 || id >= persons.size()) {
            return new ProtocolResponse(ProtocolResponse.Code.WRONG_REQUEST, id);
        }
        Person person = persons.get(id);
        return new ProtocolResponse(ProtocolResponse.Code.OK, person);
    }
}
