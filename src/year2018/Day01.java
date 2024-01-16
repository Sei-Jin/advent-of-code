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
public class Day01 {

    public static void main(String[] args) throws IOException {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));

        partOne(inputLines);
        partTwo(inputLines);
    }


    private static void partOne(List<String> inputLines) {
        int currentFrequency = 0;

        for (String line : inputLines) {
            char sign = line.charAt(0);
            int frequencyChange = Integer.parseInt(line.substring(1));

            if (sign == '-') {
                frequencyChange *= -1;
            }

            currentFrequency += frequencyChange;
        }

        System.out.println("The resulting frequency is: " + currentFrequency);
    }


    private static void partTwo(List<String> inputLines) {
        int startingFrequency = 0;

        int duplicateFrequency = findDuplicateFrequency(inputLines, startingFrequency);

        System.out.println("The first frequency reached twice is: " + duplicateFrequency);
    }


    private static int findDuplicateFrequency(List<String> inputLines, int startingFrequency) {
        Set<Integer> previousFrequencies = new HashSet<>();

        previousFrequencies.add(startingFrequency);
        int currentFrequency = startingFrequency;

        boolean foundDuplicate = false;

        while (!foundDuplicate) {
            for (String line : inputLines) {
                char sign = line.charAt(0);
                int frequencyChange = Integer.parseInt(line.substring(1));

                if (sign == '-') {
                    frequencyChange *= -1;
                }

                currentFrequency += frequencyChange;

                if (previousFrequencies.contains(currentFrequency)) {
                    foundDuplicate = true;
                    break;
                } else {
                    previousFrequencies.add(currentFrequency);
                }
            }
        }

        return currentFrequency;
    }
}
