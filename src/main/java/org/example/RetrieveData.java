package org.example;

import org.example.Model.Data;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class RetrieveData {

    private final Map<String, Integer> duplicates = new HashMap<>();
    private final Map<String, BigInteger> weightsByGroup = new HashMap<>();

    private long maxWeight = Long.MIN_VALUE;
    private long minWeight = Long.MAX_VALUE;

    public void processData(Data object) {

        String key = object.getGroup() + "-" + object.getType(); //переполнение из за этой строчки
        duplicates.put(key, duplicates.getOrDefault(key, 0) + 1);

        long weight = object.getWeight();

        weightsByGroup.put(object.getGroup(),
                weightsByGroup.getOrDefault(object.getGroup(), BigInteger.ZERO)
                        .add(BigInteger.valueOf(weight)));

        if (weight > maxWeight) {
            maxWeight = weight;
        }
        if (weight < minWeight) {
            minWeight = weight;
        }
    }

    public void printResults() {
        boolean hasDuplicates = false;
        System.out.println("Дубликаты объектов:");
        for (Map.Entry<String, Integer> entry : duplicates.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
                hasDuplicates = true;
            }
        }

        if (!hasDuplicates) {
            System.out.println("Нет дубликатов");
        }

        System.out.println("Суммарный вес объектов в каждой группе:");
        weightsByGroup.forEach((group, totalWeight) -> System.out.println(group + " : " + totalWeight));

        System.out.println("Максимальный вес: " + maxWeight);
        System.out.println("Минимальный вес: " + minWeight);

        clearData();
    }

    private void clearData() {
        duplicates.clear();
        weightsByGroup.clear();
        maxWeight = Long.MIN_VALUE;
        minWeight = Long.MAX_VALUE;

        System.gc();
    }
}