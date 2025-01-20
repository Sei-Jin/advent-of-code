package aoc.event.year2021.day04.giantSquid;

import aoc.PuzzleSolver;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution implements PuzzleSolver
{
    /// Pattern that matches all numbers in a string.
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
    
    /// The size of each board is 5x5.
    private static final int BOARD_SIZE = 5;
    
    /// @param rowIndex the index of the row the point is on.
    /// @param columnIndex the index of the column the point is on.
    private record Position(int rowIndex, int columnIndex) {}
    
    private record BoardData(int[][] board, Map<Integer, Position> positions) {}
    
    private List<Integer> parseBingoNumbers(String bingoNumbersInput)
    {
        List<Integer> bingoNumbers = new ArrayList<>();
        
        Matcher matcher = NUMBER_PATTERN.matcher(bingoNumbersInput);
        
        while (matcher.find())
        {
            bingoNumbers.add(Integer.valueOf(matcher.group()));
        }
        
        return bingoNumbers;
    }
    
    private static List<BoardData> parseBoards(List<String> inputLines)
    {
        List<BoardData> boards = new ArrayList<>();
        
        for (int inputLineIndex = 0; inputLineIndex < inputLines.size(); inputLineIndex += 6)
        {
            int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
            Map<Integer, Position> positions = new HashMap<>();
            
            for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++)
            {
                Matcher matcher = NUMBER_PATTERN.matcher(inputLines.get(inputLineIndex + rowIndex));
                
                int columnIndex = 0;
                
                while (matcher.find())
                {
                    int number = Integer.parseInt(matcher.group());
                    
                    board[rowIndex][columnIndex] = number;
                    positions.put(number, new Position(rowIndex, columnIndex));
                    
                    columnIndex++;
                }
            }
            
            boards.add(new BoardData(board, positions));
        }
        
        return boards;
    }
    
    @Override
    public Object partOne(List<String> inputLines)
    {
        List<Integer> numbers = parseBingoNumbers(inputLines.getFirst());
        List<BoardData> boards = parseBoards(inputLines.subList(2, inputLines.size()));
        
        Set<Integer> currentNumbers = new HashSet<>();
        
        for (int currentNumber : numbers)
        {
            currentNumbers.add(currentNumber);
            
            for (BoardData boardData : boards)
            {
                if (!boardData.positions.containsKey(currentNumber))
                {
                    continue;
                }
                
                boolean bingo = isBingo(
                        boardData.positions.get(currentNumber),
                        boardData.board,
                        currentNumbers
                );
                
                if (bingo)
                {
                    return calculateFinalScore(
                            currentNumber,
                            boardData,
                            currentNumbers
                    );
                }
            }
        }
        
        throw new IllegalStateException("No winning bingo boards were found!");
    }
    
    private static boolean isBingo(
            Position position,
            int[][] board,
            Set<Integer> currentNumbers)
    {
        boolean horizontalBingo = true;
        
        for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++)
        {
            if (!currentNumbers.contains(board[rowIndex][position.columnIndex]))
            {
                horizontalBingo = false;
                break;
            }
        }
        
        boolean verticalBingo = true;
        
        for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++)
        {
            if (!currentNumbers.contains(board[position.rowIndex][columnIndex]))
            {
                verticalBingo = false;
                break;
            }
        }
        
        return horizontalBingo || verticalBingo;
    }
    
    private static int calculateFinalScore(
            int currentNumber,
            BoardData boardData,
            Set<Integer> currentNumbers)
    {
        Set<Integer> unmarkedNumbers = new HashSet<>(boardData.positions.keySet());
        unmarkedNumbers.removeAll(currentNumbers);
        
        int unmarkedNumberSum = unmarkedNumbers.stream()
                .reduce(Integer::sum)
                .orElse(0);
        
        return unmarkedNumberSum * currentNumber;
    }
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        List<Integer> numbers = parseBingoNumbers(inputLines.getFirst());
        List<BoardData> boards = parseBoards(inputLines.subList(2, inputLines.size()));
        
        int winningBoardIndex = -1, winningNumberIndex = -1;
        Set<Integer> currentNumbers = new HashSet<>();
        Set<BoardData> winningBoardData = new HashSet<>();
        
        for (int numberIndex = 0; numberIndex < numbers.size(); numberIndex++)
        {
            int currentNumber = numbers.get(numberIndex);
            currentNumbers.add(currentNumber);
            
            for (int boardIndex = 0; boardIndex < boards.size(); boardIndex++)
            {
                BoardData boardData = boards.get(boardIndex);
                
                if (!boardData.positions.containsKey(currentNumber))
                {
                    continue;
                }
                
                if (winningBoardData.contains(boardData))
                {
                    continue;
                }
                
                boolean bingo = isBingo(
                        boardData.positions.get(currentNumber),
                        boardData.board,
                        currentNumbers
                );
                
                if (bingo)
                {
                    winningBoardData.add(boardData);
                    winningNumberIndex = numberIndex;
                    winningBoardIndex = boardIndex;
                }
            }
        }
        
        if (winningBoardIndex == -1)
        {
            throw new IllegalStateException("No winning bingo boards were found!");
        }
        else
        {
            return calculateFinalScore(
                    numbers.get(winningNumberIndex),
                    boards.get(winningBoardIndex),
                    new HashSet<>(numbers.subList(0, winningNumberIndex + 1))
            );
        }
    }
}
