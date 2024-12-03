package aoc.event.year2024.day02.rednosedReports;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void exampleReport1()
    {
        List<String> report  = new ArrayList<>(List.of("7 6 4 2 1"));
        assertEquals(1, new Solution().partOne(report));
        assertEquals(1, new Solution().partTwo(report));
    }
    
    @Test
    void exampleReport2()
    {
        List<String> report  = new ArrayList<>(List.of("1 2 7 8 9"));
        assertEquals(0, new Solution().partOne(report));
        assertEquals(0, new Solution().partTwo(report));
    }
    
    @Test
    void exampleReport3()
    {
        List<String> report  = new ArrayList<>(List.of("9 7 6 2 1"));
        assertEquals(0, new Solution().partOne(report));
        assertEquals(0, new Solution().partTwo(report));
    }
    
    @Test
    void exampleReport4()
    {
        List<String> report  = new ArrayList<>(List.of("1 3 2 4 5"));
        assertEquals(0, new Solution().partOne(report));
        assertEquals(1, new Solution().partTwo(report));
    }
    
    @Test
    void exampleReport5()
    {
        List<String> report  = new ArrayList<>(List.of("8 6 4 4 1"));
        assertEquals(0, new Solution().partOne(report));
        assertEquals(1, new Solution().partTwo(report));
    }
    
    @Test
    void exampleReport6()
    {
        List<String> report  = new ArrayList<>(List.of("1 3 6 7 9"));
        assertEquals(1, new Solution().partOne(report));
        assertEquals(1, new Solution().partTwo(report));
    }
    
    @Test
    void allExampleReports()
    {
        List<String> reports  = new ArrayList<>(List.of(
                "7 6 4 2 1",
                "1 2 7 8 9",
                "9 7 6 2 1",
                "1 3 2 4 5",
                "8 6 4 4 1",
                "1 3 6 7 9"
        ));
        assertEquals(2, new Solution().partOne(reports));
        assertEquals(4, new Solution().partTwo(reports));
    }
}
