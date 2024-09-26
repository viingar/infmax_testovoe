package org.example;

import org.example.Model.Data;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static final int limit = 10000000;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите путь до файла (или 'exit' для выхода):");
            String input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            try {

                List<Data> objects;
                if (input.endsWith(".csv")) {
                    objects = new CsvParser().readCsv(input);
                } else if (input.endsWith(".json")) {
                    objects = new JsonParser().readJson(input);
                }
                 else {
                    System.out.println("Неподдерживаемый формат файла.");
                    continue;
                }

                 if (objects.size() > limit) {
                     System.out.println("Превышено количество данных в файле, уменьшите количество.");
                     continue;
                 }

                if (objects.isEmpty()) {
                    System.out.println("Файл пуст.");
                } else {
                    new RetrieveData().extraction(objects);
                }


            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
            }
        }
        scanner.close();
    }
}