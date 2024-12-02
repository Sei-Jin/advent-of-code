package aoc.event.year2023.day03.gearRatios;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void exampleSchematic()
    {
        List<String> schematic = List.of(
                "467..114..",
                "...*......",
                "..35..633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.*....",
                ".664.598.."
        );
        assertEquals(4361, new Solution().partOne(schematic));
    }
}
