package ru.angorbunov.report.parsers.tsv;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TsvParser {
    private static TsvParser _instance = null;
    public List<String> lines;

    private TsvParser() {
        lines = initializeLines();
        if (lines == null) {
            System.out.println("can't parse tsv");
        }
    }

    public static synchronized TsvParser getInstance() {
        if (_instance == null) {
            return new TsvParser();
        } else {
            return _instance;
        }
    }

    private List<String> initializeLines() {
        String fileName = "/source-data.tsv";
        URL url = getClass().getResource(fileName);
        try {
            return Files.lines(Paths.get(url.toURI().getPath()), StandardCharsets.UTF_16).collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
