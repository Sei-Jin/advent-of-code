package aoc.event.year2021;

import aoc.Solver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/// # [2021-05: Hydrothermal Venture](https://adventofcode.com/2021/day/5)
public class Day05 implements Solver<Integer, Integer> {
    
    private static List<LineSegment> segments;
    
    public Day05(String input) {
        segments = parse(input);
    }
    
    private static List<LineSegment> parse(String input) {
        return input
            .lines()
            .map(line -> {
                var parts = line.split(" -> ");
                var point1 = parsePoint(parts[0]);
                var point2 = parsePoint(parts[1]);
                return new LineSegment(point1, point2);
            })
            .toList();
    }
    
    private static Point parsePoint(String string) {
        var values = string.split(",");
        var x = Integer.parseInt(values[0]);
        var y = Integer.parseInt(values[1]);
        return new Point(x, y);
    }
    
    private static void countHorizontalVertical(LineSegment segment, Map<Point, Integer> counts) {
        var minX = Math.min(segment.start.x, segment.end.x);
        var minY = Math.min(segment.start.y, segment.end.y);
        var maxX = Math.max(segment.start.x, segment.end.x);
        var maxY = Math.max(segment.start.y, segment.end.y);
        
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                var point = new Point(x, y);
                var count = counts.getOrDefault(point, 0) + 1;
                counts.put(point, count);
            }
        }
    }
    
    @Override
    public Integer partOne() {
        var counts = new HashMap<Point, Integer>();
        for (var segment : segments) {
            if (LineSegment.isHorizontal(segment) || LineSegment.isVertical(segment)) {
                countHorizontalVertical(segment, counts);
            }
        }
        return countOverlapping(counts);
    }
    
    @Override
    public Integer partTwo() {
        var counts = new HashMap<Point, Integer>();
        for (var segment : segments) {
            if (LineSegment.isHorizontal(segment) || LineSegment.isVertical(segment)) {
                countHorizontalVertical(segment, counts);
            }
            else if (LineSegment.isDiagonal(segment)) {
                countDiagonal(segment, counts);   
            }
        }
        return countOverlapping(counts);
    }
    
    private static void countDiagonal(LineSegment segment, Map<Point, Integer> counts) {
        var isDescendingX = segment.start.x > segment.end.x;
        var isDescendingY = segment.start.y > segment.end.y;
        
        var incrementX = isDescendingX ? -1 : 1;
        var incrementY = isDescendingY ? -1 : 1;
        
        var horizontalLength = Math.abs(segment.start.x - segment.end.x);
        var x = segment.start.x;
        var y = segment.start.y;
        
        for (int i = 0; i <= horizontalLength; i++) {
            var position = new Point(x, y);
            var count = counts.getOrDefault(position, 0) + 1;
            counts.put(position, count);
            x += incrementX;
            y += incrementY;
        }
    }
    
    private static int countOverlapping(HashMap<Point, Integer> positionCounts) {
        return (int) positionCounts
            .values()
            .stream()
            .filter(value -> value >= 2)
            .count();
    }
    
    private record Point(int x, int y) {
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point(int x1, int y1))) return false;
            return x == x1 && y == y1;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    
    private record LineSegment(Point start, Point end) {
        
        public static boolean isVertical(LineSegment segment) {
            return segment.start.y == segment.end.y;
        }
        
        public static boolean isHorizontal(LineSegment segment) {
            return segment.start.x == segment.end.x;
        }
        
        public static boolean isDiagonal(LineSegment segment) {
            var horizontalLength = Math.abs(segment.start.x - segment.end.x);
            var verticalLength = Math.abs(segment.start.y - segment.end.y);
            return horizontalLength == verticalLength;
        }
    }
}
