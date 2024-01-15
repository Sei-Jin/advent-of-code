package year2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * --- Day 1: Chronal Calibration ---
 */
public class Day01
{
    public static void main(String[] args) throws IOException
    {
        List<String> lines = Files.readAllLines(Path.of("input.txt"));

        partOne(lines);
    }

    private static void partOne(List<String> lines)
    {
        int total = 0;

        for (String line : lines)
        {
            char sign = line.charAt(0);
            int value = Integer.parseInt(line.substring(1));

            if (sign == '+')
            {
                total += value;
            }
            else
            {
                total -= value;
            }
        }

        System.out.println(total);
    }
}
