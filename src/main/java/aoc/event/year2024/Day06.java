package aoc.event.year2024;

import aoc.Solver;
import aoc.util.Parse;

import java.util.HashSet;
import java.util.Set;

/// # [2024-06: Guard Gallivant](https://adventofcode.com/2024/day/6)
public class Day06 implements Solver<Integer, Integer> {
    
    /// This character represents any obstructions that can be run into.
    private static final char OBSTRUCTION = '#';
    private final char[][] map;
    
    public Day06(String input) {
        map = Parse.toCharGrid(input);
    }
    
    @Override
    public Integer partOne() {
        var starting = determineStartingState();
        var positions = getAllPositions(starting, map);
        return positions.size();
    }
    
    private State determineStartingState() {
        var position = findStartingPosition(map);
        var value = map[position.row()][position.column()];
        var direction = Direction.of(value);
        return new State(position, direction);
    }
    
    private static Position findStartingPosition(char[][] map) {
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[row].length; column++) {
                char current = map[row][column];
                if (current == '^' || current == '>' || current == 'v' || current == '<') {
                    return new Position(row, column);
                }
            }
        }
        throw new IllegalArgumentException("No starting position found");
    }
    
    private static Set<Position> getAllPositions(State starting, char[][] map) {
        var positions = new HashSet<Position>();
        positions.add(starting.position());
        
        var row = starting.position().row();
        var column = starting.position().column();
        var direction = starting.direction();
        while (true) {
            switch (direction) {
                case UP -> row--;
                case RIGHT -> column++;
                case DOWN -> row++;
                case LEFT -> column--;
            }
            
            if (isWithinBounds(row, column, map)) {
                break;
            }
            else if (map[row][column] == OBSTRUCTION) {
                switch (direction.ofOpposite()) {
                    case UP -> row--;
                    case RIGHT -> column++;
                    case DOWN -> row++;
                    case LEFT -> column--;
                }
                direction = direction.ofRight();
            }
            else {
                positions.add(new Position(row, column));
            }
        }
        return positions;
    }
    
    private static boolean isWithinBounds(int row, int column, char[][] map) {
        return (row < 0)
            || (row >= map.length)
            || (column < 0)
            || (column >= map[row].length);
    }
    
    @Override
    public Integer partTwo() {
        return 0;
    }
    
    private record Position(int row, int column) {}
    
    private enum Direction {
        
        UP, RIGHT, DOWN, LEFT;
        
        private Direction ofRight() {
            return switch (this) {
                case UP -> Direction.RIGHT;
                case RIGHT -> Direction.DOWN;
                case DOWN -> Direction.LEFT;
                case LEFT -> Direction.UP;
            };
        }
        
        private Direction ofOpposite() {
            return switch (this) {
                case UP -> Direction.DOWN;
                case RIGHT -> Direction.LEFT;
                case DOWN -> Direction.UP;
                case LEFT -> Direction.RIGHT;
            };
        }
        
        private static Direction of(char symbol) {
            return switch (symbol) {
                case '^' -> Direction.UP;
                case '>' -> Direction.RIGHT;
                case 'v' -> Direction.DOWN;
                case '<' -> Direction.LEFT;
                default -> throw new IllegalArgumentException("Invalid symbol provided");
            };
        }
    }
    
    record State(Position position, Direction direction) {}
}
