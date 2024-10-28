package advent_of_code.year_2019.__03__crossed_wires;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Nested
    class PartOne
    {
        @Test
        void exampleOne()
        {
            List<String> inputLines = new ArrayList<>(List.of(
                    "R8,U5,L5,D3",
                    "U7,R6,D4,L4"
            ));
            assertEquals(6, new Solution().partOne(inputLines));
        }
        
        @Test
        void exampleTwo()
        {
            List<String> inputLines = new ArrayList<>(List.of(
                    "R75,D30,R83,U83,L12,D49,R71,U7,L72",
                    "U62,R66,U55,R34,D71,R55,D58,R83"
            ));
            assertEquals(159, new Solution().partOne(inputLines));
        }
        
        @Test
        void exampleThree()
        {
            List<String> inputLines = new ArrayList<>(List.of(
                    "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51",
                    "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"
            ));
            assertEquals(135, new Solution().partOne(inputLines));
        }
        
        @Test
        void extraTestsPartOne()
        {
            List<String> inputLines = new ArrayList<>(Arrays.asList(
                    "U2,R2",
                    "R2,U2"
            ));
            assertEquals(4, new Solution().partOne(inputLines));
        }
    }

}
