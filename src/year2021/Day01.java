package year2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 1: Sonar Sweep ---
 */
public class Day01 {
    
    public static void main(String[] args) throws IOException {
        
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        List<Integer> sonarSweepReport = new ArrayList<>();
        
        for (String line : inputLines) {
            sonarSweepReport.add(Integer.parseInt(line));
        }
        
        partOne(sonarSweepReport);
    }
    
    
    private static void partOne(List<Integer> sonarSweepReport) {
        
        int totalMeasurementIncreases = 0;
        
        int previousDepth = sonarSweepReport.getFirst();
        
        for (int depth : sonarSweepReport.subList(1, sonarSweepReport.size())) {
            
            if (previousDepth < depth) {
                totalMeasurementIncreases++;
            }
            
            previousDepth = depth;
        }
        
        System.out.println("The number of measurements that are larger than the previous measurement is: "
                + totalMeasurementIncreases);
    }
}
