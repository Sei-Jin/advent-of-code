package aoc.event.year2023;

import aoc.Solver;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/// # [2023-04: Scratchcards](https://adventofcode.com/2023/day/4)
public class Day04 implements Solver<Integer, Integer> {
    
    private static final Pattern numberPattern = Pattern.compile("(\\d+)");
    private final List<Card> cards;
    
    public Day04(String input) {
        cards = parse(input);
    }
    
    private static List<Card> parse(String input) {
        return input
            .lines()
            .map(line -> {
                var parts = line.split("[:|]");
                
                int id = Integer.parseInt(parts[0].split("\\s+")[1]);
                var winningNumbers = parseNumbers(parts[1]);
                var potentialWinningNumbers = parseNumbers(parts[2]);
                
                return new Card(id, winningNumbers, potentialWinningNumbers);
            })
            .toList();
    }
    
    private static Set<Integer> parseNumbers(String section) {
        return numberPattern
            .matcher(section)
            .results()
            .map(MatchResult::group)
            .map(Integer::parseInt)
            .collect(Collectors.toSet());
    }
    
    private static int countMatches(Set<Integer> ourNumbers, Set<Integer> winningNumbers) {
        return (int) ourNumbers
            .stream()
            .filter(winningNumbers::contains)
            .count();
    }
    
    /// Calculates the total points won by all the scratchcards.
    @Override
    public Integer partOne() {
        return cards
            .stream()
            .map(card -> countMatches(card.ourNumbers(), card.winningNumbers()))
            .mapToInt(Day04::calculatePoints)
            .sum();
    }
    
    private static int calculatePoints(int matches) {
        if (matches > 0) {
            return (int) Math.pow(2, matches - 1);
        }
        else {
            return 0;
        }
    }
    
    /// Calculates the total number of scratchcards.
    @Override
    public Integer partTwo() {
        var cardCounts = new HashMap<Integer, Integer>();
        for (var card : cards) {
            int count = cardCounts.getOrDefault(card.id(), 0) + 1;
            cardCounts.put(card.id(), count);
            
            int startId = card.id() + 1;
            int endId = card.id() + countMatches(card.ourNumbers(), card.winningNumbers());
            for (int i = startId; i <= endId; i++) {
                int extraCopies = cardCounts.getOrDefault(i, 0);
                cardCounts.put(i, count + extraCopies);
            }
        }
        
        var originalIds = cards
            .stream()
            .map(Card::id)
            .collect(Collectors.toSet());
        
        return originalIds
            .stream()
            .mapToInt(cardCounts::get)
            .sum();
    }
    
    private record Card(int id, Set<Integer> winningNumbers, Set<Integer> ourNumbers) {}
}
