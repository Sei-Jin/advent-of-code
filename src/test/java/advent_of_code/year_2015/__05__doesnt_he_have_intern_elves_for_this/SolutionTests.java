package advent_of_code.year_2015.__05__doesnt_he_have_intern_elves_for_this;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Nested
    class PartOne
    {
        @Test
        void allThreeRules()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("ugknbfddgicrmopn");
            assertEquals(1, new Solution().partOne(inputLines));
        }
        
        @Test
        void allThreeRulesOverlapping()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("aaa");
            assertEquals(1, new Solution().partOne(inputLines));
        }
        
        @Test
        void noDoubleLetter()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("jchzalrnumimnmhp");
            assertEquals(0, new Solution().partOne(inputLines));
        }
        
        @Test
        void disallowedSubstring()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("haegwjzuvuyypxyu");
            assertEquals(0, new Solution().partOne(inputLines));
        }
        
        @Test
        void onlyOneVowel()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("dvszwmarrgswjxmb");
            assertEquals(0, new Solution().partOne(inputLines));
        }
        
    }
    
    
    @Nested
    class PartTwo
    {
        @Test
        void repeatingPairAndRepeatingLetterWithOneLetterBetween()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("qjhvhtzxzqqjkmpb");
            assertEquals(1, new Solution().partTwo(inputLines));
        }
        
        @Test
        void repeatingPair()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("uurcxstgmygtbstg");
            assertEquals(0, new Solution().partTwo(inputLines));
        }
        
        @Test
        void repeatingLetterWithOneLetterBetween()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("ieodomkazucvgmuy");
            assertEquals(0, new Solution().partTwo(inputLines));
        }
    }
}
