package aoc;

import java.io.BufferedReader;

public class Runner {
    /// There are two parts to each puzzle.
    private enum Part {
        ONE, TWO
    }

    /// Stores the execution data for a single part of the puzzle.
    private record PartData(Object result, long executionTimeInMicroseconds) {}

    /// Stores the execution data for both parts of the puzzle.
    protected record RunData(PartData partOne, PartData partTwo) {}

    /// Runs and prints both parts of the puzzle.
    ///
    /// @param year the year of the puzzle.
    /// @param day  the day of the puzzle.
    public static void runAndPrint(int year, int day) {
        Puzzle puzzle = new Puzzle(year, day);
        RunData runData = run(puzzle);
        print(puzzle, runData);
    }

    /// Runs both parts of the given puzzle
    ///
    /// @param puzzle a puzzle.
    /// @return the execution data for each of the two parts.
    protected static RunData run(Puzzle puzzle) {
        Solver solver = puzzle.determinePuzzleSolver();
        BufferedReader reader = PuzzleInputRetriever.retrievePuzzleInput(puzzle);

        solver.parse(reader);
        PartData partOne = runPart(Part.ONE, solver);
        PartData partTwo = runPart(Part.TWO, solver);

        return new RunData(partOne, partTwo);
    }

    /// Executes a part of the puzzle and stores the execution data.
    ///
    /// @param part   the part to run.
    /// @param solver the puzzle solver.
    /// @return the execution data for the part.
    private static PartData runPart(Part part, Solver solver) {
        long startTime = System.nanoTime();

        Object result = switch (part) {
            case Part.ONE -> solver.partOne();
            case Part.TWO -> solver.partTwo();
        };

        long endTime = System.nanoTime();

        // Dividing by 1000 gives the time in microseconds instead of nanoseconds.
        long executionTimeInMicroseconds = (endTime - startTime) / 1000;

        return new PartData(result, executionTimeInMicroseconds);
    }

    /// Prints the execution data for the puzzle.
    ///
    /// @param puzzle  the puzzle
    /// @param runData the execution data of the puzzle.
    private static void print(Puzzle puzzle, RunData runData) {
        System.out.printf("\n Year: %d Day: %d\n", puzzle.year(), puzzle.day());
        System.out.println("---------------");

        System.out.printf("%-15s", "Part One");
        printPartData(runData.partOne.result, runData.partOne.executionTimeInMicroseconds);

        System.out.printf("\n%-15s", "Part Two");
        printPartData(runData.partTwo.result, runData.partTwo.executionTimeInMicroseconds);
    }

    /// Prints the data for one part of the execution data.
    ///
    /// @param result                      the solution part output.
    /// @param executionTimeInMicroseconds the execution time of the solution part.
    private static void printPartData(Object result, long executionTimeInMicroseconds) {
        if (result != null) {
            System.out.printf("Result: %-15s", result);
        } else {
            System.out.println("Not implemented yet.");
        }

        System.out.printf("Time Elapsed: %d μs", executionTimeInMicroseconds);
    }
}
