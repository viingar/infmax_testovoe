package org.example;

import org.example.Model.Data;

import java.io.*;
import java.util.function.Consumer;

public class CsvParser {
    public void readCsv(String fileName, Consumer<Data> processRow) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine();
            String line;
            Data data = new Data();

            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(",");

                if (splitLine.length < 4) {
                    System.err.println("Недостаточно данных в строке: " + line);
                    continue;
                }

                data.setGroup(splitLine[0]);
                data.setType(splitLine[1]);
                data.setNumber(Long.parseLong(splitLine[2]));
                data.setWeight(Long.parseLong(splitLine[3]));


                processRow.accept(data);
            }
        }
    }
}