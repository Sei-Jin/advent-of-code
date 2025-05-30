package aoc.event.year2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {
    
    @Test
    void example() {
        var input = """
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
        var solution = new Day01(input);
        assertEquals(24000, solution.partOne());
        assertEquals(45000, solution.partTwo());
    }
}
