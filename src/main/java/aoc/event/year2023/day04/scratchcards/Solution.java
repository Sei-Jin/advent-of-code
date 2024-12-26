package aoc.event.year2023.day04.scratchcards;

import aoc.PuzzleSolver;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution implements PuzzleSolver
{
    /// A regex pattern to parse the numbers for each set in the scratchcard.
    ///
    /// The pattern is pre-compiled ahead of time to avoid two possible issues:
    ///
    /// - Recompiling every time a card is parsed.
    /// - Passing through method parameters multiple times.
    private static final Pattern NUMBER_PATTERN = Pattern.compile("(\\d+)");
    
    /// Calculates the total points won by all the scratchcards.
    ///
    /// Time Complexity: O(n)
    /// - `n` is the number of characters in the puzzle input.
    ///
    /// Space Complexity: O(m)
    /// - `m` is the number of numbers on the largest scratchcard.
    ///
    /// @param inputLines the puzzle input is a list of scratchcards.
    /// @return the total points won by all the scratchcards.
    @Override
    public Object partOne(List<String> inputLines)
    {
        int totalPoints = 0;
        
        for (String line : inputLines)
        {
            Card card = parseCard(line);
            totalPoints += calculatePoints(card.getMatchingNumbers().size());
        }
        
        return totalPoints;
    }
    
    /// Calculates the total number of scratchcards.
    ///
    /// Time Complexity: `O(n)`
    /// - `n` is the number of characters in the puzzle input.
    ///
    /// Space Complexity: `O(2k + m)`
    /// - `k` is the number of scratchcards in the puzzle input, and `m` is the maximum number of
    ///  matching numbers a scratchcard contains.
    ///
    /// @param inputLines the puzzle input is a list of scratchcards.
    /// @return the total number of scratchcards.
    @Override
    public Object partTwo(List<String> inputLines)
    {
        Map<Integer, Integer> cardCounts = new HashMap<>();
        Set<Integer> cardIds = new HashSet<>();
        
        for (String line : inputLines)
        {
            Card card = parseCard(line);
            cardIds.add(card.id);
            
            increaseCardCount(card.id, cardCounts, 1);
            increaseNextCardCounts(card.id, cardCounts, card.getMatchingNumbers().size());
        }
        
        cardCounts.keySet().retainAll(cardIds);
        return sumCardCounts(cardCounts);
    }
    
    /// Parses a line of the puzzle input for the card data.
    ///
    /// Each scratchcard is in the form:
    ///
    /// `Card #: A A A A A A | B B B B B B B B`
    ///
    /// Splitting the line by `:` and `|` splits the line into three sections:
    ///
    /// - The first section contains the card number, where `#` represents the number of the
    /// card.
    /// - The second section contains a set of the winning numbers, where each `A` represents a
    /// winning number.
    /// - The third section contains a set of our numbers, where each `B` represents one of our
    /// numbers.
    ///
    /// The first section is ignored. The second and third sections are parsed for their sets of
    /// numbers.
    ///
    /// @param line a line of the puzzle input.
    /// @return a new Card containing the parsed data.
    private static Card parseCard(String line)
    {
        String[] sections = line.split("[:|]");
        
        int id = parseId(sections[0]);
        Set<Integer> winningNumbers = parseNumbers(sections[1]);
        Set<Integer> potentialWinningNumbers = parseNumbers(sections[2]);
        
        return new Card(id, winningNumbers, potentialWinningNumbers);
    }
    
    /// Parses a section of the puzzle input for the card id number.
    ///
    /// The section should be in the form:
    ///
    /// `Card #`
    ///
    /// Where `#` represents a number.
    ///
    /// @param section a section of the puzzle input that contains the card id number.
    /// @return the card id number.
    private static int parseId(String section)
    {
        Matcher matcher = NUMBER_PATTERN.matcher(section);
        
        if (matcher.find())
        {
            return Integer.parseInt(matcher.group());
        }
        else
        {
            throw new IllegalArgumentException(
                    "Invalid card id format encountered in input: " + section
            );
        }
    }
    
    /// Parses a set of numbers from the given section of the puzzle input.
    ///
    /// The given section should be in the form:
    ///
    /// ` A A A A A A A `
    ///
    /// Each `A` represents a number, and the numbers can be more than 1 digit long. The section
    /// can contain a leading and/or trailing space.
    ///
    /// @param section a section of the puzzle input that contains a set of numbers to
    ///         be parsed.
    /// @return the set of the numbers in `section`.
    private static Set<Integer> parseNumbers(String section)
    {
        Set<Integer> numbers = new HashSet<>();
        Matcher matcher = NUMBER_PATTERN.matcher(section);
        
        while (matcher.find())
        {
            numbers.add(Integer.valueOf(matcher.group()));
        }
        
        return numbers;
    }
    
    /// Calculates the total points won by a scratchcard.
    ///
    /// The first matching number is worth one point, and every number after that doubles the point
    /// value.
    ///
    /// @param totalMatches the number of matches on the card.
    /// @return the points won by a scratchcard.
    private static int calculatePoints(int totalMatches)
    {
        int points = 0;
        
        if (totalMatches >= 1)
        {
            points = 1;
        }
        
        for (int matches = 2; matches <= totalMatches; matches++)
        {
            points *= 2;
        }
        
        return points;
    }
    
    /// Increases the card count by the given amount.
    ///
    /// @param cardId the card id.
    /// @param cardCounts a mapping between the card ids and their counts.
    /// @param amount the amount to increase the card id by.
    private static void increaseCardCount(int cardId, Map<Integer, Integer> cardCounts, int amount)
    {
        int count = cardCounts.getOrDefault(cardId, 0);
        cardCounts.put(cardId, count + amount);
    }
    
    /// Increases the next `m` card counts by the current card count, where `m` is the number of
    /// matches.
    ///
    /// @param currentCardId the current card id.
    /// @param cardCounts a mapping between each card id and its count.
    /// @param matches the number of matches on the card.
    private static void increaseNextCardCounts(
            int currentCardId,
            Map<Integer, Integer> cardCounts,
            int matches)
    {
        int startId = currentCardId + 1;
        int endId = currentCardId + matches;
        
        for (int nextCardId = startId; nextCardId <= endId; nextCardId++)
        {
            increaseCardCount(nextCardId, cardCounts, cardCounts.get(currentCardId));
        }
    }
    
    /// Calculates the sum of all the card counts.
    ///
    /// @param cardCounts a mapping between each card id and its count.
    /// @return the sum of all the card counts.
    private static int sumCardCounts(Map<Integer, Integer> cardCounts)
    {
        int sum = 0;
        
        for (int count : cardCounts.values())
        {
            sum += count;
        }
        
        return sum;
    }
    
    /// Stores the data for each card.
    private record Card(int id, Set<Integer> winningNumbers, Set<Integer> ourNumbers)
    {
        /// Creates a set of the matching numbers.
        ///
        /// A matching number is considered any number that appears in both sets.
        ///
        /// @return the set of matching numbers.
        public Set<Integer> getMatchingNumbers()
        {
            Set<Integer> matchingNumbers = new HashSet<>(this.winningNumbers());
            matchingNumbers.retainAll(this.ourNumbers());
            
            return matchingNumbers;
        }
    }
}
