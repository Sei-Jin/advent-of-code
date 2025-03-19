package aoc.event.year2024;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test
{
    @Test
    void example()
    {
        List<String> input = new ArrayList<>(
                Arrays.stream("""
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ..........
                .#..^.....
                ........#.
                #.........
                ......#...
                """.split("\n")).toList()
        );
        assertEquals(41, new Day06().partOne(input));
    }
}
