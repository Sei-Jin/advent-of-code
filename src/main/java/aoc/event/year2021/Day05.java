package aoc.event.year2021;

import aoc.DeprecatedSolver2;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Day05 implements DeprecatedSolver2<Integer> {
    
    private static List<LineSegment> segments;
    
    public Day05(String input) {
        segments = parse(input);
    }
    
    private static List<LineSegment> parse(String input) {
        return input
            .lines()
            .map(line -> {
                final var rawPositions = line.split(" -> ");
                final var rawPosition1 = rawPositions[0];
                final var rawPosition2 = rawPositions[1];
                
                return new LineSegment(Position.of(rawPosition1), Position.of(rawPosition2));
            })
            .toList();
    }
    
    @Override
    public Integer partOne() {
        final var positionCounts = new HashMap<Position, Integer>();
        
        for (final var segment : segments) {
            if ((segment.start.x != segment.end.x) && (segment.start.y != segment.end.y)) {
                continue;
            }
            
            final var minY = Math.min(segment.start.y, segment.end.y);
            final var maxY = Math.max(segment.start.y, segment.end.y);
            
            final var minX = Math.min(segment.start.x, segment.end.x);
            final var maxX = Math.max(segment.start.x, segment.end.x);
            
            for (int x = minX; x <= maxX; x++) {
                for (int y = minY; y <= maxY; y++) {
                    final var position = new Position(x, y);
                    final var count = positionCounts.getOrDefault(position, 0) + 1;
                    positionCounts.put(position, count);
                }
            }
        }
        
        return countOverlapping(positionCounts);
    }
    
    @Override
    public Integer partTwo() {
        final var positionCounts = new HashMap<Position, Integer>();
        
        for (final var segment : segments) {
            final var isHorizontal = segment.start.x == segment.end.x;
            final var isVertical = segment.start.y == segment.end.y;
            final var isDiagonal = Math.abs(segment.start.x - segment.end.x) ==
                Math.abs(segment.start.y - segment.end.y);
            
            if (isHorizontal || isVertical) {
                final var minY = Math.min(segment.start.y, segment.end.y);
                final var maxY = Math.max(segment.start.y, segment.end.y);
                
                final var minX = Math.min(segment.start.x, segment.end.x);
                final var maxX = Math.max(segment.start.x, segment.end.x);
                
                for (int x = minX; x <= maxX; x++) {
                    for (int y = minY; y <= maxY; y++) {
                        final var position = new Position(x, y);
                        final var count = positionCounts.getOrDefault(position, 0) + 1;
                        positionCounts.put(position, count);
                    }
                }
            } else if (isDiagonal) {
                final var isDescendingX = segment.start.x > segment.end.x;
                final var isDescendingY = segment.start.y > segment.end.y;
                
                final var incrementX = isDescendingX ? -1 : 1;
                final var incrementY = isDescendingY ? -1 : 1;
                
                final var length = Math.abs(segment.start.x - segment.end.x);
                
                var x = segment.start.x;
                var y = segment.start.y;
                
                for (int i = 0; i <= length; i++) {
                    final var position = new Position(x, y);
                    final var count = positionCounts.getOrDefault(position, 0) + 1;
                    positionCounts.put(position, count);
                    
                    x += incrementX;
                    y += incrementY;
                }
            }
        }
        
        return countOverlapping(positionCounts);
    }
    
    private static int countOverlapping(HashMap<Position, Integer> positionCounts) {
        return (int) positionCounts
            .values()
            .stream()
            .filter(value -> value >= 2)
            .count();
    }
    
    private record LineSegment(Position start, Position end) {}
    
    private record Position(int x, int y) {
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Position(int x1, int y1))) return false;
            return x == x1 && y == y1;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        
        public static Position of(String string) {
            final var values = string.split(",");
            final var x = Integer.parseInt(values[0]);
            final var y = Integer.parseInt(values[1]);
            return new Position(x, y);
        }
    }
}
