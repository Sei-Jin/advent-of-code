package advent_of_code.year_2017.__02__corruption_checksum;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Nested
    class PartOne
    {
        @Test
        void exampleSpreadsheetFirstRow()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("5 1 9 5");
            assertEquals(8, new Solution().partOne(inputLines));
        }
        
        @Test
        void exampleSpreadsheetSecondRow()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("7 5 3  ");
            assertEquals(4, new Solution().partOne(inputLines));
        }
        
        @Test
        void exampleSpreadsheetThirdRow()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("2 4 6 8");
            assertEquals(6, new Solution().partOne(inputLines));
        }
        
        @Test
        void exampleSpreadsheet()
        {
            List<String> inputLines = new ArrayList<>(Arrays.asList(
                    "5 1 9 5",
                    "7 5 3  ",
                    "2 4 6 8"
            ));
            assertEquals(18, new Solution().partOne(inputLines));
        }
    }
    
    
    @Nested
    class PartTwo
    {
        @Test
        void exampleSpreadsheetFirstRow()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("5 9 2 8");
            assertEquals(4, new Solution().partTwo(inputLines));
        }
        
        @Test
        void exampleSpreadsheetSecondRow()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("9 4 7 3");
            assertEquals(3, new Solution().partTwo(inputLines));
        }
        
        @Test
        void exampleSpreadsheetThirdRow()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("3 8 6 5");
            assertEquals(2, new Solution().partTwo(inputLines));
        }
        
        @Test
        void exampleSpreadsheet()
        {
            List<String> inputLines = new ArrayList<>(Arrays.asList(
                    "5 9 2 8",
                    "9 4 7 3",
                    "3 8 6 5"
            ));
            assertEquals(9, new Solution().partTwo(inputLines));
        }
    }
}
