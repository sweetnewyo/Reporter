package ru.angorbunov.report.formater;

import java.util.ArrayList;
import java.util.LinkedList;

public class Formatter {

    public ArrayList<String> formatStringFIO(String sourceString, int columnWidth) {

        ArrayList<String> resultStrings = new ArrayList<>();

        LinkedList<String> tempList = new LinkedList<>();

        if (sourceString.contains(" ")) {
            tempList.addAll(separate(sourceString, ' ', columnWidth));
        }


        ArrayList<Integer> indexes = checkWidth(tempList, columnWidth);

        if (indexes.size() == 0) {
            resultStrings.addAll(tempList);
            return resultStrings;
        }

        boolean flag = false;

        for (int i = 0; i < indexes.size(); i++) {

            if (tempList.get(indexes.get(i)).contains("-")) {
                if (!flag) {
                    LinkedList<String> sep = separate(tempList.get(indexes.get(i)), '-', columnWidth);
                    tempList.set(indexes.get(i), sep.getFirst());
                    tempList.add(indexes.get(i) + 1, sep.getLast());
                    flag = true;
                } else {
                    LinkedList<String> sep = separate(tempList.get(indexes.get(i) + 1), '-', columnWidth);
                    tempList.set(indexes.get(i) + 1, sep.getFirst());
                    tempList.add(indexes.get(i) + 2, sep.getLast());
                }


            }
        }

        indexes = checkWidth(tempList, columnWidth);

        if (indexes.size() == 0) {
            resultStrings.addAll(tempList);
            return resultStrings;
        }

        for (int i = 0; i < indexes.size(); i++) {
            LinkedList<String> sep = separate(tempList.get(indexes.get(i)), 'm', columnWidth);
            tempList.set(indexes.get(i), sep.getFirst());
            tempList.add(sep.getLast());
        }

        resultStrings.addAll(tempList);
        return resultStrings;
    }


    private LinkedList<String> separate(String str, char separator, int columnWidth) {
        LinkedList<String> strTemp = new LinkedList<>();
        if (separator == ' ') {
            strTemp.add(str.substring(0, str.lastIndexOf(separator)).trim());
            strTemp.add(str.substring(str.lastIndexOf(separator) + 1).trim());
        } else if (separator == '-') {
            if (str.lastIndexOf(separator) + 1 > columnWidth) {
                strTemp.add(str.substring(0, str.lastIndexOf(separator)).trim());
                strTemp.add(str.substring(str.lastIndexOf(separator)).trim());
            } else {
                strTemp.add(str.substring(0, str.lastIndexOf(separator) + 1).trim());
                strTemp.add(str.substring(str.lastIndexOf(separator) + 1).trim());
            }
        } else {
            String temp = splitStringSizeColumn(str, columnWidth);
            strTemp.add(temp);
            strTemp.add(str.replaceAll(temp, ""));
        }
        return strTemp;
    }


    private String splitStringSizeColumn(String string, int columnWidth) {
        return String.format("%." + columnWidth + "s", string);
    }

    private ArrayList<Integer> checkWidth(LinkedList<String> list, int columnWidth) {
        ArrayList<Integer> indexArr = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() > columnWidth) {
                indexArr.add(i);
            }
        }
        return indexArr;
    }


    public ArrayList<String> formatStringDate(String sourceString, int columnWidth) {
        ArrayList<String> date = new ArrayList<>();

        String temp = sourceString.substring(0, sourceString.lastIndexOf('/') + 1);
        date.add(temp);
        date.add(sourceString.replaceAll(temp, ""));
        return date;
    }
}


