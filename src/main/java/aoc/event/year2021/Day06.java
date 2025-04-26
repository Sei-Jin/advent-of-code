package aoc.event.year2021;

import aoc.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06 implements Solver<Long> {
    
    private final List<Long> initialState;
    
    public Day06(String input) {
        initialState = parse(input);
    }
    
    private static List<Long> parse(String input) {
        return Arrays.stream(input.split(","))
            .map(Long::parseLong)
            .toList();
    }
    
    @Override
    public Long partOne() {
        final var state = new ArrayList<>(initialState);
        
        for (int i = 0; i < 80; i++) {
            var count = 0;
            
            for (int j = 0; j < state.size(); j++) {
                final var timer = state.get(j) - 1;
                
                if (timer >= 0) {
                    state.set(j, timer);
                } else {
                    state.set(j, 6L);
                    count++;
                }
            }
            
            for (int j = 0; j < count; j++) {
                state.add(8L);
            }
        }
        
        return (long) state.size();
    }
    
    @Override
    public Long partTwo() {
        return 0L;
    }
}
