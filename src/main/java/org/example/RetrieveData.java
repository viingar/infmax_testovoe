package org.example;

import org.example.Model.Data;
import org.example.Model.GroupTypeKey;

import java.math.BigInteger;
import java.util.*;

public class RetrieveData {

    private final Map<GroupTypeKey, Integer> duplicates = new LinkedHashMap<GroupTypeKey, Integer>(16, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<GroupTypeKey, Integer> eldest) {
            return size() > 1000000;
        }
    };

    private final Map<String, BigInteger> weightsByGroup = new HashMap<>();

    private long maxWeight = Long.MIN_VALUE;
    private long minWeight = Long.MAX_VALUE;

    private GroupTypeKey firstKey = null;
    private GroupTypeKey secondToLastKey = null;
    private int firstKeyCount = 0;
    private int secondToLastKeyCount = 0;

    public void processData(Data object) {
        GroupTypeKey key = new GroupTypeKey(object.getGroup(), object.getType());

        if (firstKey == null) {
            firstKey = key;
        }

        secondToLastKey = key;

        duplicates.put(key, duplicates.getOrDefault(key, 0) + 1);

        if (key.equals(firstKey)) {
            firstKeyCount++;
        }
        if (key.equals(secondToLastKey)) {
            secondToLastKeyCount++;
        }

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


        for (Map.Entry<GroupTypeKey, Integer> entry : duplicates.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println("Группа: " + entry.getKey().group + ", Тип: " + entry.getKey().type + " : " + entry.getValue());
                hasDuplicates = true;
            }
        }


        if (firstKeyCount > 1) {
            System.out.println("Группа: " + firstKey.group + ", Тип: " + firstKey.type + " : " + firstKeyCount);
            hasDuplicates = true;
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

    void clearData() {
        duplicates.clear();
        weightsByGroup.clear();
        maxWeight = Long.MIN_VALUE;
        minWeight = Long.MAX_VALUE;
        firstKey = null;
        secondToLastKey = null;
        firstKeyCount = 0;
        secondToLastKeyCount = 0;
    }
}