package aoc.event.year2021;

import aoc.Solver;

import java.util.Arrays;

/// # [2021-06: Lanternfish](https://adventofcode.com/2021/day/6)
public class Day06 implements Solver<Long, Long> {
    
    private final int[] initialState;
    
    public Day06(String input) {
        initialState = parse(input);
    }
    
    private static int[] parse(String input) {
        return Arrays
            .stream(input.split(","))
            .mapToInt(Integer::parseInt)
            .toArray();
    }
    
    private long fishAfterXDays(int x) {
        var fishStates = new long[9];
        for (int timer : initialState) {
            fishStates[timer]++;
        }
        for (int day = 0; day < x; day++) {
            var zeroTimerCount = fishStates[0];
            for (int j = 0; j < fishStates.length - 1; j++) {
                fishStates[j] = fishStates[j + 1];
            }
            fishStates[6] += zeroTimerCount;
            fishStates[8] = zeroTimerCount;
        }
        return Arrays.stream(fishStates).sum();
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
