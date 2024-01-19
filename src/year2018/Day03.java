package year2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * --- Day 3: No Matter How You Slice It ---
 */
public class Day03 {
    
    public static void main(String[] args) throws IOException {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        List<Claim> claims = new ArrayList<>();
        
        for (String line : inputLines) {
            Claim claim = getClaim(line);
            claims.add(claim);
        }
        
        int[][] fabricArea = mapFabricClaims(claims);
        
        partOne(fabricArea);
        partTwo(fabricArea, claims);
    }
    
    
    private record Claim(int claimID, int distanceLeft, int distanceTop, int width, int height) { }
    
    
    private static Claim getClaim(String line) {
        int[] lineValues = Arrays.stream(line.split("[^0-9]+"))
                .skip(1)
                .mapToInt(Integer::parseInt)
                .toArray();
        
        int claimID = lineValues[0];
        int distanceLeft = lineValues[1];
        int distanceTop = lineValues[2];
        int width = lineValues[3];
        int height = lineValues[4];
        
        return new Claim(claimID, distanceLeft, distanceTop, width, height);
    }
    
    
    private static int[][] mapFabricClaims(List<Claim> claims) {
        int[][] fabricArea = new int[1000][1000];
        
        for (Claim claim : claims) {
            for (int row = claim.distanceTop(); row < claim.distanceTop() + claim.height(); row++) {
                for (int column = claim.distanceLeft(); column < claim.distanceLeft() + claim.width(); column++) {
                    fabricArea[row][column]++;
                }
            }
        }
        
        return fabricArea;
    }
    
    
    private static void partOne(int[][] fabricArea) {
        int areaWithinTwoClaims = 0;
        
        for (int row = 0; row < fabricArea.length; row++) {
            for (int column = 0; column < fabricArea.length; column++) {
                if (fabricArea[row][column] > 1) {
                    areaWithinTwoClaims++;
                }
            }
        }
        
        System.out.println("The square inches of fabric within two claims is: " + areaWithinTwoClaims);
    }
    
    
    private static void partTwo(int[][] fabricArea, List<Claim> claims) {
        int nonOverlappingClaimID = 0;
        
        for (Claim claim : claims) {
            boolean nonOverlappingClaim = true;
            
            for (int row = claim.distanceTop(); row < claim.distanceTop() + claim.height(); row++) {
                for (int column = claim.distanceLeft(); column < claim.distanceLeft() + claim.width(); column++) {
                    if (fabricArea[row][column] > 1) {
                        nonOverlappingClaim = false;
                        break;
                    }
                }
            }
            
            if (nonOverlappingClaim) {
                nonOverlappingClaimID = claim.claimID();
                break;
            }
        }
        
        System.out.println("The ID of the only claim that does not overlap is: " + nonOverlappingClaimID);
    }
}
