package advent_of_code;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public class Puzzle
{
    private final int day;
    private final int year;
    
    public Puzzle(int year, int day)
    {
        this.year = year;
        this.day = day;
    }
    
    public int getDay()
    {
        return day;
    }
    
    public int getYear()
    {
        return year;
    }
    
    /// This method pads an extra zero to days with single-digit values (1-9).
    ///
    /// This is useful for directory and file paths when we want all the day values to take up two digits of space.
    ///
    /// @return the padded day value.
    public String getDayWithPadding()
    {
        boolean singleDigitDay = (day < 10);
        
        if (singleDigitDay)
        {
            return "0" + day;
        }
        else
        {
            return String.valueOf(day);
        }
    }
    
    /// Determines the classpath of the puzzle solution.
    ///
    /// First, the name of the puzzle must be determined. Only after the puzzle name has been determined
    /// can the full classpath be determined.
    ///
    /// @return the classpath of the puzzle solution.
    private String getClassPath()
    {
        String outerPath = "src/main/java/";
        String innerPath = String.format("advent_of_code/event/year_%d/day%s/", year, getDayWithPadding());
        String totalPath = outerPath + innerPath;
        
        File dayPackage = new File(Path.of(totalPath).toAbsolutePath().toString());
        
        String puzzleName = Arrays.stream(Objects.requireNonNull(dayPackage.listFiles()))
                .toList()
                .getFirst()
                .getName();
        
        String dayPackageName = innerPath.replace('/', '.');
        
        return String.format("%s%s.Solution", dayPackageName, puzzleName);
    }
    
    /// Determines the solution class for the puzzle.
    ///
    /// This method uses the Reflection API to dynamically determine which solution implementation matches the puzzle.
    ///
    /// @return the solution class for the puzzle.
    public PuzzleSolver getPuzzleSolver()
    {
        String classPath = getClassPath();
        
        Class<?> solutionClass;
        
        try
        {
            solutionClass = Class.forName(classPath);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        
        try
        {
            return (PuzzleSolver) solutionClass.getConstructor().newInstance();
        }
        catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        }
    }
}
