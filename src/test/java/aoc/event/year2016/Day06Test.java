package aoc.event.year2016;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {
    
    @Test
    void example() {
        final var input = """
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
            """;
        final var solution = new Day06(input);
        assertEquals("easter", solution.partOne());
        assertEquals("advent", solution.partTwo());
    }
}
