package aoc.event.year2016.day02.bathroomSecurity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void exampleInstructions()
    {
        List<String> inputLines = new ArrayList<>(List.of(
                "ULL",
                "RRDDD",
                "LURDL",
                "UUUUD"
        ));
        assertEquals("1985", new Solution().partOne(inputLines).toString());
        assertEquals("5DB3", new Solution().partTwo(inputLines).toString());
    }
}
