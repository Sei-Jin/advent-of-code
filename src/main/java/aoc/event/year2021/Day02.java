package aoc.event.year2021;

import aoc.Solver;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/// # [2021-02: Dive!](https://adventofcode.com/2021/day/2)
public class Day02 implements Solver<Integer, Integer> {
    
    private static final Pattern COMMAND_PATTERN = Pattern.compile("^(\\w+)\\s+(\\d+)$");
    private final List<Command> commands;
    
    public Day02(String input) {
        commands = parse(input);
    }
    
    private List<Command> parse(String input) {
        return input
            .lines()
            .map(COMMAND_PATTERN::matcher)
            .flatMap(Matcher::results)
            .map(result -> {
                var direction = Direction.valueOf(result.group(1).toUpperCase());
                var units = Integer.parseInt(result.group(2));
                return new Command(direction, units);
            })
            .toList();
    }
    
    /// Calculates the product of final horizontal position and depth after the commands have been
    /// executed.
    @Override
    public Integer partOne() {
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
    @Override
    public Integer partTwo() {
        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;
        
        for (var command : commands) {
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
    
    private record Command(Direction direction, int units) {}
    
    private enum Direction {DOWN, FORWARD, UP}
}
