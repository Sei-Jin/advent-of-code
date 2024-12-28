package aoc.event.year2016.day04.securityThroughObscurity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void exampleLine1()
    {
        List<String> input = List.of("aaaaa-bbb-z-y-x-123[abxyz]");
        assertEquals(123, new Solution().partOne(input));
    }
    
    @Test
    void exampleLine2()
    {
        List<String> input = List.of("a-b-c-d-e-f-g-h-987[abcde]");
        assertEquals(987, new Solution().partOne(input));
    }
    
    @Test
    void exampleLine3()
    {
        List<String> input = List.of("not-a-real-room-404[oarel]");
        assertEquals(404, new Solution().partOne(input));
    }
    
    @Test
    void exampleLine4()
    {
        List<String> input = List.of("totally-real-room-200[decoy]");
        assertEquals(0, new Solution().partOne(input));
    }
    
    @Test
    void completeExample()
    {
        List<String> input = Arrays.stream("""
                aaaaa-bbb-z-y-x-123[abxyz]
                a-b-c-d-e-f-g-h-987[abcde]
                not-a-real-room-404[oarel]
                totally-real-room-200[decoy]
                """.split("\n")).toList();
        
        assertEquals(1514, new Solution().partOne(input));
    }
}
