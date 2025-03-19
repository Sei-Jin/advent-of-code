package aoc;

import java.io.File;
import java.nio.file.Path;

public record Solution(int year, int day) {
    
    /// Determines the classpath of the puzzle solution.
    ///
    /// First, the name of the puzzle must be determined. Only after the puzzle name has been
    /// determined can the full classpath be determined.
    ///
    /// @return the classpath of the puzzle solution.
    public String determineClassPath() {
        final var outerDirectories = "src/main/java/";
        final var innerDirectories = String.format(
            "aoc/event/year%d/day%s/",
            year,
            getDayWithPadding()
        );
        final var allDirectories = outerDirectories + innerDirectories;
        
        final var dayPackageName = innerDirectories.replace('/', '.');
        final var puzzleName = determinePuzzleName(allDirectories);
        
        return String.format("%s%s.Solution", dayPackageName, puzzleName);
    }
    
    private static String determinePuzzleName(String totalPath) {
        final var dayPackage = new File(Path.of(totalPath).toAbsolutePath().toString());
        final var directories = dayPackage.listFiles();
        
        if (directories == null) {
            throw new AssertionError(
                "The package did not contain any directories."
            );
        }
        
        if (directories.length != 1) {
            throw new AssertionError(
                "There should only be one package for the puzzle name"
            );
        }
        
        return directories[0].getName();
    }
    
    /// This method pads an extra zero to days with single-digit values (1-9).
    ///
    /// This is useful for directory and file paths when we want all the day values to take up two
    /// digits of space.
    ///
    /// @return the padded day value.
    public String getDayWithPadding() {
        final var singleDigitDay = (day < 10);
        
        if (singleDigitDay) {
            return "0" + day;
        } else {
            return String.valueOf(day);
        }
    }
}
