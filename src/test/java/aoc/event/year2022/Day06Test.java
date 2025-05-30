package aoc.event.year2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {
    
    @Test
    void example1() {
        var input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
        var solution = new Day06(input);
        assertEquals(7, solution.partOne());
        assertEquals(19, solution.partTwo());
    }
    
    @Test
    void example2() {
        var input = "bvwbjplbgvbhsrlpgdmjqwftvncz";
        var solution = new Day06(input);
        assertEquals(5, solution.partOne());
        assertEquals(23, solution.partTwo());
    }
    
    @Test
    void example3() {
        var input = "nppdvjthqldpwncqszvftbrmjlhg";
        var solution = new Day06(input);
        assertEquals(6, solution.partOne());
        assertEquals(23, solution.partTwo());
    }
    
    @Test
    void example4() {
        var input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
        var solution = new Day06(input);
        assertEquals(10, solution.partOne());
        assertEquals(29, solution.partTwo());
    }
    
    @Test
    void example5() {
        var input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";
        var solution = new Day06(input);
        assertEquals(11, solution.partOne());
        assertEquals(26, solution.partTwo());
    }
}
