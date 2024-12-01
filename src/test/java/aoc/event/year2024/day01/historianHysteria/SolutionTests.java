package aoc.event.year2024.day01.historianHysteria;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void exampleList()
    {
        List<String> input = new ArrayList<>(List.of(
                "3   4",
                "4   3",
                "2   5",
                "1   3",
                "3   9",
                "3   3"
        ));
        assertEquals(11, new Solution().partOne(input));
    }
}
