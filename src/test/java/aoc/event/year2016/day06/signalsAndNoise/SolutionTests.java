package aoc.event.year2016.day06.signalsAndNoise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests {
    
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
        final var solution = new Solution(input);
        assertEquals("easter", solution.partOne());
        assertEquals("advent", solution.partTwo());
    }
}
