package advent_of_code.year_2016.__02__bathroom_security;

import advent_of_code.PuzzleSolver;

import java.awt.*;
import java.util.List;

/**
 * --- Day 2: Bathroom Security ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the bathroom code.
     */
    public Object partOne(List<String> inputLines)
    {
        StringBuilder bathroomCode = new StringBuilder();

        int[][] keypad = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        Point position = new Point(1, 1);

        for (String line : inputLines)
        {
            List<Character> moveSequence = line.chars()
                    .mapToObj(c -> (char) c)
                    .toList();

            for (char move : moveSequence)
            {
                switch (move)
                {
                    case 'U' ->
                    {
                        if (position.y > 0)
                        {
                            position.y--;
                        }
                    }
                    case 'D' ->
                    {
                        if (position.y < keypad.length - 1)
                        {
                            position.y++;
                        }
                    }
                    case 'L' ->
                    {
                        if (position.x > 0)
                        {
                            position.x--;
                        }
                    }
                    case 'R' ->
                    {
                        if (position.x < keypad.length - 1)
                        {
                            position.x++;
                        }
                    }
                }
            }

            bathroomCode.append(keypad[position.y][position.x]);
        }
        
        return bathroomCode;
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the correct bathroom code.
     */
    public Object partTwo(List<String> inputLines)
    {
        StringBuilder bathroomCode = new StringBuilder();

        char[][] keypad = {
                { ' ', ' ', '1', ' ', ' ' },
                { ' ', '2', '3', '4', ' ' },
                { '5', '6', '7', '8', '9' },
                { ' ', 'A', 'B', 'C', ' ' },
                { ' ', ' ', 'D', ' ', ' ' },
        };

        Point position = new Point(0, 2);

        for (String line : inputLines)
        {
            List<Character> moveSequence = line.chars()
                    .mapToObj(c -> (char) c)
                    .toList();

            for (char move : moveSequence)
            {
                switch (move)
                {
                    case 'U' ->
                    {
                        if (position.y > 0 && keypad[position.y - 1][position.x] != ' ')
                        {
                            position.y--;
                        }
                    }
                    case 'D' ->
                    {
                        if (position.y < keypad.length - 1 && keypad[position.y + 1][position.x] != ' ')
                        {
                            position.y++;
                        }
                    }
                    case 'L' ->
                    {
                        if (position.x > 0 && keypad[position.y][position.x - 1] != ' ')
                        {
                            position.x--;
                        }
                    }
                    case 'R' ->
                    {
                        if (position.x < keypad.length - 1  && keypad[position.y][position.x + 1] != ' ')
                        {
                            position.x++;
                        }
                    }
                }
            }

            bathroomCode.append(keypad[position.y][position.x]);
        }
        
        return bathroomCode;
    }
}
