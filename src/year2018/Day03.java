package year2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day03 {
    
    public static void main(String[] args) throws IOException {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
        partTwo(inputLines);
    }
    
    
    private static void partOne(List<String> inputLines) {
        int[][] fabricArea = mapFabricClaims(inputLines);
        
        int withinTwoClaims = 0;
        
        for (int row = 0; row < fabricArea.length; row++) {
            for (int column = 0; column < fabricArea.length; column++) {
                if (fabricArea[row][column] > 1) {
                    withinTwoClaims++;
                }
            }
        }
        
        System.out.println("The square inches of fabric within two claims is: " + withinTwoClaims);
    }
    
    
    private static int[][] mapFabricClaims(List<String> inputLines) {
        int[][] fabricArea = new int[1000][1000];
        
        for (String line : inputLines) {
            Claim claim = getClaim(line);
            
            for (int row = claim.distanceTop(); row < claim.distanceTop() + claim.height(); row++) {
                for (int column = claim.distanceLeft(); column < claim.distanceLeft() + claim.width(); column++) {
                    fabricArea[row][column]++;
                }
            }
        }
        
        return fabricArea;
    }
    
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
    
    
    private record Claim(int claimID, int distanceLeft, int distanceTop, int width, int height) { }
    
    
    private static void partTwo(List<String> inputLines) {
        int[][] fabricArea = mapFabricClaims(inputLines);
        
        int nonOverlappingClaimID = 0;
        
        for (String line : inputLines) {
            boolean nonOverlappingClaim = true;
            
            Claim claim = getClaim(line);
            
            for (int row = claim.distanceTop; row < claim.distanceTop + claim.height; row++) {
                for (int column = claim.distanceLeft; column < claim.distanceLeft + claim.width; column++) {
                    if (fabricArea[row][column] > 1) {
                        nonOverlappingClaim = false;
                        break;
                    }
                }
            }
            
            if (nonOverlappingClaim) {
                nonOverlappingClaimID = claim.claimID;
                break;
            }
        }
        
        System.out.println("The ID of the only claim that does not overlap is: " + nonOverlappingClaimID);
    }
}
