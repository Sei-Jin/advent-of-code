package advent_of_code.year2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;


/**
 * --- Day 7: Some Assembly Required ---
 */
public class Day07
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
    }
    
    
    private static void partOne(List<String> instructions)
    {
        for (String instruction : instructions)
        {
            List<String> instructionParts = Arrays.stream(instruction.split(" ")).toList();
            
            if (instructionParts.contains("AND"))
        }
    }
}
