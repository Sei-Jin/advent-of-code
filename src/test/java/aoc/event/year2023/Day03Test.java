package aoc.event.year2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {
    
    @Test
    void example() {
        var input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
            """;
        var solution = new Day03(input);
        assertEquals(4361, solution.partOne());
        assertEquals(467835, solution.partTwo());
    }
}
