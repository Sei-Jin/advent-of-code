package aoc.event.year2024;

import aoc.Solver;
import aoc.util.Parse;

import java.util.Arrays;

/// # [2024-04: Ceres Search](https://adventofcode.com/2024/day/4)
public class Day04 implements Solver<Integer, Integer> {
    
    private static char[][] wordSearch;
    private static final String TARGET = "XMAS";
    private static final Direction[] possibleDirections = {
        new Direction(1, 1),
        new Direction(0, 1),
        new Direction(-1, 1),
        new Direction(-1, 0),
        new Direction(-1, -1),
        new Direction(0, -1),
        new Direction(1, -1),
        new Direction(1, 0),
    };
    
    public Day04(String input) {
        wordSearch = Parse.toCharGrid(input);
    }
    
    /// Counts the number of strings in the word search that match the target string.
    ///
    /// The target string can be horizontal, vertical or diagonal in the word search.
    @Override
    public Integer partOne() {
        int sum = 0;
        for (int row = 0; row < wordSearch.length; row++) {
            for (int column = 0; column < wordSearch[row].length; column++) {
                var position = new Position(row, column);
                sum += countMatchingInAllDirections(position, wordSearch);
            }
        }
        return sum;
    }
    
    /// Calculates the number of matching strings in all 8 directions from the current position.
    private static int countMatchingInAllDirections(Position position, char[][] wordSearch) {
        return (int) Arrays
            .stream(possibleDirections)
            .map(direction -> new Search(position, direction))
            .filter(search -> isValidDirection(search, wordSearch))
            .filter(search -> isMatching(search, wordSearch))
            .count();
    }
    
    /// The current direction is valid if the word will stay within the bounds of the word search.
    private static boolean isValidDirection(Search search, char[][] wordSearch) {
        var position = search.position();
        var direction = search.direction();
        
        int endRow = position.row() + direction.row() * (TARGET.length() - 1);
        int endColumn = position.column() + direction.column() * (TARGET.length() - 1);
        
        return (endRow >= 0)
            && (endRow < wordSearch.length)
            && (endColumn >= 0)
            && (endColumn < wordSearch[position.row()].length);
    }
    
    /// Determines if the word matches the target string.
    private static boolean isMatching(Search search, char[][] wordSearch) {
        var position = search.position();
        var direction = search.direction();
        
        for (int i = 0; i < TARGET.length(); i++) {
            int newRow = position.row() + i * direction.row();
            int newColumn = position.column() + i * direction.column();
            
            if (wordSearch[newRow][newColumn] != TARGET.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public Integer partTwo() {
        return 0;
    }
    
    private record Position(int row, int column) {}
    
    private record Direction(int row, int column) {}
    
    private record Search(Position position, Direction direction) {}
}
