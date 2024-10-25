package advent_of_code.year2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * --- Day 1: The Tyranny of the Rocket Equation ---
 */
public class Day01
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
        partTwo(inputLines);
    }
    
    
    private static void partOne(List<String> inputLines)
    {
        int totalFuelRequired = 0;
        
        for (String line : inputLines)
        {
            int mass = Integer.parseInt(line);
            
            int fuelRequired = mass / 3 - 2;
            
            totalFuelRequired += fuelRequired;
        }
        
        System.out.println("The sum of all fuel required for the modules in the spacecraft is: " + totalFuelRequired);
    }
    
    
    private static void partTwo(List<String> inputLines)
    {
        int totalFuelRequired = 0;
        
        for (String line : inputLines)
        {
            int mass = Integer.parseInt(line);
            
            int fuelRequired = mass / 3 - 2;
            fuelRequired = calculateFuelRequired(fuelRequired);
            
            totalFuelRequired += fuelRequired;
        }
        
        System.out.println("When taking into account the mass of the added fuel the sum is: " + totalFuelRequired);
    }
    
    
    private static int calculateFuelRequired(int fuelRequired)
    {
        if (fuelRequired < 0)
        {
            return 0;
        }
        else
        {
            return fuelRequired + calculateFuelRequired(fuelRequired / 3 - 2);
        }
    }
}
