package aoc.event.year2023;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test
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
        assertEquals(4361, new Day03().partOne(schematic));
    }
}
