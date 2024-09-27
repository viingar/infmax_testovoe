package org.example;

import org.example.Model.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {
    public List<Data> readCsv(String fileName) throws IOException {

        List<Data> dataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(",");
                if (splitLine.length != 4) {
                    throw new IOException("Неверное количество данных");
                }
                String group = splitLine[0];
                String type = splitLine[1];
                long number = Long.parseLong(splitLine[2]);
                long weight = Long.parseLong(splitLine[3]);

                dataList.add(new Data(group, type, number, weight));
            }
        }
        return dataList;
    }
}
