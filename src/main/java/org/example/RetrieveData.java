package org.example;

import org.example.Model.Data;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetrieveData {
    public void extraction(List<Data> objects) {
        Map<String, Integer> duplicates = new HashMap<>();
        Map<String, BigInteger> weightsByGroup = new HashMap<>();

        BigInteger maxWeight = BigInteger.valueOf(Long.MIN_VALUE);
        BigInteger minWeight = BigInteger.valueOf(Long.MAX_VALUE);

        for (Data object : objects) {

            String key = object.getGroup() + "-" + object.getType();
            duplicates.put(key, duplicates.getOrDefault(key, 0) + 1);

            BigInteger weight = BigInteger.valueOf(object.getWeight());

            weightsByGroup.put(object.getGroup(),
                   weightsByGroup.getOrDefault(object.getGroup(), BigInteger.ZERO).add(weight));

            if (weight.compareTo(maxWeight) > 0) {
                maxWeight = weight;
            }
            if (weight.compareTo(minWeight) < 0) {
                minWeight = weight;
            }
        }

        System.out.println("Дубликаты объектов:");
        duplicates.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("Суммарный вес объектов в каждой группе:");
        weightsByGroup.forEach((group, totalWeight) -> System.out.println(group + ": " + totalWeight));

        System.out.println("Максимальный вес: " + maxWeight);
        System.out.println("Минимальный вес: " + minWeight);
    }
}