package aoc.event.year2018;

import aoc.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
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
        List<Claim> claims = new ArrayList<>();
        
        for (String line : puzzleInput) {
            Claim claim = parseClaim(line);
            claims.add(claim);
        }
        
        return claims;
    }
    
    /// Parses a line of the puzzle input for the claim data.
    ///
    /// Each line of the puzzle input is in the form:
    ///
    /// ```
    ///#8 @ 3,7: 3x6
    ///```
    ///
    /// - `#8` refers to the id of the claim.
    /// - `@ 3,7` refers to the coordinates of the top left corner of the claim. This can also be
    /// thought of as the offset from the left and top sides of the claim area.
    /// - `3x6` refers to the size of the claim, where `3` is the width and `6` is the height.
    ///
    /// @param line a line of the puzzle input.
    /// @return a new claim storing the relevant parsed data.
    /// @throws IllegalArgumentException if the input line did not match the expected format.
    private static Claim parseClaim(String line) {
        Matcher matcher = CLAIM_PATTERN.matcher(line);
        
        if (matcher.find()) {
            int claimId = Integer.parseInt(matcher.group(1));
            int leftOffset = Integer.parseInt(matcher.group(2));
            int topOffset = Integer.parseInt(matcher.group(3));
            int width = Integer.parseInt(matcher.group(4));
            int height = Integer.parseInt(matcher.group(5));
            
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
        int[][] claimArea = new int[1000][1000];
        
        for (Claim claim : claims) {
            int maxRowIndex = claim.topOffset + claim.height;
            int maxColumnIndex = claim.leftOffset + claim.width;
            
            for (int rowIndex = claim.topOffset; rowIndex < maxRowIndex; rowIndex++) {
                for (int columnIndex = claim.leftOffset; columnIndex < maxColumnIndex; columnIndex++) {
                    claimArea[rowIndex][columnIndex]++;
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
        int[][] claimCounts = countClaims(claims);
        
        int areaWithTwoOrMoreClaims = 0;
        
        for (int[] claimCount : claimCounts) {
            for (int columnIndex = 0; columnIndex < claimCounts.length; columnIndex++) {
                if (claimCount[columnIndex] > 1) {
                    areaWithTwoOrMoreClaims++;
                }
            }
        }
        
        return areaWithTwoOrMoreClaims;
    }
    
    /// Finds the claim id of the only claim that does not overlap with any other claims
    ///
    /// @return the id of the only claim that does not overlap.
    /// @throws IllegalStateException if there were no claims that did not overlap.
    @Override
    public Integer partTwo() {
        int[][] claimCounts = countClaims(claims);
        
        for (Claim claim : claims) {
            if (!isOverlappingClaim(claimCounts, claim)) {
                return claim.claimId;
            }
        }
        
        throw new IllegalStateException("Error: There were no claims that overlapped.");
    }
    
    /// Determines if the given claim overlaps with any other claim.
    ///
    /// @param claimCounts a 2D array of the claim counts at each index.
    /// @param claim       a claim.
    /// @return true if the claim overlaps with any other claims, or false otherwise.
    private static boolean isOverlappingClaim(int[][] claimCounts, Claim claim) {
        int maxRowIndex = claim.topOffset + claim.height;
        int maxColumnIndex = claim.leftOffset + claim.width;
        
        for (int rowIndex = claim.topOffset; rowIndex < maxRowIndex; rowIndex++) {
            for (int columnIndex = claim.leftOffset; columnIndex < maxColumnIndex; columnIndex++) {
                if (claimCounts[rowIndex][columnIndex] > 1) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /// Stores the information for a claim.
    ///
    /// @param claimId    the id of the claim.
    /// @param leftOffset the offset from the start of the claim to the left side of the
    ///                                             claim area.
    /// @param topOffset  the offset from the start of the claim to the top side of the claim
    ///                                             area.
    /// @param width      the width of the claim.
    /// @param height     the height of the claim.
    private record Claim(int claimId, int leftOffset, int topOffset, int width, int height) {}
}
