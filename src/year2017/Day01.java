package year2017;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * --- Day 1: Inverse Captcha ---
 */
public class Day01 {
    
    public static void main(String[] args) throws IOException {

        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        for (String line : inputLines) {
            partOne(line);
            partTwo(line);
        }
    }
    
    
    private static void partOne (String line) {
        
        int solution = solveCaptcha(line, 1);
        
        System.out.println("The solution to the captcha is: " + solution);
    }
    
    
    private static void partTwo (String line) {
        
        int solution = solveCaptcha(line, line.length() / 2);
        
        System.out.println("The solution to the new captcha is: " + solution);
    }
    
    
    private static int solveCaptcha(String line, int offset) {
        
        int runningTotal = 0;
        
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == line.charAt((i + offset) % line.length())) {
                runningTotal += Character.getNumericValue(line.charAt(i));
            }
        }
        
        return runningTotal;
    }
}
