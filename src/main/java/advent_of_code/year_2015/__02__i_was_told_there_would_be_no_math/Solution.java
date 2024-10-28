package advent_of_code.year_2015.__02__i_was_told_there_would_be_no_math;

import advent_of_code.PuzzleSolver;

import java.util.*;

/**
 * --- Day 2: I Was Told There Would Be No Math ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the total square feet of wrapping paper the elves should order.
     */
    @Override
    public Object partOne(List<String> inputLines)
    {
        int totalArea = 0;
        
        for (String line : inputLines)
        {
            Box box = getBoxDimensions(line);
            
            int area1 = box.length * box.width;
            int area2 = box.width * box.height;
            int area3 = box.height * box.length;
            
            int minArea = Integer.min(Integer.min(area1, area2), area3);
            int presentArea = 2 * (area1 + area2 + area3) + minArea;
            
            totalArea += presentArea;
        }
        
        return totalArea;
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the total length of ribbon in feet the elves should order.
     */
    @Override
    public Object partTwo(List<String> inputLines)
    {
        int totalRibbonLength = 0;
        
        for (String line : inputLines)
        {
            Box box = getBoxDimensions(line);
            
            List<Integer> sideLengths = new ArrayList<>();
            
            sideLengths.add(box.length);
            sideLengths.add(box.width);
            sideLengths.add(box.height);
            
            Collections.sort(sideLengths);
            
            int wrapLength = 2 * (sideLengths.get(0) + sideLengths.get(1));
            int bowLength = box.length * box.width * box.height;
            
            totalRibbonLength += wrapLength + bowLength;
        }
        
        return totalRibbonLength;
    }
    
    
    private static Box getBoxDimensions(String line)
    {
        int[] dimensions = Arrays.stream(line.split("x"))
                .mapToInt(Integer::parseInt)
                .toArray();
        
        int length = dimensions[0];
        int width = dimensions[1];
        int height = dimensions[2];
        
        return new Box(length, width, height);
    }
    
    
    private record Box(int length, int width, int height) {}
}
