package aoc.event.year2022.day01.calorieCounting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests {
    
    @Test
    void example() {
        final var input = """
                1000
                2000
                3000
                
                4000
                
                5000
                6000
                
                7000
                8000
                9000
                
                10000
                """;
        final var solution = new Solution(input);
        assertEquals(24000, solution.partOne());
        assertEquals(45000, solution.partTwo());
    }
}
