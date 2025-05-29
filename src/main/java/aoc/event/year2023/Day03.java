package aoc.event.year2023;

import aoc.DeprecatedSolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/// # [2023-03: Gear Ratios](https://adventofcode.com/2023/day/3)
public class Day03 implements DeprecatedSolver
{
    
    private static final char EMPTY_POSITION = '.';
    
    @Override
    public Object partOne(List<String> engineSchematic)
    {
        List<Position> symbolPositions = getSymbolPositions(engineSchematic);
        HashSet<Position> partNumberPositions = getPartNumberPositions(engineSchematic, symbolPositions);
        List<Integer> partNumbers = getPartNumbers(engineSchematic, partNumberPositions);
        
        return partNumbers.stream()
                .reduce(Integer::sum)
                .orElse(0);
    }
    
    private record Position(int row, int column) {}
    
    private List<Position> getSymbolPositions(List<String> engineSchematic)
    {
        List<Position> symbolPositions = new ArrayList<>();
        
        for (int row = 0; row < engineSchematic.size(); row++)
        {
            String schematicRow = engineSchematic.get(row);
            
            for (int column = 0; column < schematicRow.length(); column++)
            {
                char character = schematicRow.charAt(column);
                boolean symbolCharacter = (character != EMPTY_POSITION && !Character.isDigit(character));
                
                if (symbolCharacter)
                {
                    symbolPositions.add(new Position(row, column));
                }
            }
        }
        
        return symbolPositions;
    }
    
    private static HashSet<Position> getPartNumberPositions(List<String> engineSchematic, List<Position> symbolPositions)
    {
        HashSet<Position> partNumberPositions = new HashSet<>();
        
        for (Position symbolPosition : symbolPositions)
        {
            int rowStart = Math.max(symbolPosition.row() - 1, 0);
            int rowEnd = Math.min(symbolPosition.row() + 1, engineSchematic.size() - 1);
            
            int columnStart = Math.max(symbolPosition.column() - 1, 0);
            int columnEnd = Math.min(symbolPosition.column() + 1, engineSchematic.get(symbolPosition.row()).length() - 1);
            
            for (int row = rowStart; row <= rowEnd; row++)
            {
                for (int column = columnStart; column <= columnEnd; column++)
                {
                    char character = engineSchematic.get(row).charAt(column);
                    
                    if (Character.isDigit(character))
                    {
                        partNumberPositions.add(new Position(row, column));
                    }
                }
            }
        }
        
        return partNumberPositions;
    }
    
    private static List<Integer> getPartNumbers(List<String> engineSchematic, HashSet<Position> partNumberPositions)
    {
        List<Integer> partNumbers = new ArrayList<>();
        
        HashSet<String> addedPositions = new HashSet<>();
        
        for (Position partNumberPosition : partNumberPositions)
        {
            if (addedPositions.contains(partNumberPosition.toString()))
            {
                continue;
            }
            
            StringBuilder partNumber = getPartNumber(engineSchematic, partNumberPosition, addedPositions);
            partNumbers.add(Integer.valueOf(partNumber.toString()));
        }
        
        return partNumbers;
    }
    
    private static StringBuilder getPartNumber(List<String> engineSchematic, Position partNumberPosition, HashSet<String> addedPositions)
    {
        StringBuilder partNumber = new StringBuilder();
        
        int columnPointer = partNumberPosition.column - 1;
        
        while (columnPointer >= 0 &&
                Character.isDigit(engineSchematic.get(partNumberPosition.row()).charAt(columnPointer)))
        {
            columnPointer--;
        }
        
        columnPointer++;
        
        while (columnPointer < engineSchematic.get(partNumberPosition.row()).length() &&
                Character.isDigit(engineSchematic.get(partNumberPosition.row()).charAt(columnPointer)))
        {
            partNumber.append(engineSchematic.get(partNumberPosition.row()).charAt(columnPointer));
            addedPositions.add(new Position(partNumberPosition.row(), columnPointer).toString());
            columnPointer++;
        }
        
        return partNumber;
    }
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        return null;
    }
}
