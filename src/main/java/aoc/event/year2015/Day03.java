package aoc.event.year2015;

import aoc.Solver;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/// # [2015-03: Perfectly Spherical Houses in a Vacuum](https://adventofcode.com/2015/day/3)
public class Day03 implements Solver<Integer, Integer> {
    
    private final List<Direction> directions;
    
    public Day03(String input) {
        directions = parse(input);
    }
    
    private static List<Direction> parse(String input) {
        return input
            .chars()
            .mapToObj(i -> Direction.parse((char) i))
            .toList();
    }
    
    private static void increment(MutablePoint point, Direction direction) {
        switch (direction) {
            case UP -> point.y++;
            case DOWN -> point.y--;
            case LEFT -> point.x--;
            case RIGHT -> point.x++;
        }
    }
    
    @Override
    public Integer partOne() {
        var seen = new HashSet<MutablePoint>();
        
        var current = new MutablePoint(0, 0);
        seen.add(MutablePoint.copyOf(current));
        
        for (var direction : directions) {
            increment(current, direction);
            seen.add(MutablePoint.copyOf(current));
        }
        return seen.size();
    }
    
    @Override
    public Integer partTwo() {
        var santa = new MutablePoint(0, 0);
        var robot = new MutablePoint(0, 0);
        
        var seen = new HashSet<MutablePoint>();
        seen.add(MutablePoint.copyOf(santa));
        
        for (int i = 0; i < directions.size(); i++) {
            var direction = directions.get(i);
            
            if (isEven(i)) {
                increment(santa, direction);
                seen.add(MutablePoint.copyOf(santa));
            }
            else {
                increment(robot, direction);
                seen.add(MutablePoint.copyOf(robot));
            }
        }
        return seen.size();
    }
    
    private static boolean isEven(int index) {
        return index % 2 == 0;
    }
    
    private static class MutablePoint {
        
        int x;
        int y;
        
        public MutablePoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof MutablePoint that)) return false;
            return x == that.x && y == that.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        
        public static MutablePoint copyOf(MutablePoint point) {
            return new MutablePoint(point.x, point.y);
        }
    }
    
    private enum Direction {
        
        UP, DOWN, LEFT, RIGHT;
        
        private static Direction parse(char character) {
            return switch (character) {
                case '^' -> UP;
                case 'v' -> DOWN;
                case '<' -> LEFT;
                case '>' -> RIGHT;
                default -> throw new IllegalArgumentException("Unexpected value: " + character);
            };
        }
    }
}
