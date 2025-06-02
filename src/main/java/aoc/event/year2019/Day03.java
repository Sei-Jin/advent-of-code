package aoc.event.year2019;

import aoc.Solver;

import java.util.*;

/// # [2019-03: Crossed Wires](https://adventofcode.com/2019/day/3)
public class Day03 implements Solver<Integer, Integer> {
    
    private static final Point STARTING_LOCATION = new Point(0, 0);
    private final Map<Point, Integer> firstPoints;
    private final Map<Point, Integer> secondPoints;
    
    public Day03(String input) {
        var parts = input.split("\n");
        firstPoints = createPointMap(parse(parts[0]));
        secondPoints = createPointMap(parse(parts[1]));
    }
    
    private static List<Instruction> parse(String line) {
        return Arrays
            .stream(line.split(","))
            .map(instruction -> {
                var direction = instruction.charAt(0);
                var moveDistance = Integer.parseInt(instruction.substring(1));
                return new Instruction(direction, moveDistance);
            })
            .toList();
    }
    
    private static Map<Point, Integer> createPointMap(List<Instruction> instructions) {
        var visited = new HashMap<Point, Integer>();
        var x = STARTING_LOCATION.x;
        var y = STARTING_LOCATION.y;
        var totalSteps = 0;
        
        for (var instruction : instructions) {
            for (var steps = 0; steps < instruction.distance; steps++) {
                switch (instruction.direction) {
                    case 'U' -> y++;
                    case 'D' -> y--;
                    case 'L' -> x--;
                    case 'R' -> x++;
                }
                totalSteps++;
                
                var current = new Point(x, y);
                if (!visited.containsKey(current)) {
                    visited.put(current, totalSteps);
                }
            }
        }
        return visited;
    }
    
    /// Calculates the shortest Manhattan distance between an intersection of the two wires and
    /// their starting location.
    @Override
    public Integer partOne() {
        return firstPoints
            .keySet()
            .stream()
            .filter(secondPoints::containsKey)
            .mapToInt(point -> Math.abs(point.x) + Math.abs(point.y))
            .min()
            .orElseThrow(() -> new IllegalStateException("There were no intersections"));
    }
    
    /// Calculates the shortest combined distance of the two wires from their starting location to
    /// the first intersection point.
    @Override
    public Integer partTwo() {
        return firstPoints
            .keySet()
            .stream()
            .filter(secondPoints::containsKey)
            .mapToInt(point -> firstPoints.get(point) + secondPoints.get(point))
            .min()
            .orElseThrow(() -> new IllegalStateException("There were no intersections"));
    }
    
    private record Instruction(Character direction, int distance) {}
    
    private record Point(int x, int y) {
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point(int x1, int y1))) return false;
            return x == x1 && y == y1;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
