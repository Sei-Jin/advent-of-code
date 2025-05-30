package aoc.runner;

import aoc.Solver;

import java.util.ArrayList;
import java.util.List;

class Runner {
    
    public static void execute(int year, int day) {
        var solution = new Solution(year, day);
        var runData = run(solution);
        var totalTime = runData
            .stream()
            .mapToLong(Data::timeInMicros)
            .sum();
        System.out.printf("\n%d-%02d", year, day);
        print(runData, totalTime);
    }
    
    private static List<Data> run(Solution solution) {
        var input = Input.retrieve(solution);
        
        var startTime = System.nanoTime();
        var solver = solution.construct(input);
        var endTime = System.nanoTime();
        var executionTimeInMicroseconds = (endTime - startTime) / 1000;
        
        var runData = new ArrayList<Data>();
        runData.add(new Data("PARSE", "", executionTimeInMicroseconds));
        runData.add(runPart(Part.ONE, solver));
        runData.add(runPart(Part.TWO, solver));
        return runData;
    }
    
    private static Data runPart(Part part, Solver<?, ?> solver) {
        var startTime = System.nanoTime();
        var result = switch (part) {
            case Part.ONE -> solver.partOne().toString();
            case Part.TWO -> solver.partTwo().toString();
        };
        var endTime = System.nanoTime();
        var executionTimeInMicroseconds = (endTime - startTime) / 1000;
        return new Data(part.name(), result, executionTimeInMicroseconds);
    }
    
    private static void print(List<Data> runData, long totalTime) {
        System.out.println();
        System.out.printf("%-12s|%12s |%13s\n", "Part", "Time", "Result");
        System.out.println("----------------------------------------");
        
        for (var data : runData) {
            System.out.printf("%-12s|", data.name());
            System.out.printf("%13s|", data.timeInMicros() + " μs ");
            System.out.printf("%13s\n", data.result());
        }
        
        System.out.println("----------------------------------------");
        System.out.printf("Total Time: %10d μs\n", totalTime);
    }
    
    private enum Part {ONE, TWO}
    
    private record Data(String name, String result, long timeInMicros) {}
}
