package aoc.event.year2019;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/// --- Day 2: 1202 Program Alarm ---
public class Day02 implements Solver {
    
    private final List<Integer> initialState;
    
    public Day02(String input) {
        initialState = Collections.unmodifiableList(parse(input));
    }
    
    private static List<Integer> parse(String input) {
        return Arrays.stream(input.split(","))
            .mapToInt(Integer::parseInt)
            .boxed()
            .toList();
    }
    
    /// @return the value left at position 0 after the program halts.
    @Override
    public Integer partOne() {
        final var intcode = new ArrayList<>(initialState);
        
        intcode.set(1, 12);
        intcode.set(2, 2);
        
        runProgram(intcode);
        
        return intcode.getFirst();
    }
    
    /// @return the input noun and verb that cause the program to produce the output 19690720, or
    /// -1 if there are none.
    @Override
    public Integer partTwo() {
        for (var noun = 0; noun <= 99; noun++) {
            for (var verb = 0; verb <= 99; verb++) {
                final var intcode = new ArrayList<>(initialState);
                
                intcode.set(1, noun);
                intcode.set(2, verb);
                
                runProgram(intcode);
                
                if (intcode.getFirst() == 19690720) {
                    return 100 * noun + verb;
                }
            }
        }
        
        return -1;
    }
    
    private static void runProgram(List<Integer> intcode) {
        for (var position = 0; position < intcode.size(); position += 4) {
            final var opcode = intcode.get(position);
            
            switch (opcode) {
                case 1 -> add(intcode, position);
                case 2 -> multiply(intcode, position);
                case 99 -> position = intcode.size();
            }
        }
    }
    
    private static void add(List<Integer> intcode, int position) {
        final var positions = determinePositions(intcode, position);
        final var sum = intcode.get(positions.first) + intcode.get(positions.second);
        
        intcode.set(positions.output, sum);
    }
    
    private static void multiply(List<Integer> intcode, int position) {
        final var positions = determinePositions(intcode, position);
        final var product = intcode.get(positions.first) * intcode.get(positions.second);
        
        intcode.set(positions.output, product);
    }
    
    private static Positions determinePositions(List<Integer> intcode, int position) {
        final var first = intcode.get(position + 1);
        final var second = intcode.get(position + 2);
        final var output = intcode.get(position + 3);
        
        return new Positions(first, second, output);
    }
    
    private record Positions(Integer first, Integer second, Integer output) {}
    
    public static void main(String[] args) {
        Runner.runAndPrint(2019, 2);
    }
}
