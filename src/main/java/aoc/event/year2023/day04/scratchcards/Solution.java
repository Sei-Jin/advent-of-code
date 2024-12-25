package aoc.event.year2023.day04.scratchcards;

import aoc.PuzzleSolver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution implements PuzzleSolver
{
    /// Calculates the total points won by all the scratchcards.
    ///
    /// @param inputLines the puzzle input.
    /// @return the total points won by all the scratchcards.
    @Override
    public Object partOne(List<String> inputLines)
    {
        int totalPoints = 0;
        
        Pattern pattern = Pattern.compile("(\\d+)");
        
        for (String line : inputLines)
        {
            Card card = parseCard(pattern, line);
            
            Set<Integer> actualWinningNumbers = new HashSet<>(card.winningNumbers());
            actualWinningNumbers.retainAll(card.ourNumbers());
            
            int points = calculatePoints(actualWinningNumbers);
            totalPoints += points;
        }
        
        return totalPoints;
    }
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        return null;
    }
    
    /// Parses a line of the puzzle input for the card data.
    ///
    /// Each scratchcard is in the form:
    ///
    /// `Card #: A A A A A A | B B B B B B B B`
    ///
    /// Splitting the line by `:` and `|` splits the line into three sections:
    ///
    /// - The first section contains the card number, where `#` represents the number of the card.
    /// - The second section contains a set of the winning numbers, where each `A` represents a
    /// winning number.
    /// - The third section contains a set of our numbers, where each `B` represents one of our
    /// numbers.
    ///
    /// The first section is ignored. The second and third sections are parsed for their sets of
    /// numbers.
    ///
    /// @param pattern a pattern that matches the numbers in each set.
    /// @param line a line of the puzzle input.
    /// @return a new Card containing the parsed data.
    private static Card parseCard(Pattern pattern, String line)
    {
        String[] sections = line.split("[:|]");
        
        Set<Integer> winningNumbers = parseNumbers(pattern, sections[1]);
        Set<Integer> potentialWinningNumbers = parseNumbers(pattern, sections[2]);
        
        return new Card(winningNumbers, potentialWinningNumbers);
    }
    
    /// Parses a set of numbers from the given section of the puzzle input.
    ///
    /// The given section should be in the form:
    ///
    /// ` A A A A A A A `
    ///
    /// Each `A` represents a number, and the numbers can be more than 1 digit long. The section
    /// can contain leading and/or trailing spaces.
    ///
    /// @param pattern a pattern that matches the numbers in each set
    /// @param section a section of the puzzle input that contains a set of numbers to be
    ///         parsed.
    /// @return the set of the numbers in `section`.
    private static Set<Integer> parseNumbers(Pattern pattern, String section)
    {
        Set<Integer> numbers = new HashSet<>();
        Matcher matcher = pattern.matcher(section);
        
        while (matcher.find())
        {
            numbers.add(Integer.valueOf(matcher.group()));
        }
        
        return numbers;
    }
    
    /// Calculates the total points won by a scratchcard.
    ///
    /// The first winning number is worth one point, and every number after that doubles the point
    /// value.
    ///
    /// @param actualWinningNumbers the winning scratchcard numbers.
    /// @return the points won by a scratchcard.
    private static int calculatePoints(Set<Integer> actualWinningNumbers)
    {
        int points = 0;
        
        if (!actualWinningNumbers.isEmpty())
        {
            points = 1;
        }
        
        for (int winners = 1; winners < actualWinningNumbers.size(); winners++)
        {
            points *= 2;
        }
        
        return points;
    }
    
    /// Stores the data for each card.
    private record Card(Set<Integer> winningNumbers, Set<Integer> ourNumbers) {}
}
