package aoc.event.year2023;

import aoc.Solver;
import aoc.util.Parse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/// # [2023-03: Gear Ratios](https://adventofcode.com/2023/day/3)
public class Day03 implements Solver<Integer, Integer> {
    
    private static final char EMPTY_POSITION = '.';
    private final char[][] schematic;
    
    public Day03(String input) {
        schematic = Parse.toCharGrid(input);
    }
    
    @Override
    public Integer partOne() {
        var symbolPositions = findSymbols(schematic);
        var numberPositions = findAdjacentNumbers(schematic, symbolPositions);
        var partNumbers = parsePartNumbers(schematic, numberPositions);
        return partNumbers
            .stream()
            .reduce(Integer::sum)
            .orElse(0);
    }
    
    private static List<Position> findSymbols(char[][] schematic) {
        var symbols = new ArrayList<Position>();
        for (int row = 0; row < schematic.length; row++) {
            for (int column = 0; column < schematic[row].length; column++) {
                char character = schematic[row][column];
                boolean isSymbol = (character != EMPTY_POSITION && !Character.isDigit(character));
                if (isSymbol) {
                    symbols.add(new Position(row, column));
                }
            }
        }
        return symbols;
    }
    
    private static Set<Position> findAdjacentNumbers(char[][] schematic, List<Position> symbols) {
        var numbers = new HashSet<Position>();
        for (var symbol : symbols) {
            int rowStart = Math.max(symbol.row() - 1, 0);
            int rowEnd = Math.min(symbol.row() + 1, schematic.length - 1);
            int columnStart = Math.max(symbol.column() - 1, 0);
            int columnEnd = Math.min(symbol.column() + 1, schematic[symbol.row()].length - 1);
            
            for (int row = rowStart; row <= rowEnd; row++) {
                for (int column = columnStart; column <= columnEnd; column++) {
                    if (Character.isDigit(schematic[row][column])) {
                        numbers.add(new Position(row, column));
                    }
                }
            }
        }
        return numbers;
    }
    
    private static List<Integer> parsePartNumbers(
        char[][] schematic,
        Set<Position> numberPositions
    ) {
        var numbers = new ArrayList<Integer>();
        var seen = new HashSet<Position>();
        
        for (var position : numberPositions) {
            if (seen.contains(position)) {
                continue;
            }
            var partNumber = parsePartNumber(schematic, position, seen);
            numbers.add(Integer.valueOf(partNumber));
        }
        return numbers;
    }
    
    private static String parsePartNumber(
        char[][] engineSchematic,
        Position part,
        Set<Position> seen
    ) {
        var number = new StringBuilder();
        var row = part.row;
        int column = part.column() - 1;
        
        while (column >= 0 && Character.isDigit(engineSchematic[row][column])) {
            column--;
        }
        column++;
        while (column < engineSchematic[row].length &&
            Character.isDigit(engineSchematic[row][column])
        ) {
            number.append(engineSchematic[row][column]);
            seen.add(new Position(row, column));
            column++;
        }
        return number.toString();
    }
    
    @Override
    public Integer partTwo() {
        return 0;
    }
    
    private record Position(int row, int column) {}
}
