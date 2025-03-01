package aoc.event.year2017.day04.highEntropyPassphrases;

import aoc.Runner;
import aoc.Solver;

import java.util.*;

public class Solution implements Solver {
    
    private final List<List<String>> wordLists;
    
    public Solution(String input) {
        wordLists = Collections.unmodifiableList(parse(input));
    }
    
    private static List<List<String>> parse(String input) {
        final var wordLists = new ArrayList<List<String>>();
        
        for (final var line : input.lines().toList()) {
            final var words = Arrays.stream(line.split(" ")).toList();
            wordLists.add(words);
        }
        
        return wordLists;
    }
    
    /// @return the number of valid passphrases.
    @Override
    public Object partOne() {
        int validPassphrases = 0;
        
        for (final var list : wordLists) {
            if (!containsDuplicate(list)) {
                validPassphrases++;
            }
        }
        
        return validPassphrases;
    }
    
    /// @return the number of valid passphrases under the new system policy.
    @Override
    public Object partTwo() {
        int validPassphrases = 0;
        
        for (final var list : wordLists) {
            if (!containsAnagram(list)) {
                validPassphrases++;
            }
        }
        
        return validPassphrases;
    }
    
    private static boolean containsDuplicate(List<String> elements) {
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
    
    private static boolean containsAnagram(List<String> list) {
        final var anagrams = new HashSet<List<Character>>();
        
        for (final var string : list) {
            List<Character> sortedWord = string.chars()
                .mapToObj(c -> (char) c)
                .sorted()
                .toList();
            
            if (anagrams.contains(sortedWord)) {
                return true;
            } else {
                anagrams.add(sortedWord);
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2017, 4);
    }
}
