package advent_of_code.year_2017.__01__inverse_captcha;

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
        void twoDigitsRepeatNext()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("1122");
            assertEquals(3, new Solution().partOne(inputLines));
        }
        
        @Test
        void allDigitsRepeatNext()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("1111");
            assertEquals(4, new Solution().partOne(inputLines));
        }
        
        @Test
        void noDigitsRepeatNext()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("1234");
            assertEquals(0, new Solution().partOne(inputLines));
        }
        
        @Test
        void endDigitRepeatsNext()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("91212129");
            assertEquals(9, new Solution().partOne(inputLines));
        }
    }
    
    
    @Nested
    class PartTwo
    {
        @Test
        void allDigitsRepeatHalfway()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("1212");
            assertEquals(6, new Solution().partTwo(inputLines));
        }
        
        @Test
        void noDigitsRepeatHalfway()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("1221");
            assertEquals(0, new Solution().partTwo(inputLines));
        }
        
        @Test
        void oneDigitRepeatsHalfway()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("123425");
            assertEquals(4, new Solution().partTwo(inputLines));
        }
        
        @Test
        void allDigitsRepeatHalfway2()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("123123");
            assertEquals(12, new Solution().partTwo(inputLines));
        }
        
        @Test
        void twoDigitsRepeatHalfway()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("12131415");
            assertEquals(4, new Solution().partTwo(inputLines));
        }
    }
}
