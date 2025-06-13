package aoc.event.year2016;

import aoc.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Gatherers;

/// # [2016-07: Internet Protocol Version 7](https://adventofcode.com/2016/day/7)
public class Day07 implements Solver<Integer, Integer> {
    
    private static List<Sequence> sequences;
    
    public Day07(String input) {
        sequences = parse(input);
    }
    
    private static List<Sequence> parse(String input) {
        return input
            .lines()
            .map(line -> {
                var outside = new ArrayList<Character>();
                var inside = new ArrayList<Character>();
                var isInside = false;
                
                for (int i = 0; i < line.length(); i++) {
                    var character = line.charAt(i);
                    
                    if (character == '[') {
                        isInside = true;
                    }
                    else if (character == ']') {
                        isInside = false;
                    }
                    
                    if (isInside) {
                        inside.add(character);
                    }
                    else {
                        outside.add(character);
                    }
                }
                return new Sequence(outside, inside);
            })
            .toList();
    }
    
    @Override
    public Integer partOne() {
        return (int) sequences
            .stream()
            .filter(sequence -> {
                var hasAbbaOutside = hasAbba(sequence.outside());
                var hasAbbaInside = hasAbba(sequence.inside());
                return hasAbbaOutside && !hasAbbaInside;
            })
            .count();
    }
    
    private static boolean hasAbba(List<Character> sequence) {
        return sequence
            .stream()
            .gather(Gatherers.windowSliding(4))
            .anyMatch(i ->
                i.getFirst() == i.get(3) && i.get(1) == i.get(2) && i.getFirst() != i.get(1)
            );
    }
    
    @Override
    public Integer partTwo() {
        return (int) sequences
            .stream()
            .filter(pair -> {
                var outsideAbas = pair.outside()
                    .stream()
                    .gather(Gatherers.windowSliding(3))
                    .filter(i -> i.get(0) == i.get(2) && i.get(0) != i.get(1))
                    .toList();
                
                var outsideBabs = outsideAbas
                    .stream()
                    .map(list -> {
                        var inverted = new ArrayList<Character>();
                        inverted.add(list.get(1));
                        inverted.add(list.get(0));
                        inverted.add(list.get(1));
                        return inverted;
                    })
                    .toList();
                
                return pair.inside()
                    .stream()
                    .gather(Gatherers.windowSliding(3))
                    .anyMatch(i -> outsideBabs.contains(i.subList(0, i.size())));
            })
            .count();
    }
    
    private record Sequence(List<Character> outside, List<Character> inside) {}
}
