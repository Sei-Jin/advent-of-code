package aoc.event.year2019;

import aoc.Solver;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Gatherers;
import java.util.stream.IntStream;

/// # [2019-04: Secure Container](https://adventofcode.com/2019/day/4)
public class Day04 implements Solver<Integer, Integer> {
    
    private final Range range;
    
    public Day04(String input) {
        var parts = input.split("-");
        int start = Integer.parseInt(parts[0]);
        int end = Integer.parseInt(parts[1]);
        range = new Range(start, end);
    }
    
    private static Deque<Integer> toDigits(int number) {
        var digits = new ArrayDeque<Integer>();
        while (number > 0) {
            digits.addFirst(number % 10);
            number = number / 10;
        }
        return digits;
    }
    
    private static boolean isNonDecreasing(Deque<Integer> digits) {
        return digits
            .stream()
            .gather(Gatherers.windowSliding(2))
            .allMatch(window -> window.get(0) <= window.get(1));
    }
    
    @Override
    public Integer partOne() {
        return (int) IntStream
            .rangeClosed(range.start(), range.end())
            .boxed()
            .map(Day04::toDigits)
            .filter(Day04::isNonDecreasing)
            .filter(Day04::containsPair)
            .count();
    }
    
    private static boolean containsPair(Deque<Integer> digits) {
        return digits
            .stream()
            .gather(Gatherers.windowSliding(2))
            .anyMatch(window -> Objects.equals(window.get(0), window.get(1)));
    }
    
    @Override
    public Integer partTwo() {
        return (int) IntStream
            .rangeClosed(range.start(), range.end())
            .boxed()
            .map(Day04::toDigits)
            .filter(Day04::isNonDecreasing)
            .filter(Day04::containsStandalonePair)
            .count();
    }
    
    private static boolean containsStandalonePair(Deque<Integer> digits) {
        var middle = digits
            .stream()
            .gather(Gatherers.windowSliding(4))
            .anyMatch(window -> {
                var isPair = Objects.equals(window.get(1), window.get(2));
                var partOfLargerGroup =
                    Objects.equals(window.get(0), window.get(1))
                        || Objects.equals(window.get(2), window.get(3));
                return isPair && !partOfLargerGroup;
            });
        // Edge cases. Check if the first or last two digits form a standalone pair.
        var start = false;
        var end = false;
        if (digits.size() >= 3) {
            start = hasEdgePair(digits.iterator());
            end = hasEdgePair(digits.reversed().iterator());
        }
        return middle || start || end;
    }
    
    private static boolean hasEdgePair(Iterator<Integer> it) {
        var first = it.next();
        var second = it.next();
        var third = it.next();
        var isPair = Objects.equals(second, first);
        var partOfLargerGroup = Objects.equals(third, second);
        return isPair && !partOfLargerGroup;
    }
    
    private record Range(int start, int end) {}
}
