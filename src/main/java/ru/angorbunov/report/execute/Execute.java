package ru.angorbunov.report.execute;

import ru.angorbunov.report.data.Data;
import ru.angorbunov.report.generator.ReportGenerator;
import ru.angorbunov.report.parsers.tsv.TsvParser;
import ru.angorbunov.report.table.factory.FactoryPage;
import ru.angorbunov.report.table.page.Page;
import ru.angorbunov.report.writers.WriteToFile;

public class Execute {

    public static void main(String[] args) {
        FactoryPage factoryPage = new FactoryPage();
        Page page = factoryPage.createPage();

        TsvParser tsvParser = TsvParser.getInstance();

        Data data = new Data();
        data.createData(tsvParser.lines);

        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateReport(page, data);

        WriteToFile writeToFile = new WriteToFile();
        writeToFile.writeToFile(reportGenerator.pageStrings);
    }
}
