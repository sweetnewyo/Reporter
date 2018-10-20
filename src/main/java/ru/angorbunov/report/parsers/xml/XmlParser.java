package ru.angorbunov.report.parsers.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.*;


public class XmlParser {
    private static XmlParser _instance = null;
    public Document document;

    private XmlParser(){
        document = initializeDoc();
    }

    public synchronized static XmlParser get_instance() {
        if (_instance == null) {
            return new XmlParser();
        } else {
            return _instance;
        }
    }

    private Document initializeDoc(){
        Document doc = null;

        String fileName = "settings.xml";
        InputStream inputStream = getClass().getResourceAsStream("/" + fileName);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputStream);
        } catch (ParserConfigurationException | SAXException | IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return doc;
    }
}

