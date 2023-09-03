package com.example.number_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator {

        int numberOfRandomNumbers = 16;
        int maxRange = 100; // Giới hạn trên cho số ngẫu nhiên
        List<Integer> randomNumbers = generateUniqueRandomNumbers(numberOfRandomNumbers, maxRange);

    public static List<Integer> generateUniqueRandomNumbers(int count, int maxRange) {
        if (count > maxRange) {
            throw new IllegalArgumentException("Số lượng số ngẫu nhiên không được lớn hơn giới hạn trên.");
        }

        List<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();

        while (randomNumbers.size() < count) {
            int randomNumber = random.nextInt(maxRange) + 1;
            if (!randomNumbers.contains(randomNumber)) {
                randomNumbers.add(randomNumber);
            }
        }

        return randomNumbers;
    }
}


