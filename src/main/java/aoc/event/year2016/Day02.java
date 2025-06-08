package aoc.event.year2016;

import aoc.Solver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/// # [2016-02: Bathroom Security](https://adventofcode.com/2016/day/2)
public class Day02 implements Solver<String, String> {
    
    private final List<List<Direction>> instructionLists;
    
    public Day02(String input) {
        instructionLists = parse(input);
    }
    
    private static List<List<Direction>> parse(String input) {
        return input
            .lines()
            .map(line -> line
                .chars()
                .mapToObj(i -> (char) i)
                .map(Direction::parse)
                .toList())
            .toList();
    }
    
    private static Map<Point, Character> convertToKeypad(String input) {
        var lines = input
            .lines()
            .toList();
        var keypad = new HashMap<Point, Character>();
        
        for (int y = 0; y < lines.size(); y++) {
            var line = lines.get(y);
            for (int x = 0; x < line.length(); x += 2) {
                var character = line.charAt(x);
                if (character != ' ') {
                    keypad.put(new Point(x / 2, y), character);
                }
            }
        }
        return keypad;
    }
    
    private static String buildBathroomCode(
        Point current,
        Map<Point, Character> keypad,
        List<List<Direction>> instructionLists
    ) {
        var bathroomCode = new StringBuilder();
        for (var list : instructionLists) {
            for (var instruction : list) {
                switch (instruction) {
                    case UP -> {
                        current.y--;
                        if (!keypad.containsKey(current)) {
                            current.y++;
                        }
                    }
                    case DOWN -> {
                        current.y++;
                        if (!keypad.containsKey(current)) {
                            current.y--;
                        }
                    }
                    case LEFT -> {
                        current.x--;
                        if (!keypad.containsKey(current)) {
                            current.x++;
                        }
                    }
                    case RIGHT -> {
                        current.x++;
                        if (!keypad.containsKey(current)) {
                            current.x--;
                        }
                    }
                }
            }
            bathroomCode.append(keypad.get(current));
        }
        return bathroomCode.toString();
    }
    
    @Override
    public String partOne() {
        var input = """
            1 2 3
            4 5 6
            7 8 9
            """;
        var keypad = convertToKeypad(input);
        var current = new Point(1, 1);
        return buildBathroomCode(current, keypad, instructionLists);
    }
    
    @Override
    public String partTwo() {
        var input = """
                1
              2 3 4
            5 6 7 8 9
              A B C
                D
            """;
        var keypad = convertToKeypad(input);
        var current = new Point(0, 2);
        return buildBathroomCode(current, keypad, instructionLists);
    }
    
    private enum Direction {
        UP, DOWN, LEFT, RIGHT;
        
        private static Direction parse(char character) {
            return switch (character) {
                case 'U' -> UP;
                case 'D' -> DOWN;
                case 'L' -> LEFT;
                case 'R' -> RIGHT;
                default -> throw new IllegalStateException("Unexpected value: " + character);
            };
        }
    }
    
    private static class Point {
        
        private int x;
        private int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point point)) return false;
            return x == point.x && y == point.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
