package aoc.event.year2021;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 implements Solver {
    
    /// Defines the size of the measurement windows.
    private static final int WINDOW_SIZE = 3;
    
    /// Stores the depth measurements.
    private final List<Integer> depths;
    
    /// Initializes the solution with the puzzle input.
    public Day01(String input) {
        this.depths = Collections.unmodifiableList(parse(input));
    }
    
    /// Parses the puzzle input for a list of depth measurements.
    ///
    /// The puzzle input is in the form:
    ///
    /// ```
    /// #
    /// #
    /// #
    /// ```
    ///
    /// Where each `#` is a number.
    ///
    /// @param input the puzzle input.
    /// @return a list of the depths measurements.
    private static List<Integer> parse(String input) {
        final var depths = new ArrayList<Integer>();
        
        input.lines().forEach(line -> depths.add(Integer.valueOf(line)));
        
        return depths;
    }
    
    /// Calculates the amount of numbers that are larger than the previous in the list.
    ///
    /// @return the total numbers that are larger than their predecessor in the list.
    @Override
    public Integer partOne() {
        var increases = 0;
        var previousDepth = depths.getFirst();
        
        for (final var currentDepth : depths.subList(1, depths.size())) {
            if (previousDepth < currentDepth) {
                increases++;
            }
            
            previousDepth = currentDepth;
        }
        
        return increases;
    }
    
    /// Calculates the number of measurement windows with a larger sum than the previous.
    ///
    /// @return the number of measurement windows with a larger sum than the previous.
    @Override
    public Integer partTwo() {
        var increases = 0;
        var previousSum = depths.get(0) + depths.get(1) + depths.get(2);
        
        for (var i = 0; i < depths.size() - WINDOW_SIZE; i++) {
            final var previousMeasurement = depths.get(i);
            final var nextMeasurement = depths.get(i + WINDOW_SIZE);
            
            final var currentSum = previousSum - previousMeasurement + nextMeasurement;
            
            if (previousSum < currentSum) {
                increases++;
            }
            
            previousSum = currentSum;
        }
        
        return increases;
    }
    
    /// Runs the solution.
    public static void main(String[] args) {
        Runner.runAndPrint(2021, 1);
    }
}
