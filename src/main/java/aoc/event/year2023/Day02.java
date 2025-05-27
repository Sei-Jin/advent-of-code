package aoc.event.year2023;

import aoc.DeprecatedSolver2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/// # [2023-02: Cube Conundrum](https://adventofcode.com/2023/day/2)
public class Day02 implements DeprecatedSolver2 {
    
    // Stores the data for the games.
    private final List<Game> games;
    
    /// Initializes the solution
    ///
    /// @param input the puzzle input
    public Day02(String input) {
        this.games = parse(input);
    }
    
    /// Parses the puzzle input for the game data.
    ///
    /// Each line of the input contains the data for a single game. The expected format for a
    /// game is:
    ///
    /// `Game #: # colour, # colour, # colour; # colour; # colour, #colour...`
    ///
    /// - The first part of the input, `Game #`, contains the unique id of the game, `#`.
    /// - The second half of the input contains the sets of cubes.
    ///     - The semicolons, `;`, separate each set of cubes.
    ///     - The commas, `,`, separate each pair of cubes.
    ///         - Each pair, `# colour`, defines the number of cubes with that colour.
    ///
    /// @param input the puzzle input.
    /// @return the list of game data.
    private static List<Game> parse(String input) {
        final var games = new ArrayList<Game>();
        
        input.lines().forEach(line -> {
            final var parts = line.split(":");
            
            final var id = Integer.parseInt(parts[0].split(" ")[1]);
            final var sets = parts[1].split(";");
            
            final var maxCounts = calculateMaxCounts(sets);
            
            games.add(new Game(id, maxCounts));
        });
        
        return games;
    }
    
    private static HashMap<String, Integer> calculateMaxCounts(String[] sets) {
        final var maxCounts = new HashMap<String, Integer>();
        
        for (final var set : sets) {
            final var pairs = set.split(",");
            
            for (final var pair: pairs) {
                final var values = pair.trim().split(" ");
                
                final var count = Integer.parseInt(values[0]);
                final var colour = values[1];
                
                if (maxCounts.getOrDefault(colour, 0) < count) {
                    maxCounts.put(colour, count);
                }
            }
        }
        
        return maxCounts;
    }
    
    /// Calculates the sum of the ids for the possible games.
    ///
    /// A game is considered possible if the game contained a maximum of:
    /// - 12 red cubes
    /// - 13 green cubes
    /// - 14 blue cubes
    ///
    /// @return the sum of the ids of the possible games
    @Override
    public Object partOne() {
        var sum = 0;
        
        for (final var game : games) {
            boolean possibleGame = game.maxCounts.get("red") <= 12
                    && game.maxCounts.get("green") <= 13
                    && game.maxCounts.get("blue") <= 14;
            
            if (possibleGame) {
                sum += game.id;
            }
        }
        
        return sum;
    }
    
    /// Calculates the sum of the powers from all games.
    ///
    /// The power of a game is the product of the maximum counts seen across all sets. The total
    /// power is the sum of the powers from all games.
    ///
    /// @return the total power of the minimum sets of cubes counts.
    @Override
    public Object partTwo() {
        var sum = 0;
        
        for (final var game : games) {
            sum += game.maxCounts.get("red")
                    * game.maxCounts.get("green")
                    * game.maxCounts.get("blue");
        }
        
        return sum;
    }
    
    /// Stores the data for a game.
    ///
    /// @param id a unique id for the game.
    /// @param maxCounts stores the highest count for each color across all sets.
    record Game(int id, Map<String, Integer> maxCounts) {}
}