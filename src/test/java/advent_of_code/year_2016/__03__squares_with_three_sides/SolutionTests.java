package advent_of_code.year_2016.__03__squares_with_three_sides;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Nested
    class PartOne
    {
        @Test
        void possibleTriangle1()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("5 10 10");
            assertEquals(1, new Solution().partOne(inputLines));
        }
        
        @Test
        void possibleTriangle2()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("5 10 14");
            assertEquals(1, new Solution().partOne(inputLines));
        }
        
        @Test
        void impossibleTriangle1()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("5 10 25");
            assertEquals(0, new Solution().partOne(inputLines));
        }
        
        @Test
        void impossibleTriangle2()
        {
            List<String> inputLines = new ArrayList<>();
            inputLines.add("15 10 5");
            assertEquals(0, new Solution().partOne(inputLines));
        }
    }
    
    @Nested
    class PartTwo
    {
        @Test
        void noPossibleTriangles()
        {
            List<String> inputLines = List.of(
                    "101 301 1500",
                    "102 700 501",
                    "300 302 502"
            );
            assertEquals(0, new Solution().partTwo(inputLines));
        }
        
        @Test
        void onePossibleTriangle()
        {
            List<String> inputLines = List.of(
                    "101 301 501",
                    "102 302 502",
                    "300 700 503"
            );
            assertEquals(1, new Solution().partTwo(inputLines));
        }
        
        @Test
        void twoPossibleTriangles()
        {
            List<String> inputLines = List.of(
                    "101 301 501",
                    "102 302 502",
                    "300 303 503"
            );
            assertEquals(2, new Solution().partTwo(inputLines));
        }
        
        @Test
        void threePossibleTriangles()
        {
            List<String> inputLines = List.of(
                    "101 301 501",
                    "102 302 502",
                    "103 303 503"
            );
            assertEquals(3, new Solution().partTwo(inputLines));
        }
    }
}
