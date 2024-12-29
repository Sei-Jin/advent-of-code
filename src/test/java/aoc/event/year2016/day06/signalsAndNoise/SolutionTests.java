package aoc.event.year2016.day06.signalsAndNoise;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void example()
    {
        List<String> input = Arrays.stream("""
                eedadn
                drvtee
                eandsr
                raavrd
                atevrs
                tsrnev
                sdttsa
                rasrtv
                nssdts
                ntnada
                svetve
                tesnvt
                vntsnd
                vrdear
                dvrsen
                enarar
                """.split("\n")).toList();
        assertEquals("easter", new Solution().partOne(input));
        assertEquals("advent", new Solution().partTwo(input));
    }
}
