package aoc.event.year2021;

import aoc.Solver;
import aoc.util.Parse;

/// # [2021-03: Binary Diagnostic](https://adventofcode.com/2021/day/3)
public class Day03 implements Solver<Integer, Integer> {
    
    private final boolean[] isOneMostCommonInColumn;
    
    public Day03(String input) {
        var grid = Parse.toBooleanGrid(input);
        isOneMostCommonInColumn = isOneMostCommonInColumn(grid);
    }
    
    private boolean[] isOneMostCommonInColumn(boolean[][] grid) {
        var mostCommon = new boolean[grid[0].length];
        for (int column = 0; column < grid[0].length; column++) {
            var trueCount = 0;
            for (var row : grid) {
                if (row[column]) {
                    trueCount++;
                }
            }
            if (trueCount > grid.length / 2) {
                mostCommon[column] = true;
            }
        }
        return mostCommon;
    }
    
    @Override
    public Integer partOne() {
        var binaryGammaRate = new StringBuilder();
        var binaryEpsilonRate = new StringBuilder();
        
        for (var oneIsMostCommon : isOneMostCommonInColumn) {
            if (oneIsMostCommon) {
                binaryGammaRate.append(1);
                binaryEpsilonRate.append(0);
            }
            else {
                binaryEpsilonRate.append(1);
                binaryGammaRate.append(0);
            }
        }
        int gammaRate = Integer.parseInt(String.valueOf(binaryGammaRate), 2);
        int epsilonRate = Integer.parseInt(String.valueOf(binaryEpsilonRate), 2);
        return gammaRate * epsilonRate;
    }
    
    @Override
    public Integer partTwo() {
        return 0;
    }
}
