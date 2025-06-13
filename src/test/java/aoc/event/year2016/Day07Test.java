package aoc.event.year2016;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day07Test {
    
    @Nested
    public class partOne {
        
        @Test
        void example1() {
            var input = "abba[mnop]qrst";
            var solution = new Day07(input);
            assertEquals(1, solution.partOne());
        }
        
        @Test
        void example2() {
            var input = "abcd[bddb]xyyx";
            var solution = new Day07(input);
            assertEquals(0, solution.partOne());
        }
        
        @Test
        void example3() {
            var input = "aaaa[qwer]tyui";
            var solution = new Day07(input);
            assertEquals(0, solution.partOne());
        }
        
        @Test
        void example4() {
            var input = "ioxxoj[asdfgh]zxcvbn";
            var solution = new Day07(input);
            assertEquals(1, solution.partOne());
        }
    }
    
    @Nested
    public class partTwo {
        
        @Test
        void example1() {
            var input = "aba[bab]xyz";
            var solution = new Day07(input);
            assertEquals(1, solution.partTwo());
        }
        
        @Test
        void example2() {
            var input = "xyx[xyx]xyx";
            var solution = new Day07(input);
            assertEquals(0, solution.partTwo());
        }
        
        @Test
        void example3() {
            var input = "aaa[kek]eke";
            var solution = new Day07(input);
            assertEquals(1, solution.partTwo());
        }
        
        @Test
        void example4() {
            var input = "zazbz[bzb]cdb";
            var solution = new Day07(input);
            assertEquals(1, solution.partTwo());
        }
    }
}
