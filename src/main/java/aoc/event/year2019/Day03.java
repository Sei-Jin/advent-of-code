package aoc.event.year2019;

import aoc.Solver;

import java.awt.*;
import java.util.*;
import java.util.List;

/// --- Day 3: Crossed Wires ---
///
/// Puzzle Input - Two sets of instructions, one for each wire. Each set of instructions is a
/// `String` of comma-separated values, and each instruction contains the direction and distance the
/// wire should follow.
public class Day03 implements Solver<Integer> {
    
    /// The location of the central port is (0, 0). The central port is the starting location of the
    /// wires, and it does not count if the wires cross at the central port location, since they
    /// cross there by default.
    private static final Point STARTING_LOCATION = new Point(0, 0);
    
    
    private final Map<Point, Integer> firstWirePoints;
    private final Map<Point, Integer> secondWirePoints;
    
    
    public Day03(String input) {
        final var parts = input.split("\n");
        firstWirePoints = createWirePointMapping(parseInstructionList(parts[0]));
        secondWirePoints = createWirePointMapping(parseInstructionList(parts[1]));
    }
    
    /// Calculates the shortest Manhattan distance between an intersection of the two wires and
    /// their starting location.
    ///
    /// @return the Manhattan distance from the central port to the closest intersection of the two
    ///  wires.
    @Override
    public Integer partOne() {
        final var crossingPoints = new HashSet<>(firstWirePoints.keySet());
        crossingPoints.retainAll(secondWirePoints.keySet());
        return calculateClosestDistance(crossingPoints);
    }
    
    
    /// Parses a line from the puzzle input and returns a `List` of `Instruction`.
    ///
    /// @param inputLine a line from the puzzle input.
    /// @return a `List` of `Instruction` parsed from a line of the puzzle input.
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
    ///
    /// @param instructions a `List` of moves that the wire should follow.
    /// @return a `HashMap` the unique points crossed by a wire and the total length of the wire
    ///  when it first reached each point.
    private static Map<Point, Integer> createWirePointMapping(List<Instruction> instructions) {
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
                
                final var pointVisited = new Point(xPosition, yPosition);
                
                if (!pointsVisited.containsKey(pointVisited)) {
                    pointsVisited.put(pointVisited, totalSteps);
                }
            }
        }
        
        return pointsVisited;
    }
    
    
    /// Calculates the shortest Manhattan distance from an intersection between the two wires and
    /// their starting location.
    ///
    /// @param crossingPoints a `HashSet` of the points where the two wires cross.
    /// @return the Manhattan distance from the central port to the closest intersection of the two
    ///  wires.
    private static int calculateClosestDistance(HashSet<Point> crossingPoints) {
        var closestDistance = 0;
        
        for (final var crossingPoint : crossingPoints) {
            final var distance = Math.abs(crossingPoint.x) + Math.abs(crossingPoint.y);
            
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
    /// an intersection point.
    ///
    /// @return the shortest combined distance of the two wires it takes to reach an intersection
    ///  between them.
    @Override
    public Integer partTwo() {
        final var crossingPoints = new HashSet<>(firstWirePoints.keySet());
        crossingPoints.retainAll(secondWirePoints.keySet());
        
        return calculateShortestCombinedDistance(crossingPoints, firstWirePoints, secondWirePoints);
    }
    
    
    /// Calculates the shortest combined distance of the two wires from their starting location to
    /// an intersection point.
    ///
    /// @param crossingPoints a `HashSet` of the points where the two wires cross.
    /// @param firstWirePoints a `HashMap` that maps the points where the first wire crosses to the
    /// shortest length of the wire at the point of crossing.
    /// @param secondWirePoints a `HashMap` that maps the points where the second wire crosses to
    /// the shortest length of the wire at the point of crossing.
    /// @return the shortest combined distance of the two wires from their starting location to an
    /// intersection point.
    private int calculateShortestCombinedDistance
    (
        Set<Point> crossingPoints,
        Map<Point, Integer> firstWirePoints,
        Map<Point, Integer> secondWirePoints
    ) {
        var fewestCombinedSteps = 0;
        
        for (final var crossingPoint : crossingPoints) {
            final var combinedSteps =
                firstWirePoints.get(crossingPoint) + secondWirePoints.get(crossingPoint);
            
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
    
    
    /// The Move record class stores the information for a move in the instruction set.
    ///
    /// @param direction the direction to be taken. Can be `U`, `D`, `L`, `R`.
    /// @param distance the distance to be travelled.
    private record Instruction(Character direction, int distance) {}
}
