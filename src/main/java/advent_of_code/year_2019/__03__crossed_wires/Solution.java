package advent_of_code.year_2019.__03__crossed_wires;

import advent_of_code.PuzzleSolver;

import java.awt.*;

import java.util.*;
import java.util.List;

/**
 * <p>--- Day 3: Crossed Wires ---
 *
 * <p>Puzzle Input - Two sets of instructions, one for each wire. Each set of instructions is a {@code String}
 * of comma-separated values, and each instruction contains the direction and distance the wire should follow.
 * <ul>
 *     <li>{@link Solution#partOne(List)} - Calculates the shortest Manhattan distance between an intersection
 *     of the two wires and their starting location.</li>
 *     <li>{@link Solution#partTwo(List)} - Calculates the shortest combined distance of the two wires from their
 *     starting location to an intersection point.</li>
 * </ul>
 */
public class Solution implements PuzzleSolver
{
    /**
     * The location of the central port is (0, 0). The central port is the starting location of the wires,
     * and it does not count if the wires cross at the central port location, since they cross there by default.
     */
    private static final Point STARTING_LOCATION = new Point(0, 0);
    
    
    /**
     * Calculates the shortest Manhattan distance between an intersection of the two wires and their starting location.
     *
     * @param inputLines the puzzle input.
     * @return the Manhattan distance from the central port to the closest intersection of the two wires.
     */
    @Override
    public Object partOne(List<String> inputLines)
    {
        List<Instruction> firstWireInstructions = parseInstructionList(inputLines.getFirst());
        List<Instruction> secondWireInstructions = parseInstructionList(inputLines.getLast());
        
        HashMap<Point, Integer> firstWirePoints = createWirePointMapping(firstWireInstructions);
        HashMap<Point, Integer> secondWirePoints = createWirePointMapping(secondWireInstructions);
        
        HashSet<Point> crossingPoints = new HashSet<>(firstWirePoints.keySet());
        crossingPoints.retainAll(secondWirePoints.keySet());
        
        return calculateClosestDistance(crossingPoints);
    }
    
    
    /**
     * The Move record class stores the information for a move in the instruction set.
     *
     * @param direction the direction to be taken. Can be {@code U}, {@code D}, {@code L}, {@code R}.
     * @param distance the distance to be travelled.
     */
    private record Instruction(Character direction, int distance) {}
    
    
    /**
     * Parses a line from the puzzle input and returns a {@code List} of {@code Instruction}.
     *
     * @param inputLine a line from the puzzle input.
     * @return a {@code List} of {@code Instruction} parsed from a line of the puzzle input.
     */
    private static List<Instruction> parseInstructionList(String inputLine)
    {
        List<Instruction> instructionList = new ArrayList<>();
        
        List<String> instructions = List.of(inputLine.split(","));
        
        for (String instruction : instructions)
        {
            Character direction = instruction.charAt(0);
            int moveDistance = Integer.parseInt(instruction.substring(1));
            
            instructionList.add(new Instruction(direction, moveDistance));
        }
        
        return instructionList;
    }
    
    
    /**
     * Creates a mapping between the unique points crossed by a wire and the total length of the wire when it first
     * reached each point.
     *
     * @param instructions a {@code List} of moves that the wire should follow.
     * @return a {@code HashMap} the unique points crossed by a wire and the total length of the wire when it first
     * reached each point.
     */
    private static HashMap<Point, Integer> createWirePointMapping(List<Instruction> instructions)
    {
        HashMap<Point, Integer> pointsVisited = new HashMap<>();
        
        int xPosition = STARTING_LOCATION.x;
        int yPosition = STARTING_LOCATION.y;
        int totalSteps = 0;

        for (Instruction instruction : instructions)
        {
            for (int steps = 0; steps < instruction.distance; steps++)
            {
                switch (instruction.direction)
                {
                    case 'U' -> yPosition++;
                    case 'D' -> yPosition--;
                    case 'L' -> xPosition--;
                    case 'R' -> xPosition++;
                }
                totalSteps++;
                
                Point pointVisited = new Point(xPosition, yPosition);
                
                if (!pointsVisited.containsKey(pointVisited))
                {
                    pointsVisited.put(pointVisited, totalSteps);
                }
            }
        }
        
        return pointsVisited;
    }
    
    
    /**
     * Calculates the shortest Manhattan distance from an intersection between the two wires and their
     * starting location.
     *
     * @param crossingPoints a {@code HashSet} of the points where the two wires cross.
     * @return the Manhattan distance from the central port to the closest intersection of the two wires.
     */
    private static int calculateClosestDistance(HashSet<Point> crossingPoints)
    {
        int closestDistance = 0;
        
        for (Point crossingPoint : crossingPoints)
        {
            int distance = Math.abs(crossingPoint.x) + Math.abs(crossingPoint.y);
            
            if (distance == 0)
            {
                continue;
            }
            
            if (closestDistance == 0)
            {
                closestDistance = distance;
            }
            else
            {
                closestDistance = Math.min(closestDistance, distance);
            }
        }
        
        return closestDistance;
    }
    
    
    /**
     * Calculates the shortest combined distance of the two wires from their starting location to an intersection point.
     *
     * @param inputLines the puzzle input.
     * @return the shortest combined distance of the two wires it takes to reach an intersection between them.
     */
    @Override
    public Object partTwo(List<String> inputLines)
    {
        List<Instruction> firstWireInstructions = parseInstructionList(inputLines.getFirst());
        List<Instruction> secondWireInstructions = parseInstructionList(inputLines.getLast());
        
        HashMap<Point, Integer> firstWirePoints = createWirePointMapping(firstWireInstructions);
        HashMap<Point, Integer> secondWirePoints = createWirePointMapping(secondWireInstructions);
        
        HashSet<Point> crossingPoints = new HashSet<>(firstWirePoints.keySet());
        crossingPoints.retainAll(secondWirePoints.keySet());
        
        return calculateShortestCombinedDistance(crossingPoints, firstWirePoints, secondWirePoints);
    }
    
    
    /**
     * Calculates the shortest combined distance of the two wires from their starting location to an intersection point.
     *
     * @param crossingPoints a {@code HashSet} of the points where the two wires cross.
     * @param firstWirePoints a {@code HashMap} that maps the points where the first wire crosses to
     *                       the shortest length of the wire at the point of crossing.
     * @param secondWirePoints a {@code HashMap} that maps the points where the second wire crosses to
     *                       the shortest length of the wire at the point of crossing.
     * @return the shortest combined distance of the two wires from their starting location to an intersection point.
     */
    private int calculateShortestCombinedDistance
    (
            HashSet<Point> crossingPoints,
            HashMap<Point, Integer> firstWirePoints,
            HashMap<Point, Integer> secondWirePoints
    )
    {
        int fewestCombinedSteps = 0;
        
        for (Point crossingPoint : crossingPoints)
        {
            int combinedSteps = firstWirePoints.get(crossingPoint) + secondWirePoints.get(crossingPoint);
            
            if (combinedSteps == 0)
            {
                continue;
            }
            
            if (fewestCombinedSteps == 0)
            {
                fewestCombinedSteps = combinedSteps;
            }
            else
            {
                fewestCombinedSteps = Math.min(fewestCombinedSteps, combinedSteps);
            }
        }
        
        return fewestCombinedSteps;
    }
}
