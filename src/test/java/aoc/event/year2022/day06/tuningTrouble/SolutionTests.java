package aoc.event.year2022.day06.tuningTrouble;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void example1()
    {
        List<String> input = List.of("mjqjpqmgbljsphdztnvjfqwrcgsmlb");
        assertEquals(7, new Solution().partOne(input));
        assertEquals(19, new Solution().partTwo(input));
    }
    
    @Test
    void example2()
    {
        List<String> input = List.of("bvwbjplbgvbhsrlpgdmjqwftvncz");
        assertEquals(5, new Solution().partOne(input));
        assertEquals(23, new Solution().partTwo(input));
    }
    
    @Test
    void example3()
    {
        List<String> input = List.of("nppdvjthqldpwncqszvftbrmjlhg");
        assertEquals(6, new Solution().partOne(input));
        assertEquals(23, new Solution().partTwo(input));
    }
    
    @Test
    void example4()
    {
        List<String> input = List.of("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg");
        assertEquals(10, new Solution().partOne(input));
        assertEquals(29, new Solution().partTwo(input));
    }
    
    @Test
    void example5()
    {
        List<String> input = List.of("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw");
        assertEquals(11, new Solution().partOne(input));
        assertEquals(26, new Solution().partTwo(input));
    }
}
