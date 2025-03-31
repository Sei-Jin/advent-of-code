package aoc.event.year2019;

import aoc.Solver;

import java.util.*;

public class Day03 implements Solver<Integer> {
    
    /// The location of the central port is (0, 0). The central port is the starting location of the
    /// wires, and it does not count if the wires cross at the central port location, since they
    /// cross there by default.
    private static final Point STARTING_LOCATION = new Point(0, 0);
    
    private final Map<Point, Integer> firstPoints;
    private final Map<Point, Integer> secondPoints;
    
    public Day03(String input) {
        final var parts = input.split("\n");
        firstPoints = createPointMap(parse(parts[0]));
        secondPoints = createPointMap(parse(parts[1]));
    }
    
    /// Parses a line from the puzzle input.
    private static List<Instruction> parse(String line) {
        return Arrays.stream(line.split(","))
            .map(instruction -> {
                final var direction = instruction.charAt(0);
                final var moveDistance = Integer.parseInt(instruction.substring(1));
                return new Instruction(direction, moveDistance);
            })
            .toList();
    }
    
    /// Creates a mapping between the unique points crossed by a wire and the total length of the
    /// wire when it first reached each point.
    private static Map<Point, Integer> createPointMap(List<Instruction> instructions) {
        final var visited = new HashMap<Point, Integer>();
        
        var x = STARTING_LOCATION.x;
        var y = STARTING_LOCATION.y;
        var totalSteps = 0;
        
        for (final var instruction : instructions) {
            for (var steps = 0; steps < instruction.distance; steps++) {
                switch (instruction.direction) {
                    case 'U' -> y++;
                    case 'D' -> y--;
                    case 'L' -> x--;
                    case 'R' -> x++;
                }
                totalSteps++;
                
                final var current = new Point(x, y);
                
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
        final var intersections = new HashSet<>(firstPoints.keySet());
        intersections.retainAll(secondPoints.keySet());
        
        return intersections
            .stream()
            .mapToInt(point -> Math.abs(point.x) + Math.abs(point.y))
            .min()
            .orElseThrow(() -> new IllegalStateException("There were no intersections"));
    }
    
    /// Calculates the shortest combined distance of the two wires from their starting location to
    /// the first intersection point.
    @Override
    public Integer partTwo() {
        final var intersections = new HashSet<>(firstPoints.keySet());
        intersections.retainAll(secondPoints.keySet());
        
        return intersections
            .stream()
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
