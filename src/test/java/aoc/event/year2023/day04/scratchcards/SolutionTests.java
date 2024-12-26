package aoc.event.year2023.day04.scratchcards;

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
        void exampleCard1()
        {
            List<String> inputLines = new ArrayList<>(
                    List.of("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")
            );
            assertEquals(8, new Solution().partOne(inputLines));
        }
        
        @Test
        void exampleCard2()
        {
            List<String> inputLines = new ArrayList<>(
                    List.of("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19")
            );
            assertEquals(2, new Solution().partOne(inputLines));
        }
        
        @Test
        void exampleCard3()
        {
            List<String> inputLines = new ArrayList<>(
                    List.of("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1")
            );
            assertEquals(2, new Solution().partOne(inputLines));
        }
        
        @Test
        void exampleCard4()
        {
            List<String> inputLines = new ArrayList<>(
                    List.of("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83")
            );
            assertEquals(1, new Solution().partOne(inputLines));
        }
        
        @Test
        void exampleCard5()
        {
            List<String> inputLines = new ArrayList<>(
                    List.of("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36")
            );
            assertEquals(0, new Solution().partOne(inputLines));
        }
        
        @Test
        void exampleCard6()
        {
            List<String> inputLines = new ArrayList<>(
                    List.of("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11")
            );
            assertEquals(0, new Solution().partOne(inputLines));
        }
        
        @Test
        void completeExample()
        {
            List<String> inputLines = new ArrayList<>(
                    Arrays.stream("""
                Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
                """.split("\n")).toList()
            );
            assertEquals(13, new Solution().partOne(inputLines));
        }
    }
    
    @Nested
    class PartTwo
    {
        @Test
        void exampleCard1()
        {
            List<String> inputLines = new ArrayList<>(Arrays.stream("""
                Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                """.split("\n")).toList()
            );
            assertEquals(1, new Solution().partTwo(inputLines));
        }
        
        @Test
        void exampleCards1to2()
        {
            List<String> inputLines = new ArrayList<>(Arrays.stream("""
                Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                """.split("\n")).toList()
            );
            assertEquals(3, new Solution().partTwo(inputLines));
        }
        
        @Test
        void exampleCards1to3()
        {
            List<String> inputLines = new ArrayList<>(Arrays.stream("""
                Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                """.split("\n")).toList()
            );
            assertEquals(7, new Solution().partTwo(inputLines));
        }
        
        @Test
        void exampleCards1to4()
        {
            List<String> inputLines = new ArrayList<>(Arrays.stream("""
                Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                """.split("\n")).toList()
            );
            assertEquals(15, new Solution().partTwo(inputLines));
        }
        
        @Test
        void exampleCards1to5()
        {
            List<String> inputLines = new ArrayList<>(Arrays.stream("""
                Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                """.split("\n")).toList()
            );
            assertEquals(29, new Solution().partTwo(inputLines));
        }
        
        @Test
        void exampleCards1to6()
        {
            List<String> inputLines = new ArrayList<>(Arrays.stream("""
                Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
                """.split("\n")).toList()
            );
            assertEquals(30, new Solution().partTwo(inputLines));
        }
    }
}
