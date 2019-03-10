package com.telran.app;

import com.telran.person.core.PersonClient;
import com.telran.person.dto.Person;
import com.telran.view.ConsoleInputOutput;
import com.telran.view.InputOutput;
import com.telran.view.Item;
import com.telran.view.Menu;

import java.io.IOException;

public class PersonClientApp {
    public static void main(String[] args) throws IOException {
        try (PersonClient client = new PersonClient("127.0.0.1")){
            InputOutput cio = new ConsoleInputOutput();
            Menu menu = new Menu("Person Client Menu",
                    Item.of("Add new person", io -> {
                        int id = cio.readInt("Type Id ");
                        String fName = cio.readString("Type name ");
                        String lName = cio.readString("Type lastname ");
                        String phone = cio.readString("Type phone ");
                        boolean res = client.sendAddPersonRequest(new Person(id, fName, lName, phone));
                        cio.writeLine("Operation add is " + res);
                    }),
                    Item.of("Remove person by id", io -> {
                        int id = cio.readInt("Type person id ");
                        boolean res = client.sendRemovePerson(id);
                        if (res) {
                            cio.writeLine(String.format("Person with %d id was deleted"));
                        } else {
                            cio.writeLine(String.format("Person with %d id wasn't found"));
                        }
                    }),
                    Item.of("Get person by id", io -> {
                        int id = cio.readInt("Type person id ");
                        Person res = client.sendGetPersonById(id);
                        if (res != null) {
                            cio.writeLine(res);
                        } else {
                            cio.writeLine(String.format("Person with %d id wasn't found"));
                        }
                    }),
                    Item.exit()
            );
            menu.perform(cio);
        }
    }
}
