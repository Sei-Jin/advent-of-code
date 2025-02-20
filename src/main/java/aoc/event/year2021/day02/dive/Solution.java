package aoc.event.year2021.day02.dive;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Solution implements Solver {
    
    /// Pre-compiles the expected input format for a command.
    private static final Pattern COMMAND_PATTERN = Pattern.compile("^(\\w+)\\s+(\\d+)$");
    
    /// Stores the list of commands.
    private final List<Command> commands;
    
    /// Initializes the solution.
    ///
    /// @param input the puzzle input.
    public Solution(String input) {
        commands = Collections.unmodifiableList(parse(input));
    }
    
    /// Parses the input for the list of commands.
    ///
    /// The expected format for each line is `direction #`, where:
    /// - `direction` is the direction as a string.
    /// - `#` is a number representing the units.
    ///
    /// @param input the puzzle input.
    /// @return the list of commands.
    private List<Command> parse(String input) {
        final var commands = new ArrayList<Command>();
        
        input.lines().forEach(line -> {
            final var matcher = COMMAND_PATTERN.matcher(line);
            
            if (matcher.find()) {
                final var direction = Direction.valueOf(matcher.group(1).toUpperCase());
                final var units = Integer.parseInt(matcher.group(2));
                
                commands.add(new Command(direction, units));
            } else {
                throw new IllegalArgumentException("Invalid input for line: " + line);
            }
        });
        
        return commands;
    }
    
    /// Calculates the product of final horizontal position and depth after the commands have been
    /// executed.
    ///
    /// @return the product of the final horizontal position and the final depth.
    @Override
    public Object partOne() {
        int horizontalPosition = 0;
        int depth = 0;
        
        for (final var command : commands) {
            switch (command.direction) {
                case FORWARD -> horizontalPosition += command.units;
                case DOWN -> depth += command.units;
                case UP -> depth -= command.units;
            }
        }
        
        return horizontalPosition * depth;
    }
    
    
    /// Calculates the product of the final horizontal position and depth after the commands have
    /// been executed.
    ///
    /// The `down` and `up` directions now change the aim instead of directly changing the depth.
    /// Note that since these are the commands for a submarine, `down` increases the aim and `up`
    /// decreases the aim.
    ///
    /// @return the product of the final horizontal position and depth.
    @Override
    public Object partTwo() {
        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;
        
        for (final var command : commands) {
            switch (command.direction) {
                case FORWARD -> {
                    horizontalPosition += command.units;
                    depth += aim * command.units;
                }
                case DOWN -> aim += command.units;
                case UP -> aim -= command.units;
            }
        }
        
        return horizontalPosition * depth;
    }
    
    /// Stores the data for a command.
    private record Command(Direction direction, int units) {}
    
    /// Defines the possible directions.
    private enum Direction {
        DOWN, FORWARD, UP
    }
    
    /// Runs the solution.
    public static void main(String[] args) {
        Runner.runAndPrint(2021, 2);
    }
}
