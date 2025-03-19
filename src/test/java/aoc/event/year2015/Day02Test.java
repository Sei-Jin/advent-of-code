package aoc.event.year2015;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day02Test {
    
    @Test
    void increasingDimensions() {
        final var input = "2x3x4";
        final var solution = new Day02(input);
        Assertions.assertEquals(58, solution.partOne());
        Assertions.assertEquals(34, solution.partTwo());
    }
    
    @Test
    void smallSmallestSide() {
        final var input = "1x1x10";
        final var solution = new Day02(input);
        Assertions.assertEquals(43, solution.partOne());
        Assertions.assertEquals(14, solution.partTwo());
    }
    
    @Test
    void evenDimensions() {
        final var input = "5x5x5";
        final var solution = new Day02(input);
        Assertions.assertEquals(175, solution.partOne());
        Assertions.assertEquals(145, solution.partTwo());
    }
}
