package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.Model.Data;

import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;


public class CsvParser {

    private static final long MAX_OBJECTS = 10_000_000;

    public boolean readCsv(String fileName, Consumer<Data> processRow) throws IOException {
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            String[] nextLine;
            csvReader.readNext();

            long count = 0;
            while ((nextLine = csvReader.readNext()) != null) {
                count++;
                if (count >= MAX_OBJECTS) {
                    System.out.println("Файл содержит более 10 миллионов объектов. Пожалуйста, используйте файл меньшего объема.");
                    return false;
                }

                Data data = new Data();
                data.setGroup(nextLine[0]);
                data.setType(nextLine[1]);
                data.setNumber(Long.parseLong(nextLine[2]));
                data.setWeight(Long.parseLong(nextLine[3]));

                processRow.accept(data);
            }
            return true;
        } catch (CsvValidationException e) {
            throw new RuntimeException("Ошибка валидации CSV файла: " + e.getMessage());
        }
    }
}