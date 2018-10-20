package ru.angorbunov.report.table.page;

import ru.angorbunov.report.table.column.Column;

import java.util.ArrayList;

public class Page {
    private int height;
    private  int width;
    private ArrayList<Column> columns;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }
}
