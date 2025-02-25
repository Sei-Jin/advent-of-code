package aoc.event.year2019.day01.theTyrannyOfTheRocketEquation;

import aoc.Solver;

import java.util.Collections;
import java.util.List;

public class Solution implements Solver {
    
    private final List<Integer> numbers;
    
    public Solution(String input) {
        numbers = Collections.unmodifiableList(parse(input));
    }
    
    private static List<Integer> parse(String input) {
        return input.lines()
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }
    
    /// @return the sum of all fuel required for the modules in the spacecraft.
    @Override
    public Integer partOne() {
        var totalFuelRequired = 0;
        
        for (final var mass : numbers) {
            final var fuelRequired = mass / 3 - 2;
            totalFuelRequired += fuelRequired;
        }
        
        return totalFuelRequired;
    }
    
    
    /// @return the sum of all fuel required for the modules in the spacecraft when also taking into
    /// consideration the mass of the added fuel itself.
    @Override
    public Integer partTwo() {
        var totalFuelRequired = 0;
        
        for (final var mass : numbers) {
            int fuelRequired = mass / 3 - 2;
            fuelRequired = calculateFuelRequired(fuelRequired);
            totalFuelRequired += fuelRequired;
        }
        
        return totalFuelRequired;
    }
    
    private static int calculateFuelRequired(int fuelRequired) {
        if (fuelRequired < 0) {
            return 0;
        } else {
            return fuelRequired + calculateFuelRequired(fuelRequired / 3 - 2);
        }
    }
}
