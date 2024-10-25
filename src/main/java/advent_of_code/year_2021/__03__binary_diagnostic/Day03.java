package advent_of_code.year_2021.__03__binary_diagnostic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 3: Binary Diagnostic ---
 */
public class Day03
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
    }
    
    
    private static void partOne(List<String> inputLines)
    {
        List<Integer> bitCounts = new ArrayList<>();
        
        for (String line : inputLines)
        {
            List<Integer> bits = line.chars()
                    .map(Character::getNumericValue)
                    .boxed()
                    .toList();
            
            for (int index = 0; index < bits.size(); index++)
            {
                if (bitCounts.size() == index)
                {
                    bitCounts.add(bits.get(index));
                }
                else
                {
                    int totalBitCount = bitCounts.get(index) + bits.get(index);
                    bitCounts.set(index, totalBitCount);
                }
            }
        }
        
        StringBuilder binaryGammaRate = new StringBuilder();
        StringBuilder binaryEpsilonRate = new StringBuilder();
        
        for (Integer bitCount : bitCounts)
        {
            int midPoint = inputLines.size() / 2;
            
            boolean oneIsMostCommon = inputLines.size() - bitCount < midPoint;
            
            if (oneIsMostCommon)
            {
                binaryGammaRate.append(1);
                binaryEpsilonRate.append(0);
            }
            else
            {
                binaryEpsilonRate.append(1);
                binaryGammaRate.append(0);
            }
        }
        
        int epsilonRate = Integer.parseInt(String.valueOf(binaryEpsilonRate), 2);
        int gammaRate = Integer.parseInt(String.valueOf(binaryGammaRate), 2);
        
        int powerConsumption = gammaRate * epsilonRate;
        
        System.out.println("The power consumption of the submarine is: " + powerConsumption);
    }
}
