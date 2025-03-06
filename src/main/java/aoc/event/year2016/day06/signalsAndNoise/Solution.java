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
        StringBuilder messageBuilder = new StringBuilder();
        
        for (final var list : characterLists) {
            Map<Character, Integer> characterCounts = calculateCharacterCounts(list);
            char mostFrequentCharacter = calculateMostFrequentCharacter(characterCounts);
            
            messageBuilder.append(mostFrequentCharacter);
        }
        
        return messageBuilder.toString();
    }
    
    private static Character calculateMostFrequentCharacter(Map<Character, Integer> characterCounts) {
        char mostFrequentCharacter = 0;
        int maxCount = 0;
        
        for (char character : characterCounts.keySet()) {
            int count = characterCounts.get(character);
            
            if (count > maxCount) {
                mostFrequentCharacter = character;
                maxCount = count;
            }
        }
        
        return mostFrequentCharacter;
    }
    
    @Override
    public Object partTwo() {
        StringBuilder messageBuilder = new StringBuilder();
        
        for (final var list : characterLists) {
            Map<Character, Integer> characterCounts = calculateCharacterCounts(list);
            char leastFrequentCharacter = calculateLeastFrequentCharacter(characterCounts);
            
            messageBuilder.append(leastFrequentCharacter);
        }
        
        return messageBuilder.toString();
    }
    
    private char calculateLeastFrequentCharacter(Map<Character, Integer> characterCounts) {
        char leastFrequentCharacter = 0;
        int minCount = Integer.MAX_VALUE;
        
        for (char character : characterCounts.keySet()) {
            int count = characterCounts.get(character);
            
            if (count < minCount) {
                leastFrequentCharacter = character;
                minCount = count;
            }
        }
        
        return leastFrequentCharacter;
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2016, 6);
    }
}
