package aoc.event.year2015;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {
    
    @Nested
    class PartOne {
        
        @Test
        void allThreeRules() {
            var input = "ugknbfddgicrmopn";
            var solution = new Day05(input);
            assertEquals(1, solution.partOne());
        }
        
        @Test
        void allThreeRulesOverlapping() {
            var input = "aaa";
            var solution = new Day05(input);
            assertEquals(1, solution.partOne());
        }
        
        @Test
        void noDoubleLetter() {
            var input = "jchzalrnumimnmhp";
            var solution = new Day05(input);
            assertEquals(0, solution.partOne());
        }
        
        @Test
        void disallowedSubstring() {
            var input = "haegwjzuvuyypxyu";
            var solution = new Day05(input);
            assertEquals(0, solution.partOne());
        }
        
        @Test
        void onlyOneVowel() {
            var input = "dvszwmarrgswjxmb";
            var solution = new Day05(input);
            assertEquals(0, solution.partOne());
        }
    }
    
    @Nested
    class PartTwo {
        
        @Test
        void repeatingPairAndRepeatingLetterWithOneLetterBetween() {
            var input = "qjhvhtzxzqqjkmpb";
            var solution = new Day05(input);
            assertEquals(1, solution.partTwo());
        }
        
        @Test
        void repeatingPair() {
            var input = "uurcxstgmygtbstg";
            var solution = new Day05(input);
            assertEquals(0, solution.partTwo());
        }
        
        @Test
        void repeatingLetterWithOneLetterBetween() {
            var input = "ieodomkazucvgmuy";
            var solution = new Day05(input);
            assertEquals(0, solution.partTwo());
        }
    }
}
