package aoc.event.year2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {
    
    @Test
    void examplePart1() {
        var input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
        var solution = new Day03(input);
        assertEquals(161, solution.partOne());
    }
    
    @Test
    void examplePart2() {
        var input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
        var solution = new Day03(input);
        assertEquals(48, solution.partTwo());
    }
}
