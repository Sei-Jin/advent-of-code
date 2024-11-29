package advent_of_code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Scanner commandLineInput = new Scanner(System.in);
        
        System.out.print("Enter the year for the puzzle you would like to run: ");
        int year = commandLineInput.nextInt();
        
        System.out.print("Enter the day for the puzzle you would like to run: ");
        int day = commandLineInput.nextInt();
        
        commandLineInput.close();
        
        Puzzle puzzle = new Puzzle(year, day);
        
        String fileInputPath = "input/year_" +
                puzzle.getYear() +
                "/day" +
                puzzle.getDayWithPadding() +
                ".txt";
        
        List<String> inputLines = Files.readAllLines(Path.of(fileInputPath));
        
        System.out.printf("\nYear %d Day %d\n", year, day);
        System.out.println("---------------");
        System.out.printf("Part One Result: %s\n", puzzle.getPuzzleSolver().partOne(inputLines).toString());
        System.out.printf("Part Two Result: %s\n", puzzle.getPuzzleSolver().partTwo(inputLines).toString());
    }
}
