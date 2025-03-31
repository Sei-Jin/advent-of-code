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
        firstPoints = createPointMap(parseInstructionList(parts[0]));
        secondPoints = createPointMap(parseInstructionList(parts[1]));
    }
    
    /// Parses a line from the puzzle input and returns a `List` of `Instruction`.
    private static List<Instruction> parseInstructionList(String inputLine) {
        final var instructionList = new ArrayList<Instruction>();
        final var instructions = List.of(inputLine.split(","));
        
        for (final var instruction : instructions) {
            final var direction = instruction.charAt(0);
            final var moveDistance = Integer.parseInt(instruction.substring(1));
            instructionList.add(new Instruction(direction, moveDistance));
        }
        
        return instructionList;
    }
    
    /// Creates a mapping between the unique points crossed by a wire and the total length of the
    /// wire when it first reached each point.
    private static Map<Point, Integer> createPointMap(List<Instruction> instructions) {
        final var pointsVisited = new HashMap<Point, Integer>();
        
        var xPosition = STARTING_LOCATION.x;
        var yPosition = STARTING_LOCATION.y;
        var totalSteps = 0;
        
        for (final var instruction : instructions) {
            for (var steps = 0; steps < instruction.distance; steps++) {
                switch (instruction.direction) {
                    case 'U' -> yPosition++;
                    case 'D' -> yPosition--;
                    case 'L' -> xPosition--;
                    case 'R' -> xPosition++;
                }
                totalSteps++;
                
                final var current = new Point(xPosition, yPosition);
                
                if (!pointsVisited.containsKey(current)) {
                    pointsVisited.put(current, totalSteps);
                }
            }
        }
        
        return pointsVisited;
    }
    
    /// Calculates the shortest Manhattan distance between an intersection of the two wires and
    /// their starting location.
    @Override
    public Integer partOne() {
        final var crossings = new HashSet<>(firstPoints.keySet());
        crossings.retainAll(secondPoints.keySet());
        return calculateClosestDistance(crossings);
    }

    private static int calculateClosestDistance(HashSet<Point> points) {
        var closestDistance = 0;
        
        for (final var point : points) {
            final var distance = Math.abs(point.x) + Math.abs(point.y);
            
            if (distance == 0) {
                continue;
            }
            
            if (closestDistance == 0) {
                closestDistance = distance;
            } else {
                closestDistance = Math.min(closestDistance, distance);
            }
        }
        
        return closestDistance;
    }
    
    /// Calculates the shortest combined distance of the two wires from their starting location to
    /// the first intersection point.
    @Override
    public Integer partTwo() {
        final var crossingPoints = new HashSet<>(firstPoints.keySet());
        crossingPoints.retainAll(secondPoints.keySet());
        
        return calculateShortestCombinedDistance(crossingPoints, firstPoints, secondPoints);
    }
    
    private int calculateShortestCombinedDistance(
        Set<Point> points,
        Map<Point, Integer> firstPoints,
        Map<Point, Integer> secondPoints
    ) {
        var fewestCombinedSteps = 0;
        
        for (final var point : points) {
            final var combinedSteps = firstPoints.get(point) + secondPoints.get(point);
            
            if (combinedSteps == 0) {
                continue;
            }
            
            if (fewestCombinedSteps == 0) {
                fewestCombinedSteps = combinedSteps;
            } else {
                fewestCombinedSteps = Math.min(fewestCombinedSteps, combinedSteps);
            }
        }
        
        return fewestCombinedSteps;
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
