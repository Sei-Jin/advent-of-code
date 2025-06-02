package aoc.event.year2019;

import aoc.Solver;

import java.util.List;

/// # [2019-01: The Tyranny of the Rocket Equation](https://adventofcode.com/2019/day/1)
public class Day01 implements Solver<Integer, Integer> {
    
    private final List<Integer> moduleMasses;
    
    public Day01(String input) {
        moduleMasses = parse(input);
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
        return moduleMasses
            .stream()
            .mapToInt(Day01::calculateFuelMass)
            .sum();
    }
    
    /// Calculates the sum of the fuel required when also taking into consideration the mass of the
    /// added fuel itself.
    @Override
    public Integer partTwo() {
        return moduleMasses
            .stream()
            .mapToInt(Day01::calculateFuelMassRecursively)
            .sum();
    }
    
    private static int calculateFuelMassRecursively(int mass) {
        var fuelMass = calculateFuelMass(mass);
        if (fuelMass <= 0) {
            return 0;
        }
        else {
            return fuelMass + calculateFuelMassRecursively(fuelMass);
        }
    }
}
