package aoc.event.year2019;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test
{
    @Test
    void repeatingDigits()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("111111-111111");
        assertEquals(1, new Day04().partOne(inputLines));
    }
    
    @Test
    void decreasingPairOfDigits()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("223450-223450");
        assertEquals(0, new Day04().partOne(inputLines));
    }
    
    @Test
    void increasingDigits()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("123789-123789");
        assertEquals(0, new Day04().partOne(inputLines));
    }
    
    @Test
    void smallRange()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("111110-111120");
        assertEquals(9, new Day04().partOne(inputLines));
    }
}
