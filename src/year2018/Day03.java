package year2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day03 {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));

        partOne(inputLines);
    }


    private static void partOne(List<String> inputLines) {
        int[][] fabricArea = new int[1000][1000];

        for (String line : inputLines) {
            int[] lineValues = Arrays.stream(line.split("[^0-9]+"))
                    .skip(1)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int claimID = lineValues[0];
            int distanceLeft = lineValues[1];
            int distanceTop = lineValues[2];
            int width = lineValues[3];
            int height = lineValues[4];

            for (int row = distanceTop; row < distanceTop + height; row++) {
                for (int column = distanceLeft; column < distanceLeft + width; column++) {
                    fabricArea[row][column]++;
                }
            }
        }

        int withinTwoClaims = 0;

        for (int row = 0; row < fabricArea.length; row++) {
            for (int column = 0; column < fabricArea.length; column++) {
                if (fabricArea[row][column] > 1) {
                    withinTwoClaims++;
                }
            }
        }

        System.out.println("The number of square inches of fabric within two claims is: " + withinTwoClaims);
    }
}
