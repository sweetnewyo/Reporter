package ru.angorbunov.report.table.factory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.angorbunov.report.parsers.xml.XmlParser;
import ru.angorbunov.report.table.column.Column;
import org.w3c.dom.Document;
import ru.angorbunov.report.table.page.Page;

import java.util.ArrayList;

public class FactoryPage {

    private ArrayList<Column> createColumns() {

        XmlParser xmlParser = XmlParser.get_instance();
        Document doc = xmlParser.document;

        ArrayList<Column> columns = new ArrayList<>();

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("column");

        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                Column column = new Column();
                column.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                column.setWidth(Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()));

                columns.add(column);
            }
        }
        return columns;
    }

    public Page createPage() {
        XmlParser xmlParser = XmlParser.get_instance();
        Document doc = xmlParser.document;

        Page page = new Page();

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("page");

        Node nNode = nodeList.item(0);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;

            page.setHeight(Integer.parseInt(eElement.getElementsByTagName("height").item(0).getTextContent()));
            page.setWidth(Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()));
        }

        page.setColumns(createColumns());

        return page;
    }
}
