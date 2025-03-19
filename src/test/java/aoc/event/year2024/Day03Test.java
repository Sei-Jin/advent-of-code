package aoc.event.year2024;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test
{
    @Nested
    class PartOne
    {
        @Test
        void example()
        {
            String programMemory = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
            assertEquals(161, new Day03().partOne(Collections.singletonList(programMemory)));
        }
    }
    
    @Nested
    class PartTwo
    {
        @Test
        void example()
        {
            String programMemory = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
            assertEquals(48, new Day03().partTwo(Collections.singletonList(programMemory)));
        }
    }
}
