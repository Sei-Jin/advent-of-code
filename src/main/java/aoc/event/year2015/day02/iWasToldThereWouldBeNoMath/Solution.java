package aoc.event.year2015.day02.iWasToldThereWouldBeNoMath;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution implements Solver {
    
    private final List<Box> boxes;
    
    public Solution(String input) {
        boxes = parse(input);
    }
    
    private static List<Box> parse(String input) {
        final var boxes = new ArrayList<Box>();
        
        for (final var line : input.lines().toList()) {
            final var dimensions = Arrays.stream(line.split("x"))
                .mapToInt(Integer::parseInt)
                .toArray();
            
            final var length = dimensions[0];
            final var width = dimensions[1];
            final var height = dimensions[2];
            
            boxes.add(new Box(length, width, height));
        }
        
        return boxes;
    }
    
    /// @return the total square feet of wrapping paper the elves should order.
    @Override
    public Integer partOne() {
        var totalArea = 0;
        
        for (final var box : boxes) {
            final var area1 = box.length * box.width;
            final var area2 = box.width * box.height;
            final var area3 = box.height * box.length;
            
            final var minArea = Integer.min(Integer.min(area1, area2), area3);
            final var presentArea = 2 * (area1 + area2 + area3) + minArea;
            
            totalArea += presentArea;
        }
        
        return totalArea;
    }
    
    /// @return the total length of ribbon in feet the elves should order.
    @Override
    public Integer partTwo() {
        var totalRibbonLength = 0;
        
        for (final var box : boxes) {
            final var sideLengths = new ArrayList<Integer>();
            
            sideLengths.add(box.length);
            sideLengths.add(box.width);
            sideLengths.add(box.height);
            
            Collections.sort(sideLengths);
            
            final var wrapLength = 2 * (sideLengths.get(0) + sideLengths.get(1));
            final var bowLength = box.length * box.width * box.height;
            
            totalRibbonLength += wrapLength + bowLength;
        }
        
        return totalRibbonLength;
    }
    
    private record Box(int length, int width, int height) {}
    
    public static void main(String[] args) {
        Runner.runAndPrint(2015, 2);
    }
}
