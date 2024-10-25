package advent_of_code.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * --- Day 3: Rucksack Reorganization ---
 */
public class Day03
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
        partTwo(inputLines);
    }
    
    
    private static void partOne(List<String> inputLines)
    {
        int totalItemPriority = 0;
        
        for (String line : inputLines)
        {
            int midPoint = line.length() / 2;
            
            Set<Character> firstHalf = line.substring(0, midPoint)
                    .chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toSet());
            
            Set<Character> secondHalf = line.substring(midPoint)
                    .chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toSet());
            
            char sharedItem = findSharedItem(firstHalf, secondHalf).getFirst();
            
            int itemPriority = calculateItemPriority(sharedItem);
            
            totalItemPriority += itemPriority;
        }
        
        System.out.println("The sum of the priorities is: " + totalItemPriority);
    }
    
    
    private static void partTwo(List<String> inputLines)
    {
        int totalItemPriority = 0;
        
        List<Set<Character>> group = new ArrayList<>();
        
        for (String line : inputLines)
        {
            Set<Character> rucksack = line.chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toSet());
            
            group.add(rucksack);
            
            if (group.size() < 3)
            {
                continue;
            }
            
            char sharedItem = findBadge(group).getFirst();
            
            int itemPriority = calculateItemPriority(sharedItem);
            
            totalItemPriority += itemPriority;
            
            group.clear();
        }
        
        System.out.println("The sum of the priorities of the badges is: " + totalItemPriority);
    }
    
    
    private static List<Character> findSharedItem(Set<Character> firstSet, Set<Character> secondSet)
    {
        firstSet.retainAll(secondSet);
        
        return firstSet.stream().toList();
    }
    
    
    private static List<Character> findBadge(List<Set<Character>> group)
    {
        Set<Character> firstGroup = group.getFirst();
        Set<Character> secondGroup = group.get(1);
        Set<Character> thirdGroup = group.get(2);
        
        firstGroup.retainAll(secondGroup);
        firstGroup.retainAll(thirdGroup);
        
        return firstGroup.stream().toList();
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
