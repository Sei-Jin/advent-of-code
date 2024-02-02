package year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * --- Day 3: Rucksack Reorganization ---
 */
public class Day03
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
    }
    
    
    private static void partOne(List<String> inputLines)
    {
        int totalItemPriority = 0;
        
        for (String line : inputLines)
        {
            int midPoint = line.length() / 2;
            
            List<Character> firstHalf = line.substring(0, midPoint)
                    .chars()
                    .mapToObj(c -> (char) c)
                    .toList();
            
            List<Character> secondHalf = line.substring(midPoint)
                    .chars()
                    .mapToObj(c -> (char) c)
                    .toList();;
            
            char sharedItem = findSharedItem(firstHalf, secondHalf);
            
            int itemPriority = calculateItemPriority(sharedItem);
            
            totalItemPriority += itemPriority;
        }
        
        System.out.println("The sum of the priorities is: " + totalItemPriority);
    }
    
    
    private static char findSharedItem(List<Character> firstHalf, List<Character> secondHalf)
    {
        char sharedItem = ' ';
        
        for (char firstHalfItem : firstHalf)
        {
            for (char secondHalfItem : secondHalf)
            {
                if (firstHalfItem == secondHalfItem)
                {
                    sharedItem = firstHalfItem;
                    break;
                }
            }
        }
        
        return sharedItem;
    }
    
    
    private static int calculateItemPriority(char sharedItem)
    {
        int itemPriority = 0;
        
        if (sharedItem >= 'a' && sharedItem <= 'z')
        {
            itemPriority = sharedItem - 96;
        }
        else if (sharedItem >= 'A' && sharedItem <= 'Z')
        {
            itemPriority = sharedItem - 38;
        }
        
        return itemPriority;
    }
}
