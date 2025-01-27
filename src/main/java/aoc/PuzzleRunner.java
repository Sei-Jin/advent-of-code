package aoc;

import java.util.List;

public class PuzzleRunner
{
    private enum Part
    {
        ONE, TWO
    }
    
    private record PartData(Object result, long executionTimeInMicroseconds) {}
    protected record RunData(PartData partOne, PartData partTwo) {}
    
    public static void runAndPrint(int year, int day)
    {
        Puzzle puzzle = new Puzzle(year, day);
        RunData runData = run(puzzle);
        print(puzzle, runData);
    }
    
    /// Executes the steps needed to run a single puzzle.
    ///
    /// 1. The puzzle input is retrieved.
    /// 2. The puzzle solution is run with the puzzle input.
    /// 3. The puzzle results are printed.
    ///
    /// @param puzzle the puzzle.
    /// @return the data for each of the two parts.
    protected static RunData run(Puzzle puzzle)
    {
        PuzzleSolver puzzleSolver = puzzle.determinePuzzleSolver();
        List<String> inputLines = PuzzleInputRetriever.retrievePuzzleInput(puzzle);
        
        PartData partOne = runPart(Part.ONE, puzzleSolver, inputLines);
        PartData partTwo = runPart(Part.TWO, puzzleSolver, inputLines);
        
        return new RunData(partOne, partTwo);
    }
    
    private static PartData runPart(Part part, PuzzleSolver solver, List<String> inputLines)
    {
        long startTime =  System.nanoTime();
        
        Object partOneResult = switch (part)
        {
            case Part.ONE -> solver.partOne(inputLines);
            case Part.TWO -> solver.partTwo(inputLines);
        };
        
        long endTime = System.nanoTime();
        
        // Dividing by 1000 gives the time in microseconds instead of nanoseconds.
        long executionTimeInMicroseconds = (endTime - startTime) / 1000;
        
        return new PartData(partOneResult, executionTimeInMicroseconds);
    }
    
    private static void print(Puzzle puzzle, RunData runData)
    {
        System.out.printf("\n Year: %d Day: %d\n", puzzle.getYear(), puzzle.getDay());
        System.out.println("---------------");
        
        System.out.printf("%-15s", "Part One");
        printData(runData.partOne.result, runData.partOne.executionTimeInMicroseconds);
        
        System.out.printf("\n%-15s", "Part Two");
        printData(runData.partTwo.result, runData.partTwo.executionTimeInMicroseconds);
    }
    
    private static void printData(Object result, long executionTimeInMicroseconds)
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
