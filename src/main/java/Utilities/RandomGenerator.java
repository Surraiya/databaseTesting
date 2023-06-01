package Utilities;

import java.util.List;
import java.util.Random;

public class RandomGenerator {

    public static int getRandomInteger(int range){
        Random rand = new Random();
        return rand.nextInt(range);
    }

    public static List<Integer> getUniqueRandomIntegers(int count, int min, int max){
        if (count > max - min + 1) {
            throw new IllegalArgumentException("Cannot generate unique random integers. Count exceeds the range.");
        }

        return new Random()
                .ints(min, max + 1)
                .distinct()
                .limit(count)
                .boxed()
                .toList();
    }
}
