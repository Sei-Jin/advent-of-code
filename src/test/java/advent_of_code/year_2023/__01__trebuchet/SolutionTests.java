package advent_of_code.year_2023.__01__trebuchet;

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
        void exampleLine1()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("1abc2");
            assertEquals(12, new Solution().partOne(inputLines));
        }
        
        @Test
        void exampleLine2()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("pqr3stu8vwx");
            assertEquals(38, new Solution().partOne(inputLines));
        }
        
        @Test
        void exampleLine3()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("a1b2c3d4e5f");
            assertEquals(15, new Solution().partOne(inputLines));
        }
        
        @Test
        void exampleLine4()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("treb7uchet");
            assertEquals(77, new Solution().partOne(inputLines));
        }
    }
    
    
    @Nested
    class PartTwo
    {
        @Test
        void exampleLine1()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("two1nine");
            assertEquals(29, new Solution().partTwo(inputLines));
        }
        
        @Test
        void exampleLine2()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("eightwothree");
            assertEquals(83, new Solution().partTwo(inputLines));
        }
        
        @Test
        void exampleLine3()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("abcone2threexyz");
            assertEquals(13, new Solution().partTwo(inputLines));
        }
        
        @Test
        void exampleLine4()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("xtwone3four");
            assertEquals(24, new Solution().partTwo(inputLines));
        }
        
        @Test
        void exampleLine5()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("4nineeightseven2");
            assertEquals(42, new Solution().partTwo(inputLines));
        }
        
        @Test
        void exampleLine6()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("zoneight234");
            assertEquals(14, new Solution().partTwo(inputLines));
        }
        
        @Test
        void exampleLine7()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("7pqrstsixteen");
            assertEquals(76, new Solution().partTwo(inputLines));
        }
    }
}
