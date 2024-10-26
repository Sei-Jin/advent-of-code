package advent_of_code.year_2016.__01__no_time_for_a_taxicab;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void northEast()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("R2, L3");
        assertEquals(5, new Solution().partOne(inputLines));
        assertEquals(-1, new Solution().partTwo(inputLines));
    }
    
    @Test
    void eastToSouth()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("R2, R2, R2");
        assertEquals(2, new Solution().partOne(inputLines));
        assertEquals(-1, new Solution().partTwo(inputLines));
    }
    
    @Test
    void snakeEastwards()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("R5, L5, R5, R3");
        assertEquals(12, new Solution().partOne(inputLines));
        assertEquals(-1, new Solution().partTwo(inputLines));
    }
    
    @Test
    void crossingPaths()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("R8, R4, R4, R8");
        assertEquals(8, new Solution().partOne(inputLines));
        assertEquals(4, new Solution().partTwo(inputLines));
    }
}
