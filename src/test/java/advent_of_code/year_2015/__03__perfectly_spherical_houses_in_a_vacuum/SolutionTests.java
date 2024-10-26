package advent_of_code.year_2015.__03__perfectly_spherical_houses_in_a_vacuum;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void headEast()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add(">");
        assertEquals(2, new Solution().partOne(inputLines));
        assertEquals(2, new Solution().partTwo(inputLines));
    }
    
    @Test
    void cycleThroughDirections()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("^>v<");
        assertEquals(4, new Solution().partOne(inputLines));
        assertEquals(3, new Solution().partTwo(inputLines));
    }
    
    @Test
    void northAndSouthAlternating()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("^v^v^v^v^v");
        assertEquals(2, new Solution().partOne(inputLines));
        assertEquals(11, new Solution().partTwo(inputLines));
    }
    
    @Test
    void northAndSouth()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("^v");
        assertEquals(2, new Solution().partOne(inputLines));
        assertEquals(3, new Solution().partTwo(inputLines));
    }
}
