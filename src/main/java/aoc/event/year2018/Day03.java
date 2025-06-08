package aoc.event.year2018;

import aoc.Solver;

import java.util.List;
import java.util.regex.Pattern;

/// # [2018-03: No Matter How You Slice It](https://adventofcode.com/2018/day/3)
public class Day03 implements Solver<Integer, Integer> {
    
    private static final Pattern CLAIM_PATTERN = Pattern.compile(
        "#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)"
    );
    private final List<Claim> claims;
    private final int[][] claimCounts;
    
    public Day03(String input) {
        claims = parse(input);
        claimCounts = count(claims);
    }
    
    private static List<Claim> parse(String input) {
        return input
            .lines()
            .map(line -> {
                var matcher = CLAIM_PATTERN.matcher(line);
                if (matcher.find()) {
                    var claimId = Integer.parseInt(matcher.group(1));
                    var columnOffset = Integer.parseInt(matcher.group(2));
                    var rowOffset = Integer.parseInt(matcher.group(3));
                    var columns = Integer.parseInt(matcher.group(4));
                    var rows = Integer.parseInt(matcher.group(5));
                    return new Claim(claimId, columnOffset, rowOffset, columns, rows);
                }
                else {
                    throw new IllegalArgumentException("Invalid input line: " + line);
                }
            })
            .toList();
    }
    
    private static int[][] count(List<Claim> claims) {
        var claimArea = new int[1000][1000];
        
        for (var claim : claims) {
            var maxRow = claim.rowOffset + claim.rows;
            var maxColumn = claim.columnOffset + claim.columns;
            
            for (var row = claim.rowOffset; row < maxRow; row++) {
                for (var column = claim.columnOffset; column < maxColumn; column++) {
                    claimArea[row][column]++;
                }
            }
        }
        return claimArea;
    }
    
    /// Calculates the square inches of fabric within two or more claims.
    @Override
    public Integer partOne() {
        var count = 0;
        
        for (int row = 0; row < claimCounts[0].length; row++) {
            for (var column = 0; column < claimCounts.length; column++) {
                if (claimCounts[row][column] > 1) {
                    count++;
                }
            }
        }
        return count;
    }
    
    /// Finds the claim id of the only claim that does not overlap with any other claims.
    @Override
    public Integer partTwo() {
        var nonOverlapping = claims
            .stream()
            .filter(claim -> !isOverlappingClaim(claim, claimCounts))
            .toList();
        if (nonOverlapping.size() > 1) {
            throw new IllegalStateException("There was more than one non-overlapping claim");
        }
        if (nonOverlapping.isEmpty()) {
            throw new IllegalStateException("There was no non-overlapping claims.");
        }
        return nonOverlapping.getFirst().claimId();
    }
    
    private static boolean isOverlappingClaim(Claim claim, int[][] claimCounts) {
        var maxRow = claim.rowOffset + claim.rows;
        var maxColumn = claim.columnOffset + claim.columns;
        
        for (var row = claim.rowOffset; row < maxRow; row++) {
            for (var column = claim.columnOffset; column < maxColumn; column++) {
                if (claimCounts[row][column] > 1) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private record Claim(int claimId, int columnOffset, int rowOffset, int columns, int rows) {}
}
