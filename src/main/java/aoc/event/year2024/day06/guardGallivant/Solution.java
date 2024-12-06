package aoc.event.year2024.day06.guardGallivant;

import aoc.PuzzleSolver;

import java.util.HashSet;
import java.util.List;

public class Solution implements PuzzleSolver
{
    @Override
    public Object partOne(List<String> inputLines)
    {
        char[][] map = getMap(inputLines);
        
        State startingState = getStartingState(map);
        
        HashSet<Position> previousPositions = new HashSet<>();
        previousPositions.add(startingState.position());
        
        int currentRowIndex = startingState.position().rowIndex();
        int currentColumnIndex = startingState.position().columnIndex();
        Direction currentDirection = startingState.direction();
        
        while (true)
        {
            // Move forwards
            switch (currentDirection)
            {
                case UP -> currentRowIndex--;
                case RIGHT -> currentColumnIndex++;
                case DOWN -> currentRowIndex++;
                case LEFT -> currentColumnIndex--;
            }
            
            // Out of bounds
            if (currentRowIndex < 0 ||
                    currentRowIndex >= map.length ||
                    currentColumnIndex < 0 ||
                    currentColumnIndex >= map[currentRowIndex].length)
            {
                break;
            }
            else
            {
                if (map[currentRowIndex][currentColumnIndex] == '#')
                {
                    // Move backwards
                    switch (currentDirection)
                    {
                        case UP -> currentRowIndex++;
                        case RIGHT -> currentColumnIndex--;
                        case DOWN -> currentRowIndex--;
                        case LEFT -> currentColumnIndex++;
                    }
                    
                    currentDirection = Direction.getRightDirection(currentDirection);
                }
                
                previousPositions.add(new Position(currentRowIndex, currentColumnIndex));
            }
        }
        
        return previousPositions.size();
    }
    
    private static char[][] getMap(List<String> inputLines)
    {
        char[][] map = new char[inputLines.size()][inputLines.getFirst().length()];
        
        for (int rowIndex = 0; rowIndex < inputLines.size(); rowIndex++)
        {
            for (int columnIndex = 0; columnIndex < inputLines.getFirst().length(); columnIndex++)
            {
                map[rowIndex][columnIndex] = inputLines.get(rowIndex).charAt(columnIndex);
            }
        }
        
        return map;
    }
    
    enum Direction
    {
        UP('^'),
        RIGHT('>'),
        DOWN('v'),
        LEFT('<');
        
        final char symbol;
        
        Direction(char symbol)
        {
            this.symbol = symbol;
        }
        
        char getSymbol()
        {
            return this.symbol;
        }
        
        static Direction getDirection(char symbol)
        {
            for (Direction direction : Direction.values())
            {
                if (direction.getSymbol() == symbol)
                {
                    return direction;
                }
            }
            
            return null;
        }
        
        static Direction getRightDirection(Direction direction)
        {
            return switch (direction)
            {
                case UP -> Direction.RIGHT;
                case RIGHT -> Direction.DOWN;
                case DOWN -> Direction.LEFT;
                case LEFT -> Direction.UP;
            };
        }
    }
    
    record Position(int rowIndex, int columnIndex) {}
    record State(Position position, Direction direction) {}
    
    private static State getStartingState(char[][] map)
    {
        for (int rowIndex = 0; rowIndex < map.length; rowIndex++)
        {
            for (int columnIndex = 0; columnIndex < map[rowIndex].length; columnIndex++)
            {
                char currentSymbol = map[rowIndex][columnIndex];
                Direction direction = Direction.getDirection(currentSymbol);
                
                if (direction != null)
                {
                    Position position = new Position(rowIndex, columnIndex);
                    return new State(position, direction);
                }
            }
        }
        
        throw new RuntimeException("No starting position found");
    }
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        return null;
    }
}
