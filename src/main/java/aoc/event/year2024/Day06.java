package aoc.event.year2024;

import aoc.DeprecatedSolver;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Day06 implements DeprecatedSolver
{
    /// This character represents any obstructions that can be run into.
    static final char OBSTRUCTION = '#';
    
    @Override
    public Object partOne(List<String> inputLines)
    {
        char[][] map = getMap(inputLines);
        State startingState = getStartingState(map);
        
        HashSet<Position> previousPositions = getPreviousPositions(startingState, map);
        return previousPositions.size();
    }
    
    private static HashSet<Position> getPreviousPositions(State startingState, char[][] map)
    {
        HashSet<Position> previousPositions = new HashSet<>();
        previousPositions.add(startingState.position());
        
        Position currentPosition = new Position(
                startingState.position().rowIndex,
                startingState.position().columnIndex
        );
        Direction currentDirection = startingState.direction();
        
        while (true)
        {
            moveForward(currentPosition, currentDirection);
            
            if (outOfArea(map, currentPosition))
            {
                break;
            }
            else if (map[currentPosition.rowIndex][currentPosition.columnIndex] == OBSTRUCTION)
            {
                moveBackward(currentPosition, currentDirection);
                currentDirection = Direction.getRightDirection(currentDirection);
            }
            else
            {
                Position position = new Position(currentPosition.rowIndex, currentPosition.columnIndex);
                previousPositions.add(position);
            }
        }
        
        return previousPositions;
    }
    
    /// Returns the layout of the laboratory floor.
    ///
    /// @param inputLines the puzzle input.
    /// @return the layout of the laboratory floor.
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
    
    /// This enum stores the different directions that the starting position of the guard can be in.
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
        
        /// Returns the direction the provided symbol represents.
        ///
        /// @param symbol the symbol used to indicate the starting direction of the guard.
        /// @return the direction the symbol represents.
        static Optional<Direction> getDirection(char symbol)
        {
            for (Direction direction : Direction.values())
            {
                if (direction.getSymbol() == symbol)
                {
                    return Optional.of(direction);
                }
            }
            
            return Optional.empty();
        }
        
        /// Returns the direction after taking a 90-degree turn from the given direction.
        ///
        /// @param direction a direction.
        /// @return the direction after making a 90-degree turn to the right.
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
    
    /// This class stores the indices for a position. Equals and HashCode methods are overridden so positions
    /// can be compared by their indices.
    static class Position
    {
        int rowIndex;
        int columnIndex;
        
        public Position(int rowIndex, int columnIndex)
        {
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
        }
        
        @Override
        public boolean equals(Object o)
        {
            if (o == null || getClass() != o.getClass()) { return false; }
            Position position = (Position) o;
            return rowIndex == position.rowIndex && columnIndex == position.columnIndex;
        }
        
        @Override
        public int hashCode()
        {
            return Objects.hash(rowIndex, columnIndex);
        }
    }
    
    /// This record stores the initial state of the guard.
    record State(Position position, Direction direction) {}
    
    /// Returns the starting state of the guard.
    ///
    /// @param map the layout of the laboratory floor.
    /// @return the starting state of the guard.
    private static State getStartingState(char[][] map)
    {
        for (int rowIndex = 0; rowIndex < map.length; rowIndex++)
        {
            for (int columnIndex = 0; columnIndex < map[rowIndex].length; columnIndex++)
            {
                char currentSymbol = map[rowIndex][columnIndex];
                
                Optional<Direction> direction = Direction.getDirection(currentSymbol);
                
                if (direction.isPresent())
                {
                    Position position = new Position(rowIndex, columnIndex);
                    return new State(position, direction.get());
                }
            }
        }
        
        throw new RuntimeException("No starting position found");
    }
    
    /// Moves the position one step forward in the given direction.
    ///
    /// @param position the current position.
    /// @param direction the current direction.
    private static void moveForward(Position position, Direction direction)
    {
        switch (direction)
        {
            case UP -> position.rowIndex--;
            case RIGHT -> position.columnIndex++;
            case DOWN -> position.rowIndex++;
            case LEFT -> position.columnIndex--;
        }
    }
    
    /// Determines if the position is within the layout boundaries.
    ///
    /// @param map the layout of the laboratory floor.
    /// @param position a position.
    /// @return true if the position is within the bounds of the layout, or false otherwise.
    private static boolean outOfArea(char[][] map, Position position)
    {
        return position.rowIndex < 0 ||
                position.rowIndex >= map.length ||
                position.columnIndex < 0 ||
                position.columnIndex >= map[position.rowIndex].length;
    }
    
    /// Moves the position one step backward in the given direction.
    ///
    /// @param position the current position.
    /// @param direction the current direction.
    private static void moveBackward(Position position, Direction direction)
    {
        switch (direction)
        {
            case UP -> position.rowIndex++;
            case RIGHT -> position.columnIndex--;
            case DOWN -> position.rowIndex--;
            case LEFT -> position.columnIndex++;
        }
    }
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        return null;
    }
}
