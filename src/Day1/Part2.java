package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {

        File inFile = new File("input.txt");
        Scanner in = new Scanner(inFile);

        String[] digits = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

        // Create a hashmap of the spelled out digits and their numerical values
        HashMap<String, Integer> digitsMap = new HashMap<>();
        for (int j = 0; j < digits.length; j++) {
            digitsMap.put(digits[j], j + 1);
        }

        int calibrationTotal = 0;

        // Read in each line one at a time
        while (in.hasNext()) {
            String line = in.next();

            int firstDigit = 0, lastDigit = 0;

            // Scan over the string looking for the first digit
            for (int i = 0; i < line.length(); i++) {
                if (Character.isDigit(line.charAt(i))) {
                    firstDigit = Character.getNumericValue(line.charAt(i));
                    break;
                }

                // From the current index, compare the next 3, 4, and 5 length strings to the digits map
                for (int j = 3; j <= 5; j++) {
                    StringBuilder letters = new StringBuilder();

                    for (int k = i; k < line.length() && k < i + j; k++) {
                        letters.append(line.charAt(k));
                    }

                    if (digitsMap.containsKey(letters.toString())) {
                        firstDigit = digitsMap.get(letters.toString());
                    }
                }

                // Break out of the loop if we found a matching digit
                if (firstDigit != 0) {
                    break;
                }
            }

            // Scan over the string looking for the last digit
            for (int i = line.length() - 1; i >= 0; i--) {
                if (Character.isDigit(line.charAt(i))) {
                    lastDigit = Character.getNumericValue(line.charAt(i));
                    break;
                }

                // From the current index, compare the next 3, 4, and 5 length strings to the digits map
                for (int j = 3; j <= 5; j++) {
                    StringBuilder letters = new StringBuilder();

                    for (int k = i - j + 1; k >= 0 && k <= i; k++) {
                        letters.append(line.charAt(k));
                    }

                    if (digitsMap.containsKey(letters.toString())) {
                        lastDigit = digitsMap.get(letters.toString());
                    }
                }

                // Break out of the loop if we found a matching digit
                if (lastDigit != 0) {
                    break;
                }
            }

            int calibrationValue = firstDigit * 10 + lastDigit;
            calibrationTotal += calibrationValue;
        }

        System.out.println(calibrationTotal);
    }
}