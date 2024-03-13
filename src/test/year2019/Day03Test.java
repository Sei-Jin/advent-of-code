package test.year2019;

import org.junit.jupiter.api.Test;
import year2019.Day03;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test
{
    @Test
    void testSampleInputsPartOne()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("R8,U5,L5,D3");
        inputLines.add("U7,R6,D4,L4");

        assertEquals(6, Day03.partOne(inputLines));

        inputLines.clear();
        inputLines.add("R75,D30,R83,U83,L12,D49,R71,U7,L72");
        inputLines.add("U62,R66,U55,R34,D71,R55,D58,R83");

        assertEquals(159, Day03.partOne(inputLines));

        inputLines.clear();
        inputLines.add("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51");
        inputLines.add("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");

        assertEquals(135, Day03.partOne(inputLines));
    }


    @Test
    void extraTestsPartOne()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("U2,R2");
        inputLines.add("R2,U2");

        assertEquals(4, Day03.partOne(inputLines));
    }

}
