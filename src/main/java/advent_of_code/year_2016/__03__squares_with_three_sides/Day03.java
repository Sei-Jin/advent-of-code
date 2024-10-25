package advent_of_code.year_2016.__03__squares_with_three_sides;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day03
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));

        partOne(inputLines);
        partTwo(inputLines);
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

            if (possibleTriangle(sideLengths))
            {
                possibleTriangles++;
            }
        }

        System.out.println("The number of possible triangles is: " + possibleTriangles);
    }


    private static void partTwo(List<String> inputLines)
    {
        int possibleTriangles = 0;

        List<List<Integer>> storedRows = new ArrayList<>();

        int rowCount = 0;

        for (String line : inputLines)
        {
            List<Integer> rowValues = Arrays.stream(line.trim().split("\\s+"))
                    .map(Integer::parseInt)
                    .toList();

            storedRows.add(rowValues);

            rowCount++;

            if (rowCount % 3 == 0)
            {
                List<Integer> sideLengths = new ArrayList<>();

                for (int index = 0; index < storedRows.size(); index++)
                {
                    for (List<Integer> rowVals : storedRows)
                    {
                        sideLengths.add(rowVals.get(index));
                    }

                    Collections.sort(sideLengths);

                    if (possibleTriangle(sideLengths))
                    {
                        possibleTriangles++;
                    }

                    sideLengths.clear();
                }

                storedRows.clear();
            }
        }

        System.out.println("The number of possible triangles is: " + possibleTriangles);
    }


    private static boolean possibleTriangle(List<Integer> sideLengths)
    {
        return sideLengths.get(0) + sideLengths.get(1) > sideLengths.get(2);
    }
}
