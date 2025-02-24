package aoc.event.year2024.day07.bridgeRepair;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests {
    
    @Test
    void exampleLine1() {
        final var input = """
                190: 10 19
                """;
        final var solution = new Solution(input);
        assertEquals(190L, solution.partOne());
    }
    
    @Test
    void exampleLine2() {
        final var input = """
                3267: 81 40 27
                """;
        final var solution = new Solution(input);
        assertEquals(3267L, solution.partOne());
    }
    
    @Test
    void exampleLine3() {
        final var input = """
                292: 11 6 16 20
                """;
        final var solution = new Solution(input);
        assertEquals(292L, solution.partOne());
    }
    
    @Test
    void example() {
        final var input = """
                190: 10 19
                3267: 81 40 27
                83: 17 5
                156: 15 6
                7290: 6 8 6 15
                161011: 16 10 13
                192: 17 8 14
                21037: 9 7 18 13
                292: 11 6 16 20
                """;
        final var solution = new Solution(input);
        assertEquals(3749L, solution.partOne());
    }
}
