package advent_of_code.year2023.Day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * --- Day 3: Gear Ratios ---
 */
public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {

        File inFile = new File("input.txt");
        Scanner in = new Scanner(inFile);

        char[][] schematic = new char[200][];
        int lineNumber = 0;

        while (in.hasNextLine()) {
            schematic[lineNumber] = in.nextLine().toCharArray();
            lineNumber++;
        }

        int partNumberSum = 0;

        StringBuilder partNumber = new StringBuilder();

        for (int i = 0; i < lineNumber; i++) {
            for (int j = 0; j < schematic[0].length; j++) {

                boolean validPartNumber = false;

                if (j == 0) {
                    partNumber = new StringBuilder();
                }

                if (Character.isDigit(schematic[i][j])) {
                    partNumber.append(schematic[i][j]);
                } else {
                    partNumber = new StringBuilder();
                    continue;
                }

                // Have we reached the end of the number?
                if (j == schematic[0].length - 1 || j + 1 < schematic[0].length && !Character.isDigit(schematic[i][j + 1])) {
                    int size = partNumber.length();

                    // Check left bound
                    int columnLeft = j - size;
                    if (columnLeft < 0) {
                        columnLeft = j - size + 1;
                    }

                    // Check right bound
                    int columnRight = j + 1;
                    if (columnRight >= schematic[0].length) {
                        columnRight = j;
                    }

                    // Check top bound
                    int rowTop = i - 1;
                    if (rowTop < 0) {
                        rowTop = i;
                    }

                    // Check bottom bound
                    int rowBottom = i + 1;
                    if (rowBottom >= schematic[0].length) {
                        rowBottom = i;
                    }

                    // Loop over the bounding box checking for symbols
                    for (int row = rowTop; row <= rowBottom; row++) {
                        for (int column = columnLeft; column <= columnRight; column++) {
                            if (!validPartNumber) {
                                validPartNumber = partValidation(schematic[row][column]);
                            }
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
