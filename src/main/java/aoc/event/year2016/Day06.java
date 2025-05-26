package aoc.event.year2016;

import aoc.DeprecatedSolver2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day06 implements DeprecatedSolver2 {
    
    private final List<List<Character>> characterLists;
    
    public Day06(String input) {
        characterLists = parse(input);
    }
    
    private static List<List<Character>> parse(String input) {
        final var characterLists = new ArrayList<List<Character>>();
        final var lines = input.lines().toList();
        
        for (int i = 0; i < lines.getFirst().length(); i++) {
            characterLists.add(new ArrayList<>());
        }
        
        for (final var line : lines) {
            for (int i = 0; i < line.length(); i++) {
                final var character = line.charAt(i);
                characterLists.get(i).add(character);
            }
        }
        
        return characterLists;
    }
    
    private static Map<Character, Integer> countEntries(List<Character> list) {
        final var entryCounts = new HashMap<Character, Integer>();
        
        for (final var entry : list) {
            final var count = entryCounts.getOrDefault(entry, 0) + 1;
            entryCounts.put(entry, count);
        }
        
        return entryCounts;
    }
    
    @Override
    public String partOne() {
        final var messageBuilder = new StringBuilder();
        
        for (final var list : characterLists) {
            final var characterCounts = countEntries(list);
            
            final var mostFrequentCharacter = characterCounts
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow();
            
            messageBuilder.append(mostFrequentCharacter);
        }
        
        return messageBuilder.toString();
    }
    
    @Override
    public String partTwo() {
        final var messageBuilder = new StringBuilder();
        
        for (final var list : characterLists) {
            final var characterCounts = countEntries(list);
            
            final var leastFrequentCharacter = characterCounts
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow();
            
            messageBuilder.append(leastFrequentCharacter);
        }
        
        return messageBuilder.toString();
    }
}
