package advent_of_code.year_2018.__03__no_matter_how_you_slice_it;

import advent_of_code.PuzzleSolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * --- Day 3: No Matter How You Slice It ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the square inches of fabric within two claims.
     */
    @Override
    public Object partOne(List<String> inputLines)
    {
        List<Claim> claims = new ArrayList<>();
        
        for (String line : inputLines)
        {
            Claim claim = getClaim(line);
            claims.add(claim);
        }
        
        int[][] mappedFabricArea = mapFabricClaims(claims);
        
        int areaWithinTwoClaims = 0;
        
        for (int row = 0; row < mappedFabricArea.length; row++)
        {
            for (int column = 0; column < mappedFabricArea.length; column++)
            {
                if (mappedFabricArea[row][column] > 1)
                {
                    areaWithinTwoClaims++;
                }
            }
        }
        
        return areaWithinTwoClaims;
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the ID of the only claim that does not overlap.
     */
    @Override
    public Object partTwo(List<String> inputLines)
    {
        List<Claim> claims = new ArrayList<>();
        
        for (String line : inputLines)
        {
            Claim claim = getClaim(line);
            claims.add(claim);
        }
        
        int[][] mappedFabricArea = mapFabricClaims(claims);
        
        int nonOverlappingClaimID = 0;
        
        for (Claim claim : claims)
        {
            boolean nonOverlappingClaim = isNonOverlappingClaim(mappedFabricArea, claim);
            
            if (nonOverlappingClaim)
            {
                nonOverlappingClaimID = claim.claimID();
                break;
            }
        }
        
        return nonOverlappingClaimID;
    }
    
    
    private static boolean isNonOverlappingClaim(int[][] mappedFabricArea, Claim claim)
    {
        boolean nonOverlappingClaim = true;
        
        for (int row = claim.distanceTop(); row < claim.distanceTop() + claim.height(); row++)
        {
            for (int column = claim.distanceLeft(); column < claim.distanceLeft() + claim.width(); column++)
            {
                if (mappedFabricArea[row][column] > 1)
                {
                    nonOverlappingClaim = false;
                    break;
                }
            }
        }
        
        return nonOverlappingClaim;
    }
    
    
    private static Claim getClaim(String line)
    {
        int[] lineValues = Arrays.stream(line.split("[^0-9]+")).skip(1).mapToInt(Integer::parseInt).toArray();
        
        int claimID = lineValues[0];
        int distanceLeft = lineValues[1];
        int distanceTop = lineValues[2];
        int width = lineValues[3];
        int height = lineValues[4];
        
        return new Claim(claimID, distanceLeft, distanceTop, width, height);
    }
    
    
    private static int[][] mapFabricClaims(List<Claim> claims)
    {
        int[][] fabricArea = new int[1000][1000];
        
        for (Claim claim : claims)
        {
            for (int row = claim.distanceTop(); row < claim.distanceTop() + claim.height(); row++)
            {
                for (int column = claim.distanceLeft(); column < claim.distanceLeft() + claim.width(); column++)
                {
                    fabricArea[row][column]++;
                }
            }
        }
        
        return fabricArea;
    }
    
    
    private record Claim(int claimID, int distanceLeft, int distanceTop, int width, int height) {}
}
