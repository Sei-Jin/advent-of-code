package aoc.event.year2017.day01.inverseCaptcha;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    private static final Solution solution = new Solution();

    @Nested
    class PartOne
    {
        @Test
        void twoDigitsRepeatNext()
        {
            solution.parse("1122");
            assertEquals(3, solution.partOne());
        }
        
        @Test
        void allDigitsRepeatNext()
        {
            solution.parse("1111");
            assertEquals(4, solution.partOne());
        }
        
        @Test
        void noDigitsRepeatNext()
        {
            solution.parse("1234");
            assertEquals(0, solution.partOne());
        }
        
        @Test
        void endDigitRepeatsNext()
        {
            solution.parse("91212129");
            assertEquals(9, solution.partOne());
        }
    }
    
    
    @Nested
    class PartTwo
    {
        @Test
        void allDigitsRepeatHalfway()
        {
            solution.parse("1212");
            assertEquals(6, solution.partTwo());
        }
        
        @Test
        void noDigitsRepeatHalfway()
        {
            solution.parse("1221");
            assertEquals(0, solution.partTwo());
        }
        
        @Test
        void oneDigitRepeatsHalfway()
        {
            solution.parse("123425");
            assertEquals(4, solution.partTwo());
        }
        
        @Test
        void allDigitsRepeatHalfway2()
        {
            solution.parse("123123");
            assertEquals(12, solution.partTwo());
        }
        
        @Test
        void twoDigitsRepeatHalfway()
        {
            solution.parse("12131415");
            assertEquals(4, solution.partTwo());
        }
    }
}
