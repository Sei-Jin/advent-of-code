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
        year2015.put(1, new advent_of_code.year_2015.__01__not_quite_lisp.Solution());
        year2015.put(2, new advent_of_code.year_2015.__02__i_was_told_there_would_be_no_math.Solution());
        year2015.put(3, new advent_of_code.year_2015.__03__perfectly_spherical_houses_in_a_vacuum.Solution());
        year2015.put(5, new advent_of_code.year_2015.__05__doesnt_he_have_intern_elves_for_this.Solution());
        year2015.put(6, new advent_of_code.year_2015.__06__probably_a_fire_hazard.Solution());
        year2015.put(7, new advent_of_code.year_2015.__07__some_assembly_required.Solution());
        
        puzzleSolverMap.put(2015, year2015);
        
        
        Map<Integer, PuzzleSolver> year2016 = new HashMap<>();
        year2016.put(1, new advent_of_code.year_2016.__01__no_time_for_a_taxicab.Solution());
        year2016.put(2, new advent_of_code.year_2016.__02__bathroom_security.Solution());
        year2016.put(3, new advent_of_code.year_2016.__03__squares_with_three_sides.Solution());
        
        puzzleSolverMap.put(2016, year2016);
        
        
        Map<Integer, PuzzleSolver> year2017 = new HashMap<>();
        year2017.put(1, new advent_of_code.year_2017.__01__inverse_captcha.Solution());
        year2017.put(2, new advent_of_code.year_2017.__02__corruption_checksum.Solution());
        year2017.put(4, new advent_of_code.year_2017.__04__high_entropy_passphrases.Solution());
        year2017.put(5, new advent_of_code.year_2017.__05__a_maze_of_twisty_trampolines_all_alike.Solution());
        
        puzzleSolverMap.put(2017, year2017);
        
        Map<Integer, PuzzleSolver> year2019 = new HashMap<>();
        year2019.put(1, new advent_of_code.year_2019.__01__the_tyranny_of_the_rocket_equation.Solution());
        year2019.put(2, new advent_of_code.year_2019.__02__1202_program_alarm.Solution());
        year2019.put(3, new advent_of_code.year_2019.__03__crossed_wires.Solution());
        
        puzzleSolverMap.put(2019, year2019);
    }
}
