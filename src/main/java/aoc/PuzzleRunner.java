package aoc;

import java.util.List;

public class PuzzleRunner
{
    /// There are two parts to each puzzle.
    private enum Part
    {
        ONE, TWO
    }
    
    /// Stores the execution data for a single part of the puzzle.
    private record PartData(Object result, long executionTimeInMicroseconds) {}
    
    /// Stores the execution data for both parts of the puzzle.
    protected record RunData(PartData partOne, PartData partTwo) {}
    
    /// Runs and prints both parts of the puzzle.
    ///
    /// @param year the year of the puzzle.
    /// @param day the day of the puzzle.
    public static void runAndPrint(int year, int day)
    {
        Puzzle puzzle = new Puzzle(year, day);
        RunData runData = run(puzzle);
        print(puzzle, runData);
    }
    
    /// Runs both parts of the given puzzle
    ///
    /// @param puzzle a puzzle.
    /// @return the execution data for each of the two parts.
    protected static RunData run(Puzzle puzzle)
    {
        PuzzleSolver puzzleSolver = puzzle.determinePuzzleSolver();
        List<String> puzzleInput = PuzzleInputRetriever.retrievePuzzleInput(puzzle);
        
        PartData partOne = runPart(Part.ONE, puzzleSolver, puzzleInput);
        PartData partTwo = runPart(Part.TWO, puzzleSolver, puzzleInput);
        
        return new RunData(partOne, partTwo);
    }
    
    /// Executes a part of the puzzle and stores the execution data.
    ///
    /// @param part the part to run.
    /// @param solver the puzzle solver.
    /// @param puzzleInput the input of the puzzle.
    /// @return the execution data for the part.
    private static PartData runPart(Part part, PuzzleSolver solver, List<String> puzzleInput)
    {
        long startTime =  System.nanoTime();
        
        Object partOneResult = switch (part)
        {
            case Part.ONE -> solver.partOne(puzzleInput);
            case Part.TWO -> solver.partTwo(puzzleInput);
        };
        
        long endTime = System.nanoTime();
        
        // Dividing by 1000 gives the time in microseconds instead of nanoseconds.
        long executionTimeInMicroseconds = (endTime - startTime) / 1000;
        
        return new PartData(partOneResult, executionTimeInMicroseconds);
    }
    
    /// Prints the execution data for the puzzle.
    ///
    /// @param puzzle the puzzle
    /// @param runData the execution data of the puzzle.
    private static void print(Puzzle puzzle, RunData runData)
    {
        System.out.printf("\n Year: %d Day: %d\n", puzzle.getYear(), puzzle.getDay());
        System.out.println("---------------");
        
        System.out.printf("%-15s", "Part One");
        printPartData(runData.partOne.result, runData.partOne.executionTimeInMicroseconds);
        
        System.out.printf("\n%-15s", "Part Two");
        printPartData(runData.partTwo.result, runData.partTwo.executionTimeInMicroseconds);
    }
    
    /// Prints the data for one part of the execution data.
    ///
    /// @param result the solution part output.
    /// @param executionTimeInMicroseconds the execution time of the solution part.
    private static void printPartData(Object result, long executionTimeInMicroseconds)
    {
        if (result != null)
        {
            System.out.printf("Result: %-15s", result);
        }
        else
        {
            System.out.println("Not implemented yet.");
        }
        
        System.out.printf("Time Elapsed: %d μs", executionTimeInMicroseconds);
    }
}
