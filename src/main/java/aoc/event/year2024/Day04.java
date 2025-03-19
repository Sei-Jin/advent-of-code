package aoc.event.year2024;

import aoc.DeprecatedSolver;

import java.util.List;

public class Day04 implements DeprecatedSolver
{
    /// The target string.
    final String TARGET = "XMAS";
    
    /// Counts the number of strings in the word search that match the string `XMAS`.
    ///
    /// The string `XMAS` can be horizontal, vertical or diagonal in the word search.
    ///
    /// @param inputLines the puzzle input.
    /// @return the number of strings that match `XMAS` in the word search.
    @Override
    public Object partOne(List<String> inputLines)
    {
        char[][] wordSearch = getWordSearch(inputLines);
        
        int totalMatchingStrings = 0;
        
        for (int rowIndex = 0; rowIndex < wordSearch.length; rowIndex++)
        {
            for (int columnIndex = 0; columnIndex < wordSearch[rowIndex].length; columnIndex++)
            {
                totalMatchingStrings += countMatchingStringsAtPosition(rowIndex, columnIndex, wordSearch);
            }
        }
        
        return totalMatchingStrings;
    }
    
    /// Converts the puzzle input into a two-dimensional array of characters.
    ///
    /// @param inputLines the puzzle input
    /// @return the puzzle input as a two-dimensional array of characters.
    private static char[][] getWordSearch(List<String> inputLines)
    {
        char[][] wordSearch = new char[inputLines.size()][inputLines.getFirst().length()];
        
        for (int row = 0; row < inputLines.size(); row++)
        {
            for (int column = 0; column < inputLines.getFirst().length(); column++)
            {
                wordSearch[row][column] = inputLines.get(row).charAt(column);
            }
        }
        
        return wordSearch;
    }
    
    /// Calculates the number of matching strings in all 8 directions from the current position.
    ///
    /// @param rowIndex the row of the current position.
    /// @param columnIndex the column of the current position.
    /// @param wordSearch the word search.
    /// @return the number of matching strings in any direction at the current position.
    private int countMatchingStringsAtPosition(int rowIndex, int columnIndex, char[][] wordSearch)
    {
        int matchingStringsAtPosition = 0;
        
        for (int rowDirection = -1; rowDirection <= 1; rowDirection++)
        {
            for (int columnDirection = -1; columnDirection <= 1; columnDirection++)
            {
                if (rowDirection == 0 && columnDirection == 0)
                {
                    continue;
                }
                
                if (isValidDirection(rowIndex, columnIndex, rowDirection, columnDirection, wordSearch))
                {
                    if (isMatchingInDirection(rowIndex, columnIndex, rowDirection, columnDirection, wordSearch))
                    {
                        matchingStringsAtPosition++;
                    }
                }
            }
        }
        
        return matchingStringsAtPosition;
    }
    
    /// Returns true if the word can fit on the word search grid in current direction, false if otherwise.
    ///
    /// The word can fit on the word search grid if the last character will be within the bounds of the grid in
    /// the current direction.
    ///
    /// @param rowIndex the row of the current position.
    /// @param columnIndex the column of the current position.
    /// @param rowDirection the current direction row-wise.
    /// @param columnDirection the current direction column-wise.
    /// @param wordSearch the word search.
    /// @return true if the word can fit in the current direction, false if otherwise.
    private boolean isValidDirection(int rowIndex, int columnIndex, int rowDirection, int columnDirection, char[][] wordSearch)
    {
        int lastRowIndex = rowIndex + rowDirection * (TARGET.length() - 1);
        int lastColumnIndex = columnIndex + columnDirection * (TARGET.length() - 1);
        
        return (lastRowIndex >= 0) &&
                (lastRowIndex < wordSearch.length) &&
                (lastColumnIndex >= 0) &&
                (lastColumnIndex < wordSearch[rowIndex].length);
    }
    
    /// Returns true if the word matches the target string `XMAS` in the current direction, false if otherwise.
    ///
    /// @param rowIndex the row of the current position.
    /// @param columnIndex the column of the current position.
    /// @param rowDirection the current direction row-wise.
    /// @param columnDirection the current direction column-wise.
    /// @param wordSearch the word search.
    /// @return true if the word matches, false if otherwise.
    boolean isMatchingInDirection(int rowIndex, int columnIndex, int rowDirection, int columnDirection, char[][] wordSearch)
    {
        boolean matchingString = true;
        
        for (int wordIndex = 0; wordIndex < TARGET.length(); wordIndex++)
        {
            int newRowIndex = rowIndex + wordIndex * rowDirection;
            int newColumnIndex = columnIndex + wordIndex * columnDirection;
            
            if (wordSearch[newRowIndex][newColumnIndex] != TARGET.charAt(wordIndex))
            {
                matchingString = false;
                break;
            }
        }
        
        return matchingString;
    }
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        return null;
    }
}
