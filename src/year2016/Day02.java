package year2016;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * --- Day 2: Bathroom Security ---
 */
public class Day02 {
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
    }
    
    private static void partOne(List<String> inputLines)
    {
        StringBuilder bathroomCode = new StringBuilder();

        int[][] keypad = { {1, 4, 7}, {2, 5, 8}, {3, 6, 9} };

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

            bathroomCode.append(keypad[position.x][position.y]);
        }

        System.out.println("The bathroom code is: " + bathroomCode);
    }
}
