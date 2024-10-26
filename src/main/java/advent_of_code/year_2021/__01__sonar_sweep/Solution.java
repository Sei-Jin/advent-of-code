package advent_of_code.year_2021.__01__sonar_sweep;

import advent_of_code.PuzzleSolver;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 1: Sonar Sweep ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the number of measurements that are larger than their previous measurement.
     */
    @Override
    public Object partOne(List<String> inputLines)
    {
        List<Integer> sonarSweepReport = getSonarSweepReport(inputLines);
        
        int totalMeasurementIncreases = 0;
        
        int previousDepth = sonarSweepReport.getFirst();
        
        for (int currentDepth : sonarSweepReport.subList(1, sonarSweepReport.size()))
        {
            if (previousDepth < currentDepth)
            {
                totalMeasurementIncreases++;
            }
            
            previousDepth = currentDepth;
        }
        
        return totalMeasurementIncreases;
    }
    
    
    private static List<Integer> getSonarSweepReport(List<String> inputLines)
    {
        List<Integer> sonarSweepReport = new ArrayList<>();
        
        for (String line : inputLines)
        {
            sonarSweepReport.add(Integer.parseInt(line));
        }
        return sonarSweepReport;
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the number of three-measurement sliding windows that are larger than their previous
     * three-measurement sliding windows.
     */
    @Override
    public Object partTwo(List<String> inputLines)
    {
        List<Integer> sonarSweepReport = getSonarSweepReport(inputLines);
        
        int totalThreeMeasurementWindowIncreases = 0;
        
        int previousDepthWindow = sonarSweepReport.get(0)
                + sonarSweepReport.get(1)
                + sonarSweepReport.get(2);
        
        for (int depthIndex = 1; depthIndex < sonarSweepReport.size() - 2; depthIndex++)
        {
            int currentDepthWindow = sonarSweepReport.get(depthIndex)
                    + sonarSweepReport.get(depthIndex + 1)
                    + sonarSweepReport.get(depthIndex + 2);
            
            if (previousDepthWindow < currentDepthWindow)
            {
                totalThreeMeasurementWindowIncreases++;
            }
            
            previousDepthWindow = currentDepthWindow;
        }
        
        return totalThreeMeasurementWindowIncreases;
    }
}
