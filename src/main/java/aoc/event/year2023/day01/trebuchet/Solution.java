package aoc.event.year2023.day01.trebuchet;

import aoc.Runner;
import aoc.Solver;

import java.util.HashMap;
import java.util.List;

public class Solution implements Solver {
    
    /// Stores the puzzle input.
    private final List<String> lines;
    
    /// Initializes the solution.
    public Solution(String input) {
        lines = input.lines().toList();
    }
    
    /// @return the sum of all the calibration values.
    @Override
    public Integer partOne() {
        return lines
                .stream()
                .mapToInt(line -> {
                    final var firstDigit = findFirstDigit(line);
                    final var lastDigit = findLastDigit(line);
                    return firstDigit * 10 + lastDigit;
                })
                .sum();
    }
    
    private static int findFirstDigit(String line) {
        for (var i = 0; i < line.length(); i++) {
            final var character = line.charAt(i);
            
            if (Character.isDigit(character)) {
                return Character.getNumericValue(character);
            }
        }
        
        throw new IllegalArgumentException(
                "The input did not contain any digits for line: " + line
        );
    }
    
    private static int findLastDigit(String line) {
        for (int i = line.length() - 1; i >= 0; i--) {
            final var character = line.charAt(i);
            
            if (Character.isDigit(character)) {
                return Character.getNumericValue(character);
            }
        }
        
        throw new IllegalArgumentException(
                "The input did not contain any digits for line: " + line
        );
    }
    
    /// @return the sum of all the calibration values.
    @Override
    public Integer partTwo() {
        var calibrationTotal = 0;
        final var digitsMap = createDigitsMap();

        for (String line : lines) {
            var firstDigit = -1;
            var lastDigit = -1;
            
            for (int i = 0; i < line.length(); i++) {
                final var character = line.charAt(i);
                
                if (Character.isDigit(character)) {
                    final var digit = Character.getNumericValue(character);
                    
                    if (firstDigit == -1) {
                        firstDigit = digit;
                    }
                    lastDigit = digit;
                } else {
                    for (int j = i + 3; j < line.length() && j < 5; j++) {
                        if (Character.isDigit(line.charAt(j))) {
                            break;
                        }
                        
                        final var substring = line.substring(i, j);
                        
                        if (digitsMap.containsKey(substring)) {
                            final var digit = digitsMap.get(substring);
                            
                            if (firstDigit == -1) {
                                firstDigit = digit;
                            }
                            lastDigit = digit;
                        }
                    }
                }
            }
            
            if (firstDigit == -1 || lastDigit == -1) {
                throw new IllegalArgumentException("The input string did not contain a digit");
            }
            
            calibrationTotal += firstDigit * 10 + lastDigit;
        }
        
        return calibrationTotal;
    }
    
    private static HashMap<String, Integer> createDigitsMap() {
        final var digitsMap = new HashMap<String, Integer>();
        
        digitsMap.put("one", 1);
        digitsMap.put("two", 2);
        digitsMap.put("three", 3);
        digitsMap.put("four", 4);
        digitsMap.put("five", 5);
        digitsMap.put("six", 6);
        digitsMap.put("seven", 7);
        digitsMap.put("eight", 8);
        digitsMap.put("nine", 9);
        
        return digitsMap;
    }
    
    /// Runs the solution
    public static void main(String[] args) {
        Runner.runAndPrint(2023, 1);
    }
}