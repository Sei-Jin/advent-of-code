package advent_of_code;

import java.util.HashMap;
import java.util.Map;

public class Puzzle
{
    private final int day;
    private final int year;
    private static final Map<Integer, Map<Integer, PuzzleSolver>> puzzleSolverMap = new HashMap<>();
    
    public Puzzle(int year, int day)
    {
        this.year = year;
        this.day = day;
    }
    
    public PuzzleSolver getPuzzleSolver()
    {
        Map<Integer, PuzzleSolver> yearMap = puzzleSolverMap.get(this.year);
        if (yearMap == null)
        {
            throw new IllegalArgumentException("No puzzles found for year: " + this.year);
        }
        
        PuzzleSolver solver = yearMap.get(this.day);
        if (solver == null)
        {
            throw new IllegalArgumentException("No puzzle found for day: " + this.day + " in year: " + this.year);
        }
        
        return solver;
    }
    
    static
    {
        Map<Integer, PuzzleSolver> year2015 = new HashMap<>();
        year2015.put(1, new advent_of_code.year_2015.__01__not_quite_lisp.Day01());
        
        puzzleSolverMap.put(2015, year2015);
    }
}
