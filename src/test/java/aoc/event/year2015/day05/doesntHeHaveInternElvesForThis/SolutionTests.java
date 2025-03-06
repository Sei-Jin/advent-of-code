package aoc.event.year2015.day05.doesntHeHaveInternElvesForThis;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests {
    
    @Nested
    class PartOne {
        
        @Test
        void allThreeRules() {
            final var input = "ugknbfddgicrmopn";
            final var solution = new Solution(input);
            assertEquals(1, solution.partOne());
        }
        
        @Test
        void allThreeRulesOverlapping() {
            final var input = "aaa";
            final var solution = new Solution(input);
            assertEquals(1, solution.partOne());
        }
        
        @Test
        void noDoubleLetter() {
            final var input = "jchzalrnumimnmhp";
            final var solution = new Solution(input);
            assertEquals(0, solution.partOne());
        }
        
        @Test
        void disallowedSubstring() {
            final var input = "haegwjzuvuyypxyu";
            final var solution = new Solution(input);
            assertEquals(0, solution.partOne());
        }
        
        @Test
        void onlyOneVowel() {
            final var input = "dvszwmarrgswjxmb";
            final var solution = new Solution(input);
            assertEquals(0, solution.partOne());
        }
    }
    
    @Nested
    class PartTwo {
        
        @Test
        void repeatingPairAndRepeatingLetterWithOneLetterBetween() {
            final var input = "qjhvhtzxzqqjkmpb";
            final var solution = new Solution(input);
            assertEquals(1, solution.partTwo());
        }
        
        @Test
        void repeatingPair() {
            final var input = "uurcxstgmygtbstg";
            final var solution = new Solution(input);
            assertEquals(0, solution.partTwo());
        }
        
        @Test
        void repeatingLetterWithOneLetterBetween() {
            final var input = "ieodomkazucvgmuy";
            final var solution = new Solution(input);
            assertEquals(0, solution.partTwo());
        }
    }
}
