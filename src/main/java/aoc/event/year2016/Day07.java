package aoc.event.year2016;

import aoc.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Gatherers;

public class Day07 implements Solver<Integer> {
    
    private static List<String> lines;
    
    public Day07(String input) {
        lines = input.lines().toList();
    }
    
    @Override
    public Integer partOne() {
        var count = 0;
        
        for (final var line : lines) {
            final var outside = new ArrayList<Character>();
            final var inside = new ArrayList<Character>();
            
            var isInside = false;
            
            for (int i = 0; i < line.length(); i++) {
                final var character = line.charAt(i);
                
                if (character == '[') {
                    isInside = true;
                } else if (character == ']') {
                    isInside = false;
                } else {
                    if (isInside) {
                        inside.add(character);
                    } else {
                        outside.add(character);
                    }
                }
            }
            
            final var outsideSequence = outside
                .stream()
                .gather(Gatherers.windowSliding(4))
                .anyMatch(i ->
                    i.getFirst() == i.get(3) && i.get(1) == i.get(2) && i.getFirst() != i.get(1)
                );
            
            final var insideSequence = inside
                .stream()
                .gather(Gatherers.windowSliding(4))
                .anyMatch(i ->
                    i.getFirst() == i.get(3) && i.get(1) == i.get(2) && i.getFirst() != i.get(1)
                );
            
            if (outsideSequence && !insideSequence) {
                count++;
            }
        }
        
        return count;
    }
    
    @Override
    public Integer partTwo() {
        var count = 0;
        
        for (final var line : lines) {
            final var outside = new ArrayList<Character>();
            final var inside = new ArrayList<Character>();
            
            var isInside = false;
            
            for (int i = 0; i < line.length(); i++) {
                final var character = line.charAt(i);
                
                if (character == '[') {
                    isInside = true;
                } else if (character == ']') {
                    isInside = false;
                } else {
                    if (isInside) {
                        inside.add(character);
                    } else {
                        outside.add(character);
                    }
                }
            }
            
            final var outsideSequences = outside
                .stream()
                .gather(Gatherers.windowSliding(3))
                .filter(i -> i.getFirst() == i.get(2) && i.getFirst() != i.get(1))
                .toList();
            
            final var invertedOutsideSequences = new ArrayList<List<Character>>();
            
            for (final var list : outsideSequences) {
                final var inverted = new ArrayList<Character>();
                
                inverted.add(list.get(1));
                inverted.add(list.get(0));
                inverted.add(list.get(1));
                
                invertedOutsideSequences.add(inverted);
            }
            
            final var supportsSsl = inside
                .stream()
                .gather(Gatherers.windowSliding(3))
                .anyMatch(i -> invertedOutsideSequences.contains(i.subList(0, i.size())));
            
            if (supportsSsl) {
                count++;
            }
        }
        
        return count;
    }
}
