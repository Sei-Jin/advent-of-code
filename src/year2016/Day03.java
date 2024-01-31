package year2016;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day03
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));

        partOne(inputLines);
    }


    private static void partOne(List<String> inputLines)
    {
        int possibleTriangles = 0;

        for (String line : inputLines)
        {
            List<Integer> sideLengths = Arrays.stream(line.trim().split("\\s+"))
                    .map(Integer::parseInt)
                    .sorted()
                    .toList();

            if (sideLengths.get(0) + sideLengths.get(1) > sideLengths.get(2))
            {
                possibleTriangles++;
            }
        }

        System.out.println("The number of possible triangles is: " + possibleTriangles);
    }
}
