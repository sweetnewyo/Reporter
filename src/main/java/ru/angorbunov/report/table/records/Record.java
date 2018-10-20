package ru.angorbunov.report.table.records;

import java.util.ArrayList;

public class Record {
    private int id;
    private ArrayList<String> lines;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    public void setLines(ArrayList<String> lines) {
        this.lines = lines;
    }
}
