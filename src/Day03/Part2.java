package Day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * --- Day 3: Gear Ratios ---
 */
public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {

        File inFile = new File("sample.txt");
        Scanner in = new Scanner(inFile);

        char[][] schematic = new char[200][];
        int lineCount = 0;

        while (in.hasNextLine()) {
            schematic[lineCount] = in.nextLine().toCharArray();
            lineCount++;
        }

        int partNumberSum = 0;

        StringBuilder partNumber = new StringBuilder();

        for (int row = 0; row < lineCount; row++) {
            for (int column = 0; column < schematic[0].length; column++) {

                boolean validPartNumber = false;

                // If we are on a new line, delete the stored part number
                if (column == 0) {
                    partNumber.delete(0, partNumber.length());
                }

                if (Character.isDigit(schematic[row][column])) {
                    partNumber.append(schematic[row][column]);
                } else {
                    partNumber.delete(0, partNumber.length());
                    continue;
                }

                boolean endOfPartNumber = column + 1 < schematic[0].length && Character.isDigit(schematic[row][column + 1]);
                boolean endOfRow = column == schematic[0].length - 1;

                // If we have not reached the end of the number or the end of the row
                if (endOfPartNumber || endOfRow) {
                    continue;
                }

                int size = partNumber.length();

                // Set the left bound
                int leftBound = column - size;
                if (leftBound < 0) {
                    leftBound = column - size + 1;
                }

                // Set the right bound
                int rightBound = column + 1;
                if (rightBound >= schematic[0].length) {
                    rightBound = column;
                }

                // Set the top bound
                int topBound = row - 1;
                if (topBound < 0) {
                    topBound = row;
                }

                // Set the bottom bound
                int bottomBound = row + 1;
                if (bottomBound >= schematic[0].length) {
                    bottomBound = row;
                }

                // Loop over the bounding box checking for symbols
                for (int boundedRow = topBound; boundedRow <= bottomBound; boundedRow++) {
                    for (int boundedColumn = leftBound; boundedColumn <= rightBound; boundedColumn++) {
                        if (!validPartNumber) {
                            validPartNumber = partValidation(schematic[boundedRow][boundedColumn]);
                        }
                    }
                }


                if (validPartNumber) {
                    partNumberSum += Integer.parseInt(partNumber.toString());
                }
            }
        }

        System.out.println(partNumberSum);
    }

    /**
     * If the given character is not a '.' or a digit, it is a symbol. If the character is a symbol then the
     * part is valid.
     * @param character can be any character
     * @return true if the character is a symbol
     */
    static boolean partValidation(char character) {
        return character != '.' && !Character.isDigit(character);
    }
}
