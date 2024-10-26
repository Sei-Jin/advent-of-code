package advent_of_code.year_2020.__01__report_repair;

import advent_of_code.PuzzleSolver;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 1: Report Repair ---
 */
public class Solution implements PuzzleSolver
{
    @Override
    public Object partOne(List<String> inputLines)
    {
        List<Integer> expenseReport = getExpenseReport(inputLines);
        
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
                    return expenseReport.get(firstEntry) * expenseReport.get(secondEntry);
                }
            }
        }
        
        return null;
    }
    
    
    private static List<Integer> getExpenseReport(List<String> inputLines)
    {
        List<Integer> expenseReport = new ArrayList<>();
        
        for (String line : inputLines)
        {
            expenseReport.add(Integer.parseInt(line));
        }
        return expenseReport;
    }
    
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        List<Integer> expenseReport = getExpenseReport(inputLines);
        
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
                        return expenseReport.get(firstEntry)
                                * expenseReport.get(secondEntry)
                                * expenseReport.get(thirdEntry);
                    }
                }
            }
        }
        
        return null;
    }
}
