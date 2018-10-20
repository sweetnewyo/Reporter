package ru.angorbunov.report.data.persons;

public class Person {
    private int id;
    private String date;
    private String FIO;

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getFIO() {
        return FIO;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }
}
