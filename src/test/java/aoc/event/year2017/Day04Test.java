package aoc.event.year2017;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {
    
    @Nested
    class PartOne {
        @Test
        void noDuplicateWords1() {
            final var input = "aa bb cc dd ee";
            final var solution = new Day04(input);
            assertEquals(1, solution.partOne());
        }
        
        @Test
        void duplicateWords() {
            final var input = "aa bb cc dd aa";
            final var solution = new Day04(input);
            assertEquals(0, solution.partOne());
        }
        
        @Test
        void noDuplicateWords2() {
            final var input = "aa bb cc dd aaa";
            final var solution = new Day04(input);
            assertEquals(1, solution.partOne());
        }
    }
    
    @Nested
    class PartTwo {
        @Test
        void noAnagrams1() {
            final var input = "abcde fghij";
            final var solution = new Day04(input);
            assertEquals(1, solution.partTwo());
        }
        
        @Test
        void noAnagrams2() {
            final var input = "a ab abc abd abf abj";
            final var solution = new Day04(input);
            assertEquals(1, solution.partTwo());
        }
        
        @Test
        void noAnagrams3() {
            final var input = "iiii oiii ooii oooi oooo";
            final var solution = new Day04(input);
            assertEquals(1, solution.partTwo());
        }
        
        @Test
        void containsAnagram() {
            final var input = "abcde xyz ecdab";
            final var solution = new Day04(input);
            assertEquals(0, solution.partTwo());
        }
        
        @Test
        void allAnagrams() {
            final var input = "oiii ioii iioi iiio";
            final var solution = new Day04(input);
            assertEquals(0, solution.partTwo());
        }
    }
}
