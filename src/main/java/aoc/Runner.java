package aoc;

import java.io.BufferedReader;

public class Runner {

    /// There are two parts to each puzzle.
    private enum Part {
        ONE, TWO
    }

    /// Stores the execution data for a single part of the puzzle.
    private record Data(String result, long executionTimeInMicroseconds) {}

    /// Stores the execution data for both parts of the puzzle.
    protected record RunData(Data parser, Data partOne, Data partTwo) {

        /// Calculates the total execution time for all parts of the solution.
        ///
        /// @return the total execution time.
        private long calculateTotalExecutionTime() {
            return this.parser.executionTimeInMicroseconds +
                    this.partOne.executionTimeInMicroseconds +
                    this.partTwo.executionTimeInMicroseconds;
        }
    }

    /// Runs and prints both parts of the puzzle.
    ///
    /// @param year the year of the puzzle.
    /// @param day  the day of the puzzle.
    public static void runAndPrint(int year, int day) {
        final var puzzle = new Puzzle(year, day);
        final var runData = run(puzzle);
        print(puzzle, runData);
    }

    /// Runs both parts of the given puzzle
    ///
    /// @param puzzle a puzzle.
    /// @return the execution data for each of the two parts.
    protected static RunData run(Puzzle puzzle) {
        final var solver = puzzle.determinePuzzleSolver();
        final var reader = PuzzleInputRetriever.retrievePuzzleInput(puzzle);

        final var parser = runParser(solver, reader);
        final var partOne = runPart(Part.ONE, solver);
        final var partTwo = runPart(Part.TWO, solver);

        return new RunData(parser, partOne, partTwo);
    }

    private static Data runParser(Solver solver, BufferedReader reader) {
        final var startTime = System.nanoTime();

        solver.parse(reader);

        final var endTime = System.nanoTime();
        final var executionTimeInMicroseconds = (endTime - startTime) / 1000;

        return new Data("", executionTimeInMicroseconds);
    }

    /// Executes a part of the puzzle and stores the execution data.
    ///
    /// @param part   the part to run.
    /// @param solver the puzzle solver.
    /// @return the execution data for the part.
    private static Data runPart(Part part, Solver solver) {
        final var startTime = System.nanoTime();

        final var result = switch (part) {
            case Part.ONE -> solver.partOne().toString();
            case Part.TWO -> solver.partTwo().toString();
        };

        final var endTime = System.nanoTime();
        final var executionTimeInMicroseconds = (endTime - startTime) / 1000;

        return new Data(result, executionTimeInMicroseconds);
    }

    /// Prints the execution data for the puzzle.
    ///
    /// @param puzzle  the puzzle
    /// @param runData the execution data of the puzzle.
    private static void print(Puzzle puzzle, RunData runData) {
        System.out.printf("\nYear: %d Day: %d\n", puzzle.year(), puzzle.day());
        System.out.println("--------------------------------------");
        System.out.printf("%-10s|%12s |%13s\n", "Part", "Time", "Result");
        System.out.println("--------------------------------------");

        System.out.printf("%-10s|", "Parser");
        printData(runData.parser.result, runData.parser.executionTimeInMicroseconds);

        System.out.printf("%-10s|", "Part One");
        printData(runData.partOne.result, runData.partOne.executionTimeInMicroseconds);

        System.out.printf("%-10s|", "Part Two");
        printData(runData.partTwo.result, runData.partTwo.executionTimeInMicroseconds);

        System.out.println("--------------------------------------");
        System.out.printf("Total Time: %8d μs\n", runData.calculateTotalExecutionTime());
    }

    /// Prints the data for one part of the execution data.
    ///
    /// @param result                      the solution part output.
    /// @param executionTimeInMicroseconds the execution time of the solution part.
    private static void printData(String result, long executionTimeInMicroseconds) {
        System.out.printf("%13s|", executionTimeInMicroseconds + " μs ");

        if (result != null) {
            System.out.printf("%13s\n", result);
        } else {
            System.out.println("Not implemented yet.");
        }
    }
}
