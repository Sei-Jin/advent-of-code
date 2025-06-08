package aoc.event.year2017;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {
    
    @Nested
    class PartOne {
        @Test
        void noDuplicateWords1() {
            var input = "aa bb cc dd ee";
            var solution = new Day04(input);
            assertEquals(1, solution.partOne());
        }
        
        @Test
        void duplicateWords() {
            var input = "aa bb cc dd aa";
            var solution = new Day04(input);
            assertEquals(0, solution.partOne());
        }
        
        @Test
        void noDuplicateWords2() {
            var input = "aa bb cc dd aaa";
            var solution = new Day04(input);
            assertEquals(1, solution.partOne());
        }
    }
    
    @Nested
    class PartTwo {
        @Test
        void noAnagrams1() {
            var input = "abcde fghij";
            var solution = new Day04(input);
            assertEquals(1, solution.partTwo());
        }
        
        @Test
        void noAnagrams2() {
            var input = "a ab abc abd abf abj";
            var solution = new Day04(input);
            assertEquals(1, solution.partTwo());
        }
        
        @Test
        void noAnagrams3() {
            var input = "iiii oiii ooii oooi oooo";
            var solution = new Day04(input);
            assertEquals(1, solution.partTwo());
        }
        
        @Test
        void containsAnagram() {
            var input = "abcde xyz ecdab";
            var solution = new Day04(input);
            assertEquals(0, solution.partTwo());
        }
        
        @Test
        void allAnagrams() {
            var input = "oiii ioii iioi iiio";
            var solution = new Day04(input);
            assertEquals(0, solution.partTwo());
        }
    }
}
