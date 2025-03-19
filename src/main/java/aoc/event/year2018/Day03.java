package aoc.event.year2018;

import aoc.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Day03 implements Solver<Integer> {
    
    /// Pattern of the relevant claim information given in each line of the puzzle input.
    private static final Pattern CLAIM_PATTERN = Pattern.compile(
        "#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)"
    );
    
    private static List<Claim> claims;
    
    public Day03(String input) {
        claims = parseClaims(input.lines().toList());
        
    }
    
    /// Parses the puzzle input to create a list of claims.
    ///
    /// @param puzzleInput the puzzle input.
    /// @return a list of claims.
    private static List<Claim> parseClaims(List<String> puzzleInput) {
        final var claims = new ArrayList<Claim>();
        
        for (final var line : puzzleInput) {
            final var claim = parseClaim(line);
            claims.add(claim);
        }
        
        return claims;
    }
    
    /// Parses a line of the puzzle input for the claim data.
    ///
    /// Each line of the puzzle input is in the form: `#8 @ 3,7: 3x6`
    ///
    /// - `#8` refers to the id of the claim.
    /// - `@ 3,7` refers to the coordinates of the top left corner of the claim. This can also be
    /// thought of as the offset from the left and top sides of the claim area.
    /// - `3x6` refers to the size of the claim, where `3` is the columns and `6` is the rows.
    ///
    /// @param line a line of the puzzle input.
    /// @return a new claim storing the relevant parsed data.
    /// @throws IllegalArgumentException if the input line did not match the expected format.
    private static Claim parseClaim(String line) {
        final var matcher = CLAIM_PATTERN.matcher(line);
        
        if (matcher.find()) {
            final var claimId = Integer.parseInt(matcher.group(1));
            final var leftOffset = Integer.parseInt(matcher.group(2));
            final var topOffset = Integer.parseInt(matcher.group(3));
            final var width = Integer.parseInt(matcher.group(4));
            final var height = Integer.parseInt(matcher.group(5));
            
            return new Claim(claimId, leftOffset, topOffset, width, height);
        } else {
            throw new IllegalArgumentException("Error: Invalid input line: " + line);
        }
    }
    
    /// Counts the number of claims at each index.
    ///
    /// @param claims a list of claims.
    /// @return a 2D array of the number of claims at each index.
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
    ///
    /// @return the square inches of fabric within two or more claims.
    @Override
    public Integer partOne() {
        final var claimCounts = countClaims(claims);
        
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
    
    /// Finds the claim id of the only claim that does not overlap with any other claims
    ///
    /// @return the id of the only claim that does not overlap.
    /// @throws IllegalStateException if there were no claims that did not overlap.
    @Override
    public Integer partTwo() {
        final var claimCounts = countClaims(claims);
        
        return claims
            .stream()
            .filter(claim -> !isOverlappingClaim(claimCounts, claim))
            .findAny()
            .orElseThrow(() ->
                new IllegalStateException("There were no claims that did not overlap.")
            )
            .claimId;
    }
    
    /// Determines if the given claim overlaps with any other claim.
    ///
    /// @param claimCounts a 2D array of the claim counts at each index.
    /// @param claim a claim.
    /// @return true if the claim overlaps with any other claims, or false otherwise.
    private static boolean isOverlappingClaim(int[][] claimCounts, Claim claim) {
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
    
    /// Stores the information for a claim.
    ///
    /// @param claimId the id of the claim.
    /// @param columnOffset the offset from the start of the claim to the left side of the claim area.
    /// @param rowOffset the offset from the start of the claim to the top side of the claim area.
    /// @param columns the columns of the claim.
    /// @param rows the rows of the claim.
    private record Claim(int claimId, int columnOffset, int rowOffset, int columns, int rows) {}
}
