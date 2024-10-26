package advent_of_code.year_2019.__01__the_tyranny_of_the_rocket_equation;

import advent_of_code.PuzzleSolver;

import java.util.List;

/**
 * --- Day 1: The Tyranny of the Rocket Equation ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the sum of all fuel required for the modules in the spacecraft.
     */
    @Override
    public Object partOne(List<String> inputLines)
    {
        int totalFuelRequired = 0;
        
        for (String line : inputLines)
        {
            int mass = Integer.parseInt(line);
            int fuelRequired = mass / 3 - 2;
            totalFuelRequired += fuelRequired;
        }
        
        return totalFuelRequired;
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the sum of all fuel required for the modules in the spacecraft when also taking into consideration
     * the mass of the added fuel itself.
     */
    @Override
    public Object partTwo(List<String> inputLines)
    {
        int totalFuelRequired = 0;
        
        for (String line : inputLines)
        {
            int mass = Integer.parseInt(line);
            
            int fuelRequired = mass / 3 - 2;
            fuelRequired = calculateFuelRequired(fuelRequired);
            
            totalFuelRequired += fuelRequired;
        }
        
        return totalFuelRequired;
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
