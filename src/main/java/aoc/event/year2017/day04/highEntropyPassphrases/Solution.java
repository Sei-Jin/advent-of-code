package aoc.event.year2017.day04.highEntropyPassphrases;

import aoc.Runner;
import aoc.Solver;

import java.util.*;

public class Solution implements Solver {
    
    private final List<List<List<Character>>> wordLists;
    
    public Solution(String input) {
        wordLists = Collections.unmodifiableList(parse(input));
    }
    
    private static List<List<List<Character>>> parse(String input) {
        final var wordLists = new ArrayList<List<List<Character>>>();

        for (final var line : input.lines().toList()) {
            final var strings = Arrays.stream(line.split(" ")).toList();

            final var characterLists = new ArrayList<List<Character>>();

            for (final var string : strings) {
                final var characters = string
                    .chars()
                    .mapToObj(i -> (char) i)
                    .toList();

                characterLists.add(characters);
            }

            wordLists.add(Collections.unmodifiableList(characterLists));
        }
        
        return wordLists;
    }
    
    /// @return the number of valid passphrases.
    @Override
    public Integer partOne() {
        var validPassphrases = 0;
        
        for (final var list : wordLists) {
            if (!containsDuplicate(list)) {
                validPassphrases++;
            }
        }
        
        return validPassphrases;
    }
    
    /// @return the number of valid passphrases under the new system policy.
    @Override
    public Integer partTwo() {
        var validPassphrases = 0;
        
        for (final var list : wordLists) {
            if (!containsAnagram(list)) {
                validPassphrases++;
            }
        }
        
        return validPassphrases;
    }
    
    private static boolean containsDuplicate(List<List<Character>> elements) {
        final var encountered = new HashSet<>();
        
        for (final var element : elements) {
            if (encountered.contains(element)) {
                return true;
            } else {
                encountered.add(element);
            }
        }
        
        return false;
    }
    
    private static boolean containsAnagram(List<List<Character>> list) {
        final var mutableList = new ArrayList<List<Character>>();
        
        for (final var element : list) {
            mutableList.add(new ArrayList<>(element));
        }
        
        for (final var element : mutableList) {
            element.sort(Character::compareTo);
        }
        
        return containsDuplicate(mutableList);
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2017, 4);
    }
}
