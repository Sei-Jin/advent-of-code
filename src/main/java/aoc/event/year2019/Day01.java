package aoc.event.year2019;

import aoc.Solver;

import java.util.Collections;
import java.util.List;

public class Day01 implements Solver {
    
    private final List<Integer> moduleMasses;
    
    /// Initializes the solution with the parsed puzzle input.
    ///
    /// The puzzle input contains one number per line.
    public Day01(String input) {
        moduleMasses = Collections.unmodifiableList(parse(input));
    }
    
    private static List<Integer> parse(String input) {
        return input.lines()
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }
    
    private static int calculateFuelMass(int mass) {
        return mass / 3 - 2;
    }
    
    /// Calculates the sum fuel masses required.
    @Override
    public Integer partOne() {
        var sum = 0;
        
        for (final var moduleMass : moduleMasses) {
            final var fuelMass = calculateFuelMass(moduleMass);
            sum += fuelMass;
        }
        
        return sum;
    }
    
    /// Calculates the sum of the fuel required when also taking into consideration the mass of the
    /// added fuel itself.
    @Override
    public Integer partTwo() {
        var sum = 0;
        
        for (final var moduleMass : moduleMasses) {
            sum += calculateFuelMassRecursively(moduleMass);
        }
        
        return sum;
    }
    
    private static int calculateFuelMassRecursively(int mass) {
        final var fuelMass = calculateFuelMass(mass);
        
        if (fuelMass <= 0) {
            return 0;
        } else {
            return fuelMass + calculateFuelMassRecursively(fuelMass);
        }
    }
}
