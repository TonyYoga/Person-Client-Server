package com.telran.person.core;

import com.telran.person.dto.Person;
import com.telran.protocol.Protocol;
import com.telran.protocol.ProtocolRequest;
import com.telran.protocol.ProtocolResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PersonProtocol implements Protocol {
    private Map<String, Function<ProtocolRequest, ProtocolResponse>> mapper;
    private Map<Integer, Person> persons;

    public PersonProtocol() {
        mapper = new HashMap<>();
        persons = new HashMap<>();
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
//                return new ProtocolResponse(ProtocolResponse.Code.UNKNOWN, request);
                throw new Exception("Wrong request type " + type);
            }
            return mappedFunction.apply(request);
        } catch (Exception e) {
            return new ProtocolResponse(ProtocolResponse.Code.WRONG_REQUEST, e.getMessage());
        }
    }

    private ProtocolResponse addPerson(ProtocolRequest request) {
        Person person = (Person) request.data;
        if (person == null || persons.containsKey(person.getId())) {
            return new ProtocolResponse(ProtocolResponse.Code.WRONG_REQUEST, false);
        }
        persons.put(person.getId(),person);
        return new ProtocolResponse(ProtocolResponse.Code.OK, true);
    }

    private ProtocolResponse removePerson(ProtocolRequest request) {
        int id = (int) request.data;
        Person toFind = persons.remove(id);
        if (toFind == null) {
            return new ProtocolResponse(ProtocolResponse.Code.WRONG_REQUEST, false);
        }
        return new ProtocolResponse(ProtocolResponse.Code.OK, true);
    }

    private ProtocolResponse getPersonById(ProtocolRequest request) {
        int id = (int) request.data;
        Person toFind = persons.get(id);
        if (toFind == null) {
            return new ProtocolResponse(ProtocolResponse.Code.WRONG_REQUEST, toFind);
        }
        return new ProtocolResponse(ProtocolResponse.Code.OK, toFind);
    }
}
