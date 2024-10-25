package advent_of_code.year_2020.__01__report_repair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 1: Report Repair ---
 */
public class Day01
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        List<Integer> expenseReport = new ArrayList<>();
        
        for (String line : inputLines)
        {
            expenseReport.add(Integer.parseInt(line));
        }
        
        partOne(expenseReport);
        partTwo(expenseReport);
    }
    
    
    private static void partOne(List<Integer> expenseReport)
    {
        for (int firstEntry = 0; firstEntry < expenseReport.size(); firstEntry++)
        {
            for (int secondEntry = 0; secondEntry < expenseReport.size(); secondEntry++)
            {
                if (firstEntry == secondEntry)
                {
                    continue;
                }
                
                int sum = expenseReport.get(firstEntry) + expenseReport.get(secondEntry);
                
                if (sum == 2020)
                {
                    int product = expenseReport.get(firstEntry) * expenseReport.get(secondEntry);
                    
                    System.out.println(product);
                    return;
                }
            }
        }
    }
    
    
    private static void partTwo(List<Integer> expenseReport)
    {
        for (int firstEntry = 0; firstEntry < expenseReport.size(); firstEntry++)
        {
            for (int secondEntry = 0; secondEntry < expenseReport.size(); secondEntry++)
            {
                for (int thirdEntry = 0; thirdEntry < expenseReport.size(); thirdEntry++)
                {
                    if (firstEntry == secondEntry || secondEntry == thirdEntry || thirdEntry == firstEntry)
                    {
                        continue;
                    }
                    
                    int sum = expenseReport.get(firstEntry)
                            + expenseReport.get(secondEntry)
                            + expenseReport.get(thirdEntry);
                    
                    if (sum == 2020)
                    {
                        int product = expenseReport.get(firstEntry)
                                * expenseReport.get(secondEntry)
                                * expenseReport.get(thirdEntry);
                        
                        System.out.println(product);
                        return;
                    }
                }
            }
        }
    }
}
