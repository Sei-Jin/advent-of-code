package aoc.event.year2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {
    
    @Test
    void example() {
        var input = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """;
        var solution = new Day06(input);
        assertEquals(41, solution.partOne());
    }
}
