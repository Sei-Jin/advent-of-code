package aoc.event.year2016;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day07Test {
    
    @Test
    void example1() {
        final var input = "abba[mnop]qrst";
        final var solution = new Day07(input);
        assertEquals(1, solution.partOne());
    }
    
    @Test
    void example2() {
        final var input = "abcd[bddb]xyyx";
        final var solution = new Day07(input);
        assertEquals(0, solution.partOne());
    }
    
    @Test
    void example3() {
        final var input = "aaaa[qwer]tyui";
        final var solution = new Day07(input);
        assertEquals(0, solution.partOne());
    }
    
    @Test
    void example4() {
        final var input = "ioxxoj[asdfgh]zxcvbn";
        final var solution = new Day07(input);
        assertEquals(1, solution.partOne());
    }
}
