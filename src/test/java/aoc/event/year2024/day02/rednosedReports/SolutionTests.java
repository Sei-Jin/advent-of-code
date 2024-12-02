package aoc.event.year2024.day02.rednosedReports;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void exampleReportLevel1()
    {
        List<String> report  = new ArrayList<>(List.of("7 6 4 2 1"));
        assertEquals(1, new Solution().partOne(report));
    }
    
    @Test
    void exampleReportLevel2()
    {
        List<String> report  = new ArrayList<>(List.of("1 2 7 8 9"));
        assertEquals(0, new Solution().partOne(report));
    }
    
    @Test
    void exampleReportLevel3()
    {
        List<String> report  = new ArrayList<>(List.of("9 7 6 2 1"));
        assertEquals(0, new Solution().partOne(report));
    }
    
    @Test
    void exampleReportLevel4()
    {
        List<String> report  = new ArrayList<>(List.of("1 3 2 4 5"));
        assertEquals(0, new Solution().partOne(report));
    }
    
    @Test
    void exampleReportLevel5()
    {
        List<String> report  = new ArrayList<>(List.of("8 6 4 4 1"));
        assertEquals(0, new Solution().partOne(report));
    }
    
    @Test
    void exampleReportLevel6()
    {
        List<String> report  = new ArrayList<>(List.of("1 3 6 7 9"));
        assertEquals(1, new Solution().partOne(report));
    }
    
    @Test
    void exampleReportAllLevels()
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
    }
}
