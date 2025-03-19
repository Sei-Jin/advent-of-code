package aoc.event.year2018;

import aoc.Solver;

import java.util.List;
import java.util.regex.Pattern;

public class Day03 implements Solver<Integer> {
    
    private static final Pattern CLAIM_PATTERN = Pattern.compile(
        "#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)"
    );
    
    private static List<Claim> claims;
    private static int[][] claimCounts;
    
    public Day03(String input) {
        claims = parseClaims(input);
        claimCounts = countClaims(claims);
    }
    
    /// Parses the puzzle input to create a list of claims.
    ///
    /// Each line of the puzzle input is in the form: `#8 @ 3,7: 3x6`
    ///
    /// - `#8` refers to the id of the claim.
    /// - `@ 3,7` refers to the coordinates of the top left corner of the claim. This can also be
    /// thought of as the offset from the left and top sides of the claim area.
    /// - `3x6` refers to the size of the claim, where `3` is the columns and `6` is the rows.
    ///
    /// @param input the puzzle input.
    /// @return a list of claims.
    private static List<Claim> parseClaims(String input) {
        return input
            .lines()
            .map(line -> {
                final var matcher = CLAIM_PATTERN.matcher(line);
                
                if (matcher.find()) {
                    final var claimId = Integer.parseInt(matcher.group(1));
                    final var columnOffset = Integer.parseInt(matcher.group(2));
                    final var rowOffset = Integer.parseInt(matcher.group(3));
                    final var columns = Integer.parseInt(matcher.group(4));
                    final var rows = Integer.parseInt(matcher.group(5));
                    
                    return new Claim(claimId, columnOffset, rowOffset, columns, rows);
                } else {
                    throw new IllegalArgumentException("Error: Invalid input line: " + line);
                }
            })
            .toList();
    }

    private static int[][] countClaims(List<Claim> claims) {
        final var claimArea = new int[1000][1000];
        
        for (final var claim : claims) {
            final var maxRow = claim.rowOffset + claim.rows;
            final var maxColumn = claim.columnOffset + claim.columns;
            
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
        return claims
            .stream()
            .filter(claim -> !isOverlappingClaim(claim))
            .findAny()
            .orElseThrow(() ->
                new IllegalStateException("There were no claims that did not overlap.")
            )
            .claimId;
    }
    
    private static boolean isOverlappingClaim(Claim claim) {
        final var maxRow = claim.rowOffset + claim.rows;
        final var maxColumn = claim.columnOffset + claim.columns;
        
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
