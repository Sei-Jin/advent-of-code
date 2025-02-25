package aoc.event.year2020.day02.passwordPhilosophy;

import aoc.Runner;
import aoc.Solver;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Solution implements Solver {
    
    private static final Pattern LINE_PATTERN = Pattern.compile(
            "^(\\d+)-(\\d+) (\\w): (\\w+)$"
    );
    
    private final List<Line> lines;
    
    public Solution(String input) {
        lines = Collections.unmodifiableList(parse(input));
    }
    
    private static List<Line> parse(String input) {
        return input.lines().map(line -> {
            final var matcher = LINE_PATTERN.matcher(line);
            
            if (matcher.find()) {
                final var first = Integer.parseInt(matcher.group(1));
                final var second = Integer.parseInt(matcher.group(2));
                final var character = matcher.group(3).charAt(0);
                final var passwordString = matcher.group(4);
                
                final var password = passwordString.chars()
                        .mapToObj(c -> (char) c)
                        .toList();
                
                return new Line(first, second, character, password);
            } else {
                throw new IllegalArgumentException(
                        "Input was in unexpected format for line: " + line
                );
            }
        }).toList();
    }
    
    /// @return the number of valid passwords according to the set policies.
    @Override
    public Integer partOne() {
        var validPasswords = 0;
        
        for (final var line : lines) {
            var letterCount = 0;
            
            for (final var character : line.password) {
                if (character == line.character) {
                    letterCount++;
                }
            }
            
            if (letterCount >= line.first && letterCount <= line.second) {
                validPasswords++;
            }
        }
        
        return validPasswords;
    }
    
    /// @return the number of valid passwords according to the new interpretation of the policies.
    @Override
    public Integer partTwo() {
        var validPasswords = 0;
        
        for (final var line : lines) {
            final var onlyOnePresent = (line.character == line.password.get(line.first - 1) ^
                            line.character == line.password.get(line.second - 1));
            
            if (onlyOnePresent) {
                validPasswords++;
            }
        }
        
        return validPasswords;
    }
    
    private record Line(int first, int second, char character, List<Character> password) {}
    
    public static void main(String[] args) {
        Runner.runAndPrint(2020, 2);
    }
}
