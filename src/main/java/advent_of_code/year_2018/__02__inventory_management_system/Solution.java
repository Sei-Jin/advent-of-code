package advent_of_code.year_2018.__02__inventory_management_system;

import advent_of_code.PuzzleSolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 2: Inventory Management System ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the checksum for the list of box IDs.
     */
    @Override
    public Object partOne(List<String> inputLines)
    {
        int twoCount = 0;
        int threeCount = 0;
        
        for (String line : inputLines)
        {
            HashMap<Character, Integer> letterCount = new HashMap<>();
            
            for (int i = 0; i < line.length(); i++)
            {
                char ch = line.charAt(i);
                
                int count;
                if (letterCount.containsKey(ch))
                {
                    count = letterCount.get(ch) + 1;
                }
                else
                {
                    count = 1;
                }
                letterCount.put(ch, count);
            }
            
            boolean foundPair = false;
            boolean foundTriplet = false;
            
            for (Map.Entry<Character, Integer> entry : letterCount.entrySet())
            {
                int count = entry.getValue();
                
                if (count == 2 && !foundPair)
                {
                    twoCount++;
                    foundPair = true;
                }
                if (count == 3 && !foundTriplet)
                {
                    threeCount++;
                    foundTriplet = true;
                }
            }
        }
        
        return twoCount * threeCount;
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the letters common between the two correct box IDs.
     */
    @Override
    public Object partTwo(List<String> inputLines)
    {
        String commonID = "";
        
        for (String line : inputLines)
        {
            for (String compareLine : inputLines)
            {
                int differentCharacters = 0;
                
                for (int i = 0; i < line.length(); i++)
                {
                    if (line.charAt(i) != compareLine.charAt(i))
                    {
                        differentCharacters++;
                    }
                }
                
                if (differentCharacters == 1)
                {
                    int removeIndex = 0;
                    
                    for (int i = 0; i < line.length(); i++)
                    {
                        if (line.charAt(i) != compareLine.charAt(i))
                        {
                            removeIndex = i;
                        }
                    }
                    
                    commonID = line.substring(0, removeIndex) + line.substring(removeIndex + 1);
                }
            }
        }
        
        return commonID;
    }
}
