package year2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * --- Day 3: Binary Diagnostic ---
 */
public class Day03 {
    
    public static void main(String[] args) throws IOException {
    
        List<String> inputLines = Files.readAllLines(Path.of("sample.txt"));
        
        partOne(inputLines);
    }
    
    
    private static void partOne(List<String> inputLines) {
        
        int[] bitCount = new int[inputLines.getFirst().length()];
        
        for (String line : inputLines) {
            
            int[] binaryString = line.chars()
                    .map(Character::getNumericValue)
                    .toArray();
            
            for (int index = 0; index < binaryString.length; index++) {
                bitCount[index] += binaryString[index];
            }
        }
        
        int[] binaryGammaRate = new int[inputLines.getFirst().length()];
        int[] binaryEpsilonRate = new int[inputLines.getFirst().length()];
        
        for (int index = 0; index < bitCount.length; index++) {
            if (bitCount[index] / (bitCount.length / 2) > 1) {
                binaryGammaRate[index]++;
            } else {
                binaryEpsilonRate[index]++;
            }
        }
        
        System.out.println(Arrays.toString(binaryGammaRate));
        System.out.println(Arrays.toString(binaryEpsilonRate));
    }
}
