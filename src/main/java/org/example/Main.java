package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Runtime runtime = Runtime.getRuntime();
        RetrieveData retrieveData = new RetrieveData();

        while (true) {
            System.out.println("Введите путь до файла (или 'exit' для выхода):");
            String input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input)) {
                break;
            }
            if (input.endsWith(".csv")) {
                try {
                    new CsvParser().readCsv(input, retrieveData::processData);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (input.endsWith(".json")) {
                try {
                    new JsonParsing().readJson(input, retrieveData::processData);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            retrieveData.printResults();

        }

        runtime.gc();
    }
}