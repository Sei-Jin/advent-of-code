package year2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 2: Inventory Management System ---
 */
public class Day02 {

    public static void main(String[] args) throws IOException {
        
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));

        partOne(inputLines);
        partTwo(inputLines);
    }


    private static void partOne(List<String> inputLines) {
        
        int twoCount = 0;
        int threeCount = 0;

        for (String line : inputLines) {
            HashMap<Character, Integer> letterCount = new HashMap<>();

            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);

                int count;
                if (letterCount.containsKey(ch)) {
                    count = letterCount.get(ch) + 1;
                } else {
                    count = 1;
                }
                letterCount.put(ch, count);
            }

            boolean foundPair = false;
            boolean foundTriplet = false;

            for (Map.Entry<Character, Integer> entry : letterCount.entrySet()) {
                int count = entry.getValue();

                if (count == 2 && !foundPair) {
                    twoCount++;
                    foundPair = true;
                }
                if (count == 3 && !foundTriplet) {
                    threeCount++;
                    foundTriplet = true;
                }
            }
        }

        int checksum = twoCount * threeCount;

        System.out.println("The checksum for the list of box IDs is: " + checksum);
    }


    private static void partTwo(List<String> inputLines) {
        
        String commonID = "";

        for (String line : inputLines) {
            for (String compareLine : inputLines) {
                int differentCharacters = 0;

                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != compareLine.charAt(i)) {
                        differentCharacters++;
                    }
                }

                if (differentCharacters == 1) {
                    int removeIndex = 0;

                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) != compareLine.charAt(i)) {
                            removeIndex = i;
                        }
                    }

                    commonID = line.substring(0, removeIndex) + line.substring(removeIndex + 1);
                }
            }
        }

        System.out.println("The letters common between the two correct box IDs are: " + commonID);
    }
}
