package aoc.event.year2016.day06.signalsAndNoise;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution implements Solver {
    
    private final List<List<Character>> characterLists;
    
    public Solution(String input) {
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
    
    private static Map<Character, Integer> calculateCharacterCounts(List<Character> list) {
        Map<Character, Integer> characterCounts = new HashMap<>();
        
        for (final var character : list) {
            int count = characterCounts.getOrDefault(character, 0) + 1;
            characterCounts.put(character, count);
        }
        
        return characterCounts;
    }
    
    @Override
    public Object partOne() {
        final var messageBuilder = new StringBuilder();
        
        for (final var list : characterLists) {
            final var characterCounts = calculateCharacterCounts(list);
            
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
    public Object partTwo() {
        final var messageBuilder = new StringBuilder();
        
        for (final var list : characterLists) {
            final var characterCounts = calculateCharacterCounts(list);
            
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
    
    public static void main(String[] args) {
        Runner.runAndPrint(2016, 6);
    }
}
