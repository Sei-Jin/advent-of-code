package advent_of_code.year_2019.__03__crossed_wires;

import advent_of_code.PuzzleSolver;

import java.awt.*;

import java.util.*;
import java.util.List;

public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the Manhattan distance from the central port to the closest intersection.
     */
    @Override
    public Object partOne(List<String> inputLines)
    {
        List<Move> firstMoveList = getMoves(inputLines.getFirst());
        List<Move> secondMoveList = getMoves(inputLines.getLast());
        
        Set<Point> firstWirePoints = determineWirePoints(firstMoveList);
        Set<Point> secondWirePoints = determineWirePoints(secondMoveList);
        
        Set<Point> crossingPoints = new HashSet<>(firstWirePoints);
        crossingPoints.retainAll(secondWirePoints);
        
        return getClosestDistance(crossingPoints);
    }
    
    
    private record Move(Character direction, int distance) {}
    
    
    private static List<Move> getMoves(String inputLine)
    {
        List<Move> moveList = new ArrayList<>();
        
        List<String> moves = List.of(inputLine.split(","));
        
        for (String move : moves)
        {
            Character direction = move.charAt(0);
            int moveDistance = Integer.parseInt(move.substring(1));
            
            moveList.add(new Move(direction, moveDistance));
        }
        
        return moveList;
    }
    
    
    private static HashSet<Point> determineWirePoints(List<Move> moves)
    {
        HashSet<Point> pointsVisited = new HashSet<>();
        
        int yPosition = 0;
        int xPosition = 0;
        
        Point initialPoint = new Point(0, 0);
        pointsVisited.add(initialPoint);

        for (Move move : moves)
        {
            for (int distanceTravelled = 0; distanceTravelled < move.distance; distanceTravelled++)
            {
                switch (move.direction)
                {
                    case 'U' -> yPosition++;
                    case 'D' -> yPosition--;
                    case 'L' -> xPosition--;
                    case 'R' -> xPosition++;
                }

                pointsVisited.add(new Point(xPosition, yPosition));
            }
        }
        
        return pointsVisited;
    }

    
    private static int getClosestDistance(Set<Point> crossingPoints)
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
    
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        return null;
    }
}
