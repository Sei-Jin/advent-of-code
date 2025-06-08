package aoc.event.year2017;

import aoc.Solver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/// # [2017-04: High-Entropy Passphrases](https://adventofcode.com/2017/day/4)
public class Day04 implements Solver<Integer, Integer> {
    
    private final List<List<String>> passphrases;
    
    public Day04(String input) {
        passphrases = parse(input);
    }
    
    private static List<List<String>> parse(String input) {
        return input
            .lines()
            .map(line -> Arrays
                .stream(line.split(" "))
                .toList()
            )
            .toList();
    }
    
    /// Calculates the number of passphrases without duplicate words.
    @Override
    public Integer partOne() {
        return (int) passphrases
            .stream()
            .filter(list -> !containsDuplicate(list))
            .count();
    }
    
    private static boolean containsDuplicate(List<String> elements) {
        var seen = new HashSet<>();
        for (var element : elements) {
            if (seen.contains(element)) {
                return true;
            }
            else {
                seen.add(element);
            }
        }
        return false;
    }
    
    /// Calculates the number of passphrases with words that cannot form anagrams.
    @Override
    public Integer partTwo() {
        return (int) passphrases
            .stream()
            .filter(list -> !containsAnagram(list))
            .count();
    }
    
    private static boolean containsAnagram(List<String> passphrase) {
        var seen = new HashSet<>();
        for (var word : passphrase) {
            var sorted = createSorted(word);
            if (seen.contains(sorted)) {
                return true;
            }
            else {
                seen.add(sorted);
            }
        }
        return false;
    }
    
    private static String createSorted(String string) {
        var test = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            test[i] = string.charAt(i);
        }
        Arrays.sort(test);
        var stringBuilder = new StringBuilder();
        for (char c : test) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
