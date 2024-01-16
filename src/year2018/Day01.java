package year2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * --- Day 1: Chronal Calibration ---
 */
public class Day01
{
    public static void main(String[] args) throws IOException
    {
        List<String> lines = Files.readAllLines(Path.of("sample.txt"));

        partOne(lines);
        partTwo(lines);
    }

    private static void partOne(List<String> lines)
    {
        int total = 0;

        for (String line : lines)
        {
            char sign = line.charAt(0);
            int value = Integer.parseInt(line.substring(1));

            if (sign == '-')
            {
                value *= -1;
            }

            total += value;
        }

        System.out.println("The resulting frequency is: " + total);
    }

    private static void partTwo(List<String> lines)
    {
        Set<Integer> seenValues = new HashSet<>();

        int startingValue = 0;
        seenValues.add(startingValue);
        int total = startingValue;

        boolean sameFrequency = false;

        for (String line : lines)
        {
            char sign = line.charAt(0);
            int value = Integer.parseInt(line.substring(1));

            if (sign == '-')
            {
                value *= -1;
            }

            total += value;

            if (seenValues.contains(total))
            {
                sameFrequency = true;
                break;
            }
            else
            {
                seenValues.add(total);
            }
        }

        if (sameFrequency)
        {
            System.out.println("The first frequency reached twice is: " + total);
        }
        else
        {
            System.out.println("No frequencies reached twice!");
        }
    }
}
