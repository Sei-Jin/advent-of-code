package aoc.event.year2024;

import aoc.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/// # [2024-07: Bridge Repair](https://adventofcode.com/2024/day/7)
///
/// Brute-force solution. All possible combinations of operations are generated for each equation.
///
/// To generalize this approach, this is an implementation of the algorithm: `Generate all
/// possible permutations with repetition of length k`.
///
/// The generatePermutations method is derived from the pseudocode at:
/// [Baeldung: Generating Permutations with Repetition](
/// https://www.baeldung.com/cs/permutations-with-repetition)
public class Day07 implements Solver<Long, Long> {
    
    private final List<Equation> equations;
    
    public Day07(String input) {
        equations = parse(input);
    }
    
    private static List<Equation> parse(String input) {
        return input
            .lines()
            .map(line -> {
                var parts = line.split(": ");
                
                var target = Long.parseLong(parts[0]);
                var numbers = Arrays
                    .stream(parts[1].split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
                
                return new Equation(target, numbers);
            })
            .toList();
    }
    
    private static <T> List<List<T>> generatePermutations(List<T> operations, int k) {
        if (k == 0) {
            var base = new ArrayList<List<T>>();
            base.add(new ArrayList<>());
            return base;
        }
        else {
            var tuplesKMinus1 = generatePermutations(operations, k - 1);
            var tuplesK = new ArrayList<List<T>>();
            
            for (var tuple : tuplesKMinus1) {
                for (var operation : operations) {
                    var newTuple = new ArrayList<>(tuple);
                    newTuple.add(operation);
                    tuplesK.add(newTuple);
                }
            }
            return tuplesK;
        }
    }
    
    private static boolean isPossible(Equation equation, List<List<Operation>> permutations) {
        for (var permutation : permutations) {
            var total = (long) equation.numbers[0];
            for (int i = 0; i < permutation.size(); i++) {
                var operation = permutation.get(i);
                var number = equation.numbers[i + 1];
                
                switch (operation) {
                    case ADD -> total += number;
                    case MULTIPLY -> total *= number;
                    case CONCAT -> total = Long.parseLong(total + String.valueOf(number));
                }
            }
            if (total == equation.target()) {
                return true;
            }
        }
        return false;
    }
    
    private static long sumPossible(List<Equation> equations, List<Operation> operations) {
        var total = 0L;
        for (var equation : equations) {
            var operationCount = equation.numbers().length - 1;
            var permutations = generatePermutations(operations, operationCount);
            
            if (isPossible(equation, permutations)) {
                total += equation.target();
            }
        }
        return total;
    }
    
    @Override
    public Long partOne() {
        var operations = List.of(
            Operation.ADD,
            Operation.MULTIPLY
        );
        return sumPossible(equations, operations);
    }
    
    @Override
    public Long partTwo() {
        var operations = List.of(
            Operation.ADD,
            Operation.MULTIPLY,
            Operation.CONCAT
        );
        return sumPossible(equations, operations);
    }
    
    private enum Operation {ADD, MULTIPLY, CONCAT}
    
    private record Equation(long target, int[] numbers) {}
}
