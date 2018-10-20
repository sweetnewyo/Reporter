package ru.angorbunov.report.data.persons.factory;

import ru.angorbunov.report.data.persons.Person;

public class FactoryPersons {
    public Person createPerson(String line){
        Person person = new Person();
        String[] lines = line.split("\t");

        person.setId(Integer.parseInt(lines[0]));
        person.setDate(lines[1]);
        person.setFIO(lines[2]);

        return person;
    }

}
