package year2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * --- Day 1: Not Quite Lisp ---
 */
public class Day01
{
    public static void main(String[] args) throws IOException
    {
        String inputLine = Files.readString(Path.of("input.txt"));
        
        partOne(inputLine);
        partTwo(inputLine);
    }
    
    
    private static void partOne(String inputLine)
    {
        int floorLevel = 0;
        
        for (int i = 0; i < inputLine.length(); i++)
        {
            switch (inputLine.charAt(i))
            {
                case '(' -> floorLevel++;
                case ')' -> floorLevel--;
            }
        }
        
        System.out.println("The instructions take Santa to floor: " + floorLevel);
    }
    
    
    private static void partTwo(String inputLine)
    {
        int floorLevel = 0;
        boolean reachedBasement = false;
        
        for (int i = 0; i < inputLine.length(); i++)
        {
            switch (inputLine.charAt(i))
            {
                case '(' -> floorLevel++;
                case ')' -> floorLevel--;
            }
            
            if (floorLevel < 0)
            {
                reachedBasement = true;
                
                int instructionNumber = i + 1;
                System.out.println("The number of instructions it takes Santa to reach the basement is: " + instructionNumber);
                
                break;
            }
        }
        
        if (!reachedBasement)
        {
            System.out.println("Santa did not reach the basement within the given number of instructions.");
        }
    }
}
