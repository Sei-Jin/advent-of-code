package aoc.event.year2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {
    
    @Test
    void example1() {
        final var input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
        final var solution = new Day06(input);
        assertEquals(7, solution.partOne());
        assertEquals(19, solution.partTwo());
    }
    
    @Test
    void example2() {
        final var input = "bvwbjplbgvbhsrlpgdmjqwftvncz";
        final var solution = new Day06(input);
        assertEquals(5, solution.partOne());
        assertEquals(23, solution.partTwo());
    }
    
    @Test
    void example3() {
        final var input = "nppdvjthqldpwncqszvftbrmjlhg";
        final var solution = new Day06(input);
        assertEquals(6, solution.partOne());
        assertEquals(23, solution.partTwo());
    }
    
    @Test
    void example4() {
        final var input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
        final var solution = new Day06(input);
        assertEquals(10, solution.partOne());
        assertEquals(29, solution.partTwo());
    }
    
    @Test
    void example5() {
        final var input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";
        final var solution = new Day06(input);
        assertEquals(11, solution.partOne());
        assertEquals(26, solution.partTwo());
    }
}
