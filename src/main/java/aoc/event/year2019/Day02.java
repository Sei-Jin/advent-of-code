package aoc.event.year2019;

import aoc.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/// # [2019-02: 1202 Program Alarm](https://adventofcode.com/2019/day/2)
public class Day02 implements Solver<Integer, Integer> {
    
    private final List<Integer> initialState;
    
    public Day02(String input) {
        initialState = Collections.unmodifiableList(parse(input));
    }
    
    private static List<Integer> parse(String input) {
        return Arrays
            .stream(input.split(","))
            .mapToInt(Integer::parseInt)
            .boxed()
            .toList();
    }
    
    public static List<Integer> runProgram(List<Integer> intcode) {
        for (var position = 0; position < intcode.size(); position += 4) {
            var opcode = intcode.get(position);
            switch (opcode) {
                case 1 -> add(intcode, position);
                case 2 -> multiply(intcode, position);
                case 99 -> position = intcode.size();
            }
        }
        return intcode;
    }
    
    private static void add(List<Integer> intcode, int position) {
        var positions = determinePositions(intcode, position);
        var sum = intcode.get(positions.first) + intcode.get(positions.second);
        intcode.set(positions.output, sum);
    }
    
    private static void multiply(List<Integer> intcode, int position) {
        var positions = determinePositions(intcode, position);
        var product = intcode.get(positions.first) * intcode.get(positions.second);
        intcode.set(positions.output, product);
    }
    
    private static Positions determinePositions(List<Integer> intcode, int position) {
        var first = intcode.get(position + 1);
        var second = intcode.get(position + 2);
        var output = intcode.get(position + 3);
        return new Positions(first, second, output);
    }
    
    /// @return the value left at position 0 after the program halts.
    @Override
    public Integer partOne() {
        var intcode = new ArrayList<>(initialState);
        intcode.set(1, 12);
        intcode.set(2, 2);
        return runProgram(intcode).getFirst();
    }
    
    /// @return the input noun and verb that cause the program to produce the output 19690720, or
    /// -1 if there are none.
    @Override
    public Integer partTwo() {
        for (var noun = 0; noun <= 99; noun++) {
            for (var verb = 0; verb <= 99; verb++) {
                var intcode = new ArrayList<>(initialState);
                intcode.set(1, noun);
                intcode.set(2, verb);
                if (runProgram(intcode).getFirst() == 19690720) {
                    return 100 * noun + verb;
                }
            }
        }
        return -1;
    }
    
    private record Positions(Integer first, Integer second, Integer output) {}
}
