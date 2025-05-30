package aoc.event.year2023;

import aoc.Solver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/// # [2023-02: Cube Conundrum](https://adventofcode.com/2023/day/2)
public class Day02 implements Solver<Integer, Integer> {
    
    private final List<Game> games;
    
    public Day02(String input) {
        games = parse(input);
    }
    
    private static List<Game> parse(String input) {
        return input
            .lines()
            .map(line -> {
                var parts = line.split(":");
                
                var id = Integer.parseInt(parts[0].split(" ")[1]);
                var sets = parts[1].split(";");
                
                var maxCounts = calculateMaxCounts(sets);
                return new Game(id, maxCounts);
            })
            .toList();
    }
    
    private static Map<Colour, Integer> calculateMaxCounts(String[] sets) {
        var maxCounts = new HashMap<Colour, Integer>();
        for (var set : sets) {
            var pairs = set.split(",");
            for (var pair : pairs) {
                var values = pair
                    .stripLeading()
                    .split(" ");
                
                var count = Integer.parseInt(values[0]);
                var colour = Colour.of(values[1]);
                
                if (maxCounts.getOrDefault(colour, 0) < count) {
                    maxCounts.put(colour, count);
                }
            }
        }
        return maxCounts;
    }
    
    /// Calculates the sum of the ids for the possible games.
    @Override
    public Integer partOne() {
        return games
            .stream()
            .filter(game -> {
                var maxCounts = game.maxCounts();
                return maxCounts.get(Colour.RED) <= 12
                    && maxCounts.get(Colour.GREEN) <= 13
                    && maxCounts.get(Colour.BLUE) <= 14;
            })
            .mapToInt(Game::id)
            .sum();
    }
    
    /// Calculates the sum of the products of the max counts for all games.
    @Override
    public Integer partTwo() {
        return games
            .stream()
            .map(Game::maxCounts)
            .mapToInt(maxCounts ->
                maxCounts.get(Colour.RED) * maxCounts.get(Colour.GREEN) * maxCounts.get(Colour.BLUE)
            )
            .sum();
    }
    
    private enum Colour {
        RED, GREEN, BLUE;
        
        public static Colour of(String string) {
            return switch (string) {
                case "red" -> RED;
                case "green" -> GREEN;
                case "blue" -> BLUE;
                default -> throw new IllegalArgumentException();
            };
        }
    }
    
    private record Game(int id, Map<Colour, Integer> maxCounts) {}
}