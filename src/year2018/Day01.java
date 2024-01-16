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
        List<String> lines = Files.readAllLines(Path.of("input.txt"));

        partOne(lines);
        partTwo(lines);
    }

    private static void partOne(List<String> lines) {
        int total = 0;

        for (String line : lines) {
            char sign = line.charAt(0);
            int value = Integer.parseInt(line.substring(1));

            if (sign == '-') {
                value *= -1;
            }

            total += value;
        }

        System.out.println("The resulting frequency is: " + total);
    }

    private static void partTwo(List<String> lines) {
        Set<Integer> seenValues = new HashSet<>();

        int startingValue = 0;
        seenValues.add(startingValue);

        boolean sameFrequency = false;

        Result result = getResult(lines, sameFrequency, startingValue, seenValues);

        if (result.sameFrequency()) {
            System.out.println("The first frequency reached twice is: " + result.total());
        } else {
            System.out.println("No frequencies reached twice!");
        }
    }

    private static Result getResult(List<String> lines, boolean sameFrequency, int startingValue, Set<Integer> seenValues) {
        int total = startingValue;

        while (!sameFrequency) {
            for (String line : lines) {
                char sign = line.charAt(0);
                int value = Integer.parseInt(line.substring(1));

                if (sign == '-') {
                    value *= -1;
                }

                total += value;

                if (seenValues.contains(total)) {
                    sameFrequency = true;
                    break;
                } else {
                    seenValues.add(total);
                }
            }
        }

        return new Result(total, sameFrequency);
    }

    private record Result(int total, boolean sameFrequency) {
    }
}
