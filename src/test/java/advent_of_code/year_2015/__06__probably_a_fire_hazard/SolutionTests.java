package advent_of_code.year_2015.__06__probably_a_fire_hazard;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void turnOnEverything()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("turn on 0,0 through 999,999");
        assertEquals(1000000, new Solution().partOne(inputLines));
        assertEquals(1000000, new Solution().partTwo(inputLines));
    }
    
    @Test
    void toggleFirstRow()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("toggle 0,0 through 999,0");
        assertEquals(1000, new Solution().partOne(inputLines));
        assertEquals(2000, new Solution().partTwo(inputLines));
    }
    
    @Test
    void turnOffMiddleFour()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("turn off 499,499 through 500,500");
        assertEquals(0, new Solution().partOne(inputLines));
        assertEquals(0, new Solution().partTwo(inputLines));
    }
    
    @Test
    void turnOnEverythingThenTurnOffMiddleFour()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("turn on 0,0 through 999,999");
        inputLines.add("turn off 499,499 through 500,500");
        assertEquals(999996, new Solution().partOne(inputLines));
        assertEquals(999996, new Solution().partTwo(inputLines));
    }
    
    @Test
    void turnOnFirstTwice()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("turn on 0,0 through 0,0");
        inputLines.add("turn on 0,0 through 0,0");
        assertEquals(1, new Solution().partOne(inputLines));
        assertEquals(2, new Solution().partTwo(inputLines));
    }
    
    @Test
    void toggleEverything()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("toggle 0,0 through 999,999");
        assertEquals(1000000, new Solution().partOne(inputLines));
        assertEquals(2000000, new Solution().partTwo(inputLines));
    }
}
