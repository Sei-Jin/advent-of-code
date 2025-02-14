package aoc.event.year2024.day07.bridgeRepair;

import aoc.DeprecatedSolver;

import java.util.Arrays;
import java.util.List;

public class Solution implements DeprecatedSolver
{
    @Override
    public Object partOne(List<String> inputLines)
    {
        int totalCalibrationResult = 0;
        
        for (String line : inputLines)
        {
            List<Integer> values = Arrays.stream(line.split(": | "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();
            
            int testValue =  values.getFirst();
            List<Integer> equationValues = values.subList(1, values.size());
        }
        
        return totalCalibrationResult;
    }
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        return null;
    }
}
