package aoc.event.year2015;

import aoc.Solver;

import java.util.List;

/// # [2015-02: I Was Told There Would Be No Math](https://adventofcode.com/2015/day/2)
public class Day02 implements Solver<Integer, Integer> {
    
    private final List<Box> boxes;
    
    public Day02(String input) {
        boxes = parse(input);
    }
    
    private static List<Box> parse(String input) {
        return input
            .lines()
            .map(line -> {
                var parts = line.split("x");
                
                var length = Integer.parseInt(parts[0]);
                var width = Integer.parseInt(parts[1]);
                var height = Integer.parseInt(parts[2]);
                
                return new Box(length, width, height);
            })
            .toList();
    }
    
    /// @return the total area of wrapping paper the elves should order.
    @Override
    public Integer partOne() {
        return boxes
            .stream()
            .mapToInt(box -> {
                var area1 = box.length * box.width;
                var area2 = box.width * box.height;
                var area3 = box.height * box.length;
                
                var minArea = Integer.min(Integer.min(area1, area2), area3);
                
                return 2 * (area1 + area2 + area3) + minArea;
            })
            .sum();
    }
    
    /// @return the total length of ribbon the elves should order.
    @Override
    public Integer partTwo() {
        return boxes
            .stream()
            .mapToInt(box -> {
                var minLengthHeight = Integer.min(box.length, box.height);
                var min = Integer.min(minLengthHeight, box.width);
                var mid = Integer.max(minLengthHeight, box.width);
                
                var wrapLength = 2 * (min + mid);
                var bowLength = box.length * box.width * box.height;
                
                return wrapLength + bowLength;
            })
            .sum();
    }
    
    private record Box(int length, int width, int height) {}
}
