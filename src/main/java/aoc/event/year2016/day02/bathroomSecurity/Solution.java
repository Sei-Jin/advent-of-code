package aoc.event.year2016.day02.bathroomSecurity;

import aoc.Runner;
import aoc.Solver;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/// --- Day 2: Bathroom Security ---
public class Solution implements Solver {
    
    private final List<List<Character>> instructionLists;
    
    public Solution(String input) {
        instructionLists = parse(input);
    }
    
    private static List<List<Character>> parse(String input) {
        final var instructionLists = new ArrayList<List<Character>>();
        final var lines = input.lines().toList();
        
        for (final var line : lines) {
            final var instructions = new ArrayList<Character>();
            
            for (var i = 0; i < line.length(); i++) {
                instructions.add(line.charAt(i));
            }
            
            instructionLists.add(instructions);
        }
        
        return instructionLists;
    }
    
    /// @return the bathroom code.
    @Override
    public String partOne() {
        final var bathroomCode = new StringBuilder();
        final var keypad = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        final var position = new Point(1, 1);
        
        for (final var list : instructionLists) {
            for (final var instruction : list) {
                switch (instruction) {
                    case 'U' -> {
                        if (position.y > 0) {
                            position.y--;
                        }
                    }
                    case 'D' -> {
                        if (position.y < keypad.length - 1) {
                            position.y++;
                        }
                    }
                    case 'L' -> {
                        if (position.x > 0) {
                            position.x--;
                        }
                    }
                    case 'R' -> {
                        if (position.x < keypad.length - 1) {
                            position.x++;
                        }
                    }
                }
            }
            
            bathroomCode.append(keypad[position.y][position.x]);
        }
        
        return bathroomCode.toString();
    }
    
    /// @return the correct bathroom code.
    @Override
    public String partTwo() {
        final var bathroomCode = new StringBuilder();
        final var keypad = new char[][]{
            {' ', ' ', '1', ' ', ' '},
            {' ', '2', '3', '4', ' '},
            {'5', '6', '7', '8', '9'},
            {' ', 'A', 'B', 'C', ' '},
            {' ', ' ', 'D', ' ', ' '},
        };
        final var position = new Point(0, 2);
        
        for (final var list : instructionLists) {
            for (final var instruction : list) {
                switch (instruction) {
                    case 'U' -> {
                        if (position.y > 0 && keypad[position.y - 1][position.x] != ' ') {
                            position.y--;
                        }
                    }
                    case 'D' -> {
                        if (position.y < keypad.length - 1 && keypad[position.y + 1][position.x] != ' ') {
                            position.y++;
                        }
                    }
                    case 'L' -> {
                        if (position.x > 0 && keypad[position.y][position.x - 1] != ' ') {
                            position.x--;
                        }
                    }
                    case 'R' -> {
                        if (position.x < keypad.length - 1 && keypad[position.y][position.x + 1] != ' ') {
                            position.x++;
                        }
                    }
                }
            }
            
            bathroomCode.append(keypad[position.y][position.x]);
        }
        
        return bathroomCode.toString();
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2016, 2);
    }
}
