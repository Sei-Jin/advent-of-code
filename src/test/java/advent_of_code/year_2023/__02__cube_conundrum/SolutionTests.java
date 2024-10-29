package advent_of_code.year_2023.__02__cube_conundrum;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void exampleGame1()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");
        assertEquals(1, new Solution().partOne(inputLines));
        assertEquals(48, new Solution().partTwo(inputLines));
    }
    
    @Test
    void exampleGame2()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue");
        assertEquals(2, new Solution().partOne(inputLines));
        assertEquals(12, new Solution().partTwo(inputLines));
    }
    
    @Test
    void exampleGame3()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red");
        assertEquals(0, new Solution().partOne(inputLines));
        assertEquals(1560, new Solution().partTwo(inputLines));
    }
    
    @Test
    void exampleGame4()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red");
        assertEquals(0, new Solution().partOne(inputLines));
        assertEquals(630, new Solution().partTwo(inputLines));
    }
    
    @Test
    void exampleGame5()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");
        assertEquals(5, new Solution().partOne(inputLines));
        assertEquals(36, new Solution().partTwo(inputLines));
    }
}
