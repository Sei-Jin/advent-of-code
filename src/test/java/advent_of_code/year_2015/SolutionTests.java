package advent_of_code.year_2015;

import advent_of_code.year_2015.__01__not_quite_lisp.Solution;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void startingFloor1 ()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("(())");
        assertEquals(0, new Solution().partOne(inputLines));
    }
    
    @Test
    void startingFloor2 ()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("()()");
        assertEquals(0, new Solution().partOne(inputLines));
    }
    
    @Test
    void positiveFloor1 ()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("(((");
        assertEquals(3, new Solution().partOne(inputLines));
    }
    
    @Test
    void positiveFloor2 ()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("(()(()(");
        assertEquals(3, new Solution().partOne(inputLines));
    }
    
    @Test
    void positiveFloor3 ()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("))(((((");
        assertEquals(3, new Solution().partOne(inputLines));
    }
    
    @Test
    void negativeFloor ()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("())");
        assertEquals(-1, new Solution().partOne(inputLines));
    }
    
    @Test
    void deeperNegativeFloor ()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("())");
        assertEquals(-1, new Solution().partOne(inputLines));
    }
}
