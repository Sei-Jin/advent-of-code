package advent_of_code;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException
    {
        UserInput userInput = getUserInput();
        
        Puzzle puzzle = new Puzzle(userInput.year(), userInput.day());
        
        runPuzzle(puzzle);
    }
    
    /// Stores the data entered by the user.
    ///
    /// @param year the year of the puzzle.
    /// @param day the day of the puzzle.
    private record UserInput(int year, int day) { }
    
    /// Retrieves the user input from the command line and stores the data in a new record class.
    ///
    /// @return a new record class that stores the user input.
    private static UserInput getUserInput()
    {
        Scanner commandLineInput = new Scanner(System.in);
        
        System.out.print("Enter the year for the puzzle you would like to run: ");
        int year = commandLineInput.nextInt();
        
        System.out.print("Enter the day for the puzzle you would like to run: ");
        int day = commandLineInput.nextInt();
        
        commandLineInput.close();
        
        return new UserInput(year, day);
    }
    
    /// Executes the steps needed to run a single puzzle.
    ///
    /// 1. The puzzle input is retrieved.
    /// 2. The puzzle solution is run with the puzzle input.
    /// 3. The puzzle results are printed.
    ///
    /// @param puzzle the puzzle.
    private static void runPuzzle(Puzzle puzzle) throws IOException, URISyntaxException, InterruptedException
    {
        List<String> inputLines = PuzzleInputRetriever.retrievePuzzleInput(puzzle);
        PuzzleResults puzzleResults = getPuzzleResults(puzzle, inputLines);
        printPuzzleResults(puzzle, puzzleResults);
    }
    
    /// Stores the results from part one and two of the puzzle.
    ///
    /// @param partOneResult the result from part one of the puzzle.
    /// @param partTwoResult the result form part two of the puzzle.
    private record PuzzleResults(String partOneResult, String partTwoResult) { }
    
    /// Runs the puzzle solution and stores the results from both parts.
    ///
    /// @param puzzle the puzzle.
    /// @param inputLines the puzzle input.
    private static PuzzleResults getPuzzleResults(Puzzle puzzle, List<String> inputLines)
    {
        return new PuzzleResults(
                puzzle.getPuzzleSolver().partOne(inputLines).toString(),
                puzzle.getPuzzleSolver().partTwo(inputLines).toString()
        );
    }
    
    /// Prints the puzzle results.
    ///
    /// @param puzzle the puzzle.
    /// @param puzzleResults the puzzle results.
    private static void printPuzzleResults(Puzzle puzzle, PuzzleResults puzzleResults)
    {
        System.out.printf("\nYear %d Day %d\n", puzzle.getYear(), puzzle.getDay());
        System.out.println("---------------");
        System.out.printf("Part One Result: %s\n", puzzleResults.partOneResult());
        System.out.printf("Part Two Result: %s\n", puzzleResults.partTwoResult());
    }
}
