package advent_of_code.year_2015.__01__not_quite_lisp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartTwoTests
{
    @Test
    void ascendThenDescendSameAmount()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("(())");
        Assertions.assertEquals(-1, new Solution().partTwo(inputLines));
    }
    
    @Test
    void loopingBetweenFloors()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("()()");
        assertEquals(-1, new Solution().partTwo(inputLines));
    }
    
    @Test
    void onlyAscending()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("(((");
        assertEquals(-1, new Solution().partTwo(inputLines));
    }
    
    @Test
    void ascendingWithLoops()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("(()(()(");
        assertEquals(-1, new Solution().partTwo(inputLines));
    }
    
    @Test
    void descendThenSharpAscend()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("))(((((");
        assertEquals(1, new Solution().partTwo(inputLines));
    }
    
    @Test
    void loopThenDescend()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("())");
        assertEquals(3, new Solution().partTwo(inputLines));
    }
    
    @Test
    void descendThenSlightAscend()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("))(");
        assertEquals(1, new Solution().partTwo(inputLines));
    }
    
    @Test
    void onlyDescending()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add(")))");
        assertEquals(1, new Solution().partTwo(inputLines));
    }
    
    @Test
    void descendingWithLoops()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add(")())())");
        assertEquals(1, new Solution().partTwo(inputLines));
    }
    
    @Test
    void immediateDescent()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add(")");
        assertEquals(1, new Solution().partTwo(inputLines));
    }
    
    @Test
    void loopingThenDescend()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("()())");
        assertEquals(5, new Solution().partTwo(inputLines));
    }
}
