package aoc.util;

public class Parse {
    
    public static char[][] toCharGrid(String input) {
        var lines = input.lines().toList();
        
        var rowSize = lines.size();
        var columnSize = lines.getFirst().length();
        
        var grid = new char[rowSize][columnSize];
        for (int row = 0; row < rowSize; row++) {
            for (int column = 0; column < columnSize; column++) {
                grid[row][column] = lines.get(row).charAt(column);
            }
        }
        return grid;
    }
}
