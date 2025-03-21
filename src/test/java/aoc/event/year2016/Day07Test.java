package aoc.event.year2016;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day07Test {
    
    @Nested
    public class partOne {
        
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
    
    @Nested
    public class partTwo {
        
        @Test
        void example1() {
            final var input = "aba[bab]xyz";
            final var solution = new Day07(input);
            assertEquals(1, solution.partTwo());
        }
        
        @Test
        void example2() {
            final var input = "xyx[xyx]xyx";
            final var solution = new Day07(input);
            assertEquals(0, solution.partTwo());
        }
        
        @Test
        void example3() {
            final var input = "aaa[kek]eke";
            final var solution = new Day07(input);
            assertEquals(1, solution.partTwo());
        }
        
        @Test
        void example4() {
            final var input = "zazbz[bzb]cdb";
            final var solution = new Day07(input);
            assertEquals(1, solution.partTwo());
        }
    }
}
