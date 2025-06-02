package aoc.event.year2020;

import aoc.Solver;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/// # [2020-02: Password Philosophy](https://adventofcode.com/2020/day/2)
public class Day02 implements Solver<Integer, Integer> {
    
    private static final Pattern LINE_PATTERN = Pattern.compile(
        "^(\\d+)-(\\d+) (\\w): (\\w+)$"
    );
    
    private final List<Line> lines;
    
    public Day02(String input) {
        lines = parse(input);
    }
    
    private static List<Line> parse(String input) {
        return input
            .lines()
            .map(LINE_PATTERN::matcher)
            .flatMap(Matcher::results)
            .map(result -> {
                var first = Integer.parseInt(result.group(1));
                var second = Integer.parseInt(result.group(2));
                var character = result.group(3).charAt(0);
                var password = result.group(4);
                return new Line(first, second, character, password);
            })
            .toList();
    }
    
    @Override
    public Integer partOne() {
        return (int) lines
            .stream()
            .filter(line -> {
                var letterCount = line.password()
                    .chars()
                    .map(i -> (char) i)
                    .filter(character -> character == line.character())
                    .count();
                return letterCount >= line.first() && letterCount <= line.second();
            })
            .count();
    }
    
    @Override
    public Integer partTwo() {
        return (int) lines
            .stream()
            .filter(line ->
                line.character() == line.password().charAt(line.first() - 1)
                    ^ line.character() == line.password().charAt(line.second() - 1)
            )
            .count();
    }
    
    private record Line(int first, int second, char character, String password) {}
}
