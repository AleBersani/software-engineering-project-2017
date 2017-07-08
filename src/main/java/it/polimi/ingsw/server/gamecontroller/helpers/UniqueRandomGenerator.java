package it.polimi.ingsw.server.gamecontroller.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UniqueRandomGenerator {
    private int poolSize;

    public static void main(String argv[]) {
        UniqueRandomGenerator uniqueRandomGenerator = new UniqueRandomGenerator(24);
        uniqueRandomGenerator.generateRandoms(16).forEach(integer -> System.out.println(integer.toString()));
    }

    public UniqueRandomGenerator(int poolSize) {
        this.poolSize = poolSize;
    }

    public List<Integer> generateRandoms(int numberToExtract) {
        List<Integer> range = IntStream.range(0, poolSize).boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(range);
        return range.subList(0, numberToExtract);
    }

    public int generateRandom() {
        List<Integer> range = IntStream.range(0, poolSize).boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(range);
        return range.get(0);
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }
}
