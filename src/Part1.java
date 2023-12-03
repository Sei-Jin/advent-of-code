import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {

        File inFile = new File("input.txt");
        Scanner in = new Scanner(inFile);

        int calibrationTotal = 0;

        while (in.hasNext()) {
            String line = in.next();

            int firstDigit = 0, lastDigit = 0;

            // Scan over the string looking for the first digit
            for (int i = 0; i < line.length(); i++) {
                if (Character.isDigit(line.charAt(i))) {
                    firstDigit = Character.getNumericValue(line.charAt(i));
                    break;
                }
            }

            // Scan over the string looking for the last digit
            for (int i = line.length() - 1; i >= 0; i--) {
                if (Character.isDigit(line.charAt(i))) {
                    lastDigit = Character.getNumericValue(line.charAt(i));
                    break;
                }
            }

            int calibrationValue = firstDigit * 10 + lastDigit;
            calibrationTotal += calibrationValue;
        }

        System.out.println(calibrationTotal);
    }
}