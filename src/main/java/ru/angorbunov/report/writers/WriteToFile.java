package ru.angorbunov.report.writers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WriteToFile {

    public void writeToFile(ArrayList<String> lines) {

        String fileName = "/example-report.txt";

        String workDir = System.getProperty("user.dir");

        try {
            Path pathFile = Paths.get(workDir + fileName);
            Files.write(pathFile, lines, Charset.forName("UTF-16"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
