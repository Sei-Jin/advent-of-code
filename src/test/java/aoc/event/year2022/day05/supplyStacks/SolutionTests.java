package aoc.event.year2022.day05.supplyStacks;

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
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s
                
                move 1 from 2 to 1
                move 3 from 1 to 3
                move 2 from 2 to 1
                move 1 from 1 to 2
                """.split("\n")).toList()
        );
        assertEquals("CMZ", new Solution().partOne(input));
        assertEquals("MCD", new Solution().partTwo(input));
    }
}
