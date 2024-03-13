package year2019;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class Day03
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));

        partOne(inputLines);
    }


    public static int partOne(List<String> inputLines)
    {
        Set<Point> firstWirePointsVisited = new HashSet<>();
        Set<Point> secondWirePointsVisited = new HashSet<>();

        determineWirePoints(inputLines.getFirst(), firstWirePointsVisited);
        determineWirePoints(inputLines.getLast(), secondWirePointsVisited);

        int closestIntersectionDistance = 0;

        for (Point firstWirePoint : firstWirePointsVisited)
        {
            for (Point secondWirePoint : secondWirePointsVisited)
            {
                boolean pathIntersection = firstWirePoint.equals(secondWirePoint);

                if (pathIntersection)
                {
                    boolean atCentralPort = (firstWirePoint.x == 0 && firstWirePoint.y == 0);

                    if (atCentralPort)
                    {
                        continue;
                    }

                    int currentIntersectionDistance = Math.abs(firstWirePoint.x) + Math.abs(firstWirePoint.y);

                    if (closestIntersectionDistance == 0 ||
                            currentIntersectionDistance < closestIntersectionDistance)
                    {
                        closestIntersectionDistance = currentIntersectionDistance;
                    }
                }
            }
        }

        System.out.println("The Manhattan distance from the central port to the closest intersection is: " +
                closestIntersectionDistance);

        return closestIntersectionDistance;
    }


    private static void determineWirePoints(String line, Set<Point> pointsVisited)
    {
        int xPosition = 0;
        int yPosition = 0;

        Point initialPoint = new Point(xPosition, yPosition);
        pointsVisited.add(initialPoint);

        List<String> moves = List.of(line.split(","));

        for (String move : moves)
        {
            String directionValue = String.valueOf(move.charAt(0));
            Direction moveDirection = Direction.getDirectionByName(directionValue);

            int moveDistance = Integer.parseInt(move.substring(1));

            for (int distanceTravelled = 0; distanceTravelled < moveDistance; distanceTravelled++)
            {
                switch (Objects.requireNonNull(moveDirection))
                {
                    case UP -> yPosition++;
                    case DOWN -> yPosition--;
                    case LEFT -> xPosition--;
                    case RIGHT -> xPosition++;
                }

                Point point = new Point(xPosition, yPosition);
                pointsVisited.add(point);
            }
        }
    }


    enum Direction
    {
        RIGHT("R"),
        LEFT("L"),
        DOWN("D"),
        UP("U");

        private final String directionValue;

        Direction(String directionValue)
        {
            this.directionValue = directionValue;
        }

        @Override
        public String toString()
        {
            return this.directionValue;
        }

        public static Direction getDirectionByName(String directionValue)
        {
            for (Direction direction : Direction.values())
            {
                if (direction.toString().equals(directionValue))
                {
                    return direction;
                }
            }

            return null;
        }
    }
}
