package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите путь до файла (или 'exit' для выхода):");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Пожалуйста, введите путь до файла.");
                continue;
            }

            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            RetrieveData retrieveData = new RetrieveData();

            try {
                boolean isSuccess = false;
                if (input.endsWith(".csv")) {
                    isSuccess = new CsvParser().readCsv(input, retrieveData::processData);
                } else if (input.endsWith(".json")) {
                    isSuccess = new JsonParsing().readJson(input, retrieveData::processData);
                } else {
                    System.out.println("Неверный формат файла. Поддерживаются только файлы .csv или .json.");
                    continue;
                }

                if (isSuccess) {
                    retrieveData.printResults();
                }

            } catch (IOException e) {
                System.out.println("Ошибка при обработке файла: " + e.getMessage());
            }

            retrieveData.clearData();
        }
    }
}