package aoc.event.year2022.day01.calorieCounting;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void example()
    {
        List<String> input = new ArrayList<>(
                Arrays.stream("""
                1000
                2000
                3000
                
                4000
                
                5000
                6000
                
                7000
                8000
                9000
                
                10000
                """.split("\n")).toList()
        );
        assertEquals(24000, new Solution().partOne(input));
        assertEquals(45000, new Solution().partTwo(input));
    }
}
