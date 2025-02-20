package aoc.event.year2023.day02.cubeConundrum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests {
    
    @Test
    void exampleGame1() {
        final var input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
        final var solution = new Solution(input);
        assertEquals(1, solution.partOne());
        assertEquals(48, solution.partTwo());
    }
    
    @Test
    void exampleGame2() {
        final var input = "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue";
        final var solution = new Solution(input);
        assertEquals(2, solution.partOne());
        assertEquals(12, solution.partTwo());
    }
    
    @Test
    void exampleGame3() {
        final var input = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red";
        final var solution = new Solution(input);
        assertEquals(0, solution.partOne());
        assertEquals(1560, solution.partTwo());
    }
    
    @Test
    void exampleGame4() {
        final var input = "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red";
        final var solution = new Solution(input);
        assertEquals(0, solution.partOne());
        assertEquals(630, solution.partTwo());
    }
    
    @Test
    void exampleGame5() {
        final var input = "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";
        final var solution = new Solution(input);
        assertEquals(5, solution.partOne());
        assertEquals(36, solution.partTwo());
    }
}
