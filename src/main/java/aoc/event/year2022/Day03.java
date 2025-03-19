package aoc.event.year2022;

import aoc.Solver;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Day03 implements Solver {
    
    private final List<List<Character>> lines;
    
    /// Initializes the solution with the parsed data
    ///
    /// Each line of the puzzle input is in the form `cccccccc...`, where each `c` an
    /// alphabetical character from `a-z` that may or may not be capitalized.
    ///
    /// @param input the puzzle input.
    public Day03(String input) {
        lines = Collections.unmodifiableList(parse(input));
    }
    
    private static List<List<Character>> parse(String input) {
        return input.lines()
                .map(line -> line.chars()
                        .mapToObj(c -> (char) c)
                        .toList())
                .toList();
    }
    
    /// Maps lowercase characters to the values 1 to 26 and uppercase characters to the values 27
    /// to 52.
    private static int calculateItemPriority(char sharedItem) {
        var itemPriority = 0;
        
        if (sharedItem >= 'a' && sharedItem <= 'z') {
            itemPriority = sharedItem - 96;
        } else if (sharedItem >= 'A' && sharedItem <= 'Z') {
            itemPriority = sharedItem - 38;
        }
        
        return itemPriority;
    }
    
    /// Calculates the sum of the priority values for the shared characters.
    ///
    /// Each line contains one shared character between the first and second half
    ///
    /// @return the sum of the priority values for the shared characters.
    public Integer partOne() {
        var totalItemPriority = 0;
        
        for (final var line : lines) {
            final var midPoint = line.size() / 2;
            
            final var firstHalf = new HashSet<>(line.subList(0, midPoint));
            final var secondHalf = new HashSet<>(line.subList(midPoint, line.size()));
            
            firstHalf.retainAll(secondHalf);
            
            final var sharedItem = firstHalf.stream().findFirst().orElseThrow();
            totalItemPriority += calculateItemPriority(sharedItem);
        }
        
        return totalItemPriority;
    }
    
    /// Calculates the sum of the priority values for the shared characters.
    ///
    /// Each three-line group contains one shared character.
    ///
    /// @return the sum of the priority values for the shared characters.
    public Integer partTwo() {
        var totalItemPriority = 0;
        
        for (var i = 0; i < lines.size(); i += 3) {
            final var first = new HashSet<>(lines.get(i));
            final var second = new HashSet<>(lines.get(i + 1));
            final var third = new HashSet<>(lines.get(i + 2));
            
            first.retainAll(second);
            first.retainAll(third);
            
            final var sharedItem = first.stream().findFirst().orElseThrow();
            totalItemPriority += calculateItemPriority(sharedItem);
        }
        
        return totalItemPriority;
    }
}
