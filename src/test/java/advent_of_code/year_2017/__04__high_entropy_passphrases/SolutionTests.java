package advent_of_code.year_2017.__04__high_entropy_passphrases;

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
        void noDuplicateWords1()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("aa bb cc dd ee");
            assertEquals(1, new Solution().partOne(inputLines));
        }
        
        @Test
        void duplicateWords()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("aa bb cc dd aa");
            assertEquals(0, new Solution().partOne(inputLines));
        }
        
        @Test
        void noDuplicateWords2()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("aa bb cc dd aaa");
            assertEquals(1, new Solution().partOne(inputLines));
        }
    }
    
    
    @Nested
    class PartTwo
    {
        @Test
        void noAnagrams1()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("abcde fghij");
            assertEquals(1, new Solution().partTwo(inputLines));
        }
        
        
        @Test
        void noAnagrams2()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("a ab abc abd abf abj");
            assertEquals(1, new Solution().partTwo(inputLines));
        }
        
        @Test
        void noAnagrams3()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("iiii oiii ooii oooi oooo");
            assertEquals(1, new Solution().partTwo(inputLines));
        }
        
        @Test
        void containsAnagram()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("abcde xyz ecdab");
            assertEquals(0, new Solution().partTwo(inputLines));
        }
        
        @Test
        void allAnagrams()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("oiii ioii iioi iiio");
            assertEquals(0, new Solution().partTwo(inputLines));
        }
    }
}
