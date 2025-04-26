package aoc.event.year2021;

import aoc.Solver;

import java.util.Arrays;

public class Day06 implements Solver<Long> {
    
    private final int[] initialState;
    
    public Day06(String input) {
        initialState = parse(input);
    }
    
    private static int[] parse(String input) {
        return Arrays.stream(input.split(","))
            .mapToInt(Integer::parseInt)
            .toArray();
    }
    
    private long fishAfterXDays(int x) {
        final var fishState = new long[9];
        
        for (final int timer : initialState) {
            fishState[timer]++;
        }
        
        for (int day = 0; day < x; day++) {
            final var zeroTimerCount = fishState[0];
            for (int j = 0; j < fishState.length - 1; j++) {
                fishState[j] = fishState[j + 1];
            }
            fishState[6] += zeroTimerCount;
            fishState[8] = zeroTimerCount;
        }
        
        return Arrays.stream(fishState).sum();
    }
    
    @Override
    public Long partOne() {
        return fishAfterXDays(80);
    }
    
    @Override
    public Long partTwo() {
        return fishAfterXDays(256);
    }
}
