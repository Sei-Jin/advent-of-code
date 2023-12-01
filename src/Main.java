import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.Character.isDigit;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        File inFile = new File("input.txt");
        Scanner in = new Scanner(inFile);

        int calibrationTotal = 0;

        String line = "";
        while (in.hasNext()) {
            line = in.next();

            int firstDigit = 0, lastDigit = 0;

            // Scan over the string looking for the first and last digit
            for (int i = 0; i < line.length(); i++) {
                if (isDigit(line.charAt(i))) {
                    firstDigit = Character.getNumericValue(line.charAt(i));
                    break;
                }
            }

            for (int i = line.length() - 1; i >= 0; i--) {
                if (isDigit(line.charAt(i))) {
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