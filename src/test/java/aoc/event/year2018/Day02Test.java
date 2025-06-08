package aoc.event.year2018;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {
    
    @Test
    void testExamplePart1() {
        var input = """
            abcdef
            bababc
            abbcde
            abcccd
            aabcdd
            abcdee
            ababab
            """;
        var solution = new Day02(input);
        assertEquals(12, solution.partOne());
    }
    
    @Test
    void testExamplePart2() {
        var input = """
            abcde
            fghij
            klmno
            pqrst
            fguij
            axcye
            wvxyz
            """;
        var solution = new Day02(input);
        assertEquals("fgij", solution.partTwo());
    }
}
