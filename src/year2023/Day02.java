package year2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * --- Day 2: Cube Conundrum ---
 */
public class Day02
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
        partTwo(inputLines);
    }
    
    
    private static void partOne(List<String> inputLines)
    {
        int sumPossibleGameIDs = 0;
        int currentGameID = 1;
        
        for (String line : inputLines)
        {
            HashMap<String, Integer> cubeCounts = initializeCubeCounts();
            
            findMaximumCubeCounts(line, cubeCounts);
            
            boolean possibleGame = cubeCounts.get("red") <= 12
                    && cubeCounts.get("green") <= 13
                    && cubeCounts.get("blue") <= 14;
            
            if (possibleGame)
            {
                sumPossibleGameIDs += currentGameID;
            }

            currentGameID++;
        }
        
        System.out.println("The sum of the IDs of the possible games is: " + sumPossibleGameIDs);
    }
    
    
    private static void partTwo(List<String> inputLines)
    {
        int totalPower = 0;
        
        for (String line : inputLines)
        {
            HashMap<String, Integer> cubeCounts = initializeCubeCounts();
            
            findMaximumCubeCounts(line, cubeCounts);
            
            totalPower += cubeCounts.get("red") * cubeCounts.get("green") * cubeCounts.get("blue");
        }
        
        System.out.println("The total power of the minimum sets of cubes that must have been present is: " + totalPower);
    }
    
    
    private static void findMaximumCubeCounts(String line, HashMap<String, Integer> cubes)
    {
        List<String> set = Arrays.stream(line.split("[:;]"))
                .skip(1)
                .toList();
        
        for (String subset : set)
        {
            List<String> cubeCountValuePairs = List.of(subset.split("[,]"));
            
            for (String pair : cubeCountValuePairs)
            {
                Scanner cubeScan = new Scanner(pair);

                int cubeCount = cubeScan.nextInt();
                String cubeColour = cubeScan.next();

                if (cubes.get(cubeColour) < cubeCount)
                {
                    cubes.replace(cubeColour, cubeCount);
                }

                cubeScan.close();
            }
        }
    }
    
    
    private static HashMap<String, Integer> initializeCubeCounts()
    {
        HashMap<String, Integer> cubeCounts = new HashMap<>();
        
        cubeCounts.put("red", 0);
        cubeCounts.put("green", 0);
        cubeCounts.put("blue", 0);
        
        return cubeCounts;
    }
}