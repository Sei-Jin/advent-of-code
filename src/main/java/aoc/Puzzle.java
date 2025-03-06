package aoc;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public record Puzzle(int year, int day) {
    
    /// Determines the classpath of the puzzle solution.
    ///
    /// First, the name of the puzzle must be determined. Only after the puzzle name has been
    /// determined can the full classpath be determined.
    ///
    /// @return the classpath of the puzzle solution.
    public String determineClassPath() {
        String outerPath = "src/main/java/";
        String innerPath = String.format("aoc/event/year%d/day%s/", year, getDayWithPadding());
        String totalPath = outerPath + innerPath;
        
        File dayPackage = new File(Path.of(totalPath).toAbsolutePath().toString());
        
        String puzzleName = Arrays.stream(Objects.requireNonNull(dayPackage.listFiles()))
            .toList()
            .getFirst()
            .getName();
        
        String dayPackageName = innerPath.replace('/', '.');
        
        return String.format("%s%s.Solution", dayPackageName, puzzleName);
    }
    
    /// This method pads an extra zero to days with single-digit values (1-9).
    ///
    /// This is useful for directory and file paths when we want all the day values to take up two
    /// digits of space.
    ///
    /// @return the padded day value.
    public String getDayWithPadding() {
        boolean singleDigitDay = (day < 10);
        
        if (singleDigitDay) {
            return "0" + day;
        } else {
            return String.valueOf(day);
        }
    }
}
