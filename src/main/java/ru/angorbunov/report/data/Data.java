package ru.angorbunov.report.data;

import ru.angorbunov.report.data.persons.Person;
import ru.angorbunov.report.data.persons.factory.FactoryPersons;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private ArrayList<Person> persons = new ArrayList<>();

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public void createData(List<String> data){
        data.forEach(d -> {
            FactoryPersons factoryPersons = new FactoryPersons();
            persons.add(factoryPersons.createPerson(d));
        });
    }
}
