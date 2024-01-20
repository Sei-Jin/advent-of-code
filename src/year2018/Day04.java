package year2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * --- Day 4: Repose Record ---
 */
public class Day04 {
    
    public static void main(String[] args) throws IOException {
        List<String> inputLines = Files.readAllLines(Path.of("sample.txt"));
        
        for (String line : inputLines) {
            int[] values = Arrays.stream(line.split("[^0-9]+"))
                    .skip(1)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            
            int year = values[0];
            int month = values[1];
            int day = values[2];
            int hour = values[3];
            int minute = values[4];
            
            Timestamp newTime = new Timestamp(year, month, day, hour, minute);
            
            if (line.contains("Guard")) {
                int guardID = values[5];
                
                
            }
        }
    }
    
    private record Timestamp (int year, int month, int day, int hour, int minute) {}
    
    
    
}
