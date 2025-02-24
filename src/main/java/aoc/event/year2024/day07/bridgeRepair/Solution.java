package aoc.event.year2024.day07.bridgeRepair;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Solution implements Solver {
    
    private static final Pattern NUMBER_PATTERN = Pattern.compile("(\\d+)");
    
    private final List<Equation> equations;
    
    public Solution(String input) {
        equations = Collections.unmodifiableList(parse(input));
    }
    
    private static List<Equation> parse(String input) {
        final var equations = new ArrayList<Equation>();
        
        input.lines().forEach(line -> {
            final var values = line.split(": ");
            
            final var target = Long.parseLong(values[0]);
            final var numbersInput = values[1];
            
            final var matcher = NUMBER_PATTERN.matcher(numbersInput);
            
            final var numbers = new ArrayList<Integer>();
            
            while (matcher.find()) {
                numbers.add(Integer.parseInt(matcher.group(1)));
            }
            
            equations.add(new Equation(target, numbers));
        });
        
        return equations;
    }
    
    /// Generates all possible permutations with repetition of length k.
    ///
    /// Modified version of the pseudocode from: [Baeldung: Generating Permutations with Repetition
    /// ](https://www.baeldung.com/cs/permutations-with-repetition)
    private static <T> List<List<T>> generatePermutations(List<T> operations, int k) {
        if (k == 0) {
            final var base = new ArrayList<List<T>>();
            base.add(new ArrayList<>());
            return base;
        } else {
            final var tuples_k_minus_1 = generatePermutations(operations, k - 1);
            final var tuples_k = new ArrayList<List<T>>();
            
            for (final var tuple : tuples_k_minus_1) {
                for (final var operation : operations) {
                    final var newTuple = new ArrayList<>(tuple);
                    newTuple.addFirst(operation);
                    tuples_k.add(newTuple);
                }
            }
            
            return tuples_k;
        }
    }
    
    private static boolean isPossible(Equation equation, List<List<Operation>> permutations) {
        for (final var permutation : permutations) {
            var total = (long) equation.numbers.getFirst();
            
            for (int i = 0; i < permutation.size(); i++) {
                final var operation = permutation.get(i);
                final var number = equation.numbers.get(i + 1);
                
                switch (operation) {
                    case ADD -> total += number;
                    case MULTIPLY -> total *= number;
                    case CONCAT -> total = Long.parseLong(total + String.valueOf(number));
                }
            }
            
            if (total == equation.target) {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public Long partOne() {
        var total = 0L;
        
        for (final var equation : equations) {
            final var operations = List.of(Operation.ADD, Operation.MULTIPLY);
            final var operationCount = equation.numbers.size() - 1;
            
            final var permutations = generatePermutations(operations, operationCount);
            
            if (isPossible(equation, permutations)) {
                total += equation.target;
            }
        }
        
        return total;
    }
    
    @Override
    public Long partTwo() {
        var total = 0L;
        
        for (final var equation : equations) {
            final var operations = List.of(Operation.ADD, Operation.MULTIPLY, Operation.CONCAT);
            final var operationCount = equation.numbers.size() - 1;
            
            final var permutations = generatePermutations(operations, operationCount);
            
            if (isPossible(equation, permutations)) {
                total += equation.target;
            }
        }
        
        return total;
    }
    
    private record Equation(long target, List<Integer> numbers) {}
    
    private enum Operation {
        ADD,
        MULTIPLY,
        CONCAT
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2024, 7);
    }
}
