package aoc.event.year2024;

import aoc.DeprecatedSolver2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/// # [2024-07: Bridge Repair](https://adventofcode.com/2024/day/7)
public class Day07 implements DeprecatedSolver2 {
    
    private static final Pattern NUMBER_PATTERN = Pattern.compile("(\\d+)");
    
    private final List<Equation> equations;
    
    public Day07(String input) {
        equations = Collections.unmodifiableList(parse(input));
    }
    
    private static List<Equation> parse(String input) {
        var equations = new ArrayList<Equation>();
        
        input.lines().forEach(line -> {
            var values = line.split(": ");
            
            var target = Long.parseLong(values[0]);
            var numbersInput = values[1];
            
            var matcher = NUMBER_PATTERN.matcher(numbersInput);
            
            var numbers = new ArrayList<Integer>();
            
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
            var base = new ArrayList<List<T>>();
            base.add(new ArrayList<>());
            return base;
        } else {
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
            var total = (long) equation.numbers.getFirst();
            
            for (int i = 0; i < permutation.size(); i++) {
                var operation = permutation.get(i);
                var number = equation.numbers.get(i + 1);
                
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
        
        for (var equation : equations) {
            var operations = List.of(Operation.ADD, Operation.MULTIPLY);
            var operationCount = equation.numbers.size() - 1;
            
            var permutations = generatePermutations(operations, operationCount);
            
            if (isPossible(equation, permutations)) {
                total += equation.target;
            }
        }
        
        return total;
    }
    
    @Override
    public Long partTwo() {
        var total = 0L;
        
        for (var equation : equations) {
            var operations = List.of(Operation.ADD, Operation.MULTIPLY, Operation.CONCAT);
            var operationCount = equation.numbers.size() - 1;
            
            var permutations = generatePermutations(operations, operationCount);
            
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
}
