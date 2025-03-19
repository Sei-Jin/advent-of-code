package aoc.event.year2024;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test
{
    @Test
    void example()
    {
        List<String> input = new ArrayList<>(Arrays.stream(
                """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """.split("\n")).toList()
        );
        assertEquals(18, new Day04().partOne(input));
    }
}
