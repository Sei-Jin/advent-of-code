package aoc.event.year2021;

import aoc.DeprecatedSolver;

import java.util.ArrayList;
import java.util.List;

/// # [2021-03: Binary Diagnostic](https://adventofcode.com/2021/day/3)
public class Day03 implements DeprecatedSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the power consumption of the submarine.
     */
    @Override
    public Object partOne(List<String> inputLines)
    {
        List<Integer> bitCounts = getBitCounts(inputLines);
        
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
        
        return gammaRate * epsilonRate;
    }
    
    
    private static List<Integer> getBitCounts(List<String> inputLines)
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
        
        return bitCounts;
    }
    
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        return null;
    }
}
