package advent_of_code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner commandLineInput = new Scanner(System.in);
        
        System.out.print("Enter the year for the puzzle you would like to run: ");
        int year = commandLineInput.nextInt();
        
        System.out.print("Enter the day for the puzzle you would like to run: ");
        int day = commandLineInput.nextInt();
        
        commandLineInput.close();
        
        StringBuilder fileInputPath = new StringBuilder();
        fileInputPath.append("input/year_");
        fileInputPath.append(year);
        fileInputPath.append("/day");
        
        if (day / 10 == 0)
        {
            fileInputPath.append(0);
        }
        
        fileInputPath.append(day);
        fileInputPath.append(".txt");
        
        List<String> inputLines;
        
        try
        {
            inputLines = Files.readAllLines(Path.of(fileInputPath.toString()));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        
        System.out.printf("\nYear %d Day %d\n", year, day);
        System.out.println("---------------");
        System.out.printf("Part One Result: %s\n", new Puzzle(year, day).getPuzzleSolver().partOne(inputLines).toString());
        System.out.printf("Part Two Result: %s\n", new Puzzle(year, day).getPuzzleSolver().partTwo(inputLines).toString());
    }
}
