package year2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 1: Sonar Sweep ---
 */
public class Day01
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        List<Integer> sonarSweepReport = new ArrayList<>();
        
        for (String line : inputLines)
        {
            sonarSweepReport.add(Integer.parseInt(line));
        }
        
        partOne(sonarSweepReport);
        partTwo(sonarSweepReport);
    }
    
    
    private static void partOne(List<Integer> sonarSweepReport)
    {
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
        
        System.out.println("The number of measurements that are larger than their previous measurement is: "
                + totalMeasurementIncreases);
    }
    
    
    private static void partTwo(List<Integer> sonarSweepReport)
    {
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
        
        System.out.println("The number of three-measurement sliding windows that are larger than their previous " +
                "three-measurement sliding window is: " + totalThreeMeasurementWindowIncreases);
    }
}
