package ru.angorbunov.report.generator;

import ru.angorbunov.report.data.Data;
import ru.angorbunov.report.data.persons.Person;
import ru.angorbunov.report.formater.Formatter;
import ru.angorbunov.report.table.column.Column;
import ru.angorbunov.report.table.page.Page;
import ru.angorbunov.report.table.records.Record;

import java.util.*;
import java.util.stream.Stream;

public class ReportGenerator {

    public ArrayList<String> pageStrings = new ArrayList<>();

    public ArrayList<String> generateHeader(Page page) {
        ArrayList<Column> columns = page.getColumns();
        ArrayList<String> header = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        columns.forEach(column -> {
            String headerColumn = String.format("| %-" + column.getWidth() + "s ", column.getTitle());
            stringBuilder.append(headerColumn);
        });
        header.add(stringBuilder.append("|\r").toString());

        return header;
    }

    public String generateSeparator(Page page) {
        StringBuilder stringBuilder = new StringBuilder();
        Stream<String> stream = Stream.generate(() -> "-").limit(page.getWidth());

        stream.forEach(stringBuilder::append);
        return stringBuilder.append("\r").toString();
    }


    public void generateReport(Page page, Data data) {
        ArrayList<Record> records = new ArrayList<>();

        data.getPersons().forEach(person -> records.add(generateRecordOfTable(person, page)));

        pageStrings.addAll(generateHeader(page));
        int pageSize = pageStrings.size();

        for (Record record : records) {
            if (pageSize + record.getLines().size() <= page.getHeight()) {
                pageStrings.addAll(record.getLines());
                pageSize += record.getLines().size();

            } else {
                pageStrings.add("~\r");
                pageStrings.addAll(generateHeader(page));
                pageStrings.addAll(record.getLines());

                pageSize = generateHeader(page).size() + record.getLines().size();
            }
        }

        pageStrings.forEach(System.out::println);

    }

    private Record generateRecordOfTable(Person person, Page page) {

        ArrayList<String> id = new ArrayList<>();
        ArrayList<String> date = new ArrayList<>();
        ArrayList<String> FIO = new ArrayList<>();

        ArrayList<String> resultLines = new ArrayList<>();

        id.add(String.valueOf(person.getId()));

        Formatter formatter = new Formatter();

        Optional<Column> idOptional = page.getColumns().stream().filter(c -> c.getTitle().equals("Номер")).findFirst();
        Column idColumn = idOptional.get();

        Optional<Column> dateOptional = page.getColumns().stream().filter(c -> c.getTitle().equals("Дата")).findFirst();
        Column dateColumn = dateOptional.get();

        Optional<Column> fIOoptional = page.getColumns().stream().filter(c -> c.getTitle().equals("ФИО")).findFirst();
        Column fIOcolumn = fIOoptional.get();

        if (person.getDate().length() > dateColumn.getWidth()) {
            date = formatter.formatStringDate(person.getDate(), dateColumn.getWidth());
        } else {
            date.add(person.getDate());
        }

        if (person.getFIO().length() > fIOcolumn.getWidth()) {
            FIO = formatter.formatStringFIO(person.getFIO(), fIOcolumn.getWidth());
        } else {
            FIO.add(person.getFIO());
        }

        int size = Math.max(date.size(), FIO.size());
        if (id.size() < size) {
            id.addAll(initializeArray(size));
        }
        if (date.size() < size) {
            date.addAll(initializeArray(size));
        }
        if (FIO.size() < size) {
            FIO.addAll(initializeArray(size));
        }

        resultLines.add(generateSeparator(page));


        for (int i = 0; i < size; i++) {

            StringBuilder resultString = new StringBuilder();
            resultString.append(generateLine(id.get(i), idColumn.getWidth()));
            resultString.append(generateLine(date.get(i), dateColumn.getWidth()));
            resultString.append(generateLine(FIO.get(i), fIOcolumn.getWidth()));
            resultString.append("|\r");
            resultLines.add(resultString.toString());
        }

        Record record = new Record();
        record.setId(person.getId());
        record.setLines(resultLines);

        return record;
    }


    private String generateLine(String str, int columnWidth) {
        StringBuilder result = new StringBuilder();

        if (!str.equals("")) {
            result.append(String.format("| %-" + columnWidth + "s ", str));
        } else {
            result.append(String.format("| %-" + columnWidth + "s ", ""));
        }
        return result.toString();
    }

    private ArrayList<String> initializeArray(int count) {
        ArrayList<String> tempArr = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tempArr.add("");
        }
        return tempArr;
    }
}
