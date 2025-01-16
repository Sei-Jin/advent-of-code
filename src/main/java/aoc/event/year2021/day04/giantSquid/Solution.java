package aoc.event.year2021.day04.giantSquid;

import aoc.PuzzleSolver;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution implements PuzzleSolver
{
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
    private static final int BOARD_SIZE = 5;
    
    private record Point(int row, int column) {}
    
    private record BoardData(
            Map<Integer, Point> board,
            List<Set<Integer>> rows,
            List<Set<Integer>> columns
    ) {}
    
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
    
    private static List<BoardData> parseBingoBoards(List<String> inputLines)
    {
        List<BoardData> bingoBoards = new ArrayList<>();
        
        for (int inputLineIndex = 2; inputLineIndex < inputLines.size(); inputLineIndex += 6)
        {
            // Parse board
            Map<Integer, Point> bingoBoard = new HashMap<>();
            List<Set<Integer>> rows = new ArrayList<>();
            List<Set<Integer>> columns = new ArrayList<>();
            
            for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++)
            {
                Set<Integer> rowNumbers = new HashSet<>();
                rows.add(rowNumbers);
            }
            
            for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++)
            {
                Set<Integer> columnNumbers = new HashSet<>();
                columns.add(columnNumbers);
            }
            
            for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++)
            {
                String row = inputLines.get(inputLineIndex + rowIndex);
                Matcher matcher = NUMBER_PATTERN.matcher(row);
                
                int columnIndex = 0;
                
                while (matcher.find())
                {
                    int number = Integer.parseInt(matcher.group());
                    
                    bingoBoard.put(number, new Point(rowIndex, columnIndex));
                    rows.get(rowIndex).add(number);
                    columns.get(columnIndex).add(number);
                    
                    columnIndex++;
                }
            }
            
            bingoBoards.add(new BoardData(bingoBoard, rows, columns));
        }
        
        return bingoBoards;
    }
    
    @Override
    public Object partOne(List<String> inputLines)
    {
        List<Integer> bingoNumbers = parseBingoNumbers(inputLines.getFirst());
        List<BoardData> bingoBoards = parseBingoBoards(inputLines);
        
        Set<Integer> currentNumbers = new HashSet<>();
        
        for (int currentNumber : bingoNumbers)
        {
            currentNumbers.add(currentNumber);
            
            for (BoardData boardData : bingoBoards)
            {
                if (!boardData.board.containsKey(currentNumber))
                {
                    continue;
                }
                
                Point point = boardData.board.get(currentNumber);
                
                Set<Integer> currentRow = boardData.rows.get(point.row);
                Set<Integer> currentColumn = boardData.columns.get(point.column);
                
                if (currentNumbers.containsAll(currentRow) ||
                        currentNumbers.containsAll(currentColumn))
                {
                    // Calculate final score
                    Set<Integer> unmarkedNumbers = new HashSet<>(boardData.board.keySet());
                    unmarkedNumbers.removeAll(currentNumbers);
                    
                    int unmarkedNumberSum = 0;
                    
                    for (int number : unmarkedNumbers)
                    {
                        unmarkedNumberSum += number;
                    }
                    
                    return unmarkedNumberSum * currentNumber;
                }
            }
        }
        
        throw new IllegalStateException("No winning bingo boards were found!");
    }
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        return null;
    }
}
